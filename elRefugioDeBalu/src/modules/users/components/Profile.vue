<template>
  <b-container
    fluid
    class="d-flex justify-content-center align-items-center h-100 bg-blueC"
  >
    <div class="container-fluid mx-auto" style="width: 100%; margin: 50px 0">
      <div class="card encabezadoColorform text-center">
        <h4 style="margin-left: 2rem; color: white">Perfil</h4>
      </div>
      <b-card-group deck>
        <b-card class="contentform" img-alt="Card image">
          <b-container>
            <div class="clearfix">
              <b-img
                left
                src="https://picsum.photos/200/200/?image=58"
                alt="Left image"
                class="mb-4"
                style="max-width: 400px; margin-top: 70px"
              ></b-img>
              <b-col cols="12" class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
                <b-row>
                  <b-col cols="10" md="8" lg="6">
                    <b-card
                      class="py-2 card-shadow card encabezadoColorform2 text-center"
                      no-body
                    >
                      <div class="d-flex align-items-center ms-3 ms-md-4">
                        <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
                        <h4 class="mb-0" style="color: white">Información personal</h4>
                      </div>
                    </b-card>
                  </b-col>
                </b-row>
                <b-row >  
                  <b-col cols="12">
                    <b-card bg-variant="dark-gray" class="card-shadow col-md-12">
                      <b-container>
                        <div class="row">
                          <div class="col-md-4">
                            <b-form-group label="Nombre" label-align="left">
                              <b-input v-model="name" :readonly="!editing"></b-input>
                            </b-form-group>
                          </div>
                          <div class="col-md-4">
                            <b-form-group label="Apellido" label-align="left">
                              <b-input v-model="lastName" :readonly="!editing"></b-input>
                            </b-form-group>
                          </div>
                          <div class="col-md-4">
                            <b-form-group label="Segundo apellido" label-align="left">
                              <b-input v-model="surName" :readonly="!editing"></b-input>
                            </b-form-group>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-md-4">
                            <b-form-group label="Teléfono" label-align="left" :state="phoneNumberValidation">
                              <b-input v-model="phoneNumber" type="tel"></b-input>
                              <b-form-invalid-feedback :state="phoneNumberValidation">Ingresa solo números</b-form-invalid-feedback>
                            </b-form-group>
                          </div>
                          <div class="col-md-4">
                            <b-form-group label="Correo electrónico secundario" label-align="left" :state="secondaryEmailValidation">
                              <b-input v-model="secondaryEmail" type="email"></b-input>
                              <b-form-invalid-feedback :state="secondaryEmailValidation">Correo electrónico inválido</b-form-invalid-feedback>
                            </b-form-group>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-md-8 container d-flex justify-content-end mt-3">
                            <b-button @click="submitForm" class="btn1">{{ editing ? "Guardar" : "Modificar" }}</b-button>
                          </div>
                        </div>
                      </b-container>
                    </b-card>
                  </b-col>
                </b-row>
                <div class="d-flex justify-content-end mt-3">
                  <b-button v-b-toggle.sidebar-right class="btn1">Cambiar contraseña</b-button>
                </div>
              </b-col>
            </div>
          </b-container>
        </b-card>
      </b-card-group>
      <!-- Sidebar -->
      <b-sidebar id="sidebar-right" right shadow>
        <div class="px-3 py-2">
          <div class="d-flex align-items-center mb-3 text-dark-secondary-blue">
            <i class="material-icons me-2 text-dark-secondary-blue" style="font-size: 1.5rem">pets</i>
            <h4 class="mb-0 text-dark-secondary-blue">Cambio de contraseña</h4>
          </div>
          <b-form-group label="Cambio de contraseña actual">
            <b-form-input v-model="currentPassword" type="password"></b-form-input>
          </b-form-group>
          <b-form-group label="Nueva contraseña" :state="passwordValidation">
            <b-form-input v-model="newPassword" type="password"></b-form-input>
            <b-form-invalid-feedback :state="passwordValidation">{{ passwordValidationMessage }}</b-form-invalid-feedback>
          </b-form-group>
          <b-form-group label="Confirmar contraseña">
            <b-form-input v-model="confirmPassword" type="password"></b-form-input>
            <b-form-invalid-feedback :state="confirmPasswordValidation">{{ confirmPasswordValidationMessage }}</b-form-invalid-feedback>
          </b-form-group>
          <div class="mt-3 text-right">
            <b-button @click="changePassword" variant="outline-success" class="mr-2">Modificar</b-button>
            <b-button @click="$bvModal.hide('sidebar-right')" variant="outline-danger">Cancelar</b-button>
          </div>
            <div class="mt-5" style="position: absolute; bottom: 0; right: 0;">
        <img src="../../../assets//imgs/gatocontra.png" width="200" alt="gato" fluid  style="margin-left: 10px;">
      </div>
        </div>
      </b-sidebar>
    </div>
  </b-container>
</template>

<script>
import swal from "sweetalert2";
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
export default {
  name: "FormStyle",
  data() {
    return {
      name: "Andrea",
      lastName: "Díaz",
      surName: "Zagal",
      phoneNumber: "123456789",
      secondaryPhoneNumber: "987654321",
      secondaryEmail: "andrea@gmail.com",
      editing: false,
      currentPassword: "",
      newPassword: "",
      confirmPassword: "",
      phoneNumberValidation: null,
      secondaryEmailValidation: null,
      passwordValidation: null,
      passwordValidationMessage: "",
      confirmPasswordValidation: null,
      confirmPasswordValidationMessage: "",
    };
  },
  methods: {
    toggleSidebar() {
      this.$bvModal.show("sidebar-1");
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
    changePassword() {
    },
  },
  watch: {
    phoneNumber(newVal) {
      this.phoneNumberValidation = /^[0-9]{10}$/.test(newVal.trim()) ? true : false;
    },
    secondaryEmail(newVal) {
      this.secondaryEmailValidation = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test(newVal.trim()) ? true : false;
    },
    newPassword(newVal) {
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
    confirmPassword(newVal) {
      if (newVal !== this.newPassword) {
        this.confirmPasswordValidation = false;
        this.confirmPasswordValidationMessage = "Las contraseñas no coinciden";
      } else {
        this.confirmPasswordValidation = true;
        this.confirmPasswordValidationMessage = "";
      }
    },
  },
};
</script>

<style>
.bg-blueC {
  background-color: #118a95;
}
.encabezadoColorform {
  width: 50%;
  background-color: #5ebfc5;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em, rgba(255, 255, 255, 0.05) 0px 0.25em 1em;
  position: relative;
  z-index: 2;
  color: #ffffff;
}
.encabezadoColorform2 {
  width: 50%;
  background-color: #5ebfc5;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em, rgba(255, 255, 255, 0.05) 0px 0.25em 1em;
  position: relative;
  z-index: 2;
  color: #ffffff;
}
.contentform {
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em, rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
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
.card.encabezadoColorform2 {
  margin: 0 auto;
  width: 95%;
  height: 40px;
  padding: 15px;
  border-radius: 60px;
  text-align: center;
}
.container {
  text-align: right;
  margin-top: 20px;
}
.btn1 {
  padding: 10px 20px;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  background-color: #ff9900;
}
</style>
