<template>
<div>
    <el-main >
        <!-- 表格-->
        <el-table
                :data="roleData"
                border
                fit
                v-loading="loading"
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="id"
                    label="角色编号">
            </el-table-column>
            <el-table-column
                    prop="roleName"
                    label="角色名">
            </el-table-column>
            <el-table-column
                    prop="description"
                    label="描述">
            </el-table-column>
        </el-table>
    </el-main>
</div>
</template>

<script>
import axios from "axios";

export default {
    name: "Role",
    data() {
        return {
            roleData: [],
            loading: true
        }
    },
    created() {
     this.list()
    },
    methods: {
        //获取数据
        list() {
            axios.get("/api/role/list").then((res) => {
                this.roleData = res.data.data
                this.loading=false
            })
        }
    }
}
</script>

<style scoped>

</style>