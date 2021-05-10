<template>
  <v-container>
    <v-row class="ma-5" align="center" justify="center">
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
          v-model="attendance.code"
          :rules="[rules.required, rules.length]"
          single-line
          hide-details
          class="centered-input title"
        ></v-text-field>

        <v-btn class="ma-1" plain> 출석하기 </v-btn>
      </v-card>
    </v-row>
    <v-row class="ma-5" align="center" justify="center">
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
        <v-btn class="ma-1" plain> 종료하기 </v-btn>
      </v-card>
    </v-row>
    <v-row class="ma-5" align="center" justify="center">
      <v-card
        class="px-7 pt-4 pb-4 text-center d-inline-block"
        color="teal darken-3"
        width="60%"
        dark
      >
        <v-btn class="ma-1" plain> 시작하기 </v-btn>
      </v-card>
    </v-row>
    <!-- 출석사용자 리스트 -->
    <v-row>
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
      },
      rules: {
        required: (value) => !!value || "값을 입력해주세요.",
        length: (v) => v.length == 3 || "3자리 번호 입니다.",
      },
    };
  },
  async created() {},
  methods: {
    openSnackbar(text, color) {
      this.snackbar.text = text;
      this.snackbar.color = color;
      this.snackbar.show = true;
    },
  },
};
</script>
<style scoped>
.centered-input >>> input {
  text-align: center;
}
</style>
