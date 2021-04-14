import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import VueLogger from "vuejs-logger";
const isProduction = process.env.NODE_ENV === "production";

Vue.config.productionTip = false;

const options = {
  isEnabled: true,
  logLevel: isProduction ? "error" : "debud",
  stringifyArguments: false,
  showLogLevel: true,
  showMethodName: true,
  separator: "|",
  showConsoleColors: true,
};

Vue.use(VueLogger, options);

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
