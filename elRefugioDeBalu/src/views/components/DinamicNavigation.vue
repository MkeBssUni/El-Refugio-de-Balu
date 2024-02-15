<script>
import Swal from 'sweetalert2';

export default {
    data() {
        return {
            chevron: 'chevron-down',
            dropdownOpen: false,
        };
    },
    props: ['role'],
    mounted() {
        console.log("este es el role: ", this.role)
    },
    methods: {
        toggleDropdown() {
            this.chevron = this.dropdownOpen ? 'chevron-down' : 'chevron-up';
            this.dropdownOpen = !this.dropdownOpen;
        },
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
    <div v-if="role !== ''">

    </div>
    <div v-else>
        <b-navbar class="bg-blue d-flex align-items-center justify-content-between">
            <b-navbar-nav class="ms-4">
                <b-navbar-brand>
                    <img src="../../assets/imgs/logoSinLBlanco.png" alt="logo" width="40" height="40">
                </b-navbar-brand>
            </b-navbar-nav>
            <b-navbar-nav class="me-2 d-flex align-items-center">
                <b-nav-item class="me-2 d-none d-md-inline-block">
                    <b-button variant="outline-light" class="px-3 d-flex align-items-center">
                        <span style="font-size: 1rem;">Crear cuenta</span>
                        <b-icon icon="person-plus" font-scale="1" class="ms-2"></b-icon>
                    </b-button>
                </b-nav-item>
                <b-nav-item class="me-2 d-none d-md-inline-block">
                    <b-button variant="outline-light" class="px-3 d-flex align-items-center">
                        <span style="font-size: 1rem;">Iniciar sesión</span>
                        <b-icon icon="box-arrow-in-right" font-scale="1" class="ms-2"></b-icon>
                    </b-button>
                </b-nav-item>
                <b-nav-item class="me-2 d-inline-block d-md-none">
                    <b-button @click="toggleDropdown" variant="outline-light" class="px-3 d-flex align-items-center">
                        <b-icon icon="person" font-scale="1"></b-icon>
                        <b-icon :icon="chevron" font-scale="0.8" class="ms-2"></b-icon>
                    </b-button>
                    <div v-if="dropdownOpen" class="mt-2 menu">
                        <a href="#" class="item d-flex justify-content-between p-3">
                            <span>Crear cuenta</span>
                            <b-icon icon="person-plus" font-scale="1" class="ms-2"></b-icon>
                        </a>
                        <hr class="my-0">
                        <a href="#" class="item d-flex justify-content-between p-3">
                            <span>Iniciar sesión</span>
                            <b-icon icon="box-arrow-in-right" font-scale="1" class="ms-2"></b-icon>
                        </a>
                    </div>
                </b-nav-item>
                <b-nav-item>
                    <b-button v-b-toggle.sidebar-1 variant="outline-light" class="d-flex align-items-center">
                        <b-icon icon="list" font-scale="1"></b-icon>
                    </b-button>
                </b-nav-item>
            </b-navbar-nav>
        </b-navbar>

        <b-sidebar id="sidebar-1" width="300px" backdrop shadow visible no-header>
            <template #default="{ hide }">
                <div class="my-3 d-flex align-items-center justify-content-between">
                    <span class="ms-3 text-dark-secondary-blue" style="font-size: 1.4rem;">¡Bienvenido/a!</span>
                    <b-button class="closeButton me-3" @click="hide">
                        <b-icon icon="x" font-scale="1.8" class="text-dark"></b-icon>
                    </b-button>
                </div>
                <hr class="my-0">
                <b-nav vertical class="my-2 px-3 text-center">
                    <b-button variant="outline-dark-secondary-orange" :to="'/'"
                        class="mt-3 d-flex align-items-center justify-content-center">
                        Ver mascotas
                        <i class="material-icons ms-2" style="font-size: larger;">pets</i>
                    </b-button>
                    <b-button variant="outline-dark-secondary-orange" :to="'/'"
                        class="mt-3 d-flex align-items-center justify-content-center">
                        Antes de adoptar
                        <i class="material-icons ms-2" style="font-size: larger;">info</i>
                    </b-button>
                    <b-button variant="outline-dark-secondary-orange" :to="'/'"
                        class="mt-3 d-flex align-items-center justify-content-center">
                        Preguntas frecuentes (FAQ)
                        <i class="material-icons ms-2" style="font-size: larger;">quiz</i>
                    </b-button>
                </b-nav>
                <div class="mt-5">
                    <img src="../../assets//imgs/gatoxperro.png" width="300" alt="gatoxperro" fluid
                        style="position: absolute; bottom: 0;">
                </div>
            </template>
        </b-sidebar>
    </div>

    <!-- <div v-if="role === ''">
            <b-navbar class="bg-blue justify-content-between">
                
                <b-navbar-nav class="text-right text-white">
                    
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
            sidebar para usuario sin rol
            <div v-if="role == ''">
                <b-sidebar id="sidebar-1" title="Bienvenido" shadow visible width="300px" backdrop text-variant="dark-secondary-blue">
                    <b-nav vertical class="mt-2 px-3 py-3 text-center">
                        <b-button variant="outline" :to="'/'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">favorite</i>
                            Adoptar
                        </b-button>

                        <b-button variant="outline" :to="'/'"
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
                        width="55" height="40">
                </b-navbar-brand>
                <b-navbar-nav class="text-right text-white">
                    <b-row class="justify-content-start mx-3 py-2 text-center">
                        <b-col cols="3">

                            BOTONES PARA LOS DIFERENTES SIDEBAR
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

            sidebar para usuario ADOPTANTE
            <div v-if="role == 'ADOPTANTE'">
                <b-sidebar id="sidebar-2" title="Bienvenido" shadow visible width="300px" backdrop text-variant="dark-secondary-blue">
                    <b-nav vertical class="mt-2 px-3 py-3 text-center">
                        <b-button variant="outline" :to="'/'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">favorite</i>
                            Adoptar
                        </b-button>

                        <b-button variant="outline" :to="'/'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">handshake</i>
                            Dar en adopción
                        </b-button>

                        <b-button variant="outline" :to="'/'"
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
            SIDEBAR MODERADOR
            <div v-if="role == 'MODERADOR'">
                <b-sidebar id="sidebar-3" title="Bienvenido" shadow visible width="300px" backdrop text-variant="dark-secondary-blue">
                    <b-nav vertical class="mt-2 px-3 py-3 text-center">
                        <b-button variant="outline" :to="'/'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">pets</i>
                            Animales
                        </b-button>

                        <b-button variant="outline" :to="'/'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">dashboard</i>
                            Categorías
                        </b-button>

                        <b-button variant="outline" :to="'/'"
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
            SIDEBAR ADMINISTRADOR
            <div v-if="role == 'ADMINISTRADOR'">
                <b-sidebar id="sidebar-4" title="Bienvenido" shadow visible width="300px" backdrop text-variant="dark-secondary-blue">
                    <b-nav vertical class="mt-2 px-3 py-3 text-center">
                        <b-button variant="outline" :to="'/'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">pets</i>
                            Animales
                        </b-button>

                        <b-button variant="outline" :to="'/'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">dashboard</i>
                            Categorías
                        </b-button>

                        <b-button variant="outline" :to="'/'"
                            class="mt-3 button btn-outline-dark-secondary-orange">
                            <i class="material-icons">assignment</i>
                            Solicitudes de adopción
                        </b-button>
                        
                        <b-button variant="outline" :to="'/'"
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
        </div> -->
</template>

<style scoped>
.menu {
    position: absolute;
    top: 70%;
    left: 43%;
    min-width: 40%;
    border-radius: 6px;
    background-color: #ffffff;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
    z-index: 100;
}

.item {
    display: block;
    text-decoration: none;
    color: #000000;
    font-size: 1rem;
}

.item:hover {
    color: #686868;
    background-color: #F2F2F2;
}

.closeButton {
    padding: 0;    
    background-color: transparent;
    border: none;

}
.closeButton:hover {
    background-color: #F2F2F2;
}
</style>