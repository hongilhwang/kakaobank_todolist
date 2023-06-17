export default {
  SAVE_TASKS(state, tasks) {
    state.tasks = tasks;
  },
  UPDATE_TASK(state, task) {
    state.tasks = [task, ...state.tasks.filter(({ id }) => id !== task.id)];
  },
  UPDATE_COMMENT(state, comment) {
    state.tasks.forEach(({ comments }) => {
      for (const { id } of comments) {
        if (id === comment.id) {
          comments = [
            ...comments.filter(({ id }) => id !== comment.id),
            comment
          ];
          return;
        }
      }
    });
  },
  LOGIN_FAILURE(state, message) {
    state.login_failure = message;
  },
  LOGIN_SUCCESS(state, loginUser) {
    state.user = loginUser.user;
    state.login_failure = "";
    sessionStorage.setItem("token", loginUser.token);
  },
  SAVE_USERS(state, users) {
    state.users = users;
  }
};
