<template>
  <v-card class="mx-auto" max-width="344" outlined>
    <v-list-item three-line>
      <v-list-item-content>
        <v-list-item-title class="title mb-1">
          {{ attendanceInfo.id }}
        </v-list-item-title>
        <v-list-item-subtitle class="font-weight-bold">{{
          getStateMessage(attendanceInfo.state)
        }}</v-list-item-subtitle>
      </v-list-item-content>

      <v-list-item-avatar tile size="80">
        <v-img
          src="https://user-images.githubusercontent.com/43667316/117445037-30d17580-af75-11eb-9b74-7cae7aa5e423.JPG"
        ></v-img>
      </v-list-item-avatar>
    </v-list-item>
  </v-card>
</template>
<script>
export default {
  name: "ByDateComponents",
  props: ["attendanceInfo"],
  data: function () {
    return {};
  },
  methods: {
    getStateMessage(state) {
      if (state == "attendance") return "출석";
      else if (state == "late") return "지각";
      else return "결석";
    },
  },
};
</script>
