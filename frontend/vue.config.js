module.exports = {
  devServer: {
    public: "0.0.0.0:8080",
    disableHostCheck: true,
    proxy: {
      "/api": {
        target: `http://${process.env.BACK_END || "localhost"}:8080`,
        ws: true,
        changeOrigin: true
      }
    }
  }
};
