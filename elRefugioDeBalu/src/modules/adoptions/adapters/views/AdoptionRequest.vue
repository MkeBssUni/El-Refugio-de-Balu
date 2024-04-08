<template>
  <div class="">
    <Encabezado
      color="#E84E0F"
      :imagenUrl="require('@/assets/imgs/perrogato.png')"
      titulo="Solicitudes en Adopción"
    />
    <b-container fluid>
      <b-row align-h="center" class="lg">
        <b-col cols="12" md="2" class="pt-md-3">
          <b-input-group class="mt-3">
            <b-form-input
              type="text"
              placeholder="Buscar. . ."
              v-model="search"
            ></b-form-input>
          </b-input-group>
        </b-col>
      </b-row>
      <b-row class="px-4 pt-4">
        <b-col cols="12">
          <b-table
            :fields="fields"
            :items="filteredadoptions"
            :filter="search"
            label-sort-asc=""
            label-sort-desc=""
            no-sort-reset
            :per-page="perPage"
            :current-page="currentPage"
            responsive
            small
            striped
            hover
            class="text-center custom-scroll-style"
          >
            <template #cell(status)="data">
              <b-badge :variant="getBadgeVariant(data.value)">{{
                data.value
              }}</b-badge>
            </template>
            <template #cell(actions)>
              <b-button
                pill
                size="sm"
                variant="outline-dark-blue"
                class="px-2 d-none d-md-inline-block"
              >
                Visualizar
                <b-icon icon="card-heading" font-scale="1.5"></b-icon>
              </b-button>
              <b-button
                pill
                size="sm"
                variant="outline-dark-blue"
                class="px-2 d-md-none"
              >
                <b-icon icon="info-circle" font-scale="1"></b-icon>
              </b-button>
            </template>
          </b-table>
        </b-col>
      </b-row>
      <b-row class="pt-4">
        <b-col cols="12">
          <b-pagination
          pills
            v-model="currentPage"
            :total-rows="filteredadoptions.length"
            :per-page="perPage"
            align="center"
          />
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import Encabezado from "../../../../views/components/Encabezado.vue";

export default {
  name: "AdoptioRequest",
  components: {
    Encabezado,
  },
  data() {
    return {
      search: "",
      fields: [
        { key: "namePet", label: "Nombre de la mascota", sortable: false },
        { key: "speciesPet", label: "Especie", sortable: true },
        {
          key: "dateRealization",
          label: "Fecha de realización",
          sortable: true,
        },
        { key: "nameAdopt", label: "Nombre del Adoptante", sortable: false },
        { key: "status", label: "Estado", sortable: false },
        { key: "actions", label: "Acciones" },
      ],
      ListAdoptions: [
        {
          namePet: "Luna",
          speciesPet: "Gato",
          dateRealization: "20-01-2021",
          nameAdopt: "Andrea Elizabeth",
          status: "Aceptada",
        },
        {
          namePet: "Max",
          speciesPet: "Perro",
          dateRealization: "15-03-2020",
          nameAdopt: "Juan Perez",
          status: "Pendiente",
        },
        {
          namePet: "Bella",
          speciesPet: "Perro",
          dateRealization: "10-05-2019",
          nameAdopt: "Maria Rodriguez",
          status: "Rechazada",
        },
      ],
    };
  },
  methods: {
    getBadgeVariant(status) {
      switch (status) {
        case "Aceptada":
          return "success";
        case "Rechazada":
          return "danger";
        case "Pendiente":
          return "warning";
        default:
          return "secondary";
      }
    },
  },
  computed: {
    filteredadoptions() {
      // Filtrar las mascotas según los valores seleccionados en los filtros
      return this.ListAdoptions.filter((adoption) => {
        const namePetMatch = this.namePetFilter
          ? adoption.namePet === this.namePetFilter
          : true;
        const statusMatch = this.statusFilter
          ? adoption.status === this.statusFilter
          : true;

        return namePetMatch && statusMatch;
      });
    },
  },
};
</script>
