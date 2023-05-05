<template>
    <el-main style="padding: 0">
        <el-upload style="left: 650px; position: relative;"
                   action="/api/user/upload"
                   class="avatar-uploader"
                   :show-file-list="false"
                   :headers="header"
                   :on-success="handleAvatarSuccess"
                   :before-upload="beforeAvatarUpload">
            <el-avatar :size="160"><img v-if="imageUrl" :src="imageUrl" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i></el-avatar>
        </el-upload>
        <p style="left: 680px; position: relative;font-size:25px">个人信息</p>
        <el-descriptions  direction="vertical" :column="3" border style="width: 500px ;left: 500px; position: relative">
            <el-descriptions-item label="用户名">{{ userInfoForm.username }}</el-descriptions-item>
            <el-descriptions-item label="职工姓名">{{ userInfoForm.employee.empName }}</el-descriptions-item>
            <el-descriptions-item label="家庭住址" :span="2">{{ userInfoForm.employee.address }}</el-descriptions-item>
            <el-descriptions-item label="性别"> <el-tag size="small">{{ userInfoForm.employee.gender}}</el-tag></el-descriptions-item>
            <el-descriptions-item label="所属部门">
                <el-tag size="small">{{ userInfoForm.employee.deptName}}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="入职日期">{{ userInfoForm.employee.hireDate }}</el-descriptions-item>
        </el-descriptions>


    </el-main>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            header: {
                Authorization: window.sessionStorage.getItem('token')
            },
            name: "UserInfo",
            imageUrl: window.sessionStorage.getItem('avatar'),
            userInfoForm: {
                id: '',
                username:'',
                employee:{
                    id:'',
                    deptId:'',
                    empName:'',
                    deptName:'',
                    gender:'',
                    address:'',
                    phone:'',
                    hireDate:''
                }


            }
        }
    },created() {
        this.getUserInfo()
    },
    methods: {
        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
            window.sessionStorage.setItem('avatar', this.imageUrl)
            this.$message.success("修改成功")

        },
        beforeAvatarUpload(file) {
            const isLt2M = file.size / 1024 / 1024 < 2;
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isLt2M;
        },
        getUserInfo(){
            axios("/api/user/userInfo/"+window.sessionStorage.getItem('id')).then((res)=>{
                console.log(res.data)
                this.userInfoForm.username=res.data.data.username
                this.userInfoForm.employee=res.data.data.employeeVO
            })
        }

    }

}
</script>

<style scoped>

.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

.avatar {
    width: 178px;
    height: 178px;
    display: block;
}

</style>