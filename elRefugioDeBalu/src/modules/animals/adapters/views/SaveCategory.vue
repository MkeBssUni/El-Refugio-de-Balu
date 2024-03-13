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
                @input="UpdateStateInputCategoryName()"
                :state="nameValidationState"
              ></b-form-input>
              <b-form-invalid-feedback
                v-if="!ValidationSpecialCharactersName()"
              >
                El nombre de la categoría contiene caracteres especiales no
                permitidos (/[<>{}' || \\ \/]/)
              </b-form-invalid-feedback>
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
                @input="UpdateStateInputCategoryDescription()"
                :state="descriptionValidationState"
              ></b-form-textarea>
              <b-form-invalid-feedback
                v-if="!ValidationSpecialCharactersDescription()"
              >
                La descripción de la categoría contiene caracteres especiales no
                permitidos (/[<>{}' || \\ \/]/)
              </b-form-invalid-feedback>
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
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";

export default {
  name: "SaveCategory",
  data() {
    return {
      SaveCategoryDto: {
        name: "",
        description: "",
        image: null,
      },
      size: false,
      imageFile: null,
      nameValidationState: null,
      descriptionValidationState: null,
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
      if (file.size > maxSize) {
        this.size = false;
        this.SaveCategoryDto.image = null;
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
          Swal.fire({
            title: "Espera un momento...",
            text: "Estamos enviando tu solicitud de adopción",
            imageUrl: gatoWalkingGif,
            timer: 2000,
            timerProgressBar: true,
            imageWidth: 160, // Ancho de la imagen
            imageHeight: 160, // Altura de la imagen
            showConfirmButton: false,
          }).then(() => {
            this.SaveCategory();
          });
        }
      });
    },
    ValidationFormCategoryRegister() {
      return !(
        this.SaveCategoryDto.name &&
        this.SaveCategoryDto.description &&
        this.SaveCategoryDto.image &&
        this.descriptionValidationState &&
        this.nameValidationState
      );
    },
    ValidationSpecialCharactersName() {
      const expresionRegular = /[<>{}' || \\ \/]/;
      return !expresionRegular.test(this.SaveCategoryDto.name);
    },
    UpdateStateInputCategoryName() {
      if (this.SaveCategoryDto.name.trim() === "") {
        // Si el campo está vacío, el estado es null
        this.nameValidationState = null;
      } else {
        // Si no está vacío, actualizamos el estado según la validación
        this.nameValidationState = this.ValidationSpecialCharactersName()
          ? true
          : false;
      }
    },
    ValidationSpecialCharactersName() {
      const expresionRegular = /[<>{}' || \\ \/]/;
      return !expresionRegular.test(this.SaveCategoryDto.name);
    },
    UpdateStateInputCategoryDescription() {
      if (this.SaveCategoryDto.description.trim() === "") {
        // Si el campo está vacío, el estado es null
        this.descriptionValidationState = null;
      } else {
        // Si no está vacío, actualizamos el estado según la validación
        this.descriptionValidationState =
          this.ValidationSpecialCharactersDescription() ? true : false;
      }
    },
    ValidationSpecialCharactersDescription() {
      const expresionRegular = /[<>{}' || \\ \/]/;
      return !expresionRegular.test(this.SaveCategoryDto.description);
    },
  },
};
</script>
<style></style>