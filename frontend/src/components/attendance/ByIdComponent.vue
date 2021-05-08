<template>
  <v-container fluid>
    <v-row align="center" justify="center">
      <v-chip class="ma-5 pa-5" filter color="white">
        {{ this.$store.state.name }}의 출석현황
      </v-chip>
    </v-row>
    <v-row align="center" justify="center">
      <v-data-iterator
        v-if="attendance.length > 0"
        :items="attendance"
        hide-default-footer
      >
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
              <v-card class="mx-auto" max-width="344" outlined>
                <v-list-item three-line>
                  <v-list-item-content>
                    <v-list-item-title class="title mb-1">
                      {{ item.id }}
                    </v-list-item-title>
                    <v-list-item-subtitle>
                      {{ item.date }}
                    </v-list-item-subtitle>
                    <v-list-item-subtitle>
                      {{ getStateMessage(item.state) }}
                    </v-list-item-subtitle>
                  </v-list-item-content>

                  <v-list-item-avatar tile size="80">
                    <v-img
                      src="https://user-images.githubusercontent.com/43667316/117448367-9de70a00-af79-11eb-8152-d7afbcd478e4.JPG"
                    ></v-img>
                  </v-list-item-avatar>
                </v-list-item>
              </v-card>
            </v-col>
          </v-row>
        </template>
      </v-data-iterator>
    </v-row>
  </v-container>
</template>

<script>
export default {
  props: ["attendance"],
  methods: {
    getStateMessage(state) {
      if (state == "attendance") return "출석";
      else if (state == "late") return "지각";
      else return "결석";
    },
  },
};
</script>
