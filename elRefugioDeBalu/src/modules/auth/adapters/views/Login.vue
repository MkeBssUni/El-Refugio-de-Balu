<script>
import Swal from 'sweetalert2';
import instance from '../../../../config/axios';
import { decrypt, encrypt } from '../../../../kernel/hashFunctions';
export default {
    data() {
        return {
            form: {
                username: '',
                password: '',
                isValid: false
            },
            logInForm: {
                username: '',
                password: ''
            },
            errors: {
                email: '',
                password: ''
            },
            showErrors: {
                email: false,
                password: false
            },
            arrayRegex: {
                validUsername: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
            }
        }
    },
    methods: {
        validateInput(validateInput) {
            let input;
            switch (validateInput) {
                case 'email':
                    input = document.getElementById('input-email');
                    if (this.form.username === '') {
                        this.errors.email = 'Campo requerido';
                        input.classList.add('is-invalid');
                        this.showErrors.email = true;
                    } else if (!this.arrayRegex.validUsername.test(this.form.username)) {
                        input.classList.add('is-invalid');
                        this.errors.email = 'Campo no válido';
                        this.showErrors.email = true;
                    } else {
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                        this.showErrors.email = false;
                    }
                    break;
                case 'password':
                    input = document.getElementById('input-password');
                    if (this.form.password === '') {
                        this.errors.password = 'Campo requerido';
                        input.classList.add('is-invalid');
                        this.showErrors.password = true;
                    } else {
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                        this.showErrors.password = false;
                    }
                    break;
                default:
                    break;
            }

            if (this.form.username !== '' && this.form.password !== '' && !this.showErrors.email && !this.showErrors.password) {
                this.form.isValid = true;
            } else {
                this.form.isValid = false;
            }
        },
        async login() {
            this.logInForm.username = await encrypt(this.form.username);
            this.logInForm.password = await encrypt(this.form.password);

            Swal.fire({
                title: 'Iniciando sesión',
                allowOutsideClick: false,
                showConfirmButton: false,
                willOpen: () => {
                    Swal.showLoading();
                }
            });

            try {
                const response = await instance.post('/auth/', this.logInForm)

                localStorage.setItem('userId', response.data.data.userId);
                localStorage.setItem('token', await encrypt(response.data.data.token));
                localStorage.setItem('role', await encrypt(response.data.data.role.name));

                Swal.fire({
                    icon: 'success',
                    title: '¡Bienvenido!',
                    text: 'Iniciaste sesión correctamente'
                });

                this.$router.push('/home');
            } catch (error) {
                let message = error.response.data.message;
                switch (message) {
                    case 'Bad credentials':
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: 'Usuario o contraseña incorrectos'
                        });
                        break;
                    case 'INCORRECT_CREDENTIALS':
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: 'Usuario o contraseña incorrectos'
                        });
                        break;
                    case 'ACCOUNT_BLOCKED_TEMPORARY':
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: 'Tu cuenta se encuentra bloqueda temporalmente'
                        });
                        break;
                    case 'ACCOUNT_JUST_BLOCKED':
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: 'Has alcanzado el límite de intentos, tu cuenta ha sido bloqueada temporalmente, puedes intentarlo nuevamente en 30 minutos'
                        });
                        break;
                }
            }
        }
    }
}
</script>

<template>
    <b-container fluid class="bg-blue">
        <b-row align-v="center">
            <b-col cols="5" class="px-5">
                <b-card class="shadow bg-orange">
                    <img src="../../../../assets/imgs/logo blanco.png" class="img-fluid d-block mx-auto"
                        alt="Responsive image" width="30%">
                    <b-form class="px-5">
                        <b-form-group class="my-3 text-white" label="Correo electrónico:" label-for="input-email">
                            <b-form-input class="bg-light shadow text-dark-gray-input" id="input-email"
                                v-model.trim="form.username" @input="validateInput('email')"
                                type="email"></b-form-input>
                            <b-form-invalid-feedback v-if="showErrors.email">{{ errors.email
                                }}</b-form-invalid-feedback>
                        </b-form-group>

                        <b-form-group class="my-3 text-white" label="Contraseña:" label-for="input-password">
                            <b-form-input id="input-password" class="bg-light shadow text-dark-gray-input"
                                v-model.trim="form.password" @input="validateInput('password')"
                                type="password"></b-form-input>
                            <b-form-invalid-feedback v-if="showErrors.password">{{ errors.password
                                }}</b-form-invalid-feedback>
                        </b-form-group>
                        <b-row class="justify-content-center">
                            <b-col class="d-flex justify-content-center align-items-center mt-3">
                                <b-button lass="bg-dark-secondary-orange text-white mx-5" @click="login()"
                                    :disabled="!form.isValid">Iniciar sesión</b-button>
                            </b-col>
                        </b-row>

                        <b-row class="justify-content-center">
                            <b-col class="d-flex justify-content-center align-items-center mt-3">
                                <b-link to="/selfRegistration" class="text-dark-blue text-decoration-none">
                                    ¿No tienes cuenta? Regístrate</b-link>
                            </b-col>
                        </b-row>
                    </b-form>
                </b-card>
            </b-col>
            <b-col cols="7" class="text-center">
                <b-img
                    src="https://img.freepik.com/foto-gratis/ai-generado-perro-labrador-retriever_23-2150686788.jpg?w=740&t=st=1707087667~exp=1707088267~hmac=fef11794c20b1e6d8d9dea1a348eb8e50c293ef7c414c3bb0ec333617e044beb"
                    fluid left rounded class="img"></b-img>
            </b-col>
        </b-row>
    </b-container>
</template>

<style scoped>
.img {
    height: 90vh;
    width: 100%;
    object-fit: cover;
}
</style>