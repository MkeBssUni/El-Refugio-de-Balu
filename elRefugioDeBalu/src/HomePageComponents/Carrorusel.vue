<template>
    <b-container fluid>
        <b-row>
            <b-col cols="12" class="text-center mt-5 position-relative">
                <b-row class="border">
                    <b-col class="header-homePage ms-5  shadow">
                        <h1 class="title-headerPage mt-2 pe-0 text-end">
                            Adopta amor,
                        </h1>
                    </b-col>
                    <b-col class="header-homePageBlue me-5  shadow">
                        <h1 class="title-headerPage mt-2 ps-0 text-start">
                            adopta vida
                        </h1>
                    </b-col>
                </b-row>
                <b-row class="border">
                    <b-carousel id="carousel-fade" style="text-shadow: 0px 0px 2px #000" fade indicators img-width="1024"
                        img-height="480">
                        <b-carousel-slide caption="First Slide"
                            img-src="https://picsum.photos/1024/480/?image=10"></b-carousel-slide>
                        <b-carousel-slide caption="Second Slide"
                            img-src="https://picsum.photos/1024/480/?image=12"></b-carousel-slide>
                        <b-carousel-slide caption="Third Slide"
                            img-src="https://picsum.photos/1024/480/?image=22"></b-carousel-slide>
                    </b-carousel>
                </b-row>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
export default {

}
</script>

<style></style>