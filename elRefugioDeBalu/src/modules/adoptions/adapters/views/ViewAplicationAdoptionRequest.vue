<template>
  <div>
    <b-card bg-variant="light" class="mx-3 my-3 box-shadow-pretty">
      <b-row>
        <b-col cols="12" sm="12" lg="4" md="4">
          <b-card
            bg-variant="card-content-secondary-orange"
            class="text-center box-shadow-pretty"
            v-if="credentialPet"
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
                v-if="requestAdoption.status && requestAdoption.status.name"
                :variant="getBadgeVariant(requestAdoption.status.name)"
                >{{ getStatus(requestAdoption.status.name) }}</b-badge
              >

              <br />
              {{ infoStatus }}
            </b-card-text>
          </b-card>
          <b-card bg-variant="light" class="box-shadow-pretty text-center">
            <b-card-title>Lugar de residencia</b-card-title>
            <b-card-body>
              <b-card-text
                >Lugar en la que dormira o descansara la mascota</b-card-text
              >
              <img
                :src="getRequestImage(requestAdoption.requestImages, 0)"
                class="homePhotos"
                alt="resting place of the pet"
              />
            </b-card-body>
            <b-card-body>
              <b-card-text
                >Lugar en el que vivira la mascota (casa)</b-card-text
              >
              <img
                :src="getRequestImage(requestAdoption.requestImages, 1)"
                class="homePhotos"
                alt="place where the pet will live"
              />
            </b-card-body>
            <b-card-body>
              <b-card-text
                >Lugar en el que jugará la mascota (casa)</b-card-text
              >
              <img
                :src="getRequestImage(requestAdoption.requestImages, 2)"
                class="homePhotos"
                alt="place where the pet will play"
              />
            </b-card-body>
          </b-card>
        </b-col>
        <b-col cols="12" sm="12" lg="8" md="8">
          <b-card class="target">
            <b-row>
              <b-col cols="12" sm="12" lg="5" md="5">
                <img
                  :src="credentialPet.image"
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
                    <b-card-title>
                      {{
                        credentialPet.name ? credentialPet.name : "Sin nombre"
                      }}
                    </b-card-title>
                    <hr class="my-line" />
                    <b-row>
                      <b-col cols="12" sm="12" lg="8" md="8" xl="8">
                        <b-card-text>
                          <b>Raza:</b> &nbsp;{{ credentialPet.breed }}
                        </b-card-text>
                      </b-col>
                    </b-row>
                    <b-row>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text>
                          <b>Especie:</b>&nbsp; {{ credentialPet.category }}
                        </b-card-text>
                      </b-col>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text>
                          <b>Peso:</b>&nbsp;
                          {{ getAgeNumber(credentialPet.weight) }}
                          {{ mapweightUnits(getAgeUnit(credentialPet.weight)) }}
                        </b-card-text>
                      </b-col>
                    </b-row>
                    <b-row>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text>
                          <b>Tamaño:</b>&nbsp;
                          {{
                            mapSize(credentialPet.size.toString().toLowerCase())
                          }}
                        </b-card-text>
                      </b-col>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text>
                          <b>Etapa:</b> &nbsp;
                          {{
                            maplifeStages(
                              credentialPet.lifeStage.toString().toLowerCase()
                            )
                          }}
                        </b-card-text>
                      </b-col>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text>
                          <b>Edad:</b>&nbsp;{{
                            getAgeNumber(credentialPet.age)
                          }}
                          {{
                            mapageUnits(getAgeUnit(credentialPet.age))
                          }}</b-card-text
                        >
                      </b-col>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text>
                          <b>Género:</b>&nbsp;
                          {{
                            mapGender(
                              credentialPet.gender.toString().toLowerCase()
                            )
                          }}
                        </b-card-text>
                      </b-col>
                    </b-row>
                  </b-card-body>
                </b-card>
              </b-col>
            </b-row>
          </b-card>
          <b-card bg-variant="light" class="my-3 box-shadow-pretty">
            <b-card-title class="text-center">Motivos de adopción</b-card-title>
            <b-row>
              <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                <b-card-text>
                  <b>¿Tienes o has tenido otros animales de compañia?</b>
                  {{ requestAdoption.reasonsForAdoption.haveHadPets }}
                </b-card-text>
              </b-col>
              <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                <b-card-text>
                  <b>¿En que parte de la casa lo planeas tener?</b>
                  {{ requestAdoption.reasonsForAdoption.whereWillThePetBe }}
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
                  {{ requestAdoption.reasonsForAdoption.peopleAgreeToAdopt }}
                </b-card-text>
              </b-col>
              <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                <b-card-text>
                  <b>¿Por qué deseas adoptar esta mascota?</b>
                  {{
                    requestAdoption.reasonsForAdoption.whyAdoptPet
                      ? requestAdoption.reasonsForAdoption.whyAdoptPet
                      : "No se registraron motivos de adopción"
                  }}
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
                  <b>¿Cuál fue tu última mascota?</b>
                  {{
                    requestAdoption.previousExperience &&
                    requestAdoption.previousExperience.lastPet
                      ? requestAdoption.previousExperience.lastPet
                      : "No se registró una mascota anterior"
                  }}
                </b-card-text>
              </b-col>
              <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                <b-card-text>
                  <b>¿Cuando se enfermaba la mascota que hacias?</b>
                  {{
                    requestAdoption.previousExperience
                      .whatDidYouDoWhenThePetGotSick
                      ? requestAdoption.previousExperience
                          .whatDidYouDoWhenThePetGotSick
                      : "No se registraron acciones"
                  }}
                </b-card-text>
              </b-col>
            </b-row>
            <b-row>
              <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                <b-card-text>
                  <b>¿Qué tipo de mascotas haz tenido antes?</b>
                  {{
                    requestAdoption.previousExperience
                      .whatKindOfPetsHaveYouHadBefore
                  }}
                </b-card-text>
              </b-col>
              <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                <b-card-text>
                  <b>¿Qué recuerdos tienes con tu mascota?</b>
                  {{
                    requestAdoption.previousExperience
                      .whatMemoriesDoYouHaveWithYourPet
                      ? requestAdoption.previousExperience
                          .whatMemoriesDoYouHaveWithYourPet
                      : "No se registraron recuerdos"
                  }}
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
                  {{
                    requestAdoption.additionalInformation
                      ? requestAdoption.additionalInformation
                      : "No se registró información adicional"
                  }}
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
          class="mt-3 mx-4"
          @click="goBack"
        >
          <b-icon icon="box-arrow-left"></b-icon> &nbsp; Regresar
        </b-button>
        <b-button
          type="submit"
          variant="outline-danger"
          class="mt-3"
          @click="closedRequestAdoption"
          :disabled="
            requestAdoption.status && requestAdoption.status.name !== 'PENDING'
          "
        >
          <b-icon icon="clipboard-x"></b-icon>
          &nbsp;Cancelar solicitud
        </b-button>
      </div>
    </b-card>
  </div>
