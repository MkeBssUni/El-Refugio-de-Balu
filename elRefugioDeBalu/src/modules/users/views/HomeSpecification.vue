<template>
  <b-col cols="12" class="px-2 px-sm-4 px-xl-5 mt-4">
    <b-row>
      <b-col cols="12" md="8" lg="6">
        <b-card
          bg-variant="dark-orange"
          class="py-2 form-card-shadow relative-position form-card-title"
          no-body
        >
          <div class="d-flex align-items-center ms-3 ms-md-4">
            <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
            <h4 class="mb-0 mt-1">Especificaciones de Vivienda</h4>
          </div>
        </b-card>
      </b-col>
    </b-row>

    <b-row>
      <b-col cols="12">
        <b-card
          bg-variant="card-content-orange"
          class="form-card-shadow form-card-content"
        >
          <b-card-body>
            <b-row class="mt-3">
              <b-col
                cols="12"
                lg="5"
                xl="4"
                class="d-flex align-items-center justify-content-center"
              >
                <b-row>
                  <b-col cols="12" class="position-relative">
                    <b-img
                      :src="showImg()"
                      class="main-img"
                      alt="Imagen principal seleccionada"
                      fluid
                      rounded
                      center
                    ></b-img>
                    <input
                      type="file"
                      ref="mainImageSelector"
                      style="display: none"
                      accept="image/jpeg, image/png"
                      @change="selectImg()"
                    />
                    <b-button
                      v-if="!form.mainImage"
                      @click="triggerMainImgSelector()"
                      class="btn-add center-position d-flex align-items-center justify-content-center p-2"
                    >
                      <b-icon icon="plus" font-scale="5"></b-icon>
                    </b-button>
                    <b-button
                      v-else
                      @click="unselectImg()"
                      class="btn-remove center-position d-flex align-items-center justify-content-center p-2"
                    >
                      <b-icon icon="x" font-scale="5"></b-icon>
                    </b-button>
                  </b-col>
                </b-row>
              </b-col>
              <b-col cols="12" lg="7" xl="8" class="mt-4 mt-lg-0">
                <b-row>
                  <b-col cols="12" sm="6">
                    <b-form-group
                      label="Tipo de vivienda"
                      label-for="homeType"
                      :state="getErrorState('homeType')"
                    >
                      <b-form-select
                        id="homeType"
                        v-model="form.type"
                        :options="homeTypesOptions"
                        placeholder="Selecciona el tipo de vivienda..."
                        @change="validateInput('homeType')"
                      ></b-form-select>
                      <b-form-invalid-feedback v-if="getErrorState('homeType')">
                        Debes seleccionar el tipo de vivienda.
                      </b-form-invalid-feedback>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" sm="6">
                    <b-form-group label="¿Tiene área exterior?" label-for="outdoorArea">
                      <b-form-checkbox
                        id="outdoorArea"
                        v-model="form.outdoorArea"
                        :value="true"
                      >
                        Sí
                      </b-form-checkbox>
                    </b-form-group>
                  </b-col>
                </b-row>
                <b-row>
                  <b-col cols="12" sm="6">
                    <b-form-group
                      label="Número de residentes"
                      label-for="numberOfResidents"
                      :state="getErrorState('numberOfResidents')"
                    >
                      <b-form-input
                        id="numberOfResidents"
                        v-model.number="form.numberOfResidents"
                        type="number"
                      ></b-form-input>
                      <b-form-invalid-feedback v-if="getErrorState('numberOfResidents')">
                        Ingresa un número válido de residentes.
                      </b-form-invalid-feedback>
                    </b-form-group>
                  </b-col>
                </b-row>
                <b-col cols="12">
                  <div class="d-flex justify-content-end mt-3">
                    <b-button
                      @click="submitForm"
                      variant="outline-light"
                      :disabled="!isValidForm || loading"
                    >
                      <span v-if="loading">Cargando...</span>
                      <span v-else>Modificar</span>
                      <i
                        class="material-icons"
                        style="font-size: 1.5rem; color: white"
                      >
                        home
                      </i>
                    </b-button>
                  </div>
                </b-col>
              </b-col>
            </b-row>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <Overlay v-if="loading" />
  </b-col>
