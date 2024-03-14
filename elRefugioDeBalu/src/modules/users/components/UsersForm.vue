<template>
  <div>
    <Encabezado color="#ffbc58" :imagenUrl="require('@/assets/imgs/UsersForm.png')" titulo="Registrar usuario" />
    <div class="mt-4 mx-5 fondo-solicitud">
      <b-container>
        <b-row>
          <b-col cols="12" sm="12">
            <div class="container-fluid">
              <div class="card encabezadoColorform">
                <h4 style="margin-left: 2rem">
                  <i class="material-icons ms-2" style="font-size: larger; vertical-align: middle">pets</i>
                  Datos personales
                </h4>
              </div>
              <form @submit.prevent="submitForm">
                <b-card-group deck>
                  <b-card class="contentform" img-alt="Card image">
                    <b-row>
                      <b-row>
                        <b-col cols="6">
                          <b-form-group id="name" label="Nombre (s):" label-for="nameInput">
                            <b-form-input id="nameInput" v-model.trim="formData.name"
                              @input="validateInput('name')"></b-form-input>
                            <b-form-invalid-feedback v-if="showErrors.name">{{ errorsMessages.name
                              }}</b-form-invalid-feedback>
                          </b-form-group>
                        </b-col>
                        <b-col cols="6">
                          <b-form-group id="lastName" label="Apellido Paterno:" label-for="lastNameInput">
                            <b-form-input id="lastNameInput" v-model.trim="formData.lastname" @input="validateInput('lastname')" ></b-form-input>
                            <b-form-invalid-feedback v-if="showErrors.lastname">{{ errorsMessages.lastname
                              }}</b-form-invalid-feedback>
                          </b-form-group>
                        </b-col>
                      </b-row>
                      <b-row>
                        <b-col cols="6">
                          <b-form-group id="surName" label="Apellido Materno:" label-for="surNameInput">
                            <b-form-input id="surNameInput" v-model.trim="formData.surname" @input="validateInput('surname')" ></b-form-input>
                            <b-form-invalid-feedback v-if="showErrors.surname">{{ errorsMessages.surname
                              }}</b-form-invalid-feedback>
                          </b-form-group>
                        </b-col>
                        <b-col cols="6">
                          <b-form-group id="phoneNumber" label="Número de Teléfono:" label-for="phoneNumberInput">
                            <b-form-input id="phoneNumberInput" v-model.trim="formData.phoneNumber"
                              type="number" @input="validateInput('phone')"></b-form-input>
                            <b-form-invalid-feedback v-if="showErrors.phoneNumber">{{ errorsMessages.phoneNumber
                              }}</b-form-invalid-feedback>
                          </b-form-group>
                        </b-col>
                      </b-row>
                      <b-row>
                        <b-col cols="6">
                          <b-form-group id="emai" label="Correo Electrónico:" label-for="email">
                            <b-form-input id="email" type="email" v-model.trim="formData.username" @input="validateInput('email')"></b-form-input>
                            <b-form-invalid-feedback v-if="showErrors.username">{{ errorsMessages.username
                              }}</b-form-invalid-feedback>
                          </b-form-group>
                        </b-col>
                        <b-col cols="6">
                          <b-form-group label="Selecciona un rol" label-for="rol">
                            <b-form-select id="rol" v-model="formData.role.id"
                            @focus="validateInput('role')"
                            :options="[
                              { value: null, text: 'Selecciona un rol' },
                              { value: 2, text: 'Administrador' },
                              { value: 3, text: 'Moderador' }]" class="form-select"></b-form-select>
                            <b-form-invalid-feedback v-if="showErrors.role">{{ errorsMessages.role
                              }}</b-form-invalid-feedback>
                          </b-form-group>
                        </b-col>
                      </b-row>
                    </b-row>
                    <div class="button-container">
                      <b-button @click="submitForm" :disabled="!(showErrors.name && showErrors.lastname && showErrors.surname && showErrors.phoneNumber && showErrors.username && showErrors.role)"
                      variant="outline-success">Registrar</b-button>
                      <b-button type="button" variant="outline-danger" @click="cancelForm">Regresar</b-button>
                    </div>
                  </b-card>
                </b-card-group>
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
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import instance from '../../../config/axios';
import { decrypt, encrypt } from '../../../kernel/hashFunctions'
import { isInvalidEmail, isInvalidName, isInvalidPhoneNumber } from "../../../kernel/validations";

