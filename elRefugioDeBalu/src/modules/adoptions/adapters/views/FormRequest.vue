<template>
  <div class="card mt-4 mx-5 fondo-solicitud">
    <h1 class="mx-3 my-2">Solicitud de adopción</h1>
    <b-container>
      <b-row>
        <b-col cols="10" sm="10">
          <form-style color="amarillo" contenido="Aqui los inputs" titulo="Datos personales" :inputs="datosPersonalesInputs" />
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import FormStyle from '../../../../views/components/FormStyle.vue';
export default {
  name:"FormRequest",
  components: {
    FormStyle
  },
  data() {
    return {
      datosPersonalesInputs: [
        { label: "Nombre", value: "" },
        { label: "Correo electrónico", value: "" },
        // Añade más objetos para más inputs según sea necesario
      ],
      
    }
  },

};
</script>

<style>
.fondo-solicitud {
  background-color: #5ebfc5;
  box-shadow: rgba(0, 0, 0, 0.12) 0px 1px 3px, rgba(0, 0, 0, 0.24) 0px 1px 2px;
}

.form {
  width: 50%;
  background-color: #ff9900;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.boxform {
  background-color: #ffbc58;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.form-second {
  width: 50%;
  background-color: #e84e0f;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.boxform-second {
  background-color: #ee7240;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.tam {
  width: 150%;
}
.image-column {
  position: relative;
  right:  8%;
}

.image-column img {
  position: absolute;
  bottom: 0;

}

.logo {
  width: 50%;
}
</style>
