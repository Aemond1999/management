<template>
    <div>
        <el-main >
                <el-form ref="form" :model="form" :rules="rules" label-width="90px" style="width: 500px ">
                    <el-form-item label="客户:" prop="customerId">
                        <el-select v-model="form.customerId"  placeholder="请选择客户" >
                            <el-option v-for="item in customer" :value="item.id" :key="item.id" :label="item.customerName"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="仓库:" prop="warehouseId">
                        <el-select v-model="form.warehouseId" placeholder="请选择仓库">
                            <el-option v-for="item in warehouse" :value="item.id" :key="item.id" :label="item.warehouseName"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="备注:">
                        <el-input type="textarea" v-model="form.message"></el-input>
                    </el-form-item>
                    <el-form-item label="商品明细:">
                        <el-button type="primary" icon="el-icon-circle-plus" @click="openProduct">添加商品</el-button>
                    </el-form-item>
                </el-form>

                <el-dialog title="添加商品" :visible.sync="AddFormVisible">
                    <el-table
                        :data="product"
                        border
                        tooltip-effect="dark"
                        @selection-change="handleSelectionChange"
                        ref="multipleTable"
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
                            label="单价">
                        </el-table-column>
                        <el-table-column
                            prop="unit"
                            label="单位">
                        </el-table-column>
                        <el-table-column
                            prop="number"
                            label="当前库存"
                            width="130">
                        </el-table-column>
                        <el-table-column
                            prop="saleNumber"
                            label="销售量"
                            width="130">
                            <template slot-scope="scope">
                                <input type="text" v-model="scope.row.saleNumber"
                                       style="border:2px deepskyblue;height: 30px;width: 80px;background: #d9d9d9;color: red">
                            </template>
                        </el-table-column>
                    </el-table>
                    <div slot="footer" class="dialog-footer" style="text-align: left">
                        <el-button @click="cancel">取 消</el-button>
                        <el-button type="primary" @click="add">确 定</el-button>
                    </div>
                </el-dialog>

                <el-table
                    :data="form.detail"
                    border
                    fit
                    style="width: 60%;position: relative;left: 83px">
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
                        label="单价">
                    </el-table-column>
                    <el-table-column
                        prop="saleNumber"
                        label="销售量">
                    </el-table-column>
                    <el-table-column
                        prop="unit"
                        label="单位">
                    </el-table-column>
                </el-table>
                <el-form style="position: relative;left: 40px">
                    <el-form-item label="合计:">
                        <el-input v-model="form.sum" style="width: 150px"></el-input>
                    </el-form-item>
                    <el-button  style="position: relative;left: 45px"  type="primary" icon="el-icon-success"  @click="addSaleOrder('form')">生成订单</el-button>
                </el-form>
        </el-main>

    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "Sale",
    data() {
        return {
            AddFormVisible: false,//弹框是否可见
            formLabelWidth: '80px',//弹框宽度
            form: {
                id: '',
                customerId: '',
                sum: '',
                message: '',
                warehouseId: '',
                detail: []
            },
            warehouse: [],
            customer: [],
            product: [],
            chosen: [],
            rules: {
                customerId: [
                    {required: true, message: '请选择客户', trigger: 'blur'},
                ],
                warehouseId: [
                    {required: true, message: '请选择仓库', trigger: 'change'},
                ],

            }
        }
    },
    created() {
        this.getCustomer()
        this.getWarehouse()
    },
    methods: {
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
        getCustomer() {
            axios.get("/api/customer/list").then((res) => {
                this.customer = res.data.data
            })
        },
        openProduct() {
            this.AddFormVisible = true
            this.getProduct()
        },
        handleSelectionChange(val) {
            this.chosen = val;
        },
        cancel() {
            this.AddFormVisible = false
            this.chosen = []
        },
        add() {
            this.form.detail = this.chosen
            this.getSum()
            this.AddFormVisible = false
        },
        getSum() {
            let sum = 0
            for (let i = 0; i < this.form.detail.length; i++) {
                sum += (this.form.detail[i].saleNumber * this.form.detail[i].value)
            }
           this.form.sum=sum
        },
        addSaleOrder(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    axios.post("/api/saleOrder/add", this.form).then((res) => {
                        if (res.data.code === 200) {
                            this.$message.success("保存成功")
                        } else if (res.data.code === 403) {
                            this.$message.error("没有权限")
                        } else {
                            this.$message.error("保存失败")
                        }
                    })
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        }
    }
}
</script>

<style scoped>
::-webkit-scrollbar {
    width: 0 !important;
    height: 0;
}
</style>