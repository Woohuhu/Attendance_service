import axios from "axios";

const axiosAuth = axios.create({
  baseURL:
    process.env.VUE_APP_NODE_ENV == "dev"
      ? "http://localhost:8080/api"
      : process.env.VUE_APP_AUTH_URL,
  withCredentials: true,
});

export default axiosAuth;
