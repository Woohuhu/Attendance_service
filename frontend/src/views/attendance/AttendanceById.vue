<template>
  <ByIdComponent v-bind:attendance="attendances"></ByIdComponent>
</template>

<script>
import ByIdComponent from "../../components/attendance/ByIdComponent";

export default {
  components: {
    ByIdComponent,
  },
  data: () => ({
    attendances: [],
    name: "",
  }),
  async created() {},
};
</script>
