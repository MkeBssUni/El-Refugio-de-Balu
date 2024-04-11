<template>
  <b-card bg-variant="card-header-orange" class="my-2 information-pet">
    <b-card-body >
      <b-card-title>{{ title }}</b-card-title>
      <hr class="my-line" />
      <b-row>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text>
            <b>Ciudad:</b> &nbsp; 
          </b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>País:</b>  </b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>Estado:</b>  </b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>Calle:</b>  </b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>Colonia:</b>  </b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>Código Postal:</b></b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>Número exterior:</b></b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>Número interior:</b></b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>Referencia:</b></b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="12" md="12" xl="12" class="my-3">
          <h4>Especificación del Hogar</h4>
        <hr class="my-line" />
        </b-col>
      </b-row>
    </b-card-body>
  </b-card>
</template>

<script>
export default {
  name: "AddressByAdoptant",
  props: {
    address: {
      type: Object,
      required: true,
    },
    title: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      address: {
        email: "",
        phone: "",
      },
    };
  },
//   mounted() {
//     this.address = this.address;
//   },
};
</script>

<style></style>
