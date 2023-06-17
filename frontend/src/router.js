import Vue from "vue";
import Router from "vue-router";
import Tasks from "@/views/Tasks.vue";
import Users from "@/views/Users.vue";
import Login from "@/views/Login.vue";
import NotFound from "@/views/NotFound.vue";

Vue.use(Router);

const router = new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        title: "login"
      }
    },
    {
      path: "/",
      name: "tasks",
      component: Tasks,
      meta: {
        title: "Tasks - pawaTask"
      }
    },
    {
      path: "/users",
      name: "users",
      component: Users,
      meta: {
        title: "Tasks - pawaTask"
      }
    },
    {
      path: "/404",
      component: NotFound,
      meta: {
        title: "404 - pawaTask"
      }
    },
    { path: "*", redirect: "/404" }
  ]
});

router.beforeEach((to, _, next) => {
  document.title = to.meta.title;

  next();
});

export default router;
