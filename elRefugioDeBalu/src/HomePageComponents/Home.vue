<template>
    <b-container fluid>
        <b-row class="justify-content-center mt-3">
            <b-col class="header-homePage shadow" cols="5" md="5" lg="5" xl="5">
                <h1 class="title-headerPage mt-2 text-end">
                     Adopta amor,
                </h1>
            </b-col>
            <b-col class="header-homePageBlue shadow" cols="5" md="5" lg="5" xl="5">
                <h1 class="title-headerPage mt-2 text-start">
                    adopta vida

                </h1>
            </b-col>
        </b-row>
        <b-row class="px-4 mt-3 d-flex justify-content-center">
            <b-col class="px-4" cols="12" sm="12" md="10" lg="10" xl="11">
                <CarouselGalery class="d-none d-md-flex" />
            </b-col>
            <b-col class="px-0" cols="12" sm="12" md="10" lg="10" xl="11">
                <b-jumbotron bg-variant="info" class="d-md-none py-3 px-3">
                    <template #header>¿Quienes somos?</template>

                    <template #lead>
                        El refugio de Baloo es una organizacion que ayuda a nuestros amigos a encontrar un hogar, proporcionando servicios de adopciones y rescates, permitiendo que familias que estan en busca de un nuevo amigo, puedan encontralo facilmente...
                    </template>

                    <hr class="my-4">

                    <p>
                      Nuestro principal objetivo es que nuestros diferentes tipos de amigos puedan encontrar un hogar en donde sean amados y ellos puedan ser integrados como parte de una familia...
                    </p>

                    <b-button class="btn-outline-dark-blue" pill href="#">Inicia sesion para conocerlos <i class="material-icons" style="font-size: 1rem;">pets</i></b-button>
                    <b-button pill class="my-3 btn-outline-dark-blue" href="#">Registrate <i class="material-icons" style="font-size: 1rem;">pets</i> </b-button>

                </b-jumbotron>
            </b-col>
        </b-row>
        <b-row class="mb px-0 justify-content-center">
            <b-col class="ps-0" cols="11" sm="11" md="5" lg="5" xl="5">
                <Banner class="" />
            </b-col>
            <b-col class="my-0 px-0 pb-4 pt-3" cols="11" sm="11" md="5" lg="5" xl="5">
                <div id="carouselExampleIndicators" class="carousel pb-5 mb-0 slide h-100">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <div class="my-5 mx-5" id="12">
                                <b-row>
                                    <b-col class="pb-3">
                                        <h2>Conoce a nuestros amigos...</h2>
                                    </b-col>
                                </b-row>
                                <b-row class="d-flex justify-content-center align-items-center text-center">
                                    <b-col class="" cols="11" sm="11" md="5" lg="5" xl="5">
                                        <img src="../assets/imgs/tortugaJulia.jpg" class="imageFriends" alt="">
                                    </b-col>
                                    <b-col class="text-start ps-5" cols="11" sm="11" md="7" lg="7" xl="7">
                                        <div class="mt-3">

                                            <h4>Yo soy Julia</h4>
                                            <h5>Una tortuga orejas rojas...</h5>
                                            <p>Tlapan,CDMX</p>
                                        </div>
                                        <b-button pill variant="outline-danger">Adoptame <i
                                                class="material-icons">pets</i></b-button>
                                    </b-col>
                                </b-row>
                            </div>
                        </div>
                        <div class="carousel-item active">
                            <div class="my-5 mx-5" id="12">
                                <b-row>
                                    <b-col class="pb-3">
                                        <h2>Conoce a nuestros amigos...</h2>
                                    </b-col>
                                </b-row>
                                <b-row class="d-flex justify-content-center align-items-center text-center">
                                    <b-col class="" cols="11" sm="11" md="5" lg="5" xl="5">
                                        <img src="../assets/imgs/iguanaOmar.jpeg" class="imageFriends" alt="">
                                    </b-col>
                                    <b-col class="text-start ps-5" cols="11" sm="11" md="7" lg="7" xl="7">
                                        <div class="mt-3">

                                            <h4>Yo soy Omar</h4>
                                            <h5>Una iguana adulta</h5>
                                            <p>Guerrero, chilpancingo</p>
                                        </div>
                                        <b-button pill variant="outline-danger">Adoptame <i
                                                class="material-icons">pets</i></b-button>
                                    </b-col>
                                </b-row>
                            </div>
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
                        data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
                        data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>

            </b-col>
        </b-row>
        <b-row class="justify-content-center mt-3">
            <b-col class="px-1_5" cols="11" sm="11" md="11" lg="11" xl="10">
                <div class="title-headerPage info-homepage w-100 mb-2 py-3  text-center w-100">
                    <h4>Tu compañero ideal está esperando: descubre los amigos que tenemos para ti.</h4>
                    <b-button class="home"><i class="material-icons">pets</i> Vamos a conocerlos <i
                            class="material-icons">pets</i></b-button>
                </div>
            </b-col>
        </b-row>


    </b-container>
</template>

<script>
import CarouselGalery from '../HomePageComponents/Carrorusel.vue'
import Banner from '../HomePageComponents/Banner.vue'
export default {
    components: {
        CarouselGalery,
        Banner
    }
}
</script>

<style></style>divc