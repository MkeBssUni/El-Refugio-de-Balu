<template>
  <div :style="{ backgroundColor: color }" class="contenedor">
    <b-container>
      <b-row>
        <b-col>
          <img src="../../assets/perrogato.png" class="tamImg" />
        </b-col>
        <b-col>
          <h1 class="title">{{ titulo }}</h1>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
export default {
  name: "Encabezado",
  props: {
    color: {
      type: String,
      default: null,
    },
    titulo: {
      type: String,
      default: null,
    },
    imagenUrl: {
      type: String,
      default: null,
    },
  },
};
</script>

<style>
.contenedor {
  display: flex;
  flex-direction: column;
  height: 70%;
}

.title {
  float: left;
  display: grid;
  grid-template-columns: auto 1fr;
  color: #FFFF,
}

.tamImg {
  width: 100%;
}
</style>
