<template>
  <div class="">
    <Encabezado color="#00737e" :imagenUrl="require('@/assets/imgs/perroblanco.png')"
      titulo="Lista de Administradores y Moderadores" />
    <b-container fluid>
      <b-row class="justify-content-center px-4">
        <b-col cols="12" md="8" class="pt-0 pt-md-3 text-center">
          <b-input-group class="mt-3">
            <b-form-input type="text" placeholder="Buscar..." v-model="searchValue" @keyup.enter="getUsersPaged"
              class="form-control-md border-right-0"></b-form-input>
            <b-input-group-append>
              <b-icon icon="searchValue" style="
                  background-color: transparent;
                  position: absolute;
                  right: 10px;
                  top: 50%;
                  transform: translateY(-50%);
                "></b-icon>
            </b-input-group-append>
          </b-input-group>
        </b-col>
        <b-col cols="12" md="4" class="pt-0 pt-md-3">
          <b-button variant="outline-success" class="mt-3 d-flex align-items-center justify-content-center"
            to='/usersForm'>
            <b-icon icon="person-plus-fill" font-scale="1"></b-icon> Agregar
          </b-button>
        </b-col>
      </b-row>
      <b-row class="px-4 pt-4">
        <b-col cols="12">
          <b-table :fields="fields" :items="users" :filter="searchValue" label-sort-asc="" label-sort-desc=""
            no-sort-reset :per-page="size" :current-page="page" responsive small striped hover
            class="text-center custom-scroll-style">
            <template #cell(actions)="data">
              <div class="d-flex justify-content-center">
                <b-button :variant="data.item.blocked ? 'outline-success' : 'outline-danger'"
                  class="d-flex align-items-center" @click="showChangeStatusConfirmation(data.item)">
                  <b-icon :icon="data.item.blocked ? 'unlock-fill' : 'lock-fill'"></b-icon>
                </b-button>
              </div>
            </template>

            <template #cell(blocked)="data">
              <b-badge :variant="data.item.blocked ? 'danger' : 'success'">
                {{ data.item.blocked ? "Bloqueado" : "Activo" }}
              </b-badge>
            </template>
            <template #cell(role)="data">
              <b-badge :variant="data.item.role === 'ADMIN' ? 'warning' : 'info'">
                {{ data.item.role === 'ADMIN' ? "Administrador" : "Moderador" }}
              </b-badge>
            </template>
          </b-table>
        </b-col>
      </b-row>
      <b-row class="pt-4">
        <b-col cols="12">
          <b-row class="justify-content-center">
            <b-col cols="6" md="4" class="pt-0 pt-md-3">
              <b-form-select :options="options" v-model="size" class="form-select"
                @change="getUsersPaged"></b-form-select>
            </b-col>
          </b-row>
          <b-pagination pills v-model="page" :total-rows="users.length" :per-page="size" align="center">
          </b-pagination>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import Swal from "sweetalert2";

import Encabezado from "../../../views/components/Encabezado.vue";
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import instance from "../../../config/axios";
import { decrypt } from "../../../kernel/hashFunctions";
export default {
  data() {
    return {
      searchValue: "",
      size: 10,
      page: 1,
      fields: [
        { key: "fullname", label: "Nombre completo" },
        { key: "username", label: "Correo Electrónico" },
        { key: "phone", label: "Teléfono" },
        { key: "blocked", label: "Estado" },
        { key: "role", label: "Rol" },
        { key: "actions", label: "Acciones" },
      ],
      users: [
        {
          id: null,
          fullname: "",
          username: "",
          phone: "",
          blocked: false,
        }
      ],
      personId: null,
      pageResponse: {},
      options: [1, 5, 10, 20, 50]
    };
  },
  mounted() {
    this.getUsersPaged();
  },
  methods: {
    async sendChangeStatusRequest() {
      try {
        Swal.fire({
          title: "Cargando...",
          text: "Estamos procesando tu solicitud, espera un momento",
          imageUrl: gatoWalkingGif,
          imageWidth: 160,
          imageHeight: 160,
          showConfirmButton: false,
        })
        await instance.patch('/person/change/status',{personId: this.personId})
        Swal.fire({
          title: "Éxito",
          text: "El estado del usuario ha sido modificado",
          icon: "success",
          iconColor: "#00FF00",
          timer: 2000,
          timerProgressBar: true,
        }).then(() => {
          this.getUsersPaged();  
        });
      } catch (error) {
        Swal.fire({
          title: "Error",
          text: "Ocurrió un error al cambiar el estado del usuario",
          icon: "error",
          iconColor: "#FF0000",
          showConfirmButton: true,
        });
      }

    },
    showChangeStatusConfirmation(person) {
      this.personId = person.id;
      Swal.fire({
        title: "¿Estás seguro?",
        text: person.blocked
          ? "Estás a punto de desbloquear a este usuario"
          : "Estás a punto de bloquear a este usuario",
        icon: "warning",
        showCancelButton: true,
        showConfirmButton: true,
        confirmButtonText: person.blocked
          ? "Desbloquear"
          : "Bloquear",
        cancelButtonText: "Cancelar",
      }).then((result) => {
        if (result.isConfirmed) {
          this.sendChangeStatusRequest();
        }
      });
    },
    async getUsersPaged() {
      try {
        Swal.fire({
          title: "Cargando...",
          text: "Estamos cargando los usuarios, espera un momento",
          imageUrl: gatoWalkingGif,
          imageWidth: 160,
          imageHeight: 160,
          showConfirmButton: false,
        })
        const response = await instance.post(`/person/paged/?page=${this.page - 1}&?size=${this.size}`
          , {
            searchValue: this.searchValue,
          })
        this.users = response.data.data.content
        this.pageResponse = response.data.data

        for await (const user of this.users) {
          user.username = await decrypt(user.username)
          user.phone = await decrypt(user.phone)
        }

        Swal.close()
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
    }
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
