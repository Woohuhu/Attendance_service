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
