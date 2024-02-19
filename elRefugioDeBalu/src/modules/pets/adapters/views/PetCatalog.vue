<template>
    <b-container fluid>
        <b-row>
            <b-col cols="12" class="p-4">
                <b-card bg-variant="orange" class="px-3">
                    <b-row>
                        <b-col cols="3" class="d-none d-lg-flex align-items-center">
                            <div>
                                <h2 class="text-dark-secondary-blue">¡ADOPTA CON NOSOTROS!</h2>
                                <span style="font-size: 1.4rem;">Encuentra al nuevo intregrante de tu familia</span>
                            </div>
                        </b-col>
                        <b-col cols="12" md="8" lg="6" class="d-flex align-items-center my-3">
                            <b-form>
                                <span>Quiero adoptar un...</span>
                                <b-row>
                                    <b-col cols="6" class="mt-3">
                                        <b-form-select v-model="category" :options="categories"
                                            class="form-select"></b-form-select>
                                    </b-col>
                                    <b-col cols="6" class="mt-3">
                                        <b-form-select v-model="size" :options="sizes" class="form-select"></b-form-select>
                                    </b-col>
                                    <b-col cols="6" class="mt-3">
                                        <b-form-select v-model="age" :options="ages" class="form-select"></b-form-select>
                                    </b-col>
                                    <b-col cols="6" class="mt-3">
                                        <b-form-select v-model="sex" :options="sexes" class="form-select"></b-form-select>
                                    </b-col>
                                    <b-col cols="6" class="mt-3">
                                        <b-button variant="outline-danger"
                                            class="w-100 d-flex align-items-center justify-content-between">
                                            <span>Limpiar</span>
                                            <b-icon icon="trash"></b-icon>
                                        </b-button>
                                    </b-col>
                                    <b-col cols="6" class="mt-3">
                                        <b-button variant="outline-dark-secondary-blue"
                                            class="w-100 d-flex align-items-center justify-content-between">
                                            <span>Buscar</span>
                                            <b-icon icon="search" flip-h></b-icon>
                                        </b-button>
                                    </b-col>
                                </b-row>
                            </b-form>
                        </b-col>
                        <b-col cols="4" lg="3" class="d-none d-md-flex justify-content-end">
                            <img src="@/assets/imgs/gatito_buscador.png" height="119%" />
                        </b-col>
                    </b-row>
                </b-card>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
export default {
    data() {
        return {
            category: null,
            size: null,
            age: null,
            sex: null,
            categories: [
                { value: null, text: 'Todas las especies' },
                { value: 'perro', text: 'Perro' },
                { value: 'gato', text: 'Gato' },
                { value: 'conejo', text: 'Conejo' },
                { value: 'hamster', text: 'Hamster' },
                { value: 'tortuga', text: 'Tortuga' }
            ],
            sizes: [
                { value: null, text: 'Todos los tamaños' },
                { value: 'pequeno', text: 'Pequeño' },
                { value: 'mediano', text: 'Mediano' },
                { value: 'grande', text: 'Grande' }
            ],
            ages: [
                { value: null, text: 'Todas las edades' },
                { value: 'cachorro', text: 'Cachorro' },
                { value: 'joven', text: 'Joven' },
                { value: 'adulto', text: 'Adulto' },
                { value: 'senior', text: 'Senior' }
            ],
            sexes: [
                { value: null, text: 'Cualquier sexo' },
                { value: 'macho', text: 'Macho' },
                { value: 'hembra', text: 'Hembra' }
            ]
        }
    }
}
</script>

<style scoped></style>