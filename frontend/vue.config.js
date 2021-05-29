module.exports = {
  transpileDependencies: ["vuetify"],
  devServer: {
    proxy: {
      "/api/v1/user": {
        changeOrigin: true,
        target: "http://localhost:3000",
      },
      "/api/v1/login": {
        changeOrigin: true,
        target: "http://localhost:3000",
      },
      "/api": {
        changeOrigin: true,
        target: "http://localhost:2000",
      },
    },
  },
};