</template>

<script>
import Swal from "sweetalert2";
import perroChato from "@/assets/imgs/perroChato1.gif";
import instance from "../../../../config/axios";
import { decrypt, encrypt } from "../../../../kernel/hashFunctions";
import {
  sizes,
  weightUnits,
  lifeStages,
  ageUnits,
  gender,
} from "../../../../kernel/data/mappingDictionaries";

export default {
  name: "viewAplicationAdoptionRequest",
  data() {
    return {
      requestAdoption: {
        user: "",
        pet: "",
        reasonsForAdoption: {
          peopleAgreeToAdopt: "",
          haveHadPets: "",
          whereWillThePetBe: "",
          whyAdoptPet: "",
        },
        previousExperience: {
          lastPet: "",
          whatDidYouDoWhenThePetGotSick: "",
          whatKindOfPetsHaveYouHadBefore: "",
          whatMemoriesDoYouHaveWithYourPet: "",
        },
        additional_information: "",
        imageAdoption: [],
      },

      infoStatus: "",
      credentialPet: {
        name: "",
        age: "",
        breed: "",
        category: "",
        gender: "",
        image: "",
        lifeStage: "",
        size: "",
        weight: "",
      },
    };
  },
  mounted() {
    this.getAdoption();
  },
  methods: {
    getRequestImage(images, index) {
      if (images?.[index]) {
        return images[index].image;
      } else {
        return "";
      }
    },
    getAgeUnit(age) {
      if (age === undefined || age === null || age === "") {
        return "";
      }
      const matches = age.match(/^(\d+)\s*(\w+)$/);
      if (matches && matches.length === 3) {
        return matches[2].toLowerCase();
      } else {
        return "";
      }
    },
    getAgeNumber(num) {
      if (num === undefined || num === null || num === "") {
        return "";
      }

      const matches = num.match(/^(\d+)\s*(\w+)$/);
      if (matches && matches.length === 3) {
        return matches[1];
      } else {
        return "";
      }
    },
    goBack() {
      this.$router.push("/myAplicationAdoption");
    },
    mapSize(size) {
      return sizes[size] || size;
    },
    maplifeStages(stage) {
      return lifeStages[stage] || stage;
    },
    mapweightUnits(weightUnit) {
      return weightUnits[weightUnit] || weightUnit;
    },
    mapGender(genderpet) {
      return gender[genderpet] || genderpet;
    },
    mapageUnits(ageUnit) {
      return ageUnits[ageUnit] || ageUnit;
    },
    getDate(date) {
      const formattedDate = new Date(date);
      const options = { year: "numeric", month: "long", day: "2-digit" };
      return formattedDate.toLocaleDateString("es-ES", options);
    },
    getBadgeVariant(status) {
      switch (status) {
        case "ADOPTED":
          return "info";
        case "CLOSED":
          return "danger";
        case "PENDING":
          return "warning";
        default:
          return "info";
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
        this.requestAdoption.previousExperience = JSON.parse(
          this.requestAdoption.previousExperience
        );
        this.requestAdoption.previousExperience.lastPet = await decrypt(
          this.requestAdoption.previousExperience.lastPet
        );
        this.requestAdoption.previousExperience.whatDidYouDoWhenThePetGotSick =
          await decrypt(
            this.requestAdoption.previousExperience
              .whatDidYouDoWhenThePetGotSick
          );
        this.requestAdoption.previousExperience.whatKindOfPetsHaveYouHadBefore =
          await decrypt(
            this.requestAdoption.previousExperience
              .whatKindOfPetsHaveYouHadBefore
          );
        this.requestAdoption.previousExperience.whatMemoriesDoYouHaveWithYourPet =
          await decrypt(
            this.requestAdoption.previousExperience
              .whatMemoriesDoYouHaveWithYourPet
          );

        this.requestAdoption.reasonsForAdoption = JSON.parse(
          this.requestAdoption.reasonsForAdoption
        );
        this.requestAdoption.reasonsForAdoption.haveHadPets = await decrypt(
          this.requestAdoption.reasonsForAdoption.haveHadPets
        );
        this.requestAdoption.reasonsForAdoption.peopleAgreeToAdopt =
          await decrypt(
            this.requestAdoption.reasonsForAdoption.peopleAgreeToAdopt
          );
        this.requestAdoption.reasonsForAdoption.whereWillThePetBe =
          await decrypt(
            this.requestAdoption.reasonsForAdoption.whereWillThePetBe
          );
        this.requestAdoption.reasonsForAdoption.whyAdoptPet = await decrypt(
          this.requestAdoption.reasonsForAdoption.whyAdoptPet
        );
        this.requestAdoption.additionalInformation = await decrypt(
          this.requestAdoption.additionalInformation
        );
        this.getCredentialPet();
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
    async getCredentialPet() {
      try {
        let idPet = await encrypt(this.requestAdoption.pet.id);
        const response = await instance.post("/pet/credential", {
          id: idPet,
        });
        this.credentialPet = response.data.data;
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
    async closedRequestAdoption() {
      Swal.fire({
        title: "¿Estás seguro de cancelar la solicitud de adopción?",
        text: "Una vez enviada no podrá ser modificada",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Si, enviar",
        cancelButtonText: "Cancelar",
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire({
            title: "Espera un momento...",
            text: "Estamos enviando tu solicitud",
            imageUrl: perroChato,
            timer: 2000,
            timerProgressBar: true,
            imageWidth: 160,
            imageHeight: 160,
            showConfirmButton: false,
          }).then(() => {
            let response = instance.put("/adoption/changeStatusClosed", {
              idAdoption: localStorage.getItem("adoptionId"),
            });
            if (!response.error) {
              Swal.fire({
                title: "Solicitud cancelada",
                text: "Tu solicitud ha sido cancelada",
                icon: "success",
                showConfirmButton: true,
              }).then((result) => {
                if (result.isConfirmed) {
                  this.$router.push("/myAplicationAdoption");
                }
              });
            } else {
              Swal.fire({
                title: "Error",
                text: "Ocurrió un error al cancelar la solicitud",
                icon: "error",
                iconColor: "#FF0000",
                showConfirmButton: true,
              }).then((result) => {
                if (result.isConfirmed) {
                  this.$router.push("/home");
                }
              });
            }
          });
        }
      });
    },
  },
};
</script>
