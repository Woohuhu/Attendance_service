<template>
  <div>
    <v-app-bar color="white" flat>
      <v-container class="py-0">
        <v-row align="center" justify="center">
          <v-col cols="8">
            <img
              src="https://user-images.githubusercontent.com/43667316/117254945-09e74680-ae84-11eb-81ea-2e42105fe7b6.png"
              alt="logo"
              width="150px"
              height="50px"
              align="center"
            />
            <v-btn
              v-for="link in links_left"
              :key="link.name"
              text
              @click="$router.push(link.url)"
            >
              {{ link.name }}
            </v-btn>
          </v-col>

          <v-col
            v-if="$store.state.accessToken === null"
            class="text-right"
            cols="4"
          >
            <v-btn
              v-for="link in links_right"
              :key="link.name"
              text
              @click="$router.push(link.url)"
            >
              {{ link.name }}
            </v-btn>
          </v-col>
          <v-col
            v-if="$store.state.accessToken !== null"
            class="text-right"
            cols="4"
          >
            <v-btn text @click="$store.commit('Logout')">로그아웃</v-btn>
          </v-col>
        </v-row>

        <v-spacer></v-spacer>
      </v-container>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  data: () => ({
    links_left: [
      {
        name: "출석체크",
        url: "/attendance",
      },
      {
        name: "출석현황(일별)",
        url: "/attendance/date",
      },
      {
        name: "출석현황(사용자별)",
        url: "/attendance/user",
      },
    ],
    links_right: [
      {
        name: "회원가입",
        url: "/signup",
      },
      {
        name: "로그인",
        url: "/login",
      },
    ],
  }),
};
</script>
