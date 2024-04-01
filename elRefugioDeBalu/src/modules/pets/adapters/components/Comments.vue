<template>
    <b-modal id="commentsModal" centered scrollable @hidden="resetForm()">
        <template #modal-header="{ close }">
            <h5 class="mb-0">Comentarios de la mascota</h5>
            <b-button size="sm" variant="light" @click="close()" class="ms-auto">
                <b-icon icon="x" font-scale="1.8" class="text-dark"></b-icon>
            </b-button>
        </template>
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
                <span v-show="comments.length == 0" class="text-dark-gray-input">Esta mascota no tiene comentarios</span>
            </b-col>
        </b-row>
        <template #modal-footer>
            <b-form class="d-flex align-items-start">
                <b-form-textarea id="textarea" v-model="newComment" placeholder="Escribe un comentario..." rows="2"
                    max-rows="3" class="mb-2"></b-form-textarea>
                <b-button size="sm" variant="outline-dark-blue" class="ms-3 d-flex align-items-center">
                    <span class="me-2">Enviar</span>
                    <b-icon icon="cursor" font-scale="1.5"></b-icon>
                </b-button>
            </b-form>
        </template>
    </b-modal>
</template>

<script>
import { decrypt } from '../../../../kernel/hashFunctions';

export default {
    props: {
        comments: Array,
        required: true
    },
    data() {
        return {
            sessionRole: "",
            newComment: ""
        }
    },
    methods: {
        async getSessionRole() {
            const sessionRole = await decrypt(localStorage.getItem('role'));
            this.sessionRole = sessionRole.toString().toLowerCase();
        },
        resetForm() {
            this.newComment = ""
        }
    },
    mounted() {
        this.getSessionRole();
    }
}
</script>

<style scoped>
.card {
    width: 90%;
}

form {
    width: 100%;
}

.comment {
    font-size: 1.15rem;
}
</style>