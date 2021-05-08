<template>
  <v-container>
    <v-row align="center" justify="center">
      <v-chip class="ma-5 pa-5" filter color="white">
        {{ this.$store.state.name }}의 출석현황
      </v-chip>
    </v-row>
    <v-row>
      <template>
        <v-container fluid style="width: 70%">
          <v-card
            v-if="attendances.length == 0"
            class="mx-auto"
            max-width="344"
            outlined
          >
            <v-img
              src="https://user-images.githubusercontent.com/43667316/117448367-9de70a00-af79-11eb-8152-d7afbcd478e4.JPG"
            ></v-img>
            <v-list-item three-line>
              <v-list-item-content>
                <v-list-item-title align="center" class="title mb-1">
                  출석정보가 없어요!
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-card>
          <v-data-iterator v-if="attendances.length > 0" :items="attendances">
            <template v-slot:default="props">
              <v-row>
                <v-col
                  v-for="item in props.items"
                  :key="item.idx"
                  cols="12"
                  sm="12"
                  md="6"
                  lg="4"
                >
                  <ByIdComponent v-bind:attendance="item"></ByIdComponent>
                </v-col>
              </v-row>
            </template>
          </v-data-iterator>
        </v-container>
      </template>
    </v-row>
  </v-container>
</template>

<script>
import ByIdComponent from "../../components/attendance/ByIdComponent";
import { AttendanceService } from "./../../service/attendance/attendance.service";

export default {
  components: {
    ByIdComponent,
  },
  data: () => ({
    attendances: [],
    name: "",
    itemsPerPage: 8,
  }),
  async created() {
    await this.fetchAll();
  },
  methods: {
    async fetchAll() {
      await this.getAttendanceInfo();
    },
    async getAttendanceInfo() {
      this.attendances = [];
      try {
        this.attendances = await AttendanceService.getAttendanceById(
          this.$store.state.id
        );
      } catch (err) {
        this.attendances = [];
      }
    },
  },
};
</script>
