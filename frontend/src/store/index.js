import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import $axiosAuth from "./../service/global/axios.auth";

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    id: null,
    name: null,
    accessToken: null,
  },
  mutations: {
    Login(state, user) {
      state.id = user.id;
      state.name = user.name;
      state.accessToken = user.accessToken;
    },
    Logout(state) {
      state.id = null;
      state.name = null;
      state.accessToken = null;
    },
  },
  actions: {
    async Login(context, { id, password }) {
      const result = await $axiosAuth.post("/v1/login", {
        id,
        password,
      });

      const user = result.data.data;
      return context.commit("Login", user);
    },
    async Logout(context) {
      return context.commit("Logout");
    },
  },
  modules: {},
});
