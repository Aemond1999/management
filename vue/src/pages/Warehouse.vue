<template>
    <el-main style="padding: 0">
        <el-button type="primary" size="medium" @click="openAddForm">新增</el-button>
        <!--新增弹窗-->
        <el-dialog title="新增仓库" :visible.sync="AddFormVisible">
            <el-form :model="form" :rules="rules">
                <el-form-item label="仓库名" :label-width="formLabelWidth" prop="warehouseName">
                    <el-input v-model="form.warehouseName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="仓库地址" :label-width="formLabelWidth" prop="address">
                    <el-input v-model="form.address" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="描述" :label-width="formLabelWidth" >
                    <el-input
                            type="textarea"
                            :rows="2"
                            placeholder="请输入内容"
                            v-model="form.description">
                    </el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="AddFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="add">确 定</el-button>
            </div>
        </el-dialog>

        <!--修改弹窗-->
        <el-dialog title="修改仓库信息" :visible.sync="UpdateFormVisible">
            <el-form :model="form" :rules="rules">
                <el-form-item label="仓库名" :label-width="formLabelWidth" prop="warehouseName">
                    <el-input v-model="form.warehouseName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="仓库地址" :label-width="formLabelWidth" prop="address">
                    <el-input v-model="form.address" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="描述" :label-width="formLabelWidth" >
                    <el-input
                            type="textarea"
                            :rows="2"
                            placeholder="请输入内容"
                            v-model="form.description">
                    </el-input>
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
                :data="warehouseData"
                border
                fit
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="id"
                    label="仓库编号">
            </el-table-column>
            <el-table-column
                    prop="warehouseName"
                    label="仓库名">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="仓库地址"
                    >
            </el-table-column>
            <el-table-column
                    prop="description"
                    label="描述"
                    >
            </el-table-column>
            <el-table-column
                    label="操作"
                    >
                <template slot-scope="scope">
                    <el-button type="primary" icon="el-icon-edit" @click="openUpdateForm(scope.row)" size="small"
                               circle></el-button>
                    <el-button type="danger" icon="el-icon-delete" @click="remove(scope.row)" size="small"
                               circle></el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-main>
</template>

<script>
import axios from "axios";

export default {
    name: "Warehouse",
    data() {
        return {
            warehouseData: [],
            AddFormVisible: false,//弹框是否可见
            UpdateFormVisible: false,
            formLabelWidth: '85px',//弹框宽度
            loading: true,
            form: {
                id: '',
                warehouseName: '',
                address: '',
                description: ''
            },
            rules: {
                warehouseName: [
                    {required: true, message: '请输入名称', trigger: 'blur'},
                ],
                address: [
                    {required: true, message: '请输入名称', trigger: 'blur'},
                ],

            }
        }
    },
    created() {
        this.list();
    },
    methods: {
        reset() {
            this.form = {}
        },
        //获取数据
        list() {
            axios.get("/api/warehouse/list").then((res) => {
                this.warehouseData = res.data.data
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
            axios.put("/api/warehouse/update", this.form).then((res) => {
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
                axios.delete("/api/warehouse/delete/" + row.id).then((res) => {
                    if (res.data.code === 200) {
                        this.$message.success("删除成功")
                        this.list()
                    } else if (res.data.code === 403) {
                        this.$message.error("没有权限")
                    } else {
                        this.$message.error("删除失败")
                    }
                })
            })

        },
        //打开新增弹窗
        openAddForm() {
            this.AddFormVisible = true
            this.reset()
        },
        //新增
        add() {
            axios.post("/api/warehouse/add", this.form).then((res) => {
                if (res.data.code === 200) {
                    this.$message.success("添加成功")
                    this.list()
                    this.AddFormVisible = false
                } else if (res.data.code === 403) {
                    this.$message.error("没有权限")
                    this.AddFormVisible = false
                } else {
                    this.$message.error("添加失败")
                    this.AddFormVisible = false
                }
            })
        }
    }
}
</script>

<style scoped>

</style>