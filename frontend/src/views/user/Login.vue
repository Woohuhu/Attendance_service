<template>
  <v-container class="fill-height" fluid>
    <v-row class="fill-height">
      <v-col cols="12" md="12" class="d-flex ma-0 pa-0 fill-screen">
        <v-row align="center" justify="center">
          <v-col cols="12" sm="6" md="7" align="center" justify="center">
            <v-card flat class="transparent" max-width="600px">
              <v-toolbar flat class="teal lighten-5">
                <v-toolbar-title class="flex ma-9 display-1 text-center">
                  Login
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
                  :error="!!errorMsg"
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
                  :error="!!errorMsg"
                  :error-messages="errorMsg"
                />
              </v-card-text>
              <v-card-actions>
                <v-spacer />
                <v-btn
                  rounded
                  @click="$router.push('/signup')"
                  color="#426dad"
                  class="subtitle-1 white--text"
                >
                  <span>회원가입</span>
                </v-btn>
                <v-btn
                  @click="onLogin"
                  rounded
                  color="#426dad"
                  class="subtitle-1 white--text"
                >
                  <span>로그인</span>
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  name: "login",
  data: function () {
    return {
      user: {
        id: "",
        password: "",
      },
      errorMsg: "",
    };
  },
  methods: {
    async onLogin() {
      if (!this.user.id) {
        this.errorMsg = "아이디를 입력하세요.";
        return;
      }
      if (!this.user.password) {
        this.errorMsg = "비밀번호를 입력하세요.";
        return;
      }
      try {
        const { id, password } = this.user;
        this.$store.dispatch("Login", { id, password });
        return this.$router.push("/");
      } catch (error) {
        this.errorMsg = "로그인 실패";
      }
    },
  },
};
</script>
