<template>
    <b-container fluid>
        <b-row>
            <Encabezado color="#ffc876" :imagenUrl="require('@/assets/imgs/dog.png')"
                titulo="Lista de seguimiento de mascotas" />
        </b-row>
        <b-row align-h="end" class="px-4">
            <b-col cols="12" sm="6" md="4" lg="3" class="pt-0 pt-md-3">
                <b-input-group class="mt-3">
                    <b-form-select :options="categories" v-model="category" class="form-select"></b-form-select>
                </b-input-group>
            </b-col>
            <b-col cols="12" sm="6" md="4" lg="3" class="pt-0 pt-md-3">
                <b-input-group class="mt-3">
                    <b-form-select :options="statusses" v-model="status" class="form-select"></b-form-select>
                </b-input-group>
            </b-col>
            <b-col cols="12" md="4" lg="4" class="pt-0 pt-md-3">
                <b-input-group class="mt-3">
                    <b-form-input type="text" placeholder="Buscar..." id="searchValue"
                        v-model="searchValue"></b-form-input>
                    <b-button variant="dark-gray" type="button" id="searchValue"
                        class="d-flex align-items-center justify-content-between">
                        <b-icon icon="search"></b-icon>
                    </b-button>
                </b-input-group>
            </b-col>
        </b-row>
        <b-row class="px-4 pt-4">
            <b-col cols="12">
                <b-table :fields="fields" :items="pets" :filter="searchValue" label-sort-asc="" label-sort-desc=""
                    no-sort-reset :per-page="perPage" :current-page="currentPage" responsive small striped hover
                    class="text-center custom-scroll-style">
                    <template #cell(cancelRequest)="data">
                        <b-icon v-if="data.value" icon="exclamation-circle" variant="danger" font-scale="1.3"
                            v-b-tooltip.hover.right="'Solicitud de cancelación'"></b-icon>
                    </template>
                    <template #cell(requests)="data">
                        <b-badge v-if="data.value > 0" variant="warning">{{ data.value }}</b-badge>
                    </template>
                    <template #cell(status)="data">
                        <b-badge :variant="getBadgeVariant(data.value)">{{ data.value }}</b-badge>
                    </template>
                    <template #cell(actions)>
                        <div class="d-none d-lg-inline-block">
                            <b-button pill size="sm" variant="outline-dark-blue" class="px-3 d-flex align-items-center">
                                <span>Solicitudes de adopción</span>
                                <b-icon icon="file-earmark-text" font-scale="1" class="ms-1"></b-icon>
                            </b-button>
                        </div>
                        <div class="d-none d-lg-inline-block ms-0 ms-sm-2">
                            <b-button pill size="sm" variant="outline-dark-orange"
                                class="px-3 d-flex align-items-center">
                                <span>Comentarios</span>
                                <b-icon icon="chat-left-text" font-scale="1" class="ms-1"></b-icon>
                            </b-button>
                        </div>
                        <div class="d-inline-block d-lg-none">
                            <b-button pill size="sm" variant="outline-dark-blue" class="px-2 d-flex align-items-center"
                                v-b-tooltip.hover.left="'Solicitudes de adopción'">
                                <b-icon icon="file-earmark-text" font-scale="1"></b-icon>
                            </b-button>
                        </div>
                        <div class="d-inline-block d-lg-none ms-0 ms-sm-2">
                            <b-button pill size="sm" variant="outline-dark-orange"
                                class="px-2 d-flex align-items-center" v-b-tooltip.hover.left="'Comentarios'">
                                <b-icon icon="chat-left-text" font-scale="1"></b-icon>
                            </b-button>
                        </div>
                    </template>
                </b-table>
            </b-col>
        </b-row>
        <b-row class="pt-4">
            <b-col cols="12">
                <b-pagination pills v-model="currentPage" :total-rows="pets.length" :per-page="perPage"
                    align="center">
                </b-pagination>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
import Encabezado from "../../../../views/components/Encabezado.vue";
export default {
    data() {
        return {
            searchValue: '',
            category: '',
            status: '',
            perPage: 5,
            currentPage: 1,
            categories: [
                { value: '', text: 'Todas las categorías' },
                { value: 'Perro', text: 'Perro' },
                { value: 'Gato', text: 'Gato' },
                { value: 'Conejo', text: 'Conejo' },
                { value: 'Pájaro', text: 'Pájaro' }
            ],
            statusses: [
                { value: '', text: 'Todos los estados' },
                { value: 'Aceptada', text: 'Aceptada' },
                { value: 'Rechazada', text: 'Rechazada' },
                { value: 'Pendiente', text: 'Pendiente' }
            ],
            fields: [
                { key: 'cancelRequest', label: '' },
                { key: 'requests', label: 'Solicitudes' },
                { key: 'category', label: 'Categoría', sortable: true },
                { key: 'name', label: 'Nombre', sortable: true },
                { key: 'status', label: 'Estado' },
                { key: 'actions', label: 'Acciones' }
            ],
            pets: [
                { cancelRequest: false, category: 'Perro', name: 'Firulais', status: 'Aceptada', requests: 1 },
                { cancelRequest: true, category: 'Gato', name: 'Michi', status: 'Rechazada', requests: 0 },
                { cancelRequest: false, category: 'Conejo', name: 'Bugs Bunny', status: 'Pendiente', requests: 1 },
                { cancelRequest: false, category: 'Pájaro', name: 'Piolín', status: 'Aceptada', requests: 5 }
            ]
        }
    },
    computed: {

    },
    methods: {
        getBadgeVariant(status) {
            switch (status) {
                case 'Aceptada': return 'success';
                case 'Rechazada': return 'danger';
                case 'Pendiente': return 'warning';
                default: return 'secondary';
            }
        },
    },
    components: {
        Encabezado
    },
}
</script>

<style scoped>
.form-select {
    width: 100%;
}
</style>