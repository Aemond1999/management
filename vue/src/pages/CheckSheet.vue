<template>
    <div>
        <el-main>
            <el-form :inline="true" :model="queryForm" class="demo-form-inline">
                <el-form-item label="单据编号:">
                    <el-input v-model="queryForm.id" placeholder="单据编号" size="small"></el-input>
                </el-form-item>
                <el-form-item label="库存类型:">
                    <el-select v-model="queryForm.type">
                        <el-option :value="0" :label="'原料'"></el-option>
                        <el-option :value="1" :label="'成品'"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="所属仓库:">
                    <el-select v-model="queryForm.warehouseId">
                        <el-option v-for="item in warehouse" :value="item.id" :key="item.id"
                                   :label="item.warehouseName"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="small" @click="list" icon="el-icon-search">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="small" @click="reset" icon="el-icon-refresh">重置</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="small" icon="el-icon-circle-plus" @click="AddFormVisible=true">
                        新增盘点单
                    </el-button>
                </el-form-item>
            </el-form>


            <el-dialog :visible.sync="AddFormVisible">
                <el-form ref="form" :inline="true" :model="form" :rules="rules" class="demo-form-inline">
                    <el-form-item label="盘点仓库:" :label-width="formLabelWidth" prop="warehouseId">
                        <el-select v-model="form.warehouseId">
                            <el-option v-for="item in warehouse" :value="item.id" :key="item.id"
                                       :label="item.warehouseName"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="库存类型:" prop="type">
                        <el-select v-model="form.type">
                            <el-option :value="0" :label="'原料'"></el-option>
                            <el-option :value="1" :label="'成品'"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="备注:" :label-width="formLabelWidth">
                        <el-input v-model="form.message" size="small"></el-input>
                    </el-form-item>
                </el-form>


                <!--                弹出添加库存按钮-->
                <el-button style="position: relative;left: 100px;" type="primary" icon="el-icon-circle-plus"
                           @click="addInventory">新增
                </el-button>

                <el-descriptions direction="vertical" :column="5" border>
                    <el-descriptions-item label="库存编号" :span="1">{{ inventory.id }}</el-descriptions-item>
                    <el-descriptions-item label="库存名称" :span="1">{{ inventory.name }}</el-descriptions-item>
                    <el-descriptions-item label="数量" :span="1">{{ inventory.number }}</el-descriptions-item>
                    <el-descriptions-item label="价格" :span="1">{{ inventory.value }}</el-descriptions-item>
                    <el-descriptions-item label="单位" :span="1">{{ inventory.unit }}</el-descriptions-item>
                </el-descriptions>
                <br>

                <el-form :model="form" :rules="rules" style="position: relative;left: 40px;">
                    <el-form-item label="实际库存:" prop="realNumber">
                        <el-input v-model="form.realNumber" @change="count" style="width: 150px"
                                  placeholder="请输入实际库存"></el-input>
                    </el-form-item>
                    <el-form-item label="盈亏库存:" style="position: relative;left: 10px;">
                        <el-input v-model="profitAndLoss" style="width: 150px" readonly></el-input>
                    </el-form-item>
                </el-form>


                <div slot="footer" class="dialog-footer" style="text-align: left">
                    <el-button @click="AddFormVisible=false">取 消</el-button>
                    <el-button type="primary" @click="save('form')">确 定</el-button>
                </div>
                <!-- 嵌套对话框-->
                <el-dialog :visible.sync="productFormVisible" width="40%">
                    <el-table
                            :data="product"
                            border
                            class="single-select-table"
                            tooltip-effect="dark"
                            @selection-change="handleSelectionChange"
                            ref="table"
                            fit
                            style="width: 100%">
                        <el-table-column
                                type="selection">
                        </el-table-column>
                        <el-table-column
                                prop="id"
                                label="成品编号">
                        </el-table-column>
                        <el-table-column
                                prop="productName"
                                label="成品名称">
                        </el-table-column>
                        <el-table-column
                                prop="value"
                                label="单价"
                                sortable>
                        </el-table-column>
                        <el-table-column
                                prop="unit"
                                label="单位">
                        </el-table-column>
                        <el-table-column
                                prop="number"
                                label="库存"
                                sortable>
                        </el-table-column>
                    </el-table>
                    <div slot="footer" class="dialog-footer" style="text-align: left">
                        <el-button @click="productFormVisible=false">取 消</el-button>
                        <el-button type="primary" @click="confirm">确 定</el-button>
                    </div>
                </el-dialog>

                <el-dialog :visible.sync="rawMaterialFormVisible" width="40%">
                    <el-table
                            :data="rawMaterial"
                            border
                            class="single-select-table"
                            tooltip-effect="dark"
                            @selection-change="handleSelectionChange"
                            ref="table"
                            fit
                            style="width: 100%">
                        <el-table-column
                                type="selection">
                        </el-table-column>
                        <el-table-column
                                prop="id"
                                label="原料编号">
                        </el-table-column>
                        <el-table-column
                                prop="rawMaterialName"
                                label="原料名称">
                        </el-table-column>
                        <el-table-column
                                prop="value"
                                label="单价"
                                sortable>
                        </el-table-column>
                        <el-table-column
                                prop="unit"
                                label="单位">
                        </el-table-column>
                        <el-table-column
                                prop="number"
                                label="库存"
                                sortable>
                        </el-table-column>
                    </el-table>
                    <div slot="footer" class="dialog-footer" style="text-align: left">
                        <el-button @click="rawMaterialFormVisible=false">取 消</el-button>
                        <el-button type="primary" @click="confirm">确 定</el-button>
                    </div>
                </el-dialog>

            </el-dialog>


            <el-dialog :visible.sync="InfoVisible">
                <el-descriptions title="盘点明细" direction="vertical" :column="5" border>
                    <el-descriptions-item label="盘点人员" :span="1">{{ info.empName }}</el-descriptions-item>
                    <el-descriptions-item label="库存类型" :span="1">{{ info.type === 1 ? '成品' : '原料' }}
                    </el-descriptions-item>
                    <el-descriptions-item label="原始库存" :span="1">{{ info.originalNumber }}</el-descriptions-item>
                    <el-descriptions-item label="实际库存" :span="1">{{ info.realNumber }}</el-descriptions-item>
                    <el-descriptions-item label="盈亏库存" :span="1">{{ info.realNumber - info.originalNumber}}</el-descriptions-item>
                    <el-descriptions-item label="是否可用" :span="1">{{ info.status }}</el-descriptions-item>
                    <el-descriptions-item label="是否已审核" :span="1">{{ info.audit }}</el-descriptions-item>
                    <el-descriptions-item label="盘点仓库" :span="1">{{ info.warehouseName }}</el-descriptions-item>
                    <el-descriptions-item label="制单日期" :span="1">{{ info.createTime }}</el-descriptions-item>
                    <el-descriptions-item label="盘点日期" :span="1">{{ info.checkTime }}</el-descriptions-item>

                    <el-descriptions-item label="库存编号" :span="1">{{ inventory.id }}</el-descriptions-item>
                    <el-descriptions-item label="名称" :span="1">{{ inventory.name }}</el-descriptions-item>
                    <el-descriptions-item label="当前数量" :span="1">{{ inventory.number }}</el-descriptions-item>
                    <el-descriptions-item label="价格" :span="1">{{ inventory.value }}</el-descriptions-item>
                    <el-descriptions-item label="单位" :span="1">{{ inventory.unit }}</el-descriptions-item>

                </el-descriptions>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="quxiao">取 消</el-button>
                </div>
            </el-dialog>


            <el-table
                    :data="checkSheetData"
                    border
                    fit
                    v-loading="loading">
                <el-table-column
                        prop="id"
                        label="单据编号">
                </el-table-column>
                <el-table-column
                        prop="createTime"
                        label="制单日期">
                </el-table-column>
                <el-table-column
                        prop="checkTime"
                        label="盘点日期">
                </el-table-column>
                <el-table-column
                        prop="status"
                        label="状态"
                        sortable>
                    <template slot-scope="scope">
                        {{ scope.row.status ? "可用" : "不可用" }}
                    </template>
                </el-table-column>
                <el-table-column
                        prop="audit"
                        label="审核"
                        sortable>
                    <template slot-scope="scope">
                        {{ scope.row.audit ? "已审核" : "未审核" }}
                    </template>
                </el-table-column>
                <el-table-column
                        label="操作">
                    <template slot-scope="scope">
                        <el-button type="primary" @click="openInfoForm(scope.row)"
                                   size="small">详情
                        </el-button>
                        <el-button type="primary" @click="audit(scope.row)"
                                   size="small">审核
                        </el-button>
                        <el-button type="primary" @click="adjust(scope.row)"
                                   size="small">确认
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>


        </el-main>
        <el-footer>
            <!-- 分页-->
            <el-pagination
                    @current-change="handleCurrentChange"
                    :current-page="pagination.current"
                    :page-size=pagination.size
                    layout="prev, pager, next, jumper"
                    :total=pagination.total>
            </el-pagination>
        </el-footer>

    </div>
