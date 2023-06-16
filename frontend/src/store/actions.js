import { parse } from "date-fns";

const getToken = () => {
  const token = sessionStorage.getItem('token')
  if (token === null || token === "") location.href = "/login";
  return { Authentication: `BEARER ${token}` };
};

export default {
  sortTasks({ state }) {
    state.tasks.sort((a, b) => a.id - b.id);
  },
  async getTasks({ commit, dispatch }) {
    const response = await fetch("/api/tasks", { headers: getToken() });
    const tasks = await response.json();
    if (!response.ok) {
      console.error(response);
      return;
    }

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
    if (!response.ok) {
      console.error(response);
      return;
    }

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
    if (!response.ok) {
      console.error(response);
      return;
    }

    const taskNew = await response.json();
    commit("UPDATE_TASK", taskNew);
    dispatch("sortTasks");
  },
  async deleteTask({ dispatch }, taskId) {
    const response = await fetch(`/api/tasks/${taskId}`, {
      method: "delete",
      headers: getToken()
    });

    if (!response.ok) {
      console.error(response);
      return;
    }

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

    if (!response.ok) {
      console.error(response);
      return;
    }

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

    if (!response.ok) {
      console.error(response);
      return;
    }

    const commentUpdated = await response.json();
    commit("UPDATE_COMMENT", commentUpdated);
    dispatch("sortTasks");
  },
  async deleteComment({ dispatch }, commentId) {
    const response = await fetch(`/api/comments/${commentId}`, {
      method: "delete",
      headers: getToken()
    });

    if (!response.ok) {
      console.error(response);
      return;
    }

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
    location.href = "/";
  }
};
