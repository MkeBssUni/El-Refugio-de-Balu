<template>
    <b-container fluid>
        <b-row class="px-5 my-5">
            <b-col cols="12">
                <b-card bg-variant="orange" class="px-3">
                    <b-row>
                        <b-col cols="3" class="d-none d-xl-flex align-items-center text-start">
                            <div>
                                <h2 class="text-dark-secondary-blue">¡ADOPTA CON NOSOTROS!</h2>
                                <span style="font-size: 1.3rem;">Encuentra al nuevo intregrante de tu
                                    familia</span>
                            </div>
                        </b-col>
                        <b-col cols="12" md="7" lg="8" xl="6" class="d-flex align-items-center my-2">
                            <b-form>
                                <b-row>
                                    <b-col cols="12">
                                        <span>Filtrar búsqueda por...</span>
                                    </b-col>
                                    <b-col cols="12" sm="6" class="mt-2">
                                        <b-form-select v-model="payload.category" class="form-select">
                                            <option value="">Todas las categorías</option>
                                            <option v-show="!categories" value="" disabled>No hay categorías disponibles</option>
                                            <option v-for="category in categories" :key="category.id"
                                                :value="category.id">
                                                {{ category.name }}
                                            </option>
                                        </b-form-select>
                                    </b-col>
                                    <b-col cols="12" sm="6" class="mt-3 mt-sm-2">
                                        <b-form-select v-model="payload.size" class="form-select">
                                            <option value="">Todos los tamaños</option>
                                            <option v-for="size in sizes" :key="size.key" :value="size.value">
                                                {{ size.text }}
                                            </option>
                                        </b-form-select>
                                    </b-col>
                                    <b-col cols="12" sm="6" class="mt-3">
                                        <b-form-select v-model="payload.lifeStage" class="form-select">
                                            <option value="">Todas las edades</option>
                                            <option v-for="lifeStage in lifeStages" :key="lifeStage.key"
                                                :value="lifeStage.value">
                                                {{ lifeStage.text }}
                                            </option>
                                        </b-form-select>
                                    </b-col>
                                    <b-col cols="12" sm="6" class="mt-3">
                                        <b-form-select v-model="payload.gender" class="form-select">
                                            <option value="">Todos los géneros</option>
                                            <option v-for="gender in genders" :key="gender.key" :value="gender.value">
                                                {{ gender.text }}
                                            </option>
                                        </b-form-select>
                                    </b-col>
                                    <b-col cols="12" class="mt-3">
                                        <b-form-select v-model="payload.location" class="form-select">
                                            <option v-for="state in states" :key="state.value" :value="state.value">
                                                {{ state.value }}
                                            </option>
                                            <option value="">Todos los estados</option>
                                        </b-form-select>
                                    </b-col>
                                </b-row>
                                <b-row>
                                    <b-col cols="6" class="mt-3">
                                        <b-button variant="outline-danger"
                                            class="w-100 d-flex align-items-center justify-content-between"
                                            @click="cleanForm()">
                                            <span>Limpiar</span>
                                            <b-icon icon="trash"></b-icon>
                                        </b-button>
                                    </b-col>
                                    <b-col cols="6" class="mt-3">
                                        <b-button variant="outline-dark-secondary-blue"
                                            class="w-100 d-flex align-items-center justify-content-between"
                                            @click="getPetCatalog()">
                                            <span>Buscar</span>
                                            <b-icon icon="search" flip-h></b-icon>
                                        </b-button>
                                    </b-col>
                                </b-row>
                            </b-form>
                        </b-col>
                        <b-col cols="5" lg="4" xl="3" class="d-none d-md-flex justify-content-center">
                            <img src="@/assets/imgs/gatito_buscador.png" height="119%" />
                        </b-col>
                    </b-row>
                </b-card>
            </b-col>
        </b-row>
        <TransitionGroup name="roll" tag="div" class="row px-5 mb-5">
            <b-col v-for="pet in pets" :key="pet.id" cols="12" sm="6" lg="4" xl="3" class="mt-3">
                <b-card :key="pet.id" :img-src="pet.image" class="pet-card" no-body>
                    <b-row class="transparent absolute-position">
                        <b-col cols="12" class="d-flex justify-content-end">
                            <b-button variant="dark-gray" class="m-2 py-2 d-flex align-items-center" pill
                                @mouseover="hoverIn(pet.id)" @mouseleave="hoverOut(pet.id)"
                                @click="addOrRemoveFavoritePet(pet)"
                                v-b-tooltip.hover.left="pet.favorite ? 'Eliminar de favoritas' : 'Marcar como favorita'">
                                <b-icon :icon="getIcon(pet)"
                                    :class="{ 'text-danger': shouldHighlight(pet), 'mt-1': true }"
                                    font-scale="2"></b-icon>
                            </b-button>
                        </b-col>
                    </b-row>
                    <b-card-body class="text-center">
                        <b-card-title>{{ pet.name }}</b-card-title>
                        <b-card-sub-title>{{ pet.location }}</b-card-sub-title>
                        <div class="d-flex justify-content-center">
                            <b-button pill variant="outline-dark-blue" class="mt-3 px-5 d-flex align-items-center"
                                @click="getDetails(pet)">
                                <span>Ver detalles</span>
                            </b-button>
                        </div>
                    </b-card-body>
                </b-card>
            </b-col>
        </TransitionGroup>
        <b-row v-show="total == 0">
            <b-col cols="12">
                <h5 class="text-center">No hay mascotas disponibles por el momento</h5>
            </b-col>
        </b-row>
        <b-row class="pt-2" v-show="total > 0">
            <b-col cols="12">
                <b-pagination pills v-model="page" :total-rows="total" :per-page="size" align="center">
                </b-pagination>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
