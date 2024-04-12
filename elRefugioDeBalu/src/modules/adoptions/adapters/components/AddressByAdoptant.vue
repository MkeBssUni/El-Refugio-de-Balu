<template>
  <b-card bg-variant="card-header-orange" class="my-2 information-pet">
    <b-card-body >
      <b-card-title>{{ title }}</b-card-title>
      <hr class="my-line" />
      <b-row>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text>
            <b>Ciudad:</b> &nbsp; {{ address.country }}
          </b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>País:</b>&nbsp; {{ address.city }}  </b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>Estado:</b>&nbsp; {{ address.state }}  </b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>Calle:</b>&nbsp; {{ address.street }}  </b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>Colonia:</b>&nbsp; {{ address.colony }}  </b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>Código Postal:</b>&nbsp; {{ address.postalCode }}</b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>Número exterior:</b>&nbsp; {{ address.exteriorNumber }} </b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>Número interior:</b>&nbsp; {{ address.interiorNumber }}</b-card-text>
        </b-col>
        <b-col cols="12" sm="12" lg="4" md="4" xl="4">
          <b-card-text> <b>Referencia:</b> &nbsp; {{ address.addressReference }}</b-card-text>
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
};
</script>