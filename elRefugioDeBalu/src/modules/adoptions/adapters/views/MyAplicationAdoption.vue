<template>
  <div>
    <Encabezado
      color="#FFBC58"
      :imagenUrl="require('@/assets/imgs/black-pig.png')"
      titulo="Mis Solicitudes de adopción"
    />
    <b-container fluid>
      <b-row align-h="center">
        <b-col cols="12" sm="6" md="6" class="pt-0 pt-md-3">
          <b-input-group class="mt-3">
            <b-form-input
              type="text"
              placeholder="Buscar..."
              id="searchValue"
              v-model="searchValue"
              @keyup.enter="getAdoptionByGeneralPaged()"
            ></b-form-input>
            <b-button
              variant="dark-gray"
              type="button"
              id="searchValue"
              @click="getAdoptionByGeneralPaged()"
              class="d-flex align-items-center justify-content-between"
            >
              <b-icon icon="search"></b-icon>
            </b-button>
          </b-input-group>
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="12" sm="12" lg="12" class="mx-2 my-2">
          <b-alert :show="adoptions.length == 0" variant="warning"
            >No hay datos relacionados o aun no cuentas con registros de
            solicitud</b-alert
          >
          <b-table
            :fields="fields"
            :items="adoptions"
            :filter="searchValue"
            label-sort-asc=""
            label-sort-desc=""
            no-sort-reset
            :per-page="size"
            :current-page="page"
            responsive
            small
            striped
            hover
            class="text-center custom-scroll-style"
          >
            <template #cell(status)="data">
              <b-badge :variant="getBadgeVariant(data.value)">{{
                statusSpain(data.value)
              }}</b-badge>
            </template>
            <template #cell(actions)="data">
              <b-button
                pill
                size="sm"
                variant="outline-dark-blue"
                class="px-2 d-none d-md-inline-block"
                @click="viewAdoptionRequest(data.item)"
              >
                Visualizar solicitud &nbsp;
                <b-icon icon="card-heading" font-scale="1.5"></b-icon>
              </b-button>
            </template>
          </b-table>
        </b-col>
      </b-row>
      <b-row class="pt-2">
        <b-col cols="12">
          <b-row class="justify-content-center">
            <b-col cols="6" md="4" class="pt-0 pt-md-3">
              <b-form-select
                :options="options"
                v-model="size"
                class="form-select"
                @change="getAdoptionByGeneralPaged"
              ></b-form-select>
            </b-col>
          </b-row>
          <br />
          <b-pagination
            pills
            v-model="page"
            :total-rows="adoptions.length"
            :per-page="size"
            align="center"
          >
          </b-pagination>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import Encabezado from "../../../../views/components/Encabezado.vue";
import Swal from "sweetalert2";
import { encrypt } from "../../../../kernel/hashFunctions";
import instance from "../../../../config/axios";
import perrito from "@/assets/imgs/perroRascando.gif";

export default {
  name: "MyAplicationAdoption",
  components: {
    Encabezado,
  },
  data() {
    return {
      searchValue: "",
      pageResponse: {},
      options: [1, 5, 10, 20, 50],
      size: 10,
      page: 1,
      fields: [
        { key: "specie", label: "Especie", sortable: true },
        { key: "petName", label: "Nombre mascota", sortable: true },
        { key: "created_at", label: "Fecha de realización", sortable: true },
        { key: "status", label: "Estado", sortable: true },
        { key: "actions", label: "Acciones" },
      ],
      adoptions: [
        {
          id: null,
          specie: "",
          petName: "",
          created_at: "",
          status: "",
        },
      ],
    };
  },
  mounted() {
    this.getAdoptionByGeneralPaged();
  },
  methods: {
    getBadgeVariant(status) {
      switch (status) {
        case "ADOPTED":
          return "primary";
        case "CLOSED":
          return "danger";
        case "PENDING":
          return "warning";
        default:
          return "primary";
      }
    },
    statusSpain(status) {
      switch (status) {
        case "ADOPTED":
          return "Adoptado";
        case "CLOSED":
          return "Cerrado";
        case "PENDING":
          return "Pendiente";
        default:
          return "SIN DATOS";
      }
    },
    async getAdoptionByGeneralPaged() {
      try {
        Swal.fire({
          title: "Cargando...",
          text: "Estamos cargando las solicitudes de adopciones, espera un momento",
          imageUrl: perrito,
          imageWidth: 160,
          imageHeight: 160,
          showConfirmButton: false,
        });
        const response = await instance.post(
          `/adoption/paged/?page=${this.page - 1}&?size=${this.size}`,
          {
            idUser: localStorage.getItem("userId"),
            searchValue: this.searchValue,
          }
        );

        this.adoptions = response.data.data.content;
        this.pageResponse = response.data.data;

        for await (const adoption of this.adoptions) {
          const date = new Date(adoption.created_at);
          const options = { year: "numeric", month: "long", day: "2-digit" };
          adoption.created_at = date.toLocaleDateString("es-ES", options);
        }
        Swal.close();
      } catch (error) {
        Swal.fire({
          title: "Error",
          text: "Ocurrió un error al cargar los usuarios",
          icon: "error",
          iconColor: "#FF0000",
          showConfirmButton: true,
        }).then((result) => {
          if (result.isConfirmed) {
            this.$router.push("/home");
          }
        });
      }
    },
    async viewAdoptionRequest(adoption) {
      let adoptionEncrypted = await encrypt(adoption.id);
      this.$router.push({
        name: "viewAplicationAdoptionRequest",
        params: { adoptionId: adoptionEncrypted },
      });
      localStorage.setItem("adoptionId", adoptionEncrypted);
    },
  },
};
</script>


