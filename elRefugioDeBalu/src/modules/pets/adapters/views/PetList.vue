<template>
    <b-container fluid>
        <b-row>
            <Encabezado color="#F2F2F2" :imagenUrl="require('@/assets/imgs/mascotas_pet_list.png')"
                titulo="Lista de mascotas" />
        </b-row>
        <b-row align-h="end" class="px-4">
            <b-col cols="12" sm="6" md="3" lg="2" class="pt-3">
                <b-input-group class="mt-3">
                    <b-form-select v-model="payload.category" class="form-select" @change="getPetList()">
                        <option value="">Todas las categorías</option>
                        <option v-for="category in categories" :key="category.id" :value="category.id">
                            {{ category.name }}
                        </option>
                    </b-form-select>
                </b-input-group>
            </b-col>
            <b-col cols="12" sm="6" md="3" lg="2" class="pt-0 pt-sm-3">
                <b-input-group class="mt-3">
                    <b-form-select v-model="payload.size" class="form-select" @change="getPetList()">
                        <option value="">Todas los tamaños</option>
                        <option v-for="size in sizes" :key="size.key" :value="size.value">
                            {{ size.text }}
                        </option>
                    </b-form-select>
                </b-input-group>
            </b-col>
            <b-col cols="12" sm="6" md="3" lg="2" class="pt-0 pt-md-3">
                <b-input-group class="mt-3">
                    <b-form-select v-model="payload.gender" class="form-select" @change="getPetList()">
                        <option value="">Todos los géneros</option>
                        <option v-for="gender in genders" :key="gender.key" :value="gender.value">
                            {{ gender.text }}
                        </option>
                    </b-form-select>
                </b-input-group>
            </b-col>
            <b-col cols="12" sm="6" md="3" class="pt-0 pt-md-3">
                <b-input-group class="mt-3">
                    <b-form-input type="text" placeholder="Buscar..." id="searchValue" v-model="payload.searchValue"
                        @keyup.enter="getPetList()"></b-form-input>
                    <b-button variant="dark-gray" type="button" id="searchValue" @click="getPetList()"
                        class="d-flex align-items-center justify-content-between">
                        <b-icon icon="search"></b-icon>
                    </b-button>
                </b-input-group>
            </b-col>
        </b-row>
        <b-row class="px-4 pt-4">
            <b-col cols="12">
                <b-table :fields="fields" :items="pets" label-sort-asc="" label-sort-desc="" no-sort-reset responsive
                    small striped hover class="text-center custom-scroll-style">
                    <template #cell(age)="data">
                        <span>{{ data.item.age }} {{ mapAgeUnit((data.item.ageUnit).toString().toLowerCase()) }}</span>
                    </template>
                    <template #cell(size)="data">
                        <span>{{ mapSize((data.item.size).toString().toLowerCase()) }}</span>
                    </template>
                    <template #cell(gender)="data">
                        <b-icon v-if="data.value === 'male'" icon="gender-male" variant="gender-male"
                            font-scale="1.4"></b-icon>
                        <b-icon v-else icon="gender-female" variant="gender-female" font-scale="1.4"></b-icon>
                    </template>
                    <template #cell(status)="data">
                        <b-badge variant="warning">{{ mapStatus((data.item.status).toString().toLowerCase()) }}</b-badge>
                    </template>
                    <template #cell(actions)>
                        <div class="d-none d-md-inline-block">
                            <b-button pill size="sm" variant="outline-dark-blue" class="px-3 d-flex align-items-center">
                                <span>Ver detalles</span>
                                <b-icon icon="info-circle" font-scale="1" class="ms-1"></b-icon>
                            </b-button>
                        </div>
                        <div class="d-inline-block d-md-none">
                            <b-button pill size="sm" variant="outline-dark-blue" class="px-2 d-flex align-items-center"
                                v-b-tooltip.hover.top="'Ver detalles'">
                                <b-icon icon="info-circle" font-scale="1"></b-icon>
                            </b-button>
                        </div>
                    </template>
                </b-table>
            </b-col>
        </b-row>
        <b-row class="px-4">
            <b-col cols="12" class="d-flex align-items-center">
                <label for="perPage">Selecciona la cantidad de registros que deseas mostrar:</label>
                <b-form-select :options="options" v-model="size" class="ms-3 my-3 form-select" style="width: 80px" @change="getPetList()"></b-form-select>
            </b-col>
            <b-col cols="12" class="text-center">
                <b-pagination pills v-model="page" :total-rows="total" :per-page="size" align="center">
                </b-pagination>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
import Swal from "sweetalert2";
import instance from "../../../../config/axios";

import Encabezado from "../../../../views/components/Encabezado.vue";
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import getSizes from "../../../../kernel/data/sizes";
import getGenders from "../../../../kernel/data/genders";
import { sizes, ageUnits, statusses } from "../../../../kernel/data/mappingDictionaries";

export default {
    data() {
        return {
            size: 5,
            page: 1,
            total: 0,
            genders: getGenders,
            sizes: getSizes,
            options: [5, 10, 20, 50],
            payload: {
                category: "",
                size: "",
                gender: "",
                searchValue: ""
            },
            categories: [],
            fields: [
                { key: 'category', label: 'Especie', sortable: false },
                { key: 'name', label: 'Nombre', sortable: true },
                { key: 'breed', label: 'Raza', sortable: true },
                { key: 'age', label: 'Edad', sortable: true },
                { key: 'size', label: 'Tamaño', sortable: false },
                { key: 'gender', label: 'Género', sortable: false },
                { key: 'status', label: 'Estado', sortable: false },
                { key: 'actions', label: 'Acciones', sortable: false }
            ],
            pets: []
        }
    },
    computed: {
    },
    methods: {
        async getCategories() {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Estamos cargando las categorías, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                const response = await instance.get(`/category/list`)
                this.categories = response.data.data
                Swal.close()
            } catch (error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al cargar las categorías',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    this.$router.push('/home')
                })
            }
        },
        async getPetList() {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Estamos cargando las nuevas publicaciones de mascotas, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                const response = await instance.post(`/pet/requests?page=${this.page - 1}&size=${this.size}`, {
                    category: this.payload.category,
                    size: this.payload.size,
                    gender: this.payload.gender,
                    searchValue: this.payload.searchValue
                })
                this.pets = response.data.data.content
                this.total = response.data.data.totalElements
                Swal.close()
            } catch (error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al cargar las nuevas publicaciones de mascotas',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    this.$router.push('/home')
                })
            }
        },
        mapSize(size) {
            return sizes[size] || size;
        },
        mapAgeUnit(ageUnit) {
            return ageUnits[ageUnit] || ageUnit;
        },
        mapStatus(status) {
            return statusses[status] || status;
        }
    },
    mounted() {
        this.getCategories()
        this.getPetList()
    },
    watch: {
        page(previous, next) {
            if (previous !== next) {
                this.getPetList();
            }
        }
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