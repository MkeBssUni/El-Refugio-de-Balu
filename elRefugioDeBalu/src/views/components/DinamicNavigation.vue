<script>
import Swal from 'sweetalert2';

export default {
    props: ['role'],
    mounted() {
        console.log("este es el role: ", this.role)
    },
    methods: {
        cerrarSesion() {
            console.log("click en cerrar sesion")
            Swal.fire({
                title: "¿Estás seguro?",
                text: "¿Estás seguro que deseas cerrar sesión? Deberás iniciar sesión nuevamente para poder acceder a tu cuenta.",
                icon: "warning",
                iconColor: "#FF0000",
                showCancelButton: true,
                showConfirmButton: true,
                confirmButtonText: "Continuar",
                cancelButtonText: "Cancelar",
            }).then((result) => {
                console.log("result: ", result)
                if (result.isConfirmed) {
                    console.log("cerrar sesion")
                } else {
                    console.log("no cerrar sesion")
                }
            });
        }
    }
}
</script>

<template>
    <div>
        <div v-if="role === ''">
            <b-navbar class="bg-blue justify-content-between">
                <b-navbar-brand style="margin-left: 10px;!important">
                    <img class="d-inline-block align-text-top" src="../../assets/imgs/logoSinLBlanco.png" alt="logo"
                        width="55" height="50">
                </b-navbar-brand>
                <b-navbar-nav class="text-right text-white">
                    <b-row class="justify-content-start mx-3 py-2 text-center">
                        <b-col cols="12">
                            <b-nav-item>
                                <p style="font-size: x-large;" class="text-white text-center">Crear cuenta</p>
                            </b-nav-item>
                        </b-col>
                    </b-row>
                    <b-row class="justify-content-start mx-3 py-2 text-center">
                        <b-col cols="12">
                            <b-nav-item to="/login">
                                <p style="font-size: x-large;" class="text-white text-center">Iniciar Sesión</p>
                            </b-nav-item>
                        </b-col>
                    </b-row>
                    <b-row class="justify-content-start mx-3 py-2 text-center">
                        <div v-if="role === ''">
                            <b-col cols="12">
                                <b-button v-b-toggle.sidebar-1 variant="outline-light" class="me-2">
                                    <b-icon icon="list" font-scale="1.5"></b-icon>
                                </b-button>
                            </b-col>
                        </div>
                    </b-row>
                </b-navbar-nav>
            </b-navbar>
            <!-- sidebar para usuario sin rol-->
            <div v-if="role == ''">
                <b-sidebar id="sidebar-1" title="Bienvenido" shadow visible width="300px" backdrop text-variant="dark-secondary-blue">
                    <b-nav vertical class="mt-2 px-3 py-3 text-center">
                        <b-button variant="outline" :to="'/rentar-espacio'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">favorite</i>
                            Adoptar
                        </b-button>

                        <b-button variant="outline" :to="'/rentar-espacio'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">handshake</i>
                            Dar en adopción
                        </b-button>
                    </b-nav>
                    <div class="mt-5">
                        <img src="../../assets//imgs/gatoxperro.png" width="300" alt="gatoxperro" fluid
                            style="position: absolute; bottom: 0;">
                    </div>
                </b-sidebar>
            </div>
        </div>

        <div v-if="role != ''">
            <b-navbar class="bg-blue justify-content-between">
                <b-navbar-brand style="margin-left: 10px;!important">
                    <img class="d-inline-block align-text-top" src="../../assets/imgs/logoSinLBlanco.png" alt="logo"
                        width="55" height="50">
                </b-navbar-brand>
                <b-navbar-nav class="text-right text-white">
                    <b-row class="justify-content-start mx-3 py-2 text-center">
                        <b-col cols="3">

                            <!-- BOTONES PARA LOS DIFERENTES SIDEBAR -->
                            <div v-if="role === 'ADOPTANTE'">
                                <b-button v-b-toggle.sidebar-2 variant="outline-light" class="me-2">
                                    <b-icon icon="list" font-scale="1.5"></b-icon>
                                </b-button>
                            </div>

                            <div v-if="role === 'MODERADOR'">
                                <b-button v-b-toggle.sidebar-3 variant="outline-light" class="me-2">
                                    <b-icon icon="list" font-scale="1.5"></b-icon>
                                </b-button>
                            </div>
                            
                            <div v-if="role === 'ADMINISTRADOR'">
                                <b-button v-b-toggle.sidebar-4 variant="outline-light" class="me-2">
                                    <b-icon icon="list" font-scale="1.5"></b-icon>
                                </b-button>
                            </div>

                        </b-col>
                        <b-col cols="9">
                            <b-nav-item>
                                <p style="font-size: x-large;" class="text-white text-center" @click="cerrarSesion">Cerrar
                                    sesión</p>
                            </b-nav-item>
                        </b-col>
                    </b-row>
                </b-navbar-nav>
            </b-navbar>

            <!-- sidebar para usuario ADOPTANTE -->
            <div v-if="role == 'ADOPTANTE'">
                <b-sidebar id="sidebar-2" title="Bienvenido" shadow visible width="300px" backdrop text-variant="dark-secondary-blue">
                    <b-nav vertical class="mt-2 px-3 py-3 text-center">
                        <b-button variant="outline" :to="'/rentar-espacio'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">favorite</i>
                            Adoptar
                        </b-button>

                        <b-button variant="outline" :to="'/rentar-espacio'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">handshake</i>
                            Dar en adopción
                        </b-button>

                        <b-button variant="outline" :to="'/rentar-espacio'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">handshake</i>
                            Mis solicitudes
                        </b-button>
                    </b-nav>
                    <div class="mt-5">
                        <img src="../../assets//imgs/gatoxperro.png" width="300" alt="gatoxperro" fluid
                            style="position: absolute; bottom: 0;">
                    </div>
                </b-sidebar>
            </div>
            <!-- SIDEBAR MODERADOR -->
            <div v-if="role == 'MODERADOR'">
                <b-sidebar id="sidebar-3" title="Bienvenido" shadow visible width="300px" backdrop text-variant="dark-secondary-blue">
                    <b-nav vertical class="mt-2 px-3 py-3 text-center">
                        <b-button variant="outline" :to="'/rentar-espacio'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">pets</i>
                            Animales
                        </b-button>

                        <b-button variant="outline" :to="'/animalCategories'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">dashboard</i>
                            Categorías
                        </b-button>

                        <b-button variant="outline" :to="'/rentar-espacio'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">assignment</i>
                            Solicitudes de adopción
                        </b-button>
                    </b-nav>
                    <div class="mt-5">
                        <img src="../../assets//imgs/gatoxperro.png" width="300" alt="gatoxperro" fluid
                            style="position: absolute; bottom: 0;">
                    </div>
                </b-sidebar>
            </div>
            <!-- SIDEBAR ADMINISTRADOR -->
            <div v-if="role == 'ADMINISTRADOR'">
                <b-sidebar id="sidebar-4" title="Bienvenido" shadow visible width="300px" backdrop text-variant="dark-secondary-blue">
                    <b-nav vertical class="mt-2 px-3 py-3 text-center">
                        <b-button variant="outline" :to="'/rentar-espacio'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">pets</i>
                            Animales
                        </b-button>

                        <b-button variant="outline" :to="'/animalCategories'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">dashboard</i>
                            Categorías
                        </b-button>

                        <b-button variant="outline" :to="'/rentar-espacio'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">assignment</i>
                            Solicitudes de adopción
                        </b-button>
                        
                        <b-button variant="outline" :to="'/rentar-espacio'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">person</i>
                            Usuarios
                        </b-button>
                    </b-nav>
                    <div class="mt-5">
                        <img src="../../assets//imgs/gatoxperro.png" width="300" alt="gatoxperro" fluid
                            style="position: absolute; bottom: 0;">
                    </div>
                </b-sidebar>
            </div>
        </div>
    </div>
</template>

<style scoped>
#button {
    width: 10px;
    height: 50px;
    margin-bottom: 20px;
    display: flex;
    align-items: center;
    justify-content: center;
    box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.20);
}
</style>