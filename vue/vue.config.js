module.exports = {

  devServer: {
    host:'0.0.0.0',
    port: 8080,
    proxy: {
      '/api': {
        //target: 'http://ericbackend.azurewebsites.net/',
        target: 'http://localhost:9090',
        changeOrigin: true,
        pathRewrite: {
          '/api': ''
        }
      }
    }
  }
}
