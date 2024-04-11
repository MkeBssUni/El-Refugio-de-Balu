<template>
  <b-container
    fluid
    class="d-flex justify-content-center align-items-center h-100"
  >
    <div class="container-fluid mx-auto" style="width: 100%; margin: 50px 0">
      <div class="card encabezadoColorform text-center">
        <h4 style="margin-left: 2rem; color: white">Perfil</h4>
      </div>
      <b-card-group
        style="
          box-shadow: rgba(255, 255, 255, 0.1) 0px 1px 1px 0px inset,
            rgba(50, 50, 93, 0.25) 0px 50px 100px -20px,
            rgba(0, 0, 0, 0.3) 0px 30px 60px -30px;
        "
      >
        <b-card class="contentform" img-alt="Card image">
          <b-container>
            <div class="row">
              <b-col cols="2" md="2" lg="2" style="margin-right: 95px">
                <b-img
                  alt="profilePic"
                  :src="profile"
                  class="mb-4"
                  style="max-width: 300px; margin-top: 60px"
                ></b-img>
              </b-col>
              <b-col cols="9" >
                <b-row class="px-2 my-2">
                  <PersonalInformationCar ref="personalInformationCar" />
                </b-row>
              </b-col>
              <b-col cols="12" >
                <b-row class="px-2 my-2">
                  <CurrentAddressCard ref="currentAddressCard" />
                </b-row>
              </b-col>
              <b-col cols="12" >
                <b-row class="px-2 my-2">
                  <HomeSpecification ref="homeSpecification" />
                </b-row>
              </b-col>
            </div>
          </b-container>

          <div class="row">
            <b-col cols="12" class="px-2 px-sm-4 px-xl-5 mb-sm-5">
              <b-row> </b-row>
            </b-col>
            <div class="d-flex justify-content-end mt-3">
              <b-button v-b-toggle.sidebar-right class="btn1"
                ><span class="me-2">Cambiar contraseña</span>
                <b-icon icon="lock-fill" font-scale="1.3"></b-icon
              ></b-button>
            </div>
          </div>
        </b-card>
      </b-card-group>

      <!-- Sidebar -->
      <b-sidebar id="sidebar-right" right shadow no-header>
        <template #default="{ hide }">
          <div class="px-3 py-2">
            <div
              class="d-flex align-items-center mb-3 text-dark-secondary-blue"
            >
              <b-button class="closeButton me-3" @click="hide">
                <b-icon icon="x" font-scale="1.8" class="text-dark"></b-icon>
              </b-button>
              <span class="text-dark-secondary-blue" style="font-size: 1.3rem"
                >Cambio de contraseña</span
              >
            </div>
            <b-form-group label="Cambio de contraseña actual">
              <b-input-group>
                <b-input
                  v-model="currentPassword"
                  :type="showPasswordCurrent ? 'text' : 'password'"
                  class="w-75"
                ></b-input>
                <b-button
                  @click="togglePasswordVisibility('currentPassword')"
                  variant="outline-secondary"
                  class="btn-eye rounded-right"
                >
                  <b-icon
                    v-if="!showPasswordCurrent"
                    icon="eye"
                    class="eye-icon"
                  ></b-icon>
                  <b-icon v-else icon="eye-slash" class="eye-icon"></b-icon>
                </b-button>
              </b-input-group>
            </b-form-group>

            <b-form-group label="Nueva contraseña" :state="passwordValidation">
              <b-input-group>
                <b-input
                  v-model="newPassword"
                  :type="showPasswordNew ? 'text' : 'password'"
                  class="w-75"
                ></b-input>
                <b-button
                  @click="togglePasswordVisibility('newPassword')"
                  variant="outline-secondary"
                  class="btn-eye rounded-right"
                >
                  <b-icon
                    v-if="!showPasswordNew"
                    icon="eye"
                    class="eye-icon"
                  ></b-icon>
                  <b-icon v-else icon="eye-slash" class="eye-icon"></b-icon>
                </b-button>
              </b-input-group>
              <b-form-invalid-feedback :state="passwordValidation">{{
                passwordValidationMessage
              }}</b-form-invalid-feedback>
            </b-form-group>

            <b-form-group label="Confirmar contraseña">
              <b-input-group>
                <b-input
                  v-model="confirmPassword"
                  :type="showPasswordConfirm ? 'text' : 'password'"
                  class="w-75"
                ></b-input>
                <b-button
                  @click="togglePasswordVisibility('confirmPassword')"
                  variant="outline-secondary"
                  class="btn-eye rounded-right"
                >
                  <b-icon
                    v-if="!showPasswordConfirm"
                    icon="eye"
                    class="eye-icon"
                  ></b-icon>
                  <b-icon v-else icon="eye-slash" class="eye-icon"></b-icon>
                </b-button>
              </b-input-group>
              <b-form-invalid-feedback :state="confirmPasswordValidation">{{
                confirmPasswordValidationMessage
              }}</b-form-invalid-feedback>
            </b-form-group>
            <div class="mt-3 text-right">
              <b-button
                @click="changePassword"
                variant="outline-success"
                class="mr-4"
                >Modificar</b-button
              >
              <div class="mt-5" style="position: absolute; bottom: 0; right: 0">
                <img
                  src="../../../assets//imgs/gatocontra.png"
                  width="240"
                  alt="gato"
                  fluid
                  style="margin-left: 10px"
                />
              </div>
            </div>
          </div>
        </template>
      </b-sidebar>
    </div>
  </b-container>
