<template>
    <div>
        <b-container fluid>
            <b-row>
                <Encabezado color="#ffc876" :imagenUrl="require('@/assets/imgs/dog.png')"
                    titulo="Mis mascotas asignadas" />
            </b-row>
            <b-row align-h="end" class="px-4">
                <b-col cols="12" sm="6" md="4" lg="3" class="pt-0 pt-md-3">
                    <b-input-group class="mt-3">
                        <b-form-select v-model="payload.category" class="form-select" @change="getPetList()">
                            <option value="">Todas las categorías</option>
                            <option v-for="category in categories" :key="category.id" :value="category.id">
                                {{ category.name }}
                            </option>
                        </b-form-select>
                    </b-input-group>
                </b-col>
                <b-col cols="12" sm="6" md="4" lg="3" class="pt-0 pt-md-3">
                    <b-input-group class="mt-3">
                        <b-form-select v-model="payload.status" class="form-select" @change="getPetList()">
                            <option value="">Todos los estados</option>
                            <option v-for="status in statusses" :key="status.value" :value="status.value">
                                {{ status.text }}
                            </option>
                        </b-form-select>
                    </b-input-group>
                </b-col>
                <b-col cols="12" md="4" lg="4" class="pt-0 pt-md-3">
                    <b-input-group class="mt-3">
                        <b-form-input type="text" placeholder="Buscar..." id="searchValue" @keyup.enter="getPetList()"
                            v-model="payload.searchValue"></b-form-input>
                        <b-button variant="dark-gray" type="button" id="searchValue"
                            class="d-flex align-items-center justify-content-between" @click="getPetList()">
                            <b-icon icon="search"></b-icon>
                        </b-button>
                    </b-input-group>
                </b-col>
            </b-row>
            <b-row class="px-4 pt-4">
                <b-col cols="12" v-show="pets.length === 0">
                    <h5 class="text-center my-3">No tienes ninguna mascota asignada</h5>
                </b-col>
                <b-col cols="12" v-show="pets.length > 0">
                    <b-table :fields="fields" :items="pets" label-sort-asc="" label-sort-desc="" no-sort-reset
                        responsive small striped hover class="text-center custom-scroll-style">
                        <template #cell(cancelRequest)="data">
                            <b-icon v-if="data.item.cancelRequest && data.item.status != 'closed'" icon="exclamation-circle" variant="danger" font-scale="1.3"
                                v-b-tooltip.hover.right="'Solicitud de cancelación'"></b-icon>
                        </template>
                        <template #cell(requests)="data">
                            <b-badge variant="warning">{{ data.value }}</b-badge>
                        </template>
                        <template #cell(status)="data">
                            <b-badge :variant="getBadgeVariant(data.value)">{{
                                mapStatus((data.value).toString().toLowerCase()) }}</b-badge>
                        </template>
                        <template #cell(actions)="data">
                            <div class="d-flex justify-content-center">
                                <b-button pill size="sm" variant="outline-dark-blue" @click="getDetails(data.item.id)"
                                    class="px-3 d-flex align-items-center"
                                    v-b-tooltip.hover.left="'Ver detalles de la mascota'">
                                    <i class="material-icons" style="font-size: 1rem">pets</i>
                                </b-button>
                                <b-button pill size="sm" variant="outline-dark-secondary-blue"
                                    @click="getAdoptionRequests(data.item.id)" class="ms-2 px-3 d-flex align-items-center"
                                    v-b-tooltip.hover.left="'Ver solicitudes de adopción'">
                                    <b-icon icon="card-heading" font-scale="1"></b-icon>
                                </b-button>
                                <b-button pill size="sm" v-b-tooltip.hover.left="'Ver comentarios'"
                                    :variant="(data.item.cancelRequest && data.item.status != 'closed') ? 'outline-danger' : 'outline-dark-orange'"
                                    class="ms-2 px-3 d-flex align-items-center" @click="getComments(data.item.id)">
                                    <b-icon icon="chat-left-text" font-scale="1"></b-icon>
                                </b-button>
                            </div>
                        </template>
                    </b-table>
                </b-col>
            </b-row>
            <b-row class="px-4">
                <b-col cols="12" class="d-flex align-items-center">
                    <label for="perPage">Selecciona la cantidad de registros que deseas mostrar:</label>
                    <b-form-select :options="options" v-model="size" class="ms-3 my-3 form-select" style="width: 80px"
                        @change="getPetList()"></b-form-select>
                </b-col>
                <b-col cols="12" class="mt-1">
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

import Encabezado from "../../../../views/components/Encabezado.vue";
import Modal from "../components/Comments.vue";
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import getStatusses from "../../../../kernel/data/modStatusses";
import { statusses } from "../../../../kernel/data/mappingDictionaries";

export default {
    data() {
        return {
            size: 5,
            page: 1,
            total: 0,
            options: [5, 10, 15, 20],
            categories: [],
            statusses: getStatusses,
            payload: {
                user: localStorage.getItem("userId") ? localStorage.getItem("userId") : null,
                category: "",
                status: "",
                searchValue: ""
            },
            fields: [
                { key: 'cancelRequest', label: '' },
                { key: 'requests', label: 'Solicitudes', sortable: true },
                { key: 'category', label: 'Categoría', sortable: true },
                { key: 'name', label: 'Nombre', sortable: true },
                { key: 'status', label: 'Estado' },
                { key: 'actions', label: 'Acciones' }
            ],
            pets: [],
            selectedPetId: "",
            selectedPetCancelRequest: "",
            comments: [],
            canComment: false
        }
    },
    methods: {
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
        async getPetList() {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Estamos cargando tus mascotas asignadas, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                const response = await instance.post(`/pet/moderated?page=${this.page - 1}&size=${this.size}`, {
                    user: this.payload.user,
                    category: this.payload.category === '' ? null : this.payload.category,
                    status: this.payload.status == '' ? null : this.payload.status,
                    searchValue: this.payload.searchValue
                })
                this.pets = response.data.data.content
                this.total = response.data.data.totalElements
                Swal.close()
            } catch (error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al cargar tus mascotas asignadas',
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
        getBadgeVariant(status) {
            switch (status) {
                case 'approved': return 'success';
                case 'in_revision': return 'warning';
                case 'adopted': return 'info';
                case 'closed': return 'danger';
                default: return 'secondary';
            }
        },
        mapStatus(status) {
            return statusses[status] || status;
        },
        async getComments(petId) {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Estamos cargando los comentarios, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                this.selectedPetId = petId
                this.selectedPetCancelRequest = this.pets.find(pet => pet.id === petId).cancelRequest;
                this.canComment = ['in_revision', 'approved'].includes(this.pets.find(pet => pet.id === petId).status);
                const response = await instance.post(`/pet/comment/all`, {
                    pet: petId,
                    user: this.payload.user
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
            this.getComments(this.selectedPetId)
        },
        getDetails(petId) {
            localStorage.setItem('petId', petId)
            this.$router.push({ name: 'myModeratedPet' });
        },
        getAdoptionRequests(petId) {
            localStorage.setItem('petId', petId)
            this.$router.push({ name: 'adoptionList' });
        }
    },
    mounted() {
        this.getCategories()
        this.getPetList()
    },
    watch: {
        page(previous, next) {
            if (previous !== next) {
                this.getPetList();
            }
        }
    },
    components: {
        Encabezado,
        Modal
    },
}
</script>

<style scoped>
.form-select {
    width: 100%;
}
</style>