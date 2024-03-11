<template>
  <div>
    <div class="card encabezadoColorform">
      <h4 style="margin-left: 2rem">
        <i class="material-icons ms-2" style="font-size: larger; vertical-align: middle">pets</i>
        Registro de categoria
      </h4>
    </div>
    <b-card-group deck>
      <b-card class="contentform">
        <b-row>
          <b-col>
            <b-form-group label="Categoria">
              <b-form-input id="input-1" v-model="this.SaveCategoryDto.name" :state="state" trim></b-form-input>
            </b-form-group>
          </b-col>
          <b-col>
            <b-form-group label="Imagen">
              <b-form-file class="form-control" v-model="this.SaveCategoryDto.image" plain
                @change="ConvertImageToBase64($event)"></b-form-file>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col>
            <b-form-group label="Descripción">
              <b-form-textarea id="input-1" v-model="this.SaveCategoryDto.name" :state="state" trim
                rows="3"></b-form-textarea>
            </b-form-group>
          </b-col>
          <b-col>
            <b-img :src="this.SaveCategoryDto.image" fluid thumbnail></b-img>
          </b-col>
        </b-row>
        <b-row class="mt-3">
          <b-col class="text-right">
            <b-button variant="outline-success">Registrar</b-button>
          </b-col>
        </b-row>
      </b-card>
    </b-card-group>
  </div>
</template>

<script>
export default {
  name: "SaveCategory",
  data() {
    return {
      SaveCategoryDto: {
        name: "",
        description: "",
        image: "",
      },
    };
  },
  methods: {
    ConvertImageToBase64(event) {
      const maxSize = 2 * 1024 * 1024;
      const { files } = event.target
      const file = Array.from(files);
      if (file.length > 1) {
        this.size = false;
      }
      if (file[0].size > maxSize) {
        this.size = false
        this.file1 = ''
        this.news.image = ''
      } else {
        const reader = new FileReader();
        reader.onload = (e) => {
          const base64 = e?.target?.result;
          this.news.image = base64
        };
        reader.readAsDataURL(file[0]);
        this.size = true
      }
    }
  },
};
</script>


<!-- <b-form-group label="Imagen de portada *" laberl-for="inputImage">
  <b-form-file id="inputImage" browse-text="Elegir"  accept="image/png, image/jpeg, image/jpg"
      placeholder="Selecciona una imagen" drop-placeholder="Suelta el archivo aquí"
      v-model="$v.file1.$model"
      :state="($v.file1 && $v.file1.$dirty) && size  ? (size || $v.file1 && $v.news.image?.required) : null"
      @change="convertImageToBase64($event)"></b-form-file>
  <small class="text-danger">
      Nota: El tamaño máximo soportado es de 2mb
  </small>
  <b-form-invalid-feedback v-if="news.image?.length <= 0 && size">
      Este campo es obligatorio
  </b-form-invalid-feedback>
  <b-form-invalid-feedback v-if="!size">
      La imagen es muy pesada, el tamaño máximo soportado es de 2mb
  </b-form-invalid-feedback>
  <b-form-invalid-feedback v-if="!format">
      El formato de la imagen no es valido
  </b-form-invalid-feedback>
  <br>
</b-form-group> -->

<style></style>