<template>
  <div class="">
    <Encabezado
      color="#00737e"
      :imagenUrl="require('@/assets/imgs/perroblanco.png')"
      titulo="Lista de usuarios"
    />
    <b-container fluid>
      <b-row class="justify-content-center px-4">
        <b-col cols="12" md="8" class="pt-0 pt-md-3 text-center">
          <b-input-group class="mt-3">
            <b-form-input
              type="text"
              placeholder="Buscar..."
              v-model="search"
              class="form-control-md border-right-0"
            ></b-form-input>
            <b-input-group-append>
              <b-icon
                icon="search"
                style="
                  background-color: transparent;
                  position: absolute;
                  right: 10px;
                  top: 50%;
                  transform: translateY(-50%);
                "
              ></b-icon>
            </b-input-group-append>
          </b-input-group>
        </b-col>
        <b-col cols="12" md="4" class="pt-0 pt-md-3">
          <b-button variant="outline-success" class="mt-3 d-flex align-items-center justify-content-center">
            <b-icon icon="person-plus-fill" font-scale="1"></b-icon> Agregar
          </b-button>
        </b-col>
      </b-row>
      <b-row class="px-4 pt-4">
        <b-col cols="12">
          <b-table
            :fields="fields"
            :items="filteredUsers"
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
            <template #cell(actions)="data">
              <!-- Agregar el evento @click para mostrar la alerta -->
              <b-button
                @click="showDeleteConfirmation(data.item)"
                pill
                size="sm"
                variant="outline-danger"
                class="px-2"
              >
              <i
                  class="material-icons "
                  style="font-size: larger; vertical-align: middle"
                  >personremove</i>
              </b-button>
            </template>

            <template #cell(status)="data">
              <b-badge v-if="data.value === 'Activo'" variant="success">
                {{ data.value }}
              </b-badge>
              <b-badge v-else-if="data.value === 'Inactivo'" variant="danger">
                {{ data.value }}
              </b-badge>
            </template>
          </b-table>
        </b-col>
      </b-row>
      <b-row class="pt-4">
        <b-col cols="12">
          <b-pagination pills
            v-model="currentPage"
            :total-rows="filteredUsers.length"
            :per-page="perPage"
            align="center"
          />
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import Swal from 'sweetalert2'; 

import Encabezado from "../../../views/components/Encabezado.vue";
export default {
  data() {
    return {
      search: "",
      perPage: 5,
      currentPage: 1,
      fields: [
        { key: "name", label: "Nombre" },
        { key: "lastName", label: "Apellido Paterno" },
        { key: "middleName", label: "Apellido Materno" },
        { key: "email", label: "Correo Electrónico" },
        { key: "phoneNumber", label: "Numero de teléfono" },
        { key: "status", label: "Estado" },
        { key: "actions", label: "Acciones" },
      ],
      users: [
        {
          name: "Lisseth Georgina",
          lastName: "Fuentes",
          middleName: "Figueroa",
          email: "liss@gmail.com",
          phoneNumber: "7773778161",
          status: "Activo",
        },
         {
          name: "Anna christina",
          lastName: "Bustos",
          middleName: ".",
          email: "Criss@gmail.com",
          phoneNumber: "7771237683",
          status: "Activo",
        },
        {
          name: "José Miguel",
          lastName: "Aguario",
          middleName: "Díaz",
          email: "Mike@gmail.com",
          phoneNumber: "7778745609",
          status: "Inactivo",
        },
        {
          name: "Valeria Marianne ",
          lastName: "Santos",
          middleName: "Perez",
          email: "Marianne@gmail.com",
          phoneNumber: "7774567890",
          status: "Activo",
        },
      ],
    };
  },
  computed: {
    filteredUsers() {
      return this.users.filter((user) => {
        const nameMatch = user.name
          .toLowerCase()
          .includes(this.search.toLowerCase());
        const lastNameMatch = user.lastName
          .toLowerCase()
          .includes(this.search.toLowerCase());
        const middleNameMatch = user.middleName
          .toLowerCase()
          .includes(this.search.toLowerCase());
        const emailMatch = user.email
          .toLowerCase()
          .includes(this.search.toLowerCase());
        const phoneNumberMatch = user.phoneNumber
          .toLowerCase()
          .includes(this.search.toLowerCase());
        const statusMatch = user.status
          .toLowerCase()
          .includes(this.search.toLowerCase());

        return (
          nameMatch ||
          lastNameMatch ||
          middleNameMatch ||
          emailMatch ||
          phoneNumberMatch ||
          statusMatch
        );
      });
    },
  },
  methods: {
    editUser(user) {},
    deleteUser(user) {
    },
    showDeleteConfirmation(user) {
      Swal.fire({
        title: "¿Estás seguro?",
        text: "¿Estás seguro que desea modificar el status de este usuario?",
        icon: "warning",
        iconColor: "#FF0000",
        showCancelButton: true,
        showConfirmButton: true,
        confirmButtonText: "Modificar",
        cancelButtonText: "Cancelar",
      }).then((result) => {
        if (result.isConfirmed) {
          this.deleteUser(user); 
        }
      });
    },
  },
  components: {
    Encabezado,
  },
};
</script>

<style scoped>
.form-select {
  width: 100%;
}
.font-size-lg {
  font-size: 1.5rem;
}
</style>
