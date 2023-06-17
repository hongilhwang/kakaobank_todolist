<template>
  <ul class="list">
    <li v-for="user in users" :key="user.id" class="user">
      <div class="panel">
        <div class="panel__id">{{ user.id }}</div>
        <div class="panel__name">{{ user.name }}</div>
        <div class="panel__role">
          <Checkbox
            :id="`checkbox-${user.id}`"
            v-model="user.admin"
            :name="user.id"
            :on-checked="() => toggleRole(user)"
          />
        </div>
        <div class="panel__actions">
          <Button @click.native.prevent="() => deleteUser(user.id)">
            <img class="icon" src="../assets/multiply.png" alt="Delete Icon" />
          </Button>
        </div>
      </div>
    </li>
  </ul>
</template>

<script>
import Checkbox from "@/components/Checkbox.vue";
import { mapActions } from "vuex";
import Button from "@/components/Button.vue";

export default {
  components: { Button, Checkbox },
  props: { users: { type: Array, required: true } },
  data: function() {
    return {};
  },
  methods: {
    ...mapActions(["updateUser", "deleteUser"]),
    toggleRole(user) {
      this.updateUser(user);
    }
  }
};
</script>

<style scoped lang="scss">
@import "../styles/variables";
.list {
  margin: 0;
  padding: 0;
  list-style-type: none;
}
.user {
  display: flex;
  border-bottom: 1px solid $gray;
  padding: 0.6rem 0;
  position: relative;
  justify-content: space-between;
  align-items: center;

  &:last-child {
    border-bottom: none;
    padding-bottom: 0;
  }
}
.panel {
  display: flex;
  align-items: center;
  flex-direction: row;
  width: 100%;

  .panel__actions {
    width: 50px;
    display: flex;
    align-items: center;
  }
  .panel__id {
    min-width: 50px;
  }
  .panel__name {
    display: flex;
    width: 100%;
  }
  .panel__role {
    width: 50px;
  }
}
.icon {
  height: 1.2rem;
  width: 1.2rem;
}
.date {
  font-size: $font-size;
  margin: 0 1rem 0 0.4rem;
}
.button {
  margin: 0 0.3rem 0.1rem;
  padding: 0.4rem;
  display: flex;
  align-items: center;

  .icon {
    height: 0.9rem;
    width: 0.9rem;
  }

  &:last-child {
    margin-right: 0;
  }
}
</style>
