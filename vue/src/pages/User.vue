<template>
    <div>
        <el-main style="padding: 0">
            <el-button type="primary" size="small" @click="openAddForm">新增</el-button>
            <!--新增弹窗-->
            <el-dialog title="新增用户" :visible.sync="AddFormVisible">
                <el-form :model="form" :rules="rules">
                    <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
                        <el-input  v-model="form.username" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
                        <el-input v-model="form.password" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="角色:" :label-width="formLabelWidth" prop="roleId">
                        <el-select v-model="form.roleId" placeholder="请选择角色">
                            <el-option v-for="item in role" v-bind:value="item.id" :key="item.id"
                                       :label="item.roleName">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="所属员工:" :label-width="formLabelWidth" prop="roleId">
                        <el-select v-model="form.empId" placeholder="请选择员工">
                            <el-option v-for="item in employee" v-bind:value="item.id" :key="item.id"
                                       :label="item.empName">
                            </el-option>
                        </el-select>
                    </el-form-item>

                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="AddFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="add">确 定</el-button>
                </div>
            </el-dialog>
            <!--修改弹窗-->
            <el-dialog title="修改用户信息" :visible.sync="UpdateFormVisible">
                <el-form :model="form" :rules="rules">
                    <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
                        <el-input v-model="form.username" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" :label-width="formLabelWidth" prop="password">
                        <el-input v-model="form.password" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="角色:" :label-width="formLabelWidth" prop="roleId">
                        <el-select v-model="form.roleId" placeholder="请选择角色">
                            <el-option v-for="item in role" v-bind:value="item.id" :key="item.id"
                                       :label="item.roleName">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="所属员工:" :label-width="formLabelWidth" prop="roleId">
                        <el-select v-model="form.empId" placeholder="请选择员工">
                            <el-option v-for="item in employee" v-bind:value="item.id" :key="item.id"
                                       :label="item.empName">
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
                    :data="userData"
                    border
                    fit
                    v-loading="loading"
                    style="width: 100%">
                <el-table-column
                        fixed
                        prop="id"
                        label="ID">
                </el-table-column>
                <el-table-column
                        prop="username"
                        label="用户名">
                </el-table-column>
                <el-table-column
                        prop="empName"
                        label="所属员工">
                </el-table-column>
                <el-table-column
                        prop="roleName"
                        label="角色">
                </el-table-column>
                <el-table-column
                        label="操作"
                        width="200">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-edit" @click="openUpdateForm(scope.row)" size="small"
                                   circle></el-button>
                        <el-button type="danger" icon="el-icon-delete" @click="remove(scope.row)" size="small"
                                   circle></el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-main>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "User",
    data() {
        return {
            loading: true,
            userData: [],
            AddFormVisible: false,//弹框是否可见
            UpdateFormVisible: false,
            formLabelWidth: '85px',//弹框宽度
            form: {
                id: '',
                username: '',
                empId: '',
                password: '',
                roleId: ''
            },
            role: [],
            employee: [],
            rules: {
                username: [
                    {required: true, message: '请输入名称', trigger: 'blur'},
                ],
                password: [
                    {required: true, message: '请输入名称', trigger: 'blur'},
                ],
                roleId: [
                    {required: true, message: '请输入名称', trigger: 'change'},
                ],
                empId: [
                    {required: true, message: '请输入名称', trigger: 'change'},
                ],

            }
        }
    },
    created() {
        this.list()
        this.getRole()
        this.getEmployee()
    },
    methods: {
        //获取数据
        list() {
            axios.get("/api/user/list").then((res) => {
                this.userData = res.data.data
                this.loading = false
            })
        },
        //打开更新弹窗
        openUpdateForm(row) {
            this.reset()
            this.form = row
            this.UpdateFormVisible = true
        },
        //更新
        update() {
            axios.put("/api/user/update", this.form).then((res) => {
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
                axios.delete("/api/user/delete/" + row.id).then((res) => {
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
            this.reset()
            this.AddFormVisible = true
        },
        //新增
        add() {
            axios.post("/api/user/add", this.form).then((res) => {
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

        },
        getRole() {
            axios.get("/api/role/list").then((res) => {
                this.role = res.data.data
            })
        },
        getEmployee() {
            axios.get("/api/employee/list").then((res) => {
                this.employee = res.data.data
            })
        },
        reset() {
            this.form = {}
        }

    }
}
</script>

<style scoped>

</style>