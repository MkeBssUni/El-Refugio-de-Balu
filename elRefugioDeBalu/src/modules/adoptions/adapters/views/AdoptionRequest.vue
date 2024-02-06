<template>
  <div class="">
   <Encabezado color="#E84E0F" imagenUrl="../../assets/mascotas.png" titulo="Lista de Adopciones" />
   <p>Contenido</p>
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
