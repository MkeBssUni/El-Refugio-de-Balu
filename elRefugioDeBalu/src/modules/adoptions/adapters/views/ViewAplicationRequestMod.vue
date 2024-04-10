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
                  :src="imgGender"
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
                    <b-card-title>Anna Christina Bustos</b-card-title>
                    <hr class="my-line" />
                    <b-row>
                      <b-col cols="12" sm="12" lg="8" md="8" xl="8">
                        <b-card-text>
                          <b>Telefono:</b> &nbsp; 777 890 567
                        </b-card-text>
                      </b-col>
                    </b-row>
                    <b-row>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text>
                          <b>Ciudad:</b>&nbsp; Cuernavaca, Morelos
                        </b-card-text>
                      </b-col>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text>
                          <b>Dirección:</b>&nbsp;Lorem ipsum dolor sit amet,
                          consectetur adipiscing elit. Aenean porta
                        </b-card-text>
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
                :src="base64ToImage(requestAdoption.requestImages[0].image)"
                class="homePhotos"
              />
            </b-card-body>
            <b-card-body>
              <b-card-text
                >Lugar en el que vivira la mascota (casa)</b-card-text
              >
              <img
                :src="base64ToImage(requestAdoption.requestImages[1].image)"
                class="homePhotos"
              />
            </b-card-body>
            <b-card-body>
              <b-card-text
                >Lugar en el que jugará la mascota (casa)</b-card-text
              >
              <img
                :src="base64ToImage(requestAdoption.requestImages[2].image)"
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
                      : "No se registraron motivos"
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
                  <b>¿Cuál fue tu ultima mascota?</b>
                  {{ requestAdoption.previousExperience.lastPet }}
                </b-card-text>
              </b-col>
              <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                <b-card-text>
                  <b>¿Cuando se enfermaba la mascota que hacias?</b>
                  {{
                    requestAdoption.previousExperience
                      .whatDidYouDoWhenThePetGotSick
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
          variant="outline-success"
          class="mt-3 mx-4"
          @click="adopted"
          :disabled="requestAdoption.status.name !== 'PENDING'"
        >
          <b-icon icon="clipboard-check"></b-icon>
          &nbsp;Aprobar Solicitud del adoptante
        </b-button>

        <b-button
          type="submit"
          variant="outline-danger"
          class="mt-3"
          @click="closed"
          :disabled="requestAdoption.status.name !== 'PENDING'"
        >
          <b-icon icon="clipboard-x"></b-icon>
          &nbsp;Finalizar Solicitud del adoptante
        </b-button>
      </div>
    </b-card>
  </div>
</template>

<script>
import Swal from "sweetalert2";
import perroChato from "@/assets/imgs/perroChato1.gif";
import female from "@/assets/imgs/female.jpg";
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
  name: "viewAplicationAdoptionRequestMod",
  data() {
    return {
      requestAdoption: {},
      infoStatus: "",
      credentialPet: {},
      information: {},
      imgGender: female,
      typeState: "",
      statu: "",
    };
  },
  mounted() {
    this.getAdoption();
  },
  methods: {
    getAgeUnit(age) {
      const matches = age.match(/^(\d+)\s*(\w+)$/);
      if (matches && matches.length === 3) {
        return matches[2].toLowerCase();
      } else {
        return "";
      }
    },
    getAgeNumber(age) {
      const matches = age.match(/^(\d+)\s*(\w+)$/);
      if (matches && matches.length === 3) {
        return matches[1]; // Devuelve solo el número
      } else {
        return ""; // Opción de manejo de errores si el formato no es válido
      }
    },
    goBack() {
      this.$router.push("/moderated/adoptionList");
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
    base64ToImage(base64String) {
      if (!base64String) {
        console.error("base64String es nulo o indefinido");
        return null;
      }
      // Extraer el tipo de la imagen desde la cadena Base64
      const type = base64String.substring(
        "data:image/".length,
        base64String.indexOf(";base64")
      );

      // Crear un blob desde la cadena Base64
      const byteCharacters = atob(base64String.split(",")[1]);
      const byteArrays = [];
      for (let offset = 0; offset < byteCharacters.length; offset += 512) {
        const slice = byteCharacters.slice(offset, offset + 512);
        const byteNumbers = new Array(slice.length);
        for (let i = 0; i < slice.length; i++) {
          byteNumbers[i] = slice.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        byteArrays.push(byteArray);
      }
      const blob = new Blob(byteArrays, { type: type });

      // Crear una URL para la imagen
      const url = URL.createObjectURL(blob);

      // Retornar la URL de la imagen
      return url;
    },
    async changeStatus() {},
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
        this.requestAdoption.reasonsForAdoption.whyAdoptPet =
          await decrypt(
            this.requestAdoption.reasonsForAdoption.whyAdoptPet
          );
        this.requestAdoption.additionalInformation = await decrypt(
          this.requestAdoption.additionalInformation
        );
        this.getDetails();
        console.log(this.requestAdoption.user.id);
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
        console.log(this.credentialPet);
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
    adopted() {
      this.typeState = "aprobar al adoptante";
      this.statu = "ADOPTED";
      this.changeStatus();
    },
    closed() {
      this.typeState = "finalizar la solicitud al adoptante";
      this.statu = "CLOSED";
      this.changeStatus();
    },
    async changeAdopted() {
      try {
        let response = instance.put("/pet/adoption", {
          pet: localStorage.getItem("petId"),
          adoptant: this.requestAdoption.user.id,
          moderator: localStorage.getItem("userId"),
        });
        if (!response.error) {
        }
      } catch (error) {
        Swal.fire({
          title: "Ocurrio un error",
          text: "Cambio el estado no se pudo enviar correctamente",
          icon: "error",
          showConfirmButton: true,
        });
      }
    },
    async changeStatus() {
      console.log(this.statu);
      Swal.fire({
        title: `¿Estás seguro de  ${this.typeState} `,
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
            imageWidth: 160, // Ancho de la imagen
            imageHeight: 160, // Altura de la imagen
            showConfirmButton: false,
          }).then(async() => {
            let response = await instance.put("/adoption/changeStatus", {
              adoptionId: localStorage.getItem("adoptionId"),
              status: this.statu,
            });
            console.log(response);
            if (this.statu === "ADOPTED" && !response.error ) {
              let response = instance.put("/pet/adoption", {
                pet: localStorage.getItem("petId"),
                adoptant: this.requestAdoption.user.id,
                moderator: localStorage.getItem("userId"),
              });
            }
            if (!response.error) {
              Swal.fire({
                title: `Solicitud ha sido ${
                  this.typeState === "ADOPTED" ? "aprobada" : "finalizada"
                }`,
                text: "La solicitud ha cambiado de estado correctamente",
                icon: "success",
                showConfirmButton: true,
              }).then((result) => {
                if (result.isConfirmed) {
                  this.$router.push("/adoptionList");
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
    async getDetails() {
      console.log("Aqui info del usuario");
      console.log(this.requestAdoption.user.id);
      // try {
      //   const response = await instance.post("/person/details", {
      //     userId: await encrypt(this.requestAdoption.user.id),
      //   });
      //   this.information = response.data.data;
      //   this.information.phoneNumber = await decrypt(
      //     this.information.phoneNumber
      //   );
      //   this.information.user.username = await decrypt(
      //     this.information.user.username
      //   );
      //   console.log(this.information);

      //   swal.close();
      // } catch (error) {
      //   Swal
      //     .fire({
      //       title: "Error",
      //       text: "No se pudieron cargar tus datos",
      //       icon: "error",
      //       showConfirmButton: false,
      //       timer: 1500,
      //     })
      //     .then(() => {
      //       this.$router.go(-1);
      //     });
      // }
    },
  },
};
</script>
