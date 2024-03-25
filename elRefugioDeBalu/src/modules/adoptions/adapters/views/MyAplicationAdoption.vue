<template>
  <div>
    <Encabezado
      color="#FFBC58"
      :imagenUrl="require('@/assets/imgs/black-pig.png')"
      titulo="Mis Solicitudes de adopción"
    />
    <b-container fluid> 
        <b-row align-h="center">
        <b-col cols="12" md="5" sm="12" lg="5"   class="pt-md-3 my-2">
            <b-form-input
            class="buscador tam-buscador"
              type="text"
              placeholder="Buscar. . ."
              v-model="search"
            >
            </b-form-input>       
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="12" sm="12" lg="12" class="mx-2 my-2">
          <b-table
            :fields="fields"
            :items="filteredAdoptions"
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
              <b-badge :variant="getBadgeVariant(data.value)">{{ data.value }}</b-badge>
            </template>
            <template #cell(actions)>
              <b-button pill size="sm" variant="outline-dark-blue" class="px-2 d-none d-md-inline-block">
                Visualizar
                <b-icon icon="info-circle" font-scale="1"></b-icon>
              </b-button>
              <b-button pill size="sm" variant="outline-dark-blue" class="px-2 d-md-none">
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
            :total-rows="filteredAdoptions.length"
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
  name: "MyAplicationAdoption",
  components: {
    Encabezado,
  },
  data(){
    return{
        search: "",
        fields: [
            { key: "species", label: "Especie", sortable: true},
            { key: "name", label: "Nombre mascota", sortable: true },
            { key: "created_at", label: "Fecha de realización", sortable: true },
            { key: "status", label: "Estado", sortable: true },
            { key: "actions", label: "Acciones" },
        ],
        perPage: 5,
        currentPage: 1,
        adoptions: [
            {
                species: "Perro",
                name: "Firulais",
                created_at: "2021-10-10",
                status: "Pendiente",
            },
            {
                species: "Gato",
                name: "Michi",
                created_at: "2021-10-10",
                status: "Aceptada",
            },
            {
                species: "Perro",
                name: "Firulais",
                created_at: "2021-10-10",
                status: "Finalizada",
            },
            {
                species: "Gato",
                name: "Michi",
                created_at: "2021-10-10",
                status: "Pendiente",
            },
            {
                species: "Perro",
                name: "Firulais",
                created_at: "2021-10-10",
                status: "Aceptada",
            },
            {
                species: "Gato",
                name: "Michi",
                created_at: "2021-10-10",
                status: "Finalizada",
            },
        ],
    }
  },
  methods:{
    getBadgeVariant(status) {
      switch (status) {
        case "Aceptada":
          return "success";
        case "Finalizada":
          return "secondary";
        case "Pendiente":
          return "warning";
        default:
          return "primary";
      }
    }
  },
  computed: {
    filteredAdoptions() {
      return this.adoptions.filter((adoption) => {
        return (
          adoption.species.toLowerCase().includes(this.search.toLowerCase()) ||
          adoption.name.toLowerCase().includes(this.search.toLowerCase()) ||
          adoption.created_at.toLowerCase().includes(this.search.toLowerCase()) ||
          adoption.status.toLowerCase().includes(this.search.toLowerCase())
        );
      });
    },
  },
};
</script>

<style>
.buscador{
    color: #00737E !important;
    border-color: #00737E !important;
    border-radius: 20px;
}

.tam-buscador{
    width: 90%;
}

.buscador::placeholder {
  color: #00737E !important;
}
</style>
