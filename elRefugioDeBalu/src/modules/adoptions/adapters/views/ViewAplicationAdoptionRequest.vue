<template>
  <div>
    <b-card bg-variant="light" class="mx-3 my-3 box-shadow-pretty">
      <b-row>
        <b-col cols="12" sm="12" lg="4" md="4">
          <b-card
            bg-variant="card-content-secondary-orange"
            class="text-center box-shadow-pretty"
          >
            <b-card-text>Datos de solicitud</b-card-text>
          </b-card>
          <b-card
            bg-variant="card-content-orange"
            class="text-center my-1 box-shadow-pretty"
          >
            <b-card-text>
              <b>Fecha de solicitud:</b>
              {{ getDate(requestAdoption.createdAt) }}
            </b-card-text>
          </b-card>
          <b-card bg-variant="light" class="text-center my-1 box-shadow-pretty">
            <b-card-text>
              <b>Estado:</b>
              <br />
              <b-badge
                v-if="requestAdoption.status"
                :variant="getBadgeVariant(requestAdoption.status.name)"
                >{{ getStatus(requestAdoption.status.name) }}</b-badge
              >
              <br />
              {{ infoStatus }}
            </b-card-text>
          </b-card>
        </b-col>
        <b-col cols="12" sm="12" lg="8" md="8">
          <b-card class="target">
            <b-row>
              <b-col cols="12" sm="12" lg="5" md="5">
                <img
                  src="https://es.mypet.com/wp-content/uploads/sites/23/2021/03/GettyImages-623368750-e1582816063521-1.jpg"
                  alt="Imagen de perfil"
                  class="image-pet"
                />
              </b-col>
              <b-col cols="12" sm="12" lg="7" md="7">
                <b-card
                  bg-variant="card-content-orange"
                  class="my-2 information-pet"
                >
                  <b-card-body>
                    <b-card-title> Sr. Bigotes </b-card-title>
                    <hr class="my-line" />
                    <b-row>
                      <b-col cols="12" sm="12" lg="8" md="8" xl="8">
                        <b-card-text>
                          <b>Raza:</b> Neva Masquerade
                        </b-card-text>
                      </b-col>
                    </b-row>
                    <b-row>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text> <b>Especie:</b> Gato </b-card-text>
                      </b-col>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text> <b>Peso:</b> 3kg </b-card-text>
                      </b-col>
                    </b-row>
                    <b-row>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text> <b>Tamaño:</b> Mediano </b-card-text>
                      </b-col>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text> <b>Etapa:</b> Joven </b-card-text>
                      </b-col>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text> <b>Edad:</b> 8 meses </b-card-text>
                      </b-col>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text> <b>Género:</b> Macho </b-card-text>
                      </b-col>
                    </b-row>
                  </b-card-body>
                </b-card>
              </b-col>
            </b-row>
          </b-card>
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="12" sm="12" lg="4" md="4">
          <b-card bg-variant="light" class="box-shadow-pretty text-center">
            <b-card-title>Lugar de residencia</b-card-title>
            <b-card-body>
              <b-card-text
                >Lugar en la que dormira o descansara la mascota</b-card-text
              >
              <img
                src="https://t2.ea.ltmcdn.com/es/posts/0/9/4/como_hacer_una_casa_para_perros_20490_orig.jpg"
                class="homePhotos"
              />
            </b-card-body>
            <b-card-body>
              <b-card-text
                >Lugar en el que vivira la mascota (casa)</b-card-text
              >
              <img
                src="https://t2.ea.ltmcdn.com/es/posts/0/9/4/como_hacer_una_casa_para_perros_20490_orig.jpg"
                class="homePhotos"
              />
            </b-card-body>
            <b-card-body>
              <b-card-text
                >Lugar en el que jugará la mascota (casa)</b-card-text
              >
              <img
                src="https://t2.ea.ltmcdn.com/es/posts/0/9/4/como_hacer_una_casa_para_perros_20490_orig.jpg"
                class="homePhotos"
              />
            </b-card-body>
          </b-card>
        </b-col>
        <b-col cols="12" sm="12" lg="8" md="8">
          <b-card bg-variant="light" class="my-3 box-shadow-pretty">
            <b-card-title class="text-center">Motivos de adopción</b-card-title>
            <b-row>
              <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                <b-card-text>
                  <b>¿Tienes o has tenido otros animales de compañia?</b>
                  {{ requestAdoption.reasons_for_adoption.haveHadPets }}
                </b-card-text>
              </b-col>
              <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                <b-card-text>
                  <b>¿En que parte de la casa lo planeas tener?</b>
                  {{ requestAdoption.reasons_for_adoption.whereWillThePetBe }}
                </b-card-text>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                <b-card-text>
                  <b
                    >¿Las personas con quien vives están de acuerdo en
                    adoptar?</b
                  >
                  {{ requestAdoption.reasons_for_adoption.peopleAgreeToAdopt }}
                </b-card-text>
              </b-col>
              <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                <b-card-text>
                  <b>Comentarios adicionales</b>
                  {{ requestAdoption.reasons_for_adoption.additionalComments ?  requestAdoption.reasons_for_adoption.additionalComments:"No se registraron comentarios adicionales" }}
                </b-card-text>
              </b-col>
            </b-row>
          </b-card>
          <b-card bg-variant="light" class="my-3 box-shadow-pretty">
            <b-card-title class="text-center"
              >Experiencias previas</b-card-title
            >
            <b-row>
              <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                <b-card-text>
                  <b>¿Cuál fue tu ultima mascota?</b>
                  {{ requestAdoption.previous_experience.lastPet }}
                </b-card-text>
              </b-col>
              <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                <b-card-text>
                  <b>¿Cuando se enfermaba la mascota que hacias?</b>
                  {{ requestAdoption.previous_experience.whatDidYouDoWhenThePetGotSick }}
                </b-card-text>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                <b-card-text>
                  <b>¿Qué tipo de mascotas haz tenido antes?</b>
                 {{ requestAdoption.previous_experience.whatKindOfPetsHaveYouHadBefore }}
                </b-card-text>
              </b-col>
              <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                <b-card-text>
                  <b>¿Qué recuerdos tienes con tu mascota?</b>
                  {{ requestAdoption.previous_experience.whatMemoriesDoYouHaveWithYourPet ? requestAdoption.previous_experience.whatMemoriesDoYouHaveWithYourPet:"No se registraron recuerdos"}}
                </b-card-text>
              </b-col>
            </b-row>
          </b-card>
          <b-card bg-variant="light" class="my-3 box-shadow-pretty">
            <b-card-title class="text-center"
              >Información adicional</b-card-title
            >
            <b-row>
              <b-col cols="12" sm="12" lg="12" md="12" xl="12">
                <b-card-text>
                  {{ requestAdoption.additional_information ? requestAdoption.additional_information:"No se registró información adicional" }}
                </b-card-text>
              </b-col>
            </b-row>
          </b-card>
        </b-col>
      </b-row>
      <div class="container-fluid d-flex justify-content-end">
        <b-button
          type="submit"
          variant="outline-dark-secondary-blue pill"
          class="mt-3  mx-4"
          @click="goBack"
        >
          Regresar
        </b-button>
        <b-button
          type="submit"
          variant="outline-danger pills"
          class="mt-3"
        >
          Cancelar solicitud
        </b-button>
      </div>
    </b-card>
  </div>
