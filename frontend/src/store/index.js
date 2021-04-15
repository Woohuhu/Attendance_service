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
    accesstoken: null,
  },
  mutations: {
    Login(state, user) {
      state.id = user.id;
      state.name = user.name;
      state.accesstoken = user.accesstoken;
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
  },
  modules: {},
});
