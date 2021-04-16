<template>
  <v-container class="fill-height" fluid>
    <v-row class="fill-height">
      <v-col cols="12" md="12" class="d-flex ma-0 pa-0 fill-screen">
        <v-row align="center" justify="center">
          <v-col cols="12" sm="6" md="7" align="center" justify="center">
            <v-card flat class="transparent" max-width="600px">
              <v-toolbar flat class="teal lighten-5">
                <v-toolbar-title class="flex ma-9 display-1 text-center">
                  Sign Up
                </v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <v-text-field
                  filled
                  rounded
                  dense
                  label="아이디"
                  name="login"
                  prepend-icon="fas fa-user"
                  type="text"
                  v-model="user.id"
                  :error="!!errorMsg.id"
                  :error-messages="errorMsg.id"
                />

                <v-text-field
                  filled
                  rounded
                  dense
                  id="name"
                  label="이름"
                  name="name"
                  prepend-icon="fas fa-lock"
                  type="text"
                  v-model="user.name"
                  :error="!!errorMsg.name"
                  :error-messages="errorMsg.name"
                />

                <v-text-field
                  filled
                  rounded
                  dense
                  id="password"
                  label="비밀번호"
                  name="password"
                  prepend-icon="fas fa-lock"
                  type="password"
                  v-model="user.password"
                  :error="!!errorMsg.password"
                  :error-messages="errorMsg.password"
                />
              </v-card-text>
              <v-card-actions>
                <v-spacer />
                <v-btn
                  rounded
                  @click="onSignUp"
                  color="#426dad"
                  class="subtitle-1 white--text"
                >
                  회원가입
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
    <v-snackbar
      class="text-center"
      v-model="snackbar.show"
      :timeout="2000"
      :color="snackbar.color"
    >
      {{ snackbar.text }}
      <template v-slot:action="{ attrs }">
        <v-btn color="white" text v-bind="attrs" @click="snackbar.show = false">
          닫기
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>
<script>
import { UserService } from "./../../service/user/user.service";
export default {
  name: "signup",
  data: function () {
    return {
      user: {
        id: "",
        name: "",
        password: "",
      },
      errorMsg: {
        id: "",
        name: "",
        password: "",
      },
      snackbar: {
        show: false,
        text: "",
        color: "",
      },
    };
  },
  methods: {
    async onSignUp() {
      this.errorMsg.id = "";
      this.errorMsg.name = "";
      this.errorMsg.password = "";
      if (!this.user.id) {
        this.errorMsg.id = "아이디를 입력하세요.";
        return;
      }
      if (!this.user.name) {
        this.errorMsg.name = "이름을 입력하세요.";
        return;
      }
      if (!this.user.password) {
        this.errorMsg.password = "비밀번호를 입력하세요.";
        return;
      }
      try {
        await UserService.createUser(this.user);
        return this.$router.push("/login");
      } catch (error) {
        this.$log.error(error);
        this.openSnackbar("회원가입 실패", "error");
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
