<template>
    <div>
        <el-main style="padding: 0">
            <!--                弹窗-->
            <el-dialog :visible.sync="InfoVisible">
                <el-descriptions title="销售单明细" direction="vertical" :column="4" border>
                    <el-descriptions-item label="销售单号" :span="1">{{ info.id }}</el-descriptions-item>
                    <el-descriptions-item label="客户" :span="1">{{ info.provName }}</el-descriptions-item>
                    <el-descriptions-item label="制单人" :span="1">{{ info.empName }}</el-descriptions-item>
                    <el-descriptions-item label="总价" :span="1">{{ info.sum }}</el-descriptions-item>
                    <el-descriptions-item label="是否可用" :span="1">{{ info.status }}</el-descriptions-item>
                    <el-descriptions-item label="是否已审核" :span="1">{{ info.audit }}</el-descriptions-item>
                    <el-descriptions-item label="仓库" :span="1">{{ info.warehouseName }}</el-descriptions-item>
                    <el-descriptions-item label="制单日期" :span="1">{{ info.createTime }}</el-descriptions-item>
                    <el-descriptions-item label="采购日期" :span="1">{{ info.purchaseTime }}</el-descriptions-item>
                </el-descriptions>

                <el-table
                    :data="info.detail"
                    border
                    fit
                    tooltip-effect="dark"
                    ref="multipleTable"
                    style="width: 100%">
                    <el-table-column
                        prop="catalogueId"
                        label="商品编号">
                    </el-table-column>
                    <el-table-column
                        prop="catalogueName"
                        label="商品名称">
                    </el-table-column>
                    <el-table-column
                        prop="value"
                        label="单价">
                    </el-table-column>
                    <el-table-column
                        prop="unit"
                        label="单位">
                    </el-table-column>
                    <el-table-column
                        prop="purchaseNumber"
                        label="采购量">
                    </el-table-column>
                </el-table>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="InfoVisible= false">取 消</el-button>
                </div>
            </el-dialog>


            <!--按条件查询-->
            <el-form :inline="true" :model="queryForm" class="demo-form-inline" style="height: 45px">
                <el-form-item label="销售单号:">
                    <el-input v-model="queryForm.id" placeholder="销售单号" size="small"></el-input>
                </el-form-item>
                <el-form-item label="所属仓库:">
                    <el-select v-model="queryForm.warehouseId">
                        <el-option v-for="item in warehouse" :value="item.id" :key="item.id"
                                   :label="item.warehouseName"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="客户:">
                    <el-select v-model="queryForm.provId">
                        <el-option v-for="item in provider" :label="item.provName" :value="item.id" :key="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="small" @click="list" icon="el-icon-search">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="small" @click="reset" icon="el-icon-refresh">重置</el-button>
                </el-form-item>
            </el-form>

            <!-- 表格-->
            <el-table
                v-loading="loading"
                    :data="purchaseOrderData"
                    border
                    fit
                    style="width: 100%">
                <el-table-column
                    fixed
                    prop="id"
                    label="采购单号">
                </el-table-column>
                <el-table-column
                    prop="warehouseName"
                    label="仓库">
                </el-table-column>
                <el-table-column
                    prop="createTime"
                    label="制单日期"
                    sortable>
                </el-table-column>
                <el-table-column
                    prop="purchaseTime"
                    label="采购日期"
                    sortable>
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
                        <el-button type="primary" @click="purchase(scope.row)"
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
    name: "PurchaseOrder",
    data() {
        return {
            loading:true,
            purchaseOrderData: [],
            InfoVisible: false,//弹框是否可见
            formLabelWidth: '80px',//弹框宽度
            info: {
                id: '',
                provName: '',
                warehouseName: '',
                sum: '',
                status: false,
                audit: false,
                empName: '',
                message: '',
                createTime: '',
                purchaseTime: '',
                detail: []
            },
            provider:[],
            warehouse: [],
            queryForm: {
                id: '',
                warehouseId: '',
                provId: ''
            },
            //分页
            pagination: {
                total: '',
                size: 10,
                current: 1
            },

        }
    },
    created() {
        this.list()
        this.getProvider()
        this.getWarehouse()
    },
    methods: {
        handleCurrentChange(current) {
            this.pagination.current = current
            this.list()
        },
        //获取数据+条件查询
        list() {
            axios.get("/api/purchaseOrder/list/" + this.pagination.current + "/" + this.pagination.size + '?' + 'id=' + this.queryForm.id
                + '&' + 'warehouseId=' + this.queryForm.warehouseId + '&' + 'provId=' + this.queryForm.provId).then((res) => {
                this.purchaseOrderData = res.data.data.rows
                this.pagination.current = res.data.data.current
                this.pagination.total = res.data.data.total
                this.pagination.size = res.data.data.size
                this.loading = false
            })
        },
        openInfoForm(row) {
            this.info = row
            this.InfoVisible = true
        },
        getProvider() {
            axios.get("/api/provider/list").then((res) => {
                this.provider = res.data.data
            })
        },
        getWarehouse() {
            axios.get("/api/warehouse/list").then((res) => {
                this.warehouse = res.data.data
            })
        },
        reset() {
            this.queryForm.id = ''
            this.queryForm.warehouseId = ''
            this.queryForm.provId = ''
        },
        audit(row) {
            this.$confirm("审核确认？", "提示", {type: "info"}).then(() => {
                if (row.audit) {
                    this.$message.error("已审核")
                } else {
                    axios.put("/api/purchaseOrder/audit", {"id": row.id}).then((res) => {
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
        purchase(row) {
            this.$confirm("确认？", "提示", {type: "info"}).then(() => {
                if (row.status) {
                    if (row.audit) {
                        axios.post("/api/purchaseOrder/purchase", {"id": row.id}).then((res) => {
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
                        this.$message.error("采购单未审核")
                    }
                } else {
                    this.$message.error("采购单不可用")
                }
            })
        }



    }
}
</script>

<style scoped>

</style>