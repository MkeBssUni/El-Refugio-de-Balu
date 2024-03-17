<template>
  <b-card bg-variant="light"  class="px-2 px-sm-4 px-xl-5 my-4 mx-2 mb-sm-5">
    <b-container>
        <b-row>
            <b-col cols="12" sm="12" lg="4" md="4">
                <b-card bg-variant="card-content-secondary-orange" class="text-center"> 
                    <b-card-text>Datos de solicitud</b-card-text>
                </b-card>
                <b-card bg-variant="card-content-orange" class="text-center my-2"> 
                    <b-card-text>
                        <b>Fecha de solicitud:</b>
                        25 de febrero del 2024
                    </b-card-text>
                </b-card>
                <b-card bg-variant="light" class="text-center my-2"> 
                    <b-card-text>
                        <b>Estado:</b>
                        <br>
                        <b-badge variant="secondary">Finalizada</b-badge>
                    </b-card-text>
                    <b-card-text>
                        Mientras tanto, ¿has considerado adoptar una mascota? Hay muchos amigos peludos esperando un hogar amoroso.
                    </b-card-text>
                </b-card>
            </b-col>
            <b-col cols="12" sm="12" lg="8" md="8">
                <b-card> 
                    <b-row>
                        <b-col cols="12" sm="12" lg="4" md="4">
                            <img  src="https://es.mypet.com/wp-content/uploads/sites/23/2021/03/GettyImages-623368750-e1582816063521-1.jpg" alt="Imagen de perfil" class="img-fluid" />                                      
                        </b-col>
                        <b-col cols="12" sm="12" lg="8" md="8">
                            <b-card bg-variant="orange">
                                <h1>Sr.Bigotes</h1>
                                <b-card-text>
                                    <b-row>
                                    <b-col cols="12" sm="12" lg="3" md="3">
                                        <b>Especie:</b> Gato
                                    </b-col>
                                    <b-col cols="12" sm="12" lg="3" md="3">
                                        <b>Etapa:</b> Joven
                                    </b-col>
                                    <b-col cols="12" sm="12" lg="3" md="3">
                                        <b>Edad:</b> 8 meses
                                    </b-col>
                                    <b-col cols="12" sm="12" lg="3" md="3">
                                        <b>peso:</b> 3kg
                                    </b-col>
                                    </b-row>
                                </b-card-text>

                            </b-card>
                        </b-col>
                    </b-row>                    
                </b-card>
            </b-col>
           
        </b-row>
    </b-container>
  </b-card>
</template>

<script>

export default {
    name: "ViewAplication",

}
</script>

<style>

</style>