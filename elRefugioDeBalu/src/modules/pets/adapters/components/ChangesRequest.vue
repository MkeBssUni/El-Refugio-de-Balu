<template>
    <b-modal id="changesModal" centered scrollable hide-footer @hidden="resetForm()">
        <template #modal-header="{ close }">
            <h5 class="mb-0">Solicitar cambios</h5>
            <b-button size="sm" variant="light" @click="close()" class="ms-auto">
                <b-icon icon="x" font-scale="1.8" class="text-dark"></b-icon>
            </b-button>
        </template>
        <b-form>
            <b-form-group label="Describa qué le falta a la publicación:" label-for="comment">
                <b-form-textarea ref="textarea" id="comment" rows="2" max-rows="6" v-model.trim="payload.comment"
                    :class="{ 'is-invalid': showErrors.comment, 'mt-2': true }"
                    @input="validateForm()"></b-form-textarea>
                <b-form-invalid-feedback v-if="showErrors.comment">
                    {{ errorMessages.comment }}
                </b-form-invalid-feedback>
            </b-form-group>
            <b-row class="mt-3 d-flex justify-content-center">
                <b-col cols="5">
                    <b-button variant="blue" class="w-100 d-flex justify-content-between align-items-center"
                        @click="showConfirmation()">
                        <span>Solicitar cambios</span>
                        <b-icon icon="pencil" class="ms-2"></b-icon>
                    </b-button>
                </b-col>
                <b-col cols="5">
                    <b-button variant="danger" class="w-100 d-flex justify-content-between align-items-center"
                        @click="closeModal()">
                        <span>Cancelar</span>
                        <b-icon icon="x-circle" class="ms-2"></b-icon>
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
        }
    },
    data() {
        return {
            payload: {
                comment: "",
                isValid: false
            },
            errorMessages: {
                comment: ""
            },
            showErrors: {
                comment: false
            }
        }
    },
    methods: {
        closeModal() {
            this.resetForm();
            this.$bvModal.hide('changesModal');
        },
        validateForm() {
            if (this.payload.comment == "") {
                this.errorMessages.comment = "Campo obligatorio";
                this.showErrors.comment = true;
                this.payload.isValid = false;
            } else if (this.payload.comment.length < 10 || this.payload.comment.length > 500) {
                this.errorMessages.comment = "El comentario debe tener entre 10 y 500 caracteres";
                this.showErrors.comment = true;
                this.payload.isValid = false;
            } else {
                this.errorMessages.comment = "";
                this.showErrors.comment = false;
                this.payload.isValid = true;
            }
        },
        showError() {
            Swal.fire({
                title: 'Error',
                text: 'Ocurrió un error al solicitar cambios en la publicación de la mascota',
                icon: 'error',
                iconColor: '#A93D3D',
                timer: 3000,
                timerProgressBar: true,
                showConfirmButton: false
            }).then(() => {
                this.$bvModal.hide('changesModal');
            })
        },
        async requestChanges() {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Estamos solicitando los cambios en la publicación de la mascota, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                const user = localStorage.getItem('userId');
                if (this.petId && user) {
                    await instance.post(`/pet/select`, {
                        pet: this.petId,
                        user: user,
                        status: 'in_revision'
                    })
                    await instance.post(`/pet/comment`, {
                        pet: this.petId,
                        user: user,
                        comment: this.payload.comment
                    });
                } else {
                    this.showError();
                }
                Swal.fire({
                    title: 'Cambios solicitados',
                    text: 'Te hemos asignado la mascota y se han solicitado los cambios exitosamente',
                    icon: 'success',
                    iconColor: '#4CAF50',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    this.$bvModal.hide('changesModal');
                    this.$router.push('moderated/petList')
                })
            } catch (error) {
                this.showError();
            }
        },
        showConfirmation() {
            this.validateForm();
            if (this.payload.isValid) {
                Swal.fire({
                    title: "¿Está seguro de solicitar cambios a la publicación?",
                    text: "Una vez solicitados los cambios, se te asignará esta mascota y deberás darle seguimiento a su proceso de adopción, ¿deseas continuar?",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#3085d6",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "Continuar",
                    cancelButtonText: "Cancelar",
                }).then((result) => {
                    if (result.isConfirmed) {
                        this.requestChanges();
                    }
                });
            }
        },
        resetForm() {
            this.payload.comment = "";
            this.errorMessages.comment = "";
            this.showErrors.comment = false;
            this.payload.isValid = false;
        },
    },
}
</script>
