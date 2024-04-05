<template>
    <div>
        <b-container fluid class="px-5">
            <b-row class="mt-5 d-flex align-items-center">
                <b-col cols="12" md="6" lg="5"
                    class="d-flex align-items-center justify-content-center justify-content-md-start">
                    <span class="custom-h1">Mis publicaciones de mascotas</span>
                </b-col>
                <b-col cols="12" md="6" lg="7" class="mt-3 mt-md-0">
                    <b-row class="d-flex justify-content-center">
                        <b-col cols="12" sm="6" md="12" lg="5">
                            <b-input-group>
                                <b-form-input type="text" placeholder="Buscar..." id="searchValue"
                                    v-model.trim="form.searchValue" @keyup.enter="getMyPets()"></b-form-input>
                                <b-button variant="dark-gray" type="button" id="searchValue" @click="getMyPets()"
                                    class="d-flex align-items-center justify-content-between">
                                    <b-icon icon="search"></b-icon>
                                </b-button>
                            </b-input-group>
                        </b-col>
                        <b-col cols="12" sm="6" md="12" lg="4" class="mt-3 mt-sm-0 mt-md-3 mt-lg-0">
                            <b-input-group>
                                <b-form-select v-model="form.status" class="form-select" @change="getMyPets()">
                                    <option value="">Todos los estados</option>
                                    <option v-for="status in statusses" :key="status.value" :value="status.value">
                                        {{ status.text }}
                                    </option>
                                </b-form-select>
                            </b-input-group>
                        </b-col>
                        <b-col cols="12" sm="6" md="12" lg="3" class="d-flex justify-content-center mt-3 mt-lg-0">
                            <b-button variant="outline-success w-100" to="/petForm"
                                class="d-flex align-items-center justify-content-between">
                                <span class="mr-2">Agregar</span>
                                <b-icon icon="plus-circle" class="ms-2"></b-icon>
                            </b-button>
                        </b-col>
                    </b-row>
                </b-col>
            </b-row>
            <hr class="mt-4">
            <TransitionGroup name="roll" tag="div" class="row mb-5">
                <b-col v-for="pet in pets" :key="pet.id" cols="12" sm="6" lg="4" xl="3" class="mt-3">
                    <b-card :key="pet.id" :img-src="pet.image" class="pet-card" no-body>
                        <b-row v-if="pet.comments > 0" class="transparent absolute-position">
                            <b-col cols="12" class="d-flex justify-content-end">
                                <b-button
                                    class="py-2 d-flex align-items-center relative-position custom-transparent-button"
                                    pill v-b-tooltip.hover.left="'Ver comentarios'" @click="getComments(pet)">
                                    <b-icon icon="chat-left-fill" font-scale="3"
                                        class="text-dark-secondary-orange chat-icon mt-2"></b-icon>
                                    <span class="text-light comments">
                                        {{ (pet.comments > 9) ? '9+' : pet.comments }}
                                    </span>
                                </b-button>
                            </b-col>
                        </b-row>
                        <b-card-body class="text-center">
                            <b-card-title>{{ pet.name }}</b-card-title>
                            <b-card-sub-title>{{ pet.location }}</b-card-sub-title>
                            <b-badge :variant="getBadgeVariant(pet.status)" class="mt-2">{{
                                        mapStatus((pet.status).toString().toLowerCase()) }}</b-badge>
                            <div class="d-flex justify-content-center">
                                <b-button pill variant="outline-dark-blue"
                                    class="mt-3 px-5 d-flex align-items-center justify-content-center" @click="getDetails(pet.id)">
                                    <span>Ver detalles</span>
                                </b-button>
                            </div>
                        </b-card-body>
                    </b-card>
                </b-col>
            </TransitionGroup>
            <b-row class="pt-2">
                <b-col cols="12">
                    <b-pagination pills v-model="page" :total-rows="total" :per-page="size" align="center">
                    </b-pagination>
                </b-col>
            </b-row>
        </b-container>
        <Modal :comments="comments" :petId="selectedPetId" :canComment="canComment"
            :cancelRequest="selectedPetCancelRequest" @comment-added="loadComments()" />
    </div>
</template>

<script>
import Swal from "sweetalert2";
import instance from "../../../../config/axios";
import getStatusses from "../../../../kernel/data/userStatusses";
import { statusses } from "../../../../kernel/data/mappingDictionaries";

import Modal from "../components/Comments.vue";
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";

export default {
    data() {
        return {
            size: 8,
            page: 1,
            total: 0,
            form: {
                user: localStorage.getItem("userId") ? localStorage.getItem("userId") : null,
                status: "",
                searchValue: ""
            },
            statusses: getStatusses,
            pets: [],
            selectedPetId: "",
            selectedPetCancelRequest: "",
            comments: [],
            canComment: false
        }
    },
    methods: {
        async getMyPets() {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Estamos cargando tus mascotas, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                const response = await instance.post(`/pet/owned?page=${this.page - 1}&size=${this.size}`, {
                    user: this.form.user,
                    status: this.form.status != "" ? this.form.status : null,
                    searchValue: this.form.searchValue
                })
                this.pets = response.data.data.content
                this.total = response.data.data.totalElements
                Swal.close()
            } catch (error) {
                console.log(error)
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al cargar tus mascotas',
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
        mapStatus(status) {
            return statusses[status] || status;
        },
        getBadgeVariant(status) {
            switch (status) {
                case 'approved': return 'success';
                case 'in_revision': return 'warning';
                case 'adopted': return 'info';
                case 'closed': return 'danger';
                default: return 'secondary';
            }
        },
        async getComments(pet) {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Estamos cargando los comentarios, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                this.selectedPetId = pet.id
                this.canComment = ['in_revision', 'approved'].includes(pet.status);
                const response = await instance.post(`/pet/comment/all`, {
                    pet: pet.id,
                    user: this.form.user
                })
                this.comments = response.data.data
                Swal.close()
                this.$bvModal.show('commentsModal')
            } catch (error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al cargar los comentarios',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    this.$bvModal.hide('commentsModal')
                })
            }
        },
        async loadComments() {
            const pet = this.pets.find(pet => pet.id === this.selectedPetId);
            if (pet) {
                await this.getComments(pet);
            }
        },        
        getDetails(petId) {
            this.$router.push({ name: 'myPet', params: { petId: petId } });
            sessionStorage.setItem('petId', petId);                        
        }
    },
    mounted() {
        this.getMyPets();
    },
    watch: {
        page(previous, next) {
            if (previous !== next) {
                this.getMyPets();
            }
        }
    },
    components: {
        Modal
    }
}
</script>

<style scoped>
.chat-icon {
    position: relative;
    z-index: 1;
}

.comments {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 2;
    font-size: 1.5em;

}

.custom-transparent-button {
    background-color: transparent !important;
    width: 100%;
}

.custom-transparent-button:hover .chat-icon {
    color: #ff7d4a !important;
}
</style>