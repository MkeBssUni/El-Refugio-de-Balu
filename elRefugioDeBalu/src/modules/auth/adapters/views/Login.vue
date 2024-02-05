<script>
export default {
    data() {
        return {
            form: {
                email: '',
                password: '',
                isValid: false
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
                //validPassword: /^(?=.*[A-Z]+)(?=.*[._#]+)(?=.*[0-9]+)[a-zA-Z0-9._#]{3,16}$/
            }
        }
    },
    methods: {
        validateEmail() {
            let input = document.getElementById('input-email');
            if (this.form.email === '') {
                this.errors.email = 'Campo requerido';
                input.classList.add('is-invalid');
                this.showErrors.email = true;
            } else if (!this.arrayRegex.validUsername.test(this.form.email)) {
                input.classList.add('is-invalid');
                this.errors.email = 'Campo no válido';
                this.showErrors.email = true;
            } else {
                input.classList.remove('is-invalid');
                input.classList.add('is-valid');
                this.showErrors.email = false;
            }
        },
        validatePassword() {
            let input = document.getElementById('input-password');
            if (this.form.password === '') {
                this.errors.password = 'Campo requerido';
                input.classList.add('is-invalid');
                this.showErrors.password = true;
            } else {
                input.classList.remove('is-invalid');
                input.classList.add('is-valid');
                this.showErrors.password = false;
            }
        }
    }
}
</script>
<template>
    <b-container fluid class="bg-blue">
        <b-row>
            <!-- Formulario inicio de sesión -->
            <b-col cols="4">
                <b-card class="shadow bg-orange">
                    <img src="../../../../assets/imgs/logo blanco.png" class="img-fluid d-block mx-auto"
                        alt="Responsive image" width="30%">
                    <b-form class="px-5">
                        <b-form-group class="my-3 text-white" label="Correo electrónico:" label-for="input-email">
                            <b-form-input class="bg-light shadow text-dark-gray-input" id="input-email"
                                v-model.trim="form.email" @input="validateEmail" @focus="validateEmail"
                                type="email"></b-form-input>
                            <b-form-invalid-feedback v-if="showErrors.email">{{ errors.email }}</b-form-invalid-feedback>
                        </b-form-group>

                        <b-form-group class="my-3 text-white" label="Contraseña:" label-for="input-password">
                            <!--Pendiente añadir icono de ver contraseña -->
                            <b-form-input id="input-password" class="bg-light shadow text-dark-gray-input"
                                v-model.trim="form.password" @input="validatePassword" @focus="validatePassword"
                                type="password"></b-form-input>
                            <b-form-invalid-feedback v-if="showErrors.password">{{ errors.password
                            }}</b-form-invalid-feedback>
                        </b-form-group>
                        <!-- centrar el botón -->
                        <b-row class="justify-content-center">
                            <b-col class="d-flex justify-content-center align-items-center mt-3">
                                <b-button class="bg-dark-secondary-orange text-white mx-5" type="submit">Iniciar
                                    sesión</b-button>
                            </b-col>
                        </b-row>

                        <b-row class="justify-content-center">
                            <b-col class="d-flex justify-content-center align-items-center mt-3">
                                <b-link to="/auth/register" class="text-dark-blue text-decoration-none">
                                    ¿No tienes cuenta? Regístrate</b-link>
                            </b-col>
                        </b-row>
                    </b-form>
                </b-card>
            </b-col>
            <!-- Image -->
            <b-col>
                <!-- la imagen debe estar pegada del lado izquierdo y no debe exceder el alto de la pantalla -->
                <b-img
                        src="https://img.freepik.com/foto-gratis/ai-generado-perro-labrador-retriever_23-2150686788.jpg?w=740&t=st=1707087667~exp=1707088267~hmac=fef11794c20b1e6d8d9dea1a348eb8e50c293ef7c414c3bb0ec333617e044beb"
                        fluid left

                        ></b-img>
            </b-col>
        </b-row>
    </b-container>
</template>