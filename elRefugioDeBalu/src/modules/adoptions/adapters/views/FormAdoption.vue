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
            <!-- inicio de plantilla -->
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
                      <b-col cols="12" sm="12" md="4" lg="4" class="text-center">
                        <label>Lugar en la que dormirá la mascota</label>
                        <br />
                        <i
                          class="material-icons ms-2"
                          style="font-size: 100px; vertical-align: middle"
                          >image_search</i
                        >
                        <input type="file" accept="image/*" />
                      </b-col>
                      <b-col cols="12" sm="12" md="4" lg="4" class="text-center">
                        <label>Lugar en el que vivirá la mascota (casa)</label>
                        <br />
                        <i
                          class="material-icons ms-2"
                          style="font-size: 100px; vertical-align: middle"
                          >image_search</i
                        >
                        <input type="file" accept="image/*" />
                      </b-col>
                      <b-col cols="12" sm="12" md="4" lg="4" class="text-center">
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
            <!-- fin de plantilla -->
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
</style>
