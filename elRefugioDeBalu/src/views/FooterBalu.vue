<template>
    <footer class="bg-orange text-light py-4 mt-5">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-3">
                    <img class="py-3 mx-3" src="../assets/imgs/logo blanco.png" alt="logo-elRefugio" width="180">
                </div>
                <div class="col-12 col-md-4 mt-3">
                    <h3 class="mb-4">
                        Páginas principales
                    </h3>
                    <ul class="list-unstyled">
                        <li><router-link to="/">Inicio</router-link></li>
                        <li><router-link to="/adoptar">Adoptar</router-link></li>
                        <li><router-link to="/dar-en-adopcion">Dar en adopción</router-link></li>
                        <li><router-link to="/crear-cuenta">Crear cuenta</router-link></li>
                    </ul>
                </div>
                <div class="col-12 col-md-4 mt-3">
                    <h3 class="mb-4">
                        Información de contacto
                    </h3>
                    <ul class="list-unstyled">
                        <li><a href="https://www.facebook.com/paginadefacebook">
                            <b-icon icon="facebook" font-scale="1" class="me-2"></b-icon>
                            Facebook: Página DeFacebok</a></li>
                        <li><a href="https://www.instagram.com/perfildelg">
                            <b-icon icon="instagram" font-scale="1" class="me-2"></b-icon>
                            Instagram: PerfilDelg</a></li>
                        <li><a href="mailto:uncorreo@gmail.com">
                            <b-icon icon="envelope" font-scale="1" class="me-2"></b-icon>
                            Correo electrónico: uncorreo@gmail.com</a></li>
                    </ul>
                </div>
            </div>
            <div class="row">
                <div class="col-12 text-center">
                    <p class="mb-0">© 2024 El Refugio de Balu</p>
                </div>
            </div>
        </div>
    </footer>
</template>

<script>
export default {

}
</script>

<style>
ul li a {
    text-decoration: none;
    color: white;
}
</style>