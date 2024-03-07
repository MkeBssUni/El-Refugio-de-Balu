<template>
  <div>
    <div class="card encabezadoColorform">
      <h4 class="mt-2" style="margin-left: 2rem">
        <i
          class="material-icons ms-2"
          style="font-size: larger; vertical-align: middle"
          >pets</i
        >
        Registro de categoria
      </h4>
    </div>
    <b-card-group deck>
      <b-card class="contentform">
        <b-row>
          <b-col>
            <b-form-group label="Categoria">
              <b-form-input
                id="input-1"
                v-model="SaveCategoryDto.name"
                trim
              ></b-form-input>
            </b-form-group>
          </b-col>
          <b-col>
            <b-form-group label="Imagen">
              <b-form-file
                class="form-control"
                v-model="SaveCategoryDto.image"
                plain
                @change="ConvertImageToBase64($event)"
              ></b-form-file>
            </b-form-group>
          </b-col>
        </b-row>
        <b-row>
          <b-col>
            <b-form-group label="Descripción">
              <b-form-textarea
                id="input-1"
                v-model="SaveCategoryDto.description"
                trim
                rows="4"
              ></b-form-textarea>
            </b-form-group>
          </b-col>
          <b-col class="mt-4">
            <b-img
              v-if="this.SaveCategoryDto.image"
              :src="this.SaveCategoryDto.image"
              class="categoryImage mt-2"
              fluid
              thumbnail
            ></b-img>
          </b-col>
        </b-row>
        <b-row class="mt-3">
          <b-col class="text-right"
            ><b-button
              variant="outline-success"
              v-if="ValidationFormCategoryRegister()"
              disabled
              >Registrar</b-button
            >
            <b-button
              variant="outline-success"
              @click="ViewAlertConfirmationRegistrationCategory"
              v-else
              >Registrar</b-button
            >
            <b-button
              class="mx-2"
              variant="outline-danger"
              @click="$emit('SavedCategory')"
              >Cancelar</b-button
            >
          </b-col>
        </b-row>
      </b-card>
    </b-card-group>
  </div>
</template>

<script>
import Swal from "sweetalert2";

export default {
  name: "SaveCategory",
  data() {
    return {
      SaveCategoryDto: {
        name: "",
        description: "",
        image: "",
      },
      size: false,
    };
  },
  methods: {
    ConvertImageToBase64(event) {
      const maxSize = 2 * 1024 * 1024;
      const { files } = event.target;
      const file = Array.from(files);
      if (file.length > 1) {
        this.size = false;
      }
      if (file[0].size > maxSize) {
        this.size = false;
        this.SaveCategoryDto.image = "";
      } else {
        const reader = new FileReader();
        reader.onload = (e) => {
          const base64 = e?.target?.result;
          this.SaveCategoryDto.image = base64;
        };
        reader.readAsDataURL(file[0]);
        this.size = true;
      }
    },
    SaveCategory() {
      Swal.fire({
        title: "Acción realizada con exito",
        icon: "success",
        confirmButtonColor: "#118A95",
      });
      this.$emit("SavedCategory");
    },
    ViewAlertConfirmationRegistrationCategory() {
      Swal.fire({
        title: "¿Seguro que desea realizar la acción?",
        icon: "question",
        showCancelButton: true,
        confirmButtonColor: "#53A93D",
        cancelButtonColor: "#A93D3D",
        confirmButtonText: "Aceptar",
        cancelButtonText: "Cancelar",
      }).then((result) => {
        if (result.isConfirmed) {
          this.SaveCategory();
        }
      });
    },
    ValidationFormCategoryRegister() {
      if (
        this.SaveCategoryDto.name &&
        this.SaveCategoryDto.description &&
        this.SaveCategoryDto.image
      ) {
        return false; // Todos los campos están llenos
      }
      return true; // Al menos uno de los campos está vacío
    },
    ValidateNameCategory() {
      const expresionRegular = /[<>{}' || \\ \/]/;
      return !expresionRegular.test(oracion);
    },
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