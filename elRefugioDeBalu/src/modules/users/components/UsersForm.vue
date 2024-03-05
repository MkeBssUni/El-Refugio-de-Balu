<template>
  <div>
    <Encabezado
      color="#ffbc58"
      :imagenUrl="require('@/assets/imgs/UsersForm.png')"
      titulo="Registrar usuario"
    />
    <div class="mt-4 mx-5 fondo-solicitud">
      <b-container>
        <b-row>
          <b-col cols="12" sm="12">
            <div class="container-fluid">
              <div class="card encabezadoColorform">
                <h4 style="margin-left: 2rem">
                  <i
                    class="material-icons ms-2"
                    style="font-size: larger; vertical-align: middle"
                    >pets</i
                  >
                  Datos personales
                </h4>
              </div>
              <form @submit.prevent="submitForm">
                <b-card-group deck>
                  <b-card class="contentform" img-alt="Card image">
                    <b-row>
                      <b-row>
                        <b-col cols="6">
                          <b-form-group
                            id="name"
                            label="Nombre (s):"
                            label-for="nameInput"
                          >
                            <b-form-input
                              id="nameInput"
                              v-model="formData.name"
                              required
                            ></b-form-input>
                          </b-form-group>
                        </b-col>
                        <b-col cols="6">
                          <b-form-group
                            id="lastName"
                            label="Apellido Paterno:"
                            label-for="lastNameInput"
                          >
                            <b-form-input
                              id="lastNameInput"
                              v-model="formData.lastName"
                              required
                            ></b-form-input>
                          </b-form-group>
                        </b-col>
                      </b-row>
                      <b-row>
                        <b-col cols="6">
                          <b-form-group
                            id="surName"
                            label="Apellido Materno:"
                            label-for="surNameInput"
                          >
                            <b-form-input
                              id="surNameInput"
                              v-model="formData.surName"
                            ></b-form-input>
                          </b-form-group>
                        </b-col>
                        <b-col cols="6">
                          <b-form-group
                            id="phoneNumber"
                            label="Número de Teléfono:"
                            label-for="phoneNumberInput"
                          >
                            <b-form-input
                              id="phoneNumberInput"
                              v-model="formData.phoneNumber"
                              type="number"
                            ></b-form-input>
                          </b-form-group>
                        </b-col>
                      </b-row>
                      <b-row>
                        <b-col cols="6">
                          <b-form-group
                            id="secondaryPhoneNumber"
                            label="Número de Teléfono Secundario:"
                            label-for="secondaryPhoneNumberInput"
                          >
                            <b-form-input
                              id="secondaryPhoneNumberInput"
                              v-model="formData.secondaryPhoneNumber"
                              type="number"
                            ></b-form-input>
                          </b-form-group>
                        </b-col>
                        <b-col cols="6">
                          <b-form-group
                            id="secondaryEmail"
                            label="Correo Electrónico:"
                            label-for="secondaryEmailInput"
                          >
                            <b-form-input
                              id="secondaryEmailInput"
                              type="email"
                              v-model="formData.secondaryEmail"
                            ></b-form-input>
                          </b-form-group>
                        </b-col>
                      </b-row>
                      <b-row>
                        <b-col cols="12">
                          <b-form-group
                            id="profilePicture"
                            label="Foto de Perfil"
                            label-for="profilePictureInput"
                          >
                            <b-form-file
                              id="profilePictureInput"
                              v-model="formData.profilePicture"
                              accept="image/*"
                              @change="handleFileChange"
                              hide-no-file-selected
                            ></b-form-file>
                            <b-img
                              v-if="formData.profilePicture"
                              :src="formData.profilePictureUrl"
                              fluid
                              thumbnail
                            ></b-img>
                          </b-form-group>
                        </b-col>
                      </b-row>
                    </b-row>
                  </b-card>
                </b-card-group>
                <div class="button-container">
                  <b-button
                    type="submit"
                    variant="outline-success"
                    >Registrar</b-button
                  >
                  <b-button
                    type="button"
                    variant="outline-danger"
                    @click="cancelForm"
                    >Cancelar</b-button
                  >
                </div>
              </form>
            </div>
          </b-col>
        </b-row>
      </b-container>
    </div>
  </div>
</template>

<script>
import Encabezado from "../../../views/components/Encabezado.vue";
import Swal from 'sweetalert2';

export default {
  name: "FormRequest",
  components: {
    Encabezado,
  },
  data() {
    return {
      formData: {
        name: "",
        lastName: "",
        surName: "",
        phoneNumber: "",
        secondaryPhoneNumber: "",
        secondaryEmail: "",
        profilePicture: "",
      },
    };
  },
  methods: {
    submitForm() {
      Swal.fire({
        title: "¿Estás seguro?",
        text: "¿Estás seguro de registrar este usuario?",
        icon: "info",
        showCancelButton: true,
        confirmButtonText: "Sí, registrar",
        cancelButtonText: "Cancelar",
      }).then((result) => {
        if (result.isConfirmed) {
          console.log("Registrar usuario")
          console.log("Formulario enviado:", this.formData);
        } else {
          console.log("Cancelar registro de usuario")
        }
      });
    },
    handleFileChange(event) {
      const file = event.target.files[0];
      if (file) {
        this.formData.profilePictureUrl = URL.createObjectURL(file);
      }
    },
    cancelForm() {
      console.log("Cancelando formulario");
    },
  },
};
</script>

<style>
.encabezadoColorform {
  width: 50%;
  background-color: #e84e0f;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.contentform {
  background-color: #ee7240;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.button-container {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}
.button-container button:first-child {
  margin-right: 10px; 
}
</style>
