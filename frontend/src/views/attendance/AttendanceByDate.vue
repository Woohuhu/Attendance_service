<template>
  <v-container>
    <!-- datePicker dialog -->
    <v-row>
      <v-col cols="12" sm="6" md="4">
        <v-dialog
          ref="dialog"
          v-model="datePicker.modal"
          :return-value.sync="date"
          persistent
          width="290px"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="datePicker.date"
              label="조회할 날짜를 선택해주세요"
              prepend-icon="mdi-calendar"
              readonly
              v-bind="attrs"
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker v-model="datePicker.date" locale="ko-KR" scrollable>
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="datePicker.modal = false">
              Cancel
            </v-btn>
            <v-btn text color="primary" @click="$refs.dialog.save(date)">
              OK
            </v-btn>
          </v-date-picker>
        </v-dialog>
      </v-col>
    </v-row>
    <v-row>
      <template>
        <v-row>
          <v-col v-for="item in attendance.info" :key="item.name" cols="12">
            <v-text>꾸에에엑</v-text>
          </v-col>
        </v-row>
      </template>
    </v-row>
  </v-container>
</template>
<script>
import moment from "moment";
import { AttendanceService } from "./../../service/attendance/attendance.service";
export default {
  name: "AttendanceByDate",
  data: function () {
    return {
      user: {
        id: "",
        name: "",
        password: "",
      },
      snackbar: {
        show: false,
        text: "",
        color: "",
      },
      datePicker: {
        modal: false,
        date: "",
      },
      attendance: {
        info: [],
      },
    };
  },
  async created() {
    this.datePicker.date = moment().format("YYYY-MM-DD");
    console.log(
      await AttendanceService.getAttendanceByDate(moment().format("YYYY-MM-DD"))
    );
  },
  methods: {
    async onSignUp() {},
    openSnackbar(text, color) {
      this.snackbar.text = text;
      this.snackbar.color = color;
      this.snackbar.show = true;
    },
  },
};
</script>
