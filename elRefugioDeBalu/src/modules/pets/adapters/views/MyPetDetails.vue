<template>
    <div>
        <b-container fluid>
            <b-row>
                <b-col cols="12" class="mt-5 px-5">
                    <!-- md, lg, xl card -->
                    <b-card class="full-height-card p-4 d-none d-md-block card-shadow" bg-variant="gray">
                        <LargeContent :pet="pet" />
                        <hr class="divider my-0">
                        <b-row class="mt-4 d-flex justify-content-end">
                            <b-col cols="4" lg="3" v-show="canEdit">
                                <b-button variant="outline-dark-orange" @click="editPet()"
                                    class="me-3 d-flex align-items-center justify-content-between w-100">
                                    <span class="me-2">Editar</span>
                                    <b-icon icon="pencil" font-scale="1.3"></b-icon>
                                </b-button>
                            </b-col>
                            <b-col cols="4" lg="3" v-show="canCancel">
                                <b-button variant="outline-danger" @click="showCancelModal()"
                                    class="me-3 d-flex align-items-center justify-content-between w-100">
                                    <span class="me-2">Cancelar</span>
                                    <b-icon icon="x-circle" font-scale="1.3"></b-icon>
                                </b-button>
                            </b-col>
                            <b-col cols="4" lg="3">
                                <b-button variant="outline-secondary-gray" @click="goBack"
                                    class="d-flex align-items-center justify-content-between w-100">
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
                            <b-col cols="12" sm="4" v-show="canEdit">
                                <b-button variant="outline-dark-orange" @click="editPet()"
                                    class="me-3 d-flex align-items-center justify-content-between w-100">
                                    <span class="me-2">Editar</span>
                                    <b-icon icon="pencil" font-scale="1.3"></b-icon>
                                </b-button>
                            </b-col>
                            <b-col cols="12" sm="4" class="mt-3 mt-sm-0" v-show="canCancel" @click="showCancelModal()">
                                <b-button variant="outline-danger"
                                    class="me-3 d-flex align-items-center justify-content-between w-100">
                                    <span class="me-2">Cancelar</span>
                                    <b-icon icon="x-circle" font-scale="1.3"></b-icon>
                                </b-button>
                            </b-col>
                            <b-col cols="12" sm="4" class="mt-3 mt-sm-0">
                                <b-button variant="outline-secondary-gray" @click="goBack"
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
        <CancelModal :status="status" :petId="petId" />
    </div>
</template>

<script>
import Swal from "sweetalert2";
import instance from "../../../../config/axios";

import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import SmallContent from "../components/PetSmallCardContent.vue"
import LargeContent from "../components/PetLargeCardContent.vue"
import CancelModal from "../components/CancelRequest.vue"

export default {
    data() {
        return {
            petId: "",            
            pet: {},
            canEdit: false,
            canCancel: false,
            status: ""
        }
    },
    methods: {
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
                if (this.pet.diseases == "") this.pet.diseases = null;
                if (this.pet.allergies == "") this.pet.allergies = null;
                if (this.pet.specialCares == "") this.pet.specialCares = null;
                if (this.pet.status === 'IN_REVISION') this.canEdit = true;
                if (this.pet.status === 'PENDING' || this.pet.status === 'IN_REVISION' || this.pet.status === 'APPROVED') this.canCancel = true;
                this.status = this.pet.status;
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
                    this.$router.push('/petList')
                })
            }
        },
        goBack() {
            this.$router.go(-1);
            localStorage.removeItem("petId");
        },
        showCancelModal() {
            this.$bvModal.show('cancelModal');
        },
        editPet() {
            this.$router.push({ name: 'petUpdateForm' });
        }
    },
    mounted() {
        if (localStorage.getItem("petId")) {
            this.petId = localStorage.getItem("petId");
            this.getDetails();
        } else {
            this.goBack();
        }
    },
    components: {
        SmallContent,
        LargeContent,
        CancelModal
    },
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
</style>