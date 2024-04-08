<template>
    <b-modal id="cancelModal" centered scrollable hide-footer @hidden="resetForm()">
        <template #modal-header="{ close }">
            <h5 class="mb-0">Cancelar mi publicación</h5>
            <b-button size="sm" variant="light" @click="close()" class="ms-auto">
                <b-icon icon="x" font-scale="1.8" class="text-dark"></b-icon>
            </b-button>
        </template>
        <b-form>
            <b-form-group label="Escriba por qué desea cancelar su publicación:" label-for="reason">
                <b-form-textarea ref="textarea" id="reason" rows="2" max-rows="6" v-model.trim="payload.reason"
                    :class="{ 'is-invalid': showErrors.reason, 'mt-2': true }"
                    @input="validateForm()"></b-form-textarea>
                <b-form-invalid-feedback v-if="showErrors.reason">
                    {{ errorMessages.reason }}
                </b-form-invalid-feedback>
            </b-form-group>
            <b-row class="mt-3 d-flex justify-content-center">
                <b-col cols="5">
                    <b-button variant="danger" class="w-100 d-flex justify-content-between align-items-center"
                        @click="showConfirmation()">
                        <span>Cancelar</span>
                        <b-icon icon="x-circle" class="ms-2"></b-icon>
                    </b-button>
                </b-col>
                <b-col cols="5">
                    <b-button variant="blue" class="w-100 d-flex justify-content-between align-items-center"
                        @click="closeModal()">
                        <span>Regresar</span>
                        <b-icon icon="arrow-left-circle" class="ms-2"></b-icon>
                    </b-button>
                </b-col>
            </b-row>
        </b-form>
    </b-modal>
</template>

<script>
import Swal from "sweetalert2";
import instance from "../../../../config/axios";

import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";

export default {
    props: {
        petId: {
            type: String,
            required: true
        },
        status: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            payload: {
                reason: "",
                isValid: false
            },
            errorMessages: {
                reason: ""
            },
            showErrors: {
                reason: false
            }
        };
    },
    methods: {
        closeModal() {
            this.resetForm();
            this.$bvModal.hide("cancelModal");
        },
        validateForm() {
            if (this.payload.reason === "") {
                this.showErrors.reason = true;
                this.payload.isValid = false;
                this.errorMessages.reason = "Campo obligatorio";
            } else if (this.payload.reason.length < 50 || this.payload.reason.length > 500) {
                this.showErrors.reason = true;
                this.payload.isValid = false;
                this.errorMessages.reason = "El motivo debe tener entre 50 y 500 caracteres";
            } else {
                this.errorMessages.reason = "";
                this.showErrors.reason = false;
                this.payload.isValid = true;
            }
        },
        showError() {
            Swal.fire({
                title: "Error",
                text: "Ocurrió un error al cancelar la publicación",
                icon: 'error',
                iconColor: '#A93D3D',
                timer: 3000,
                timerProgressBar: true,
                showConfirmButton: false
            }).then(() => {
                this.$bvModal.hide("cancelModal");
            });
        },
        async cancelPetRequest() {
            try {
                Swal.fire({
                    title: 'Enviando solicitud...',
                    text: 'Estamos enviando tu solicitud de cancelación, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                });
                await instance.post("/pet/cancel", {
                    pet: this.petId,
                    owner: localStorage.getItem("userId"),
                    cancelReason: this.payload.reason
                });
                Swal.fire({
                    title: 'Solicitud enviada',
                    text: (this.status === 'IN_REVISION' || this.status === 'APPROVED') ? 'Tu solicitud de cancelación ha sido enviada al moderador encargado' : 'La publicación de tu mascota ha sido cancelada exitosamente',
                    icon: 'success',
                    iconColor: '#FFA500',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    this.$bvModal.hide("cancelModal");
                    this.$router.push("/myPets");
                });                
            } catch (error) {                
                this.showError();
            }
        },
        showConfirmation() {
            this.validateForm();
            if (this.payload.isValid) {
                Swal.fire({
                    title: '¿Estás seguro?',
                    text: (this.status === 'IN_REVISION' || this.status === 'APPROVED') ? 'Se enviará tu solicitud de cancelación al moderador encargado del seguimiento de tu mascota, ¿deseas continuar?' : 'Cancelar la publicación de tu mascota es una acción irreversible, ¿deseas continuar?',
                    icon: 'warning',
                    iconColor: '#FFA500',
                    showCancelButton: true,
                    confirmButtonColor: '#FFA500',
                    cancelButtonColor: '#A93D3D',
                    confirmButtonText: 'Continuar',
                    cancelButtonText: 'Cancelar'
                }).then((result) => {
                    if (result.isConfirmed) {
                        this.cancelPetRequest();
                    } else {
                        this.$bvModal.hide("cancelModal");
                    }
                })
            }
        },
        resetForm() {
            this.payload.reason = "";
            this.errorMessages.reason = "";
            this.showErrors.reason = false;
            this.payload.isValid = false;
        }
    }
}
</script>
