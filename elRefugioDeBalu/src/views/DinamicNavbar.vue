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
            }).then((result)=>{
                console.log("result: ", result)
                if(result.isConfirmed){
                    console.log("cerrar sesion")
                }else{
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
                    <img class="d-inline-block align-text-top" src="../assets/imgs/logoSinLBlanco.png" alt="logo" width="55"
                        height="50">
                </b-navbar-brand>
                <b-navbar-nav class="text-right text-white">
                    <b-row class="justify-content-end mx-5">
                        <b-col cols="12">
                            <b-nav-item>
                                <p class="text-white" style="font-weight: bold;">Crear cuenta</p>
                            </b-nav-item>
                        </b-col>
                    </b-row>

                    <b-row class="justify-content-end mx-5">
                        <b-col cols="12">
                            <b-nav-item>
                                <p class="text-white" style="font-weight: bold;">Iniciar Sesión</p>
                            </b-nav-item>
                        </b-col>
                    </b-row>
                </b-navbar-nav>
            </b-navbar>
        </div>

        <div v-if="role != ''">
            <b-navbar class="bg-blue justify-content-between">
                <b-navbar-brand style="margin-left: 10px;!important">
                    <img class="d-inline-block align-text-top" src="../assets/imgs/logoSinLBlanco.png" alt="logo" width="55"
                        height="50">
                </b-navbar-brand>
                <b-navbar-nav class="text-right text-white">

                    <b-row class="justify-content-end mx-5">
                        <b-col cols="12">
                            <b-nav-item>
                                <p class="text-white" @click="cerrarSesion" style="font-weight: bold;">Cerrar sesión</p>
                            </b-nav-item>
                        </b-col>
                    </b-row>
                </b-navbar-nav>
            </b-navbar>
        </div>


    </div>
</template>