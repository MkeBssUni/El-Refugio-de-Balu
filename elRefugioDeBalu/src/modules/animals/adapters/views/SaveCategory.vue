<template>
    <!-- inicio de plantilla -->
    <div class="container-fluid">
      <div class="card encabezadoColorform">
        <h4 style="margin-left: 2rem">
          <i
            class="material-icons ms-2"
            style="font-size: larger; vertical-align: middle"
            >pets</i
          >
         Registrar nueva categoria
        </h4>
      </div>
      <b-card-group deck>
        <b-card class="contentform" img-alt="Card image">
          <b-container>
            <!-- Aqui es donde va el formulario -->
            <!-- ingresar el contenido aqui -->
            <!-- desde aqui pueden manipular a su manera el formulario -->
            <!-- incio de ejemplo -->
            <b-form-group
              id="input-group-1"
              label="Email address:"
              label-for="input-1"
              description="We'll never share your email with anyone else."
            >
              <b-form-input
                id="input-1"
                v-model="form.email"
                type="email"
                placeholder="Enter email"
                required
              ></b-form-input>
            </b-form-group>
            <!-- fin ejemplo -->
          </b-container>
        </b-card>
      </b-card-group>
    </div>
    <!-- fin de plantilla -->
  </template>
  
  <script>
  export default {
    name: "saveCategory",
  };
  </script>
  
  <!-- El estilo del encabezado principal colocar los colores correspondiente -->
  <style>
  .encabezadoColorform {
    width: 50%;
    background-color: #ffb23f;
    box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
      rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
  }
  
  .contentform {
    background-color: #ffc876;
    box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
      rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
  }
  </style>
  