export default {
  name: "FormRequest",
  components: {
    Encabezado,
  },
  data() {
    return {
      formData: {
        name: "",
        lastname: "",
        surname: "",
        phoneNumber: "",
        username: "",
        role: {
          id: null
        }
      },
      encryptedForm: {
        name: "",
        lastname: "",
        surname: "",
        phoneNumber: "",
        username: "",
        roleString: ""
      },
      showErrors: {
        name: false,
        lastname: false,
        surname: false,
        phoneNumber: false,
        username: false,
        role: false
      },
      errorsMessages: {
        name: "",
        lastname: "",
        surname: "",
        phoneNumber: "",
        username: "",
        role: ""
      }
    };
  },
  methods: {
    async sendRequest() {
      this.encryptedForm.name = await encrypt(this.formData.name)
      this.encryptedForm.lastname = await encrypt(this.formData.lastname)
      this.encryptedForm.surname = await encrypt(this.formData.surname)
      this.encryptedForm.phoneNumber = await encrypt(this.formData.phoneNumber)
      this.encryptedForm.username = await encrypt(this.formData.username)
      this.encryptedForm.roleString = await encrypt(this.formData.role.id)

      const token = await decrypt(localStorage.getItem('token'))
      try {
        const response = await instance.post('/person/', this.encryptedForm, {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        })
        const password = await decrypt(response.data.data)
        Swal.fire({
          icon: 'success',
          title: 'Usuario registrado',
          text: 'El usuario ha sido registrado exitosamente, a continuación se mostrará la contraseña generada para el usuario'
        }).then(() => {
          Swal.fire({
            icon: 'info',
            title: 'Contraseña generada',
            text: `La contraseña generada para el usuario es: ${password}`,
            timer: 10000,
            timerProgressBar: true,
          }).then(() => {
            this.$router.push('/usersList')
          })
        });
      } catch (error) {
        const message = error.response.data.message
        switch (message) {
          case 'DUPLICATE_RECORD':
            Swal.fire({
              icon: 'error',
              title: 'Error',
              text: 'Este usuario ya ha sido registrado anteriormente'
            });
            break;
          default:
            Swal.fire({
              icon: 'error',
              title: 'Error',
              text: 'Ha ocurrido un error en la petición, por favor verifica los campos e intenta de nuevo'
            });
            break;
        }

      }
    },
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
          Swal.fire({
            title: "Espera un momento...",
            text: "Estamos enviando tu solicitud",
            imageUrl: gatoWalkingGif,
            imageWidth: 160,
            imageHeight: 160,
            showConfirmButton: false,
          });
          this.sendRequest()

        } else {
          Swal.fire({
            title: "Cancelado",
            text: "La solicitud ha sido cancelada",
            icon: "info"
          })
        }
      });
    },
    cancelForm() {
      Swal.fire({
        title: "¿Estás seguro?",
        text: "¿Estás seguro de regresar a la pantalla anterior?",
        icon: "info",
        showCancelButton: true,
        confirmButtonText: "Sí, salir",
        cancelButtonText: "No, continuar",
      }).then((result) => {
        if (result.isConfirmed) {
            this.$router.push('/usersList')
        }
      });
    },
    validateInput(validateInput) {
      let input;
      switch (validateInput) {
        case 'name':
          input = document.getElementById('nameInput');
          if(isInvalidName(this.formData.name)){
            input.classList.add('is-invalid');
            this.showErrors.name = true;
            this.errorsMessages.name = 'El nombre no puede contener caracteres especiales o números.';
          } else {
            input.classList.remove('is-invalid');
            input.classList.add('is-valid');
            this.showErrors.name = false;
            this.errorsMessages.name = '';
          }
          break;
        case 'lastname':
          input = document.getElementById('lastNameInput');
          if(isInvalidName(this.formData.lastname)){
            input.classList.add('is-invalid');
            this.showErrors.lastname = true;
            this.errorsMessages.lastname = 'El apellido paterno no puede contener caracteres especiales o números.';
          }else{
            input.classList.remove('is-invalid');
            input.classList.add('is-valid');
            this.showErrors.lastname = false;
            this.errorsMessages.lastname = '';
          }
          break;
        case 'surname':
          input = document.getElementById('surNameInput');
          if(isInvalidName(this.formData.surname)){
            input.classList.add('is-invalid');
            this.showErrors.surname = true;
            this.errorsMessages.surname = 'El apellido materno no puede contener caracteres especiales o números.';
          }else{
            input.classList.remove('is-invalid');
            input.classList.add('is-valid');
            this.showErrors.surname = false;
            this.errorsMessages.surname = '';
          }
          break;
          case 'phone':
            input = document.getElementById('phoneNumberInput');
            if(isInvalidPhoneNumber(this.formData.phoneNumber)){
              input.classList.add('is-invalid');
              this.showErrors.phoneNumber = true;
              this.errorsMessages.phoneNumber = 'El número de teléfono no puede contener letras o caracteres especiales, debe ser 10 dígitos.';
            }else{
              input.classList.remove('is-invalid');
              input.classList.add('is-valid');
              this.showErrors.phoneNumber = false;
              this.errorsMessages.phoneNumber = '';
            }
            break;
          case 'email':
            input = document.getElementById('email');
            if(isInvalidEmail(this.formData.username)){
              input.classList.add('is-invalid');
              this.showErrors.username = true;
              this.errorsMessages.username = 'El correo electrónico no es válido.';
            }else{
              input.classList.remove('is-invalid');
              input.classList.add('is-valid');
              this.showErrors.username = false;
              this.errorsMessages.username = '';
            }
            break;
          case 'role':
            input = document.getElementById('rol');
            if(this.formData.role.id === null){
              input.classList.add('is-invalid');
              this.showErrors.role = true;
              this.errorsMessages.role = 'Debes seleccionar un rol para el usuario.';
            }else{
              input.classList.remove('is-invalid');
              input.classList.add('is-valid');
              this.showErrors.role = false;
              this.errorsMessages.role = '';
            }
            break;
      }

    }
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
