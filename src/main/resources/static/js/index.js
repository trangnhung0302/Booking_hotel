import Vue from "vue/dist/vue.esm.js";
import Booking from "./pages/booking/index.vue";

document.addEventListener("DOMContentLoaded", () => {
  new Vue({
    el: "#app",
    components: { Booking },
    data() {
      return {
      };
    },
  });
});
