<template>
  <div class="blue mt-3 border">
    <h3 class="mt-3 mx-3">Las mascotas que puedes encontrar con nosotros...</h3>
    <div v-if="!enpty">
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
    <div v-else class="class mx-2 my-5 text-center h-100">
      <h4 class="mt-5">Parece que no hay categorías registradas</h4>
      <h6 class="mt-4">
        Parece que no hay categorías registradas aún...
      </h6>
      <small class="mt-5">
        Mientras los administradores arreglan esto, mira un conejo.
      </small>
      <img class="imgNotCategories mt-4" src="https://cdn-icons-png.flaticon.com/512/7441/7441511.png" alt="Cargando..." />
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
      enpty: null,
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
      this.enpty = false;
      try {
        const response = await instance.get("/category/carousel");
        this.categories = response.data.data;
        this.enpty = this.categories.length === 0;
      } catch (error) {
        this.enpty = true;
      }
      console.log(this.enpty);
    },
  },
  mounted() {
    this.GetCategoriesForTheCarousel();
  },
};
</script>