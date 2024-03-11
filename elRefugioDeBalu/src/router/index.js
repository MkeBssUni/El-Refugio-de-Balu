import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: "/home",
  },
  {
    path: "/404",
    name: "404",
    component: () => import("../views/components/errors/404.vue"),
  },{
    path: "/403",
    name: "403",
    component: () => import("../views/components/errors/403.vue"),
  },
  {
    path: "/500",
    name: "500",
    component: () => import("../views/components/errors/500.vue"),
  },{
    path: "/401",
    name: "401",
    component: () => import("../views/components/errors/401.vue"),
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
        component: () => import("../HomePageComponents/Home.vue"),
      },
      {
        path: "/pets",
        name: "pets",
        component: () => import("../modules/pets/adapters/views/PetCatalog.vue"),
      },
      {
        path: "/favorites",
        name: "favorites",
        component: () => import("../modules/pets/adapters/views/FavoriteCatalog.vue"),
      },
      {
        path: "/requestAdoption",
        name: "requestAdoption",
        component: () => import("../modules/adoptions/adapters/views/AdoptionRequest.vue"),
      },
      {
        path: "/formAdoption",
        name: "formAdoption",
        component: () => import("../modules/adoptions/adapters/views/FormAdoption.vue"),
      },
      {
        path: "/login",
        name: "login",
        component: () => import("../modules/auth/adapters/views/Login.vue"),
      },
      {
        path: "/pet-list",
        name: "pet-list",
        component: () => import("../modules/pets/adapters/views/PetList.vue"),
      },
      {
        path: "/petForm",
        name: "petForm",
        component: () => import("../modules/pets/adapters/views/RegisterForm.vue"),
      },
      {
        path: "/UsersList",
        name: "UsersList",
        component: () => import("../modules/users/components/UsersList.vue"),
      },
      {
        path: "/animalCategories",
        name: "animals",
        component: () => import("../modules/animals/adapters/views/AnimalCategories.vue"),
      },
      {
        path: "/UsersForm",
        name: "UsersForm",
        component: () => import("../modules/users/components/UsersForm.vue"),
      },
      {
        path: "/SelfRegistration",
        name: "SelfRegistration",
        component: () => import("../modules/users/components/SelfRegistration.vue"),
      }
    ],
  },
];

const router = new VueRouter({ routes });
export default router;