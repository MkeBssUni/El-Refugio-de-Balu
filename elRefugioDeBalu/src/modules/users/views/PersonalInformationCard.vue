<template>
  <b-col cols="12" class="px-2 px-sm-4 px-xl-5 mt-4">
    <b-row>
      <b-col cols="10" md="8" lg="6">
        <b-card
          bg-variant="card-header-orange"
          class="py-2 form-card-shadow relative-position form-card-title"
          no-body
        >
          <div class="d-flex align-items-center ms-3 ms-md-4">
            <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
            <h4 class="mb-0 mt-1">Información Personal</h4>
          </div>
        </b-card>
      </b-col>
    </b-row>
    <b-col cols="12">
                <b-card bg-variant="card-content-orange" class="form-card-shadow form-card-content">
                    <b-card-body>
    <b-row>
      <b-col cols="12" md="4">
        <b-form-group label="Nombre" label-align="left">
          <b-input v-model="information.name" :readonly="!editing"></b-input>
        </b-form-group>
      </b-col>
      <b-col cols="12" md="4">
        <b-form-group label="Apellido" label-align="left">
          <b-input
            v-model="information.lastName"
            :readonly="!editing"
          ></b-input>
        </b-form-group>
      </b-col>
      <b-col cols="12" md="4">
        <b-form-group label="Segundo apellido" label-align="left">
          <b-input v-model="information.surName" :readonly="!editing"></b-input>
        </b-form-group>
      </b-col>
      <b-col cols="12" md="4">
        <b-form-group
          label="Teléfono"
          label-align="left"
          :state="phoneNumberValidation"
        >
          <b-input
            v-model="information.phoneNumber"
            type="tel"
            pattern="[0-9]*"
          ></b-input>
          <b-form-invalid-feedback :state="phoneNumberValidation">
            Ingresa solo números
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
      <b-col cols="12" md="4">
        <b-form-group
          label="Correo electrónico"
          label-align="left"
          :state="secondaryEmailValidation"
        >
          <b-input v-model="information.user.username" type="email"></b-input>
          <b-form-invalid-feedback :state="secondaryEmailValidation">
            Correo electrónico inválido
          </b-form-invalid-feedback>
        </b-form-group>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="12">
        <div class="d-flex justify-content-end mt-3">
          <b-button @click="submitForm" variant="outline-light">
            {{ editing ? "Guardar" : "Modificar" }}
            <b-icon icon="person-circle" font-scale="1.3"></b-icon>
          </b-button>
        </div>
      </b-col>
    </b-row>
                    </b-card-body>
                </b-card>
    </b-col>
    
  </b-col>
</template>

<script>
import swal from "sweetalert2";
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import instance from "../../../config/axios";
import { decrypt } from "../../../kernel/hashFunctions";
export default {
  name: "FormStyle",
  data() {
    return {
      userId: null,
      name: "",
      lastName: "",
      surName: "",
      phoneNumber: "",
      secondaryPhoneNumber: "",
      secondaryEmail: "",
      editing: false,
      phoneNumberValidation: null,
      secondaryEmailValidation: null,
      information: {},
    };
  },
  mounted() {
    this.getDetails();
  },

  methods: {
    async getDetails() {
      swal.fire({
        title: "Espera un momento...",
        text: "Estamos cargando tus datos",
        imageUrl: gatoWalkingGif,
        imageWidth: 160,
        imageHeight: 160,
        showConfirmButton: false,
      });
      try {
        const response = await instance.post("/person/details", {
          userId: localStorage.getItem("userId"),
        });
        this.information = response.data.data;
        this.information.phoneNumber = await decrypt(
          this.information.phoneNumber
        );
        this.information.user.username = await decrypt(
          this.information.user.username
        );
        swal.close();
      } catch (error) {
        swal
          .fire({
            title: "Error",
            text: "No se pudieron cargar tus datos",
            icon: "error",
            showConfirmButton: false,
            timer: 1500,
          })
          .then(() => {
            this.$router.go(-1);
          });
      }
    },
  },
  watch: {
    phoneNumber(newVal) {
      this.phoneNumberValidation = /^[0-9]{10}$/.test(newVal.trim())
        ? true
        : false;
    },
    secondaryEmail(newVal) {
      this.secondaryEmailValidation =
        /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test(newVal.trim())
          ? true
          : false;
    },
  },
};
</script>

<style>
.encabezadoColorform {
  width: 50%;
  background-color: #4db8c0;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(255, 255, 255, 0.05) 0px 0.25em 1em;
  position: relative;
  z-index: 2;
  color: #ffffff;
}

.contentform {
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
  margin-top: -30px;
  position: relative;
  z-index: 1;
  border-radius: 20px;
  background-color: #ffffff;
}
.card.encabezadoColorform {
  margin: 0 auto;
  width: 95%;
  height: 60px;
  padding: 20px;
  border-radius: 60px;
  text-align: center;
}
</style>
