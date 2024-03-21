<template>
  <b-container fluid>
    <b-row class="d-flex justify-content-center">
      <b-col cols="12" class="mt-3">
        <img src="../../../assets/imgs/error/401.svg" class="error">
      </b-col>
      <b-col cols="3" class="mt-4 d-flex justify-content-center">
        <b-button to="/login" variant="outline-orange"
          class="w-100 d-flex align-items-center justify-content-center">
          <span>Iniciar sesión</span>
          <b-icon icon="door-open-fill" class="ms-3"></b-icon>
        </b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: '401'
}
</script>

<style>
.error {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 70vh;
  width: 100%;
}
</style>