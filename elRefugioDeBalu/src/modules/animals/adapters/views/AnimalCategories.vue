<template>
  <div>
    <div class="AnimalHeader">
      <Encabezado
        color="#E84E0F"
        :imagenUrl="require('../../../../assets/imgs/mascotas5.png')"
        titulo="Categorias de animales"
      />
    </div>
    <b-container fluid>
      <Overlay v-if="loading" />
      <!-- lo importamos dento del container, independiente de donde lo coloquemos, se vera sobre toda la pantalla siempre -->
      <div class="mt-3">
        <b-row
          class="justify-content-end"
          v-if="saveCategoryForm && updateCategoryForm"
        >
          <b-col
            cols="12"
            sm="12"
            md="3"
            lg="2"
            xl="2"
            class="d-flex justify-content-end my-2"
          >
            <b-button
              as="col"
              cols="12"
              sm="12"
              md="1"
              class="py-1 w-100 d-flex align-items-center justify-content-center"
              variant="outline-danger"
              @click="ViewCategoryRegistrationForm()"
            >
              Registrar <i class="material-icons ms-3">add_circle</i>
            </b-button>
          </b-col>
          <b-col cols="12" sm="12" md="4 " lg="3" xl="3" class="my-2">
            <b-form-select
              v-model="selected"
              as="col"
              cols="12"
              sm="12"
              md="2"
              lg="2"
              xl="2"
              class="form-control selectCategories"
              :options="options"
            ></b-form-select>
          </b-col>
          <b-col cols="12" sm="12" md="4 " lg="3" xl="3" class="my-2">
            <b-input-group>
              <b-form-input
                as
                id="searchInput"
                type="search"
                placeholder="Buscar..."
                class="searchInput"
                v-model="search"
              ></b-form-input>
              <b-input-group-append>
                <b-button class="button-search"><b-icon icon="search" aria-hidden="true"></b-icon></b-button>
              </b-input-group-append>
            </b-input-group>
          </b-col>
        </b-row>
        <b-row class="justify-content-end" v-else>
          <b-col
            cols="12"
            sm="12"
            md="3"
            lg="2"
            xl="2"
            class="d-flex justify-content-end my-2"
          >
            <b-button
              as="col"
              cols="12"
              sm="12"
              md="1"
              pill
              class="py-1 w-100 d-flex align-items-center justify-content-center"
              disabled
              variant="outline-danger"
            >
              Registrar <i class="material-icons ms-3">add_circle</i>
            </b-button>
          </b-col>
          <b-col cols="12" sm="12" md="4 " lg="3" xl="3" class="my-2">
            <b-form-select
              v-model="selected"
              as="col"
              cols="12"
              sm="12"
              md="2"
              lg="2"
              xl="2"
              class="form-control selectCategories rounded-pill"
              disabled
            ></b-form-select>
          </b-col>
          <b-col cols="12" sm="12" md="4 " lg="3" xl="3" class="my-2">
            <b-input-group>
              <b-form-input
                as
                id="searchInput"
                type="search"
                disabled
                placeholder="Buscar..."
                class="searchInput rounded-pill"
                v-model="search"
              ></b-form-input>
            </b-input-group>
          </b-col>
        </b-row>
        <b-row class="mt-2" v-if="!saveCategoryForm">
          <b-col>
            <saveCategory @SavedCategory="HideCategoryRegistrationForm()" />
          </b-col>
        </b-row>
        <b-row class="mt-2" v-if="!updateCategoryForm">
          <b-col>
            <updateCategory
              :categoryToModify="categoryToModify"
              @UpdateCategory="HideCategoryModifyForm()"
            />
          </b-col>
        </b-row>
      </div>
      <div class="animalCategories mt-3">
        <b-row class="mx-3 justify-content-center">
          <b-col
            class="mt-3"
            cols="12"
            sm="12"
            md="4 "
            lg="3"
            xl="3"
            v-for="animal in AnimalsList"
            :key="animal.id"
          >
            <b-card
              :title="animal.categoryName"
              :img-src="animal.categoryImage"
              img-alt="Image"
              img-top
              tag="article"
              class="mb-2 card-img-top-animals"
            >
              <b-card-body class="py-0 justify-content-center">
                <b-row v-if="saveCategoryForm && updateCategoryForm">
                  <b-col
                    class="my-2 mx-2 justify-content-center px-1"
                    cols="12"
                    sm="12"
                    md="12"
                    lg="12"
                    xl="5"
                  >
                    <b-button
                      pill
                      class="d-flex align-items-center justify-content-center btn-outline-dark-orange-secondary w-100 px-0"
                      style="font-size: 0.8rem"
                      @click="ViewCategoryModifyForm(animal)"
                    >
                      Modificar
                      <i class="material-icons ms-2" style="font-size: 0.8rem"
                        >border_color</i
                      >
                    </b-button>
                  </b-col>
                  <b-col
                    class="my-2 mx-2 justify-content-center px-0"
                    cols="12"
                    sm="12"
                    md="12"
                    lg="12"
                    xl="5"
                  >
                    <b-button
                      v-if="animal.categoryStatus"
                      pill
                      variant="outline-success"
                      class="d-flex align-items-center justify-content-center w-100 px-0"
                      v-b-tooltip.hover
                      title="Esta categoria es visible para los usuarios"
                      style="font-size: 0.8rem"
                      @click="ViewAlertConfirmationChangeCategoryStatus(animal)"
                      >Habilitada
                      <i class="material-icons ms-2" style="font-size: 1rem"
                        >done</i
                      ></b-button
                    >
                    <b-button
                      v-else
                      pill
                      variant="outline-danger"
                      style="font-size: 0.8rem"
                      class="d-flex align-items-center justify-content-center w-100 px-0"
                      v-b-tooltip.hover
                      title="Esta categoria no es visible para los usuarios"
                      @click="ViewAlertConfirmationChangeCategoryStatus(animal)"
                      >Deshabilitada
                      <i class="material-icons ms-2" style="font-size: 1rem"
                        >close</i
                      ></b-button
                    >
                  </b-col>
                </b-row>
                <b-row v-else>
                  <b-col
                    class="my-2 mx-2 justify-content-center px-1"
                    cols="12"
                    sm="12"
                    md="12"
                    lg="12"
                    xl="5"
                  >
                    <b-button
                      pill
                      class="d-flex align-items-center justify-content-center btn-outline-dark-orange-secondary w-100 px-0"
                      style="font-size: 0.8rem"
                      disabled
                    >
                      Modificar
                      <i class="material-icons ms-2" style="font-size: 0.8rem"
                        >border_color</i
                      >
                    </b-button>
                  </b-col>
                  <b-col
                    class="my-2 mx-2 justify-content-center px-0"
                    cols="12"
                    sm="12"
                    md="12"
                    lg="12"
                    xl="5"
                  >
                    <b-button
                      v-if="animal.status"
                      disabled
                      pill
                      variant="outline-success"
                      class="d-flex align-items-center justify-content-center w-100 px-0"
                      v-b-tooltip.hover
                      title="Esta categoria es visible para los usuarios"
                      style="font-size: 0.8rem"
                      >Habilitada
                      <i class="material-icons ms-2" style="font-size: 1rem"
                        >done</i
                      ></b-button
                    >
                    <b-button
                      v-else
                      disabled
                      pill
                      variant="outline-danger"
                      style="font-size: 0.8rem"
                      class="d-flex align-items-center justify-content-center w-100 px-0"
                      v-b-tooltip.hover
                      title="Esta categoria no es visible para los usuarios"
                      >Deshabilitada
                      <i class="material-icons ms-2" style="font-size: 1rem"
                        >close</i
                      ></b-button
                    >
                  </b-col>
                </b-row>
              </b-card-body>
            </b-card>
          </b-col>
        </b-row>
        <div class="mt-3 d-flex justify-content-center">
          <b-pagination
            pills
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
          ></b-pagination>
        </div>
      </div>
    </b-container>
  </div>
