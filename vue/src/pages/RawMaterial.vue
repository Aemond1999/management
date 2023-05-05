<template>
    <div>
        <el-main style="padding: 0;">
            <!--按条件查询-->
            <el-form :inline="true" :model="queryForm" class="demo-form-inline" style="height: 45px">
                <el-form-item label="原料编号:">
                    <el-input v-model="queryForm.id" placeholder="成品编号" size="small"></el-input>
                </el-form-item>
                <el-form-item label="原料名称:">
                    <el-input v-model="queryForm.rawMaterialName" placeholder="成品名称" size="small"></el-input>
                </el-form-item>
                <el-form-item label="所属仓库:">
                    <el-select v-model="queryForm.warehouseId">
                        <el-option v-for="item in warehouse" :label="item.warehouseName" :value="item.id"
                                   :key="item.id ">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="small" @click="list" icon="el-icon-search">查询</el-button>
                    <el-button type="primary" size="small" @click="reset" icon="el-icon-refresh">重置</el-button>
                </el-form-item>
            </el-form>
            <!--修改弹窗-->
            <el-dialog title="修改原料信息"  :visible.sync="UpdateFormVisible">
                <el-form :model="form" :rules="rules">
                    <el-form-item label="原料名" :label-width="formLabelWidth" prop="rawMaterialName">
                        <el-input v-model="form.rawMaterialName" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="单价" :label-width="formLabelWidth" prop="value">
                        <el-input v-model="form.value" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="库存数量" :label-width="formLabelWidth" prop="number">
                        <el-input v-model="form.number" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="预警值" :label-width="formLabelWidth" prop="warning">
                        <el-input v-model="form.warning" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="单位" :label-width="formLabelWidth" prop="unit">
                        <el-input v-model="form.unit" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="所属仓库" :label-width="formLabelWidth" prop="warehouseId">
                        <el-select v-model="form.warehouseId">
                            <el-option v-for="item in warehouse" :label="item.warehouseName" :value="item.id"
                                       :key="item.id ">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="UpdateFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="update">确 定</el-button>
                </div>
            </el-dialog>
            <!-- 表格-->
            <el-table
                    v-loading="loading"
                    :data="rawMaterialData"
                    border
                    fit
                    :row-class-name="tableRowClassName"
                    style="width: 100%">
                <el-table-column
                    fixed
                        prop="id"
                        label="原料编号">
                </el-table-column>
                <el-table-column
                        prop="warehouseName"
                        label="所属仓库">
                </el-table-column>
                <el-table-column
                        prop="rawMaterialName"
                        label="原料名">
                </el-table-column>
                <el-table-column
                        prop="value"
                        label="单价"
                        sortable>
                </el-table-column>
                <el-table-column
                        prop="number"
                        label="库存数量"
                        sortable>
                </el-table-column>
                <el-table-column
                        prop="warning"
                        label="预警值">
                </el-table-column>
                <el-table-column
                        prop="unit"
                        label="单位">
                </el-table-column>
                <el-table-column
                        label="操作"
                        width="150">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-edit" @click="openUpdateForm(scope.row)"
                                   size="small"
                                   circle></el-button>
                        <el-button type="danger" icon="el-icon-delete" @click="remove(scope.row)" size="small"
                                   circle></el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-main>
        <el-footer>
            <!--                分页-->
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
    name: "RawMaterial",
    data() {
        return {
            loading: true,
            rawMaterialData: [],
            AddFormVisible: false,//弹框是否可见
            UpdateFormVisible: false,
            formLabelWidth: '80px',//弹框宽度
            form: {
                id: '',
                warehouseId: '',
                rawMaterialName: '',
                value: '',
                number: '',
                warning: '',
                unit: '',
            },
            warehouse: [],
            queryForm: {
                id: '',
                rawMaterialName: '',
                warehouseId: ''
            },
            //分页
            pagination: {
                total: '',
                size: 10,
                current: 1
            },
            rules: {
                rawMaterialName: [
                    {required: true, message: '请输入名称', trigger: 'blur'},
                ],
                warehouseId: [
                    {required: true, message: '请输入名称', trigger: 'change'},
                ],
                number: [
                    {required: true, message: '请输入名称', trigger: 'blur'},
                ],
                value: [
                    {required: true, message: '请输入名称', trigger: 'blur'},
                ],
                unit: [
                    {required: true, message: '请输入名称', trigger: 'blur'},
                ],
                warning: [
                    {required: true, message: '请输入名称', trigger: 'blur'},
                ]
            }
        }
    },
    created() {
        this.list()
        this.getWarehouse()
    },
    methods: {
        tableRowClassName({row}) {
            console.log(row)
            if (Number.parseInt(row.warning)>Number.parseInt(row.number)) {
                return 'warning-row';
            } else {
                return 'success-row';
            }
        },
        handleCurrentChange(current) {
            this.pagination.current = current
            this.list()
        },
        //获取数据+条件查询
        list() {
            axios.get("/api/rawMaterial/list/" + this.pagination.current + "/" + this.pagination.size + '?' + 'id=' + this.queryForm.id
                + '&' + 'rawMaterialName=' + this.queryForm.rawMaterialName + '&' + 'warehouseId=' + this.queryForm.warehouseId).then((res) => {
                this.rawMaterialData = res.data.data.rows
                this.pagination.current = res.data.data.current
                this.pagination.total = res.data.data.total
                this.pagination.size = res.data.data.size
                this.loading = false
            })
        },
        //打开更新弹窗
        openUpdateForm(row) {
            this.form = row
            this.UpdateFormVisible = true
        },
        //更新
        update() {
            axios.put("/api/rawMaterial/update", this.form).then((res) => {
                if (res.data.code === 200) {
                    this.$message.success("更新成功")
                    this.list()
                    this.UpdateFormVisible = false
                } else if (res.data.code === 403) {
                    this.$message.error("没有权限")
                    this.UpdateFormVisible = false
                } else {
                    this.$message.error("更新失败")
                    this.UpdateFormVisible = false
                }
            })
        },
        //删除
        remove(row) {
            this.$confirm("此操作将永久删除数据", "提示", {type: "info"}).then(() => {
                axios.delete("/api/rawMaterial/delete/" + row.id).then((res) => {
                    if (res.data.code === 200) {
                        this.$message.success("删除成功")
                        this.list()
                        this.UpdateFormVisible = false
                    } else if (res.data.code === 403) {
                        this.$message.error("没有权限")
                        this.UpdateFormVisible = false
                    } else {
                        this.$message.error("删除失败")
                        this.UpdateFormVisible = false
                    }
                })
            })

        },
        reset(){
            this.queryForm.id=''
            this.queryForm.warehouseId=''
            this.queryForm.rawMaterialName=''
        },
        getWarehouse() {
            axios.get("/api/warehouse/list").then((res) => {
                this.warehouse = res.data.data
            })
        }
    }
}
</script>

<style scoped>
.el-table >>> .warning-row {
    background: oldlace;
}
.el-table >>> .success-row {
    background: #f0f9eb;
}
</style>