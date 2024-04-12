<template>
  <div :style="{ backgroundColor: color }" class="contenedor">
    <b-container class="container">
      <b-row class="align-items-center">
        <b-col>
          <img :src="imagenUrl" class="tamImg" alt="header-image" />
        </b-col>
        <b-col>
          <h1 :style="{ color: textColor }" class="title">{{ titulo }}</h1>
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
  computed: {
    textColor() {
      // Lógica para determinar el color del texto basado en el color de fondo
      // Puedes personalizar esta lógica según tus necesidades
      return this.color && this.isColorDark(this.color) ? "#FFFFFF" : "#000000";
    },
  },
  methods: {
    isColorDark(hexColor) {
      // Lógica para determinar si el color de fondo es oscuro o claro
      // Puedes personalizar esta lógica según tus necesidades
      const rgb = parseInt(hexColor.slice(1), 16);
      const r = (rgb >> 16) & 0xff;
      const g = (rgb >> 8) & 0xff;
      const b = (rgb >> 0) & 0xff;
      const luma = 0.2126 * r + 0.7152 * g + 0.0722 * b;
      return luma < 128; // Valor de referencia para decidir si es oscuro o claro
    },
  },
};
</script>

<style scoped>
.contenedor {
  display: flex;
  flex-direction: column;
  height: 70%;
}

.container {
  width: 100%;
}

.title {
  text-align: center;
  margin: 0; /* Elimina el margen por defecto del h1 */
}

.tamImg {
  max-width: 100%;
  max-height: 200px;
  display: block; /* Elimina el espacio adicional en la parte inferior de la imagen */
  margin: 0 auto; /* Centra la imagen horizontalmente */
}
</style>
