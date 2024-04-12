<template>
  <div class="mt-2">
    <div class="card encabezadoColorformCategory">
      <h4 class="mt-2" style="margin-left: 2rem">
        <i
          class="material-icons ms-2"
          style="font-size: larger; vertical-align: middle"
          >pets</i
        >
        Modificación de categoría
      </h4>
    </div>
    <b-card-group deck>
      <b-card class="contentformCategory">
        <b-row class="mt-4">
          <b-col>
            <b-row>
              <b-col>
                <b-form-group label="Categoría">
                  <b-form-input
                    id="input-1"
                    v-model="UpdateCategoryDto.name"
                    trim
                    @input="UpdateStateInputCategoryName()"
                    :state="nameValidationState"
                  ></b-form-input>
                  <b-form-invalid-feedback
                    v-if="!ValidationSpecialCharactersName()"
                  >
                    El nombre de la categoría no puede contener los caracteres
                    especiales <>$&/(){}[]'"\ ni números
                  </b-form-invalid-feedback>
                  <b-form-invalid-feedback v-if="!ValidationNameLength()">
                    El tamaño mínimo de caracteres es 3 y el máximo 100
                  </b-form-invalid-feedback>
                </b-form-group>
              </b-col>
            </b-row>
            <b-row>
              <b-col>
                <b-form-group label="Descripción">
                  <b-form-textarea
                    id="input-1"
                    v-model="UpdateCategoryDto.description"
                    trim
                    rows="4"
                    @input="UpdateStateInputCategoryDescription()"
                    :state="descriptionValidationState"
                  ></b-form-textarea>
                  <b-form-invalid-feedback
                    v-if="!ValidationSpecialCharactersDescription()"
                  >
                    La descripción de la categoría no puede contener los
                    caracteres especiales <>$&/(){}[]'"\ ni números
                  </b-form-invalid-feedback>
                  <b-form-invalid-feedback
                    v-if="!ValidationDescriptionLength()"
                  >
                    El tamaño mínimo de caracteres es 15 y el máximo 240
                  </b-form-invalid-feedback>
                </b-form-group>
              </b-col>
            </b-row>
          </b-col>
          <b-col class="position-relative text-center">
            <div style="display: flex; align-items: center">
              <h5>Imagen de referencia</h5>
              <h5 class="text-danger">*</h5>
            </div>
            <b-img
              :src="showImg()"
              class="main-img-category"
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
              @change="ConvertImageToBase64"
            />
            <b-button
              v-if="UpdateCategoryDto.image"
              @click="triggerMainImgSelector()"
              class="btn-add center-position d-flex align-items-center justify-content-center p-2"
            >
              <b-icon icon="plus" font-scale="5"></b-icon>
            </b-button>
            <small class="text-danger"
              >Nota:El tamaño maximo soportado es de 6mb</small
            >
          </b-col>
        </b-row>
        <b-row class="mt-3"> </b-row>
      </b-card>
    </b-card-group>
    <b-row class="mt-3 d-flex justify-content-end me-1">
      <b-col cols="12" sm="6" md="5" lg="4" xl="3">
        <b-button
          class="w-100"
          variant="outline-success"
          v-if="!ValidationFormCategoryModify()"
          disabled
          >Modificar</b-button
        >
        <b-button
          class="w-100"
          variant="outline-success"
          @click="ViewAlertConfirmationRegistrationCategory"
          v-else
          >Modificar</b-button
        >
      </b-col>
      <b-col cols="12" sm="6" md="5" lg="4" xl="3">
        <b-button
          class="mx-2 w-100"
          variant="outline-danger"
          @click="$emit('UpdateCategory')"
          >Cancelar</b-button
        >
      </b-col>
    </b-row>
  </div>
</template>

<script>
import Swal from "sweetalert2";
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import instance from "../../../../config/axios";
import { encrypt } from "../../../../kernel/hashFunctions";

