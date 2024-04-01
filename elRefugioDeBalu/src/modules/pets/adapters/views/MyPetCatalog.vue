<template>
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
                            <b-form-input type="text" placeholder="Buscar..." id="search"></b-form-input>
                            <b-button variant="dark-gray" type="button" id="search"
                                class="d-flex align-items-center justify-content-between">
                                <b-icon icon="search"></b-icon>
                            </b-button>
                        </b-input-group>
                    </b-col>
                    <b-col cols="12" sm="6" md="12" lg="4" class="mt-3 mt-sm-0 mt-md-3 mt-lg-0">
                        <b-input-group>
                            <b-form-select :options="status" v-model="form.status" class="form-select"></b-form-select>
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
        <b-row class="mb-5">
            <b-col v-for="pet in pets" :key="pet.id" cols="12" sm="6" lg="4" xl="3" class="mt-3">
                <b-card :key="pet.id" :img-src="pet.image" class="pet-card" no-body>
                    <b-row v-if="pet.comments > 0" class="transparent absolute-position">
                        <b-col cols="12" class="d-flex justify-content-end">
                            <b-button class="py-2 d-flex align-items-center relative-position custom-transparent-button"
                                pill v-b-tooltip.hover.left="'Ver comentarios'">
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
                        <b-badge :variant="getBadgeVariant(pet.status)" class="mt-2">{{ pet.status }}</b-badge>
                        <div class="d-flex justify-content-center">
                            <b-button pill variant="outline-dark-blue"
                                class="mt-3 px-5 d-flex align-items-center justify-content-center" to="/petDetails">
                                <span>Ver detalles</span>
                            </b-button>
                        </div>
                    </b-card-body>
                </b-card>
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
            total: 0,
            form: {
                user: "maAenU2uRx2BsEDrfomJKw==",
                status: null,
                searchValue: ""
            },
            status: [
                { value: '', text: 'Todos los estados' },
                { value: 'Aceptada', text: 'Aceptada' },
                { value: 'Rechazada', text: 'Rechazada' },
                { value: 'Pendiente', text: 'Pendiente' }
            ],
            pets: []
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
                const response = await instance.post(`/pet/owned`, {
                    user: this.form.user,
                    status: this.form.status,
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
        getBadgeVariant(status) {
            switch (status) {
                case 'Aceptada': return 'success';
                case 'Rechazada': return 'danger';
                case 'Pendiente': return 'warning';
                default: return 'secondary';
            }
        },
    },
    mounted() {
        this.getMyPets();
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