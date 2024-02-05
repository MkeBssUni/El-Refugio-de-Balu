<template>
  <div class="">
    <b-button @click="showAlert" variant="dark-secondary-orange"> 
      <i class="material-icons">pets</i>Guardar
    </b-button>
  </div>
</template>

<script>
import Swal from 'sweetalert2';

export default {
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
