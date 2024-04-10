<template>
  <div class="mt-2">
    <div class="card encabezadoColorform">
      <h4 class="mt-2" style="margin-left: 2rem">
        <i
          class="material-icons ms-2"
          style="font-size: larger; vertical-align: middle"
          >pets</i
        >
        Modificación de categoria
      </h4>
    </div>
    <b-card-group deck>
      <b-card class="contentform">
        <b-row class="mt-4">
          <b-col>
            <b-form-group label="Categoria">
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
                El nombre de la categoría contiene caracteres especiales no
                permitidos (/[<>{}' || \\ \/]/)
              </b-form-invalid-feedback>
            </b-form-group>
          </b-col>
          <b-col>
            <b-form-group label="Imagen">
              <b-form-file
                class="form-control"
                v-model="UpdateCategoryDto.image"
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
                v-model="UpdateCategoryDto.description"
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
              v-if="this.UpdateCategoryDto.image"
              :src="this.UpdateCategoryDto.image"
              class="categoryImage mt-2"
              fluid
              thumbnail
            ></b-img>
            <b-img
              v-else
              :src="this.categoryToModify.categoryImage"
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
              v-if="ValidationFormCategoryModify()"
              disabled
              >Modificar</b-button
            >
            <b-button
              variant="outline-success"
              @click="ViewAlertConfirmationRegistrationCategory"
              v-else
              >Modificar</b-button
            >
            <b-button
              class="mx-2"
              variant="outline-danger"
              @click="$emit('UpdateCategory')"
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
        this.UpdateCategoryDto.image = null;
      } else {
        const reader = new FileReader();
        reader.onload = (e) => {
          const base64 = e?.target?.result;
          this.UpdateCategoryDto.image = base64;
        };
        reader.readAsDataURL(file[0]);
        this.size = true;
      }
    },
    async UpdateCategory() {
      try {
        this.UpdateCategoryDtoEncrypted.id= await encrypt(this.UpdateCategoryDto.id);
        this.UpdateCategoryDtoEncrypted.name = await encrypt(this.UpdateCategoryDto.name);
        this.UpdateCategoryDtoEncrypted.description = await encrypt(this.UpdateCategoryDto.description);
        this.UpdateCategoryDtoEncrypted.image = this.UpdateCategoryDto.image;
        const response = await instance.put("/category/",this.UpdateCategoryDtoEncrypted);
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
      return !(
        this.UpdateCategoryDto.name &&
        this.UpdateCategoryDto.description &&
        this.UpdateCategoryDto.image
      );
    },
    UpdateStateInputCategoryName() {
        this.nameValidationState = this.ValidationSpecialCharactersName()
    },
    ValidationSpecialCharactersName() {
      const regex = /[<>{}'\\\/]/;
      return !regex.test(this.UpdateCategoryDto.name);
    },
    UpdateStateInputCategoryDescription() {
      this.descriptionValidationState =this.ValidationSpecialCharactersDescription() ? true : false;
    },
    ValidationSpecialCharactersDescription() {
      const regex = /[<>{}'\\\/]/;
      return !regex.test(this.UpdateCategoryDto.description);
    },
  }
};
</script>