import Vue from "vue";
import VueRouter from "vue-router";
import { decrypt } from "../kernel/hashFunctions";

Vue.use(VueRouter);
const DEFAULT_TITLE = "El Refugio de Balu";

const rolesAvailable = {
  general: "GENERAL",
  mod: "MOD",
  admin: "ADMIN",
};

const routes = [
  {
    path: "/",
    redirect: "/home",
  },
  {
    path: "*",
    name: "404",
    component: () => import("../views/components/errors/404.vue"),
  },
  {
    path: "/403",
    name: "403",
    meta: { title: "Acceso denegado" },
    component: () => import("../views/components/errors/403.vue"),
  },
  {
    path: "/500",
    name: "500",
    component: () => import("../views/components/errors/500.vue"),
  },
  {
    path: "/401",
    name: "401",
    meta: { title: "No autorizado" },
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
        meta: { title: "Mascotas disponibles" },
        component: () =>
          import("../modules/pets/adapters/views/PetCatalog.vue"),
      },
      {
        path: "/petDetails",
        name: "petDetails",
        meta: { title: "Detalles de la mascota" },
        props: true,
        component: () =>
          import("../modules/pets/adapters/views/PetDetails.vue"),
      },
      {
        path: "/favorites",
        name: "favorites",
        meta: {
          title: "Mis mascotas favoritas",
          requiresAuth: true,
          role: rolesAvailable.general,
        },
        component: () =>
          import("../modules/pets/adapters/views/FavoriteCatalog.vue"),
      },
      {
        path: "/myPets",
        name: "myPets",
        meta: {
          title: "Mis publicaciones",
          requiresAuth: true,
          role: rolesAvailable.general,
        },
        component: () =>
          import("../modules/pets/adapters/views/MyPetCatalog.vue"),
      },
      {
        path: "/formAdoption",
        name: "formAdoption",
        meta: {
          title: "Solicitud de adopción",
          requiresAuth: true,
          role: rolesAvailable.general,
        },
        component: () =>
          import("../modules/adoptions/adapters/views/FormAdoption.vue"),
      },
      {
        path: "/myAplicationAdoption",
        name: "myAplicationAdoption",
        meta: {
          title: "Mis Solicitudes de adopción",
          requiresAuth: true,
          role: [rolesAvailable.general],
        },
        component: () =>
          import(
            "../modules/adoptions/adapters/views/MyAplicationAdoption.vue"
          ),
      },
      {
        path: "/adoptionList",
        name: "adoptionList",
        meta: {
          title: "Solicitudes de adopción",
          requiresAuth: true,
          role: rolesAvailable.mod,
        },
        component: () =>
          import("../modules/adoptions/adapters/views/AdoptionList.vue"),
      },
      {
        path: "/viewAplicationAdoptionRequest",
        name: "viewAplicationAdoptionRequest",
        props: true,
        meta: {
          title: "Detalles de solicitud de adopción",
          requiresAuth: true,
          role: [rolesAvailable.general, rolesAvailable.mod],
        },
        component: () =>
          import("../modules/adoptions/adapters/views/ViewAplicationAdoptionRequest.vue"),
      },
      {
        path: "/login",
        name: "login",
        meta: {
          title: "Iniciar sesión",
        },
        component: () => import("../modules/auth/adapters/views/Login.vue"),
      },
      {
        path: "/petList",
        name: "petList",
        meta: {
          title: "Nuevas mascotas",
          requiresAuth: true,
          role: rolesAvailable.mod,
        },
        component: () => import("../modules/pets/adapters/views/PetList.vue"),
      },
      {
        path: "/moderated/petList",
        name: "moderatedPetList",
        meta: {
          title: "Mascotas asignadas",
          requiresAuth: false,
        },
        component: () => import("../modules/pets/adapters/views/ModeratedPetList.vue"),
      },
      {
        path: "/petForm",
        name: "petForm",
        meta: {
          title: "Registrar mascota",
          requiresAuth: true,
          role: rolesAvailable.general,
        },
        component: () =>
          import("../modules/pets/adapters/views/PetRegisterForm.vue"),
      },
      {
        path: "/usersList",
        name: "usersList",
        meta: {
          title: "Usuarios",
          requiresAuth: true,
          role: rolesAvailable.admin,
        },
        component: () => import("../modules/users/components/UsersList.vue"),
      },
      {
        path: "/animalCategories",
        name: "animals",
        meta: {
          title: "Categorías de animales",
          requiresAuth: true,
          role: rolesAvailable.admin,
        },
        component: () =>
          import("../modules/animals/adapters/views/AnimalCategories.vue"),
      },
      {
        path: "/usersForm",
        name: "usersForm",
        meta: {
          title: "Registrar usuario",
          requiresAuth: true,
          role: rolesAvailable.admin,
        },
        component: () => import("../modules/users/components/UsersForm.vue"),
      },
      {
        path: "/selfRegistration",
        name: "selfRegistration",
        meta:{
          title: "Crear cuenta"
        },
        component: () =>
          import("../modules/users/components/SelfRegistration.vue"),
      },
      {
        path: "/profile",
        name: "profile",
        meta:{
          title: "Perfil",
          requiresAuth: true,
          role: [rolesAvailable.general, rolesAvailable.mod, rolesAvailable.admin],
        },
        component: () => import("../modules/users/components/Profile.vue"),
      },
    ],
  },
];

const router = new VueRouter({ routes });

router.beforeEach(async (to,from, next)=>{
  
  if(to.meta.requiresAuth){
    let role = await decrypt(localStorage.getItem("role"));
    if(role){
      to.meta.role.includes(role) ? next() : next({name: "403"});
    }else{
      next({name: "401"});
    }
  }else{
    next();
  }
})

router.afterEach((to, from) => {
  Vue.nextTick(() => {
    document.title = to.meta.title || DEFAULT_TITLE;
  });
});
export default router;
