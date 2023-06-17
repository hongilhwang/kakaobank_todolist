<template>
  <div class="body">
    <main class="main">
      <header class="header">
        <h1 class="title">User list</h1>
      </header>
      <UserList :users="users"></UserList>
    </main>
  </div>
</template>

<script>
import { mapState } from "vuex";
import UserList from "@/components/UserList";

export default {
  components: { UserList },
  computed: {
    ...mapState(["users"])
  },
  created() {
    this.$store.dispatch("getUsers");
  }
};
</script>

<style lang="scss" scoped>
@import "../styles/variables";
@import "../styles/common";

.body {
  box-sizing: border-box;
  padding: 1.2rem;
}
.main {
  margin: 0 auto;
  background: $white;
  border: 1px solid $gray;
  padding: 1.5rem 1.3rem 1rem;

  @media (min-width: 600px) {
    width: 600px;
  }
}
.title {
  margin: 0;
  font-size: 1.5rem;
  line-height: 1px;
}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid $gray;
  padding-bottom: 0.8rem;
}
</style>
