<template>
    <b-container fluid>
        <b-row class="px-5 pb-5 pt-4">
            <b-col cols="4">
                <b-card bg-variant="orange" class="py-2 form-card-shadow relative-position form-card-title" no-body>
                    <div class="d-flex align-items-center ms-3 ms-md-4">
                        <b-icon icon="person" variant="dark" font-scale="1.5" class="me-2"></b-icon>
                        <h4 class="mb-0 mt-1">Información personal</h4>
                    </div>
                </b-card>
                <b-card bg-variant="gray" class="p-4 form-card-shadow form-card-content">
                    <b-row>
                        <b-col cols="12">
                            <b-form-group>
                                <label for="name" class="mb-2">Nombre:</label>
                                <b-form-input id="name" v-model.trim="user.name" :readonly="viewPersonalInfo"
                                    @input="validateField('name')"></b-form-input>
                                <b-form-invalid-feedback v-show="showErrors.name">
                                    {{ errorMessages.name }}
                                </b-form-invalid-feedback>
                            </b-form-group>
                        </b-col>
                        <b-col cols="12" class="mt-3">
                            <b-form-group>
                                <label for="lastname" class="mb-2">Apellido paterno:</label>
                                <b-form-input id="lastname" v-model.trim="user.lastname" :readonly="viewPersonalInfo"
                                    @input="validateField('lastname')"></b-form-input>
                                <b-form-invalid-feedback v-show="showErrors.lastname">
                                    {{ errorMessages.lastname }}
                                </b-form-invalid-feedback>
                            </b-form-group>
                        </b-col>
                        <b-col cols="12" class="mt-3">
                            <b-form-group>
                                <label for="surname" class="mb-2">Apellido materno:</label>
                                <b-form-input id="surname" v-model.trim="user.surname" :readonly="viewPersonalInfo"
                                    @input="validateField('surname')"></b-form-input>
                                <b-form-invalid-feedback v-show="showErrors.surname">
                                    {{ errorMessages.surname }}
                                </b-form-invalid-feedback>
                            </b-form-group>
                        </b-col>
                        <b-col cols="12" class="mt-3">
                            <b-form-group>
                                <label for="username" class="mb-2">Correo electrónico:</label>
                                <b-form-input id="username" v-model.trim="user.username" :readonly="viewPersonalInfo"
                                    @input="validateField('username')"></b-form-input>
                                <b-form-invalid-feedback v-show="showErrors.username">
                                    {{ errorMessages.username }}
                                </b-form-invalid-feedback>
                            </b-form-group>
                        </b-col>
                        <b-col cols="12" class="mt-3">
                            <b-form-group>
                                <label for="phoneNumber" class="mb-2">Número de teléfono:</label>
                                <b-form-input id="phoneNumber" v-model.trim="user.phoneNumber"
                                    :readonly="viewPersonalInfo" @input="validateField('phoneNumber')"></b-form-input>
                                <b-form-invalid-feedback v-show="showErrors.phoneNumber">
                                    {{ errorMessages.phoneNumber }}
                                </b-form-invalid-feedback>
                            </b-form-group>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col cols="12">
                            <b-button variant="orange"
                                class="mt-4 w-100 d-flex align-items-center justify-content-between"
                                @click="handleUpdateButton(1)">
                                <span>{{ viewPersonalInfo ? 'Modificar' : 'Guardar modificaciones' }}</span>
                                <b-icon class="me-2" :icon="viewPersonalInfo ? 'pencil' : 'check-circle'"
                                    font-scale="1.2"></b-icon>
                            </b-button>
                        </b-col>
                    </b-row>
                </b-card>
            </b-col>
            <b-col cols="8">
                <b-card bg-variant="secondary-blue" class="py-2 form-card-shadow relative-position form-card-title"
                    no-body>
                    <div class="d-flex align-items-center ms-3 ms-md-4">
                        <b-icon icon="house-door" variant="dark" font-scale="1.5" class="me-2"></b-icon>
                        <h4 class="mb-0 mt-1">Información de tu hogar</h4>
                    </div>
                </b-card>
                <b-card bg-variant="gray" class="form-card-shadow form-card-content p-4">
                    <b-row>
                        <b-col cols="6">
                            <b-row>
                                <b-col cols="12">
                                    <b-img id="homeImage" :src="user.addressDto.homeSpecification.homeImage"
                                        class="home-img"></b-img>
                                </b-col>
                                <b-col cols="12">
                                    <label for="homeImage">Imagen de tu hogar</label>
                                </b-col>
                            </b-row>
                        </b-col>
                        <b-col cols="6">
                            <b-row>
                                <b-col cols="12" class="mt-3">
                                    <b-form-group>
                                        <label for="country" class="mb-2">País:</label>
                                        <b-form-input id="country" v-model="user.addressDto.country"
                                            :readonly="viewAddress"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="12" class="mt-3">
                                    <b-form-group>
                                        <label for="state" class="mb-2">Estado:</label>
                                        <b-form-input id="state" v-model="user.addressDto.state"
                                            :readonly="viewAddress"></b-form-input>
                                    </b-form-group>
                                </b-col>
                            </b-row>
                        </b-col>
                        <b-col cols="12">
                            <b-row>
                                <b-col cols="4" class="mt-3">
                                    <b-form-group>
                                        <label for="city" class="mb-2">Ciudad:</label>
                                        <b-form-input id="city" v-model="user.addressDto.city"
                                            :readonly="viewAddress"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="4" class="mt-3">
                                    <b-form-group>
                                        <label for="colony" class="mb-2">Colonia:</label>
                                        <b-form-input id="colony" v-model="user.addressDto.colony"
                                            :readonly="viewAddress"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="4" class="mt-3">
                                    <b-form-group>
                                        <label for="street" class="mb-2">Calle:</label>
                                        <b-form-input id="street" v-model="user.addressDto.street"
                                            :readonly="viewAddress"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="3" class="mt-3">
                                    <b-form-group>
                                        <label for="externalNumber" class="mb-2">Número exterior:</label>
                                        <b-form-input id="externalNumber" v-model="user.addressDto.exteriorNumber"
                                            :readonly="viewAddress"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="3" class="mt-3">
                                    <b-form-group>
                                        <label for="internalNumber" class="mb-2">Número interior:</label>
                                        <b-form-input id="internalNumber" v-model="user.addressDto.interiorNumber"
                                            :readonly="viewAddress"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="6" class="mt-3">
                                    <b-form-group>
                                        <label for="postalCode" class="mb-2">Código postal:</label>
                                        <b-form-input id="postalCode" v-model="user.addressDto.postalCode"
                                            :readonly="viewAddress"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="12" class="mt-3">
                                    <b-form-group>
                                        <label for="addressReference" class="mb-2">Referencia de la
                                            dirección:</label>
                                        <b-form-textarea id="addressReference"
                                            v-model="user.addressDto.addressReference" rows="3"
                                            :readonly="viewAddress"></b-form-textarea>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="4" class="mt-3">
                                    <b-form-group>
                                        <label for="homeType" class="mb-2">Tipo de hogar:</label>
                                        <b-form-input id="homeType" v-model="user.addressDto.homeSpecification.type"
                                            :readonly="viewAddress"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="4" class="mt-3">
                                    <b-form-group>
                                        <label for="numberOfResidents" class="mb-2">Número de
                                            residentes:</label>
                                        <b-form-input id="numberOfResidents"
                                            v-model="user.addressDto.homeSpecification.numberOfResidents"
                                            :readonly="viewAddress"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="4" class="mt-3">
                                    <b-form-group>
                                        <label for="outdoorArea" class="mb-2">¿Tienes área exterior?</label>
                                        <b-form-select id="outdoorArea" class="form-select"
                                            v-model="user.addressDto.homeSpecification.outdoorArea"
                                            :disabled="viewAddress">
                                            <b-form-select-option value="true">Sí</b-form-select-option>
                                            <b-form-select-option value="false">No</b-form-select-option>
                                        </b-form-select>
                                    </b-form-group>
                                </b-col>
                            </b-row>
                        </b-col>
                    </b-row>
                    <b-row class="d-flex justify-content-end">
                        <b-col cols="5">
                            <b-button variant="secondary-blue"
                                class="mt-4 w-100 d-flex align-items-center justify-content-between"
                                @click="handleUpdateButton(2)">
                                <span>{{ viewAddress ? 'Modificar' : 'Guardar modificaciones' }}</span>
                                <b-icon class="me-2" :icon="viewAddress ? 'pencil' : 'check-circle'"
                                    font-scale="1.2"></b-icon>
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
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import instance from "../../../config/axios";
import { decrypt } from '../../../kernel/hashFunctions';
import { isInvalidName, isInvalidNoSpecialCharactersString, isInvalidEmail, isInvalidPhoneNumber } from "../../../kernel/validations";

