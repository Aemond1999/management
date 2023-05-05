<template>
<div id="main" >
    <router-view></router-view>
</div>
</template>
<script>

export default {
    name: 'App',
    data() {
        return {}
    },

}
</script>

<style>
#main{
    background-size: cover;
    width: 100%;
    height: 100%;
    position: absolute;
    background: #F2F6FC no-repeat fixed;
}
</style>
