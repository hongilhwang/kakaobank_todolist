<template>
  <div class="app">
    <router-view />
    <PortalTarget name="modal" @change="handleChange"></PortalTarget>
  </div>
</template>

<script>
import store from "@/store";

const body = document.querySelector("body");

export default {
  data: function() {
    return {
      modalOpen: false
    };
  },
  created: () => {
    if (sessionStorage.getItem("token")) {
      store.dispatch("whois");
    }
  },
  methods: {
    /**
     * Prevent scrolling of the main window.
     * @param {Boolean} newContent
     */
    handleChange(newContent) {
      if (newContent) {
        body.style.overflow = "hidden";
        window.scrollTo(0, 0);
      } else {
        body.style.overflow = "auto";
      }
    }
  }
};
</script>

<style lang="scss">
@import "styles/global";
@import "styles/variables";

.app {
  background: $gray-lightest;
  min-height: 100%;
}
</style>
