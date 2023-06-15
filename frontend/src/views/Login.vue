<template>
  <div class="body">
    <main class="main">
      <div class="login-template">
        <form class="form" @submit.prevent="submit">
          <table>
            <tr>
              <td><label class="label" for="id">ID: </label></td>
              <td>
                <input
                  id="id"
                  v-model.trim="id"
                  type="text"
                  :class="{
                    input: true,
                    'input--error': $v.id.$error
                  }"
                  :placeholder="$v.id.$error ? 'Enter a id' : ''"
                />
              </td>
              <td rowspan="2">
                <Button :class="{ button: true }">Login</Button>
              </td>
            </tr>
            <tr>
              <td><label class="label" for="pw">PW: </label></td>
              <td>
                <input
                  id="pw"
                  v-model.trim="pw"
                  :class="{
                    input: true,
                    'input--error': $v.pw.$error
                  }"
                  type="password"
                />
              </td>
            </tr>
            <tr>
              <td
                colspan="3"
                class="error"
                v-html="
                  $v.pw.$error
                    ? 'PW를 입력하지 않으셨거나 잘못 입력 하셨습니다.'
                    : (login_failure !== '' ? login_failure : '')
                "
              ></td>
            </tr>
          </table>
        </form>
      </div>
    </main>
  </div>
</template>

<script>
import Button from "@/components/Button.vue";
import required from "vuelidate/lib/validators/required";
import {mapActions, mapState} from "vuex";

export default {
  components: { Button },
  validations: {
    id: { required },
    pw: { required },
    validationGroup: ["id", "pw"]
  },
  data: function() {
    return {
      id: "",
      pw: ""
    };
  },
  computed: {
    ...mapState(["login_failure"])
  },
  methods: {
    ...mapActions(["login"]),
    submit() {
      if (this.$v.validationGroup.$invalid) {
        this.$v.validationGroup.$touch();
        return;
      }

      this.login({ id: this.id, pw: this.pw });
    }
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
.login-template {
  display: flex;
  justify-content: center;
  margin: 0;
  padding: 0;
}
.form {
}
.description,
.input {
  display: block;
  padding: 0.4rem;
  margin: 0;
  border: 1px solid $gray;
  font-size: $font-size;
  font-family: initial;

  &--error {
    border: 1px solid $orange;

    &::placeholder {
      color: $orange;
    }
  }
}
.error {
  color: $orange;
}
</style>
