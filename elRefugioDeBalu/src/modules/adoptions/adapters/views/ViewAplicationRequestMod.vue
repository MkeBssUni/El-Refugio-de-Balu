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
                v-if="requestAdoption.status && requestAdoption.status.name"
                :variant="getBadgeVariant(requestAdoption.status.name)"
                >{{ getStatus(requestAdoption.status.name) }}</b-badge
              >
              <br />
              {{ infoStatus }}
            </b-card-text>
          </b-card>
          <b-card bg-variant="light" class="box-shadow-pretty text-center">
            <b-card
              bg-variant="card-header-orange"
              class="my-2 information-pet"
            >
              <b-card-body>
                <b-card-title>Domicilio Mascota</b-card-title>
                <hr class="my-line" />
                <b-row>
                  <b-col cols="12" sm="12" lg="4" md="4" xl="4">
                    <b-card-text>
                      <b>Ciudad:</b> &nbsp; 
                      {{ addressByPet.country }}
                    </b-card-text>
                  </b-col>
                  <b-col cols="12" sm="12" lg="4" md="4" xl="4">
                    <b-card-text>
                      <b>País:</b>&nbsp; 
                      {{ addressByPet.city }}
                    </b-card-text>
                  </b-col>
                  <b-col cols="12" sm="12" lg="4" md="4" xl="4">
                    <b-card-text>
                      <b>Estado:</b>&nbsp; 
                      {{ addressByPet.state }}
                    </b-card-text>
                  </b-col>
                </b-row>
              </b-card-body>
            </b-card>
            <b-card-title>Lugar de residencia</b-card-title>
            <b-card-body>
              <b-card-text
                >Lugar en la que dormira o descansara la mascota</b-card-text
              >
              <img
                :src="getRequestImage(requestAdoption.requestImages, 0)"
                class="homePhotos"
                alt="restingPlace"
              />
            </b-card-body>
            <b-card-body>
              <b-card-text
                >Lugar en el que vivira la mascota (casa)</b-card-text
              >
              <img
                :src="getRequestImage(requestAdoption.requestImages, 1)"
                class="homePhotos"
                alt="homePlace"
              />
            </b-card-body>
            <b-card-body>
              <b-card-text
                >Lugar en el que jugará la mascota (casa)</b-card-text
              >
              <img
                :src="getRequestImage(requestAdoption.requestImages, 2)"
                class="homePhotos"
                alt="playPlace"
              />
            </b-card-body>
          </b-card>
        </b-col>
        <b-col cols="12" sm="12" lg="8" md="8">
          <b-card class="target-mod">
            <b-row>
              <b-col cols="12" sm="12" lg="12" md="12">
                <b-card bg-variant="card-content-orange" class="my-2">
                  <b-card-body class="text-center">
                    <b-card-title
                      >Informacion del adoptante <br />
                      {{ information.name }}</b-card-title
                    >
                    <hr class="my-line" />
                    <b-row>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text>
                          <b>Correo electrónico:</b> &nbsp;
                          {{ information.email }}
                        </b-card-text>
                      </b-col>
                      <b-col cols="12" sm="12" lg="6" md="6" xl="6">
                        <b-card-text>
                          <b>Telefono:</b> {{ information.phone }}
                        </b-card-text>
                      </b-col>
                      <AddressByAdoptant :address="address" title="Domicilio" />
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
          variant="outline-success"
          class="mt-3 mx-4"
          @click="adopted"
          :disabled="
            requestAdoption.status && requestAdoption.status.name !== 'PENDING'
          "
        >
          <b-icon icon="clipboard-check"></b-icon>
          &nbsp;Aprobar Solicitud del adoptante
        </b-button>

        <b-button
          type="submit"
          variant="outline-danger"
          class="mt-3"
          @click="closed"
          :disabled="
            requestAdoption.status && requestAdoption.status.name !== 'PENDING'
          "
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
import instance from "../../../../config/axios";
import { decrypt, encrypt } from "../../../../kernel/hashFunctions";
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import AddressByAdoptant from "../components/AddressByAdoptant.vue";

