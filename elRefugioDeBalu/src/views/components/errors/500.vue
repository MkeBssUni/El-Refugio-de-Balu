<template>
  <b-container fluid>
    <b-row class="d-flex justify-content-center">
      <b-col cols="12">
        <img src="../../../assets/imgs/error/500.svg" class="error" alt="error-500" />
      </b-col>
      <b-col cols="3" class="d-flex justify-content-center">
        <b-button @click="goBack" variant="outline-orange"
          class="w-100 d-flex align-items-center justify-content-center">
          <b-icon icon="arrow-left-circle" class="me-3"></b-icon>
          <span>Regresar</span>
        </b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: '500',
  methods: {
    goBack() {
      this.$router.go(-1);
    }
  }
}
</script>

<style>
.error {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80vh;
  width: 100%;
}
</style>