</template>

<script>
import Swal from "sweetalert2";
import perroChato from "@/assets/imgs/perroChato1.gif";
import instance from "../../../../config/axios";
import { decrypt } from "../../../../kernel/hashFunctions";

export default {
  name: "viewAplicationAdoptionRequest",
  data() {
    return {
      requestAdoption: {},
      infoStatus: "",
    };
  },
  mounted() {
    this.getAdoption();
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    getDate(date) {
      const formattedDate = new Date(date);
      const options = { year: "numeric", month: "long", day: "2-digit" };
      return formattedDate.toLocaleDateString("es-ES", options);
    },
    getBadgeVariant(status) {
      switch (status) {
        case "ADOPTED":
          return "primary";
        case "CLOSED":
          return "danger";
        case "PENDING":
          return "warning";
        default:
          return "primary";
      }
    },
    getStatus(status) {
      switch (status) {
        case "ADOPTED":
          this.infoStatus =
            "¡Felicidades! Has adoptado a un amigo peludo. ¡Gracias por darle un hogar amoroso!";
          return "Adoptado";
        case "CLOSED":
          this.infoStatus =
            "Mientras tanto, ¿has considerado adoptar una mascota? Hay muchos amigos peludos esperando un hogar amoroso.";
          return "Cerrado";
        case "PENDING":
          this.infoStatus =
            "Estamos revisando tu información. Por favor, ten paciencia mientras procesamos tu solicitud";
          return "Pendiente";
        default:
          return "SIN DATOS";
      }
    },
    async getAdoption() {
      try {
        Swal.fire({
          title: "Cargando...",
          text: "Estamos cargando la solicitud, espera un momento",
          imageUrl: perroChato,
          imageWidth: 160,
          imageHeight: 160,
          showConfirmButton: false,
        });
        const response = await instance.post("/adoption/getAdoption", {
          idAdoption: localStorage.getItem("adoptionId"),
        });
        this.requestAdoption = response.data.data;
        this.requestAdoption.previous_experience = JSON.parse(
          this.requestAdoption.previous_experience
        );
        this.requestAdoption.previous_experience.lastPet = await decrypt(
          this.requestAdoption.previous_experience.lastPet
        );
        this.requestAdoption.previous_experience.whatDidYouDoWhenThePetGotSick =
          await decrypt(
            this.requestAdoption.previous_experience
              .whatDidYouDoWhenThePetGotSick
          );
        this.requestAdoption.previous_experience.whatKindOfPetsHaveYouHadBefore =
          await decrypt(
            this.requestAdoption.previous_experience
              .whatKindOfPetsHaveYouHadBefore
          );
        this.requestAdoption.previous_experience.whatMemoriesDoYouHaveWithYourPet =
          await decrypt(
            this.requestAdoption.previous_experience
              .whatMemoriesDoYouHaveWithYourPet
          );

        this.requestAdoption.reasons_for_adoption = JSON.parse(
          this.requestAdoption.reasons_for_adoption
        );
        this.requestAdoption.reasons_for_adoption.haveHadPets = await decrypt(
          this.requestAdoption.reasons_for_adoption.haveHadPets
        );
        this.requestAdoption.reasons_for_adoption.peopleAgreeToAdopt =
          await decrypt(
            this.requestAdoption.reasons_for_adoption.peopleAgreeToAdopt
          );
        this.requestAdoption.reasons_for_adoption.whereWillThePetBe =
          await decrypt(
            this.requestAdoption.reasons_for_adoption.whereWillThePetBe
          );
        this.requestAdoption.reasons_for_adoption.additionalComments =
          await decrypt(
            this.requestAdoption.reasons_for_adoption.additionalComments
          );
        this.requestAdoption.additional_information = await decrypt(
          this.requestAdoption.additional_information
        );
        console.log(this.requestAdoption);

        Swal.close();
      } catch (error) {
        Swal.fire({
          title: "Error",
          text: "Ocurrió un error al cargar la solicitud",
          icon: "error",
          iconColor: "#FF0000",
          showConfirmButton: true,
        }).then((result) => {
          if (result.isConfirmed) {
            this.$router.push("/home");
          }
        });
      }
    },
  },
};
</script>

