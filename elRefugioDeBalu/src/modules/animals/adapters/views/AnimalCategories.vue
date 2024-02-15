<template>
    <div>
        <div class="AnimalHeader">
            <Encabezado color="#E84E0F" :imagenUrl="require('../../../../assets/imgs/mascotas5.png')" titulo="Categorias de animales" />
        </div>
        <b-container fluid>
            <div>
                <b-row>
                    <b-col> <b-button pill variant="outline-danger">Button<i class="material-icons">pets</i> </b-button> </b-col>
                    <b-col></b-col>
                    <b-col></b-col>
                </b-row>
            </div>
            <div class="animalCategories mt-5">
                <b-row class="mx-3"> 
                    <b-col class="mt-5" cols="12" sm="12" md="4 " lg="3" xl="3" v-for="animal in AnimalsList" v-bind:key="animal.id" >
                        <b-card :title="animal.name" :img-src=animal.imaen img-alt="Image" img-top
                            tag="article" style="max-width: 20rem;" class="mb-2">
                        </b-card>
                    </b-col>
                </b-row>
            </div>

        </b-container>
    </div>
</template>
  
<script>
import Encabezado from '../../../../views/components/Encabezado.vue';
import gatos from "../../../../assets/imgs/gatoGrisCut.png"

export default {
    name: "AnimalCategories",
    components: {
        Encabezado,
    },
    data(){
        return{
            AnimalsList:[
                {id:1,name:"Gatos",estado: "Activo", imaen: gatos},
                {id:2,name:"Gatos",estado: "Activo", imaen: gatos},
                {id:3,name:"Gatos",estado: "Activo", imaen: gatos},
                {id:4,name:"Gatos",estado: "Activo", imaen: gatos},
                {id:5,name:"Gatos",estado: "Activo", imaen: gatos},
                {id:6,name:"Gatos",estado: "Activo", imaen: gatos},
                {id:7,name:"Gatos",estado: "Activo", imaen: gatos},
            ]
        }
    }
}
</script>

<style></style>
  