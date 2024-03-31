<template>
    <b-container fluid class="px-5">
        <b-row class="mt-5 d-flex align-items-center">
            <b-col cols="12" sm="7" md="6" class="d-flex align-items-center">
                <span class="custom-h1">Mis mascotas favoritas</span>
            </b-col>
            <b-col cols="12" sm="5" md="6" class="mt-3 mt-sm-0">
                <b-input-group>
                    <b-input-group>
                        <b-form-input type="text" placeholder="Buscar..." id="searchValue" v-model="searchValue"
                            @keyup.enter="getFavorites()"></b-form-input>
                        <b-button variant="dark-gray" type="button" id="searchValue"
                            class="d-flex align-items-center justify-content-between">
                            <b-icon icon="search" @click="getFavorites()"></b-icon>
                        </b-button>
                    </b-input-group>
                </b-input-group>
            </b-col>
        </b-row>
        <hr class="mt-4">
        <b-row class="mb-5">
            <b-col v-for="pet in pets" :key="pet.id" cols="12" sm="6" lg="4" xl="3" class="mt-3">
                <b-card :key="pet.id" :img-src="pet.image" class="pet-card" no-body>
                    <b-row class="transparent absolute-position">
                        <b-col cols="12" class="d-flex justify-content-end">
                            <b-button variant="dark-gray" class="m-2 py-2 d-flex align-items-center" pill
                                @mouseenter="hoverIn(pet.id)" @mouseleave="hoverOut()" @click="showRemovePetConfirmation(pet)"
                                v-b-tooltip.hover.left="'Eliminar de favoritas'">
                                <b-icon :icon="hover === pet.id ? 'heart' : 'heart-fill'" font-scale="2"
                                    :class="{ 'text-danger': hover !== pet.id, 'mt-1': true }"></b-icon>
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
            <b-col v-show="total == 0">
                <h5 class="text-center mt-3">No has marcado mascotas como tus favoritas</h5>
            </b-col>
        </b-row>
        <b-row class="pt-2">
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

export default {
    data() {
        return {
            hover: null,
            searchValue: '',
            page: 1,
            size: 8,
            total: 0,
            payload: {
                user: localStorage.getItem("userId") ? localStorage.getItem("userId") : null,
                searchValue: ""
            },
            pets: []
        }
    },
    methods: {
        hoverIn(id) {
            this.hover = id;
        },
        hoverOut() {
            this.hover = null
        },
        async getFavorites() {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Estamos cargando tus mascotas favoritas, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                const response = await instance.post(`/favorite/pet/catalog?page=${this.page - 1}&size=${this.size}`, {
                    user: this.payload.user,
                    searchValue: this.searchValue
                })
                this.pets = response.data.data.content                
                this.total = response.data.data.totalElements
                Swal.close()
            } catch (error) {
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
        async removePet(pet) {
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
                    this.getFavorites()
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
        showRemovePetConfirmation(pet) {
            Swal.fire({
                title: '¿Estás seguro?',
                text: 'Estás a punto de eliminar a esta mascota de tus favoritas',
                icon: 'warning',
                showCancelButton: true,
                showConfirmButton: true,
                confirmButtonText: 'Eliminar',
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.isConfirmed) {
                    this.removePet(pet)
                }
            });
        },
        getDetails(pet) {
            this.$router.push({ name: 'petDetails', params: { petId: pet.id } });
            localStorage.setItem('petId', pet.id);
        }
    },
    mounted() {
        this.getFavorites()
    },
    watch: {
        page(previous, next) {
            if (previous !== next) {
                this.getFavorites();
            }
        }
    }
}
</script>

<style scoped></style>