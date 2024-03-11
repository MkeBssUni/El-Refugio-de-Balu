<script>
import Swal from 'sweetalert2';
import { decrypt } from '../../kernel/hashFunctions';

export default {
    data() {
        return {
            chevron: 'chevron-down',
            dropdownOpen: false,
            role: null
        };
    },
    mounted() {
        (async () => {
            await this.getRole();
        })();
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
        },
        async getRole(){
            let decryptedRole = localStorage.getItem('role');
            if(decryptedRole !== null){
                decryptedRole = await decrypt(decryptedRole)
                this.role = decryptedRole;
            }else{
                this.role = '';
            }
        }
    }
}
</script>

<template>
    <div>
        <b-navbar class="bg-blue d-flex align-items-center justify-content-between">
            <b-navbar-nav class="ms-4">
                <b-navbar-brand>
                    <img src="../../assets/imgs/logoSinLBlanco.png" alt="logo" width="40" height="40">
                </b-navbar-brand>
            </b-navbar-nav>
            <b-navbar-nav class="me-2 d-flex align-items-center">
                <b-nav-item class="me-2 d-none d-md-inline-block">
                    <b-button to="/selfRegistration" variant="outline-light" class="px-3 d-flex align-items-center">
                        <span v-if="role" style="font-size: 1rem;">Ver perfil</span>
                        <span v-else style="font-size: 1rem;">Crear cuenta</span>
                        <b-icon :icon="role !== '' ? 'person' : 'person-plus'" font-scale="1" class="ms-2"></b-icon>
                    </b-button>
                </b-nav-item>
                <b-nav-item class="me-2 d-none d-md-inline-block">
                    <b-button variant="outline-light" class="px-3 d-flex align-items-center">
                        <span v-if="role" style="font-size: 1rem;">Cerrar sesión</span>
                        <span v-else style="font-size: 1rem;">Iniciar sesión</span>
                        <b-icon :icon="role ? 'box-arrow-right' : 'box-arrow-in-right'" font-scale="1"
                            class="ms-2"></b-icon>
                    </b-button>
                </b-nav-item>
                <b-nav-item class="me-2 d-inline-block d-md-none">
                    <b-button @click="toggleDropdown" variant="outline-light" class="px-3 d-flex align-items-center">
                        <b-icon icon="person" font-scale="1"></b-icon>
                        <b-icon :icon="chevron" font-scale="0.8" class="ms-2"></b-icon>
                    </b-button>
                    <div v-if="dropdownOpen" class="mt-2 menu">
                        <a href="#" class="item-1 d-flex justify-content-between p-3">
                            <span v-if="role">Ver perfil</span>
                            <span v-else>Crear cuenta</span>
                            <b-icon :icon="role ? 'person' : 'person-plus'" font-scale="1" class="ms-2"></b-icon>
                        </a>
                        <hr class="my-0">
                        <a href="#" class="item-2 d-flex justify-content-between p-3">
                            <span v-if="role">Cerrar sesión</span>
                            <span v-else>Iniciar sesión</span>
                            <b-icon :icon="role ? 'box-arrow-right' : 'box-arrow-in-right'" font-scale="1"
                                class="ms-2"></b-icon>
                        </a>
                    </div>
                </b-nav-item>
                <b-nav-item>
                    <b-button v-b-toggle.sidebar variant="outline-light" class="d-flex align-items-center">
                        <b-icon icon="list" font-scale="1"></b-icon>
                    </b-button>
                </b-nav-item>
            </b-navbar-nav>
        </b-navbar>
        <b-sidebar id="sidebar" width="300px" backdrop shadow visible no-header>
            <template #default="{ hide }">
                <div class="my-3 d-flex align-items-center justify-content-between">
                    <span class="ms-3 text-dark-secondary-blue" style="font-size: 1.4rem;">¡Bienvenido/a!</span>
                    <b-button class="closeButton me-3" @click="hide">
                        <b-icon icon="x" font-scale="1.8" class="text-dark"></b-icon>
                    </b-button>
                </div>
                <hr class="my-0">
                <b-nav v-if="role === 'GENERAL'" vertical class="my-2 px-3 text-center">
                    <b-button variant="outline-dark-secondary-orange" :to="'/pets'"
                        class="mt-3 d-flex align-items-center justify-content-center">
                        Ver mascotas
                        <i class="material-icons ms-2" style="font-size: larger;">pets</i>
                    </b-button>
                    <b-button variant="outline-dark-secondary-orange" :to="'/'"
                        class="mt-3 d-flex align-items-center justify-content-center">
                        Dar en adopción
                        <i class="material-icons ms-2" style="font-size: larger;">handshake</i>
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
                <b-nav v-else-if="role === 'MODERADOR'" vertical class="my-2 px-3 text-center">
                    <b-button variant="outline-dark-secondary-orange" :to="'/'"
                        class="mt-3 d-flex align-items-center justify-content-center">
                        Solicitudes de adopción
                        <i class="material-icons ms-2" style="font-size: larger;">assignment</i>
                    </b-button>
                </b-nav>
                <b-nav v-else-if="role === 'ADMINISTRADOR'" vertical class="my-2 px-3 text-center">
                    <b-button variant="outline-dark-secondary-orange" :to="'/UsersList'"
                        class="mt-3 d-flex align-items-center justify-content-center">
                        Gestión de usuarios
                        <i class="material-icons ms-2" style="font-size: larger;">person</i>
                    </b-button>
                    <b-button variant="outline-dark-secondary-orange" :to="'/'"
                        class="mt-3 d-flex align-items-center justify-content-center">
                        Gestión de categorías
                        <i class="material-icons ms-2" style="font-size: larger;">dashboard</i>
                    </b-button>
                    <b-button variant="outline-dark-secondary-orange" :to="'/pet-list'"
                        class="mt-3 d-flex align-items-center justify-content-center">
                        Gestión de animales
                        <i class="material-icons ms-2" style="font-size: larger;">pets</i>
                    </b-button>
                    <b-button variant="outline-dark-secondary-orange" :to="'/'"
                        class="mt-3 d-flex align-items-center justify-content-center">
                        Solicitudes de adopción
                        <i class="material-icons ms-2" style="font-size: larger;">assignment</i>
                    </b-button>
                </b-nav>
                <b-nav v-else vertical class="my-2 px-3 text-center">
                    <b-button variant="outline-dark-secondary-orange" :to="'/pets'"
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
</template>

<style scoped>
.menu {
    position: absolute;
    top: 70%;
    left: 43%;
    width: 40%;
    border-radius: 6px !important;
    background-color: #ffffff;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
    z-index: 100;
}


.item-1,
.item-2 {
    text-decoration: none;
    color: #000000;
    font-size: 1rem;
}


.item-1:hover {
    color: #686868;
    background-color: #F2F2F2;
    border-top-right-radius: 6px;
    border-top-left-radius: 6px;
}


.item-2:hover {
    color: #686868;
    background-color: #F2F2F2;
    border-bottom-right-radius: 6px;
    border-bottom-left-radius: 6px;
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