<template>
  <div>
    <Encabezado
      color="#118A95"
      :imagenUrl="require('@/assets/imgs/perroflow.png')"
      titulo="Solicitud de Adopción"
    />
    <div class="my-3">
      <b-container>
        <b-row>
          <form>
            <!-- incio de fotos de casa -->
            <div class="container-fluid">
              <div class="card encabezadoColorform">
                <h4 style="margin-left: 2rem" class="mt-1 mb-0">
                  <i
                    class="material-icons ms-2"
                    style="font-size: larger; vertical-align: middle"
                    >pets</i
                  >
                  Lugar de residencia
                </h4>
              </div>
              <b-card-group deck>
                <b-card
                  class="contentform d-flex justify-content-center align-items-center"
                  img-alt="Card image"
                >
                  <b-container>
                    <b-row>
                      <b-col
                        cols="12"
                        sm="12"
                        md="4"
                        lg="4"
                        class="text-center"
                      >
                        <label>Lugar en la que dormirá la mascota</label>
                        <br />
                        <i
                          class="material-icons ms-2"
                          style="font-size: 100px; vertical-align: middle"
                          >image_search</i
                        >
                        <input type="file" accept="image/*" />
                      </b-col>
                      <b-col
                        cols="12"
                        sm="12"
                        md="4"
                        lg="4"
                        class="text-center"
                      >
                        <label>Lugar en el que vivirá la mascota (casa)</label>
                        <br />
                        <i
                          class="material-icons ms-2"
                          style="font-size: 100px; vertical-align: middle"
                          >image_search</i
                        >
                        <input type="file" accept="image/*" />
                      </b-col>
                      <b-col
                        cols="12"
                        sm="12"
                        md="4"
                        lg="4"
                        class="text-center"
                      >
                        <label>Lugar en el que jugará la mascota (casa)</label>
                        <br />
                        <i
                          class="material-icons ms-2"
                          style="font-size: 100px; vertical-align: middle"
                          >image_search</i
                        >
                        <input type="file" accept="image/*" />
                      </b-col>
                    </b-row>
                  </b-container>
                </b-card>
              </b-card-group>
            </div>
            <!-- fin de fotos de casa -->
            <!-- incio de motivos de adopcion -->
            <div class="container-fluid my-3">
              <div class="card encabezadoColorformMotive">
                <h4 style="margin-left: 2rem" class="mt-1 mb-0">
                  <i
                    class="material-icons ms-2"
                    style="font-size: larger; vertical-align: middle"
                    >pets</i
                  >
                  Motivos de adopción
                </h4>
              </div>
              <b-card-group deck>
                <b-card
                  class="contentformMotive d-flex justify-content align-items"
                  img-alt="Card image"
                >
                  <b-container>
                    <b-row>
                      <b-col cols="6" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿Tienes o has tenido otros animales de compañia?"
                          label-for="input-1"
                        >
                          <b-form-input
                            id="input-1"
                            placeholder="Escribe aquí tus motivos"
                            rows="3"
                          ></b-form-input>
                        </b-form-group>
                      </b-col>
                      <b-col cols="6" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿En que parte de la casa lo planeas tener?"
                          label-for="input-1"
                        >
                          <b-form-input
                            id="input-1"
                            placeholder="Escribe aquí tus motivos"
                            rows="3"
                          ></b-form-input>
                        </b-form-group>
                      </b-col>
                    </b-row>
                    <b-row>
                      <b-col cols="6" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿Las personas con quien vives están de acuerdo en adoptar?"
                          label-for="input-1"
                        >
                          <b-form-input
                            id="input-1"
                            placeholder="Escribe aquí tus motivos"
                            rows="3"
                          ></b-form-input>
                        </b-form-group>
                      </b-col>
                      <b-col cols="6" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="Comentarios adicionales"
                          label-for="input-1"
                        >
                          <b-form-input
                            id="input-1"
                            placeholder="Escribe aquí tus motivos"
                            rows="3"
                          ></b-form-input>
                        </b-form-group>
                      </b-col>
                    </b-row>
                  </b-container>
                </b-card>
              </b-card-group>
            </div>
            <!-- fin de motivos de adopcion -->

            <!-- incio de experiencias previas -->
            <div class="container-fluid my-3">
              <div class="card encabezadoColorformExperience">
                <h4 style="margin-left: 2rem" class="mt-1 mb-0">
                  <i
                    class="material-icons ms-2"
                    style="font-size: larger; vertical-align: middle"
                    >pets</i
                  >
                  Experiencias previas
                </h4>
              </div>
              <b-card-group deck>
                <b-card
                  class="contentformExperience d-flex justify-content align-items"
                  img-alt="Card image"
                >
                  <b-container>
                    <b-row>
                      <b-col cols="6" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿Cuál fue tu ultima mascota?"
                          label-for="input-1"
                        >
                          <b-form-input
                            id="input-1"
                            placeholder="Escribe aquí tus motivos"
                            rows="3"
                          ></b-form-input>
                        </b-form-group>
                      </b-col>
                      <b-col cols="6" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿Cuando se enfermaba la mascota que hacias?"
                          label-for="input-1"
                        >
                          <b-form-input
                            id="input-1"
                            placeholder="Escribe aquí tus motivos"
                            rows="3"
                          ></b-form-input>
                        </b-form-group>
                      </b-col>
                    </b-row>
                    <b-row>
                      <b-col cols="6" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿Qué tipo de mascotas haz tenido antes?"
                          label-for="input-1"
                        >
                          <b-form-input
                            id="input-1"
                            placeholder="Escribe aquí tus motivos"
                            rows="3"
                          ></b-form-input>
                        </b-form-group>
                      </b-col>
                      <b-col cols="6" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿Qué recuerdos tienes con tu mascota?"
                          label-for="input-1"
                        >
                          <b-form-input
                            id="input-1"
                            placeholder="Escribe aquí tus motivos"
                            rows="3"
                          ></b-form-input>
                        </b-form-group>
                      </b-col>
                    </b-row>
                  </b-container>
                </b-card>
              </b-card-group>
            </div>
            <!-- fin de experiencias previas -->

            <!-- incio de info personal -->
            <div class="container-fluid my-3">
              <div class="card encabezadoColorform">
                <h4 style="margin-left: 2rem" class="mt-1 mb-0">
                  <i
                    class="material-icons ms-2"
                    style="font-size: larger; vertical-align: middle"
                    >pets</i
                  >
                  Experiencias previas
                </h4>
              </div>
              <b-card-group deck>
                <b-card
                  class="contentform d-flex justify-content align-items"
                  img-alt="Card image"
                >
                  <b-container>
                    <b-row>
                      <b-col cols="12" sm="12" md="12" lg="12">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿Desea agregar más información a su solicitud de adopción?"
                          label-for="input-1"
                        >
                          <b-form-textarea
                            id="input-1"
                            placeholder="Escribe aquí tus motivos"
                            rows="3"
                          ></b-form-textarea>
                        </b-form-group>
                      </b-col>
                    </b-row>
                  </b-container>
                </b-card>
              </b-card-group>
            </div>
            <!-- fin de info personal -->
            <div class="container-fluid d-flex justify-content-end">
              <b-button
                size="lg"
                type="submit"
                variant="outline-dark-secondary-blue pill"
                class="mt-3 rounded-button mx-4"
              >
              Adoptar
              </b-button>
              <b-button
                size="lg"
                type="submit"
                variant="outline-danger pills"
                class="mt-3 rounded-button"
              >
              Limpiar
              </b-button>
            </div>
          </form>
        </b-row>
      </b-container>
    </div>
  </div>
</template>

<script>
import Encabezado from "../../../../views/components/Encabezado.vue";

export default {
  name: "FormAdoption",
  components: {
    Encabezado,
  },
};
</script>

<style scoped>
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

.encabezadoColorformMotive {
  width: 50%;
  background-color: #fb6528;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.contentformMotive {
  background-color: #fe8757;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.encabezadoColorformExperience {
  width: 50%;
  background-color: #1da3af;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.contentformExperience {
  background-color: #5ebfc5;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.rounded-button {
        border-radius: 20px; 
}
</style>
