import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: "/home",
  },
  {
    path: "/",
    component: {
      render(c) {
        return c("router-view");
      },
    },
    children: [
      {
        path: "/home",
        name: "home",
        component: () => import("../views/Home.vue"),
      },
      {
        path: "/pets",
        name: "pets",
        component: () => import("../modules/pets/adapters/views/PetCatalog.vue"),
      },
      {
        path: "/requestAdoption",
        name: "requestAdoption",
        component: () => import("../modules/adoptions/adapters/views/AdoptionRequest.vue"),
      },
      {
        path: "/login",
        name: "login",
        component: () => import("../modules/auth/adapters/views/Login.vue"),
      }
    ],
  },
];

const router = new VueRouter({ routes });
export default router;