export default {
  name: "UpdateCategory",
  props: {
    categoryToModify: Object,
  },
  data() {
    return {
      UpdateCategoryDto: {
        id: this.categoryToModify.id,
        name: this.categoryToModify.categoryName,
        description: this.categoryToModify.categoryDescription,
        image: this.categoryToModify.categoryImage,
      },
      UpdateCategoryDtoEncrypted: {
        id: "",
        name: "",
        description: "",
        image: null,
        userId: localStorage.getItem("userId"),
      },
      size: false,
      imageFile: true,
      nameValidationState: null,
      descriptionValidationState: null,
      alertSize: null,
    };
  },
  methods: {
    ConvertImageToBase64($event) {
      const maxSize = 6 * 1024 * 1024;
      const file = $event.target.files[0];
      if (file.size > maxSize) {
        Swal.fire({
          icon: "error",
          title: "¡Error!",
          text: "¡La imagen seleccionada es muy pesada!",
          toast: true,
          position: "top-end",
          timer: 3000,
          showCancelButton: false,
          showConfirmButton: false,
          timerProgressBar: true,
        });
        this.alertSize = true;
        this.imageFile = false;
      } else {
        const reader = new FileReader();
        reader.onload = (e) => {
          const base64 = e.target.result;
          this.UpdateCategoryDto.image = base64;
          this.alertSize = false;
        };
        reader.readAsDataURL(file);
        this.imageFile = true;
      }
    },
    async UpdateCategory() {
      try {
        this.UpdateCategoryDtoEncrypted.id = await encrypt(
          this.UpdateCategoryDto.id
        );
        this.UpdateCategoryDtoEncrypted.name = await encrypt(
          this.UpdateCategoryDto.name
        );
        this.UpdateCategoryDtoEncrypted.description = await encrypt(
          this.UpdateCategoryDto.description
        );
        this.UpdateCategoryDtoEncrypted.image = this.UpdateCategoryDto.image;
        const response = await instance.put(
          "/category/",
          this.UpdateCategoryDtoEncrypted
        );
        if (response.status == 200) {
          Swal.fire({
            title: "Acción realizada con éxito",
            icon: "success",
            confirmButtonColor: "#118A95",
          });
          this.$emit("UpdateCategory");
        }
      } catch (error) {
        const msg = error.response
          ? error.response.data.message
          : error.message;
        switch (msg) {
          case "DUPLICATE_RECORD":
            Swal.fire({
              title: "Ha ocurrido un error",
              text: "Ya existe una categoría con ese nombre",
              icon: "error",
              confirmButtonColor: "#118A95",
            });
            break;
          case "UNSUPPORTED_IMAGE_FORMAT":
            Swal.fire({
              title: "Ha ocurrido un error",
              text: "El formato de la imagen no es válido",
              icon: "error",
              confirmButtonColor: "#118A95",
            });
            break;
          case "MISSING_FIELDS":
            Swal.fire({
              title: "Ha ocurrido un error",
              text: "Llene todos los campos marcados como obligatorios",
              icon: "error",
              confirmButtonColor: "#118A95",
            });
            break;
          case "INVALID_FIELD":
            Swal.fire({
              title: "Ha ocurrido un error",
              text: "Algunos campos no cumplen con el formato requerido",
              icon: "error",
              confirmButtonColor: "#118A95",
            });
            break;
          default:
            Swal.fire({
              title: "Ha ocurrido un error",
              text: "Intentelo de nuevo mas tarde " + error,
              icon: "error",
              confirmButtonColor: "#118A95",
            });
        }
      }
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
          Swal.fire({
            title: "Espera un momento...",
            text: "Estamos realizando tu modificación",
            imageUrl: gatoWalkingGif,
            timer: 2000,
            timerProgressBar: true,
            imageWidth: 160, // Ancho de la imagen
            imageHeight: 160, // Altura de la imagen
            showConfirmButton: false,
          }).then(() => {
            this.UpdateCategory();
          });
        }
      });
    },
    ValidationFormCategoryModify() {
      return (
        this.nameValidationState &&
        this.descriptionValidationState &&
        this.imageFile
      );
    },
    UpdateStateInputCategoryName() {
      this.nameValidationState =
        !(this.UpdateCategoryDto.name.trim() === "") &&
        this.ValidationSpecialCharactersName() &&
        this.ValidationNameLength();
    },
    ValidationSpecialCharactersName() {
      const regex = /^(?!.*[<>$&/(){}[\]'"\\])[^0-9]*$/;
      return regex.test(this.UpdateCategoryDto.name);
    },
    UpdateStateInputCategoryDescription() {
      this.descriptionValidationState =
        !(this.UpdateCategoryDto.description.trim() === "") &&
        this.ValidationSpecialCharactersDescription() &&
        this.ValidationDescriptionLength();
    },
    ValidationSpecialCharactersDescription() {
      const regex = /^(?!.*[<>$&/(){}[\]'"\\])[^0-9]*$/;
      return regex.test(this.UpdateCategoryDto.description);
    },
    showImg() {
      if (this.UpdateCategoryDto.image) return this.UpdateCategoryDto.image;
      return "../../../../assets/imgs/imageSearch.png";
    },
    triggerMainImgSelector() {
      this.$refs.mainImageSelector.click();
    },
    validateImg(file) {
      if (file.type != "image/jpeg" && file.type != "image/png") {
        Swal.fire({
          icon: "error",
          title: "¡Error!",
          text: "Selecciona una imagen en formato JPG o PNG",
          toast: true,
          position: "top-end",
          timer: 3000,
          showCancelButton: false,
          showConfirmButton: false,
          timerProgressBar: true,
        });
        return false;
      }
      if (file.size > 4000000) {
        Swal.fire({
          icon: "error",
          title: "¡Error!",
          text: "La imagen no puede pesar más de 4MB",
          toast: true,
          position: "top-end",
          timer: 3000,
          showCancelButton: false,
          showConfirmButton: false,
          timerProgressBar: true,
        });
        return false;
      }
      return true;
    },
    loadImgError() {
      Swal.fire({
        icon: "error",
        title: "¡Error!",
        text: "Ocurrió un error al cargar la imagen, intenta de nuevo con otra",
        toast: true,
        position: "top-end",
        timer: 3000,
        showCancelButton: false,
        showConfirmButton: false,
        timerProgressBar: true,
      });
    },
    unselectImg() {
      this.imageFile = false;
      this.UpdateCategoryDto.image = null;
    },
    ValidationDescriptionLength() {
      return (
        this.UpdateCategoryDto.description.length < 240 &&
        this.UpdateCategoryDto.description.length > 15
      );
    },
    ValidationNameLength() {
      return (
        this.UpdateCategoryDto.name.length < 100 &&
        this.UpdateCategoryDto.name.length > 3
      );
    },
  },
};
</script>

<style scoped>
.main-img-category {
  width: 450px;
  height: 255px;
  object-fit: cover;
  border: 3px solid rgb(234, 141, 3);
  background-color: burlywood;
}
.btn-add {
  border: none;
  cursor: pointer;
  border-radius: 50%;
  background-color: rgba(83, 169, 61, 0.6);
  color: #316E21;
}
.btn-add:hover {
  background-color: rgba(83, 169, 61, 1);
}
.btn-add:active {
  background-color: #347424;
  color: #1f4915;
}
.btn-remove {
  border: none;
  cursor: pointer;
  border-radius: 50%;
  background-color: rgba(169, 61, 61, 0.61);
  color: #571724;
}
.btn-remove:hover {
  background-color: rgba(169, 61, 61, 1);
}
.btn-remove:active {
  background-color: #742434;
  color: #49151f;
}
</style>