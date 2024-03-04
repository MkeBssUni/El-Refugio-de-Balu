<template>
    <div class="container-fluid">
      <div :class="color" class="card">
        <h4 style="margin-left: 2rem">
          <i class="material-icons ms-2" style="font-size: larger; vertical-align: middle">pets</i>
          {{ titulo }}
        </h4>
      </div>
      <b-card-group deck>
        <b-card class="boxform" img-alt="Card image">
          <b-container>
            <!-- Render dynamic inputs -->
            <b-form-group v-for="(input, index) in inputs" :key="index" :label="input.label" :label-for="'input-' + index">
              <b-form-input :id="'input-' + index" v-model="input.value"></b-form-input>
            </b-form-group>
          </b-container>
        </b-card>
      </b-card-group>
    </div>
  </template>
  
  <script>
  export default {
    name: "FormStyle",
    props: {
      color: {
        type: String,
        default: null,
      },
      titulo: {
        type: String,
        default: null,
      },
      inputs: {
        type: Array,
        default: () => [],
      },
    },
  };
  </script>

<style>
.fondo-solicitud {
  background-color: #5ebfc5;
  box-shadow: rgba(0, 0, 0, 0.12) 0px 1px 3px, rgba(0, 0, 0, 0.24) 0px 1px 2px;
}

.amarillo {
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
  right: 8%;
}

.image-column img {
  position: absolute;
  bottom: 0;
}

.logo {
  width: 50%;
}
</style>
