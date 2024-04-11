<template>
  <div>
    <Encabezado color="#FFBC58" :imagenUrl="require('@/assets/imgs/frogs-bros.png')" titulo="Solicitudes de adopción" />
    <b-container fluid class="my-3">
      <b-row>
        <b-col cols="12" sm="12" md="8" lg="8" xl="8">
          <b-row align-h="center">
            <b-col cols="12" md="5" sm="12" lg="5" class="pt-md-3 my-2">

              <b-input-group class="mt-3">
                <b-form-input type="text" placeholder="Buscar..." id="searchValue" v-model="searchValue"
                  @keyup.enter="getList()"></b-form-input>
                <b-button variant="dark-gray" type="button" id="searchValue" @click="getList()"
                  class="d-flex align-items-center justify-content-between">
                  <b-icon icon="search"></b-icon>
                </b-button>
              </b-input-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col cols="12" sm="12" lg="12" class="mx-2 my-2">
              <b-table :fields="fields" :items="adoptions" :filter="searchValue" label-sort-asc="" label-sort-desc=""
                no-sort-reset :per-page="size" :current-page="page" responsive small striped hover
                class="text-center custom-scroll-style">
                <template #cell(status)="data">
                  <b-badge :variant="getBadgeVariant(data.value)">{{
                    statusSpain(data.value)
                  }}</b-badge>
                </template>
                <template #cell(actions)="data">
                  <b-button pill size="sm" variant="outline-dark-blue" class="px-2 d-none d-md-inline-block"
                    to="/viewAplicationAdoptionRequest" @click="viewAdoptionRequest(data.item)"
                    v-b-tooltip.hover.top="'Ver detalles'">                  
                    <b-icon icon="card-heading" font-scale="1.5"></b-icon>
                  </b-button>
                </template>
              </b-table>
            </b-col>
          </b-row>
          <b-row class="px-4">
                <b-col cols="12" class="d-flex align-items-center">
                    <label for="perPage">Selecciona la cantidad de registros que deseas mostrar:</label>
                    <b-form-select :options="options" v-model="size" class="ms-3 my-3 form-select" style="width: 80px"
                        @change="getList()"></b-form-select>
                </b-col>
                <b-col cols="12" class="mt-1">
                    <b-pagination pills v-model="page" :total-rows="total" :per-page="size" align="center">
                    </b-pagination>
                </b-col>
            </b-row>
        </b-col>
        <b-col cols="12" sm="12" md="4" lg="4" xl="4">
          <b-card class="target">
            <b-row>
              <b-col cols="12" sm="12" lg="12" md="12">
                <img :src="credentialPet.image" alt="Imagen de perfil" class="image-pet" />
                <b-card bg-variant="card-content-orange" class="my-2 information-pet">
                  <b-card-body>
                    <b-card-title> {{ credentialPet.name }} </b-card-title>
                    <hr class="my-line" />
                    <b-row>
                      <b-col cols="12" sm="12" lg="8" md="8" xl="8">
                        <b-card-text>
                          <b>Raza:</b> &nbsp;{{ credentialPet.breed }}
                        </b-card-text>
                      </b-col>
                    </b-row>
                    <b-row>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text>
                          <b>Especie:</b>&nbsp; {{ credentialPet.category }}
                        </b-card-text>
                      </b-col>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text>
                          <b>Peso:</b>&nbsp;
                          {{ getAgeNumber(credentialPet.weight) }} {{
                            mapweightUnits(getAgeUnit(credentialPet.weight))
                          }}

                        </b-card-text>
                      </b-col>
                    </b-row>
                    <b-row>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text>
                          <b>Tamaño:</b>&nbsp;
                          {{
                            mapSize(credentialPet.size.toString().toLowerCase())
                          }}
                        </b-card-text>
                      </b-col>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text>
                          <b>Etapa:</b> &nbsp;
                          {{
                            maplifeStages(
                              credentialPet.lifeStage.toString().toLowerCase()
                            )
                          }}
                        </b-card-text>
                      </b-col>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text>
                          <b>Edad:</b>&nbsp;{{ getAgeNumber(credentialPet.age) }} {{
                            mapageUnits(getAgeUnit(credentialPet.age))
                          }}</b-card-text>
                      </b-col>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text> <b>Género:</b>&nbsp; {{ mapGender(credentialPet.gender.toString().toLowerCase())
                          }}
                        </b-card-text>
                      </b-col>
                    </b-row>
                  </b-card-body>
                </b-card>
              </b-col>
            </b-row>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import Encabezado from "../../../../views/components/Encabezado.vue";
import Swal from "sweetalert2";
import instance from "../../../../config/axios";
import { encrypt } from "../../../../kernel/hashFunctions";
import perroChato from "@/assets/imgs/perroChato1.gif";
import {
  sizes,
  weightUnits,
  lifeStages,
  ageUnits,
  gender
} from "../../../../kernel/data/mappingDictionaries";

