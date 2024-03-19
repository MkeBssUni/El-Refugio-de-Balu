<template>
  <div>
    <Encabezado
      color="#FFBC58"
      :imagenUrl="require('@/assets/imgs/frogs-bros.png')"
      titulo="Solicitudes de adopción"
    />
    <b-container fluid class="my-3">
      <b-row>
        <b-col cols="12" sm="12" md="8" lg="8" xl="8">
          <b-row align-h="center">
            <b-col cols="12" md="5" sm="12" lg="5" class="pt-md-3 my-2">
              <b-form-input
                class="buscador tam-buscador"
                type="text"
                placeholder="Buscar. . ."
                v-model="search"
              >
              </b-form-input>
            </b-col>
            <b-col cols="12" md="5" sm="12" lg="5" class="pt-md-3 my-2">
              <b-form-select
              variant="secondary"
                class="select"
                v-model="selected"
                :options="options"
              ></b-form-select>
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
                    to="/viewAplicationAdoptionRequest"
                  >
                    Visualizar
                    <b-icon icon="info-circle" font-scale="1"></b-icon>
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
                :total-rows="filteredAdoptions.length"
                :per-page="perPage"
                align="center"
              />
            </b-col>
          </b-row>
        </b-col>
        <b-col cols="12" sm="12" md="4" lg="4" xl="4">
          <b-card class="target">
            <img
              src="https://es.mypet.com/wp-content/uploads/sites/23/2021/03/GettyImages-623368750-e1582816063521-1.jpg"
              class="image-pet"
              alt="..."
            />
            <b-card
              bg-variant="card-content-orange"
              class="my-2 information-pet"
            >
              <b-card-body>
                <b-card-title>
                  Sr. Bigotes
                </b-card-title>
                <hr class="my-line" />
                <b-row>
                  <b-col cols="12" sm="12" lg="8" md="8" xl="8">
                    <b-card-text> <b>Raza:</b> Neva Masquerade </b-card-text>
                  </b-col>
                </b-row>
                <b-row>
                  <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                    <b-card-text> <b>Especie:</b> Gato </b-card-text>
                  </b-col>
                  <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                    <b-card-text> <b>Peso:</b> 3kg </b-card-text>
                  </b-col>
                </b-row>
                <b-row>
                  <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                    <b-card-text> <b>Tamaño:</b> Mediano </b-card-text>
                  </b-col>
                  <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                    <b-card-text> <b>Etapa:</b> Joven </b-card-text>
                  </b-col>
                  <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                    <b-card-text> <b>Edad:</b> 8 meses </b-card-text>
                  </b-col>
                  <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                    <b-card-text> <b>Género:</b> Macho </b-card-text>
                  </b-col>
                </b-row>
              </b-card-body>
            </b-card>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import Encabezado from "../../../../views/components/Encabezado.vue";

export default {
  name: "AdoptionList",
  components: {
    Encabezado,
  },
  data() {
    return {
        selected: null,
        options: [
          { value: null, text: 'Por favor selecciona' },
          { value: 'finalizada', text: 'Finalizada' },
          { value: 'aceptada', text: 'Aceptada' },
          { value: 'pendiente', text: 'Pendiente' },
        ],
      search: "",
      fields: [
        { key: "user", label: "Usuario", sortable: true },
        { key: "created_at", label: "Fecha de realización", sortable: true },
        { key: "status", label: "Estado", sortable: true },
        { key: "actions", label: "Acciones" },
      ],
      perPage: 8,
      currentPage: 1,
      adoptions: [
        {
          user: "Anna Christina",
          created_at: "2021-10-10",
          status: "Pendiente",
        },
        {
          user: "Mariannne Santos",
          created_at: "2021-10-10",
          status: "Pendiente",
        },
        {
          user: "Andrea Elizabeth",
          created_at: "2021-10-10",
          status: "Pendiente",
        },
        {
          user: "Andrea Elizabeth",
          created_at: "2021-10-10",
          status: "Pendiente",
        },
        {
          user: "Andrea Elizabeth",
          created_at: "2021-10-10",
          status: "Pendiente",
        },
        {
          user: "Andrea Elizabeth",
          created_at: "2021-10-10",
          status: "Pendiente",
        },
        {
          user: "Andrea Elizabeth",
          created_at: "2021-10-10",
          status: "Pendiente",
        },
        {
          user: "Andrea Elizabeth",
          created_at: "2021-10-10",
          status: "Pendiente",
        },
        {
          user: "Andrea Elizabeth",
          created_at: "2021-10-10",
          status: "Pendiente",
        },
        {
          user: "Andrea Elizabeth",
          created_at: "2021-10-10",
          status: "Pendiente",
        },
      ],
    };
  },
  methods: {
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
    },
  },
  computed: {
    filteredAdoptions() {
      return this.adoptions.filter((adoption) => {
        return (
          adoption.user.toLowerCase().includes(this.search.toLowerCase()) ||
          adoption.created_at
            .toLowerCase()
            .includes(this.search.toLowerCase()) ||
          adoption.status.toLowerCase().includes(this.search.toLowerCase())
        );
      });
    },
  },
};
</script>

<style>
.target {
  background-image: url("../../../../assets/imgs/pawBackground.jpg") !important;
  background-size: cover;
  background-position: center;
  box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px,
    rgba(17, 17, 26, 0.05) 0px 8px 32px;
}

.image-pet {
  width: 100%;
  max-height: 70%;
  border-radius: 15px;
  box-shadow: rgba(0, 0, 0, 0.16) 0px 3px 6px, rgba(0, 0, 0, 0.23) 0px 3px 6px;
  transition: transform 0.3s ease; /* Agrega una transición suave para el efecto de zoom */
}

.select{
    color: #a5a5a5 !important;
    border-color: #a5a5a5  !important;
    border-radius: 15px;
    height: 37px;
}

/* Cuando el mouse pasa sobre la imagen */
.image-pet:hover {
  transform: scale(1.1); /* Aumenta el tamaño de la imagen al 110% */
}

.information-pet {
  box-shadow: rgba(0, 0, 0, 0.16) 0px 3px 6px, rgba(0, 0, 0, 0.23) 0px 3px 6px;
  transition: transform 0.3s ease; /* Agrega una transición suave para el efecto de zoom */
}

.information-pet:hover {
  transform: scale(1.1); /* Aumenta el tamaño de la imagen al 110% */
}

.my-line {
  border: none;
  height: 2px;
  background-color: black;
}


</style>
