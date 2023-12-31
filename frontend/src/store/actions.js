import { parse } from "date-fns";
import router from "@/router";

export const getToken = () => {
  const token = sessionStorage.getItem("token");
  if (token === null || token === "") router.push("/login");
  return { Authorization: `BEARER ${token}` };
};

const checkResponseResult = (response, exceptionCallback = () => {}) => {
  if (!response.ok) {
    if (response.status === 401) {
      alert("로그인이 만료되었습니다. 로그인을 다시 시도해주세요.");
      sessionStorage.removeItem("token");
      router.push("/login");
    }
    if (response.status === 403) {
      alert("권한이 없는 페이지입니다. 메인페이지로 이동합니다.");
      router.push("/");
    }
    exceptionCallback(response);
    console.error(response);
    throw Error("서버로 부터 정상적인 응답을 받지 못했습니다.");
  }
};

export default {
  sortTasks({ state }) {
    state.tasks.sort((a, b) => a.id - b.id);
  },
  async getTasks({ commit, dispatch }) {
    const response = await fetch("/api/tasks", { headers: getToken() });

    checkResponseResult(response);
    const tasks = await response.json();
    tasks.forEach(task => (task.dueDate = parse(task.dueDate)));

    commit("SAVE_TASKS", tasks);
    dispatch("sortTasks");
  },
  async saveTask({ commit, dispatch }, task) {
    const response = await fetch(`/api/tasks`, {
      method: "post",
      body: JSON.stringify(task),
      headers: { "Content-Type": "application/json", ...getToken() }
    });

    checkResponseResult(response);

    const taskNew = await response.json();
    commit("UPDATE_TASK", taskNew);
    dispatch("sortTasks");
  },
  async updateTask({ commit, dispatch }, task) {
    const response = await fetch(`/api/tasks`, {
      method: "put",
      body: JSON.stringify(task),
      headers: { "Content-Type": "application/json", ...getToken() }
    });

    checkResponseResult(response);

    const taskNew = await response.json();
    commit("UPDATE_TASK", taskNew);
    dispatch("sortTasks");
  },
  async deleteTask({ dispatch }, taskId) {
    const response = await fetch(`/api/tasks/${taskId}`, {
      method: "delete",
      headers: getToken()
    });

    checkResponseResult(response);

    dispatch("getTasks");
  },
  async completeTask({ dispatch }, task) {
    task.status = !task.status;

    dispatch("updateTask", task);
  },
  async saveComment({ commit, dispatch }, { taskId, comment }) {
    const response = await fetch(`/api/tasks/${taskId}/comments`, {
      method: "post",
      body: JSON.stringify(comment),
      headers: { "Content-Type": "application/json", ...getToken() }
    });

    checkResponseResult(response);

    const task = await response.json();
    commit("UPDATE_TASK", task);
    dispatch("sortTasks");
  },
  async updateComment({ commit, dispatch }, comment) {
    const response = await fetch(`/api/comments`, {
      method: "put",
      body: JSON.stringify(comment),
      headers: { "Content-Type": "application/json", ...getToken() }
    });

    checkResponseResult(response);

    const commentUpdated = await response.json();
    commit("UPDATE_COMMENT", commentUpdated);
    dispatch("sortTasks");
  },
  async deleteComment({ dispatch }, commentId) {
    const response = await fetch(`/api/comments/${commentId}`, {
      method: "delete",
      headers: getToken()
    });

    checkResponseResult(response);

    dispatch("getTasks");
  },

  async login({ commit }, { id, pw }) {
    const response = await fetch(`/api/login`, {
      method: "post",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Basic " + new Buffer(id + ":" + pw).toString("base64")
      }
    });

    if (!response.ok) {
      commit(
        "LOGIN_FAILURE",
        "패스워드가 잘못 되었거나 알수 없는 이유로 로그인 실패 하였습니다."
      );
      return;
    }
    const payload = await response.json();
    commit("LOGIN_SUCCESS", payload);
    router.push("/");
  },
  async getUsers({ commit }) {
    const response = await fetch("/api/admin/users", { headers: getToken() });

    checkResponseResult(response);
    const users = await response.json();

    commit("SAVE_USERS", users);
  },
  async updateUser({ dispatch }, user) {
    const response = await fetch(`/api/admin/users/${user.id}`, {
      method: "put",
      headers: { "Content-Type": "application/json", ...getToken() }
    });
    checkResponseResult(response);
    dispatch("getUsers");
  },
  async saveUser({ dispatch }, user) {
    const response = await fetch(`/api/admin/users`, {
      method: "post",
      body: JSON.stringify(user),
      headers: { "Content-Type": "application/json", ...getToken() }
    });
    checkResponseResult(response);
    dispatch("getUsers");
  },
  async deleteUser({ dispatch }, id) {
    const response = await fetch(`/api/admin/users/${id}`, {
      method: "delete",
      headers: { "Content-Type": "application/json", ...getToken() }
    });
    checkResponseResult(response);
    dispatch("getUsers");
  },
  async whois({ commit }) {
    const response = await fetch("/api/me", {
      headers: { "Content-Type": "application/json", ...getToken() }
    });

    const user = await response.json();

    commit("LOGIN_SUCCESS", { user, token: sessionStorage.getItem("token") });
  }
};
