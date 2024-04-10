<template>
  <b-col cols="12" class="px-2 px-sm-4 px-xl-5 mt-4">
    <b-row>
      <b-col cols="10" md="8" lg="6">
        <b-card bg-variant="card-header-orange" class="py-2 form-card-shadow relative-position form-card-title" no-body>
          <div class="d-flex align-items-center ms-3 ms-md-4">
            <i class="material-icons me-2" style="font-size: 1.5rem">home</i> <!-- Icono -->
            <h4 class="mb-0 mt-1">Especificaciones de Vivienda</h4> <!-- Título -->
          </div>
        </b-card>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="12">
        <b-card bg-variant="card-content-orange" class="form-card-shadow form-card-content">
          <b-card-body>
            <b-row class="mt-3">
              <b-col cols="12" lg="5" xl="4" class="d-flex align-items-center justify-content-center">
                <b-img :src="showImg()" class="main-img" alt="Imagen principal seleccionada" fluid rounded center></b-img>
                <input type="file" ref="mainImageSelector" style="display: none" accept="image/jpeg, image/png" @change="selectImg()">
                <b-button v-if="!form.mainImage" @click="triggerMainImgSelector()" class="btn-add center-position d-flex align-items-center justify-content-center p-2">
                  <b-icon icon="plus" font-scale="5"></b-icon>
                </b-button>
                <b-button v-else @click="unselectImg()" class="btn-remove center-position d-flex align-items-center justify-content-center p-2">
                  <b-icon icon="x" font-scale="5"></b-icon>
                </b-button>
              </b-col>

              <b-col cols="12" class="mt-4">
                <b-row class="d-flex justify-content-center">
                  <b-col cols="3" v-for="(image, index) in form.additionalImages" :key="index" class="position-relative d-flex justify-content-center">
                    <div class="additional-img-container">
                      <b-img :src="image" class="additional-img" alt="Imagen adicional" center></b-img>
                    </div>
                    <b-button @click="removeAdditionalImg(index)" class="btn-remove center-position d-flex align-items-center justify-content-center p-1">
                      <b-icon icon="x" font-scale="2"></b-icon>
                    </b-button>
                  </b-col>
                  <b-col cols="3" v-if="form.additionalImages.length < 4" class="d-flex align-items-center justify-content-center">
                    <input type="file" ref="additionalImageSelector" style="display: none" accept="image/jpeg, image/png" @change="selectAdditionalImg()">
                    <b-button @click="triggerAdditionalImgSelector()" class="btn-add d-flex align-items-center justify-content-center p-1">
                      <b-icon icon="plus" font-scale="2.5"></b-icon>
                    </b-button>
                  </b-col>
                  <b-col cols="9" md="7" lg="9" v-if="form.additionalImages.length === 0" class="d-flex align-items-center">
                    <label>Agrega imágenes adicionales</label>
                  </b-col>
                </b-row>
              </b-col>
            </b-row>

            <b-row class="mt-3 mt-lg-0 d-flex align-items-center">
              <b-col cols="12" sm="7" xl="8" class="mt-3 mt-xl-0">
                <b-form-group label-for="homeName">
                  <label class="mb-2">Ingresa el nombre de la vivienda:</label>
                  <b-form-input id="homeName" v-model.trim="form.homeName" placeholder="Nombre de la vivienda..."></b-form-input>
                </b-form-group>
              </b-col>

              <b-col cols="12" sm="5" xl="4" class="mt-3 mt-xl-0">
                <b-form-group label-for="homeType">
                  <label class="mb-2">Selecciona el tipo de vivienda:</label>
                  <b-form-select id="homeType" v-model="form.homeType" :options="homeTypesOptions"></b-form-select>
                </b-form-group>
              </b-col>

            </b-row>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-col>
</template>

<script>
export default {
  data() {
    return {
      form: {
        mainImage: null,
        additionalImages: [], 
        homeName: '',
        homeType: '', 
      },
      homeTypesOptions: [
        { text: 'Departamento', value: 'DEPARTAMENTO' },
        { text: 'Casa', value: 'CASA' },
        { text: 'Terreno', value: 'TERRENO' },
      ],
    };
  },
  methods: {
    showImg() {
      return this.form.mainImage ? URL.createObjectURL(this.form.mainImage) : '';
    },
    selectImg() {
      const file = this.$refs.mainImageSelector.files[0];
      if (file) {
        this.form.mainImage = file;
      }
    },
    selectAdditionalImg() {
      const file = this.$refs.additionalImageSelector.files[0];
      if (file) {
        this.form.additionalImages.push(URL.createObjectURL(file));
      }
    },
    removeAdditionalImg(index) {
      this.form.additionalImages.splice(index, 1);
    },
    triggerMainImgSelector() {
      this.$refs.mainImageSelector.click();
    },
    triggerAdditionalImgSelector() {
      this.$refs.additionalImageSelector.click();
    },
  },
};
</script>

<style scoped>
.main-img {
  max-width: 100%;
  max-height: 300px;
  object-fit: contain;
  border: 1px solid #ddd;
  border-radius: 5px;
}
.additional-img {
  max-width: 100%;
  max-height: 150px;
  object-fit: cover;
  border: 1px solid #ddd;
  border-radius: 5px;
}
.additional-img-container {
  position: relative;
  margin-bottom: 10px;
}
.btn-add,
.btn-remove {
  position: absolute;
  bottom: -10px;
  right: -10px;
  background-color: #ff7f50;
  color: white;
  border-radius: 50%;
  cursor: pointer;
}
.required-asterisk {
  color: red;
}
</style>
