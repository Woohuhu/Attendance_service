<template>
  <v-card class="mx-auto" max-width="130" max-height="130" outlined>
    <v-img
      src="https://user-images.githubusercontent.com/43667316/117630005-b7749580-b1b5-11eb-93c3-5d8938182cb4.JPG"
      width="130px"
      height="95px"
    ></v-img>
    <v-list-item one-line>
      <v-list-item-content>
        <v-list-item-title align="center" class="title mb-1">
          <span>{{ userInfo.name }}</span>
        </v-list-item-title>
      </v-list-item-content>
    </v-list-item>
  </v-card>
</template>

<script>
export default {
  props: ["userInfo"],
};
</script>
