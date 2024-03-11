<template>
    <div>
        <div class="AnimalHeader">
            <Encabezado color="#E84E0F" :imagenUrl="require('../../../../assets/imgs/mascotas5.png')"
                titulo="Categorias de animales" />
        </div>
        <b-container fluid>
            <div class="mt-3">
                <b-row class="justify-content-end">
                    <b-col cols="12" sm="12" md="3" lg="2" xl="2" class="d-flex justify-content-end my-2">
                        <b-button as="col" cols="12" sm="12" md="1" pill class="py-1 w-100 d-flex align-items-center justify-content-center" variant="outline-danger">
                            Registrar <i class="material-icons ms-3">add_circle</i>
                        </b-button>

                    </b-col>
                    <b-col cols="12" sm="12" md="4 " lg="3" xl="3" class="my-2">
                        <b-form-select v-model="selected" as="col" cols="12" sm="12" md="2" lg="2" xl="2"
                            class="form-control selectCategories rounded-pill" :options="options"></b-form-select>
                    </b-col>
                    <b-col cols="12" sm="12" md="4 " lg="3" xl="3" class="my-2">
                        <b-input-group>
                            <b-form-input as id="searchInput" type="search" placeholder="Buscar..."
                                class="searchInput rounded-pill" v-model="search"></b-form-input>
                        </b-input-group>
                    </b-col>
                </b-row>
            </div>
            <div class="animalCategories mt-3">
                <b-row class="mx-3 justify-content-center">
                    <b-col class="mt-3" cols="12" sm="12" md="4 " lg="3" xl="3"
                        v-for="(animal, index) in paginatedAnimalsList" :key="index">
                        <b-card :title="animal.name" :img-src=animal.imaen img-alt="Image" img-top tag="article"
                            style="max-width: 20rem; border-top-left-radius: 2rem; border-top-right-radius: 2rem; border-bottom-left-radius: 1rem; border-bottom-right-radius: 1rem; " class="mb-2">
                            <b-card-body class="py-0 justify-content-center card-body-animals">
                                <b-row>
                                    <b-col class="my-2 mx-2 justify-content-center px-1" cols="12" sm="12" md="12" lg="12"
                                        xl="5">
                                        <b-button pill class="d-flex align-items-center justify-content-center btn-outline-dark-orange-secondary w-100 px-0" style="font-size: 0.8rem;">Modificar <i
                                                class="material-icons ms-2" style="font-size: 0.8rem;">border_color</i> </b-button>
                                    </b-col>
                                    <b-col class="my-2 mx-2 justify-content-center px-0" cols="12" sm="12" md="12" lg="12"
                                        xl="5">
                                        <b-button v-if="animal.status" pill variant="outline-success" class="d-flex align-items-center justify-content-center w-100 px-0" v-b-tooltip.hover title="Esta categoria es visible para los usuarios" style="font-size: 0.8rem;">Habilitada <i
                                                class="material-icons ms-2" style="font-size: 1rem;">done</i></b-button>
                                        <b-button v-else pill variant="outline-danger" style="font-size: 0.8rem;" class="d-flex align-items-center justify-content-center w-100 px-0" v-b-tooltip.hover title="Esta categoria no es visible para los usuarios">Deshabilitada <i
                                                class="material-icons ms-2" style="font-size: 1rem;">close</i></b-button>
                                    </b-col>
                                </b-row>
                            </b-card-body>
                        </b-card>
                    </b-col>
                </b-row>
                <div class="mt-3 d-flex justify-content-center">
                    <b-pagination pills v-model="currentPage" :total-rows="rows" :per-page="perPage"></b-pagination>
                </div>
            </div>
        </b-container>
    </div>
</template>

<script>
import Encabezado from '../../../../views/components/Encabezado.vue';
import gatos from "../../../../assets/imgs/gatoGrisCut.png"
import tortugas from "../../../../assets/imgs/tortugas1.jpeg"

export default {
    name: "AnimalCategories",
    components: {
        Encabezado,
    },
    data() {
        return {
            AnimalsList: [
                { id: 1, name: "tortugas", estado: "Activo", imaen: tortugas, "status": true },
                { id: 2, name: "tortugas", estado: "Activo", imaen: tortugas, "status": true },
                { id: 3, name: "tortugas", estado: "Activo", imaen: tortugas, "status": false },
                { id: 4, name: "tortugas", estado: "Activo", imaen: tortugas, "status": false },
                { id: 5, name: "tortugas", estado: "Activo", imaen: tortugas, "status": false },
                { id: 6, name: "tortugas", estado: "Activo", imaen: tortugas, "status": true },
                { id: 7, name: "tortugas", estado: "Activo", imaen: tortugas, "status": true },
                { id: 8, name: "tortugas", estado: "Activo", imaen: tortugas, "status": true },
                { id: 9, name: "tortuga", estado: "Activo", imaen: tortugas, "status": false },
                { id: 10, name: "tortuga", estado: "Activo", imaen: tortugas, "status": false },
                { id: 11, name: "tortuga", estado: "Activo", imaen: tortugas, "status": true },
                { id: 12, name: "tortuga", estado: "Activo", imaen: tortugas, "status": true },
                { id: 13, name: "tortuga", estado: "Activo", imaen: tortugas, "status": true },
                { id: 14, name: "tortuga", estado: "Activo", imaen: tortugas, "status": false },
                { id: 15, name: "tortuga", estado: "Activo", imaen: tortugas, "status": false },
                { id: 16, name: "tortuga", estado: "Activo", imaen: tortugas, "status": true },
            ],
            search: null,
            options: [
                { value: null, text: 'Todos' },
                { value: true, text: 'Activos' },
                { value: false, text: 'Inactivos' },
            ],
            selected: null,
            currentPage: 1,
            perPage: 8,
        }
    },
    computed: {
        rows() {
            return this.AnimalsList.length;
        },
        paginatedAnimalsList() {
            const startIndex = (this.currentPage - 1) * this.perPage;
            const endIndex = startIndex + this.perPage;
            return this.AnimalsList.slice(startIndex, endIndex);
        }
    }
}
</script>

<style></style>
