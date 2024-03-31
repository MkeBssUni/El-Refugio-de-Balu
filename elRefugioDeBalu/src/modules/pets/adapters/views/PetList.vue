<template>
    <b-container fluid>
        <b-row>
            <Encabezado color="#F2F2F2" :imagenUrl="require('@/assets/imgs/mascotas_pet_list.png')"
                titulo="Lista de mascotas" />
        </b-row>
        <b-row align-h="end" class="px-4">
            <b-col cols="12" sm="6" md="3" lg="2" class="pt-3">
                <b-input-group class="mt-3">
                    <b-form-select v-model="payload.category" class="form-select">
                        <option value="">Todas las categorías</option>
                        <option v-for="category in categories" :key="category.id" :value="category.id">
                            {{ category.name }}
                        </option>
                    </b-form-select>
                </b-input-group>
            </b-col>
            <b-col cols="12" sm="6" md="3" lg="2" class="pt-0 pt-sm-3">
                <b-input-group class="mt-3">
                    <b-form-select v-model="size" class="form-select">
                        <option value="">Todas los tamaños</option>
                        <option v-for="size in sizes" :key="size.key" :value="size.value">
                            {{ size.text }}
                        </option>
                    </b-form-select>
                </b-input-group>
            </b-col>
            <b-col cols="12" sm="6" md="3" lg="2" class="pt-0 pt-md-3">
                <b-input-group class="mt-3">
                    <b-form-select v-model="gender" class="form-select">
                        <option value="">Todos los géneros</option>
                        <option v-for="gender in genders" :key="gender.key" :value="gender.value">
                            {{ gender.text }}
                        </option>
                    </b-form-select>
                </b-input-group>
            </b-col>
            <b-col cols="12" sm="6" md="3" class="pt-0 pt-md-3">
                <b-input-group class="mt-3">
                    <b-form-input type="text" placeholder="Buscar..." id="search"></b-form-input>
                    <b-button variant="dark-gray" type="button" id="search"
                        class="d-flex align-items-center justify-content-between">
                        <b-icon icon="search"></b-icon>
                    </b-button>
                </b-input-group>
            </b-col>
        </b-row>
        <b-row class="px-4 pt-4">
            <b-col cols="12">
                <b-table :fields="fields" :items="filteredPets" :filter="search" label-sort-asc="" label-sort-desc=""
                    no-sort-reset :per-page="perPage" :current-page="currentPage" responsive small striped hover
                    class="text-center custom-scroll-style">
                    <template #cell(sex)="data">
                        <b-icon v-if="data.value === 'Macho'" icon="gender-male" variant="gender-male"
                            font-scale="1.4"></b-icon>
                        <b-icon v-else-if="data.value === 'Hembra'" icon="gender-female" variant="gender-female"
                            font-scale="1.4"></b-icon>
                        <b-icon v-else icon="" class="text-dark-gray" font-scale="1"></b-icon>
                    </template>
                    <template #cell(status)="data">
                        <b-badge :variant="getBadgeVariant(data.value)">{{ data.value }}</b-badge>
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
        <b-row class="pt-4">
            <b-col cols="12">
                <b-pagination pills v-model="currentPage" :total-rows="filteredPets.length" :per-page="perPage"
                    align="center">
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
import sizes from "../../../../kernel/data/sizes";
import genders from "../../../../kernel/data/genders";

export default {
    data() {
        return {
            size: "",
            gender: "",
            payload: {
                searchValue: "",
                category: ""
            },
            perPage: 5,
            currentPage: 1,
            genders: genders,
            sizes: sizes,
            categories: [],
            fields: [
                { key: 'category', label: 'Especie', sortable: false },
                { key: 'name', label: 'Nombre', sortable: true },
                { key: 'age', label: 'Edad', sortable: true },
                { key: 'size', label: 'Tamaño', sortable: false },
                { key: 'sex', label: 'Sexo', sortable: false },
                { key: 'status', label: 'Estado', sortable: false },
                { key: 'actions', label: 'Acciones', sortable: false }
            ],
            pets: [
                { category: 'Perro', name: 'Firulais', age: '3 años', size: 'Grande', sex: 'Macho', status: 'Aceptada' },
                { category: 'Gato', name: 'Michi', age: '2 años', size: 'Mediano', sex: 'Hembra', status: 'Rechazada' },
                { category: 'Perro', name: 'Rex', age: '1 año', size: 'Pequeño', sex: 'Macho', status: 'Pendiente' },
                { category: 'Gato', name: 'Luna', age: '4 años', size: 'Mediano', sex: 'Hembra', status: 'Aceptada' },
                { category: 'Perro', name: 'Buddy', age: '2 años', size: 'Grande', sex: 'Macho', status: 'Rechazada' },
                { category: 'Gato', name: 'Whiskers', age: '3 años', size: 'Pequeño', sex: 'Macho', status: 'Pendiente' },
                { category: 'Perro', name: 'Rocky', age: '5 años', size: 'Mediano', sex: 'Macho', status: 'Aceptada' },
                { category: 'Gato', name: 'Mia', age: '1 año', size: 'Pequeño', sex: 'Hembra', status: 'Rechazada' },
                { category: 'Perro', name: 'Max', age: '2 años', size: 'Grande', sex: 'Macho', status: 'Pendiente' },
                { category: 'Gato', name: 'Smokey', age: '4 años', size: 'Mediano', sex: 'Macho', status: 'Aceptada' }
            ]
        }
    },
    computed: {
        filteredPets() {
            return this.pets.filter(pet => {
                const categoryMatch = this.categoryFilter ? pet.category === this.categoryFilter : true;
                const sizeMatch = this.sizeFilter ? pet.size === this.sizeFilter : true;
                const genderMatch = this.genderFilter ? pet.sex === this.genderFilter : true;
                const statusMatch = this.statusFilter ? pet.status === this.statusFilter : true;

                return categoryMatch && sizeMatch && genderMatch && statusMatch;
            });
        }
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
        getBadgeVariant(status) {
            switch (status) {
                case 'Aceptada': return 'success';
                case 'Rechazada': return 'danger';
                case 'Pendiente': return 'warning';
                default: return 'secondary';
            }
        },
    },
    mounted() {
        this.getCategories()
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