<style scoped>
.target {
  background-image: url("../../../../assets/imgs/pawBackground.jpg") !important;
  background-size: cover;
  background-position: center;
  box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px,
    rgba(17, 17, 26, 0.05) 0px 8px 32px;
}

.image-pet {
  width: 100%;
  height: 100%;
  border-radius: 15px;
  box-shadow: rgba(0, 0, 0, 0.16) 0px 3px 6px, rgba(0, 0, 0, 0.23) 0px 3px 6px;
  transition: transform 0.3s ease; /* Agrega una transición suave para el efecto de zoom */
}

/* Cuando el mouse pasa sobre la imagen */
.image-pet:hover {
  transform: scale(1.1); /* Aumenta el tamaño de la imagen al 110% */
}

.information-pet {
  box-shadow: rgba(0, 0, 0, 0.16) 0px 3px 6px, rgba(0, 0, 0, 0.23) 0px 3px 6px;
  transition: transform 0.3s ease; /* Agrega una transición suave para el efecto de zoom */
}

.information-pet:hover {
  transform: scale(1.1); /* Aumenta el tamaño de la imagen al 110% */
}

.box-shadow-pretty {
  box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px,
    rgba(17, 17, 26, 0.05) 0px 8px 32px;
}

.my-line {
  border: none;
  height: 2px;
  background-color: black;
}

.homePhotos {
  width: 80%;
  height: 100%;
  border-radius: 15px;
  box-shadow: rgba(0, 0, 0, 0.16) 0px 3px 6px, rgba(0, 0, 0, 0.23) 0px 3px 6px;
  transition: transform 0.3s ease; /* Agrega una transición suave para el efecto de zoom */
}

/* Cuando el mouse pasa sobre la imagen */
.homePhotos:hover {
  transform: scale(1.1); /* Aumenta el tamaño de la imagen al 110% */
}


</style>
