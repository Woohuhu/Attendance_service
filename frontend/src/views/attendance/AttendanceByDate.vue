<template>
  <v-container>
    <!-- datePicker dialog -->
    <v-row align="center" justify="center">
      <v-chip class="ma-5 pa-5" large color="white" text-color="black">
        <v-col cols="12">
          <v-dialog
            ref="dialog"
            v-model="datePicker.modal"
            :return-value.sync="datePicker.date"
            persistent
            width="290px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                v-model="datePicker.date"
                prepend-icon="mdi-calendar"
                readonly
                v-bind="attrs"
                v-on="on"
                align="center"
              ></v-text-field>
            </template>
            <v-date-picker v-model="datePicker.date" locale="ko-KR" scrollable>
              <v-spacer></v-spacer>
              <v-btn text color="error" @click="datePicker.modal = false">
                취소
              </v-btn>
              <v-btn
                text
                color="primary"
                @click="fetchAll() && $refs.dialog.save(datePicker.date)"
              >
                선택
              </v-btn>
            </v-date-picker>
          </v-dialog>
        </v-col>
      </v-chip>
    </v-row>
    <v-row>
      <template>
        <v-container fluid style="width: 70%">
          <v-card
            v-if="attendance.info.length == 0"
            class="mx-auto"
            max-width="344"
            outlined
          >
            <v-img
              src="https://user-images.githubusercontent.com/43667316/117445037-30d17580-af75-11eb-9b74-7cae7aa5e423.JPG"
            ></v-img>
            <v-list-item three-line>
              <v-list-item-content>
                <v-list-item-title align="center" class="title mb-1">
                  출석정보가 없어요!
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-card>
          <v-data-iterator
            v-if="attendance.info.length > 0"
            :items="attendance.info"
            :items-per-page.sync="itemsPerPage"
          >
            <template v-slot:default="props">
              <v-row>
                <v-col
                  v-for="item in props.items"
                  :key="item.id"
                  cols="12"
                  sm="12"
                  md="6"
                  lg="4"
                >
                  <ByDateComponents
                    v-bind:attendanceInfo="item"
                  ></ByDateComponents>
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
import moment from "moment";
import { AttendanceService } from "./../../service/attendance/attendance.service";
import ByDateComponents from "./../../components/attendance/ByDateComponents";
export default {
  name: "AttendanceByDate",
  components: {
    ByDateComponents,
  },
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
      itemsPerPage: 8,
    };
  },
  async created() {
    this.datePicker.date = moment().format("YYYY-MM-DD");
    await this.fetchAll();
  },
  methods: {
    async fetchAll() {
      await this.getAttendanceInfo();
    },
    async getAttendanceInfo() {
      this.attendance.info = [];
      try {
        this.attendance.info = await AttendanceService.getAttendanceByDate(
          this.datePicker.date
        );
      } catch (err) {
        this.attendance.info = [];
      }
    },
    openSnackbar(text, color) {
      this.snackbar.text = text;
      this.snackbar.color = color;
      this.snackbar.show = true;
    },
  },
};
</script>
