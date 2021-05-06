import axios from "axios";
//import VueLogger from "vuejs-logger";
import store from "../../store/index";
import { UserService } from "../user/user.service";

const axiosResource = axios.create({
  baseURL:
    process.env.VUE_APP_NODE_ENV != "production"
      ? "http://localhost:8080/api"
      : process.env.VUE_APP_AUTH_URL,
  withCredentials: true,
});

axiosResource.interceptors.request.use(
  async function (config) {
    try {
      config.headers.common = {
        Authorization: "Bearer " + store.state.accessToken,
      };
    } catch (err) {
      //VueLogger.error(err);
    }
    console.log(config);
    return await config;
  },
  function (error) {
    return Promise.reject(error);
  }
);

axiosResource.interceptors.response.use(
  function (response) {
    console.log(response);
    return response;
  },
  async function (error) {
    console.log(error);
    const result = error.config;
    if (error.response.status === 401 && result.retry != true) {
      result.retry = true;

      const res = await UserService.refreshToken();

      error.response.config.headers = {
        Authorization: "Beareer " + res.data.accessToken,
      };
      store.commit("RefreshToken", res.data.accessToken);

      return await axiosResource(result);
    }
    console.log("here");
    return Promise.reject(error);
  }
);

export default axiosResource;
