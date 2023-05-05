<template>
            <div class="common-layout">
                <el-container>
                    <el-aside width="250px">
                        <el-menu style="border-bottom: 0;border-right: 0"
                                default-active="1"
                                class="el-menu-vertical-demo"

                                background-color="#545c64"
                                text-color="#fff"
                                active-text-color="#ffd04b">
                            <el-menu-item index="9">
                                <template slot="title">
                                    <i class="el-icon-s-home"></i>
                                    <span @click="toHome">首页</span>
                                </template>
                            </el-menu-item>

                            <el-submenu index="1">
                                <template slot="title">
                                    <i class="el-icon-s-tools"></i>
                                    <span>基础设置</span>
                                </template>
                                <el-menu-item-group>
                                    <el-menu-item index="1-1" @click="toEmployee">职工管理</el-menu-item>
                                    <el-menu-item index="1-2" @click="toWarehouse">仓库管理</el-menu-item>
                                    <el-menu-item index="1-3" @click="toProvider">供应商管理</el-menu-item>
                                    <el-menu-item index="1-4" @click="toCustomer">客户管理</el-menu-item>
                                </el-menu-item-group>
                            </el-submenu>


                            <el-submenu index="2">
                                <template slot="title">
                                    <i class="el-icon-s-goods"></i>
                                    <span>库存管理</span>
                                </template>
                                <el-menu-item-group>
                                    <el-menu-item index="2-1" @click="toRawMaterials">原料库</el-menu-item>
                                    <el-menu-item index="2-2" @click="toProduct">成品库</el-menu-item>
                                    <el-menu-item index="2-3" @click="toCheck">库存盘点</el-menu-item>
                                </el-menu-item-group>
                            </el-submenu>
                            <el-submenu index="3">
                                <template slot="title">
                                    <i class="el-icon-s-order"></i>
                                    <span>采购管理</span>
                                </template>
                                <el-menu-item-group>
                                    <el-menu-item @click="toPurchase" index="3-1">采购单添加</el-menu-item>
                                    <el-menu-item index="3-2" @click="toPurchaseOrders">采购单管理</el-menu-item>
                                </el-menu-item-group>
                            </el-submenu>
                            <el-submenu index="4">
                                <template slot="title">
                                    <i class="el-icon-money"></i>
                                    <span>销售管理</span>
                                </template>
                                <el-menu-item-group>
                                    <el-menu-item @click="toSale" index="4-1">销售单添加</el-menu-item>
                                    <el-menu-item index="4-2" @click="toSaleOrders">销售单管理</el-menu-item>
                                </el-menu-item-group>
                            </el-submenu>
                            <el-submenu index="5">
                                <template slot="title">
                                    <i class="el-icon-user-solid"></i>
                                    <span>系统管理</span>
                                </template>
                                <el-menu-item-group>
                                    <el-menu-item @click="toRole" index="5-1">角色管理</el-menu-item>
                                    <el-menu-item @click="toUser" index="5-2">用户管理</el-menu-item>
                                </el-menu-item-group>
                            </el-submenu>
                        </el-menu>
                    </el-aside>

                    <el-container>
                        <el-header style="padding: 0;height: 60px;background:#545c64">
                            <el-menu
                                    class="el-menu-demo"
                                    mode="horizontal"
                                    background-color="#545c64"
                                    text-color="#fff"
                                    active-text-color="#ffd04b"
                            style="border-bottom: 0 ">
                                <el-row>
                                    <el-col :span="2" :offset="20">
                                        <el-submenu index="1">
                                            <template slot="title">
                                                <el-avatar v-bind:src="avatar"></el-avatar>
                                            </template>
                                            <el-menu-item index="1-1" @click="toUserInfo">个人中心</el-menu-item>
                                            <el-menu-item index="1-2" @click="logout">注销</el-menu-item>
                                        </el-submenu>
                                    </el-col>
                                </el-row>
                            </el-menu>
                        </el-header>
                        <el-main style="padding: 0">
                            <router-view></router-view>
                        </el-main>
                    </el-container>
                </el-container>
            </div>

</template>
<script>
import axios from "axios";

export default {
    name: "LoginFirst",
    data() {
        return {
            avatar: window.sessionStorage.getItem('avatar')
        }
    },
    methods: {
        logout() {
            axios.get("/api/user/logout").then((res) => {
                sessionStorage.removeItem('token')
                this.$router.push("/")
            })
        },
        toHome(){
            this.$router.push({name: 'LoginFirst'})
        },
        toWarehouse() {
            this.$router.push({name: 'Warehouse'})
        },
        toProvider() {
            this.$router.push({name: 'Provider'})
        },
        toProduct() {
            this.$router.push({name: 'Product'})
        },
        toPurchaseOrders() {
            this.$router.push({name: 'PurchaseOrder'})
        },
        toRawMaterials() {
            this.$router.push({name: 'RawMaterial'})
        },
        toEmployee() {
            this.$router.push({name: 'Employee'})
        },
        toCustomer() {
            this.$router.push({name: "Customer"})
        },
        toCheck() {
            this.$router.push({name: 'CheckSheet'})
        },
        toRole() {
            this.$router.push({name: 'Role'})
        },
        toUser() {
            this.$router.push({name: 'User'})
        },
        toSale() {
            this.$router.push({name: 'Sale'})
        },
        toSaleOrders() {
            this.$router.push({name: 'SaleOrder'})
        },
        toPurchase() {
            this.$router.push({name: 'Purchase'})
        },
        toUserInfo() {
            this.$router.push({name: 'UserInfo'})
        },
    }
}
</script>

<style scoped>
::-webkit-scrollbar {
    width: 0 !important;
    height: 0;
}

[v-cloak] {
    display: none;
}
</style>