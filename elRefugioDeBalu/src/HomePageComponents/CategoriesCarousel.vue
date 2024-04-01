<template>
  <div class="blue mt-3">
    <h3 class="mt-3 mx-3">Las mascotas que puedes encontrar con nosotros...</h3>
    <div v-if='!enpty'>
      <b-carousel
        class="carousel-img"
        v-model="slide"
        :interval="4000"
        @sliding-start="onSlideStart"
        @sliding-end="onSlideEnd"
      >
        <b-carousel-slide
          v-for="category in categories"
          :key="category.id"
          :caption="category.name"
          :text="category.description"
          :img-src="category.image"
        ></b-carousel-slide>
      </b-carousel>
    </div>
    <div v-else class="carousel-img class mx-2 my-5">
     <h4 class="mt-5">Parece que no hay categorías registradas</h4>
     <p class="mt-3 mb-5 pb-5">Parece que no hay categorías registradas aún...</p>
    </div>
  </div>
</template>

<script>
import instance from "../config/axios";
export default {
  name: "categoriesCarousel",
  data() {
    return {
      slide: 0,
      sliding: null,
      categories: [],
      enpty:null,
    };
  },
  methods: {
    onSlideStart(slide) {
      this.sliding = true;
    },
    onSlideEnd(slide) {
      this.sliding = false;
    },
    async GetCategoriesForTheCarousel() {
  try {
    const response = await instance.get("/category/carousel");
    this.categories = response.data.data;
    this.enpty= this.categories== null
  } catch (error) {
    console.error("Error al obtener categorías:", error);
  }
},

  },
  mounted() {
    this.GetCategoriesForTheCarousel();
  },
};
</script>