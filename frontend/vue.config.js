module.exports = {
  lintOnSave: false,
  devServer: {
    overlay: false,
    proxy: 'http://localhost:8080',
    historyApiFallback: true
  }
}
