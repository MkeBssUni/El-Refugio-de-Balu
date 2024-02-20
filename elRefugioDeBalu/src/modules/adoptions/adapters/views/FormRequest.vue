<template>
  <div class="card mt-4 mx-5 fondo-solicitud">
    <h1 class="mx-3 my-2">Solicitud de adopción</h1>
    <b-container>
      <b-row>
        <b-col cols="10">
          <div class="">
            <div class="card mx-5 form">
              <h4 style="margin-left: 2rem; display: inline-block">
                <i
                  class="material-icons ms-2"
                  style="font-size: larger; vertical-align: middle"
                  >pets</i
                >
                Datos personales
              </h4>
            </div>
            <b-card-group deck>
              <b-card class="boxform mx-5" img-alt="Card image">
                <b-container>
                  <b-row>
                    <b-col>
                      <b-form-group
                        id="input-group-2"
                        label="Nombre completo:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                    <b-col>
                      <b-form-group
                        id="input-group-2"
                        label="Edad:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                    <b-col>
                      <b-form-group
                        id="input-group-2"
                        label="Teléfono de Casa:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                    <b-col>
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
                    <b-col>
                      <b-form-group
                        id="input-group-2"
                        label="Correo Electrónico:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                    <b-col>
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
          <div class="my-4">
            <div class="card mx-5 form-second">
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
              <b-card class="boxform-second mx-5" img-alt="Card image">
                <b-container>
                  <b-row>
                    <b-col>
                      <b-form-group
                        id="input-group-2"
                        label="¿Tienes o has tenido otros animales de compañia?:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                    <b-col>
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
                    <b-col>
                      <b-form-group
                        id="input-group-2"
                        label="¿En que parte de la casa lo planeas tener?:"
                        label-for="input-2"
                      >
                        <b-form-input id="input-2" required></b-form-input>
                      </b-form-group>
                    </b-col>
                    <b-col>
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
        <b-col cols="2" class="image-column"><img src="../../../../assets/imgs/gatoGrisCut.png" class="tam"/> </b-col>
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
  width: 35%;
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
  width: 35%;
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
  width: 197%;
}
.image-column {
  position: relative;
}

.image-column img {
  position: absolute;
  bottom: 0;
  right:  0.05;
}

.logo {
  width: 50%;
}
</style>
