<template>
<b-col cols="12" class="px-2 px-sm-4 px-xl-5 mt-4">
        <b-row>
            <b-col cols="10" md="8" lg="6">
                <b-card bg-variant="card-header-blue" class="py-2 form-card-shadow relative-position form-card-title"
                    no-body>
                    <div class="d-flex align-items-center ms-3 ms-md-4">
                        <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
                        <h4 class="mb-0 mt-1">Domicilio Actual</h4>
                    </div>
                </b-card>
            </b-col>
        </b-row>
        <b-row>
            <b-col cols="12">
                <b-card bg-variant="card-content-blue" class="form-card-shadow form-card-content">
                    <b-card-body>
                        
            <b-row>
              <b-col cols="12" class="px-2 px-sm-4 px-xl-5 mb-sm-5">
                      <b-row>
                        <b-col cols="12" md="4">
                          <b-form-group label="País" label-align="left">
                            <b-input v-model="SaveAddressDto.country"></b-input>
                          </b-form-group>
                        </b-col>
                        <b-col cols="12" md="4">
                          <b-form-group label="Calle" label-align="left">
                            <b-input v-model="SaveAddressDto.street"></b-input>
                          </b-form-group>
                        </b-col>
                        <b-col cols="12" md="4">
                          <b-form-group label="Colonia" label-align="left">
                            <b-input v-model="SaveAddressDto.colony"></b-input>
                          </b-form-group>
                        </b-col>
                      </b-row>
                      <b-row>
                        <b-col cols="12" md="4">
                          <b-form-group label="Ciudad" label-align="left">
                            <b-input v-model="SaveAddressDto.city"></b-input>
                          </b-form-group>
                        </b-col>
                        <b-col cols="12" md="4">
                          <b-form-group label="Estado" label-align="left">
                            <b-input v-model="SaveAddressDto.state"></b-input>
                          </b-form-group>
                        </b-col>
                        <b-col cols="12" md="4">
                          <b-form-group
                            label="Código Postal"
                            label-align="left"
                            :state="postalCodeValidation"
                          >
                            <b-input
                              v-model="SaveAddressDto.postalCode"
                            ></b-input>
                            <b-form-invalid-feedback
                              :state="postalCodeValidation"
                            >
                              Codigo postal invalido
                            </b-form-invalid-feedback>
                          </b-form-group>
                        </b-col>
                      </b-row>
                      <b-row>
                        <b-col cols="12">
                          <b-form-group
                            label="Referencia de dirección"
                            label-align="left"
                          >
                            <b-input
                              v-model="SaveAddressDto.addressReference"
                              type="textarea"
                            ></b-input>
                          </b-form-group>
                        </b-col>
                      </b-row>
                      <b-row>
                        <b-col cols="12" md="4">
                          <b-form-group
                            label="Número exterior"
                            label-align="left"
                          >
                            <b-input
                              v-model="SaveAddressDto.exteriorNumber"
                            ></b-input>
                            <b-form-invalid-feedback>
                              Ingresa solo números
                            </b-form-invalid-feedback>
                          </b-form-group>
                        </b-col>
                        <b-col cols="12" md="4">
                          <b-form-group
                            label="Número interior"
                            label-align="left"
                          >
                            <b-input
                              v-model="SaveAddressDto.interiorNumber"
                            ></b-input>
                          </b-form-group>
                        </b-col>
                      </b-row>
                      <div class="d-flex justify-content-end mt-3">
                        <b-button
                          @click="submitForm()"
                          variant="outline-light"
                          class="d-flex align-items-center"
                        >
                          {{ editing ? "Guardar" : "Modificar"
                          }}<span class="me-2">
                            <i
                              class="material-icons"
                              style="font-size: 1.5rem; color: white"
                              >home</i
                            >
                          </span>
                        </b-button>
                      </div>
                    </b-col>
            </b-row>
                    </b-card-body>
                </b-card>
                </b-col>
        </b-row>
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

  methods: {
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
</style>

