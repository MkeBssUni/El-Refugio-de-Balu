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
                <b-button class="button-search"
                  ><b-icon icon="search" aria-hidden="true"></b-icon
                ></b-button>
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
              class="form-control selectCategories"
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
                class="searchInput"
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
            v-for="animal in animalsList"
            :key="animal.id"
          >
            <TransitionGroup name="roll" tag="div" class="">
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
                        @click="
                          ViewAlertConfirmationChangeCategoryStatus(animal)
                        "
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
                        @click="
                          ViewAlertConfirmationChangeCategoryStatus(animal)
                        "
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
            </TransitionGroup>
          </b-col>
        </b-row>
        <b-row class="pt-2">
          <b-col cols="12">
            <b-pagination
              pills
              v-model="page"
              :total-rows="total"
              :per-page="size"
              align="center"
            >
            </b-pagination>
          </b-col>
        </b-row>
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
      animalsList: [],
      search: null,
      options: [
        { value: null, text: "Todos" },
        { value: true, text: "Activos" },
        { value: false, text: "Inactivos" },
      ],
      optionsNum: [1, 4, 8, 16, 24],
      selected: null,
      page: 1,
      total: 0,
      size: 8,
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
    };
  },
  methods: {
    ViewCategoryRegistrationForm() {
      this.saveCategoryForm = !this.saveCategoryForm;
    },
    async GetAllCategories() {
      this.loading = true;
      try {
        const pageSize = 8; // Establece el tamaño de página que prefieras
        const response = await instance.post(
          `/category/paged?page=${this.page - 1}&size=${pageSize}`,
          {
            searchCategoryValue: this.searchCategoryValueDto,
          }
        );

        this.animalsList = response.data.data.content;
        this.total = response.data.data.totalElements;
        this.size = pageSize; // Asegúrate de que esto coincida con el tamaño de página solicitado
        console.log("Total elements:", this.total);

        this.loading = false;
      } catch (error) {
        Swal.fire({
          title: "Ha sucedido un error",
          text: "Inténtelo de nuevo más tarde",
          icon: "error",
          confirmButtonColor: "#118A95",
        }).then(() => {
          this.$router.push("/");
        });
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
  watch: {
    page(previous, next) {
      if (previous !== next) {
        this.GetAllCategories();
      }
    },
  },
};
</script>

