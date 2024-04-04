<script>
import Swal from 'sweetalert2';
import instance from '../../../../config/axios';
import { encrypt } from '../../../../kernel/hashFunctions';
import { BButton, BInputGroup, BFormInput, BIcon } from "bootstrap-vue";
import loginI from "../../../../assets/imgs/loginI.jpg";
export default {
    data() {
        return {
            loginI: loginI,
            showPassword: false,
            components: {
                BButton,
                BInputGroup,
                BFormInput,
                BIcon,
            },
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
            },
            encryptedForm: {
                username: ''
            }
        }
    },
    methods: {
        togglePasswordVisibility() {
            this.showPassword = !this.showPassword;
            const inputPassword = document.getElementById("input-password");
            if (this.showPassword) {
                inputPassword.type = "text";
            } else {
                inputPassword.type = "password";
            }
        },
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
                localStorage.setItem('profileCompleted', await encrypt(response.data.data.profileCompleted));

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
        },
        async activateAccount() {
            Swal.fire({
                title: "Código de verificación",
                text: "Ingresa el correo electrónico con el que te registraste para enviar un código de verificación",
                input: "text",
                inputAttributes: {
                    autocapitalize: "off",
                },
                showCancelButton: true,
                confirmButtonText: "Enviar código",
                cancelButtonText: "Cancelar",
                showLoaderOnConfirm: true,
                preConfirm: async (email) => {
                    this.encryptedForm.username = await encrypt(email);
                    return instance.patch("/person/send/newCode", { username: this.encryptedForm.username })
                        .then((response) => {
                            if (response.status === 200) {
                                Swal.fire({
                                    title: "Código reenviado",
                                    text: "Hemos enviado un nuevo código de verificación a tu correo",
                                    icon: "success",
                                    showConfirmButton: true,
                                }).then(() => {
                                    Swal.fire({
                                        title: "Código de verificación",
                                        text: "Ingresa el código de verificación que te enviamos a tu correo",
                                        input: "text",
                                        showCancelButton: true,
                                        confirmButtonText: "Verificar",
                                        cancelButtonText: "Cancelar",
                                        showDenyButton: true,
                                        denyButtonText: "Reenviar código",
                                        preConfirm: async (code) => {
                                            let encryptedCode = await encrypt(code);
                                            return instance.patch("/person/activate/account", { activationCode: encryptedCode, username: this.encryptedForm.username })
                                                .then((response) => {
                                                    if (response.status === 200) {
                                                        Swal.fire({
                                                            title: "Cuenta verificada",
                                                            text: "Tu cuenta ha sido verificada con éxito, ahora puedes iniciar sesión",
                                                            icon: "success",
                                                            showConfirmButton: true,
                                                        });
                                                        this.$router.push("/login");
                                                    } else {
                                                        Swal.fire({
                                                            title: "Error",
                                                            text: "El código de verificación es incorrecto",
                                                            icon: "error",
                                                            showConfirmButton: true,
                                                        });
                                                    }
                                                })
                                                .catch((error) => {
                                                    Swal.fire({
                                                        title: "Error",
                                                        text: "Algo salió mal, por favor intenta de nuevo más tarde",
                                                        icon: "error",
                                                        showConfirmButton: true,
                                                    }).then(() => {
                                                        this.activateAccount();
                                                    });
                                                });
                                        },
                                        allowOutsideClick: false,
                                    });
                                });
                            } else {
                                Swal.fire({
                                    title: "Error",
                                    text: "Algo salió mal, por favor intenta de nuevo más tarde",
                                    icon: "error",
                                    showConfirmButton: true,
                                });
                            }
                        })
                },
                allowOutsideClick: false,
            });
        },
        async sendCodePassword() {
            Swal.fire({
                title: "Restablecer contraseña",
                text: "Ingresa el correo electrónico con el que te registraste, te enviaremos un código de verificación para restablecer tu contraseña",
                input: "text",
                inputAttributes: {
                    autocapitalize: "off",
                },
                showCancelButton: true,
                confirmButtonText: "Enviar código",
                cancelButtonText: "Cancelar",
                showLoaderOnConfirm: true,
                preConfirm: async (email) => {
                    let encryptedEmail = await encrypt(email);
                    try {
                        Swal.showLoading();
                        instance.patch("/person/send/confirmationCode", { username: encryptedEmail })
                        Swal.fire({
                            title: "Código enviado",
                            text: "Hemos enviado un código de verificación a tu correo",
                            icon: "success",
                            showConfirmButton: true,
                        }).then(() => {
                            Swal.fire({
                                title: "Código de verificación",
                                text: "Ingresa el código de verificación que te enviamos a tu correo",
                                input: "text",
                                showCancelButton: true,
                                confirmButtonText: "Verificar",
                                cancelButtonText: "Cancelar",
                                preConfirm: async (code) => {
                                    let encryptedCode = await encrypt(code);
                                    try {
                                        Swal.showLoading();
                                        instance.patch("/person/validate/code", { username: encryptedEmail, activationCode: encryptedCode })
                                        Swal.fire({
                                            title: "Código verificado",
                                            text: "Ingresa tu nueva contraseña",
                                            input: "password",
                                            showCancelButton: true,
                                            confirmButtonText: "Cambiar contraseña",
                                            cancelButtonText: "Cancelar",
                                            preConfirm: async (password) => {
                                                let encryptedPassword = await encrypt(password);
                                                let response = await instance.patch("/person/reset/password", { username: encryptedEmail, newPassword: encryptedPassword })
                                                if (response.status === 200) {
                                                    Swal.fire({
                                                        title: "Contraseña cambiada",
                                                        text: "Tu contraseña ha sido cambiada con éxito",
                                                        icon: "success",
                                                        showConfirmButton: true,
                                                    });
                                                } else {
                                                    Swal.fire({
                                                        title: "Error",
                                                        text: "Algo salió mal, por favor intenta de nuevo más tarde",
                                                        icon: "error",
                                                        showConfirmButton: true,
                                                    });
                                                }
                                            }
                                        })
                                    } catch (error) {
                                        Swal.fire({
                                            title: "Error",
                                            text: "El código de verificación es incorrecto",
                                            icon: "error",
                                            showConfirmButton: true,
                                        });
                                    }
                                }
                            })
                        })
                    } catch (error) {
                        Swal.fire({
                            title: "Error",
                            text: "Algo salió mal, por favor intenta de nuevo más tarde",
                            icon: "error",
                            showConfirmButton: true,
                        });
                    }

                }
            })
        }
    }
}
</script>

