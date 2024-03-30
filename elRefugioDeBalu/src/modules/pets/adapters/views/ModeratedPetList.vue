<template>
    <b-container fluid>
        <b-row>
            <Encabezado color="#ffc876" :imagenUrl="require('@/assets/imgs/dog.png')"
                titulo="Lista de seguimiento de mascotas" />
        </b-row>
    </b-container>
</template>

<script>
import Encabezado from "../../../../views/components/Encabezado.vue";
export default {
    data() {
        return {
            
        }
    },
    computed: {
        
    },
    methods: {
        
    },
    components: {
        Encabezado
    },
}
</script>

<style scoped></style>