<template>
    <b-container fluid>
        <b-row>
            <b-col cols="12" class="mt-5 px-5">
                <!-- md, lg, xl card -->
                <b-card class="full-height-card p-4 d-none d-md-block" bg-variant="gray">
                    <b-row>
                        <b-col cols="6" lg="5" xl="4">
                            <b-row>
                                <b-col cols="12">
                                    <b-img :src="pet.mainImage" class="img border-img"
                                        alt="Imagen principal de la mascota" fluid center rounded></b-img>
                                </b-col>
                                <b-col v-for="(image, index) in pet.additionalImages" :key="index" cols="3"
                                    class="mt-3">
                                    <div class="additional-img-container">
                                        <b-img :src="image" class="additional-img" alt="Imagen adicional" fluid
                                            center></b-img>
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
                                <b-col cols="12">
                                    <ul>
                                        <li class="text-dark-gray-input mt-3" v-for="(care, index) in pet.care"
                                            :key="index">{{ care }}</li>
                                    </ul>
                                </b-col>
                            </b-row>
                        </b-col>
                        <b-col cols="6" lg="7" xl="8" class="ps-3 ps-lg-5">
                            <b-row class="my-3">
                                <b-col cols="9" xl="10" class="d-flex align-items-center">
                                    <div>
                                        <h2>{{ pet.name }}</h2>
                                        <h4 class="text-dark-gray-input">{{ pet.city }}, {{ pet.state }}</h4>
                                    </div>
                                </b-col>
                                <b-col cols="3" xl="2" class="d-flex justify-content-end pe-4">
                                    <div>
                                        <b-icon v-if="pet.sex === 'M'" icon="gender-male" variant="gender-male"
                                            font-scale="3.6"></b-icon>
                                        <b-icon v-else icon="gender-female" variant="gender-female"
                                            font-scale="3.6"></b-icon>
                                        <b-card-sub-title class="mt-2" v-if="pet.sex === 'M'">Macho</b-card-sub-title>
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
                                    <p class="text-dark-gray-input">{{ pet.size }}</p>
                                </b-col>
                                <b-col cols="12" lg="6" class="d-flex">
                                    <p class="me-2">Etapa:</p>
                                    <p class="text-dark-gray-input">{{ pet.stage }}</p>
                                </b-col>
                                <b-col cols="12" lg="6" class="d-flex">
                                    <p class="me-2">Edad</p>
                                    <p class="text-dark-gray-input">{{ pet.age }}</p>
                                </b-col>
                                <b-col cols="12" lg="6" class="d-flex">
                                    <p class="me-2">Peso</p>
                                    <p class="text-dark-gray-input">{{ pet.weight }}</p>
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
                                            <b-icon v-if="pet.isVaccinated" icon="check-circle" variant="success"
                                                font-scale="1.2" class="me-4 me-xl-5"></b-icon>
                                            <b-icon v-else icon="x-circle" variant="danger" font-scale="1.2"
                                                class="me-4 me-xl-5"></b-icon>
                                        </div>
                                        <div class="d-flex justify-content-between pe-xl-5">
                                            <p>Desparasitado</p>
                                            <b-icon v-if="pet.isDewormed" icon="check-circle" variant="success"
                                                font-scale="1.2" class="me-4 me-xl-5"></b-icon>
                                            <b-icon v-else icon="x-circle" variant="danger" font-scale="1.2"
                                                class="me-4 me-xl-5"></b-icon>
                                        </div>
                                        <div class="d-flex justify-content-between pe-xl-5">
                                            <p>Esterilizado</p>
                                            <b-icon v-if="pet.isSterilised" icon="check-circle" variant="success"
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
                                        <p v-if="pet.diseases.length > 0" class="text-dark-gray-input ms-3">
                                            {{ pet.diseases.join(', ') }}
                                        </p>
                                        <p v-else class="text-dark-gray-input ms-3">Ninguna</p>
                                        <p class="mt-3">Alergias:</p>
                                        <p v-if="pet.allergies.length > 0" class="text-dark-gray-input ms-3">
                                            {{ pet.allergies.join(', ') }}
                                        </p>
                                        <p v-else class="text-dark-gray-input ms-3">Ninguna</p>
                                    </b-col>
                                </b-row>
                                <b-col cols="12">
                                    <p class="mt-3">Comentarios adicionales:</p>
                                    <p class="comment text-dark-gray-input">{{ pet.comments }}</p>
                                </b-col>
                            </b-row>
                            <hr class="divider my-0 d-none d-lg-block">
                            <b-row class="my-3 d-none d-lg-block">
                                <b-col cols="12">
                                    <h4>Hábitos y cuidados especiales</h4>
                                </b-col>
                                <b-col cols="12">
                                    <ul>
                                        <li class="text-dark-gray-input mt-3" v-for="(care, index) in pet.care"
                                            :key="index">{{ care }}</li>
                                    </ul>
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
                <b-card class="full-height-card p-4 d-block d-md-none" bg-variant="gray">
                    <b-row>
                        <b-col cols="12">
                            <b-row class="px-sm-5">
                                <b-col cols="12">
                                    <b-img :src="pet.mainImage" class="img border-img"
                                        alt="Imagen principal de la mascota" fluid center rounded></b-img>
                                </b-col>
                                <b-col v-for="(image, index) in pet.additionalImages" :key="index" cols="3"
                                    class="mt-3">
                                    <div class="additional-img-container">
                                        <b-img :src="image" class="additional-img" alt="Imagen adicional" fluid
                                            center></b-img>
                                    </div>
                                </b-col>
                            </b-row>
                            <b-row class="mt-4 mb-3">
                                <b-col cols="9" xl="10" class="d-flex align-items-center">
                                    <div>
                                        <h2>{{ pet.name }}</h2>
                                        <h4 class="text-dark-gray-input mb-0">{{ pet.city }}, {{ pet.state }}</h4>
                                    </div>
                                </b-col>
                                <b-col cols="3" xl="2" class="d-flex justify-content-end pe-4">
                                    <div>
                                        <b-icon v-if="pet.sex === 'M'" icon="gender-male" variant="gender-male"
                                            font-scale="3.6"></b-icon>
                                        <b-icon v-else icon="gender-female" variant="gender-female"
                                            font-scale="3.6"></b-icon>
                                        <b-card-sub-title class="mt-2" v-if="pet.sex === 'M'">Macho</b-card-sub-title>
                                        <b-card-sub-title class="mt-2" v-else>Hembra</b-card-sub-title>
                                    </div>
                                </b-col>
                            </b-row>
                            <hr class="divider my-0">
                            <b-row class="mt-3 g-0">
                                <b-col cols="12" sm="6" class="d-flex">
                                    <p class="me-2">Especie:</p>
                                    <p class="text-dark-gray-input">{{ pet.category }}</p>
                                </b-col>
                                <b-col cols="12" sm="6" class="d-flex">
                                    <p class="me-2">Raza:</p>
                                    <p class="text-dark-gray-input">{{ pet.breed }}</p>
                                </b-col>
                                <b-col cols="12" sm="6" class="d-flex">
                                    <p class="me-2">Tamaño:</p>
                                    <p class="text-dark-gray-input">{{ pet.size }}</p>
                                </b-col>
                                <b-col cols="12" sm="6" class="d-flex">
                                    <p class="me-2">Etapa:</p>
                                    <p class="text-dark-gray-input">{{ pet.stage }}</p>
                                </b-col>
                                <b-col cols="12" sm="6" class="d-flex">
                                    <p class="me-2">Edad</p>
                                    <p class="text-dark-gray-input">{{ pet.age }}</p>
                                </b-col>
                                <b-col cols="12" sm="6" class="d-flex">
                                    <p class="me-2">Peso</p>
                                    <p class="text-dark-gray-input">{{ pet.weight }}</p>
                                </b-col>
                            </b-row>
                            <hr class="divider my-0">
                            <b-row class="mt-3">
                                <b-col cols="12">
                                    <h4 class="mb-4">Información médica</h4>
                                </b-col>
                                <b-row>
                                    <b-col cols="12" sm="6">
                                        <div class="d-flex justify-content-between">
                                            <p>Vacunado</p>
                                            <b-icon v-if="pet.isVaccinated" icon="check-circle" variant="success"
                                                font-scale="1.2" class="me-4 me-xl-5"></b-icon>
                                            <b-icon v-else icon="x-circle" variant="danger" font-scale="1.2"
                                                class="me-4 me-xl-5"></b-icon>
                                        </div>
                                        <div class="d-flex justify-content-between pe-xl-5">
                                            <p>Desparasitado</p>
                                            <b-icon v-if="pet.isDewormed" icon="check-circle" variant="success"
                                                font-scale="1.2" class="me-4 me-xl-5"></b-icon>
                                            <b-icon v-else icon="x-circle" variant="danger" font-scale="1.2"
                                                class="me-4 me-xl-5"></b-icon>
                                        </div>
                                        <div class="d-flex justify-content-between pe-xl-5">
                                            <p>Esterilizado</p>
                                            <b-icon v-if="pet.isSterilised" icon="check-circle" variant="success"
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
                                    <b-col cols="12" sm="6">
                                        <p class="mt-4 mt-sm-0">Enfermedades:</p>
                                        <p v-if="pet.diseases.length > 0" class="text-dark-gray-input ms-3">
                                            {{ pet.diseases.join(', ') }}
                                        </p>
                                        <p v-else class="text-dark-gray-input ms-3">Ninguna</p>
                                        <p class="mt-3">Alergias:</p>
                                        <p v-if="pet.allergies.length > 0" class="text-dark-gray-input ms-3">
                                            {{ pet.allergies.join(', ') }}
                                        </p>
                                        <p v-else class="text-dark-gray-input ms-3">Ninguna</p>
                                    </b-col>
                                </b-row>
                                <b-col cols="12">
                                    <p class="mt-3">Comentarios adicionales:</p>
                                    <p class="comment text-dark-gray-input">{{ pet.comments }}</p>
                                </b-col>
                            </b-row>
                            <hr class="divider my-0">
                            <b-row class="my-3">
                                <b-col cols="12">
                                    <h4>Hábitos y cuidados especiales</h4>
                                </b-col>
                                <b-col cols="12">
                                    <ul>
                                        <li class="text-dark-gray-input mt-3" v-for="(care, index) in pet.care"
                                            :key="index">{{ care }}</li>
                                    </ul>
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
                                    <p class="comment text-dark-gray-input">{{ pet.description }}</p>
                                </b-col>
                            </b-row>
                        </b-col>
                    </b-row>
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
export default {
    data() {
        return {
            pet: {
                mainImage: 'https://i.pinimg.com/236x/28/bb/ce/28bbce5c0fa2e6bc2b786937be1c2ed9.jpg',
                additionalImages: [
                    'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtdFCuqK3UhNKl1A7YbogPffipGf2bR0DmyWG03Rhx24HnDHJl',
                    'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTZPAVHDCzHATFVORtAnf0roPACfbl1N37jTexERTs8tj7M3JXV',
                    'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRwjes3zWjaJV15Junz6ZPmzxFmcYpllrodcKdSpatr71vpyw52',
                    'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSCEM8KenUzZ_081jNyum-ie9NQp62MjBlvkuj9fx-M61zXVZ-v',
                ],
                name: 'Bolita Santos',
                sex: 'H',
                state: 'Morelos',
                city: 'Cuernavaca',
                category: 'Gato',
                breed: 'Siames',
                size: 'Pequeño',
                stage: 'Cachorro',
                age: '11 meses',
                weight: '3.5 kg',
                isVaccinated: true,
                isDewormed: false,
                isSterilised: false,
                microchip: true,
                diseases: ['Leucemia Felina', 'Rabia'], 
                /* diseases: [], */
                /* allergies: ['Polvo', 'Ácaros'], */
                allergies: [],
                comments: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                care: [
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                    "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                    "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
                ],
                characteristics: [
                    "Travieso", "Limpio", "Cariñoso", "Juguetón", "Mimoso", "Guardián"
                ],
                description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
            }
        }
    },
    methods: {
        goBack() {
            this.$router.go(-1);
        }
    }
}
</script>

<style scoped>
.full-height-card {
    border-bottom-left-radius: 0 !important;
    border-bottom-right-radius: 0 !important;
}

.img {
    width: 360px;
    height: 280px;
    object-fit: cover;
    border: 2px solid #000;
}

.additional-img-container {
    width: 100%;
    padding-top: 100%;
    position: relative;
    overflow: hidden;
    border: 2px solid black;
    border-radius: 5px;
}

.additional-img {
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;
    object-fit: cover;
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