<template>
    <b-container fluid class="bg-blue">
        <b-row align-v="center">
            <b-col cols="6" class="px-5">
                <b-card class="shadow bg-orange">
                    <img src="../../../../assets/imgs/logo blanco.png" class="img-fluid d-block mx-auto"
                        alt="Responsive image" width="30%">
                    <b-form class="px-5">
                        <b-form-group class="my-3 text-white" label-for="input-email">
                            <label slot="label">
                                Correo Electrónico: <span class="required-asterisk">*</span>
                            </label>
                            <b-form-input class="bg-light shadow text-dark-gray-input" id="input-email"
                                v-model.trim="form.username" @input="validateInput('email')"
                                type="email"></b-form-input>
                            <b-form-invalid-feedback v-if="showErrors.email">{{ errors.email
                                }}</b-form-invalid-feedback>
                        </b-form-group>

                        <b-form-group class="my-3 text-white" label-for="input-password">
                            <label slot="label">
                                Contraseña: <span class="required-asterisk">*</span>
                            </label>
                            <b-input-group class="position-relative">
                                <b-form-input id="input-password" class="bg-light shadow text-dark-gray-input"
                                    v-model.trim="form.password" @input="validateInput('password')"
                                    type="password"></b-form-input>
                                <b-input-group-prepend>
                                    <b-button @click="togglePasswordVisibility" variant="outline-secondary"
                                        class="btn-eye rounded-right">
                                        <b-icon v-if="showPassword" icon="eye-slash" class="eye-icon"></b-icon>
                                        <b-icon v-else icon="eye" class="eye-icon"></b-icon>
                                    </b-button>
                                </b-input-group-prepend>
                            </b-input-group>
                            <b-form-invalid-feedback v-if="showErrors.password">{{
                                    errors.password
                                }}</b-form-invalid-feedback>
                        </b-form-group>
                        <b-row class="justify-content-center">
                            <b-col class="d-flex justify-content-center align-items-center mt-3">
                                <b-button class="bg-dark-secondary-orange text-white mx-5" @click="login()"
                                    :disabled="!form.isValid">Iniciar sesión</b-button>
                            </b-col>
                        </b-row>

                        <b-row class="justify-content-center">
                            <b-col class="d-flex justify-content-center align-items-center mt-3">
                                <b-link @click="sendCodePassword" class="text-dark-blue text-decoration-none">
                                    ¿No recuerdas tu contraseña? Resetear contraseña</b-link>
                            </b-col>
                        </b-row>
                        <b-row class="justify-content-center">
                            <b-col class="d-flex justify-content-center align-items-center mt-3">
                                <b-link to="/selfRegistration" class="text-dark-blue text-decoration-none">
                                    ¿No tienes cuenta? Regístrate</b-link>
                            </b-col>
                        </b-row>
                        <b-row class="justify-content-center">
                            <b-col class="d-flex justify-content-center align-items-center mt-3">
                                <b-link @click="activateAccount" class="text-dark-blue text-decoration-none">
                                    Activa tu cuenta</b-link>
                            </b-col>
                        </b-row>
                    </b-form>
                </b-card>
            </b-col>
            <b-col cols="6" class="text-center">
                <b-img :src="loginI" fluid left rounded class="img mx-auto" style="max-height: 570px;"></b-img>
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

.eye-icon {
    font-size: 1rem;
}

.btn-eye {
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
    background-color: #ffffff;
    border-color: #ffffff;
    color: black;
}
</style>