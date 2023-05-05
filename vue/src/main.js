import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import VueRouter from 'vue-router'
import router from './router'
import axios from "axios";
import store from "./store"
Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.use(VueRouter)
axios.interceptors.request.use(function (config) {
        config.headers.Authorization = sessionStorage.getItem("token");
        return config;
    }
)
Vue.prototype.$http = axios;
new Vue({
    el: '#app',
    render: h => h(App),
    store,
    router

});

