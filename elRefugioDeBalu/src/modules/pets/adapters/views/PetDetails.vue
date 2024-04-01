<template>
    <b-container fluid>
        <b-row>
            <b-col cols="12" class="mt-5 px-5">
                <!-- md, lg, xl card -->
                <b-card class="full-height-card p-4 d-none d-md-block card-shadow" bg-variant="gray">
                    <b-row>
                        <b-col cols="6" lg="5" xl="4">
                            <b-row>
                                <b-col cols="12">
                                    <b-img :src="pet.mainImage" class="img border-img img-shadow"
                                        alt="Imagen principal de la mascota" fluid center rounded></b-img>
                                </b-col>
                                <b-col v-for="(image, index) in pet.images" :key="index" cols="3" class="mt-3">
                                    <div class="additional-img-container img-shadow">
                                        <b-img :src="image" class="additional-img" alt="Imagen adicional" fluid center
                                            @click="swapImage(image)"></b-img>
                                    </div>
                                </b-col>
                            </b-row>
                            <b-row class="mb-3">
                                <b-col cols="12" class="mt-5 mb-2">
                                    <h4>Características</h4>
                                </b-col>
                                <b-col cols="12">
                                    <b-badge v-for="(characteristic, index) in pet.characteristics" :key="index"
                                        variant="info-outline" class="me-2 mb-2">{{ characteristic }}</b-badge>
                                </b-col>
                            </b-row>
                            <hr class="divider my-0">
                            <b-row>
                                <b-col cols="12" class="mt-4 mb-2">
                                    <h4>Descripción</h4>
                                    <p class="comment text-dark-gray-input">{{ pet.description }}</p>
                                </b-col>
                            </b-row>
                            <hr class="divider my-0 d-block d-lg-none">
                            <b-row class="my-3 d-block d-lg-none">
                                <b-col cols="12">
                                    <h4>Hábitos y cuidados especiales</h4>
                                </b-col>
                                <b-col cols="12" v-if="pet.specialCares">
                                    <ul>
                                        <li class="text-dark-gray-input mt-3" v-for="(care, index) in pet.specialCares"
                                            :key="index">{{ care }}</li>
                                    </ul>
                                </b-col>
                                <b-col cols="12" v-else>
                                    <p class="text-dark-gray-input ms-3">Sin especificar</p>
                                </b-col>
                            </b-row>
                        </b-col>
                        <b-col cols="6" lg="7" xl="8" class="ps-3 ps-lg-5">
                            <b-row class="my-3">
                                <b-col cols="9" xl="10" class="d-flex align-items-center">
                                    <div>
                                        <h2>{{ pet.name }}</h2>
                                        <h4 class="text-dark-gray-input">{{ pet.location }}</h4>
                                    </div>
                                </b-col>
                                <b-col cols="3" xl="2" class="d-flex justify-content-end pe-4">
                                    <div>
                                        <b-icon v-if="pet.gender === 'MALE'" icon="gender-male" variant="gender-male"
                                            font-scale="3.6"></b-icon>
                                        <b-icon v-else icon="gender-female" variant="gender-female"
                                            font-scale="3.6"></b-icon>
                                        <b-card-sub-title class="mt-2"
                                            v-if="pet.gender === 'MALE'">Macho</b-card-sub-title>
                                        <b-card-sub-title class="mt-2" v-else>Hembra</b-card-sub-title>
                                    </div>
                                </b-col>
                            </b-row>
                            <hr class="divider my-0">
                            <b-row class="mt-3 g-0">
                                <b-col cols="12" lg="6" class="d-flex">
                                    <p class="me-2">Especie:</p>
                                    <p class="text-dark-gray-input">{{ pet.category }}</p>
                                </b-col>
                                <b-col cols="12" lg="6" class="d-flex">
                                    <p class="me-2">Raza:</p>
                                    <p class="text-dark-gray-input">{{ pet.breed }}</p>
                                </b-col>
                                <b-col cols="12" lg="6" class="d-flex">
                                    <p class="me-2">Tamaño:</p>
                                    <p class="text-dark-gray-input">{{ mapSize((pet.size).toString().toLowerCase()) }}
                                    </p>
                                </b-col>
                                <b-col cols="12" lg="6" class="d-flex">
                                    <p class="me-2">Etapa:</p>
                                    <p class="text-dark-gray-input">{{
                                        mapLifeStage((pet.lifeStage).toString().toLowerCase()) }}</p>
                                </b-col>
                                <b-col cols="12" lg="6" class="d-flex">
                                    <p class="me-2">Edad</p>
                                    <p class="text-dark-gray-input">{{ pet.age }} {{
                                        mapAgeUnit((pet.ageUnit).toString().toLowerCase()) }}</p>
                                </b-col>
                                <b-col cols="12" lg="6" class="d-flex">
                                    <p class="me-2">Peso</p>
                                    <p class="text-dark-gray-input">{{ pet.weight }} {{
                                        mapWeightUnit((pet.weightUnit).toString().toLowerCase()) }}</p>
                                </b-col>
                            </b-row>
                            <hr class="divider my-0">
                            <b-row class="mt-3">
                                <b-col cols="12">
                                    <h4 class="mb-4">Información médica</h4>
                                </b-col>
                                <b-row>
                                    <b-col cols="12" lg="6">
                                        <div class="d-flex justify-content-between pe-xl-5">
                                            <p>Vacunado</p>
                                            <b-icon v-if="pet.vaccinated" icon="check-circle" variant="success"
                                                font-scale="1.2" class="me-4 me-xl-5"></b-icon>
                                            <b-icon v-else icon="x-circle" variant="danger" font-scale="1.2"
                                                class="me-4 me-xl-5"></b-icon>
                                        </div>
                                        <div class="d-flex justify-content-between pe-xl-5">
                                            <p>Desparasitado</p>
                                            <b-icon v-if="pet.dewormed" icon="check-circle" variant="success"
                                                font-scale="1.2" class="me-4 me-xl-5"></b-icon>
                                            <b-icon v-else icon="x-circle" variant="danger" font-scale="1.2"
                                                class="me-4 me-xl-5"></b-icon>
                                        </div>
                                        <div class="d-flex justify-content-between pe-xl-5">
                                            <p>Esterilizado</p>
                                            <b-icon v-if="pet.sterilised" icon="check-circle" variant="success"
                                                font-scale="1.2" class="me-4 me-xl-5"></b-icon>
                                            <b-icon v-else icon="x-circle" variant="danger" font-scale="1.2"
                                                class="me-4 me-xl-5"></b-icon>
                                        </div>
                                        <div class="d-flex justify-content-between pe-xl-5">
                                            <p>Con microchip</p>
                                            <b-icon v-if="pet.microchip" icon="check-circle" variant="success"
                                                font-scale="1.2" class="me-4 me-xl-5"></b-icon>
                                            <b-icon v-else icon="x-circle" variant="danger" font-scale="1.2"
                                                class="me-4 me-xl-5"></b-icon>
                                        </div>
                                    </b-col>
                                    <b-col cols="12" lg="6">
                                        <p class="mt-md-4 mt-lg-0">Enfermedades:</p>
                                        <p v-if="pet.diseases" class="text-dark-gray-input ms-3">
                                            {{ pet.diseases.join(', ') }}
                                        </p>
                                        <p v-else class="text-dark-gray-input ms-3">Ninguna</p>
                                        <p class="mt-3">Alergias:</p>
                                        <p v-if="pet.allergies" class="text-dark-gray-input ms-3">
                                            {{ pet.allergies.join(', ') }}
                                        </p>
                                        <p v-else class="text-dark-gray-input ms-3">Ninguna</p>
                                    </b-col>
                                </b-row>
                                <b-col cols="12" v-show="pet.observations">
                                    <p class="mt-3">Comentarios adicionales:</p>
                                    <p class="comment text-dark-gray-input">{{ pet.observations }}</p>
                                </b-col>
                            </b-row>
                            <hr class="divider my-0 d-none d-lg-block">
                            <b-row class="my-3 d-none d-lg-block">
                                <b-col cols="12">
                                    <h4>Hábitos y cuidados especiales</h4>
                                </b-col>
                                <b-col cols="12" v-if="pet.specialCares">
                                    <ul>
                                        <li class="text-dark-gray-input mt-3" v-for="(care, index) in pet.specialCares"
                                            :key="index">{{ care }}</li>
                                    </ul>
                                </b-col>
                                <b-col cols="12" v-else>
                                    <p class="text-dark-gray-input ms-3">Sin especificar</p>
                                </b-col>
                            </b-row>
                        </b-col>
                    </b-row>
                    <hr class="divider my-0">
                    <b-row class="mt-4 d-flex justify-content-end">
                        <b-col cols="5" lg="4" xl="3">
                            <b-button type="submit" variant="outline-dark-secondary-blue" to="/formAdoption"
                                class="form-btn me-3 d-flex align-items-center justify-content-between w-100">
                                <span class="me-2">Solicitar adoptar</span>
                                <b-icon icon="heart" font-scale="1.3"></b-icon>
                            </b-button>
                        </b-col>
                        <b-col cols="5" lg="4" xl="3">
                            <b-button variant="outline-danger" @click="goBack"
                                class="form-btn d-flex align-items-center justify-content-between w-100">
                                <span class="me-2">Regresar</span>
                                <b-icon icon="arrow-left-circle" font-scale="1.3"></b-icon>
                            </b-button>
                        </b-col>
                    </b-row>
                </b-card>
                <!-- xs, sm card -->
                <b-card class="full-height-card p-4 d-block d-md-none card-shadow" bg-variant="gray">
                    <SmallContent :pet="pet" />
                    <hr class="divider my-0">
                    <b-row class="mt-4 d-flex justify-content-end">
                        <b-col cols="12" sm="6">
                            <b-button type="submit" variant="outline-dark-secondary-blue" to="/formAdoption"
                                class="me-3 d-flex align-items-center justify-content-between w-100">
                                <span class="me-2">Solicitar adoptar</span>
                                <b-icon icon="heart" font-scale="1.3"></b-icon>
                            </b-button>
                        </b-col>
                        <b-col cols="12" sm="6" class="mt-3 mt-sm-0">
                            <b-button variant="outline-danger" @click="goBack"
                                class="d-flex align-items-center justify-content-between w-100">
                                <span class="me-2">Regresar</span>
                                <b-icon icon="arrow-left-circle" font-scale="1.3"></b-icon>
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
import instance from "../../../../config/axios";