export default {
  name: "viewAplicationAdoptionRequestMod",
  components: {
    AddressByAdoptant,
  },
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
      information: {},
      typeState: "",
      statu: "",
      address: {},
      idUser: "",
      addressByPet: {},
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
    goBack() {
      this.$router.push("/moderated/adoptionList");
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
        this.getAddress();
        this.getDetails();
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
    async getAddress() {
      try {
        this.idUser = await encrypt(this.requestAdoption.user.id);
        const responseadress = await instance.post("/address/details", {
          userId: this.idUser,
        });
        this.address = responseadress.data.data;      
        this.getAdressByPet();
      } catch (error) {
      }
    },
    async getAdressByPet() {
      try {
        const petId = await encrypt(this.requestAdoption.pet.id);
        const responseadress = await instance.post("/address/details", {
          petId: petId,
        });
        
        this.addressByPet = responseadress.data.data;
        
      } catch (error) {
      }
    },
    adopted() {
      this.typeState = "aprobar al adoptante";
      this.statu = "ADOPTED";
      this.showConfirm();
    },
    closed() {
      this.typeState = "finalizar la solicitud al adoptante";
      this.statu = "CLOSED";
      this.showConfirm();
    },
    async getDetails() {
      try {
        const idUSer = await encrypt(this.requestAdoption.user.id);
        this.adress = idUSer;
        const response = await instance.post("/person/find/contactInfo", {
          userId: idUSer,
        });
        this.information = response.data.data;
        this.information.phone = await decrypt(this.information.phone);
        this.information.email = await decrypt(this.information.email);
        Swal.close();
      } catch (error) {
        Swal.fire({
          title: "Error",
          text: "No se pudieron cargar tus datos",
          icon: "error",
          showConfirmButton: false,
          timer: 1500,
        }).then(() => {
          this.$router.go(-1);
        });
      }
    },
    showConfirm() {
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
          if (this.statu === "CLOSED") {
            this.changeStatus();
          } else {
            this.changeAdopted();
          }
        }
      });
    },
    async changeStatus() {
      try {
        Swal.fire({
          title: "Espera un momento...",
          text: "Estamos se esta enviando la información",
          imageUrl: gatoWalkingGif,
          timerProgressBar: true,
          imageWidth: 160, 
          imageHeight: 160, 
          showConfirmButton: false,
        });
        await instance.put("/adoption/changeStatus", {
          adoptionId: localStorage.getItem("adoptionId"),
          status: this.statu,
        });
        Swal.fire({
          title: "Su cambio ha sido exitosa",
          text: "Espere un momento mientras se realiza el proceso siguiente",
          icon: "success",
          iconColor: "#53A93D",
          timer: 3000,
          timerProgressBar: true,
          showConfirmButton: false,
        }).finally(() => {
          this.$router.push("/moderated/adoptionList");
        });
      } catch (error) {
        let Msjerror = "";
        switch (error.response.data.message) {
          case "INVALID_USER":
            Msjerror =
              "Ups! por favor vuelve a iniciar sesión y volver a intentarlo";
            break;
          case "DUPLICATE_REQUEST":
            Msjerror =
              "Ups! Ya tienes una solicitud de adopción de la misma mascota";
            break;
          case "INVALID_LENGTH":
            Msjerror = "Ups! la respuesta debe tener entre 10 y 100 caracteres";
            break;
          case "INVALID_ROLE":
            Msjerror = "Ups! no tienes permisos para realizar esta acción";
            break;
          case "MAX_ADOPTIONREQUEST":
            Msjerror =
              "Ups! Solo puedes tener 5 solicitudes activas,por espera a que sean aprobadas o finalizadas";
            break;
          case "LIMIT_ADOPTIONREQUEST":
            Msjerror = "La mascota no esta disponible por el momento";
            break;
          case "ADOPTIONREQUEST_NOT_SAVED":
            Msjerror =
              "Ups! algo salió mal, por favor vuelve a intentarlo no se logro guardar la solicitud";
            break;
          default:
            Msjerror = "Ups! algo salió mal, por favor vuelve a intentarlo";
            break;
        }
        Swal.fire({
          title: "Error al enviar la solicitud de adopción",
          text: Msjerror,
          icon: "error",
          confirmButtonColor: "#3085d6",
          confirmButtonText: "Aceptar",
        });
      }
    },
    async changeAdopted() {
      try {
        Swal.fire({
          title: "Espera un momento...",
          text: "Estamos enviando la notificación de adopción al adoptante",
          imageUrl: gatoWalkingGif,
          timerProgressBar: true,
          imageWidth: 160, 
          imageHeight: 160, 
          showConfirmButton: false,
        });
        await instance.post("/pet/adoption", {
          pet: await encrypt(this.requestAdoption.pet.id),
          adoptant: await encrypt(this.requestAdoption.user.id),
          moderator: localStorage.getItem("userId"),
        });

        Swal.fire({
          title: "Listo!",
          text: "Permita un momento que se envie la notificación al adoptante",
          imageUrl: gatoWalkingGif,
          imageWidth: 160, 
          imageHeight: 160, 
          timer: 3000,
          timerProgressBar: true,
          showConfirmButton: false,
        }).finally(() => {
          this.changeStatus();
        });
      } catch (error) {        
        Swal.fire({
          title: "Ocurrio un error",
          text: "Cambio el estado no se pudo enviar correctamente",
          icon: "error",
          showConfirmButton: true,
        });
      }
    },
  },
};
</script>
