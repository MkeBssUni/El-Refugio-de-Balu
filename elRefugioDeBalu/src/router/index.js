import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);
const DEFAULT_TITLE = "El Refugio de Balu"

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
        path: "petDetails",
        name: "petDetails",
        component: () => import("../modules/pets/adapters/views/PetDetails.vue"),
      },
      {
        path: "/favorites",
        name: "favorites",
        component: () => import("../modules/pets/adapters/views/FavoriteCatalog.vue"),
      },
      {
        path: "/myPets",
        name: "myPets",
        component: () => import("../modules/pets/adapters/views/MyPetCatalog.vue"),
      },
      {
        path: "/formAdoption",
        name: "formAdoption",
        component: () => import("../modules/adoptions/adapters/views/FormAdoption.vue"),
      },{
        path: "/myAplicationAdoption",
        name: "myAplicationAdoption",
        component: () => import("../modules/adoptions/adapters/views/MyAplicationAdoption.vue"),
      },{
        path: "/adoptionList",
        name: "adoptionList",
        component: () => import("../modules/adoptions/adapters/views/AdoptionList.vue"),
      },{
        path: "/viewAplicationAdoptionRequest",
        name: "viewAplicationAdoptionRequest",
        component: () => import("../modules/adoptions/adapters/views/ViewAplication.vue"),
      },
      {
        path: "/login",
        name: "login",
        component: () => import("../modules/auth/adapters/views/Login.vue"),
      },
      {
        path: "/petList",
        name: "petList",
        component: () => import("../modules/pets/adapters/views/PetList.vue"),
      },
      {
        path: "/petForm",
        name: "petForm",
        component: () => import("../modules/pets/adapters/views/PetRegisterForm.vue"),
      },      
      {
        path: "/usersList",
        name: "usersList",
        component: () => import("../modules/users/components/UsersList.vue"),
      },
      {
        path: "/animalCategories",
        name: "animals",
        component: () => import("../modules/animals/adapters/views/AnimalCategories.vue"),
      },
      {
        path: "/usersForm",
        name: "usersForm",
        meta: { title: 'Registrar usuario'},
        component: () => import("../modules/users/components/UsersForm.vue"),
      },
      {
        path: "/selfRegistration",
        name: "selfRegistration",
        component: () => import("../modules/users/components/SelfRegistration.vue"),
      },
      {
        path: "/profile",
        name: "profile",
        component: () => import("../modules/users/components/Profile.vue"),
      }
    ],
  },
];

const router = new VueRouter({ routes });

router.afterEach((to,from)=>{
  Vue.nextTick(()=>{
    document.title = to.meta.title || DEFAULT_TITLE
  })
})
export default router;