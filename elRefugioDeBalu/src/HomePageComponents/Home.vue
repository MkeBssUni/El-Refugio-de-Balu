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
                            <CarouselGalery/>
                        </b-container>

                    </b-col>
                </b-row>
                <b-row class="mt-3 justify-content-center">
                    <b-col class="" cols="11">
                         <Banner/>
                    </b-col>
                    <b-col cols="11">
                        <b-carousel id="carousel-1" v-model="slide" :interval="4000" controls indicators
                            background="#ababab" img-width="1024" img-height="480" style="text-shadow: 1px 1px 2px #333;"
                            @sliding-start="onSlideStart" @sliding-end="onSlideEnd">
                            <!-- Text slides with image -->
                            <b-carousel-slide caption="First slide"
                                text="Nulla vitae elit libero, a pharetra augue mollis interdum."
                                img-src="https://picsum.photos/1024/480/?image=52"></b-carousel-slide>

                            <!-- Slides with custom text -->
                            <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=54">
                                <h1>Hello world!</h1>
                            </b-carousel-slide>

                            <!-- Slides with image only -->
                            <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=58"></b-carousel-slide>

                            <!-- Slides with img slot -->
                            <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
                            <b-carousel-slide>
                                <template #img>
                                    <img class="d-block img-fluid w-100" width="1024" height="480"
                                        src="https://picsum.photos/1024/480/?image=55" alt="image slot">
                                </template>
                            </b-carousel-slide>

                            <!-- Slide with blank fluid image to maintain slide aspect ratio -->
                            <b-carousel-slide caption="Blank Image" img-blank img-alt="Blank image">
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eros felis,
                                    tincidunt
                                    a tincidunt eget, convallis vel est. Ut pellentesque ut lacus vel interdum.
                                </p>
                            </b-carousel-slide>
                        </b-carousel>
                    </b-col>
                </b-row>
                <b-row class=" my-3 justify-content-center mx-3">
                    <b-col class="info-homepage" cols="12">
                        <div class="title-headerPage mt-5 mb-3 text-center">
                            <h4>Tu compañero ideal está esperando: descubre los amigos que tenemos para ti.</h4>
                            <b-button>Vamos a conocerlos</b-button>
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