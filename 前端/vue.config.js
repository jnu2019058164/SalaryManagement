const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,

  devServer:{
    open: true,
  port: 8088,
  proxy: {
    "/j2ee": {
      ws:false,
      target: "http://localhost:8080/j2ee",
      changeOrigin: true,
      pathRewrite: {
        '^/j2ee': '',
      }
    }
  }
  }
  
})
