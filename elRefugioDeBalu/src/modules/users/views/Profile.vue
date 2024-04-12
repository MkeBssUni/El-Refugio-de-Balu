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
                <b-card bg-variant="gray" class="form-card-shadow form-card-content" no-body>
                    <b-row class="px-4 pt-5">
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
                                <label for="phoneNumber" class="mb-2">Número de teléfono:</label>
                                <b-form-input id="phoneNumber" v-model.trim="user.phoneNumber"
                                    :readonly="viewPersonalInfo" @input="validateField('phoneNumber')"
                                    type="tel"></b-form-input>
                                <b-form-invalid-feedback v-show="showErrors.phoneNumber">
                                    {{ errorMessages.phoneNumber }}
                                </b-form-invalid-feedback>
                            </b-form-group>
                        </b-col>
                    </b-row>
                    <b-row class="px-4 pb-4">
                        <b-col cols="12">
                            <b-button variant="orange"
                                class="mt-4 w-100 d-flex align-items-center justify-content-between"
                                @click="handleUpdateButton(1)">
                                <span>{{ viewPersonalInfo ? 'Modificar' : 'Guardar modificaciones' }}</span>
                                <b-icon class="me-2" :icon="viewPersonalInfo ? 'pencil' : 'check-circle'"
                                    font-scale="1.2"></b-icon>
                            </b-button>
                        </b-col>
                        <b-col cols="12" v-if="!viewPersonalInfo">
                            <b-button variant="light-danger"
                                class="mt-3 w-100 d-flex align-items-center justify-content-between" @click="cancel(1)">
                                <span>Cancelar</span>
                                <b-icon class="me-2" icon="x-circle" font-scale="1.2"></b-icon>
                            </b-button>
                        </b-col>
                    </b-row>
                    <hr>
                    <b-row class="px-4">                        
                        <b-col cols="12" class="mt-3">
                            <b-form-group>
                                <label for="username" class="mb-2">Correo electrónico:</label>
                                <b-form-input id="username" v-model.trim="user.username" :readonly="viewEmail"
                                    @input="validateField('username')"></b-form-input>
                                <b-form-invalid-feedback v-show="showErrors.username">
                                    {{ errorMessages.username }}
                                </b-form-invalid-feedback>
                            </b-form-group>
                        </b-col>
                    </b-row>
                    <b-row class="px-4 pb-4">
                        <b-col cols="12">
                            <b-button variant="orange"
                                class="mt-4 w-100 d-flex align-items-center justify-content-between"
                                @click="handleUpdateButton(3)">
                                <span>{{ viewEmail ? 'Modificar email' : 'Guardar email' }}</span>
                                <b-icon class="me-2" :icon="viewEmail ? 'pencil' : 'check-circle'"
                                    font-scale="1.2"></b-icon>
                            </b-button>
                        </b-col>
                        <b-col cols="12" v-if="!viewEmail">
                            <b-button variant="light-danger"
                                class="mt-3 w-100 d-flex align-items-center justify-content-between" @click="cancel(3)">
                                <span>Cancelar</span>
                                <b-icon class="me-2" icon="x-circle" font-scale="1.2"></b-icon>
                            </b-button>
                        </b-col>
                    </b-row>
                </b-card>
            </b-col>
            <b-col cols="8" v-if="showAddress">
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
                                <b-col cols="12" class="d-flex justify-content-center mt-3">
                                    <b-img id="homeImage" :src="showImg()" class="home-img"></b-img>
                                </b-col>
                            </b-row>
                            <b-row v-show="viewAddress">
                                <b-col cols="12" class="text-center mt-2">
                                    <label for="homeImage">Imagen de tu hogar</label>
                                </b-col>
                            </b-row>
                            <b-row v-if="!viewAddress" class="d-flex justify-content-center px-3">
                                <b-col cols="6">
                                    <b-button variant="secondary-blue"
                                        class="mt-3 w-100 d-flex justify-content-between align-items-center"
                                        @click="triggerMainImgSelector">
                                        <span>Seleccionar</span>
                                        <b-icon icon="image" font-scale="1.2"></b-icon>
                                    </b-button>
                                    <input type="file" accept="image/jpeg, image/png" id="homeImage"
                                        ref="homeImageSelector" @change="selectImg" class="d-none">
                                </b-col>
                                <b-col cols="6" v-if="user.addressDto.homeSpecification.homeImage">
                                    <b-button variant="light-danger"
                                        class="mt-3 w-100 d-flex justify-content-between align-items-center"
                                        @click="removeImg()">
                                        <span>Eliminar</span>
                                        <b-icon icon="x-circle" font-scale="1.2"></b-icon>
                                    </b-button>
                                </b-col>
                            </b-row>
                        </b-col>
                        <b-col cols="6">
                            <b-row>
                                <b-col cols="12" class="mt-3">
                                    <b-form-group>
                                        <label for="country" class="mb-2">País:</label>
                                        <b-form-select id="country" v-model="user.addressDto.country"
                                            :disabled="viewAddress" class="form-select">
                                            <option v-for="country in countries" :key="country.value"
                                                :value="country.value">{{ country.value }}</option>
                                        </b-form-select>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="12" class="mt-3">
                                    <b-form-group>
                                        <label for="state" class="mb-2">Estado:</label>
                                        <b-form-select id="state" v-model="user.addressDto.state"
                                            :disabled="viewAddress" class="form-select">
                                            <option v-for="state in states" :key="state.value" :value="state.value">{{
                                                state.value }}</option>
                                        </b-form-select>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="12" class="mt-3">
                                    <b-form-group>
                                        <label for="city" class="mb-2">Ciudad:</label>
                                        <b-form-input id="city" v-model="user.addressDto.city" :readonly="viewAddress"
                                            @input="validateField('city')"></b-form-input>
                                        <b-form-invalid-feedback v-show="showErrors.city">
                                            {{ errorMessages.city }}
                                        </b-form-invalid-feedback>
                                    </b-form-group>
                                </b-col>
                            </b-row>
                        </b-col>
                        <b-col cols="12">
                            <b-row>
                                <b-col cols="6" class="mt-3">
                                    <b-form-group>
                                        <label for="colony" class="mb-2">Colonia:</label>
                                        <b-form-input id="colony" v-model="user.addressDto.colony"
                                            :readonly="viewAddress" @input="validateField('colony')"></b-form-input>
                                        <b-form-invalid-feedback v-show="showErrors.colony">
                                            {{ errorMessages.colony }}
                                        </b-form-invalid-feedback>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="6" class="mt-3">
                                    <b-form-group>
                                        <label for="street" class="mb-2">Calle:</label>
                                        <b-form-input id="street" v-model="user.addressDto.street"
                                            :readonly="viewAddress" @input="validateField('street')"></b-form-input>
                                        <b-form-invalid-feedback v-show="showErrors.street">
                                            {{ errorMessages.street }}
                                        </b-form-invalid-feedback>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="3" class="mt-3">
                                    <b-form-group>
                                        <label for="externalNumber" class="mb-2">Número exterior:</label>
                                        <b-form-input id="externalNumber" v-model="user.addressDto.exteriorNumber"
                                            :readonly="viewAddress"
                                            @input="validateField('externalNumber')"></b-form-input>
                                        <b-form-invalid-feedback v-show="showErrors.externalNumber">
                                            {{ errorMessages.externalNumber }}
                                        </b-form-invalid-feedback>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="3" class="mt-3">
                                    <b-form-group>
                                        <label for="internalNumber" class="mb-2">Número interior:</label>
                                        <b-form-input id="internalNumber" v-model="user.addressDto.interiorNumber"
                                            :readonly="viewAddress"
                                            @input="validateField('internalNumber')"></b-form-input>
                                        <b-form-invalid-feedback v-show="showErrors.internalNumber">
                                            {{ errorMessages.internalNumber }}
                                        </b-form-invalid-feedback>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="6" class="mt-3">
                                    <b-form-group>
                                        <label for="postalCode" class="mb-2">Código postal:</label>
                                        <b-form-input id="postalCode" v-model="user.addressDto.postalCode"
                                            :readonly="viewAddress" @input="validateField('postalCode')"></b-form-input>
                                        <b-form-invalid-feedback v-show="showErrors.postalCode">
                                            {{ errorMessages.postalCode }}
                                        </b-form-invalid-feedback>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="12" class="mt-3">
                                    <b-form-group>
                                        <label for="addressReference" class="mb-2">Referencia de la
                                            dirección:</label>
                                        <b-form-textarea id="addressReference"
                                            v-model="user.addressDto.addressReference" :readonly="viewAddress"
                                            @input="validateField('addressReference')" rows="3"></b-form-textarea>
                                        <b-form-invalid-feedback v-show="showErrors.addressReference">
                                            {{ errorMessages.addressReference }}
                                        </b-form-invalid-feedback>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="4" class="mt-3">
                                    <b-form-group>
                                        <label for="homeType" class="mb-2">Tipo de hogar:</label>
                                        <b-form-select id="homeType" class="form-select"
                                            v-model="user.addressDto.homeSpecification.type" :disabled="viewAddress">
                                            <b-form-select-option v-for="homeType in homeTypes" :key="homeType.value"
                                                :value="homeType.value">{{ homeType.text }}</b-form-select-option>
                                        </b-form-select>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="4" class="mt-3">
                                    <b-form-group>
                                        <label for="numberOfResidents" class="mb-2">Número de
                                            residentes:</label>
                                        <b-form-input id="numberOfResidents"
                                            v-model="user.addressDto.homeSpecification.numberOfResidents"
                                            :readonly="viewAddress" @input="validateField('numberOfResidents')"
                                            type="number"></b-form-input>
                                        <b-form-invalid-feedback v-show="showErrors.numberOfResidents">
                                            {{ errorMessages.numberOfResidents }}
                                        </b-form-invalid-feedback>
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
                        <b-col cols="5" v-if="!viewAddress">
                            <b-button variant="light-danger"
                                class="mt-4 w-100 d-flex align-items-center justify-content-between" @click="cancel(2)">
                                <span>Cancelar</span>
                                <b-icon class="me-2" icon="x-circle" font-scale="1.2"></b-icon>
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
import { decrypt, encrypt } from '../../../kernel/hashFunctions';
import { isInvalidName, isInvalidNoSpecialCharactersString, isInvalidEmail, isInvalidPhoneNumber, isInvalidOnlyNumbersString } from "../../../kernel/validations";
import countries from "../../../kernel/data/countries";
import states from "../../../kernel/data/states";
import homeTypes from "../../../kernel/data/homeTypes";

