<template>
  <div>
    <Encabezado
      color="#118A95"
      :imagenUrl="require('@/assets/imgs/perroflow.png')"
      titulo="Solicitud de Adopción"
    />
    <div class="my-3">
      <b-container>
        <b-row>
          <form @submit="submitAdoptionForm">
            <!-- incio de fotos de casa -->
            <div class="container-fluid">
              <div class="card encabezadoColorform">
                <h4 style="margin-left: 2rem" class="mt-1 mb-0">
                  <i
                    class="material-icons ms-2"
                    style="font-size: larger; vertical-align: middle"
                    >pets</i
                  >
                  Lugar de residencia
                </h4>
              </div>
              <b-card-group deck>
                <b-card
                  class="contentform d-flex justify-content-center align-items-center"
                >
                  <b-container>
                    <b-row>
                      <b-col
                        cols="12"
                        sm="12"
                        md="4"
                        lg="4"
                        class="text-center"
                      >
                        <label>Lugar en la que dormirá la mascota</label>
                        <br />
                        <img
                          v-if="adoptionInfo.placeToSleep === null"
                          src="@/assets/imgs/imageSearch.png"
                          alt="imagen"
                        />
                        <img
                          v-if="adoptionInfo.placeToSleep !== null"
                          :src="base64ToImage(adoptionInfo.placeToSleep)"
                          alt="imagen"
                          class="tam-img my-1"
                        />
                        <input
                          type="file"
                          accept="image/*"
                          class="input-img"
                          @change="
                            handleFileInputChange($event, 'placeToSleep')
                          "
                        />
                      </b-col>
                      <b-col
                        cols="12"
                        sm="12"
                        md="4"
                        lg="4"
                        class="text-center"
                      >
                        <label>Lugar en el que vivirá la mascota (casa)</label>
                        <br />
                        <img
                          v-if="adoptionInfo.placeToLive === null"
                          src="@/assets/imgs/imageSearch.png"
                          alt="imagen"
                          style="font-size: 100px; vertical-align: middle"
                        />
                        <img
                          v-if="adoptionInfo.placeToLive !== null"
                          :src="base64ToImage(adoptionInfo.placeToLive)"
                          alt="imagen"
                          class="tam-img my-1"
                        />
                        <input
                          type="file"
                          accept="image/*"
                          class="input-img"
                          @change="handleFileInputChange($event, 'placeToLive')"
                        />
                      </b-col>
                      <b-col
                        cols="12"
                        sm="12"
                        md="4"
                        lg="4"
                        class="text-center"
                      >
                        <label>Lugar en el que jugará la mascota (casa)</label>
                        <br />
                        <img
                          v-if="adoptionInfo.placeToPlay === null"
                          src="@/assets/imgs/imageSearch.png"
                          alt="imagen"
                          style="font-size: 100px; vertical-align: middle"
                        />
                        <img
                          v-if="adoptionInfo.placeToPlay !== null"
                          :src="base64ToImage(adoptionInfo.placeToPlay)"
                          alt="imagen"
                          class="tam-img my-1"
                        />
                        <input
                          type="file"
                          accept="image/*"
                          class="input-img"
                          @change="handleFileInputChange($event, 'placeToPlay')"
                        />
                      </b-col>
                    </b-row>
                  </b-container>
                </b-card>
              </b-card-group>
            </div>
            <!-- fin de fotos de casa -->
            <!-- incio de motivos de adopcion -->
            <div class="container-fluid my-3">
              <div class="card encabezadoColorformMotive">
                <h4 style="margin-left: 2rem" class="mt-1 mb-0">
                  <i
                    class="material-icons ms-2"
                    style="font-size: larger; vertical-align: middle"
                    >pets</i
                  >
                  Motivos de adopción
                </h4>
              </div>
              <b-card-group deck>
                <b-card class="contentformMotive d-flex-justify-content">
                  <b-container>
                    <b-row>
                      <b-col cols="12" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿Tienes o has tenido otros animales de compañia?"
                          label-for="input-1"
                        >
                          <b-form-input
                            id="haveHadPets"
                            v-model="adoptionInfo.haveHadPets"
                            rows="3"
                            aria-describedby="haveHadPets-live-feedback"
                            minlength="10"
                            maxlength="100"
                            :state="validation.haveHadPets"
                            @input="ValidateHaveHadPets"
                          ></b-form-input>
                          <b-form-invalid-feedback
                            id="haveHadPets-live-feedback"
                            tooltip
                          >
                            {{ error.haveHadPets }}
                          </b-form-invalid-feedback>
                        </b-form-group>
                      </b-col>
                      <b-col cols="12" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿En que parte de la casa lo planeas tener?"
                          label-for="input-1"
                        >
                          <b-form-input
                            id="input-1"
                            v-model="adoptionInfo.whereWillThePetBe"
                            rows="3"
                            aria-describedby="whereWillThePetBe-live-feedback"
                            minlength="10"
                            maxlength="100"
                            :state="validation.whereWillThePetBe"
                            @input="validatewherewill"
                          ></b-form-input>
                          <b-form-invalid-feedback
                            id="whereWillThePetBe-live-feedback"
                            tooltip
                          >
                            {{ error.whereWillThePetBe }}
                          </b-form-invalid-feedback>
                        </b-form-group>
                      </b-col>
                    </b-row>
                    <b-row>
                      <b-col cols="12" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿Las personas con quien vives están de acuerdo en adoptar?"
                          label-for="input-1"
                        >
                          <b-form-input
                            id="input-1"
                            v-model="adoptionInfo.peopleAgreeToAdopt"
                            :state="validation.peopleAgreeToAdopt"
                            @input="validatePeopleAgreeToAdopt"
                            minlegt="10"
                            maxlength="100"
                            aria-describedby="peopleAgreeToAdopt-live-feedback"
                            rows="3"
                          ></b-form-input>
                          <b-form-invalid-feedback
                            id="peopleAgreeToAdopt-live-feedback"
                            tooltip
                          >
                            {{ error.peopleAgreeToAdopt }}
                          </b-form-invalid-feedback>
                        </b-form-group>
                      </b-col>
                      <b-col cols="12" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="Comentarios adicionales"
                          label-for="input-1"
                        >
                          <b-form-input
                            id="input-1"
                            v-model="adoptionInfo.additionalComments"
                            rows="3"
                            minlegt="10"
                            maxlength="100"
                            aria-describedby="additionalComments-live-feedback"
                            :state="validation.additionalComments"
                            @input="validateAdditionalComments"
                          ></b-form-input>
                          <b-form-invalid-feedback
                            id="additionalComments-live-feedback"
                            tooltip
                          >
                            {{ error.additionalComments }}
                          </b-form-invalid-feedback>
                        </b-form-group>
                      </b-col>
                    </b-row>
                  </b-container>
                </b-card>
              </b-card-group>
            </div>
            <!-- fin de motivos de adopcion -->

            <!-- incio de experiencias previas -->
            <div class="container-fluid my-3">
              <div class="card encabezadoColorformExperience">
                <h4 style="margin-left: 2rem" class="mt-1 mb-0">
                  <i
                    class="material-icons ms-2"
                    style="font-size: larger; vertical-align: middle"
                    >pets</i
                  >
                  Experiencias previas
                </h4>
              </div>
              <b-card-group deck>
                <b-card
                  class="contentformExperience d-flex justify-content align-items"
                >
                  <b-container>
                    <b-row>
                      <b-col cols="12" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿Cuál fue tu ultima mascota?"
                          label-for="input-1"
                        >
                          <b-form-input
                            id="input-1"
                            v-model="adoptionInfo.lastPet"
                            @input="validateLastPet"
                            :state="validation.lastPet"
                            aria-describedby="lastPet-live-feedback"
                            minlegth="10"
                            maxlength="100"
                            rows="3"
                          ></b-form-input>
                          <b-form-invalid-feedback
                            id="lastPet-live-feedback"
                            tooltip>
                            {{ error.lastPet }}
                            </b-form-invalid-feedback>
                        </b-form-group>
                      </b-col>
                      <b-col cols="12" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿Cuando se enfermaba la mascota que hacias?"
                          label-for="input-1"
                        >
                          <b-form-input
                            id="input-1"
                            v-model="adoptionInfo.whatDidYouDoWhenThePetGotSick"
                            @input="validateWhatDidYouDoWhenThePetGotSick"
                            :state="validation.whatDidYouDoWhenThePetGotSick"
                            aria-describedby="whatDidYouDoWhenThePetGotSick-live-feedback"
                            minlegth="10"
                            maxlength="100"
                            rows="3"
                          ></b-form-input>
                          <b-form-invalid-feedback
                            id="whatDidYouDoWhenThePetGotSick-live-feedback"
                            tooltip>
                            {{ error.whatDidYouDoWhenThePetGotSick }}
                            </b-form-invalid-feedback>
                        </b-form-group>
                      </b-col>
                    </b-row>
                    <b-row>
                      <b-col cols="12" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿Qué tipo de mascotas haz tenido antes?"
                          label-for="input-1"
                        >
                          <b-form-input
                            v-model="
                              adoptionInfo.whatKindOfPetsHaveYouHadBefore
                            "
                            :state="validation.whatKindOfPetsHaveYouHadBefore"
                            @input="validateWhatKindOfPetsHaveYouHadBefore"
                            aria-describedby="whatKindOfPetsHaveYouHadBefore-live-feedback"
                            minlegth="10"
                            maxlength="100"
                            id="input-1"
                            rows="3"
                          ></b-form-input>
                          <b-form-invalid-feedback
                            id="whatKindOfPetsHaveYouHadBefore-live-feedback"
                            tooltip>
                            {{ error.whatKindOfPetsHaveYouHadBefore }}
                            </b-form-invalid-feedback>
                        </b-form-group>
                      </b-col>
                      <b-col cols="12" sm="12" md="6" lg="6">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿Qué recuerdos tienes con tu mascota?"
                          label-for="input-1"
                        >
                          <b-form-input
                            v-model="
                              adoptionInfo.whatMemoriesDoYouHaveWithYourPet
                            "
                            :state="validation.whatMemoriesDoYouHaveWithYourPet"
                            @input="validateWhatMemoriesDoYouHaveWithYourPet"
                            aria-describedby="whatMemoriesDoYouHaveWithYourPet-live-feedback"
                            minlegth="10"
                            maxlength="100"
                            id="input-1"
                            rows="3"
                          ></b-form-input>
                          <b-form-invalid-feedback
                            id="whatMemoriesDoYouHaveWithYourPet-live-feedback"
                            tooltip>
                            {{ error.whatMemoriesDoYouHaveWithYourPet }}
                            </b-form-invalid-feedback>
                        </b-form-group>
                      </b-col>
                    </b-row>
                  </b-container>
                </b-card>
              </b-card-group>
            </div>
            <!-- fin de experiencias previas -->

            <!-- incio de info personal -->
            <div class="container-fluid my-3">
              <div class="card encabezadoColorform">
                <h4 style="margin-left: 2rem" class="mt-1 mb-0">
                  <i
                    class="material-icons ms-2"
                    style="font-size: larger; vertical-align: middle"
                    >pets</i
                  >
                  Experiencias previas
                </h4>
              </div>
              <b-card-group deck>
                <b-card class="contentform d-flex justify-content align-items">
                  <b-container>
                    <b-row>
                      <b-col cols="12" sm="12" md="12" lg="12">
                        <b-form-group
                          class="mb-2"
                          id="input-group-1"
                          label="¿Desea agregar más información a su solicitud de adopción?"
                          label-for="input-1"
                        >
                          <b-form-textarea
                            id="input-1"
                            v-model="adoptionInfo.additionalInfo"
                            rows="3"
                            @input="validateAdditionalInfo"
                            :state="validation.additionalInfo"
                            aria-describedby="additionalInfo-live-feedback"
                            minlegth= "10"
                            maxlength="100"
                          ></b-form-textarea>
                          <b-form-invalid-feedback
                            id="additionalInfo-live-feedback"
                            tooltip>
                            {{ error.additionalInfo }}
                            </b-form-invalid-feedback>
                        </b-form-group>
                      </b-col>
                    </b-row>
                  </b-container>
                </b-card>
              </b-card-group>
            </div>
            <!-- fin de info personal -->
            <div class="container-fluid d-flex justify-content-end">
              <b-button
                type="submit"
                variant="outline-dark-secondary-blue pill"
                class="mt-3 rounded-button mx-4"
                :disabled="disableButton()"
              >
                Adoptar
              </b-button>
              <b-button
                type="submit"
                variant="outline-danger pills"
                class="mt-3 rounded-button"
                @click="cleanInfo"
              >
                Limpiar
              </b-button>
            </div>
          </form>
        </b-row>
      </b-container>
    </div>
  </div>