export default {
  name: "AdoptionList",
  total: 0,
  props: {
    petId: {
      type: String,
      required: true
    }
  },
  components: {
    Encabezado,
  },
  data() {
    return {
      selected: null,
      searchValue: "",
      pageResponse: {},
      options: [1, 5, 10, 20, 50],
      size: 10,
      page: 1,
      credentialPet: {},
      state: [
        { value: null, text: "Por favor selecciona" },
        { value: "CLOSED", text: "Finalizada" },
        { value: "ADOPTED", text: "Adoptado" },
        { value: "PENDING", text: "Pendiente" }
      ],
      fields: [
        { key: "fullname", label: "Usuario", sortable: true },
        { key: "created_at", label: "Fecha de realización", sortable: true },
        { key: "status", label: "Estado", sortable: true },
        { key: "actions", label: "Acciones" },
      ],
      adoptions: [
        {
          id: 0,
          status: "",
          fullname: "",
          created_at: "",
        },
      ],
    };
  },
  mounted() {
    if (!this.petId && sessionStorage.getItem('petId')) this.petId = sessionStorage.getItem('petId');
    if (!this.petId && !sessionStorage.getItem('petId')) this.$router.push('/moderated/petList');
    this.getList();
  },
  methods: {
    getAgeUnit(age) {
      const matches = age.match(/^(\d+)\s*(\w+)$/);
      if (matches && matches.length === 3) {
        return matches[2].toLowerCase();
      } else {
        return "";
      }
    },
    getAgeNumber(age) {
      const matches = age.match(/^(\d+)\s*(\w+)$/);
      if (matches && matches.length === 3) {
        return matches[1]; // Devuelve solo el número
      } else {
        return ""; // Opción de manejo de errores si el formato no es válido
      }
    },
    goBack() {
      this.$router.go(-1);
    },
    mapSize(size) {
      return sizes[size] || size;
    },
    maplifeStages(stage) {
      return lifeStages[stage] || stage;
    },
    mapweightUnits(weightUnit) {
      return weightUnits[weightUnit] || weightUnit;
    },
    mapGender(genderpet) {
      return gender[genderpet] || genderpet;
    },
    mapageUnits(ageUnit) {
      return ageUnits[ageUnit] || ageUnit;
    },
    getDate(date) {
      const formattedDate = new Date(date);
      const options = { year: "numeric", month: "long", day: "2-digit" };
      return formattedDate.toLocaleDateString("es-ES", options);
    },
    getBadgeVariant(status) {
      switch (status) {
        case "ADOPTED":
          return "info";
        case "CLOSED":
          return "danger";
        case "PENDING":
          return "warning";
        default:
          return "info";
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
    async getList() {
      try {
        Swal.fire({
          title: "Cargando...",
          text: "Estamos cargando las solicitudes, espera un momento",
          imageUrl: perroChato,
          imageWidth: 160,
          imageHeight: 160,
          showConfirmButton: false,
        });
        const response = await instance.post(
          `/adoption/byPet/?page=${this.page - 1}&?size=${this.size}`,
          {
            idPet: this.petId,
            searchValue: this.searchValue,
          }
        );
        this.adoptions = response.data.data.content;
        for await (const adoption of this.adoptions) {
          const date = new Date(adoption.created_at);
          const options = { year: "numeric", month: "long", day: "2-digit" };
          adoption.created_at = date.toLocaleDateString("es-ES", options);
        }
        this.getCredentialPet();
        Swal.close();
      } catch (error) {
        Swal.fire({
          title: "Error",
          text: "Ocurrió un error al cargar las solicitudes",
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
    async getCredentialPet() {
      try {
        const response = await instance.post("/pet/credential", {
          id: this.petId
        });
        this.credentialPet = response.data.data;
      } catch (error) {
        Swal.fire({
          title: "Error",
          text: "Ocurrió un error al cargar la solicitud",
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
        name: "viewAplicationAdoptionRequestMod",
        params: { adoptionId: adoptionEncrypted },
      });
      localStorage.setItem("adoptionId", adoptionEncrypted);
    },


  }
};
</script>

<style>
.target {
  background-image: url("../../../../assets/imgs/pawBackground.jpg") !important;
  background-size: cover;
  background-position: center;
  box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px,
    rgba(17, 17, 26, 0.05) 0px 8px 32px;
  max-height: 80%;
}

.image-pet {
  width: 100%;
  max-height: 40%;
  border-radius: 15px;
  box-shadow: rgba(0, 0, 0, 0.16) 0px 3px 6px, rgba(0, 0, 0, 0.23) 0px 3px 6px;
  transition: transform 0.3s ease;
  /* Agrega una transición suave para el efecto de zoom */
  object-fit: cover;
}

.select {
  color: #a5a5a5 !important;
  border-color: #a5a5a5 !important;
  border-radius: 15px;
  height: 37px;
}

/* Cuando el mouse pasa sobre la imagen */
.image-pet:hover {
  transform: scale(1.1);
  /* Aumenta el tamaño de la imagen al 110% */
}

.information-pet {
  box-shadow: rgba(0, 0, 0, 0.16) 0px 3px 6px, rgba(0, 0, 0, 0.23) 0px 3px 6px;
  transition: transform 0.3s ease;
  /* Agrega una transición suave para el efecto de zoom */
}

.information-pet:hover {
  transform: scale(1.1);
  /* Aumenta el tamaño de la imagen al 110% */
}

.my-line {
  border: none;
  height: 2px;
  background-color: black;
}
</style>
