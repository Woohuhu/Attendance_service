<template>
  <div>
    <v-app-bar flat color="white" dark dense>
      <v-col align="center" justify="center">
        <v-btn text color="black" @click="onClickRedirect()">
          🌱 Woohuhu github 🌱
        </v-btn>
      </v-col>
    </v-app-bar>
  </div>
</template>
<script>
export default {
  data: () => ({}),
  methods: {
    onClickRedirect: function () {
      window.open("https://github.com/Woohuhu/Attendance_service", "_blank");
    },
  },
};
</script>