</template>

<script>

import axios from "axios";

export default {
    name: "CheckSheet",
    data() {
        return {
            loading: true,
            AddFormVisible: false,//弹框是否可见
            productFormVisible: false,//弹框是否可见
            rawMaterialFormVisible: false,//弹框是否可见
            InfoVisible: false,
            formLabelWidth: '100px',//弹框宽度
            checkSheetData: [],
            form: {
                id: '',
                type: '',
                message: '',
                originalNumber: '',
                realNumber: '',
                inventoryId: '',
                warehouseId: ''
            },
            queryForm: {
                id: '',
                type: '',
                warehouseId: ''
            },
            //分页
            pagination: {
                total: '',
                size: 10,
                current: 1
            },
            warehouse: [],
            rawMaterial: [],
            product: [],
            info: {
                id: '',
                customerName: '',
                warehouseName: '',
                status: false,
                audit: false,
                empName: '',
                message: '',
                createTime: '',
                checkTime: '',
                originalNumber: '',
                realNumber: '',
                inventory: {},
                type: ''
            },
            chosen: [],
            inventory: {
                id: '',
                name: '',
                value: '',
                number: '',
                unit: ''
            },
            profitAndLoss: '',
            rules: {
                type: [
                    {required: true, message: '请选择供应商', trigger: 'change'},
                ],
                warehouseId: [
                    {required: true, message: '请选择仓库', trigger: 'change'},
                ],
                realNumber: [
                    {required: true, message: '请输入信息', trigger: 'blur'},
                ]

            }
        }
    },
    created() {
        this.getWarehouse()
        this.list()
    },
    methods: {
        list() {
            axios.get("/api/checkSheet/list/" + this.pagination.current + "/" + this.pagination.size + '?' + 'id=' + this.queryForm.id
                + '&' + 'type=' + this.queryForm.type + '&' + 'warehouseId=' + this.queryForm.warehouseId).then((res) => {
                this.checkSheetData = res.data.data.rows
                this.pagination.current = res.data.data.current
                this.pagination.total = res.data.data.total
                this.pagination.size = res.data.data.size
                this.loading = false
            })
        },
        getWarehouse() {
            axios.get("/api/warehouse/list").then((res) => {
                this.warehouse = res.data.data
            })
        },
        getProduct() {
            axios.get("/api/product/listById/" + this.form.warehouseId).then((res) => {
                this.product = res.data.data
            })
        },
        getRawMaterial() {
            axios.get("/api/rawMaterial/listById/" + this.form.warehouseId).then((res) => {
                this.product = res.data.data
            })
        },
        addInventory() {
            if (this.form.type === 0) {
                this.rawMaterialFormVisible = true
                this.getRawMaterial()
            } else if (this.form.type === 1) {
                this.productFormVisible = true
                this.getProduct()
            } else {
                this.$message.error("请确认盘点类型！")
            }
        },
        handleSelectionChange(val) {
            if (val.length > 1) {
                this.$refs.selectTable.clearSelection()
                this.$refs.selectTable.toggleRowSelection(val.pop())
            } else {
                this.chosen = val;
            }
        },
        confirm() {
            if (this.form.type === 0) {
                this.inventory.name = this.chosen[0].rawMaterialName
                this.rawMaterialFormVisible = false
            } else {
                this.inventory.name = this.chosen[0].productName
                this.productFormVisible = false
            }
            this.inventory.id = this.chosen[0].id
            this.inventory.value = this.chosen[0].value
            this.inventory.unit = this.chosen[0].unit
            this.inventory.number = this.chosen[0].number
            this.form.inventoryId = this.chosen[0].id
            this.form.originalNumber = this.chosen[0].number
        },
        count() {
            this.profitAndLoss = this.form.realNumber - this.form.originalNumber
        },
        save(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    axios.post("/api/checkSheet/add", this.form).then((res) => {
                        if (res.data.code === 200) {
                            this.$message.success("保存成功")

                        } else if (res.data.code === 403) {
                            this.$message.error("没有权限")
                        } else {
                            this.$message.error("保存失败")
                        }
                        this.AddFormVisible=false
                    })
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        openInfoForm(row) {
            this.info = row
            if (row.type === 1) {
                this.inventory.name = row.inventory.productName
            } else {
                this.inventory.name = row.inventory.rawMaterialName
            }
            this.inventory.number = row.inventory.number
            this.inventory.value = row.inventory.value
            this.inventory.unit = row.inventory.unit
            this.inventory.id = row.inventory.id
            this.InfoVisible = true

        },
        quxiao() {
            this.InfoVisible = false
            this.inventory = {}
        },
        audit(row) {
            this.$confirm("审核确认？", "提示", {type: "info"}).then(() => {
                if (row.audit) {
                    this.$message.error("已审核")
                } else {
                    axios.put("/api/checkSheet/audit", {"id": row.id}).then((res) => {
                        if (res.data.code === 200) {
                            this.$message.success("审核成功")
                            this.list()
                        } else if (res.data.code === 403) {
                            this.$message.error("没有权限")
                        } else {
                            this.$message.error("审核失败")
                        }
                    })
                }
            })
        },
        reset() {
            this.queryForm.id = ''
            this.queryForm.warehouseId = ''
            this.queryForm.type = ''
        },
        adjust(row){
            this.$confirm("确认？", "提示", {type: "info"}).then(() => {
                if (row.status) {
                    if (row.audit) {
                        axios.post("/api/checkSheet/adjust", {"id": row.id}).then((res) => {
                            if (res.data.code === 200) {
                                this.$message.success("操作成功")
                                this.list()
                            } else if (res.data.code === 403) {
                                this.$message.error("没有权限")
                            } else {
                                this.$message.error("操作失败")
                            }
                        })
                    } else {
                        this.$message.error("盘点单未审核")
                    }
                } else {
                    this.$message.error("盘点单不可用")
                }
            })
        }


    }
}
</script>

<style scoped>

</style>