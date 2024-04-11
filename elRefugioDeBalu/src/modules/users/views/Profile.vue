<template>
    <b-container fluid>
        <b-row class="px-5 pb-5 pt-4">
            <b-col cols="4">
                <b-card bg-variant="orange" class="py-2 form-card-shadow relative-position form-card-title"
                    no-body>
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
                                <b-form-input id="name" v-model="user.name" :readonly="view"></b-form-input>
                            </b-form-group>
                        </b-col>
                        <b-col cols="12" class="mt-3">
                            <b-form-group>
                                <label for="lastname" class="mb-2">Apellido paterno:</label>
                                <b-form-input id="lastname" v-model="user.lastname" :readonly="view"></b-form-input>
                            </b-form-group>
                        </b-col>
                        <b-col cols="12" class="mt-3">
                            <b-form-group>
                                <label for="surname" class="mb-2">Apellido materno:</label>
                                <b-form-input id="surname" v-model="user.surname" :readonly="view"></b-form-input>
                            </b-form-group>
                        </b-col>
                        <b-col cols="12" class="mt-3">
                            <b-form-group>
                                <label for="username" class="mb-2">Correo electrónico:</label>
                                <b-form-input id="username" v-model="user.username" :readonly="view"></b-form-input>
                            </b-form-group>
                        </b-col>
                        <b-col cols="12" class="mt-3">
                            <b-form-group>
                                <label for="phoneNumber" class="mb-2">Número de teléfono:</label>
                                <b-form-input id="phoneNumber" v-model="user.phoneNumber" :readonly="view"></b-form-input>
                            </b-form-group>
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
                                        <b-form-input id="country" v-model="user.addressDto.country" :readonly="view"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="12" class="mt-3">
                                    <b-form-group>
                                        <label for="state" class="mb-2">Estado:</label>
                                        <b-form-input id="state" v-model="user.addressDto.state" :readonly="view"></b-form-input>
                                    </b-form-group>
                                </b-col>
                            </b-row>
                        </b-col>
                        <b-col cols="12">
                            <b-row>
                                <b-col cols="4" class="mt-3">
                                    <b-form-group>
                                        <label for="city" class="mb-2">Ciudad:</label>
                                        <b-form-input id="city" v-model="user.addressDto.city" :readonly="view"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="4" class="mt-3">
                                    <b-form-group>
                                        <label for="colony" class="mb-2">Colonia:</label>
                                        <b-form-input id="colony" v-model="user.addressDto.colony" :readonly="view"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="4" class="mt-3">
                                    <b-form-group>
                                        <label for="street" class="mb-2">Calle:</label>
                                        <b-form-input id="street" v-model="user.addressDto.street" :readonly="view"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="3" class="mt-3">
                                    <b-form-group>
                                        <label for="externalNumber" class="mb-2">Número exterior:</label>
                                        <b-form-input id="externalNumber"
                                            v-model="user.addressDto.exteriorNumber" :readonly="view"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="3" class="mt-3">
                                    <b-form-group>
                                        <label for="internalNumber" class="mb-2">Número interior:</label>
                                        <b-form-input id="internalNumber"
                                            v-model="user.addressDto.interiorNumber" :readonly="view"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="6" class="mt-3">
                                    <b-form-group>
                                        <label for="postalCode" class="mb-2">Código postal:</label>
                                        <b-form-input id="postalCode"
                                            v-model="user.addressDto.postalCode" :readonly="view"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="12" class="mt-3">
                                    <b-form-group>
                                        <label for="addressReference" class="mb-2">Referencia de la
                                            dirección:</label>
                                        <b-form-textarea id="addressReference"
                                            v-model="user.addressDto.addressReference" rows="3" :readonly="view"></b-form-textarea>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="4" class="mt-3">
                                    <b-form-group>
                                        <label for="homeType" class="mb-2">Tipo de hogar:</label>
                                        <b-form-input id="homeType"
                                            v-model="user.addressDto.homeSpecification.type" :readonly="view"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="4" class="mt-3">
                                    <b-form-group>
                                        <label for="numberOfResidents" class="mb-2">Número de
                                            residentes:</label>
                                        <b-form-input id="numberOfResidents"
                                            v-model="user.addressDto.homeSpecification.numberOfResidents" :readonly="view"></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="4" class="mt-3">
                                    <b-form-group>
                                        <label for="outdoorArea" class="mb-2">¿Tienes área exterior?</label>
                                        <b-form-select id="outdoorArea" class="form-select"
                                            v-model="user.addressDto.homeSpecification.outdoorArea" :disabled="view">
                                            <b-form-select-option value="true">Sí</b-form-select-option>
                                            <b-form-select-option value="false">No</b-form-select-option>
                                        </b-form-select>
                                    </b-form-group>
                                </b-col>
                            </b-row>
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

export default {
    data() {
        return {
            view: true,
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
    },
    mounted() {
        if (localStorage.getItem("userId")) {
            this.user.userId = localStorage.getItem("userId");
            console.log(localStorage.getItem("userId"))
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