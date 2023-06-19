<template>
  <ModalBase :close="close" title="New User">
    <form class="form" @submit.prevent="submit">
      <label class="label" for="name">Name:</label>
      <input
        id="name"
        v-model.trim="$v.newUser.name.$model"
        :class="{
          input: true,
          'input--error': $v.newUser.name.$error
        }"
        :placeholder="$v.newUser.name.$error ? 'Enter a name' : ''"
        type="text"
      />

      <label class="label" for="pw">Pw:</label>
      <input
        id="pw"
        v-model.trim="$v.newUser.pw.$model"
        :class="{
          input: true,
          'input--error': $v.newUser.pw.$error
        }"
        :placeholder="$v.newUser.pw.$error ? 'Enter a pw' : ''"
        type="password"
      />

      <label class="label" for="pw">Pw 확인:</label>
      <input
        id="pwConfirm"
        v-model.trim="$v.newUser.pwConfirm.$model"
        :class="{
          input: true,
          'input--error': $v.newUser.pwConfirm.$error
        }"
        :placeholder="$v.newUser.pwConfirm.$error ? 'Enter a pw 확인' : ''"
        type="password"
      />

      <label class="label" for="role">Role:</label>
      <select
        id="role"
        v-model="$v.newUser.admin.$model"
        :class="{
          select: true,
          'select--error': $v.newUser.admin.$error
        }"
      >
        <option v-for="role in roles" :key="role" :value="role === '관리자'"
          >{{ role }}
        </option>
      </select>
      <footer :class="['footer', 'footer--single']">
        <Button :class="{ button: true }" :disabled="$v.validationGroup.$error"
          >Add a new user</Button
        >
      </footer>
    </form>
  </ModalBase>
</template>

<script>
import required from "vuelidate/src/validators/required";
import { mapActions } from "vuex";
import ModalBase from "./BaseModal";
import Button from "@/components/Button";

export default {
  name: "UserModal",
  components: { Button, ModalBase },
  props: {
    close: { type: Function, required: true }
  },
  data: function() {
    return {
      roles: ["일반 유저", "관리자"],
      newUser: {
        name: "",
        pw: "",
        pwConfirm: "",
        admin: false
      }
    };
  },
  validations: {
    newUser: {
      name: { required },
      pw: { required },
      pwConfirm: { required },
      admin: { required }
    },
    validationGroup: ["newUser"]
  },
  methods: {
    ...mapActions(["saveUser"]),
    submit() {
      if (this.$v.validationGroup.$invalid) {
        this.$v.validationGroup.$touch();
        return;
      }

      if (!this.$v.validationGroup.$anyDirty) {
        this.close();
        return;
      }
      if (this.newUser.pw !== this.newUser.pwConfirm) {
        alert("비밀번호를 잘못 입력 하셨습니다.");
        return;
      }
      const { name, pw, admin } = this.newUser;
      this.saveUser({ name, pw, admin });
      this.close();
    }
  }
};
</script>

<style lang="scss" scoped>
@import "../../styles/variables";
@import "../../styles/common";

.form {
  margin-top: 1.3rem;
  color: $gray-darker;
}

.label {
  margin: 0.2rem 0;
  display: inline-block;

  &:first-child {
    margin-top: 0;
  }
}

.comments,
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

.select {
  -webkit-appearance: menulist-button;
  height: 30px;
  display: block;
  width: 60%;
  font-size: $font-size;
  font-family: initial;

  &--error {
    color: $orange;
    border: 1px solid $orange;
  }
}

.input,
.description,
.footer {
  width: 100%;
}

.comments,
.description,
.input,
.date,
.priority {
  margin-bottom: 0.7rem;
}

.footer {
  display: flex;
  justify-content: space-between;

  &--single {
    justify-content: flex-end;
  }

  & .button {
    margin-bottom: 0;
  }
}

.description {
  resize: none;
}
</style>
