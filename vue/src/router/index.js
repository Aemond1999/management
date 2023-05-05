//该文件专门用于创建整个应用的路由器
import VueRouter from "vue-router";
//引入组件
import Login from "@/pages/Login.vue";
import Customer from "@/pages/Customer.vue";
import LoginFirst from "@/pages/LoginFirst.vue";
import Provider from "@/pages/Provider.vue";
import Employee from "@/pages/Employee.vue";
import Warehouse from "@/pages/Warehouse.vue";
import RawMaterial from "@/pages/RawMaterial.vue";
import Product from "@/pages/Product.vue";
import CheckSheet from "@/pages/CheckSheet.vue";
import Purchase from "@/pages/Purchase.vue";
import Sale from "@/pages/Sale.vue";
import SaleOrder from "@/pages/SaleOrder.vue";
import PurchaseOrder from "@/pages/PurchaseOrder.vue";
import User from "@/pages/User.vue";
import Role from "@/pages/Role.vue";
import UserInfo from "@/pages/UserInfo.vue";
//创建并暴露一个路由器
const router=new VueRouter({
    routes:[
        {
            path:'/',
            component:Login,
            name:'Login',

        },

        {
            path:'/loginFirst',
            component:LoginFirst,
            name:'LoginFirst',
            children:[{
                path:'customer',
                component:Customer,
                name:'Customer',

            },{
                path:'provider',
                component:Provider,
                name:'Provider',
            },
                {
                    path:'employee',
                    component:Employee,
                    name:'Employee',
                },
                {
                    path:'warehouse',
                    component:Warehouse,
                    name:'Warehouse',
                },
                {
                    path:'rawMaterial',
                    component:RawMaterial,
                    name:'RawMaterial',
                },
                {
                    path:'product',
                    component:Product,
                    name:'Product',
                },
                {
                    path:'checkSheet',
                    component:CheckSheet,
                    name:'CheckSheet',
                },
                {
                    path:'purchase',
                    component:Purchase,
                    name:'Purchase',
                },
                {
                    path:'purchaseOrder',
                    component:PurchaseOrder,
                    name:'PurchaseOrder',
                },
                {
                    path:'sale',
                    component:Sale,
                    name:'Sale',
                },
                {
                    path:'saleOrder',
                    component:SaleOrder,
                    name:'SaleOrder',
                },
                {
                    path:'role',
                    component:Role,
                    name:'Role',
                },
                {
                    path:'user',
                    component:User,
                    name:'User',
                },
                {
                    path:'userInfo',
                    component:UserInfo,
                    name:'UserInfo'
                }

            ]
        }

    ]
})

router.beforeEach((to, from, next)=>{
    if (sessionStorage.getItem('token')==null&&to.path!=='/'){
        alert("请登录")
        router.push('/')
    }else {
        next()
    }}

)
export default router