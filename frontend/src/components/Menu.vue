<template>
  <div class="body">
    <div class="menu">
      <div class="left">
        <Button @click.native.prevent="() => handleGoTo('/')">홈</Button>
        <Button class="myTodo" @click.native.prevent="() => handleGoTo('/my')">
          나의 할일
        </Button>
      </div>
      <div class="right">
        <Button
          v-if="user && user.admin"
          @click.native.prevent="() => handleGoTo('/users')"
        >
          유저관리
        </Button>
        <Button @click.native.prevent="() => handleLogout()">로그아웃</Button>
      </div>
    </div>
  </div>
</template>

<script>
import Button from "../components/Button";
import router from "@/router";
import { mapState } from "vuex";
export default {
  name: "Menu",
  components: { Button },
  props: {},
  computed: {
    ...mapState(["user"])
  },
  methods: {
    handleGoTo: patch => {
      router.push(patch);
    },
    handleLogout: () => {
      sessionStorage.removeItem("token");
      router.push("/login");
    }
  }
};
</script>

<style scoped lang="scss">
@import "../styles/variables";

.menu {
  display: flex;
  margin: 0 auto;
  background: $white;
  border: 1px solid $gray;
  padding: 0;

  .left {
    display: flex;
  }

  .right {
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    width: 100%;
  }

  button {
    width: 100px;
    margin: 0 1px 0 1px;
  }

  @media (min-width: 600px) {
    width: 600px;
  }
}
</style>
