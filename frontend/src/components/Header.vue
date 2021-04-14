<template>
  <div>
    <v-app-bar color="white" flat>
      <v-container class="py-0">
        <v-avatar class="mr-10" color="teal darken-1" size="32"></v-avatar>

        <v-btn v-for="link in links" :key="link" text>
          {{ link }}
        </v-btn>

        <v-spacer></v-spacer>
      </v-container>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  data: () => ({
    links: ["Dashboard", "Messages", "Profile", "Updates"],
  }),
};
</script>
