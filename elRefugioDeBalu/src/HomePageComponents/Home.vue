<template>
  <b-container fluid>
    <b-row class="justify-content-center mt-3">
      <b-col class="header-homePage shadow" cols="5" md="5" lg="5" xl="5">
        <h1 class="title-headerPage mt-2 text-end">Adopta amor,</h1>
      </b-col>
      <b-col class="header-homePageBlue shadow" cols="5" md="5" lg="5" xl="5">
        <h1 class="title-headerPage mt-2 text-start">adopta vida</h1>
      </b-col>
    </b-row>
    <b-row class="px-4 mt-3 d-flex justify-content-center">
      <b-col class="px-4" cols="12" sm="12" md="10" lg="10" xl="11">
        <CarouselGalery class="d-none d-md-flex" />
      </b-col>
      <b-col class="px-0" cols="12" sm="12" md="10" lg="10" xl="11">
        <b-jumbotron bg-variant="info" class="d-md-none py-3 px-3">
          <template #header>¿Quienes somos?</template>

          <template #lead>
            El refugio de Baloo es una organizacion que ayuda a nuestros amigos
            a encontrar un hogar, proporcionando servicios de adopciones y
            rescates, permitiendo que familias que estan en busca de un nuevo
            amigo, puedan encontralo facilmente...
          </template>

          <hr class="my-4" />

          <p>
            Nuestro principal objetivo es que nuestros diferentes tipos de
            amigos puedan encontrar un hogar en donde sean amados y ellos puedan
            ser integrados como parte de una familia...
          </p>

          <b-button class="btn-outline-dark-blue" pill href="#">Inicia sesion para conocerlos
            <i class="material-icons" style="font-size: 1rem">pets</i></b-button>
          <b-button pill class="my-3 btn-outline-dark-blue" href="#">Registrate
            <i class="material-icons" style="font-size: 1rem">pets</i>
          </b-button>
        </b-jumbotron>
      </b-col>
    </b-row>
    <b-row class="px-0 justify-content-center">
      <b-col class="ps-0" cols="11" sm="11" md="5" lg="5" xl="5">
        <Banner />
      </b-col>
      <b-col class="mb px-0" cols="11" sm="11" md="5" lg="5" xl="5">
        <CategoriesCarousel />
      </b-col>
    </b-row>
    <b-row class="justify-content-center mt-3">
      <b-col class="px-1_5" cols="11" sm="11" md="11" lg="11" xl="10">
        <div class="title-headerPage info-homepage w-100 mb-2 py-3 text-center w-100">
          <h4>
            Tu compañero ideal está esperando: descubre los amigos que tenemos
            para ti.
          </h4>
          <b-button class="home" to="/pets"><i class="material-icons">pets</i> Vamos a conocerlos
            <i class="material-icons">pets</i></b-button>
        </div>
      </b-col>
    </b-row>
    <b-row>
      <footer-balu-vue />
    </b-row>
  </b-container>
</template>

<script>
import CarouselGalery from "../HomePageComponents/Carrorusel.vue";
import Banner from "../HomePageComponents/Banner.vue";
import FooterBaluVue from "../views/FooterBalu.vue";
import CategoriesCarousel from "./CategoriesCarousel.vue";
import Swal from "sweetalert2";
import { decrypt } from '../kernel/hashFunctions';
export default {
  components: {
    CarouselGalery,
    Banner,
    FooterBaluVue,
    CategoriesCarousel,
  },
  data() {
    return {
      completeProfile: '',
      visibility: '',
    };
  },
  methods: {
    async CheckProfile() {
      this.visibility = await decrypt(localStorage.getItem("role"))
      if (this.visibility == "GENERAL") {
        this.completeProfile = await decrypt(localStorage.getItem("profileCompleted"))
        if (this.completeProfile) {
          Swal.fire({
            title: "¡Tu perfil no está  completo!",
            text: "Completa tu perfil para seguir buscando en el refugio…",
            icon: "warning",
            confirmButtonColor: "#53A93D",
            confirmButtonText: "Vamos a completarlo",
          }).then(() => {
            this.$router.push('/profile');
          });
        }
      } else {
        localStorage.removeItem("profileCompleted");
      }

      
    }
  },
  mounted() {
    this.CheckProfile();
  },
};
</script>
