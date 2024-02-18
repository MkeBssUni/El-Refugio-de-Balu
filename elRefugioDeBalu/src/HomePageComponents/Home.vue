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
                <CarouselGalery />
            </b-col>
        </b-row>
        <b-row class="mb px-0 justify-content-center">
            <b-col class="ps-0" cols="11" sm="11" md="5" lg="5" xl="5">
                <Banner class="w-100" />
            </b-col>
            <b-col class="my-0 px-0 " cols="11" sm="11" md="5" lg="5" xl="5">
                <div class="friends mt-3 p-2 mb-0">
                    <h4 class="mb-0">Conoce a nuestros amigos...</h4>
                </div>
                <b-carousel v-model="slide" img-width="1050" img-height="960" :interval="4000" controls>

                    <b-carousel-slide img-blank class="d-flex justify-content-center align-items-center" caption-html=' <div>
                            <b-row class="border d-flex justify-content-center align-items-center">
                                <b-col class="border" cols="11" sm="11" md="5" lg="5" xl="5">
                                    <img src="https://picsum.photos/250/250/?image=54"
                                        style="max-width: 120rem; max-height: 80rem; object-fit: contain;" rounded></img>
                                </b-col>
                                <b-col class="border" cols="11" sm="11" md="5" lg="5" xl="5"></b-col>
                            </b-row>
                        </div>'>
                    </b-carousel-slide>
                </b-carousel>
            </b-col>
        </b-row>
        <b-row class="justify-content-center mt-3">
            <b-col class="px-1_5" cols="11" sm="11" md="11" lg="11" xl="10">
                <div class="title-headerPage info-homepage w-100 mb-2 py-3  text-center w-100">
                    <h4>Tu compañero ideal está esperando: descubre los amigos que tenemos para ti.</h4>
                    <b-button class="home">Vamos a conocerlos</b-button>
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