</template>

<script>
import swal from "sweetalert2";
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import instance from "../../../config/axios";
import { decrypt } from "../../../kernel/hashFunctions";
import profile from "../../../assets/imgs/profile.jpg";
import CurrentAddressCard from "../views/CurrentAddressCard.vue";
import PersonalInformationCar from "../views/PersonalInformationCard.vue";
import HomeSpecification from "../views/HomeSpecification.vue";
export default {
  name: "FormStyle",
  data() {
    return {
      profile: profile,
      showPasswordCurrent: false,
      showPasswordNew: false,
      showPasswordConfirm: false,
      userId: null,
      name: "",
      lastName: "",
      surName: "",
      phoneNumber: "",
      secondaryPhoneNumber: "",
      secondaryEmail: "",
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
      information: {},
      SaveAddressDto: {
        country: "",
        street: "",
        colony: "",
        city: "",
        state: "",
        postalCode: "",
        addressReference: "",
        exteriorNumber: "",
        interiorNumber: "",
        userId: localStorage.getItem("userId"),
      },
      postalCodeValidation: null,
      postalCodeValidationMessage: "",
    };
  },
  mounted() {
    this.getDetails();
    this.getAddressDetails();
  },
  computed: {
    domicilioCompleto() {
      return (
        this.country &&
        this.street &&
        this.colony &&
        this.city &&
        this.state &&
        this.postalCode &&
        this.addressReference &&
        this.exteriorNumber
      );
    },
  },
  components: {
    CurrentAddressCard,
    HomeSpecification,
    PersonalInformationCar,
  },
  methods: {
    togglePasswordVisibility(field) {
      switch (field) {
        case "currentPassword":
          this.showPasswordCurrent = !this.showPasswordCurrent;
          break;
        case "newPassword":
          this.showPasswordNew = !this.showPasswordNew;
          break;
        case "confirmPassword":
          this.showPasswordConfirm = !this.showPasswordConfirm;
          break;
        default:
          break;
      }
    },
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
    async getAddressDetails() {
      this.loading = true;
      try {
        const userId = localStorage.getItem("userId");
        const response = await axios.get(`/api/user/${userId}`);
        const responseData = response.data;

        if (!responseData.error) {
          const addressData = responseData.data;
          this.address = {
            country: addressData.country,
            street: addressData.street,
            colony: addressData.colony,
            city: addressData.city,
            state: addressData.state,
            postalCode: addressData.postalCode,
            addressReference: addressData.addressReference,
            exteriorNumber: addressData.exteriorNumber,
            interiorNumber: addressData.interiorNumber,
          };
        } else {
          // Manejar errores si la respuesta tiene un error
          console.error(
            "Error al obtener la dirección del usuario:",
            responseData.message
          );
          swal.fire({
            icon: "error",
            title: "Error",
            text: "No se pudo obtener la dirección del usuario",
          });
        }
      } catch (error) {
        // Manejar errores de la solicitud HTTP
        console.error("Error al obtener la dirección del usuario:", error);
        swal.fire({
          icon: "error",
          title: "Error",
          text: "No se pudo obtener la dirección del usuario",
        });
      } finally {
        this.loading = false;
      }
    },
    toggleSidebar() {
      this.$bvModal.show("sidebar-1");
    },
    async submitNewAddress() {
      try {
        const response = await instance.post("/address/", this.SaveAddressDto);
        swal.fire({
          title: "Domicilio agregado con éxito",
          icon: "success",
        });
      } catch (error) {
        swal.fire({
          title: "Error al agregar domicilio",
          text: error.message,
          icon: "error",
        });
      }
    },
    submitForm() {
      swal
        .fire({
          title: "¿Estás seguro de realizar la accion",
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
              text: "Estamos realizando tu registro",
              imageUrl: gatoWalkingGif,
              timer: 2000,
              timerProgressBar: true,
              imageWidth: 160, // Ancho de la imagen
              imageHeight: 160, // Altura de la imagen
              showConfirmButton: false,
            });
            this.submitNewAddress();
          }
        });
    },
    changePassword() {},
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
    newPassword(newVal) {
      this.passwordValidation =
        /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,}$/.test(newVal);
      if (!this.passwordValidation) {
        let missingRequirements = [];
        if (!/(?=.*\d)/.test(newVal))
          missingRequirements.push("debe contener al menos un número (0-9)");
        if (!/(?=.*[a-z])/.test(newVal))
          missingRequirements.push(
            "debe contener al menos una letra minúscula (a-z)"
          );
        if (!/(?=.*[A-Z])/.test(newVal))
          missingRequirements.push(
            "debe contener al menos una letra mayúscula (A-Z)"
          );
        if (/\s/.test(newVal))
          missingRequirements.push("no debe contener espacios en blanco");
        if (newVal.length < 8)
          missingRequirements.push(
            "debe tener una longitud mínima de 8 caracteres"
          );
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

.btn1 {
  padding: 10px 20px;
  font-size: 20px;
  border-radius: 10px;
  background-color: #4db8c0;
  color: #fff;
  border: none;
}
.eye-icon {
  font-size: 1rem;
}

.btn-eye {
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
  background-color: #ffffff;
  border-color: rgb(207, 203, 203);
  color: black;
}
</style>
