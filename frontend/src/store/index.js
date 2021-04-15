import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

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
      const result = await this.$axiosAuth.post("/v1/login", {
        id,
        password,
      });
      const user = result.data;
      return context.commit("Login", user);
    },
  },
  modules: {},
});
