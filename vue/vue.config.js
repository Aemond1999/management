const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:7777',
        pathRewrite:{'^/api':''},
        // ws: true, //用于支持websocket,默认值为true
        // changeOrigin: true //用于控制请求头中的host值,默认值为true
      }
    }
  }
})
