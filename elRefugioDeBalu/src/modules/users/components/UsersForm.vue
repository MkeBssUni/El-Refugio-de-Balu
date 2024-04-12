<template>
  <b-container fluid>
    <b-row>
      <Encabezado color="#ffbc58" :imagenUrl="require('@/assets/imgs/UsersForm.png')" titulo="Registrar usuario" />
    </b-row>
    <b-row class="px-2 mt-2 mb-5">
      <b-col cols="12" class="px-2 px-sm-4 px-xl-5 mt-4">
        <b-row>
          <b-col cols="10" md="8" lg="6">
            <b-card bg-variant="secondary-blue" class="py-2 form-card-shadow relative-position form-card-title" no-body>
              <div class="d-flex align-items-center ms-3 ms-md-4">
                <b-icon icon="people-fill" class="me-2" font-scale="1.5"></b-icon>
                <h4 class="mb-0 mt-1">Registro de usuario</h4>
              </div>
            </b-card>
          </b-col>
        </b-row>
        <b-row>
          <b-col cols="12">
            <b-card bg-variant="gray" class="form-card-shadow form-card-content">
              <b-card-body>
                <b-row class="mt-3">
                  <b-col cols="12" md="4">
                    <b-form-group label-for="name">
                      <label slot="label">
                        Nombre(s): <span class="required-asterisk">*</span>
                      </label>
                      <b-form-input id="name" v-model.trim="formData.name"
                        @input="validateInput('name')"></b-form-input>
                      <b-form-invalid-feedback v-if="showErrors.name">{{ errorsMessages.name
                        }}</b-form-invalid-feedback>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" md="4" class="mt-3 mt-md-0">
                    <b-form-group id="lastName" label-for="lastname">
                      <label slot="label">
                        Apellido paterno: <span class="required-asterisk">*</span>
                      </label>
                      <b-form-input id="lastname" v-model.trim="formData.lastname"
                        @input="validateInput('lastname')"></b-form-input>
                      <b-form-invalid-feedback v-if="showErrors.lastname">{{ errorsMessages.lastname
                        }}</b-form-invalid-feedback>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" md="4" class="mt-3 mt-md-0">
                    <b-form-group id="surName" label-for="surname">
                      <label slot="label">
                        Apellido materno: <span class="required-asterisk">*</span>
                      </label>
                      <b-form-input id="surname" v-model.trim="formData.surname"
                        @input="validateInput('surname')"></b-form-input>
                      <b-form-invalid-feedback v-if="showErrors.surname">{{ errorsMessages.surname
                        }}</b-form-invalid-feedback>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" sm="6" md="4" class="mt-3">
                    <b-form-group id="phoneNumber" label-for="phone">
                      <label slot="label">
                        Número de teléfono: <span class="required-asterisk">*</span>
                      </label>
                      <b-form-input id="phone" v-model.trim="formData.phoneNumber" type="tel"
                        @input="validateInput('phone')"></b-form-input>
                      <b-form-invalid-feedback v-if="showErrors.phoneNumber">{{ errorsMessages.phoneNumber
                        }}</b-form-invalid-feedback>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" sm="6" md="4" class="mt-3">
                    <b-form-group id="emai" label-for="email">
                      <label slot="label">
                        Correo electrónico: <span class="required-asterisk">*</span>
                      </label>
                      <b-form-input id="email" type="email" v-model.trim="formData.username"
                        @input="validateInput('email')"></b-form-input>
                      <b-form-invalid-feedback v-if="showErrors.username">{{ errorsMessages.username
                        }}</b-form-invalid-feedback>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" md="4" class="mt-3">
                    <b-form-group label-for="role">
                      <label slot="label">
                        Selecciona un rol: <span class="required-asterisk">*</span>
                      </label>
                      <b-form-select id="role" v-model="formData.role.id" @change="validateInput('role')" :options="[
                        { value: null, text: 'Selecciona un rol' },
                        { value: 2, text: 'Administrador' },
                        { value: 3, text: 'Moderador' }]" class="form-select"></b-form-select>
                      <b-form-invalid-feedback v-if="showErrors.role">{{ errorsMessages.role
                        }}</b-form-invalid-feedback>
                    </b-form-group>
                  </b-col>
                </b-row>
                <b-row class="mt-4 px-5 px-sm-0 d-flex justify-content-end">
                  <b-col cols="12" sm="6" md="5" lg="4" xl="3">
                    <b-button variant="outline-dark-secondary-blue" @click="showConfirmation()"
                      class="d-flex align-items-center justify-content-between w-100">
                      <span class="me-2">Registrar</span>
                      <b-icon icon="plus-circle" font-scale="1.3"></b-icon>
                    </b-button>
                  </b-col>
                  <b-col cols="12" sm="6" md="5" lg="4" xl="3" class="mt-3 mt-sm-0">
                    <b-button variant="outline-danger" @click="goBack()"
                      class="d-flex align-items-center justify-content-between w-100">
                      <span class="me-2">Cancelar</span>
                      <b-icon icon="x-circle" font-scale="1.3"></b-icon>
                    </b-button>
                  </b-col>
                </b-row>
              </b-card-body>
            </b-card>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
  </b-container>
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
      isValid: false,
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
    goBack() {
      this.$router.go(-1);
    },
    async registerUser() {
      try {
        Swal.fire({
          title: 'Cargando...',
          text: 'Estamos solicitando los cambios en la publicación de la mascota, espera un momento',
          imageUrl: gatoWalkingGif,
          imageWidth: 160,
          imageHeight: 160,
          showConfirmButton: false
        })

        this.encryptedForm.name = await encrypt(this.formData.name)
        this.encryptedForm.lastname = await encrypt(this.formData.lastname)
        this.encryptedForm.surname = await encrypt(this.formData.surname)
        this.encryptedForm.phoneNumber = await encrypt(this.formData.phoneNumber)
        this.encryptedForm.username = await encrypt(this.formData.username)
        this.encryptedForm.roleString = await encrypt(this.formData.role.id)

        const response = await instance.post('/person/', this.encryptedForm)
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
        if (error.response.data.message === 'DUPLICATE_RECORD') {
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Este usuario ya ha sido registrado anteriormente'
          });
        } else {
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Ha ocurrido un error en la petición, por favor verifica los campos e intenta de nuevo'
          });
        }
      }
    },
    validateForm() {
      this.validateInput('name');
      this.validateInput('lastname');
      this.validateInput('surname');
      this.validateInput('phone');
      this.validateInput('email');
      this.validateInput('role');
      if (!this.showErrors.name && !this.showErrors.lastname && !this.showErrors.surname && !this.showErrors.phoneNumber && !this.showErrors.username && !this.showErrors.role) this.isValid = true;
      else this.isValid = false;
    },
    showConfirmation() {
      this.validateForm();
      if (this.isValid) {
        Swal.fire({
          title: '¿Estás seguro?',
          text: 'Estás por registrar un nuevo usuario, ¿deseas continuar?',
          icon: 'question',
          iconColor: '#118A95',
          showCancelButton: true,
          confirmButtonColor: '#118A95',
          cancelButtonColor: '#A93D3D',
          confirmButtonText: 'Continuar',
          cancelButtonText: 'Cancelar',
        }).then((result) => {
          if (result.isConfirmed) {
            this.registerUser();
          }
        })
      }
    },
    validateInput(validateInput) {
      const input = document.getElementById(validateInput);
      switch (validateInput) {
        case 'name':
          if (!this.formData.name) {
            input.classList.add('is-invalid');
            this.showErrors.name = true;
            this.errorsMessages.name = 'Campo obligatorio';
          } else if (this.formData.name.length < 3 || this.formData.name.length > 20) {
            input.classList.add('is-invalid');
            this.showErrors.name = true;
            this.errorsMessages.name = 'El nombre debe tener entre 3 y 20 caracteres';
          } else if (isInvalidName(this.formData.name)) {
            input.classList.add('is-invalid');
            this.showErrors.name = true;
            this.errorsMessages.name = 'El nombre no puede contener números ni caracteres especiales';
          } else {
            input.classList.remove('is-invalid');
            input.classList.add('is-valid');
            this.showErrors.name = false;
            this.errorsMessages.name = '';
          }
          break;
        case 'lastname':
          if (!this.formData.lastname) {
            input.classList.add('is-invalid');
            this.showErrors.lastname = true;
            this.errorsMessages.lastname = 'Campo obligatorio';
          } else if (this.formData.lastname.length < 3 || this.formData.lastname.length > 20) {
            input.classList.add('is-invalid');
            this.showErrors.lastname = true;
            this.errorsMessages.lastname = 'El apellido debe tener entre 3 y 20 caracteres';
          } else if (isInvalidName(this.formData.lastname)) {
            input.classList.add('is-invalid');
            this.showErrors.lastname = true;
            this.errorsMessages.lastname = 'El apellido paterno no puede contener números ni caracteres especiales';
          } else {
            input.classList.remove('is-invalid');
            input.classList.add('is-valid');
            this.showErrors.lastname = false;
            this.errorsMessages.lastname = '';
          }
          break;
        case 'surname':
          if (!this.formData.surname) {
            input.classList.add('is-invalid');
            this.showErrors.surname = true;
            this.errorsMessages.surname = 'Campo obligatorio';
          } else if (this.formData.surname.length < 3 || this.formData.surname.length > 20) {
            input.classList.add('is-invalid');
            this.showErrors.surname = true;
            this.errorsMessages.surname = 'El apellido debe tener entre 3 y 20 caracteres';
          } else if (isInvalidName(this.formData.surname)) {
            input.classList.add('is-invalid');
            this.showErrors.surname = true;
            this.errorsMessages.surname = 'El apellido materno no puede contener números ni caracteres especiales';
          } else {
            input.classList.remove('is-invalid');
            input.classList.add('is-valid');
            this.showErrors.surname = false;
            this.errorsMessages.surname = '';
          }
          break;
        case 'phone':
          if (!this.formData.phoneNumber) {
            input.classList.add('is-invalid');
            this.showErrors.phoneNumber = true;
            this.errorsMessages.phoneNumber = 'Campo obligatorio';
          } else if (isInvalidPhoneNumber(this.formData.phoneNumber)) {
            input.classList.add('is-invalid');
            this.showErrors.phoneNumber = true;
            this.errorsMessages.phoneNumber = 'El número de teléfono no es válido';
          } else {
            input.classList.remove('is-invalid');
            input.classList.add('is-valid');
            this.showErrors.phoneNumber = false;
            this.errorsMessages.phoneNumber = '';
          }
          break;
        case 'email':
          if (!this.formData.username) {
            input.classList.add('is-invalid');
            this.showErrors.username = true;
            this.errorsMessages.username = 'Campo obligatorio';
          } else if (isInvalidEmail(this.formData.username)) {
            input.classList.add('is-invalid');
            this.showErrors.username = true;
            this.errorsMessages.username = 'El correo electrónico no es válido';
          } else {
            input.classList.remove('is-invalid');
            input.classList.add('is-valid');
            this.showErrors.username = false;
            this.errorsMessages.username = '';
          }
          break;
        case 'role':
          if (this.formData.role.id === null) {
            input.classList.add('is-invalid');
            this.showErrors.role = true;
            this.errorsMessages.role = 'Debes seleccionar un rol para el usuario';
          } else {
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
