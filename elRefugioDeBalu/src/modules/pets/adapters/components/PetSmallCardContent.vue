<template>
    <b-container>
        <b-row>
            <b-col cols="12">
                <b-row class="px-sm-5">
                    <b-col cols="12" @dragover.prevent @drop="dropImage">
                        <b-img :src="pet.mainImage" class="pet-main-img" alt="Imagen principal de la mascota" fluid
                            center rounded></b-img>
                    </b-col>
                    <b-col cols="12">
                        <b-row class="mt-4">
                            <b-col v-for="(image, index) in pet.images" :key="index" cols="3" class="d-flex justify-content-center">
                                <div class="pet-additional-img-container">
                                    <b-img :src="image" class="pet-additional-img" alt="Imagen adicional" fluid center
                                        draggable="true" @dragstart="dragStart(image)"></b-img>
                                </div>
                            </b-col>
                            <b-col cols="12" class="d-flex justify-content-center mt-4">
                                <div class="d-flex align-items-center">
                                    <b-icon icon="info-circle" variant="dark" font-scale="1.2" class="me-4"></b-icon>
                                    <small class="text-dark">
                                        Arrastra las imágenes para visualizarlas en la parte principal
                                    </small>
                                </div>
                            </b-col>
                        </b-row>
                    </b-col>
                </b-row>
                <b-row class="mt-4 mb-3">
                    <b-col cols="9" xl="10" class="d-flex align-items-center">
                        <div>
                            <h2>{{ pet.name }}</h2>
                            <h4 class="text-dark-gray-input mb-0">{{ pet.location }}</h4>
                        </div>
                    </b-col>
                    <b-col cols="3" xl="2" class="d-flex justify-content-end pe-4">
                        <div>
                            <b-icon v-if="pet.gender === 'MALE'" icon="gender-male" variant="gender-male"
                                font-scale="3.6"></b-icon>
                            <b-icon v-else icon="gender-female" variant="gender-female" font-scale="3.6"></b-icon>
                            <b-card-sub-title class="mt-2" v-if="pet.gender === 'MALE'">Macho</b-card-sub-title>
                            <b-card-sub-title class="mt-2" v-else>Hembra</b-card-sub-title>
                        </div>
                    </b-col>
                </b-row>
                <hr class="divider my-0">
                <b-row class="mt-3 g-0">
                    <b-col cols="12" sm="6" class="d-flex">
                        <p class="me-2 pet-p">Especie:</p>
                        <p class="text-dark-gray-input pet-p">{{ pet.category }}</p>
                    </b-col>
                    <b-col cols="12" sm="6" class="d-flex">
                        <p class="me-2 pet-p">Raza:</p>
                        <p class="text-dark-gray-input pet-p">{{ pet.breed }}</p>
                    </b-col>
                    <b-col cols="12" sm="6" class="d-flex">
                        <p class="me-2 pet-p">Tamaño:</p>
                        <p class="text-dark-gray-input pet-p">{{ mapSize((pet.size)) }}
                        </p>
                    </b-col>
                    <b-col cols="12" sm="6" class="d-flex">
                        <p class="me-2 pet-p">Etapa:</p>
                        <p class="text-dark-gray-input pet-p">{{
                            mapLifeStage((pet.lifeStage)) }}</p>
                    </b-col>
                    <b-col cols="12" sm="6" class="d-flex">
                        <p class="me-2 pet-p">Edad</p>
                        <p class="text-dark-gray-input pet-p">{{ pet.age }} {{
                            mapAgeUnit((pet.ageUnit)) }}</p>
                    </b-col>
                    <b-col cols="12" sm="6" class="d-flex">
                        <p class="me-2 pet-p">Peso</p>
                        <p class="text-dark-gray-input pet-p">{{ pet.weight }} {{
                            mapWeightUnit((pet.weightUnit)) }}</p>
                    </b-col>
                </b-row>
                <hr class="divider my-0">
                <b-row class="mt-3">
                    <b-col cols="12">
                        <h4 class="mb-4">Información médica</h4>
                    </b-col>
                    <b-row>
                        <b-col cols="12" sm="6">
                            <div class="d-flex justify-content-between align-items-center mb-2">
                                <span class="pet-p">Vacunado</span>
                                <b-icon v-if="pet.vaccinated" icon="check-circle" variant="success" font-scale="1.2"
                                    class=""></b-icon>
                                <b-icon v-else icon="x-circle" variant="danger" font-scale="1.2" class=""></b-icon>
                            </div>
                            <div class="d-flex justify-content-between align-items-center my-2">
                                <span class="pet-p">Desparasitado</span>
                                <b-icon v-if="pet.dewormed" icon="check-circle" variant="success" font-scale="1.2"
                                    class=""></b-icon>
                                <b-icon v-else icon="x-circle" variant="danger" font-scale="1.2" class=""></b-icon>
                            </div>
                            <div class="d-flex justify-content-between align-items-center my-2">
                                <span class="pet-p">Esterilizado</span>
                                <b-icon v-if="pet.sterilised" icon="check-circle" variant="success" font-scale="1.2"
                                    class=""></b-icon>
                                <b-icon v-else icon="x-circle" variant="danger" font-scale="1.2" class=""></b-icon>
                            </div>
                            <div class="d-flex justify-content-between align-items-center mt-2">
                                <span class="pet-p">Con microchip</span>
                                <b-icon v-if="pet.microchip" icon="check-circle" variant="success" font-scale="1.2"
                                    class=""></b-icon>
                                <b-icon v-else icon="x-circle" variant="danger" font-scale="1.2" class=""></b-icon>
                            </div>
                        </b-col>
                        <b-col cols="12" sm="6">
                            <p class="mt-4 mt-sm-0 pet-p">Enfermedades:</p>
                            <p v-if="pet.diseases" class="text-dark-gray-input ms-3 pet-p">
                                {{ pet.diseases.join(', ') }}
                            </p>
                            <p v-else class="text-dark-gray-input ms-3 pet-p">Ninguna</p>
                            <p class="mt-3 pet-p">Alergias:</p>
                            <p v-if="pet.allergies" class="text-dark-gray-input ms-3 pet-p">
                                {{ pet.allergies.join(', ') }}
                            </p>
                            <p v-else class="text-dark-gray-input ms-3 pet-p">Ninguna</p>
                        </b-col>
                    </b-row>
                    <b-col cols="12" v-show="pet.observations">
                        <p class="mt-3 pet-p">Comentarios adicionales:</p>
                        <p class="pet-comment text-dark-gray-input">{{ pet.observations }}</p>
                    </b-col>
                </b-row>
                <hr class="divider my-0">
                <b-row class="my-3">
                    <b-col cols="12">
                        <h4>Hábitos y cuidados especiales</h4>
                    </b-col>
                    <b-col cols="12" v-if="pet.specialCares">
                        <ul>
                            <li class="text-dark-gray-input mt-3 p-comment" v-for="(care, index) in pet.specialCares"
                                :key="index">{{ care }}</li>
                        </ul>
                    </b-col>
                    <b-col cols="12" v-else>
                        <p class="text-dark-gray-input ms-3 p-comment">Sin especificar</p>
                    </b-col>
                </b-row>
                <hr class="divider my-0">
                <b-row class="mb-3">
                    <b-col cols="12" class="mt-3">
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
                        <p class="pet-comment text-dark-gray-input">{{ pet.description }}</p>
                    </b-col>
                </b-row>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
import { sizes, lifeStages, weightUnits, ageUnits } from "../../../../kernel/data/mappingDictionaries";

export default {
    props: {
        pet: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            draggedImage: null
        }
    },
    methods: {
        dragStart(image) {
            this.draggedImage = image;
        },
        dropImage() {
            if (this.draggedImage) {
                this.swapImage(this.draggedImage);
                this.draggedImage = null;
            }
        },
        mapSize(size) {
            size = String(size).toLowerCase();
            return sizes[size] || size;
        },
        mapLifeStage(lifeStage) {
            lifeStage = String(lifeStage).toLowerCase();
            return lifeStages[lifeStage] || lifeStage;
        },
        mapAgeUnit(unit) {
            unit = String(unit).toLowerCase();
            return ageUnits[unit] || unit;
        },
        mapWeightUnit(unit) {
            unit = String(unit).toLowerCase();
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
    }
}
</script>