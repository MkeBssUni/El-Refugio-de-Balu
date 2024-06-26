<template>
  <b-container fluid>
    <b-row>
      <b-col cols="12" class="mt-5 px-4 px-sm-5">
        <!-- md, lg, xl card -->
        <b-card class="full-height-card p-4 d-none d-md-block card-shadow" bg-variant="gray">
          <LargeContent :pet="pet" />
          <hr class="divider my-0" />
          <b-row class="mt-4 d-flex justify-content-end">
            <b-col cols="5" lg="4" xl="3">
              <b-button variant="outline-dark-secondary-blue" @click="goAdopt"
                class="me-3 d-flex align-items-center justify-content-between w-100">
                <span class="me-2">Solicitar adoptar</span>
                <b-icon icon="heart" font-scale="1.3"></b-icon>
              </b-button>
            </b-col>
            <b-col cols="5" lg="4" xl="3">
              <b-button variant="outline-secondary-gray" @click="goBack"
                class="d-flex align-items-center justify-content-between w-100">
                <span class="me-2">Regresar</span>
                <b-icon icon="arrow-left-circle" font-scale="1.3"></b-icon>
              </b-button>
            </b-col>
          </b-row>
        </b-card>
        <!-- xs, sm card -->
        <b-card class="full-height-card p-2 d-block d-md-none card-shadow" bg-variant="gray">
          <SmallContent :pet="pet" />
          <hr class="divider my-0" />
          <b-row class="mt-4 d-flex justify-content-end">
            <b-col cols="12" sm="6">
              <b-button variant="outline-dark-secondary-blue" to="/formAdoption"
                class="me-3 d-flex align-items-center justify-content-between w-100">
                <span class="me-2">Solicitar adoptar</span>
                <b-icon icon="heart" font-scale="1.3"></b-icon>
              </b-button>
            </b-col>
            <b-col cols="12" sm="6" class="mt-3 mt-sm-0">
              <b-button variant="outline-danger" @click="goBack"
                class="d-flex align-items-center justify-content-between w-100">
                <span class="me-2">Regresar</span>
                <b-icon icon="arrow-left-circle" font-scale="1.3"></b-icon>
              </b-button>
            </b-col>
          </b-row>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import Swal from "sweetalert2";
import instance from "../../../../config/axios";
import { decrypt } from "../../../../kernel/hashFunctions";
import SmallContent from "../components/PetSmallCardContent.vue";
import LargeContent from "../components/PetLargeCardContent.vue";
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import {
  sizes,
  lifeStages,
  weightUnits,
  ageUnits,
} from "../../../../kernel/data/mappingDictionaries";

export default {
  data() {
    return {
      petId: "",
      pet: {},
    };
  },
  methods: {
    mapSize(size) {
      return sizes[size] || size;
    },
    mapLifeStage(lifeStage) {
      return lifeStages[lifeStage] || lifeStage;
    },
    mapAgeUnit(unit) {
      return ageUnits[unit] || unit;
    },
    mapWeightUnit(unit) {
      return weightUnits[unit] || unit;
    },
    swapImage(selectedImage) {
      let mainImage = this.pet.mainImage;
      this.pet.mainImage = selectedImage;

      const index = this.pet.images.findIndex(
        (image) => image === selectedImage
      );
      if (index !== -1) {
        this.pet.images.splice(index, 1, mainImage);
      } else {
        this.pet.images.push(mainImage);
      }
    },
    goBack() {
      this.$router.go(-1);
      localStorage.removeItem("petId");
    },
    async goAdopt() {
      if (localStorage.getItem("userId")) {
        const userHasProfile = await decrypt(
          localStorage.getItem("profileCompleted")
        );
        if (userHasProfile == "true") {
          this.$router.push({ name: "formAdoption" });
        } else {
          Swal.fire({
            title: "Perfil incompleto",
            text: "Para poder adoptar una mascota, necesitas completar tu perfil",
            icon: "warning",
            iconColor: "#ff7d4a",
            showCancelButton: true,
            confirmButtonText: "Completar perfil",
            cancelButtonText: "Cancelar",
            confirmButtonColor: "#ff7d4a",
            cancelButtonColor: "#A93D3D",
          }).then((result) => {
            if (result.isConfirmed) {
              this.$router.push({ name: "profile" });
            }
          });
        }
      } else {
        Swal.fire({
          title: "Inicia sesión",
          text: "Para adoptar mascotas debes de iniciar sesión",
          icon: "info",
          iconColor: "#FFA500",
          showCancelButton: true,
          confirmButtonColor: "#FFA500",
          cancelButtonColor: "#A93D3D",
          confirmButtonText: "Iniciar sesión",
          cancelButtonText: "Cancelar",
        }).then((result) => {
          if (result.isConfirmed) {
            this.$router.push("/login");
          }
        });
      }
    },
    async getDetails() {
      try {
        Swal.fire({
          title: "Cargando...",
          text: "Estamos cargando los detalles de la mascota, espera un momento",
          imageUrl: gatoWalkingGif,
          imageWidth: 160,
          imageHeight: 160,
          showConfirmButton: false,
        });
        const response = await instance.post(`/pet/details`, {
          id: this.petId,
        });
        this.pet = response.data.data;
        if (this.pet.diseases == "") this.pet.diseases = null;
        if (this.pet.allergies == "") this.pet.allergies = null;
        if (this.pet.specialCares == "") this.pet.specialCares = null;
        Swal.close();
      } catch (error) {
        Swal.fire({
          title: "Error",
          text: "Ocurrió un error al cargar los detalles de la mascota",
          icon: "error",
          iconColor: "#A93D3D",
          timer: 3000,
          timerProgressBar: true,
          showConfirmButton: false,
        }).then(() => {
          this.goBack();
        });
      }
    },
  },
  mounted() {
    if (localStorage.getItem("petId")) {
      this.petId = localStorage.getItem("petId");
      this.getDetails();
    } else {
      this.goBack();
    }
  },
  components: {
    SmallContent,
    LargeContent,
  },
};
</script>

<style scoped>
.full-height-card {
  border-bottom-left-radius: 0 !important;
  border-bottom-right-radius: 0 !important;
}

.card-shadow {
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.089);
}
</style>