import Swal from "sweetalert2";
import instance from "../../../../config/axios";

import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import states from "../../../../kernel/data/states";
import sizes from "../../../../kernel/data/sizes";
import lifeStages from "../../../../kernel/data/lifeStages";
import genders from "../../../../kernel/data/genders";

export default {
    data() {
        return {
            hoverStates: {},
            page: 1,
            size: 8,
            total: 0,
            payload: {
                user: localStorage.getItem("userId") ? localStorage.getItem("userId") : null,
                category: "",
                size: "",
                lifeStage: "",
                gender: "",
                location: "",
            },
            states: states,
            categories: [],
            sizes: sizes,
            lifeStages: lifeStages,
            genders: genders,
            pets: []
        }
    },
    methods: {
        hoverIn(id) {
            this.$set(this.hoverStates, id, true);
        },
        hoverOut(id) {
            this.$set(this.hoverStates, id, false);
        },
        getIcon(pet) {
            if (this.hoverStates[pet.id]) return pet.favorite ? 'heart' : 'heart-fill';
            return pet.favorite ? 'heart-fill' : 'heart';
        },
        shouldHighlight(pet) {
            if (this.hoverStates[pet.id]) return !pet.favorite;
            return pet.favorite;
        },
        async getCategories() {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Estamos cargando las categorías, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                const response = await instance.get(`/category/list`)
                this.categories = response.data.data                               
                Swal.close()
            } catch (error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al cargar las categorías',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    this.$router.push('/home')
                })
            }
        },
        async getPetCatalog() {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Estamos cargando las mascotas, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                const response = await instance.post(`/pet/catalog?page=${this.page - 1}&size=${this.size}`, {
                    user: this.payload.user,
                    category: this.payload.category == "" ? null : this.payload.category,
                    size: this.payload.size,
                    lifeStage: this.payload.lifeStage,
                    gender: this.payload.gender,
                    location: this.payload.location
                })
                this.pets = response.data.data.content
                this.total = response.data.data.totalElements
                Swal.close()
            } catch (error) {
                console.error(error)
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al cargar las mascotas',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    this.$router.push('/home')
                })
            }
        },
        async addFavoritePet(pet) {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Agregando mascota a tus favoritas, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                await instance.post(`/favorite/pet/add`, {
                    user: localStorage.getItem("userId"),
                    pet: pet.id
                })
                Swal.fire({
                    title: 'Mascota agregada',
                    text: 'La mascota ha sido agregada a tus favoritas',
                    icon: 'success',
                    iconColor: '#4BB543',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    this.getPetCatalog()
                })
            } catch (error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al eliminar la mascota de tus favoritas',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                })
            }
        },
        async removeFavoritePet(pet) {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Eliminando mascota de tus favoritas, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                await instance.post(`/favorite/pet/remove`, {
                    user: localStorage.getItem("userId"),
                    pet: pet.id
                })
                Swal.fire({
                    title: 'Mascota eliminada',
                    text: 'La mascota ha sido eliminada de tus favoritas',
                    icon: 'success',
                    iconColor: '#4BB543',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    this.getPetCatalog()
                })
            } catch (error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al eliminar la mascota de tus favoritas',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                })
            }
        },
        async confirmRemoveFavoritePet(pet) {
            Swal.fire({
                title: '¿Estás seguro?',
                text: '¿Deseas eliminar esta mascota de tus favoritas?',
                icon: 'warning',
                iconColor: '#FFA500',
                showCancelButton: true,
                confirmButtonColor: '#FFA500',
                cancelButtonColor: '#A93D3D',
                confirmButtonText: 'Sí',
                cancelButtonText: 'No'
            }).then((result) => {
                if (result.isConfirmed) {
                    this.removeFavoritePet(pet)
                }
            })
        },
        async addOrRemoveFavoritePet(pet) {
            if (localStorage.getItem("userId")) {
                if (!pet.favorite) {
                    this.addFavoritePet(pet)
                } else {
                    this.confirmRemoveFavoritePet(pet)
                }
            } else {
                Swal.fire({
                    title: 'Inicia sesión',
                    text: 'Para agregar mascotas a tus favoritas, inicia sesión',
                    icon: 'info',
                    iconColor: '#FFA500',
                    showCancelButton: true,
                    confirmButtonColor: '#FFA500',
                    cancelButtonColor: '#A93D3D',
                    confirmButtonText: 'Iniciar sesión',
                    cancelButtonText: 'Cancelar'
                }).then((result) => {
                    if (result.isConfirmed) {
                        this.$router.push('/login')
                    }
                })
            }
        },
        cleanForm() {
            this.payload = {
                user: localStorage.getItem("userId") ? localStorage.getItem("userId") : null,
                category: "",
                size: "",
                lifeStage: "",
                gender: "",
                location: "",
            }
            this.getPetCatalog()
        },
        getDetails(pet) {
            localStorage.setItem('petId', pet.id);
            this.$router.push({ name: 'petDetails' });            
        }
    },
    mounted() {
        this.getCategories()
        this.getPetCatalog()
    },
    watch: {
        page(previous, next) {
            if (previous !== next) {
                this.getPetCatalog();
            }
        }
    },
}
</script>