export default {
    data() {
        return {
            showAddress: false,
            countries: countries,
            states: states,
            homeTypes: homeTypes,
            viewPersonalInfo: true,
            viewAddress: true,
            viewEmail: true,
            isValidPersonalInformationForm: false,
            isValidAddressForm: false,
            isValidEmailForm: false,
            user: {
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
                        homeImage: null,
                    },
                },
            },
            showErrors: {
                name: false,
                lastname: false,
                surname: false,
                username: false,
                phoneNumber: false,
                country: false,
                state: false,
                city: false,
                colony: false,
                street: false,
                externalNumber: false,
                internalNumber: false,
                postalCode: false,
                addressReference: false,
                homeType: false,
                numberOfResidents: false,
                outdoorArea: false,
                homeImage: false,
            },
            errorMessages: {
                name: "",
                lastname: "",
                surname: "",
                username: "",
                phoneNumber: "",
                country: "",
                state: "",
                city: "",
                colony: "",
                street: "",
                externalNumber: "",
                internalNumber: "",
                postalCode: "",
                addressReference: "",
                homeType: "",
                numberOfResidents: "",
                outdoorArea: "",
            }
        }
    },
    methods: {
        cleanPersonalInformationInputs() {
            document.getElementById("name").classList.remove("is-invalid", "is-valid");
            document.getElementById("lastname").classList.remove("is-invalid", "is-valid");
            document.getElementById("surname").classList.remove("is-invalid", "is-valid");
            document.getElementById("phoneNumber").classList.remove("is-invalid", "is-valid");
        },
        cleanAddressInputs() {
            document.getElementById("country").classList.remove("is-invalid", "is-valid");
            document.getElementById("state").classList.remove("is-invalid", "is-valid");
            document.getElementById("city").classList.remove("is-invalid", "is-valid");
            document.getElementById("colony").classList.remove("is-invalid", "is-valid");
            document.getElementById("street").classList.remove("is-invalid", "is-valid");
            document.getElementById("externalNumber").classList.remove("is-invalid", "is-valid");
            document.getElementById("internalNumber").classList.remove("is-invalid", "is-valid");
            document.getElementById("postalCode").classList.remove("is-invalid", "is-valid");
            document.getElementById("addressReference").classList.remove("is-invalid", "is-valid");
            document.getElementById("homeType").classList.remove("is-invalid", "is-valid");
            document.getElementById("numberOfResidents").classList.remove("is-invalid", "is-valid");
        },
        cleanEmailInput() {
            document.getElementById("username").classList.remove("is-invalid", "is-valid");
        },
        cancel(option) {
            switch (option) {
                case 1:
                    this.viewPersonalInfo = true;
                    this.cleanPersonalInformationInputs();
                    this.getProfile();
                    break;
                case 2:
                    this.viewAddress = true;
                    this.cleanAddressInputs();
                    this.getProfile();
                    break;
                case 3:
                    this.viewEmail = true;
                    this.cleanEmailInput();
                    this.getProfile();
                    break;
            }
        },
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
                const response = await instance.post(`/person/find/allInfo`, { userId: localStorage.getItem("userId") });
                this.user = response.data.data;
                this.user.username = await decrypt(this.user.username);
                this.user.phoneNumber = await decrypt(this.user.phoneNumber);
                Swal.close();
            } catch (error) {
                this.showError();
            }
        },
        loadImgError() {
            Swal.fire({
                icon: 'error',
                title: '¡Error!',
                text: 'Ocurrió un error al cargar la imagen, intenta de nuevo con otra',
                toast: true,
                position: 'top-end',
                timer: 3000,
                showCancelButton: false,
                showConfirmButton: false,
                timerProgressBar: true,
            });
        },
        convertToBase64(file) {
            return new Promise((resolve, reject) => {
                const fileReader = new FileReader();
                fileReader.readAsDataURL(file);
                fileReader.onload = () => resolve(fileReader.result);
                fileReader.onerror = (error) => reject(error);
            });
        },
        validateNotNullHomeImg() {
            if (!this.user.addressDto.homeSpecification.homeImage) {
                Swal.fire({
                    icon: 'error',
                    title: '¡Error!',
                    text: 'Debes seleccionar una imagen de tu hogar',
                    toast: true,
                    position: 'top-end',
                    timer: 3000,
                    showCancelButton: false,
                    showConfirmButton: false,
                    timerProgressBar: true,
                });
                this.showErrors.homeImage = true;
                return;
            }
            this.showErrors.homeImage = false;
        },
        validateImg(file) {
            if (file.type != "image/jpeg" && file.type != "image/png") {
                Swal.fire({
                    icon: 'error',
                    title: '¡Error!',
                    text: 'Selecciona una imagen en formato JPG o PNG',
                    toast: true,
                    position: 'top-end',
                    timer: 3000,
                    showCancelButton: false,
                    showConfirmButton: false,
                    timerProgressBar: true,
                });
                return false;
            }
            if (file.size > 4000000) {
                Swal.fire({
                    icon: 'error',
                    title: '¡Error!',
                    text: 'La imagen no puede pesar más de 4MB',
                    toast: true,
                    position: 'top-end',
                    timer: 3000,
                    showCancelButton: false,
                    showConfirmButton: false,
                    timerProgressBar: true,
                });
                return false;
            }
            return true;
        },
        showImg() {
            if (this.user.addressDto.homeSpecification.homeImage) return this.user.addressDto.homeSpecification.homeImage;
            return "https://i.pinimg.com/736x/b3/cc/d5/b3ccd57b054a73af1a0d281265b54ec8.jpg";
        },
        triggerMainImgSelector() {
            this.$refs.homeImageSelector.click();
        },
        async selectImg() {
            const file = this.$refs.homeImageSelector.files[0];
            if (file && this.validateImg(file)) {
                try {
                    const base64Image = await this.convertToBase64(file);
                    this.user.addressDto.homeSpecification.homeImage = base64Image;
                } catch (error) {
                    this.loadImgError();
                }
            }
        },
        removeImg() {
            this.user.addressDto.homeSpecification.homeImage = null;
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
                case "country":
                    if (!this.user.addressDto.country) {
                        this.showErrors.country = true;
                        this.errorMessages.country = "Campo obligatorio";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.country = false;
                        this.errorMessages.country = "";
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
                case "state":
                    if (!this.user.addressDto.state) {
                        this.showErrors.state = true;
                        this.errorMessages.state = "Campo obligatorio";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.state = false;
                        this.errorMessages.state = "";
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
                case "city":
                    if (!this.user.addressDto.city) {
                        this.showErrors.city = true;
                        this.errorMessages.city = "Campo obligatorio";
                        input.classList.add("is-invalid");
                    } else if (this.user.addressDto.city.length < 3 || this.user.addressDto.city.length > 50) {
                        this.showErrors.city = true;
                        this.errorMessages.city = "La ciudad debe tener entre 3 y 50 caracteres";
                        input.classList.add("is-invalid");
                    } else if (isInvalidNoSpecialCharactersString(this.user.addressDto.city)) {
                        this.showErrors.city = true;
                        this.errorMessages.city = "La ciudad no puede contener caracteres especiales";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.city = false;
                        this.errorMessages.city = "";
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
                case "colony":
                    if (!this.user.addressDto.colony) {
                        this.showErrors.colony = true;
                        this.errorMessages.colony = "Campo obligatorio";
                        input.classList.add("is-invalid");
                    } else if (this.user.addressDto.colony.length < 3 || this.user.addressDto.colony.length > 50) {
                        this.showErrors.colony = true;
                        this.errorMessages.colony = "La colonia debe tener entre 3 y 50 caracteres";
                        input.classList.add("is-invalid");
                    } else if (isInvalidNoSpecialCharactersString(this.user.addressDto.colony)) {
                        this.showErrors.colony = true;
                        this.errorMessages.colony = "La colonia no puede contener caracteres especiales";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.colony = false;
                        this.errorMessages.colony = "";
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
                case "street":
                    if (!this.user.addressDto.street) {
                        this.showErrors.street = true;
                        this.errorMessages.street = "Campo obligatorio";
                        input.classList.add("is-invalid");
                    } else if (this.user.addressDto.street.length < 3 || this.user.addressDto.street.length > 50) {
                        this.showErrors.street = true;
                        this.errorMessages.street = "La calle debe tener entre 3 y 50 caracteres";
                        input.classList.add("is-invalid");
                    } else if (isInvalidNoSpecialCharactersString(this.user.addressDto.street)) {
                        this.showErrors.street = true;
                        this.errorMessages.street = "La calle no puede contener caracteres especiales";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.street = false;
                        this.errorMessages.street = "";
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
                case "externalNumber":
                    if (!this.user.addressDto.exteriorNumber) {
                        this.showErrors.externalNumber = true;
                        this.errorMessages.externalNumber = "Campo obligatorio";
                        input.classList.add("is-invalid");
                    } else if (this.user.addressDto.exteriorNumber.length < 1 || this.user.addressDto.exteriorNumber.length > 3) {
                        this.showErrors.externalNumber = true;
                        this.errorMessages.externalNumber = "El número exterior debe tener entre 1 y 3 caracteres";
                        input.classList.add("is-invalid");
                    } else if (isInvalidOnlyNumbersString(this.user.addressDto.exteriorNumber)) {
                        this.showErrors.externalNumber = true;
                        this.errorMessages.externalNumber = "El número exterior solo puede contener números";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.externalNumber = false;
                        this.errorMessages.externalNumber = "";
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
                case "internalNumber":
                    if (this.user.addressDto.interiorNumber && (this.user.addressDto.interiorNumber.length < 1 || this.user.addressDto.interiorNumber.length > 3)) {
                        this.showErrors.internalNumber = true;
                        this.errorMessages.internalNumber = "El número interior debe tener entre 1 y 3 caracteres";
                        input.classList.add("is-invalid");
                    } else if (this.user.addressDto.interiorNumber && isInvalidOnlyNumbersString(this.user.addressDto.interiorNumber)) {
                        this.showErrors.internalNumber = true;
                        this.errorMessages.internalNumber = "El número interior solo puede contener números";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.internalNumber = false;
                        this.errorMessages.internalNumber = "";
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
                case "postalCode":
                    if (!this.user.addressDto.postalCode) {
                        this.showErrors.postalCode = true;
                        this.errorMessages.postalCode = "Campo obligatorio";
                        input.classList.add("is-invalid");
                    } else if (this.user.addressDto.postalCode.length < 5 || this.user.addressDto.postalCode.length > 5) {
                        this.showErrors.postalCode = true;
                        this.errorMessages.postalCode = "El código postal debe tener 5 caracteres";
                        input.classList.add("is-invalid");
                    } else if (isInvalidOnlyNumbersString(this.user.addressDto.postalCode)) {
                        this.showErrors.postalCode = true;
                        this.errorMessages.postalCode = "El código postal solo puede contener números";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.postalCode = false;
                        this.errorMessages.postalCode = "";
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
                case "addressReference":
                    if (this.user.addressDto.addressReference && (this.user.addressDto.addressReference.length < 20 || this.user.addressDto.addressReference.length > 200)) {
                        this.showErrors.addressReference = true;
                        this.errorMessages.addressReference = "La referencia de la dirección debe tener entre 20 y 200 caracteres";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.addressReference = false;
                        this.errorMessages.addressReference = "";
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
                case "homeType":
                    if (!this.user.addressDto.homeSpecification.type) {
                        this.showErrors.homeType = true;
                        this.errorMessages.homeType = "Campo obligatorio";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.homeType = false;
                        this.errorMessages.homeType = "";
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
                case "numberOfResidents":
                    if (!this.user.addressDto.homeSpecification.numberOfResidents) {
                        this.showErrors.numberOfResidents = true;
                        this.errorMessages.numberOfResidents = "Campo obligatorio";
                        input.classList.add("is-invalid");
                    } else if (this.user.addressDto.homeSpecification.numberOfResidents < 1) {
                        this.showErrors.numberOfResidents = true;
                        this.errorMessages.numberOfResidents = "El número de residentes debe ser al menos 1";
                        input.classList.add("is-invalid");
                    } else {
                        this.showErrors.numberOfResidents = false;
                        this.errorMessages.numberOfResidents = "";
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
            this.validateField("phoneNumber");
            if (!this.showErrors.name && !this.showErrors.lastname && !this.showErrors.surname && !this.showErrors.phoneNumber) this.isValidPersonalInformationForm = true;
            else this.isValidPersonalInformationForm = false;
        },
        validateAddressForm() {
            this.validateNotNullHomeImg();
            this.validateField("country");
            this.validateField("state");
            this.validateField("city");
            this.validateField("colony");
            this.validateField("street");
            this.validateField("externalNumber");
            this.validateField("internalNumber");
            this.validateField("postalCode");
            this.validateField("addressReference");
            this.validateField("homeType");
            this.validateField("numberOfResidents");
            if (!this.showErrors.country && !this.showErrors.state && !this.showErrors.city && !this.showErrors.colony && !this.showErrors.street && !this.showErrors.externalNumber && !this.showErrors.internalNumber && !this.showErrors.postalCode && !this.showErrors.addressReference && !this.showErrors.homeType && !this.showErrors.numberOfResidents && !this.showErrors.homeImage) this.isValidAddressForm = true;
            else this.isValidAddressForm = false;
        },
        validateEmailForm() {
            this.validateField("username");
            if (!this.showErrors.username) this.isValidEmailForm = true;
            else this.isValidEmailForm = false;
        },
        confirmUpdateAddress() {
            this.validateAddressForm();
            if (this.isValidAddressForm) {
                Swal.fire({
                    title: '¿Estás seguro?',
                    text: 'Estás a punto de actualizar la información de tu hogar, ¿deseas continuar?',
                    icon: 'warning',
                    iconColor: '#FFA500',
                    showCancelButton: true,
                    confirmButtonColor: '#FFA500',
                    cancelButtonColor: '#A93D3D',
                    confirmButtonText: 'Sí, continuar',
                    cancelButtonText: 'Cancelar'
                }).then((result) => {
                    if (result.isConfirmed) {
                        this.updateAddress();
                    }
                })
            }
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
        confirmUpdateEmail() {
            this.validateEmailForm();
            if (this.isValidEmailForm) {
                Swal.fire({
                    title: '¿Estás seguro?',                    
                    text: 'Estás a punto de actualizar tu correo electrónico, deberás iniciar sesión nuevamente, ¿deseas continuar?',
                    icon: 'warning',
                    iconColor: '#FFA500',
                    showCancelButton: true,
                    confirmButtonColor: '#FFA500',
                    cancelButtonColor: '#A93D3D',
                    confirmButtonText: 'Sí, continuar',
                    cancelButtonText: 'Cancelar'
                }).then((result) => {
                    if (result.isConfirmed) {
                        this.updateEmail();
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
                const email = await encrypt(this.user.username);
                const phone = await encrypt(this.user.phoneNumber);
                await instance.put(`/person/update/personalInfo`, {
                    userId: localStorage.getItem("userId"),
                    name: this.user.name,
                    lastname: this.user.lastname,
                    surname: this.user.surname,
                    email: email,
                    phone: phone
                });
                Swal.fire({
                    title: '¡Listo!',
                    text: 'Tus datos personales se han actualizado correctamente',
                    icon: 'success',
                    iconColor: '#4BB543',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    this.getProfile();
                    this.viewPersonalInfo = !this.viewPersonalInfo;
                    this.cleanPersonalInformationInputs();
                })
            } catch (error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al actualizar tus datos personales',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                })
            }
        },
        async updateAddress() {
            try {
                Swal.fire({
                    title: 'Actualizando...',
                    text: 'Estamos actualizando la información de tu hogar, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                await instance.put(`/address/`, {
                    userId: localStorage.getItem("userId"),
                    country: this.user.addressDto.country,
                    state: this.user.addressDto.state,
                    city: this.user.addressDto.city,
                    colony: this.user.addressDto.colony,
                    street: this.user.addressDto.street,
                    postalCode: this.user.addressDto.postalCode,
                    exteriorNumber: this.user.addressDto.exteriorNumber,
                    interiorNumber: this.user.addressDto.interiorNumber,
                    addressReference: this.user.addressDto.addressReference,
                    homeSpecification: {
                        type: this.user.addressDto.homeSpecification.type,
                        outdoorArea: this.user.addressDto.homeSpecification.outdoorArea,
                        numberOfResidents: this.user.addressDto.homeSpecification.numberOfResidents,
                        homeImage: this.user.addressDto.homeSpecification.homeImage
                    }
                });
                Swal.fire({
                    title: '¡Listo!',
                    text: 'La información de tu hogar se ha actualizado correctamente',
                    icon: 'success',
                    iconColor: '#4BB543',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    this.getProfile();
                    this.viewAddress = !this.viewAddress;
                    this.cleanAddressInputs();
                })
            } catch (error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al actualizar la información de tu hogar',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                })
            }
        },
        async updateEmail() {
            try {
                Swal.fire({
                    title: 'Actualizando...',
                    text: 'Estamos actualizando tu correo electrónico, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                const email = await encrypt(this.user.username);
                const phone = await encrypt(this.user.phoneNumber);
                await instance.put(`/person/update/personalInfo`, {
                    userId: localStorage.getItem("userId"),
                    name: this.user.name,
                    lastname: this.user.lastname,
                    surname: this.user.surname,
                    email: email,
                    phone: phone
                });
                Swal.fire({
                    title: '¡Listo!',
                    text: 'Tu correo electrónico se ha actualizado correctamente, deberás iniciar sesión nuevamente',
                    icon: 'success',
                    iconColor: '#4BB543',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    localStorage.clear();
                    this.$router.push({ name: "login" });
                })
            } catch (error) {
                if (error.response.data.message === "DUPLICATE_RECORD") {
                    Swal.fire({
                        icon: 'error',
                        title: '¡Error!',
                        text: 'Intenta con otro correo electrónico',
                        toast: true,
                        position: 'top-end',
                        timer: 3000,
                        showCancelButton: false,
                        showConfirmButton: false,
                        timerProgressBar: true,
                    });
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: '¡Error!',
                        text: 'Ocurrió un error al actualizar tu correo electrónico',
                        toast: true,
                        position: 'top-end',
                        timer: 3000,
                        showCancelButton: false,
                        showConfirmButton: false,
                        timerProgressBar: true,
                    });                    
                }
            }
        },
        handleUpdateButton(option) {
            switch(option) {
                case 1:
                    if (this.viewPersonalInfo) this.viewPersonalInfo = !this.viewPersonalInfo;
                    else this.confirmUpdatePersonalInfo();
                    break;
                case 2:
                    if (this.viewAddress) this.viewAddress = !this.viewAddress;
                    else this.confirmUpdateAddress();
                    break;
                case 3:
                    if (this.viewEmail) this.viewEmail = !this.viewEmail;
                    else this.confirmUpdateEmail();
                    break;
            }            
        },
        async getStorageRole() {
            return await decrypt(localStorage.getItem("role"));
        },
    },
    mounted() {
        if (localStorage.getItem("userId")) {
            const role = this.getStorageRole();
            if (role === "GENERAL") this.showAddress = true;
            else this.showAddress = false;
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
    height: 200px;
    object-fit: cover;
    border-radius: 10px;
}
</style>