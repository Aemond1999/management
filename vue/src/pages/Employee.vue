<template>
    <div>
        <el-main style="padding: 0">
            <el-button type="primary" size="medium" @click="openAddForm">新增</el-button>
            <!--新增弹窗-->
            <el-dialog title="新增职工" :visible.sync="AddFormVisible">
                <el-form :model="form" :rules="rules">
                    <el-form-item label="职工姓名" :label-width="formLabelWidth" prop="empName">
                        <el-input v-model="form.empName" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="所属部门" :label-width="formLabelWidth" prop="deptId">
                        <el-select v-model="form.deptId">
                            <el-option v-for="item in depts" :label="item.deptName" :value="item.id" :key="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="性别" :label-width="formLabelWidth" prop="gender">
                        <template>
                            <el-radio v-model="form.gender" label="男">男</el-radio>
                            <el-radio v-model="form.gender" label="女">女</el-radio>
                        </template>
                    </el-form-item>
                    <el-form-item label="家庭住址" :label-width="formLabelWidth" prop="address">
                        <el-input v-model="form.address" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="联系电话" :label-width="formLabelWidth" prop="phone">
                        <el-input v-model="form.phone" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="入职日期" :label-width="formLabelWidth" prop="hireDate">
                        <el-date-picker
                                v-model="form.hireDate"
                                type="date"
                                placeholder="选择日期"
                                format="yyyy 年 MM 月 dd 日"
                                value-format="yyyy-MM-dd">
                        </el-date-picker>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="AddFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="add">确 定</el-button>
                </div>
            </el-dialog>


            <!--修改弹窗-->
            <el-dialog title="修改职工信息" :visible.sync="UpdateFormVisible">
                <el-form :model="form" :rules="rules">
                    <el-form-item label="职工姓名" :label-width="formLabelWidth" prop="empName">
                        <el-input v-model="form.empName" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="所属部门" :label-width="formLabelWidth" prop="deptId">
                        <el-select v-model="form.deptId">
                            <el-option v-for="item in depts" :label="item.deptName" :value="item.id" :key="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="性别" :label-width="formLabelWidth" prop="gender">
                        <template>
                            <el-radio v-model="form.gender" label="男">男</el-radio>
                            <el-radio v-model="form.gender" label="女">女</el-radio>
                        </template>
                    </el-form-item>
                    <el-form-item label="家庭住址" :label-width="formLabelWidth" prop="address">
                        <el-input v-model="form.address" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="联系电话" :label-width="formLabelWidth" prop="phone">
                        <el-input v-model="form.phone" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="入职日期" :label-width="formLabelWidth" prop="hireDate">
                        <el-date-picker
                                v-model="form.hireDate"
                                type="date"
                                placeholder="选择日期"
                                format="yyyy 年 MM 月 dd 日"
                                value-format="yyyy-MM-dd">
                        </el-date-picker>
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
                    :data="employeeData"
                    border
                    fit
                    style="width: 100%">
                <el-table-column
                        fixed
                        prop="id"
                        label="职工编号">
                </el-table-column>
                <el-table-column
                        prop="empName"
                        label="职工姓名">
                </el-table-column>
                <el-table-column
                        prop="deptName"
                        label="所属部门">
                </el-table-column>
                <el-table-column
                        prop="gender"
                        label="性别">
                </el-table-column>
                <el-table-column
                        prop="address"
                        label="家庭住址">
                </el-table-column>
                <el-table-column
                        prop="phone"
                        label="电话号码">
                </el-table-column>
                <el-table-column
                        prop="hireDate"
                        label="入职日期">
                </el-table-column>
                <el-table-column
                        label="操作">
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
    name: "Employee",
    data() {
        return {
            employeeData: [],
            AddFormVisible: false,//弹框是否可见
            UpdateFormVisible: false,
            formLabelWidth: '80px',//弹框宽度
            loading: true,
            depts: [],
            form: {
                id: '',
                empName: '',
                deptId: '',
                phone: '',
                gender: '',
                address: '',
                hireDate: '',
            },
            rules: {
                empName: [
                    {required: true, message: '请输入名称', trigger: 'blur'},
                ],
                deptId: [
                    {required: true, message: '请选择', trigger: 'change'},
                ],
                phone: [
                    {required: true, message: '请输入名称', trigger: 'blur'},
                ],
                gender: [
                    {required: true, message: '请输入名称', trigger: 'blur'},
                ],
                address: [
                    {required: true, message: '请输入名称', trigger: 'blur'},
                ],
                hireDate: [
                    {type: 'date', required: true, message: '请输入名称', trigger: 'change'},
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
            axios.get("/api/employee/list").then((res) => {
                this.employeeData = res.data.data
                this.loading = false;
            })
        },
        //打开更新弹窗
        openUpdateForm(row) {
            this.form = row
            this.getDept()
            this.UpdateFormVisible = true
        },
        //更新
        update() {
            axios.put("/api/employee/update", this.form).then((res) => {
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
                axios.delete("/api/employee/delete/" + row.id).then((res) => {
                    if (res.data.code === 200) {
                        this.$message.success("更新成功")
                        this.list()
                    } else if (res.data.code === 403) {
                        this.$message.error("没有权限")
                    } else {
                        this.$message.error("更新失败")
                    }
                })
            })

        },
        //打开新增弹窗
        openAddForm() {
            this.AddFormVisible = true
            this.getDept()
            this.reset()
        },
        //新增
        add() {
            axios.post("/api/employee/add", this.form).then((res) => {
                if (res.data.code === 200) {
                    this.$message.success("更新成功")
                    this.list()
                    this.AddFormVisible = false
                } else if (res.data.code === 403) {
                    this.$message.error("没有权限")
                    this.AddFormVisible = false
                } else {
                    this.$message.error("更新失败")
                    this.AddFormVisible = false
                }
            })
        },
        //获取部门信息
        getDept() {
            axios.get("/api/dept/list").then((res) => {
                this.depts = res.data.data
            })
        }
        ,

    }
}
</script>

<style scoped>

</style>