</template>

<script>
import Swal from "sweetalert2";
import Overlay from "../../../utils/Overlay.vue";
import instance from '../../../config/axios';

export default {
  components: {
    Overlay,
  },
  data() {
    return {
      form: {
        idUser: localStorage.getItem("userId"),
        type: "", 
        outdoorArea: false, 
        numberOfResidents: "", 
      },
      homeTypesOptions: [
        { text: "Departamento", value: "DEPARTAMENTO" },
        { text: "Casa", value: "CASA" },
        { text: "Terreno", value: "TERRENO" },
        { text: "Bungalow", value: "BUNGALOW" },
        { text: "Condominio", value: "CONDOMINIO" },
        { text: "Fraccionamiento", value: "FRACCIONAMIENTO" },
        { text: "Loft", value: "LOFT" },
        { text: "Penthouse", value: "PENTHOUSE" },
        { text: "Rancho", value: "RANCHO" },
        { text: "Quinta", value: "QUINTA" },
        { text: "Hacienda", value: "HACIENDA" },
      ],
      errorMessages: {
        type: "", 
        numberOfResidents: "",
      },
      loading: false,
    };
  },
  computed: {
    isValidForm() {
      return (
        !this.errorMessages.type && 
        !this.errorMessages.numberOfResidents 
      );
    },
  },
  methods: {
    showImg() {
      return this.form.mainImage ? URL.createObjectURL(this.form.mainImage) : "https://t3.ftcdn.net/jpg/07/01/59/38/240_F_701593826_ojYyX0cKXG3OzhoYkbeesqsQtaA6zBbj.jpg";
    },
    selectImg() {
      const file = this.$refs.mainImageSelector.files[0];
      if (file) {
        this.form.mainImage = file;
      }
    },
    unselectImg() {
      this.form.mainImage = null;
    },
    triggerMainImgSelector() {
      this.$refs.mainImageSelector.click();
    },
    async submitForm() {
      try {
        if (!this.isValidForm) return;

        this.loading = true;
        console.log("esto se manda: ", this.form)
        //quitar mainImage
        this.form.mainImage = null;
        console.log("esto se manda: ", this.form)
        const response = await instance.post("/homeSpecification/", this.form);

        Swal.fire({
          icon: "success",
          title: "¡Éxito!",
          text: "Se ha enviado el formulario correctamente.",
        });

        this.resetForm();
      } catch (error) {
        console.log("error: ", error)
        Swal.fire({
          icon: "error",
          title: "Error",
          text: "Ocurrió un error al enviar el formulario.",
        });
        console.error("Error al enviar el formulario:", error);
      } finally {
        this.loading = false;
      }
    },
    validateInput(fieldName) {
      if (fieldName === "type") {
        this.errorMessages.type = this.form.type ? "" : "Selecciona el tipo de vivienda";
      }
      if (fieldName === "numberOfResidents") {
        this.errorMessages.numberOfResidents = this.form.numberOfResidents > 0 ? "" : "Ingresa un número válido de residentes";
      }
    },
    getErrorState(fieldName) {
      return this.errorMessages[fieldName] ? false : null;
    },
    resetForm() {
      this.form.type = "";
      this.form.outdoorArea = false;
      this.form.numberOfResidents = 1;
      this.errorMessages.type = "";
      this.errorMessages.numberOfResidents = "";
    },
  },
};
</script>

<style scoped>
.main-img {
  width: 280px;
  height: 240px;
  object-fit: cover;
  border: 4px solid black;
}

.btn-add,
.btn-remove {
  border: none;
  cursor: pointer;
  border-radius: 50%;
  padding: 0.5rem;
}

.btn-add {
  background-color: rgba(83, 169, 61, 0.6);
  color: #316e21;
}

.btn-remove {
  background-color: rgba(169, 61, 61, 0.61);
  color: #571724;
}

.btn-add:hover,
.btn-remove:hover {
  background-color: rgba(0, 0, 0, 0.8);
}
</style>
