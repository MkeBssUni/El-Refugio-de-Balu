<template>
  <div class="mt-2">
    <div class="card encabezadoColorform">
      <h4 class="mt-2" style="margin-left: 1rem">
        <i
          class="material-icons ms-2"
          style="font-size: larger; vertical-align: middle"
          >pets</i
        >
        Registro de categoria
      </h4>
    </div>
      <b-card class="contentform">
        <b-row class="mt-4">
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
  </div>
</template>

<script>
import Swal from "sweetalert2";
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import instance from "../../../../config/axios";
import { encrypt } from "../../../../kernel/hashFunctions";

export default {
  data() {
    return {
      SaveCategoryDto: {
        name: "",
        description: "",
        image: "",
      },
      SaveCategoryDtoEncrypted: {
        name: "",
        description: "",
        image: "",
        userId:localStorage.getItem('userId')
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
    async SaveCategory() {
      try {
        this.SaveCategoryDtoEncrypted.name = await encrypt(this.SaveCategoryDto.name);
        this.SaveCategoryDtoEncrypted.description = await encrypt(
          this.SaveCategoryDto.description
        );
        this.SaveCategoryDtoEncrypted.image = await encrypt(this.SaveCategoryDto.image);
        const response = await instance.post(
          "/category/",
          this.SaveCategoryDtoEncrypted
        );
        if (response.status == 201) {
          Swal.fire({
            title: "Acción realizada con éxito",
            icon: "success",
            confirmButtonColor: "#118A95",
          });
        this.$emit("SavedCategory");
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
            text: "Estamos realizando el registro",
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
      const expresionRegular = /[<>{}'||\\\/]/;
      return !expresionRegular.test(this.SaveCategoryDto.name);
    },
    UpdateStateInputCategoryName() {
      if (this.SaveCategoryDto.name.trim() === "") {
        this.nameValidationState = null;
      } else {
        this.nameValidationState = this.ValidationSpecialCharactersName()
          ? true
          : false;
      }
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
      const expresionRegular = /[<>{}'||\\\/]/;
      return !expresionRegular.test(this.SaveCategoryDto.description);
    },
  },
};
</script>