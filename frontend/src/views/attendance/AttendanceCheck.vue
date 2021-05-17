<template>
  <v-container>
    <v-row
      class="ma-5"
      align="center"
      justify="center"
      v-if="attendance.starter != this.$store.state.id && attendance.state"
    >
      <v-card
        class="px-7 pt-7 pb-4 text-center d-inline-block"
        color="teal darken-3"
        width="60%"
        dark
      >
        <div class="text-body-2 mb-4">
          <span>출석 번호를 입력해 주세요</span>
        </div>

        <v-text-field
          v-model="attendance.input"
          :rules="[rules.required, rules.length]"
          single-line
          hide-details
          class="centered-input title"
        ></v-text-field>

        <v-btn class="ma-1" plain v-on:click="attend()"> 출석하기 </v-btn>
      </v-card>
    </v-row>
    <v-row class="ma-5" align="center" justify="center" v-if="attendance.state">
      <v-card
        class="px-7 pt-7 pb-4 text-center d-inline-block"
        color="teal darken-3"
        width="60%"
        dark
      >
        <div class="text-body-2 mb-4">
          <span>출석 번호</span>
        </div>
        <span class="centered-input display-4">{{ attendance.code }}</span>
        <v-spacer></v-spacer>
        <v-btn
          class="ma-1"
          plain
          v-if="attendance.starter == this.$store.state.id"
          v-on:click="stopAttendance()"
        >
          종료하기
        </v-btn>
      </v-card>
    </v-row>
    <v-row
      class="ma-5"
      align="center"
      justify="center"
      v-if="attendance.state == false"
    >
      <v-card
        class="px-7 pt-4 pb-4 text-center d-inline-block"
        color="teal darken-3"
        width="60%"
        dark
      >
        <v-btn class="ma-1" plain v-on:click="startAttendance()">
          시작하기
        </v-btn>
      </v-card>
    </v-row>
    <!-- 출석사용자 리스트 -->
    <v-row v-if="attendance.state">
      <template>
        <v-container fluid style="width: 70%">
          <v-card
            v-if="attendance.userInfo.length == 0"
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
                  출석한 인원이 없어요!
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-card>
          <v-data-iterator
            v-if="attendance.userInfo.length > 0"
            :items="attendance.userInfo"
            :items-per-page.sync="itemsPerPage"
          >
            <template v-slot:default="props">
              <v-row>
                <v-col
                  v-for="item in props.items"
                  :key="item.id"
                  cols="12"
                  sm="6"
                  md="3"
                  lg="3"
                >
                  <UserListComponent v-bind:userInfo="item"></UserListComponent>
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
import UserListComponent from "../../components/attendance/UserListComponent";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

export default {
  name: "AttendanceCheck",
  components: {
    UserListComponent,
  },
  data: function () {
    return {
      attendance: {
        userInfo: [],
        state: false,
        starter: "",
        code: "",
        input: "",
        time: "",
      },
      rules: {
        required: (value) => !!value || "값을 입력해주세요.",
        length: (v) => v.length == 3 || "3자리 번호 입니다.",
      },
    };
  },
  async created() {
    let socket = new SockJS("http://localhost:2000/api/socket");
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, (frame) => {
      this.connected = true;
      this.$log.info("Connected : " + frame);
      this.stompClient.send("/join", {}, JSON.stringify({}));

      this.stompClient.subscribe("/join", (res) => {
        const result = JSON.parse(res.body);
        this.attendance.userInfo = result.attendanceUserList;
        this.attendance.state = result.isAttendance;
        this.attendance.time = result.time;
      });

      this.stompClient.subscribe("/start", (res) => {
        const result = JSON.parse(res.body);
        this.attendance.state = result.isAttendance;
        this.attendance.starter = result.adminId;
        this.attendance.time = result.time;
      });

      this.stompClient.subscribe("/attendance", (res) => {
        const result = JSON.parse(res.body);
        this.$log.info(result);
        this.attendance.userInfo.push(result);
      });
    });
  },
  methods: {
    openSnackbar(text, color) {
      this.snackbar.text = text;
      this.snackbar.color = color;
      this.snackbar.show = true;
    },
    async startAttendance() {
      this.attendance.code = Math.floor(Math.random() * 900) + 100;
      this.stompClient.send(
        "/start",
        {},
        JSON.stringify({
          code: this.attendance.code,
          adminId: this.$store.state.id,
        })
      );
      this.attendance.state = true;
    },
    async attend() {
      this.stompClient.send(
        "/attendance",
        {},
        JSON.stringify({
          code: this.attendance.input,
          id: this.$store.state.id,
          name: this.$store.state,
        })
      );
    },
    async stopAttendance() {
      if (this.stompClient !== null) {
        this.stompClient.disconnected();
      }
      this.connected = false;
      this.$log.info("Disconnected");
    },
  },
};
</script>
<style scoped>
.centered-input >>> input {
  text-align: center;
}
</style>
