<template>
    <b-modal id="commentsModal" centered scrollable @hidden="resetForm()">
        <template #modal-header="{ close }">
            <h5 class="mb-0">Comentarios de la mascota</h5>
            <b-button size="sm" variant="light" @click="close()" class="ms-auto">
                <b-icon icon="x" font-scale="1.8" class="text-dark"></b-icon>
            </b-button>
        </template>
        <b-alert variant="danger" class="custom-alert" show v-show="cancelRequest">
            <b-row class="d-flex align-items-center">
                <b-col cols="2">
                    <b-icon icon="exclamation-triangle-fill" font-scale="1.5" class="me-4"></b-icon>
                </b-col>
                <b-col cols="10">
                    <p class="mb-0">El usuario solicitó cancelar la adopción de esta mascota porque...</p>                                        
                </b-col>                                
            </b-row>                    
            <b-row class="ms-3">
                <b-col cols="12">
                    <p class="ms-5 mt-2 mb-0 text-dark">{{ cancelRequest }}</p>
                </b-col>
            </b-row>    
        </b-alert>
        <b-row v-for="comment in comments" :key="comment.id" class="my-3">
            <b-col cols="12" v-if="sessionRole == comment.userRole" class="text-end">
                <span class="text-dark comment">
                    {{ (comment.userRole == 'mod') ? 'Moderador' : 'Usuario' }} (tú)
                </span>
                <b-card bg-variant="blue" no-body class="p-2 ms-auto">
                    <p class="text-light mb-0">{{ comment.comment }}</p>
                </b-card>
            </b-col>
            <b-col cols="12" v-else>
                <span class="text-dark comment">{{ (comment.userRole == 'mod') ? 'Moderador' : 'Usuario' }}</span>
                <b-card bg-variant="light" no-body class="p-2">
                    <p class="mb-0">{{ comment.comment }}</p>
                </b-card>
            </b-col>
        </b-row>
        <b-row class="text-center">
            <b-col>
                <span v-show="comments.length == 0" class="text-dark-gray-input">Esta mascota no tiene
                    comentarios</span>
            </b-col>
        </b-row>
        <template #modal-footer>
            <b-form class="d-flex align-items-start" v-if="canComment">
                <div>
                    <b-form-textarea ref="textarea" id="textarea" v-model.trim="form.comment"
                        placeholder="Escribe un comentario..." rows="2" max-rows="3" class="mb-2"
                        @input="validateForm()" :class="{ 'is-invalid': showErrors.comment }"></b-form-textarea>
                    <b-form-invalid-feedback v-if="showErrors.comment">
                        {{ errorMessages.comment }}
                    </b-form-invalid-feedback>
                </div>
                <b-button size="sm" variant="outline-dark-blue" class="ms-3 d-flex align-items-center"
                    @click="addComment()" :disabled="isLoading">
                    <template v-if="isLoading">
                        <b-spinner small></b-spinner>
                        <span class="ms-2">Cargando...</span>
                    </template>
                    <template v-else>
                        <span class="me-2">Enviar</span>
                        <b-icon icon="cursor" font-scale="1.5"></b-icon>
                    </template>
                </b-button>
            </b-form>
            <div v-else></div>
        </template>
    </b-modal>
</template>

<script>
import Swal from "sweetalert2";
import { decrypt } from '../../../../kernel/hashFunctions';
import instance from "../../../../config/axios";

export default {
    props: {
        comments: {
            type: Array,
            required: true
        },
        petId: {
            type: String,
            required: true
        },
        canComment: {
            type: Boolean,
            required: true
        },
        cancelRequest: {
            type: String,
            default: null
        }
    },
    data() {
        return {
            sessionRole: "",
            userId: localStorage.getItem('userId') ? localStorage.getItem('userId') : "",
            isLoading: false,
            form: {
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
        async getSessionRole() {
            const sessionRole = await decrypt(localStorage.getItem('role'));
            this.sessionRole = sessionRole.toString().toLowerCase();
        },
        validateForm() {
            if (this.form.comment == "") {
                this.errorMessages.comment = "Campo obligatorio";
                this.showErrors.comment = true;
                this.form.isValid = false;
            } else if (this.form.comment.length < 2 || this.form.comment.length > 500) {
                this.errorMessages.comment = "El comentario debe tener entre 2 y 500 caracteres";
                this.showErrors.comment = true;
                this.form.isValid = false;
            } else {
                this.errorMessages.comment = "";
                this.showErrors.comment = false;
                this.form.isValid = true;
            }
        },
        async addComment() {
            this.validateForm()
            if (this.form.isValid) {
                this.isLoading = true;
                try {
                    await instance.post(`/pet/comment`, {
                        comment: this.form.comment,
                        user: this.userId,
                        pet: this.petId
                    })
                    Swal.fire({
                        title: 'Comentario enviado',
                        text: 'Tu comentario ha sido enviado correctamente.',
                        icon: 'success',
                        iconColor: '#4CAF50',
                        timer: 3000,
                        timerProgressBar: true,
                        showConfirmButton: false
                    }).then(() => {
                        this.isLoading = false;
                        this.resetForm();
                        this.$emit('comment-added');
                    })
                } catch (error) {
                    Swal.fire({
                        title: 'Error',
                        text: 'Ocurrió un error al enviar el comentario, por favor intenta más tarde.',
                        icon: 'error',
                        iconColor: '#A93D3D',
                        timer: 3000,
                        timerProgressBar: true,
                        showConfirmButton: false
                    }).then(() => {
                        this.isLoading = false;
                    })
                }
            }
        },
        resetForm() {
            this.form.comment = "";
            this.form.isValid = false;
            this.errorMessages.comment = "";
            this.showErrors.comment = false;
        }
    },
    mounted() {
        this.getSessionRole();
    }
}
</script>

<style scoped>
form {
    width: 100%;
}

form div {
    width: 80%;
}

.card {
    width: 90%;
}

.comment {
    font-size: 1.15rem;
}

.custom-alert p {
  word-wrap: break-word;
  overflow-wrap: break-word;
}
</style>