<template>
  <v-app fluid>
    <Header />
    <div class="teal lighten-5" style="height: 100%">
      <router-view />
    </div>
    <Footer />
  </v-app>
</template>

<script>
export default {
  name: "App",

  components: {
    Header: () => import("./components/Header"),
    Footer: () => import("./components/Footer"),
  },

  data: () => ({
    //
  }),
};
</script>

<style>
@font-face {
  font-family: "MapoBackpacking";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/MapoBackpackingA.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
#app {
  font-family: "MapoBackpacking";
}
</style>