export default {
    data() {
        return {
            viewPersonalInfo: true,
            viewAddress: true,
            isValidPersonalInformationForm: false,
            user: {
                userId: "",
                username: "",
                name: "",
                lastname: "",
                surname: "",
                phoneNumber: "",
                addressDto: {
                    addressId: "",
                    country: "",
                    state: "",
                    city: "",
                    colony: "",
                    street: "",
                    postalCode: "",
                    exteriorNumber: "",
                    interiorNumber: "",
                    addressReference: "",
                    homeSpecification: {
                        homeSpecificationId: "",
                        type: "",
                        outdoorArea: false,
                        numberOfResidents: 0,
                        homeImage: "",
                    },
                },
            },
            showErrors: {
                name: false,
                lastname: false,
                surname: false,
                username: false,
                phoneNumber: false,
            },
            errorMessages: {
                name: "",
                lastname: "",
                surname: "",
                username: "",
                phoneNumber: "",
                country: "",
            }
        }
    },
    methods: {
        goBack() {
            this.$router.go(-1);
        },
        showError() {
            Swal.fire({
                title: 'Error',
                text: 'Ocurrió un error al cargar los datos de tu perfil',
                icon: 'error',
                iconColor: '#A93D3D',
                timer: 3000,
                timerProgressBar: true,
                showConfirmButton: false
            }).then(() => {
                this.goBack();
            })
        },
        async getProfile() {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Estamos cargando los datos de tu perfil, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                const response = await instance.post(`/person/find/allInfo`, { userId: this.user.userId });
                this.user = response.data.data;
                this.user.username = await decrypt(this.user.username);
                this.user.phoneNumber = await decrypt(this.user.phoneNumber);
                if (this.user.addressDto.exteriorNumber == null) this.user.addressDto.exteriorNumber = "S/N";
                if (this.user.addressDto.interiorNumber == null) this.user.addressDto.interiorNumber = "S/N";
                Swal.close();
            } catch (error) {
                /* Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al cargar los detalles de la mascota',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    this.$router.push('/petList')
                }) */
            }
        },
        validateField(field) {
            const input = document.getElementById(field);
            switch (field) {
                case "name":
                    if (!this.user.name) {
                        this.showErrors.name = true;
                        this.errorMessages.name = "Campo obligatorio";
                        input.classList.add("is-invalid");
                    } else if (this.user.name.length < 3 || this.user.name.length > 50) {
                        this.showErrors.name = true;
                        this.errorMessages.name = "El nombre debe tener entre 3 y 50 caracteres";
                        input.classList.add("is-invalid");
                    } else if (isInvalidName(this.user.name)) {
                        this.showErrors.name = true;
                        this.errorMessages.name = "El nombre no puede contener números ni caracteres especiales";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.name = false;
                        this.errorMessages.name = "";
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
                case "lastname":
                    if (!this.user.lastname) {
                        this.showErrors.lastname = true;
                        this.errorMessages.lastname = "Campo obligatorio";
                        input.classList.add("is-invalid");
                    } else if (this.user.lastname.length < 3 || this.user.lastname.length > 50) {
                        this.showErrors.lastname = true;
                        this.errorMessages.lastname = "El apellido paterno debe tener entre 3 y 50 caracteres";
                        input.classList.add("is-invalid");
                    } else if (isInvalidName(this.user.lastname)) {
                        this.showErrors.lastname = true;
                        this.errorMessages.lastname = "El apellido paterno no puede contener números ni caracteres especiales";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.lastname = false;
                        this.errorMessages.lastname = "";
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
                case "surname":
                    if (!this.user.surname) {
                        this.showErrors.surname = true;
                        this.errorMessages.surname = "Campo obligatorio";
                        input.classList.add("is-invalid");
                    } else if (this.user.surname.length < 3 || this.user.surname.length > 50) {
                        this.showErrors.surname = true;
                        this.errorMessages.surname = "El apellido materno debe tener entre 3 y 50 caracteres";
                        input.classList.add("is-invalid");
                    } else if (isInvalidName(this.user.surname)) {
                        this.showErrors.surname = true;
                        this.errorMessages.surname = "El apellido materno no puede contener números ni caracteres especiales";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.surname = false;
                        this.errorMessages.surname = "";
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
                case "username":
                    if (!this.user.username) {
                        this.showErrors.username = true;
                        this.errorMessages.username = "Campo obligatorio";
                        input.classList.add("is-invalid");
                    } else if (isInvalidEmail(this.user.username)) {
                        this.showErrors.username = true;
                        this.errorMessages.username = "Correo electrónico inválido";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.username = false;
                        this.errorMessages.username = "";
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
                case "phoneNumber":
                    if (!this.user.phoneNumber) {
                        this.showErrors.phoneNumber = true;
                        this.errorMessages.phoneNumber = "Campo obligatorio";
                        input.classList.add("is-invalid");
                    } else if (isInvalidPhoneNumber(this.user.phoneNumber)) {
                        this.showErrors.phoneNumber = true;
                        this.errorMessages.phoneNumber = "Número de teléfono inválido";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.phoneNumber = false;
                        this.errorMessages.phoneNumber = "";
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
            }
        },
        validatePersonalInformationForm() {
            this.validateField("name");
            this.validateField("lastname");
            this.validateField("surname");
            this.validateField("username");
            this.validateField("phoneNumber");
            if (!this.showErrors.name && !this.showErrors.lastname && !this.showErrors.surname && !this.showErrors.username && !this.showErrors.phoneNumber) this.isValidPersonalInformationForm = true;
            else this.isValidPersonalInformationForm = false;
        },
        confirmUpdatePersonalInfo() {
            this.validatePersonalInformationForm();
            if (this.isValidPersonalInformationForm) {
                Swal.fire({
                    title: '¿Estás seguro?',
                    text: 'Estás a punto de actualizar tus datos personales, ¿deseas continuar?',
                    icon: 'warning',
                    iconColor: '#FFA500',
                    showCancelButton: true,
                    confirmButtonColor: '#FFA500',
                    cancelButtonColor: '#A93D3D',
                    confirmButtonText: 'Sí, continuar',
                    cancelButtonText: 'Cancelar'
                }).then((result) => {
                    if (result.isConfirmed) {
                        this.updatePersonalInfo();
                    }
                })
            }
        },
        async updatePersonalInfo() {
            try {
                Swal.fire({
                    title: 'Actualizando...',
                    text: 'Estamos actualizando tus datos, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                const response = true;
                Swal.fire({
                    title: '¡Listo!',
                    text: 'Tus datos se han actualizado correctamente',
                    icon: 'success',
                    iconColor: '#4BB543',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                })
                this.viewPersonalInfo = !this.viewPersonalInfo;
            } catch (error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al actualizar tus datos',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                })
            }
        },
        handleUpdateButton(option) {
            if (option == 1) {
                if (this.viewPersonalInfo) {
                    this.viewPersonalInfo = !this.viewPersonalInfo;
                } else {
                    this.confirmUpdatePersonalInfo();
                }
            } else {
                this.viewAddress = !this.viewAddress;
            }
        },
    },
    mounted() {
        if (localStorage.getItem("userId")) {
            this.user.userId = localStorage.getItem("userId");
            this.getProfile();
        } else {
            this.showError();
        }
    }
}
</script>

<style scoped>
.home-img {
    width: 300px;
    height: auto;
    object-fit: cover;
    border-radius: 10px;
}
</style>