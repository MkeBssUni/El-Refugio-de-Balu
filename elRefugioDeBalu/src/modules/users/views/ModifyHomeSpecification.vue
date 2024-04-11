<template>
  <b-col cols="12" class="px-2 px-sm-4 px-xl-5 mt-4">
    <b-row>
      <b-col cols="10" md="8" lg="6">
        <b-card
          bg-variant="card-header-blue"
          class="py-2 form-card-shadow relative-position form-card-title"
          no-body
        >
          <div class="d-flex align-items-center ms-3 ms-md-4">
            <i class="material-icons me-2" style="font-size: 1.5rem">home</i>
            <h4 class="mb-0 mt-1">Modificar Especificaciones de Vivienda</h4>
          </div>
        </b-card>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="12">
        <b-card
          bg-variant="card-content-blue"
          class="form-card-shadow form-card-content"
        >
          <b-card-body>
            <b-row>
              <b-col cols="12" class="px-2 px-sm-4 px-xl-5 mb-sm-5">
                <b-row>
                  <b-col cols="12" md="4">
                    <b-form-group label="Tipo de Vivienda" label-align="left">
                      <b-input v-model="form.homeType"></b-input>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" md="4">
                    <b-form-group label="Área Exterior" label-align="left">
                      <b-input v-model="form.outdoorAreaDisplay"></b-input>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" md="4">
                    <b-form-group label="Número de Residentes" label-align="left">
                      <b-input v-model="form.numberOfResidents"></b-input>
                    </b-form-group>
                  </b-col>
                </b-row>
                <b-row>
                  <b-col cols="12">
                    <b-form-group label="Imagen Principal" label-align="left">
                      <b-img
                        :src="showImg()"
                        alt="Imagen principal seleccionada"
                        fluid
                        rounded
                        center
                      ></b-img>
                    </b-form-group>
                  </b-col>
                </b-row>
                <div class="d-flex justify-content-end mt-3">
                  <b-button
                    @click="submitForm()"
                    variant="outline-light"
                    class="d-flex align-items-center"
                  >
                    Modificar
                    <b-icon icon="pencil" class="ms-2" font-scale="1.3"></b-icon>
                  </b-button>

                  <b-button
                    @click="cancelUpdate()"
                    variant="outline-danger"
                    class="d-flex align-items-center ms-2"
                  >
                    Cancelar<b-icon icon="x-lg" class="ms-2" font-scale="1.3"></b-icon>
                  </b-button>
                </div>
              </b-col>
            </b-row>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-col>
</template>

<script>
import Swal from "sweetalert2";
import instance from "../../../../config/axios";

export default {
  name: "ModifyHomeSpecification",
  props: {
    homeSpecToModify: Object,
  },
  data() {
    return {
      form: {
        homeType: this.homeSpecToModify.homeType,
        outdoorAreaDisplay: this.homeSpecToModify.outdoorAreaDisplay,
        numberOfResidents: this.homeSpecToModify.numberOfResidents,
        mainImage: null,
      },
      loading: false,
    };
  },
  computed: {
    isValidForm() {
      return (
        !!this.form.homeType &&
        !!this.form.outdoorAreaDisplay &&
        this.form.numberOfResidents > 0
      );
    },
  },
  methods: {
    showImg() {
      if (this.form.mainImage) {
        return URL.createObjectURL(this.form.mainImage);
      }
      return "https://via.placeholder.com/280x240.png?text=Imagen+Principal";
    },
    async submitForm() {
      try {
        if (!this.isValidForm) {
          Swal.fire({
            icon: "error",
            title: "Error",
            text: "Por favor completa todos los campos antes de modificar.",
          });
          return;
        }

        this.loading = true;
        const response = await instance.put("/api/homeSpecification", this.form);

       
        Swal.fire({
          icon: "success",
          title: "Éxito",
          text: "Especificaciones de vivienda modificadas exitosamente.",
        });

      } catch (error) {
        console.error("Error al modificar las especificaciones:", error);
        Swal.fire({
          icon: "error",
          title: "Error",
          text: "Ocurrió un error al modificar las especificaciones de vivienda.",
        });
      } finally {
        this.loading = false;
      }
    },
    cancelUpdate() {
      Swal.fire({
        icon: "info",
        title: "Acción cancelada",
        text: "La modificación de especificaciones de vivienda ha sido cancelada.",
      });
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

