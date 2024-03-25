<template>
  <b-container fluid class="bg-blue">
    <b-row align-v="center">
      <b-col cols="6" class="px-5 mr-3 mx-auto">
        <b-card class="shadow bg-orange">
          <img :src="require('@/assets/imgs/logo blanco.png')" class="img-fluid d-block mx-auto" alt="Responsive image"
            width="30%" />
          <h3 class="text-center text-white">Crear Cuenta</h3>
          <b-form class="px-5" @submit.prevent="submitForm">
            <b-form-group class="my-3" label="Nombre:" label-for="input-name" :state="nameValidation">
              <b-form-input class="bg-light shadow text-dark-gray-input" id="input-name"
                v-model.trim="form.name"></b-form-input>
              <b-form-invalid-feedback :state="nameValidation">El nombre debe contener solo
                letras</b-form-invalid-feedback>
            </b-form-group>

            <b-form-group class="my-3" label="Apellido Paterno:" label-for="input-lastname" :state="lastnameValidation">
              <b-form-input class="bg-light shadow text-dark-gray-input" id="input-lastname"
                v-model.trim="form.lastname"></b-form-input>
              <b-form-invalid-feedback :state="lastnameValidation">El apellido debe contener solo
                letras</b-form-invalid-feedback>
            </b-form-group>

            <b-form-group class="my-3" label="Apellido Materno:" label-for="input-surname">
              <b-form-input class="bg-light shadow text-dark-gray-input" id="input-surname"
                v-model.trim="form.surname"></b-form-input>
            </b-form-group>

            <b-form-group class="my-3" label="Correo electrónico:" label-for="input-email" :state="usernameValidation">
              <b-form-input class="bg-light shadow text-dark-gray-input" id="input-email" v-model.trim="form.username"
                type="email"></b-form-input>
              <b-form-invalid-feedback :state="usernameValidation">Correo electrónico inválido</b-form-invalid-feedback>
            </b-form-group>

            <b-form-group class="my-3" label="Contraseña:" label-for="input-password" :state="passwordValidation">
              <b-form-input id="input-password" class="bg-light shadow text-dark-gray-input"
                v-model.trim="form.password" type="password"></b-form-input>
              <b-form-invalid-feedback :state="passwordValidation">{{
            passwordValidationMessage
          }}</b-form-invalid-feedback>
            </b-form-group>
            <b-form-group class="my-3" label="Repetir Contraseña:" label-for="input-password-confirm" :state="passwordValidation">
              <b-form-input id="input-password-confirm" class="bg-light shadow text-dark-gray-input"
                v-model.trim="form.confirmPassword" type="password"></b-form-input>
              <b-form-invalid-feedback :state="confirmPasswordValidation">{{
            confirmPasswordValidationMessage
          }}</b-form-invalid-feedback>
            </b-form-group>

            <b-form-group class="my-3" label="Número de teléfono:" label-for="input-phone">
              <b-form-input maxlength="10" class="bg-light shadow text-dark-gray-input" id="input-phone" type="number"
                v-model.trim="form.phoneNumber"></b-form-input>
              <b-form-invalid-feedback :state="phoneValidation">{{ phoneValidationMessage }}</b-form-invalid-feedback>
            </b-form-group>
            <b-row class="justify-content-center">
              <b-col class="d-flex justify-content-center align-items-center mt-3">
                <b-button class="bg-dark-secondary-orange text-white mx-5" type="submit"
                  :disabled="disableButton()">Crear cuenta</b-button>
              </b-col>
            </b-row>
          </b-form>
        </b-card>
      </b-col>
      <b-col cols="5" class="d-flex justify-content-center ml-3 mx-auto">
        <b-img :src="selfRegistrationImage" fluid left rounded class="img mx-auto"></b-img>
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
import swal from "sweetalert2";
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import selfRegistrationImage from "@/assets/imgs/SelfRegistration.jpg";
import { encrypt } from '../../../kernel/hashFunctions';
import instance from "../../../config/axios";
export default {
  data() {
    return {
      selfRegistrationImage: selfRegistrationImage,
      form: {
        name: "",
        lastname: "",
        surname: "",
        username: "",
        password: "",
        confirmPassword: "",
        phoneNumber: "",
      },
      encryptedForm: {
        name: "",
        lastname: "",
        surname: "",
        username: "",
        password: "",
        phoneNumber: "",
      },
      nameValidation: null,
      lastnameValidation: null,
      usernameValidation: null,
      passwordValidation: null,
      phoneValidation: null,
      confirmPasswordValidation: null,
      confirmPasswordValidationMessage: "",
      phoneValidationMessage: "",
      passwordValidationMessage: "",
    };
  },
  methods: {
    disableButton() {
      return !(
        this.nameValidation === true &&
        this.lastnameValidation === true &&
        this.usernameValidation === true &&
        this.passwordValidation === true &&
        this.phoneValidation === true &&
        this.confirmPasswordValidation === true
      );
    },
    async activateAccount(){
      swal.fire({
            title: "Código de verificación",
            text: "Ingresa el código de verificación que te enviamos a tu correo",
            input: "text",
            showCancelButton: true,
            confirmButtonText: "Verificar",
            cancelButtonText: "Cancelar",
            showDenyButton: true,
            denyButtonText: "Reenviar código",
            showLoaderOnConfirm: true,
            preDeny: async ()=>{
              return instance.patch("/person/send/newCode", { username: this.encryptedForm.username })
              .then((response) => {
                if (response.status === 200) {
                  swal.fire({
                    title: "Código reenviado",
                    text: "Hemos enviado un nuevo código de verificación a tu correo",
                    icon: "success",
                    showConfirmButton: true,
                  }).then(() => {
                    this.activateAccount();
                  });
                } else {
                  swal.fire({
                    title: "Error",
                    text: "Algo salió mal, por favor intenta de nuevo más tarde",
                    icon: "error",
                    showConfirmButton: true,
                  });
                }
              })
            },
            preConfirm: async (code) => {
              let encryptedCode = await encrypt(code);
              return instance.patch("/person/activate/account", { activationCode: encryptedCode, username: this.encryptedForm.username })
                .then((response) => {
                  if (response.status === 200) {
                    swal.fire({
                      title: "Cuenta verificada",
                      text: "Tu cuenta ha sido verificada con éxito, ahora puedes iniciar sesión",
                      icon: "success",
                      showConfirmButton: true,
                    });
                    this.$router.push("/login");
                  } else {
                    swal.fire({
                      title: "Error",
                      text: "El código de verificación es incorrecto",
                      icon: "error",
                      showConfirmButton: true,
                    });
                  }
                })
                .catch((error) => {
                  swal.fire({
                    title: "Error",
                    text: "Algo salió mal, por favor intenta de nuevo más tarde",
                    icon: "error",
                    showConfirmButton: true,
                  }).then(() => {
                    this.activateAccount();
                  });
                });
            },
            allowOutsideClick: false, 
          });
    },
    async sendForm() {
      this.encryptedForm.password = await encrypt(this.form.password);
      this.encryptedForm.lastname = await encrypt(this.form.lastname);
      this.encryptedForm.surname = await encrypt(this.form.surname);
      this.encryptedForm.phoneNumber = await encrypt(this.form.phoneNumber);
      this.encryptedForm.username = await encrypt(this.form.username);
      this.encryptedForm.name = await encrypt(this.form.name);
      try {
        const response = await instance.post("/person/publicRegister", this.encryptedForm)
        if (response.status === 201) {
          swal.fire({
            title: "Solicitud enviada",
            text: "Tu solicitud ha sido enviada con éxito, en breve recibirás un correo para activar tu cuenta",
            icon: "success",
            showConfirmButton: true,
          }).then(() => {
            this.activateAccount();
          });
        }
      } catch (error) {
        swal.fire({
          title: "Error",
          text: "Algo salió mal, por favor intenta de nuevo más tarde",
          icon: "error",
          showConfirmButton: true,
        });
      }

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
              imageWidth: 160,
              imageHeight: 160,
              showConfirmButton: false,
            });
            this.sendForm();
          }
        });
    },
  },
  watch: {
    "form.name"(newVal) {
      this.nameValidation = /^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ' .]+$/.test(newVal.trim());
    },
    "form.lastname"(newVal) {
      this.lastnameValidation = /^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ' .]+$/.test(newVal.trim());
    },
    "form.username"(newVal) {
      this.usernameValidation = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test(newVal.trim());
    },
    "form.phoneNumber"(newVal) {
      this.phoneValidation = /^[0-9]{10}$/.test(newVal.trim());
      if (newVal.length > 10) {
        this.phoneValidationMessage = "El número de teléfono no puede tener más de 10 dígitos";
      }
    },
    "form.password"(newVal) {
      this.passwordValidation =
        /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,}$/.test(newVal);
      if (!this.passwordValidation) {
        let missingRequirements = [];
        if (!/(?=.*\d)/.test(newVal))
          missingRequirements.push("debe contener al menos un número (0-9)");
        if (!/(?=.*[a-z])/.test(newVal))
          missingRequirements.push("debe contener al menos una letra minúscula (a-z)");
        if (!/(?=.*[A-Z])/.test(newVal))
          missingRequirements.push("debe contener al menos una letra mayúscula (A-Z)");
        if (/\s/.test(newVal))
          missingRequirements.push("no debe contener espacios en blanco");
        if (newVal.length < 8)
          missingRequirements.push("debe tener una longitud mínima de 8 caracteres");
        this.passwordValidationMessage =
          "La contraseña " + missingRequirements.join(", ") + ".";
      } else {
        this.passwordValidationMessage = "";
      }
    },
    "form.confirmPassword"(newVal) {
      if (newVal !== this.form.password) {
        this.confirmPasswordValidation = false;
        this.confirmPasswordValidationMessage = "Las contraseñas no coinciden";
      }else{
        this.confirmPasswordValidation = true;
        this.confirmPasswordValidationMessage =""
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