import SmallContent from "../components/PetSmallCardContent.vue"
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import { sizes, lifeStages, weightUnits, ageUnits } from "../../../../kernel/data/mappingDictionaries";

export default {
    props: {
        petId: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            pet: {}
        }
    },
    methods: {
        mapSize(size) {
            return sizes[size] || size;
        },
        mapLifeStage(lifeStage) {
            return lifeStages[lifeStage] || lifeStage;
        },
        mapAgeUnit(unit) {
            return ageUnits[unit] || unit;
        },
        mapWeightUnit(unit) {
            return weightUnits[unit] || unit;
        },
        swapImage(selectedImage) {
            let mainImage = this.pet.mainImage;
            this.pet.mainImage = selectedImage;

            const index = this.pet.images.findIndex(image => image === selectedImage);
            if (index !== -1) {
                this.pet.images.splice(index, 1, mainImage);
            } else {
                this.pet.images.push(mainImage);
            }
        },
        async getDetails() {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Estamos cargando los detalles de la mascota, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                const response = await instance.post(`/pet/details`, { id: this.petId });
                this.pet = response.data.data;
                Swal.close();
            } catch (error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al cargar los detalles de la mascota',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    this.$router.push('/pets')
                })
            }
        },
        goBack() {
            this.$router.go(-1);
        }
    },
    mounted() {
        if (!this.petId && localStorage.getItem('petId')) this.petId = localStorage.getItem('petId');
        this.getDetails();
    },
    components: {
        SmallContent
    }
}
</script>

<style scoped>
.full-height-card {
    border-bottom-left-radius: 0 !important;
    border-bottom-right-radius: 0 !important;
}

.card-shadow {
    box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.089);
}

.divider {
    border-width: 2.8px;
}

p {
    font-size: 1.2rem;
}

p.comment {
    font-size: 1rem;
}

li {
    font-size: 1rem;
}

.form-btn {
    width: 20%;
}
</style>