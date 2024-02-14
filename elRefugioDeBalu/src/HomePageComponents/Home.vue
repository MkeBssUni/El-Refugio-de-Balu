<template>
    <b-container fluid class="border">
        <b-row>
            <b-col cols="12" class="mt-5">
                <b-row class="justify-content-center border">
                    <b-col class="header-homePage shadow" cols="5">
                        <h1 class="title-headerPage mt-2 pe-0 text-end">
                            Adopta amor,
                        </h1>
                    </b-col>
                    <b-col class="header-homePageBlue shadow" cols="5">
                        <h1 class="title-headerPage mt-2 ps-0 text-start">
                            adopta vida
                        </h1>
                    </b-col>
                </b-row>
                <b-row class="px-4 mt-2 justify-content-center">
                    <b-col class="mx-5">
                        <b-container>
                            <CarouselGalery />
                        </b-container>

                    </b-col>
                </b-row>
                <b-row class="mt-3 px-5">
                    <b-col class="" cols="11" sm="11" md="5" lg="5" xl="5">
                        <Banner />
                    </b-col>
                    <b-col cols="11" sm="11" md="5" lg="5" xl="5">
                        <b-carousel id="carousel-1" v-model="slide" :interval="4000" controls indicators>


                            <b-carousel-slide class="friends" img-blank>
                                <div class="friends border pt-3">
                                    <b-row class="border mt-5">
                                        <b-col class="pt-5" cols="112" sm="12" md="5" lg="5" xl="5">
                                            <b-img class="mt-5" v-bind="mainProps" fluid src="https://picsum.photos/250/250/?image=54"
                                                rounded alt="Rounded image"></b-img>
                                        </b-col>
                                        <b-col cols="12" sm="12 " md="5" lg="5" xl="5">
                                            <b-row>
                                    
                                            </b-row>
                                        </b-col>
                                    </b-row>
                                </div>
                            </b-carousel-slide>
                        </b-carousel>
                    </b-col>
                </b-row>
                <b-row class=" my-3 justify-content-center mx-3">
                    <b-col class="info-homepage" cols="12">
                        <div class="title-headerPage mt-5 mb-3 text-center">
                            <h4>Tu compañero ideal está esperando: descubre los amigos que tenemos para ti.</h4>
                            <b-button class="home">Vamos a conocerlos</b-button>
                        </div>
                    </b-col>
                </b-row>
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