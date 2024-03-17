<template>
    <b-container fluid class="px-5">
        <b-row class="mt-5 d-flex align-items-center">
            <b-col cols="12" md="6" lg="5"
                class="d-flex align-items-center justify-content-center justify-content-md-start">
                <span class="custom-h1">Mis publicaciones de mascotas</span>
            </b-col>
            <b-col cols="12" md="6" lg="7" class="mt-3 mt-md-0">
                <b-row class="d-flex justify-content-center">
                    <b-col cols="12" sm="6" md="12" lg="5">
                        <b-input-group>
                            <b-form-input type="text" placeholder="Buscar..." id="search"></b-form-input>
                            <b-button variant="dark-gray" type="button" id="search"
                                class="d-flex align-items-center justify-content-between">
                                <b-icon icon="search"></b-icon>
                            </b-button>
                        </b-input-group>
                    </b-col>
                    <b-col cols="12" sm="6" md="12" lg="4" class="mt-3 mt-sm-0 mt-md-3 mt-lg-0">
                        <b-input-group>
                            <b-form-select :options="status" v-model="statusFilter" class="form-select"></b-form-select>
                        </b-input-group>
                    </b-col>
                    <b-col cols="12" sm="6" md="12" lg="3" class="d-flex justify-content-center mt-3 mt-lg-0">
                        <b-button variant="outline-success w-100"
                            class="d-flex align-items-center justify-content-between">
                            <span class="mr-2">Agregar</span>
                            <b-icon icon="plus-circle" class="ms-2"></b-icon>
                        </b-button>
                    </b-col>
                </b-row>
            </b-col>
        </b-row>
        <hr class="mt-4">
        <b-row class="mb-5">
            <b-col v-for="pet in pets" :key="pet.id" cols="12" sm="6" lg="4" xl="3" class="mt-3">
                <b-card :key="pet.id" :img-src="pet.img" class="pet-card" no-body>
                    <b-row v-if="pet.comments > 0" class="transparent absolute-position">
                        <b-col cols="12" class="d-flex justify-content-end">
                            <b-button class="py-2 d-flex align-items-center relative-position custom-transparent-button"
                                pill v-b-tooltip.hover.left="'Ver comentarios'">
                                <b-icon icon="chat-left-fill" font-scale="3"
                                    class="text-dark-secondary-orange chat-icon mt-2"></b-icon>
                                <span class="text-light comments">
                                    {{ (pet.comments > 9) ? '9+' : pet.comments }}
                                </span>
                            </b-button>
                        </b-col>
                    </b-row>
                    <b-card-body class="text-center">
                        <b-card-title>{{ pet.name }}</b-card-title>
                        <b-card-sub-title>{{ pet.city }}, {{ pet.state }}</b-card-sub-title>
                        <b-badge :variant="getBadgeVariant(pet.status)" class="mt-2">{{ pet.status }}</b-badge>
                        <div class="d-flex justify-content-center">
                            <b-button pill variant="outline-dark-blue"
                                class="mt-3 px-5 d-flex align-items-center justify-content-center">
                                <span>Ver detalles</span>
                            </b-button>
                        </div>
                    </b-card-body>
                </b-card>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
export default {
    data() {
        return {
            statusFilter: '',
            status: [
                { value: '', text: 'Todos los estados' },
                { value: 'Aceptada', text: 'Aceptada' },
                { value: 'Rechazada', text: 'Rechazada' },
                { value: 'Pendiente', text: 'Pendiente' }
            ],
            pets: [
                {
                    id: 1, name: 'Luna', status: 'Aceptada', state: 'Morelos', city: 'Cuernavaca', comments: 0,
                    img: 'data:image/jpeg;base64,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'
                },
                {
                    id: 2, name: 'Balu', status: 'Rechazada', state: 'Morelos', city: 'Cuernavaca', comments: 3,
                    img: 'https://cdn.unotv.com/images/2023/11/01-perro-cachorrito-153446-1024x576.jpg'
                },
                {
                    id: 3, name: 'Coco', status: 'Pendiente', state: 'Morelos', city: 'Cuernavaca', comments: 90,
                    img: 'https://hips.hearstapps.com/hmg-prod/images/gettyimages-999148130-6553e6d45a7b8.jpg?crop=1xw:0.84375xh;center,top&resize=1200:*'
                },
                {
                    id: 4, name: 'Bolis', status: 'Aceptada', state: 'Morelos', city: 'Cuernavaca', comments: 2,
                    img: 'https://t2.ea.ltmcdn.com/es/posts/9/6/0/3_munchkin_23069_2_600.jpg'
                }
            ]
        }
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
    }
}
</script>
<style scoped>
.chat-icon {
    position: relative;
    z-index: 1;
}

.comments {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 2;
    font-size: 1.5em;

}

.custom-transparent-button {
    background-color: transparent !important;
    width: 100%;
}

.custom-transparent-button:hover .chat-icon {
    color: #ff7d4a !important;
}
</style>