<template>
  <div class="blue mt-3 p-2">
    <h3 class="mt-3 mx-3">Estas son las mascotas que puedes adotar con nosotros...</h3>
    <div v-if="!enpty" >
      <b-carousel v-model="slide" :interval="4000" @sliding-start="onSlideStart" @sliding-end="onSlideEnd"
        class="mx-3 mt-4">
        <b-carousel-slide v-for="category in categories" :key="category.id">
          <template #img>
            <b-img :src="category.image" fluid alt="Imagen de la categoría" class="category-img" />
          </template>
          <div class="category-caption">
            <h3>{{ category.name }}</h3>
            <p>{{ truncateDescription(category.description) }}</p>
          </div>
        </b-carousel-slide>
      </b-carousel>
    </div>
    <div v-else class="class mx-2 my-5 text-center w-100 h-100">
      <h4 class="mt-3">No hay categorías registradas</h4>
      <h6 class="mt-4">
        Parece que no hay categorías registradas aún...
      </h6>
      <small class="mt-5">
        Mientras los administradores arreglan esto, mira un conejo.
      </small>
      <img class="imgNotCategories mt-4" src="https://cdn-icons-png.flaticon.com/512/7441/7441511.png"
        alt="Cargando..." />
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
    },
    truncateDescription(description) {
      return description.length > 200 ? `${description.substring(0, 200)}...` : description;
    },
  },
  mounted() {
    this.GetCategoriesForTheCarousel();
  },
};
</script>

<style scoped>
.category-img {
  width: 100% !important;
  height: 400px !important;
  object-fit: cover !important;
  border-radius: 12px;
}

.category-caption {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(255, 255, 255, 0.603);
  color: black;
  padding: 1rem;
  border-radius: 12px
}

.carousel-item {
  background-color: #118A95 !important;
}
</style>