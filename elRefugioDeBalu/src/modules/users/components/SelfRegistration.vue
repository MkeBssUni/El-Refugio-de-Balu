<template>
  <b-container fluid class="bg-blue">
    <b-row align-v="center">
      <!-- Formulario inicio de sesión -->
      <b-col cols="6" class="px-5 mr-3 mx-auto">
        <b-card class="shadow bg-orange">
          <img
            :src="require('@/assets/imgs/logo blanco.png')"
            class="img-fluid d-block mx-auto"
            alt="Responsive image"
            width="30%"
          />
          <h3 class="text-center text-white">Crear Cuenta</h3>
          <b-form class="px-5" @submit.prevent="submitForm">
            <b-form-group
              class="my-3"
              label="Nombre:"
              label-for="input-name"
              :state="nameValidation"
            >
              <b-form-input
                class="bg-light shadow text-dark-gray-input"
                id="input-name"
                v-model.trim="form.name"
              ></b-form-input>
              <b-form-invalid-feedback :state="nameValidation"
                >El nombre debe contener solo letras</b-form-invalid-feedback
              >
            </b-form-group>

            <b-form-group
              class="my-3"
              label="Apellido Paterno:"
              label-for="input-lastname"
              :state="lastNameValidation"
            >
              <b-form-input
                class="bg-light shadow text-dark-gray-input"
                id="input-lastname"
                v-model.trim="form.lastName"
              ></b-form-input>
              <b-form-invalid-feedback :state="lastNameValidation"
                >El apellido debe contener solo letras</b-form-invalid-feedback
              >
            </b-form-group>

            <b-form-group
              class="my-3"
              label="Apellido Materno:"
              label-for="input-surname"
            >
              <b-form-input
                class="bg-light shadow text-dark-gray-input"
                id="input-surname"
                v-model.trim="form.surName"
              ></b-form-input>
            </b-form-group>

            <b-form-group
              class="my-3"
              label="Correo electrónico:"
              label-for="input-email"
              :state="emailValidation"
            >
              <b-form-input
                class="bg-light shadow text-dark-gray-input"
                id="input-email"
                v-model.trim="form.email"
                type="email"
              ></b-form-input>
              <b-form-invalid-feedback :state="emailValidation"
                >Correo electrónico inválido</b-form-invalid-feedback
              >
            </b-form-group>

            <b-form-group
              class="my-3"
              label="Contraseña:"
              label-for="input-password"
              :state="passwordValidation"
            >
              <b-form-input
                id="input-password"
                class="bg-light shadow text-dark-gray-input"
                v-model.trim="form.password"
                type="password"
              ></b-form-input>
              <b-form-invalid-feedback :state="passwordValidation">{{
                passwordValidationMessage
              }}</b-form-invalid-feedback>
            </b-form-group>

            <b-form-group
              class="my-3"
              label="Número de teléfono:"
              label-for="input-phone"
            >
              <b-form-input
                class="bg-light shadow text-dark-gray-input"
                id="input-phone"
                type="number"
                v-model.trim="form.phoneNumber"
              ></b-form-input>
            </b-form-group>
            <!-- centrar el botón -->
            <b-row class="justify-content-center">
              <b-col
                class="d-flex justify-content-center align-items-center mt-3"
              >
                <b-button
                  class="bg-dark-secondary-orange text-white mx-5"
                  type="submit"
                  :disabled="disableButton()"
                  >Crear cuenta</b-button
                >
              </b-col>
            </b-row>
          </b-form>
        </b-card>
      </b-col>
      <b-col cols="5" class="d-flex justify-content-center ml-3 mx-auto">
        <b-img
          :src="selfRegistrationImage"
          fluid
          left
          rounded
          class="img mx-auto"
        ></b-img>
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
import swal from "sweetalert2";
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import selfRegistrationImage from "@/assets/imgs/SelfRegistration.jpg";
export default {
  data() {
    return {
      selfRegistrationImage: selfRegistrationImage,
      form: {
        name: "",
        lastName: "",
        surName: "",
        email: "",
        password: "",
        phoneNumber: "",
      },
      nameValidation: null,
      lastNameValidation: null,
      emailValidation: null,
      passwordValidation: null,
      passwordValidationMessage: "",
    };
  },
  methods: {
    disableButton() {
      return !(
        this.nameValidation === true &&
        this.lastNameValidation === true &&
        this.emailValidation === true &&
        this.passwordValidation === true
      );
    },
    submitForm() {
      swal
        .fire({
          title: "¿Estás seguro de enviar la solicitud de registro?",
          text: "Una vez enviada no podrá ser modificada",
          icon: "warning",
          showCancelButton: true,
          confirmButtonColor: "#3085d6",
          cancelButtonColor: "#d33",
          confirmButtonText: "Si, enviar",
          cancelButtonText: "Cancelar",
        })
        .then((result) => {
          if (result.isConfirmed) {
            swal.fire({
              title: "Espera un momento...",
              text: "Estamos enviando tu solicitud",
              imageUrl: gatoWalkingGif,
              timer: 2000,
              timerProgressBar: true,
              imageWidth: 160, // Ancho de la imagen
              imageHeight: 160, // Altura de la imagen
              showConfirmButton: false,
            });
          }
        });
    },
  },
  watch: {
    "form.name"(newVal) {
      this.nameValidation = /^[a-zA-Z]+$/.test(newVal.trim());
    },
    "form.lastName"(newVal) {
      this.lastNameValidation = /^[a-zA-Z]+$/.test(newVal.trim());
    },
    "form.email"(newVal) {
      this.emailValidation = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(newVal.trim());
    },
    "form.password"(newVal) {
      this.passwordValidation =
        /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{8,}$/.test(newVal);
      if (!this.passwordValidation) {
        let missingRequirements = [];
        if (!/(?=.*\d)/.test(newVal))
          missingRequirements.push("al menos un número (0-9)");
        if (!/(?=.*[a-z])/.test(newVal))
          missingRequirements.push("al menos una letra minúscula (a-z)");
        if (!/(?=.*[A-Z])/.test(newVal))
          missingRequirements.push("al menos una letra mayúscula (A-Z)");
        if (/\s/.test(newVal))
          missingRequirements.push("no contener espacios en blanco");
        if (newVal.length < 8)
          missingRequirements.push("tener una longitud mínima de 8 caracteres");
        this.passwordValidationMessage =
          "La contraseña debe: " + missingRequirements.join(", ") + ".";
      } else {
        this.passwordValidationMessage = "";
      }
    },
  },
};
</script>

<style scoped>
.bg-blue {
  background-color: blue;
}

.img-fluid {
  max-width: 100%;
}
</style>
