<template>
    <div>
        <el-main>



            <el-form ref="form" :model="form" :rules="rules" label-width="90px" style="width: 500px ">
                <el-form-item label="供应商:" prop="provId">
                    <el-select v-model="form.provId"  placeholder="请选择供应商" >
                        <el-option v-for="item in provider" :value="item.id" :key="item.id" :label="item.provName"></el-option>
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
                    <el-button type="primary" icon="el-icon-circle-plus" @click="openCatalogue">添加商品</el-button>
                </el-form-item>
            </el-form>


            <el-dialog title="添加商品" :visible.sync="AddFormVisible">
                <el-table
                        :data="catalogue"
                        border
                        fit
                        tooltip-effect="dark"
                        @selection-change="handleSelectionChange"
                        ref="multipleTable">
                    <el-table-column
                            type="selection"
                            width="40">
                    </el-table-column>
                    <el-table-column
                            prop="id"
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
                        <template slot-scope="scope">
                            <input type="text" v-model="scope.row.purchaseNumber"
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
                        label="商品编号"
                        width="150">
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
                        prop="purchaseNumber"
                        label="采购数">
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
                <el-button style="position: relative;left: 45px" type="primary" icon="el-icon-success"
                           @click="addPurchaseOrder('form')">生成订单
                </el-button>
            </el-form>


        </el-main>
    </div>
</template>

<script>
import axios from "axios";
export default {
    name: "Purchase",
    data() {
        return {
            AddFormVisible: false,//弹框是否可见
            formLabelWidth: '80px',//弹框宽度
            form: {
                id: '',
                provId: '',
                sum: '',
                message: '',
                warehouseId: '',
                detail: []
            },
            warehouse: [],
            provider: [],
            catalogue: [],
            chosen: [],
            rules: {
                provId: [
                    {required: true, message: '请选择供应商', trigger: 'change'},
                ],
                warehouseId: [
                    {required: true, message: '请选择日期', trigger: 'change'},
                ],

            }
        }
    },
    created() {
        this.getProvider()
        this.getWarehouse()
    },
    methods: {
        getWarehouse() {
            axios.get("/api/warehouse/list").then((res) => {
                this.warehouse = res.data.data
            })
        },
        getProvider() {
            axios.get("/api/provider/list").then((res) => {
                this.provider = res.data.data
            })
        },
        getCatalogue() {
            axios.get("/api/catalogue/listById/" + this.form.provId).then((res) => {
                this.catalogue = res.data.data
            })
        },
        openCatalogue() {
            this.AddFormVisible = true
            this.getCatalogue()
        },
        handleSelectionChange(val) {
            this.chosen = val;
        },
        cancel() {
            this.AddFormVisible = false
            this.chosen = []
        },
        getSum() {
            let sum = 0
            for (let i = 0; i < this.form.detail.length; i++) {
                sum += (this.form.detail[i].purchaseNumber * this.form.detail[i].value)
            }
            this.form.sum = sum
        },
        add(){
            this.form.detail = this.chosen
            this.getSum()
            this.AddFormVisible = false
        },
        addPurchaseOrder(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    axios.post("/api/purchaseOrder/add", this.form).then((res) => {
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