</template>

<script>
import Encabezado from "../../../../views/components/Encabezado.vue";
import tortugas from "../../../../assets/imgs/tortugas1.jpeg";
import SaveCategory from "./SaveCategory.vue";
import UpdateCategory from "./UpdateCategory.vue";
import FishLoading from "../../../../assets/imgs/peces.gif";
import Swal from "sweetalert2";
import instance from "../../../../config/axios";
import { decrypt, encrypt } from "../../../../kernel/hashFunctions";
import Overlay from "../../../../utils/Overlay.vue";
export default {
  name: "AnimalCategories",
  components: {
    Encabezado,
    SaveCategory,
    UpdateCategory,
    Overlay,
  },
  data() {
    return {
      AnimalsList: [],
      search: null,
      options: [
        { value: null, text: "Todos" },
        { value: true, text: "Activos" },
        { value: false, text: "Inactivos" },
      ],
      selected: null,
      currentPage: 1,
      perPage: 8,
      rows: 1,
      saveCategoryForm: true,
      updateCategoryForm: true,
      categoryToModify: null,
      searchCategoryValueDto: "",
      changeStatusDto: {
        id: 0,
        status: false,
        userId: localStorage.getItem("userId"),
      },
      empty: true,
      loading: true,
      //establecemos en true el overlay, para que desde que se abre la pagina sea lo primero que se vea
    };
  },
  methods: {
    ViewCategoryRegistrationForm() {
      this.saveCategoryForm = !this.saveCategoryForm;
    },
    async GetAllCategories() {
      this.loading = true;
      //cada que recargamos, lo volvemos a establecer en true
      try {
        const response = await instance.post("/category/paged", {
          searchCategoryValue: this.searchCategoryValueDto,
        });
        this.rows = response.data.totalPages;
        this.AnimalsList = response.data.data.content;
        this.perPage = response.data.pageSize;
        this.currentPage = response.data.pageNumber;

        this.loading = false;
        //si la peticion se hace con exito,seteamos el loading en false para que se deje de ver el overlay,
      } catch (error) {
        console.error("Error al obtener categorías:", error);
      }
    },
    HideCategoryRegistrationForm() {
      this.ViewCategoryRegistrationForm();
      this.GetAllCategories();
    },
    HideCategoryModifyForm() {
      this.ViewCategoryModifyForm(null);
      this.GetAllCategories();
    },
    ViewCategoryModifyForm(category) {
      this.categoryToModify = category;
      this.updateCategoryForm = !this.updateCategoryForm;
    },
    async ViewAlertConfirmationChangeCategoryStatus(category) {
      Swal.fire({
        title: "¿Seguro que desea realizar la acción?",
        text: "Se cambiará el estatus de la categoría " + category.categoryName,
        icon: "question",
        showCancelButton: true,
        confirmButtonColor: "#53A93D",
        cancelButtonColor: "#A93D3D",
        confirmButtonText: "Aceptar",
        cancelButtonText: "Cancelar",
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire({
            title: "Espera un momento…",
            text: "Estamos realizando tu modificación",
            imageUrl: FishLoading,
            timer: 2000,
            timerProgressBar: true,
            imageWidth: 160,
            imageHeight: 160,
            showConfirmButton: false,
          }).then(() => {
            this.ChangeCategoryStatus(category);
          });
        }
      });
    },
    async ChangeCategoryStatus(category) {
      try {
        (this.changeStatusDto.id = await encrypt(category.id)),
          (this.changeStatusDto.status = await encrypt(
            category.categoryStatus
          ));
        const response = await instance.patch(
          "/category/",
          this.changeStatusDto
        );
        if (response.status == 200) {
          Swal.fire({
            title: "Acción realizada con exito",
            icon: "success",
            confirmButtonColor: "#118A95",
          });
          this.GetAllCategories();
        } else {
          Swal.fire({
            title: "Ha sucedido un error",
            text: "Inténtelo de nuevo más tarde",
            icon: "error",
            confirmButtonColor: "#118A95",
          });
          this.GetAllCategories();
        }
      } catch (error) {
        Swal.fire({
          title: "Ha sucedido un error",
          text: "codigo de error: " + error.code,
          icon: "error",
          confirmButtonColor: "#118A95",
        });
        this.GetAllCategories();
      }
    },
  },
  mounted() {
    this.GetAllCategories();
  },
};
</script>

<style></style>
