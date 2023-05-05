<template>
    <div id="building">
        <form class="login">
            <p>Login</p>
            <input type="text" placeholder="用户名" v-model="user.username">
            <input type="password" placeholder="密码" v-model="user.password">
            <input type="button" @keydown.enter="login"  @click="login" class="btn" value="登 录"/>
        </form>
    </div>
</template>

<script>
import axios from "axios";
import jwtDecode from "jwt-decode";
export default {
    name: "Login",
    data() {
        return {
            user: {
                username: '',
                password: ''
            }
        }
    },
    methods: {
        login() {
            axios.post('/api/user/login', this.user).then((res) => {
                if (res.data.code === 200) {
                    sessionStorage.setItem("token", res.data.data.token)
                    var json = jwtDecode(res.data.data.token);
                    var userInfo = JSON.parse(json.sub);
                    window.sessionStorage.setItem("id", userInfo.id)
                    window.sessionStorage.setItem("username", userInfo.username)
                    window.sessionStorage.setItem("empId", userInfo.empId)
                    window.sessionStorage.setItem("empName", userInfo.empName)
                    window.sessionStorage.setItem("avatar", userInfo.avatar)
                    this.$router.push('/loginFirst')
                } else if (res.data.code === 505) {
                    this.$message.error("账号或密码错误")
                } else {
                    this.$message.error("未知错误")
                }
            })
        }
    }
}
</script>

<style scoped>
* {
    user-select: none;
    /* 无法选中，整体感更强 */
}

#building {
    background-image: url(../assets/wallpaper.jpg);
    background-size: cover;
    background-attachment: fixed;
    background-repeat: no-repeat;
    width: 100%;
    height: 100%;
    position: fixed;
}

.login {
    position: absolute;
    top: 50%;
    margin-top: -200px;
    left: 50%;
    margin-left: -200px;
    /* absolute居中的一种方法 */
    background-color: whitesmoke;
    width: 400px;
    height: 400px;
    border-radius: 25px;
    text-align: center;
    padding: 5px 40px;
    box-sizing: border-box;
    /* 这样padding就不会影响大小 */
}

p {
    font-size: 42px;
    font-weight: 600;
}

input {
    background-color: whitesmoke;
    width: 100%;
    height: 48px;
    margin-bottom: 10px;
    border: none;
    border-bottom: 2px solid silver;
    /* 下面的会覆盖上面的步伐 */
    outline: none;
    font-size: 22px;
}

.btn {
    background-color: #59c2c5;
    width: 38%;
    height: 48px;
    border-radius: 8px;
    margin-top: 40px;
    font-size: 28px;
    font-weight: 600;
    color: white;
}

.btn:hover {
    background-color: #59c2a0;
}
</style>