<template>
    <b-container fluid>
        <b-row>
            <b-col cols="12" class="mt-5 px-5">
                <b-card class="full-height-card p-4" bg-variant="gray">
                    <b-row>
                        <b-col cols="4">
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
                        </b-col>
                        <b-col cols="8" class="ps-5">
                            <b-row class="my-3">
                                <b-col cols="10" class="d-flex align-items-center">
                                    <div>
                                        <h2>{{ pet.name }}</h2>
                                        <h4 class="text-dark-gray-input">{{ pet.city }}, {{ pet.state }}</h4>
                                    </div>
                                </b-col>
                                <b-col cols="2">
                                    <b-icon v-if="pet.sex === 'M'" icon="gender-male" variant="gender-male"
                                        font-scale="3.6"></b-icon>
                                    <b-icon v-else icon="gender-female" variant="gender-female"
                                        font-scale="3.6"></b-icon>
                                    <b-card-sub-title class="mt-2" v-if="pet.sex === 'M'">Macho</b-card-sub-title>
                                    <b-card-sub-title class="mt-2" v-else>Hembra</b-card-sub-title>
                                </b-col>
                            </b-row>
                            <hr class="divider my-0">
                            <b-row class="mt-3 g-0">
                                <b-col cols="6" class="d-flex">
                                    <p class="me-2">Especie:</p>
                                    <p class="text-dark-gray-input">{{ pet.category }}</p>
                                </b-col>
                                <b-col cols="6" class="d-flex">
                                    <p class="me-2">Raza:</p>
                                    <p class="text-dark-gray-input">{{ pet.breed }}</p>
                                </b-col>
                                <b-col cols="6" class="d-flex">
                                    <p class="me-2">Tamaño:</p>
                                    <p class="text-dark-gray-input">{{ pet.size }}</p>
                                </b-col>
                                <b-col cols="6" class="d-flex">
                                    <p class="me-2">Etapa:</p>
                                    <p class="text-dark-gray-input">{{ pet.stage }}</p>
                                </b-col>
                                <b-col cols="6" class="d-flex">
                                    <p class="me-2">Edad</p>
                                    <p class="text-dark-gray-input">{{ pet.age }}</p>
                                </b-col>
                                <b-col cols="6" class="d-flex">
                                    <p class="me-2">Peso</p>
                                    <p class="text-dark-gray-input">{{ pet.weight }}</p>
                                </b-col>
                            </b-row>
                            <hr class="divider my-0">
                            <b-row class="mt-3">
                                <b-col cols="12">
                                    <h4 class="mb-3">Información médica</h4>
                                </b-col>
                                <b-col cols="6" class="d-flex justify-content-between pe-5 mb-0">
                                    <p>Vacunado</p>
                                    <b-icon v-if="pet.isVaccinated" icon="check-circle" variant="success"
                                        font-scale="1.4"></b-icon>
                                    <b-icon v-else icon="x-circle" variant="danger" font-scale="1.4"></b-icon>
                                </b-col>
                                <b-col cols="6" class="d-flex justify-content-between pe-5 mb-0">
                                    <p>Desparasitado</p>
                                    <b-icon v-if="pet.isDewormed" icon="check-circle" variant="success"
                                        font-scale="1.4"></b-icon>
                                    <b-icon v-else icon="x-circle" variant="danger" font-scale="1.4"></b-icon>
                                </b-col>
                                <b-col cols="6" class="d-flex justify-content-between pe-5 mb-0">
                                    <p>Esterilizado</p>
                                    <b-icon v-if="pet.isSterilised" icon="check-circle" variant="success"
                                        font-scale="1.4"></b-icon>
                                    <b-icon v-else icon="x-circle" variant="danger" font-scale="1.4"></b-icon>
                                </b-col>
                                <b-col cols="6" class="d-flex justify-content-between pe-5 mb-0">
                                    <p>Con microchip</p>
                                    <b-icon v-if="pet.microchip" icon="check-circle" variant="success"
                                        font-scale="1.4"></b-icon>
                                    <b-icon v-else icon="x-circle" variant="danger" font-scale="1.4"></b-icon>
                                </b-col>
                                <b-col cols="6">
                                    <p class="mt-3">Enfermedades:</p>
                                    <ul v-if="pet.diseases.length > 0">
                                        <li class="text-dark-gray-input" v-for="(disease, index) in pet.diseases" :key="index">{{ disease }}</li>
                                    </ul>
                                    <p v-else class="text-dark-gray-input mt-3 ms-3">Ninguna</p>
                                </b-col>
                                <b-col cols="6">
                                    <p class="mt-3">Alergias:</p>
                                    <ul v-if="pet.allergies.length > 0">
                                        <li class="text-dark-gray-input" v-for="(allergy, index) in pet.allergies" :key="index">{{ allergy }}</li>
                                    </ul>
                                    <p v-else class="text-dark-gray-input mt-3 ms-3">Ninguna</p>
                                </b-col>
                                <b-col cols="12">
                                    <p class="mt-3">Comentarios adicionales:</p>
                                    <p class="comment text-dark-gray-input">{{ pet.comments }}</p>
                                </b-col>
                            </b-row>
                            <hr class="divider my-0">
                            <b-row class="my-3"></b-row>
                            <hr class="divider my-0">
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
                allergies: ['Polvo', 'Ácaros'],
                /* allergies: [], */
                comments: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            }
        }
    },
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

li {
    font-size: 1.2rem;
}

p.comment {
    font-size: 1rem;
}
</style>