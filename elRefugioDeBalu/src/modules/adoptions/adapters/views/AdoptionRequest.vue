<template>
  <div class="">
   <Encabezado color="#FFBC58" :imagenUrl="require('@/assets/imgs/mascotas.png')" titulo="Lista de Adopciones en espera" />
   <p>Contenido o tabla</p>
  </div>
</template>

<script>
import Swal from 'sweetalert2';
import Encabezado from '../../../../views/components/Encabezado.vue';

export default {
  name: "AdoptioRequest",
  components:{
    Encabezado
  },
  methods: {
    showAlert() {
      Swal.fire({
        title: "Good job!",
        text: "You clicked the button!",
        icon: "success",
        showCancelButton: true,
        showConfirmButton:true,
      });
    },
  },
};
</script>
