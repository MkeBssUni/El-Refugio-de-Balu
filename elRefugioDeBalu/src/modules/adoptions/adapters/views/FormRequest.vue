<template>
  <div class="card mt-4 mx-5 fondo-solicitud">
    <h1 class="mx-3 my-2">Solicitud de adopción</h1>
    <b-container>
      <b-row>
        <b-col cols="10" sm="10">
          <div class="container-fluid">
            <div class="card  form">
              <h4 style="margin-left: 2rem" >
                <i
                  class="material-icons ms-2"
                  style="font-size: larger; vertical-align: middle"
                  >pets</i
                >
                Datos personales
              </h4>
            </div>
            <b-card-group deck>
              <b-card class="boxform " img-alt="Card image">
                <b-container>
                  <b-row>
                    <b-col cols="4"  sm="12" lg="4" md="6" xl="4">
                      <b-form-group
                        id="input-group-2"
                        label="Nombre completo:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                    <b-col  cols="2"  sm="12" lg="2" md="6" xl="2">
                      <b-form-group
                        id="input-group-2"
                        label="Edad:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                    <b-col cols="3"  sm="12" lg="3" md="6" xl="3">
                      <b-form-group
                        id="input-group-2"
                        label="Teléfono de Casa:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                    <b-col cols="3"  sm="12" lg="3" md="6" xl="3">
                      <b-form-group
                        id="input-group-2"
                        label="Teléfono de Celular:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                  </b-row>
                  <b-row class="my-2">
                    <b-col cols="6"  sm="12" lg="6" md="6" xl="6">
                      <b-form-group
                        id="input-group-2"
                        label="Correo Electrónico:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                    <b-col cols="6"  sm="12" lg="6" md="6" xl="6">
                      <b-form-group
                        id="input-group-2"
                        label="¿Con quién vives?:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                  </b-row>
                </b-container>
              </b-card>
            </b-card-group>
          </div>
          <div class="container-fluid my-2">
            <div class="card form-second">
              <h4 style="margin-left: 2rem; display: inline-block">
                <i
                  class="material-icons ms-2"
                  style="font-size: larger; vertical-align: middle"
                  >pets</i
                >
                Motivos de adopción
              </h4>
            </div>
            <b-card-group deck>
              <b-card class="boxform-second " img-alt="Card image">
                <b-container>
                  <b-row>
                    <b-col  cols="6" sm="12" lg="6" md="6">
                      <b-form-group
                        id="input-group-2"
                        label="¿Tienes o has tenido otros animales de compañia?:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                    <b-col  cols="6" sm="12" lg="6" md="6">
                      <b-form-group
                        id="input-group-2"
                        label="¿Las personas con quien vives están de acuerdo en adoptar?:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                  </b-row>
                  <b-row class="my-2">
                    <b-col cols="6" sm="12" lg="6" md="6">
                      <b-form-group
                        id="input-group-2"
                        label="¿En que parte de la casa lo planeas tener?:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                    <b-col  cols="6" sm="12" lg="6" md="6">
                      <b-form-group
                        id="input-group-2"
                        label="Comentarios adicionales:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                  </b-row>
                </b-container>
              </b-card>
            </b-card-group>
          </div>
        </b-col>
        <b-col cols="2" sm="2" lg="2" md="2" class="image-column"><img src="../../../../assets/imgs/gatoGrisCut.png" class="tam"/> </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
export default {};
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