</template>

<script>
import Encabezado from "../../../../views/components/Encabezado.vue";
import swal from "sweetalert2";
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";

const regex = /^[a-zA-Z.]+\s?[a-zA-Z.]*$/;

export default {
  name: "FormAdoption",
  components: {
    Encabezado,
  },
  data() {
    return {
      adoptionInfo: {
        placeToSleep: null,
        placeToLive: null,
        placeToPlay: null,
        haveHadPets: null,
        whereWillThePetBe: null,
        peopleAgreeToAdopt: null,
        additionalComments: null,
        lastPet: null,
        whatDidYouDoWhenThePetGotSick: null,
        whatKindOfPetsHaveYouHadBefore: null,
        whatMemoriesDoYouHaveWithYourPet: null,
        additionalInfo: null,
      },
      validation: {
        haveHadPets: null,
        whereWillThePetBe: null,
        peopleAgreeToAdopt: null,
        additionalComments: null,
        lastPet: null,
        whatDidYouDoWhenThePetGotSick: null,
        whatKindOfPetsHaveYouHadBefore: null,
        whatMemoriesDoYouHaveWithYourPet: null,
        additionalInfo: null,
      },
      error: {
        haveHadPets: null,
        whereWillThePetBe: null,
        peopleAgreeToAdopt: null,
        additionalComments: null,
        lastPet: null,
        whatDidYouDoWhenThePetGotSick: null,
        whatKindOfPetsHaveYouHadBefore: null,
        whatMemoriesDoYouHaveWithYourPet: null,
        additionalInfo: null,
      },
    };
  },
  methods: {
    ValidateHaveHadPets() {
      if (!regex.test(this.adoptionInfo.haveHadPets)) {
        this.validation.haveHadPets = false;
        this.error.haveHadPets = "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (this.adoptionInfo.haveHadPets.length < 10) {
        this.validation.haveHadPets = false;
        this.error.haveHadPets =
          "La respuesta debe tener al menos 10 caracteres";
      } else if (this.adoptionInfo.haveHadPets.length > 100) {
        this.validation.haveHadPets = false;
        this.error.haveHadPets =
          "La respuesta debe tener menos de 100 caracteres";
      } else if (this.adoptionInfo.haveHadPets === null) {
        this.validation.haveHadPets = false;
        this.error.haveHadPets = "Este campo es requerido";
      } else {
        this.validation.haveHadPets = true;
        this.error.haveHadPets = null;
      }
    },
    validatewherewill() {
      if (!regex.test(this.adoptionInfo.whereWillThePetBe)) {
        this.validation.whereWillThePetBe = false;
        this.error.whereWillThePetBe =
          "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (this.adoptionInfo.whereWillThePetBe.length < 10) {
        this.validation.whereWillThePetBe = false;
        this.error.whereWillThePetBe =
          "La respuesta debe tener al menos 10 caracteres";
      } else if (this.adoptionInfo.whereWillThePetBe.length > 100) {
        this.validation.whereWillThePetBe = false;
        this.error.whereWillThePetBe =
          "La respuesta debe tener menos de 100 caracteres";
      } else if (this.adoptionInfo.whereWillThePetBe === null) {
        this.validation.whereWillThePetBe = false;
        this.error.whereWillThePetBe = "Este campo es requerido";
      } else {
        this.validation.whereWillThePetBe = true;
        this.error.whereWillThePetBe = null;
      }
    },
    validatePeopleAgreeToAdopt() {
      if (!regex.test(this.adoptionInfo.peopleAgreeToAdopt)) {
        this.validation.peopleAgreeToAdopt = false;
        this.error.peopleAgreeToAdopt =
          "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (this.adoptionInfo.peopleAgreeToAdopt.length < 10) {
        this.validation.peopleAgreeToAdopt = false;
        this.error.peopleAgreeToAdopt =
          "La respuesta debe tener al menos 10 caracteres";
      } else if (this.adoptionInfo.peopleAgreeToAdopt.length > 100) {
        this.validation.peopleAgreeToAdopt = false;
        this.error.peopleAgreeToAdopt =
          "La respuesta debe tener menos de 100 caracteres";
      } else if (this.adoptionInfo.peopleAgreeToAdopt === null) {
        this.validation.peopleAgreeToAdopt = false;
        this.error.peopleAgreeToAdopt = "Este campo es requerido";
      } else {
        this.validation.peopleAgreeToAdopt = true;
        this.error.peopleAgreeToAdopt = null;
      }
    },
    validateAdditionalComments() {
      if (!regex.test(this.adoptionInfo.additionalComments)) {
        this.validation.additionalComments = false;
        this.error.additionalComments =
          "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (this.adoptionInfo.additionalComments.length < 10) {
        this.validation.additionalComments = false;
        this.error.additionalComments =
          "La respuesta debe tener al menos 10 caracteres";
      } else if (this.adoptionInfo.additionalComments.length > 100) {
        this.validation.additionalComments = false;
        this.error.additionalComments =
          "La respuesta debe tener menos de 100 caracteres";
      } else if (this.adoptionInfo.additionalComments === null) {
        this.validation.additionalComments = false;
        this.error.additionalComments = "Este campo es requerido";
      } else {
        this.validation.additionalComments = true;
        this.error.additionalComments = null;
      }
    },
    validateLastPet() {
      if (!regex.test(this.adoptionInfo.lastPet)) {
        this.validation.lastPet = false;
        this.error.lastPet = "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (this.adoptionInfo.lastPet.length < 10) {
        this.validation.lastPet = false;
        this.error.lastPet = "La respuesta debe tener al menos 10 caracteres";
      } else if (this.adoptionInfo.lastPet.length > 100) {
        this.validation.lastPet = false;
        this.error.lastPet = "La respuesta debe tener menos de 100 caracteres";
      } else if (this.adoptionInfo.lastPet === null) {
        this.validation.lastPet = false;
        this.error.lastPet = "Este campo es requerido";
      } else {
        this.validation.lastPet = true;
        this.error.lastPet = null;
      }
    },
    validateWhatDidYouDoWhenThePetGotSick() {
      if (!regex.test(this.adoptionInfo.whatDidYouDoWhenThePetGotSick)) {
        this.validation.whatDidYouDoWhenThePetGotSick = false;
        this.error.whatDidYouDoWhenThePetGotSick =
          "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (this.adoptionInfo.whatDidYouDoWhenThePetGotSick.length < 10) {
        this.validation.whatDidYouDoWhenThePetGotSick = false;
        this.error.whatDidYouDoWhenThePetGotSick =
          "La respuesta debe tener al menos 10 caracteres";
      } else if (this.adoptionInfo.whatDidYouDoWhenThePetGotSick.length > 100) {
        this.validation.whatDidYouDoWhenThePetGotSick = false;
        this.error.whatDidYouDoWhenThePetGotSick =
          "La respuesta debe tener menos de 100 caracteres";
      } else if (this.adoptionInfo.whatDidYouDoWhenThePetGotSick === null) {
        this.validation.whatDidYouDoWhenThePetGotSick = false;
        this.error.whatDidYouDoWhenThePetGotSick = "Este campo es requerido";
      } else {
        this.validation.whatDidYouDoWhenThePetGotSick = true;
        this.error.whatDidYouDoWhenThePetGotSick = null;
      }
    },
    validateWhatKindOfPetsHaveYouHadBefore() {
      if (!regex.test(this.adoptionInfo.whatKindOfPetsHaveYouHadBefore)) {
        this.validation.whatKindOfPetsHaveYouHadBefore = false;
        this.error.whatKindOfPetsHaveYouHadBefore =
          "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (
        this.adoptionInfo.whatKindOfPetsHaveYouHadBefore.length < 10
      ) {
        this.validation.whatKindOfPetsHaveYouHadBefore = false;
        this.error.whatKindOfPetsHaveYouHadBefore =
          "La respuesta debe tener al menos 10 caracteres";
      } else if (
        this.adoptionInfo.whatKindOfPetsHaveYouHadBefore.length > 100
      ) {
        this.validation.whatKindOfPetsHaveYouHadBefore = false;
        this.error.whatKindOfPetsHaveYouHadBefore =
          "La respuesta debe tener menos de 100 caracteres";
      } else if (this.adoptionInfo.whatKindOfPetsHaveYouHadBefore === null) {
        this.validation.whatKindOfPetsHaveYouHadBefore = false;
        this.error.whatKindOfPetsHaveYouHadBefore = "Este campo es requerido";
      } else {
        this.validation.whatKindOfPetsHaveYouHadBefore = true;
        this.error.whatKindOfPetsHaveYouHadBefore = null;
      }
    },
    validateWhatMemoriesDoYouHaveWithYourPet() {
      if (!regex.test(this.adoptionInfo.whatMemoriesDoYouHaveWithYourPet)) {
        this.validation.whatMemoriesDoYouHaveWithYourPet = false;
        this.error.whatMemoriesDoYouHaveWithYourPet =
          "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (
        this.adoptionInfo.whatMemoriesDoYouHaveWithYourPet.length < 10
      ) {
        this.validation.whatMemoriesDoYouHaveWithYourPet = false;
        this.error.whatMemoriesDoYouHaveWithYourPet =
          "La respuesta debe tener al menos 10 caracteres";
      } else if (
        this.adoptionInfo.whatMemoriesDoYouHaveWithYourPet.length > 100
      ) {
        this.validation.whatMemoriesDoYouHaveWithYourPet = false;
        this.error.whatMemoriesDoYouHaveWithYourPet =
          "La respuesta debe tener menos de 100 caracteres";
      } else if (this.adoptionInfo.whatMemoriesDoYouHaveWithYourPet === null) {
        this.validation.whatMemoriesDoYouHaveWithYourPet = false;
        this.error.whatMemoriesDoYouHaveWithYourPet = "Este campo es requerido";
      } else {
        this.validation.whatMemoriesDoYouHaveWithYourPet = true;
        this.error.whatMemoriesDoYouHaveWithYourPet = null;
      }
    },
    validateAdditionalInfo() {
      if (!regex.test(this.adoptionInfo.additionalInfo)) {
        this.validation.additionalInfo = false;
        this.error.additionalInfo = "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (this.adoptionInfo.additionalInfo.length < 10) {
        this.validation.additionalInfo = false;
        this.error.additionalInfo = "La respuesta debe tener al menos 10 caracteres";
      } else if (this.adoptionInfo.additionalInfo.length > 100) {
        this.validation.additionalInfo = false;
        this.error.additionalInfo = "La respuesta debe tener menos de 100 caracteres";
      } else if (this.adoptionInfo.additionalInfo === null) {
        this.validation.additionalInfo = false;
        this.error.additionalInfo = "Este campo es requerido";
      } else {
        this.validation.additionalInfo = true;
        this.error.additionalInfo = null;
      }
    },
    submitAdoptionForm() {
      swal
        .fire({
          title: "¿Estás seguro de enviar la solicitud de adopción?",
          text: "Una vez enviada no podrá ser modificada",
          icon: "warning",
          showCancelButton: true,
          confirmButtonColor: "#3085d6",
          cancelButtonColor: "#d33",
          confirmButtonText: "Si, enviar",
          cancelButtonText: "Cancelar",
        })
        .then((result) => {
          if (result.isConfirmed) {
            swal.fire({
              title: "Espera un momento...",
              text: "Estamos enviando tu solicitud de adopción",
              imageUrl: gatoWalkingGif,
              timer: 2000,
              timerProgressBar: true,
              imageWidth: 160, // Ancho de la imagen
              imageHeight: 160, // Altura de la imagen
              showConfirmButton: false,
            });
            console.log(this.adoptionInfo);
          }
        });
    },
    cleanInfo() {
      this.adoptionInfo = {
        placeToSleep: null,
        placeToLive: null,
        placeToPlay: null,
        haveHadPets: null,
        whereWillThePetBe: null,
        peopleAgreeToAdopt: null,
        additionalComments: null,
        lastPet: null,
        whatDidYouDoWhenThePetGotSick: null,
        whatKindOfPetsHaveYouHadBefore: null,
        whatMemoriesDoYouHaveWithYourPet: null,
        additionalInfo: null,
      };
    },
    disableButton() {
      if (
        this.adoptionInfo.placeToSleep === null ||
        this.adoptionInfo.placeToLive === null ||
        this.adoptionInfo.placeToPlay === null ||
        this.adoptionInfo.haveHadPets === null ||
        this.adoptionInfo.whereWillThePetBe === null ||
        this.adoptionInfo.peopleAgreeToAdopt === null ||
        this.adoptionInfo.additionalComments === null ||
        this.adoptionInfo.lastPet === null ||
        this.adoptionInfo.whatDidYouDoWhenThePetGotSick === null ||
        this.adoptionInfo.whatKindOfPetsHaveYouHadBefore === null ||
        this.adoptionInfo.whatMemoriesDoYouHaveWithYourPet === null ||
        this.adoptionInfo.additionalInfo === null
      ) {
        return true;
      }
      return false;
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
    handleFileInputChange(event, propertyName) {
      const file = event.target.files[0];
      if (file) {
        this.imageToBase64(file, (base64String) => {
          this.$set(this.adoptionInfo, propertyName, base64String);
        });
      }
    },
    imageToBase64(file, callback) {
      // Verificar si el archivo es una imagen
      if (!file.type.startsWith("image/")) {
        this.makeToast("El archivo no es una imagen");
        return;
      }

      const reader = new FileReader();

      reader.onload = function (event) {
        callback(event.target.result);
      };

      reader.onerror = function (error) {
        console.error("Error al leer el archivo", error);
      };

      reader.readAsDataURL(file);
    },
    makeToast(text) {
      this.$bvToast.toast(text, {
        title: "ADVERTENCIA",
        variant: "warning",
        solid: true,
        autoHideDelay: 5000,
      });
    },
  },
};
</script>

<style scoped>
.input-img {
  display: flexbox;
  width: 100%;
  background-color: #ffb23f;
}
.encabezadoColorform {
  width: 50%;
  background-color: #ffb23f;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.contentform {
  background-color: #ffc876;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.encabezadoColorformMotive {
  width: 50%;
  background-color: #fb6528;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.contentformMotive {
  background-color: #fe8757;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.encabezadoColorformExperience {
  width: 50%;
  background-color: #1da3af;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.contentformExperience {
  background-color: #5ebfc5;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.rounded-button {
  border-radius: 20px;
  width: 20%;
}

.tam-img {
  max-width: 200px;
  height: 128px;
}
</style>
