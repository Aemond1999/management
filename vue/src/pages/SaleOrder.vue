<template>
    <div>
        <el-main style="padding: 0">
            <!--                弹窗-->
            <el-dialog :visible.sync="InfoVisible">
                <el-descriptions title="销售单明细" direction="vertical" :column="4" border>
                    <el-descriptions-item label="销售单号" :span="1">{{ info.id }}</el-descriptions-item>
                    <el-descriptions-item label="客户" :span="1">{{ info.customerName }}</el-descriptions-item>
                    <el-descriptions-item label="制单人" :span="1">{{ info.empName }}</el-descriptions-item>
                    <el-descriptions-item label="总价" :span="1">{{ info.sum }}</el-descriptions-item>
                    <el-descriptions-item label="是否可用" :span="1">{{ info.status }}</el-descriptions-item>
                    <el-descriptions-item label="是否已审核" :span="1">{{ info.audit }}</el-descriptions-item>
                    <el-descriptions-item label="仓库" :span="1">{{ info.warehouseName }}</el-descriptions-item>
                    <el-descriptions-item label="制单日期" :span="1">{{ info.createTime }}</el-descriptions-item>
                    <el-descriptions-item label="销售日期" :span="1">{{ info.saleTime }}</el-descriptions-item>
                </el-descriptions>

                <el-table
                        :data="info.detail"
                        border
                        fit
                        tooltip-effect="dark"
                        ref="multipleTable"
                        style="width: 100%">
                    <el-table-column
                            prop="productId"
                            label="成品编号">
                    </el-table-column>
                    <el-table-column
                            prop="productName"
                            label="成品名称">
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
                            prop="saleNumber"
                            label="出库量">
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
                    <el-select v-model="queryForm.customerId">
                        <el-option v-for="item in customer" :label="item.customerName" :value="item.id" :key="item.id">
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
                    :data="saleOrderData"
                    border
                    fit
                    style="width:100%">
                <el-table-column
                        fixed
                        prop="id"
                        label="销售单号">
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
                        prop="saleTime"
                        label="销售日期"
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
                        <el-button type="primary" @click="sale(scope.row)"
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
    name: "SaleOrder",
    data() {
        return {
            loading: true,
            saleOrderData: [],
            InfoVisible: false,//弹框是否可见
            formLabelWidth: '80px',//弹框宽度
            info: {
                id: '',
                customerName: '',
                warehouseName: '',
                sum: '',
                status: false,
                audit: false,
                empName: '',
                message: '',
                createTime: '',
                saleTime: '',
                detail: []
            },
            customer: [],
            warehouse: [],
            queryForm: {
                id: '',
                warehouseId: '',
                customerId: ''
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
        this.getCustomer()
        this.getWarehouse()
    },
    methods: {
        handleCurrentChange(current) {
            this.pagination.current = current
            this.list()
        },
        //获取数据+条件查询
        list() {
            axios.get("/api/saleOrder/list/" + this.pagination.current + "/" + this.pagination.size + '?' + 'id=' + this.queryForm.id
                + '&' + 'warehouseId=' + this.queryForm.warehouseId + '&' + 'customerId=' + this.queryForm.customerId).then((res) => {
                this.saleOrderData = res.data.data.rows
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
        getCustomer() {
            axios.get("/api/customer/list").then((res) => {
                this.customer = res.data.data
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
            this.queryForm.customerId = ''

        },
        audit(row) {
            this.$confirm("审核确认？", "提示", {type: "info"}).then(() => {
                if (row.audit) {
                    this.$message.error("已审核")
                } else {
                    axios.put("/api/saleOrder/audit", {"id": row.id}).then((res) => {
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
        }
        ,
        sale(row) {
            this.$confirm("确认？", "提示", {type: "info"}).then(() => {
                if (row.status) {
                    if (row.audit) {
                        axios.post("/api/saleOrder/sale", {"id": row.id}).then((res) => {
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
                        this.$message.error("销售单未审核")
                    }
                } else {
                    this.$message.error("销售单不可用")
                }
            })
        }
    }
}
</script>

<style scoped>

</style>