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
          <form>
            <!-- incio de fotos de casa -->
            <b-col cols="12" class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
              <b-row>
                <b-col cols="10" md="8" lg="6">
                  <b-card
                    bg-variant="card-header-orange"
                    class="py-2 card-shadow relative-position form-card-title"
                    no-body
                  >
                    <div class="d-flex align-items-center ms-3 ms-md-4">
                      <i class="material-icons me-2" style="font-size: 1.5rem"
                        >pets</i
                      >
                      <h4 class="mb-0 mt-1">Lugar de residencia</h4>
                    </div>
                  </b-card>
                </b-col>
              </b-row>
              <b-row>
                <b-col cols="12">
                  <b-card
                    bg-variant="card-content-orange"
                    class="card-shadow form-card-content"
                  >
                    <b-card-body>
                      <b-row>
                        <b-col
                          cols="12"
                          sm="12"
                          md="4"
                          lg="4"
                          class="text-center"
                        >
                          <label
                            >Lugar en la que dormirá la mascota
                            <span class="required-asterisk">*</span></label
                          >
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
                          <label
                            >Lugar en el que vivirá la mascota (casa)
                            <span class="required-asterisk">*</span></label
                          >
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
                            @change="
                              handleFileInputChange($event, 'placeToLive')
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
                          <label
                            >Lugar en el que jugará la mascota (casa)
                            <span class="required-asterisk">*</span></label
                          >
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
                            @change="
                              handleFileInputChange($event, 'placeToPlay')
                            "
                          />
                        </b-col>
                      </b-row>
                    </b-card-body>
                  </b-card>
                </b-col>
              </b-row>
            </b-col>
            <!-- fin de fotos de casa -->
            <!-- incio de motivos de adopcion -->
            <b-col cols="12" class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
              <b-row>
                <b-col cols="10" md="8" lg="6">
                  <b-card
                    bg-variant="card-header-secondary-orange"
                    class="py-2 card-shadow relative-position form-card-title"
                    no-body
                  >
                    <div class="d-flex align-items-center ms-3 ms-md-4">
                      <i class="material-icons me-2" style="font-size: 1.5rem"
                        >pets</i
                      >
                      <h4 class="mb-0 mt-1">Motivos de adopción</h4>
                    </div>
                  </b-card>
                </b-col>
              </b-row>
              <b-row>
                <b-col cols="12">
                  <b-card
                    bg-variant="card-content-secondary-orange"
                    class="card-shadow form-card-content"
                  >
                    <b-card-body>
                      <b-container>
                        <b-row>
                          <b-col cols="12" sm="12" md="6" lg="6">
                            <b-form-group class="mb-2" id="input-group-1">
                              <label for="input-1"
                                >¿Tienes o has tenido otros animales de
                                compañia?
                                <span class="required-asterisk">*</span></label
                              >
                              <b-form-input
                                id="haveHadPets"
                                v-model="
                                  adoptionRequestSave.reasonsForAdoption
                                    .haveHadPets
                                "
                                rows="3"
                                aria-describedby="haveHadPets-live-feedback"
                                minlength="10"
                                maxlength="100"
                                :state="validation.haveHadPets"
                                @input="ValidateHaveHadPets"
                              ></b-form-input>
                              <b-form-invalid-feedback
                                id="haveHadPets-live-feedback"
                              >
                                {{ error.haveHadPets }}
                              </b-form-invalid-feedback>
                            </b-form-group>
                          </b-col>
                          <b-col cols="12" sm="12" md="6" lg="6">
                            <b-form-group
                              class="mb-2"
                              id="input-group-1"
                              label-for="input-1"
                            >
                              <label for="input-1"
                                >¿En que parte de la casa lo planeas tener?
                                <span class="required-asterisk">*</span></label
                              >
                              <b-form-input
                                id="input-1"
                                v-model="
                                  adoptionRequestSave.reasonsForAdoption
                                    .whereWillThePetBe
                                "
                                rows="3"
                                aria-describedby="whereWillThePetBe-live-feedback"
                                minlength="10"
                                maxlength="100"
                                :state="validation.whereWillThePetBe"
                                @input="validatewherewill"
                              ></b-form-input>
                              <b-form-invalid-feedback
                                id="whereWillThePetBe-live-feedback"
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
                              label-for="input-1"
                            >
                              <label for="input-1"
                                >¿Las personas con quien vives están de acuerdo
                                en adoptar?
                                <span class="required-asterisk">*</span></label
                              >
                              <b-form-input
                                id="input-1"
                                v-model="
                                  adoptionRequestSave.reasonsForAdoption
                                    .peopleAgreeToAdopt
                                "
                                :state="validation.peopleAgreeToAdopt"
                                @input="validatePeopleAgreeToAdopt"
                                minlegt="10"
                                maxlength="100"
                                aria-describedby="peopleAgreeToAdopt-live-feedback"
                                rows="3"
                              ></b-form-input>
                              <b-form-invalid-feedback
                                id="peopleAgreeToAdopt-live-feedback"
                              >
                                {{ error.peopleAgreeToAdopt }}
                              </b-form-invalid-feedback>
                            </b-form-group>
                          </b-col>
                          <b-col cols="12" sm="12" md="6" lg="6">
                            <b-form-group
                              class="mb-2"
                              id="input-group-1"
                              label-for="input-1"
                            >
                              <label for="input-1"
                                >Comentarios adicionales
                              </label>
                              <b-form-input
                                id="input-1"
                                v-model="
                                  adoptionRequestSave.reasonsForAdoption
                                    .additionalComments
                                "
                                rows="3"
                                minlegt="10"
                                maxlength="100"
                                aria-describedby="additionalComments-live-feedback"
                                :state="validation.additionalComments"
                                @input="validateAdditionalComments"
                              ></b-form-input>

                              <b-form-invalid-feedback
                                id="additionalComments-live-feedback"
                              >
                                {{ error.additionalComments }}
                              </b-form-invalid-feedback>
                            </b-form-group>
                          </b-col>
                        </b-row>
                      </b-container>
                    </b-card-body>
                  </b-card>
                </b-col>
              </b-row>
            </b-col>

            <!-- fin de motivos de adopcion -->

            <!-- incio de experiencias previas -->
            <b-col cols="12" class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
              <b-row>
                <b-col cols="10" md="8" lg="6">
                  <b-card
                    bg-variant="card-header-blue"
                    class="py-2 card-shadow relative-position form-card-title"
                    no-body
                  >
                    <div class="d-flex align-items-center ms-3 ms-md-4">
                      <i class="material-icons me-2" style="font-size: 1.5rem"
                        >pets</i
                      >
                      <h4 class="mb-0 mt-1">Experencia Previa</h4>
                    </div>
                  </b-card>
                </b-col>
              </b-row>

              <b-row>
                <b-col cols="12">
                  <b-card
                    bg-variant="card-content-blue"
                    class="card-shadow form-card-content"
                  >
                    <b-card-body>
                      <b-container>
                        <b-row>
                          <b-col cols="12" sm="12" md="12" lg="12">
                            <b-card bg-variant="card-content-orange my-2">
                              <span class="required-asterisk">ADVERTENCIA</span>
                              En caso de no contar con experiencia previa, favor
                              de contestar las preguntas simulando que harías en
                              esos casos.
                            </b-card>
                          </b-col>
                          <b-col cols="12" sm="12" md="6" lg="6">
                            <b-form-group
                              class="mb-2"
                              id="input-group-1"
                              label-for="input-1"
                            >
                              <label for="input-1"
                                >¿Cuál fue tu ultima mascota?
                                <span class="required-asterisk">*</span></label
                              >
                              <b-form-input
                                id="input-1"
                                v-model="
                                  adoptionRequestSave.previousExperiencieDto
                                    .lastPet
                                "
                                @input="validateLastPet"
                                :state="validation.lastPet"
                                aria-describedby="lastPet-live-feedback"
                                minlegth="10"
                                maxlength="100"
                                rows="3"
                              ></b-form-input>
                              <b-form-invalid-feedback
                                id="lastPet-live-feedback"
                              >
                                {{ error.lastPet }}
                              </b-form-invalid-feedback>
                            </b-form-group>
                          </b-col>
                          <b-col cols="12" sm="12" md="6" lg="6">
                            <b-form-group
                              class="mb-2"
                              id="input-group-1"
                              label-for="input-1"
                            >
                              <label for="input-1"
                                >¿Cuando se enfermaba la mascota que hacias?
                                <span class="required-asterisk">*</span></label
                              >
                              <b-form-input
                                id="input-1"
                                v-model="
                                  adoptionRequestSave.previousExperiencieDto
                                    .whatDidYouDoWhenThePetGotSick
                                "
                                @input="validateWhatDidYouDoWhenThePetGotSick"
                                :state="
                                  validation.whatDidYouDoWhenThePetGotSick
                                "
                                aria-describedby="whatDidYouDoWhenThePetGotSick-live-feedback"
                                minlegth="10"
                                maxlength="100"
                                rows="3"
                              ></b-form-input>
                              <b-form-invalid-feedback
                                id="whatDidYouDoWhenThePetGotSick-live-feedback"
                              >
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
                              label-for="input-1"
                            >
                              <label for="input-1"
                                >¿Qué tipo de mascotas haz tenido antes?
                                <span class="required-asterisk">*</span></label
                              >
                              <b-form-input
                                v-model="
                                  adoptionRequestSave.previousExperiencieDto
                                    .whatKindOfPetsHaveYouHadBefore
                                "
                                :state="
                                  validation.whatKindOfPetsHaveYouHadBefore
                                "
                                @input="validateWhatKindOfPetsHaveYouHadBefore"
                                aria-describedby="whatKindOfPetsHaveYouHadBefore-live-feedback"
                                minlegth="10"
                                maxlength="100"
                                id="input-1"
                                rows="3"
                              ></b-form-input>
                              <b-form-invalid-feedback
                                id="whatKindOfPetsHaveYouHadBefore-live-feedback"
                              >
                                {{ error.whatKindOfPetsHaveYouHadBefore }}
                              </b-form-invalid-feedback>
                            </b-form-group>
                          </b-col>
                          <b-col cols="12" sm="12" md="6" lg="6">
                            <b-form-group
                              class="mb-2"
                              id="input-group-1"
                              label-for="input-1"
                            >
                              <label for="input-1"
                                >¿Qué recuerdos tienes con tu mascota?
                                <span class="required-asterisk">*</span></label
                              >
                              <b-form-input
                                v-model="
                                  adoptionRequestSave.previousExperiencieDto
                                    .whatMemoriesDoYouHaveWithYourPet
                                "
                                :state="
                                  validation.whatMemoriesDoYouHaveWithYourPet
                                "
                                @input="
                                  validateWhatMemoriesDoYouHaveWithYourPet
                                "
                                aria-describedby="whatMemoriesDoYouHaveWithYourPet-live-feedback"
                                minlegth="10"
                                maxlength="100"
                                id="input-1"
                                rows="3"
                              ></b-form-input>
                              <b-form-invalid-feedback
                                id="whatMemoriesDoYouHaveWithYourPet-live-feedback"
                              >
                                {{ error.whatMemoriesDoYouHaveWithYourPet }}
                              </b-form-invalid-feedback>
                            </b-form-group>
                          </b-col>
                        </b-row>
                      </b-container>
                    </b-card-body>
                  </b-card>
                </b-col>
              </b-row>
            </b-col>
            <!-- fin de experiencias previas -->

            <!-- incio de info personal -->
            <b-col cols="12" class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
              <b-row>
                <b-col cols="10" md="8" lg="6">
                  <b-card
                    bg-variant="card-header-orange"
                    class="py-2 card-shadow relative-position form-card-title"
                    no-body
                  >
                    <div class="d-flex align-items-center ms-3 ms-md-4">
                      <i class="material-icons me-2" style="font-size: 1.5rem"
                        >pets</i
                      >
                      <h4 class="mb-0 mt-1">Información Adicional</h4>
                    </div>
                  </b-card>
                </b-col>
              </b-row>
              <b-row>
                <b-col cols="12">
                  <b-card
                    bg-variant="card-content-orange"
                    class="card-shadow form-card-content"
                  >
                    <b-card-body>
                      <b-container>
                        <b-row>
                          <b-col cols="12" sm="12" md="12" lg="12">
                            <b-form-group
                              class="mb-2"
                              id="input-group-1"
                              label-for="input-1"
                            >
                              <label for="input-1"
                                >¿Desea agregar más información a su solicitud
                                de adopción?</label
                              >
                              <b-form-textarea
                                id="input-1"
                                v-model="
                                  adoptionRequestSave.additional_information
                                "
                                rows="3"
                                @input="validateAdditionalInfo"
                                :state="validation.additionalInfo"
                                aria-describedby="additionalInfo-live-feedback"
                                minlegth="10"
                                maxlength="100"
                              ></b-form-textarea>
                              <b-form-invalid-feedback
                                id="additionalInfo-live-feedback"
                              >
                                {{ error.additionalInfo }}
                              </b-form-invalid-feedback>
                            </b-form-group>
                          </b-col>
                        </b-row>
                      </b-container>
                    </b-card-body>
                  </b-card>
                </b-col>
              </b-row>
            </b-col>
            <b-col cols="12" class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
              <b-row class="px-5 px-sm-0 d-flex justify-content-end">
                <b-col cols="12" sm="6" md="5" lg="4" xl="3">
                  <b-button
                    variant="outline-dark-secondary-blue"
                    :disabled="disableButton()"
                    @click="submitAdoptionForm"
                    type="submit"
                    class="d-flex align-items-center justify-content-between w-100"
                  >
                    <span class="me-2">Enviar Solicitud</span>
                    <b-icon
                      icon="arrow-up-right-circle"
                      font-scale="1.3"
                    ></b-icon>
                  </b-button>
                </b-col>
                <b-col
                  cols="12"
                  sm="6"
                  md="5"
                  lg="4"
                  xl="3"
                  class="mt-3 mt-sm-0"
                >
                  <b-button
                    variant="outline-danger"
                    class="d-flex align-items-center justify-content-between w-100"
                    @click="closeForm()"
                  >
                    <span class="me-2">Cancelar</span>
                    <b-icon icon="trash" font-scale="1.3"></b-icon>
                  </b-button>
                </b-col>
              </b-row>
            </b-col>
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
import instance from "../../../../config/axios";
import { decrypt } from "../../../../kernel/hashFunctions";

const regex = /^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ' .]+$/;

export default {
  name: "FormAdoption",
  components: {
    Encabezado,
  },
  data() {
    return {
      homeSpecification: false,
      adoptionInfo: {
        placeToSleep: null,
        placeToLive: null,
        placeToPlay: null,
      },
      adoptionRequestSave: {
        user: localStorage.getItem("userId"),
        pet: localStorage.getItem("petId"),
        reasonsForAdoption: {
          peopleAgreeToAdopt: null,
          haveHadPets: null,
          whereWillThePetBe: null,
          additionalComments: null,
        },
        previousExperiencieDto: {
          whatDidYouDoWhenThePetGotSick: null,
          whatKindOfPetsHaveYouHadBefore: null,
          whatMemoriesDoYouHaveWithYourPet: null,
          lastPet: null,
        },
        additional_information: null,
        imageAdoption: [],
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
    closeForm() {
      this.cleanInfo();
      this.$router.push("/pets");
    },
    ValidateHaveHadPets() {
      if (
        !regex.test(this.adoptionRequestSave.reasonsForAdoption.haveHadPets)
      ) {
        this.validation.haveHadPets = false;
        this.error.haveHadPets = "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (
        this.adoptionRequestSave.reasonsForAdoption.haveHadPets.length < 10
      ) {
        this.validation.haveHadPets = false;
        this.error.haveHadPets =
          "La respuesta debe tener al menos 10 caracteres";
      } else if (
        this.adoptionRequestSave.reasonsForAdoption.haveHadPets.length > 100
      ) {
        this.validation.haveHadPets = false;
        this.error.haveHadPets =
          "La respuesta debe tener menos de 100 caracteres";
      } else if (
        this.adoptionRequestSave.reasonsForAdoption.haveHadPets === null
      ) {
        this.validation.haveHadPets = false;
        this.error.haveHadPets = "Este campo es requerido";
      } else {
        this.validation.haveHadPets = true;
        this.error.haveHadPets = null;
      }
    },
    validatewherewill() {
      if (
        !regex.test(
          this.adoptionRequestSave.reasonsForAdoption.whereWillThePetBe
        )
      ) {
        this.validation.whereWillThePetBe = false;
        this.error.whereWillThePetBe =
          "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (
        this.adoptionRequestSave.reasonsForAdoption.whereWillThePetBe.length <
        10
      ) {
        this.validation.whereWillThePetBe = false;
        this.error.whereWillThePetBe =
          "La respuesta debe tener al menos 10 caracteres";
      } else if (
        this.adoptionRequestSave.reasonsForAdoption.whereWillThePetBe.length >
        100
      ) {
        this.validation.whereWillThePetBe = false;
        this.error.whereWillThePetBe =
          "La respuesta debe tener menos de 100 caracteres";
      } else if (
        this.adoptionRequestSave.reasonsForAdoption.whereWillThePetBe === null
      ) {
        this.validation.whereWillThePetBe = false;
        this.error.whereWillThePetBe = "Este campo es requerido";
      } else {
        this.validation.whereWillThePetBe = true;
        this.error.whereWillThePetBe = null;
      }
    },
    validatePeopleAgreeToAdopt() {
      if (
        !regex.test(
          this.adoptionRequestSave.reasonsForAdoption.peopleAgreeToAdopt
        )
      ) {
        this.validation.peopleAgreeToAdopt = false;
        this.error.peopleAgreeToAdopt =
          "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (
        this.adoptionRequestSave.reasonsForAdoption.peopleAgreeToAdopt.length <
        10
      ) {
        this.validation.peopleAgreeToAdopt = false;
        this.error.peopleAgreeToAdopt =
          "La respuesta debe tener al menos 10 caracteres";
      } else if (
        this.adoptionRequestSave.reasonsForAdoption.peopleAgreeToAdopt.length >
        100
      ) {
        this.validation.peopleAgreeToAdopt = false;
        this.error.peopleAgreeToAdopt =
          "La respuesta debe tener menos de 100 caracteres";
      } else if (
        this.adoptionRequestSave.reasonsForAdoption.peopleAgreeToAdopt === null
      ) {
        this.validation.peopleAgreeToAdopt = false;
        this.error.peopleAgreeToAdopt = "Este campo es requerido";
      } else {
        this.validation.peopleAgreeToAdopt = true;
        this.error.peopleAgreeToAdopt = null;
      }
    },
    validateAdditionalComments() {
      if (
        !regex.test(
          this.adoptionRequestSave.reasonsForAdoption.additionalComments
        )
      ) {
        this.validation.additionalComments = false;
        this.error.additionalComments =
          "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (
        this.adoptionRequestSave.reasonsForAdoption.additionalComments.length <
        10
      ) {
        this.validation.additionalComments = false;
        this.error.additionalComments =
          "La respuesta debe tener al menos 10 caracteres";
      } else if (
        this.adoptionRequestSave.reasonsForAdoption.additionalComments.length >
        100
      ) {
        this.validation.additionalComments = false;
        this.error.additionalComments =
          "La respuesta debe tener menos de 100 caracteres";
      } else if (
        this.adoptionRequestSave.reasonsForAdoption.additionalComments === null
      ) {
        this.validation.additionalComments = false;
        this.error.additionalComments = "Este campo es requerido";
      } else {
        this.validation.additionalComments = true;
        this.error.additionalComments = null;
      }
    },
    validateLastPet() {
      if (
        !regex.test(this.adoptionRequestSave.previousExperiencieDto.lastPet)
      ) {
        this.validation.lastPet = false;
        this.error.lastPet = "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (
        this.adoptionRequestSave.previousExperiencieDto.lastPet.length < 10
      ) {
        this.validation.lastPet = false;
        this.error.lastPet = "La respuesta debe tener al menos 10 caracteres";
      } else if (
        this.adoptionRequestSave.previousExperiencieDto.lastPet.length > 100
      ) {
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
      if (
        !regex.test(
          this.adoptionRequestSave.previousExperiencieDto
            .whatDidYouDoWhenThePetGotSick
        )
      ) {
        this.validation.whatDidYouDoWhenThePetGotSick = false;
        this.error.whatDidYouDoWhenThePetGotSick =
          "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (
        this.adoptionRequestSave.previousExperiencieDto
          .whatDidYouDoWhenThePetGotSick.length < 10
      ) {
        this.validation.whatDidYouDoWhenThePetGotSick = false;
        this.error.whatDidYouDoWhenThePetGotSick =
          "La respuesta debe tener al menos 10 caracteres";
      } else if (
        this.adoptionRequestSave.previousExperiencieDto
          .whatDidYouDoWhenThePetGotSick.length > 100
      ) {
        this.validation.whatDidYouDoWhenThePetGotSick = false;
        this.error.whatDidYouDoWhenThePetGotSick =
          "La respuesta debe tener menos de 100 caracteres";
      } else if (
        this.adoptionRequestSave.previousExperiencieDto
          .whatDidYouDoWhenThePetGotSick === null
      ) {
        this.validation.whatDidYouDoWhenThePetGotSick = false;
        this.error.whatDidYouDoWhenThePetGotSick = "Este campo es requerido";
      } else {
        this.validation.whatDidYouDoWhenThePetGotSick = true;
        this.error.whatDidYouDoWhenThePetGotSick = null;
      }
    },
    validateWhatKindOfPetsHaveYouHadBefore() {
      if (
        !regex.test(
          this.adoptionRequestSave.previousExperiencieDto
            .whatKindOfPetsHaveYouHadBefore
        )
      ) {
        this.validation.whatKindOfPetsHaveYouHadBefore = false;
        this.error.whatKindOfPetsHaveYouHadBefore =
          "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (
        this.adoptionRequestSave.previousExperiencieDto
          .whatKindOfPetsHaveYouHadBefore.length < 10
      ) {
        this.validation.whatKindOfPetsHaveYouHadBefore = false;
        this.error.whatKindOfPetsHaveYouHadBefore =
          "La respuesta debe tener al menos 10 caracteres";
      } else if (
        this.adoptionRequestSave.previousExperiencieDto
          .whatKindOfPetsHaveYouHadBefore.length > 100
      ) {
        this.validation.whatKindOfPetsHaveYouHadBefore = false;
        this.error.whatKindOfPetsHaveYouHadBefore =
          "La respuesta debe tener menos de 100 caracteres";
      } else if (
        this.adoptionRequestSave.previousExperiencieDto
          .whatKindOfPetsHaveYouHadBefore === null
      ) {
        this.validation.whatKindOfPetsHaveYouHadBefore = false;
        this.error.whatKindOfPetsHaveYouHadBefore = "Este campo es requerido";
      } else {
        this.validation.whatKindOfPetsHaveYouHadBefore = true;
        this.error.whatKindOfPetsHaveYouHadBefore = null;
      }
    },
    validateWhatMemoriesDoYouHaveWithYourPet() {
      if (
        !regex.test(
          this.adoptionRequestSave.previousExperiencieDto
            .whatMemoriesDoYouHaveWithYourPet
        )
      ) {
        this.validation.whatMemoriesDoYouHaveWithYourPet = false;
        this.error.whatMemoriesDoYouHaveWithYourPet =
          "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (
        this.adoptionRequestSave.previousExperiencieDto
          .whatMemoriesDoYouHaveWithYourPet.length < 10
      ) {
        this.validation.whatMemoriesDoYouHaveWithYourPet = false;
        this.error.whatMemoriesDoYouHaveWithYourPet =
          "La respuesta debe tener al menos 10 caracteres";
      } else if (
        this.adoptionRequestSave.previousExperiencieDto
          .whatMemoriesDoYouHaveWithYourPet.length > 100
      ) {
        this.validation.whatMemoriesDoYouHaveWithYourPet = false;
        this.error.whatMemoriesDoYouHaveWithYourPet =
          "La respuesta debe tener menos de 100 caracteres";
      } else if (
        this.adoptionRequestSave.previousExperiencieDto
          .whatMemoriesDoYouHaveWithYourPet === null
      ) {
        this.validation.whatMemoriesDoYouHaveWithYourPet = false;
        this.error.whatMemoriesDoYouHaveWithYourPet = "Este campo es requerido";
      } else {
        this.validation.whatMemoriesDoYouHaveWithYourPet = true;
        this.error.whatMemoriesDoYouHaveWithYourPet = null;
      }
    },
    validateAdditionalInfo() {
      if (!regex.test(this.adoptionRequestSave.additional_information)) {
        this.validation.additionalInfo = false;
        this.error.additionalInfo = "Solo se aceptan letras[Aa-Zz] y puntos[.]";
      } else if (this.adoptionRequestSave.additional_information.length < 10) {
        this.validation.additionalInfo = false;
        this.error.additionalInfo =
          "La respuesta debe tener al menos 10 caracteres";
      } else if (this.adoptionRequestSave.additional_information.length > 100) {
        this.validation.additionalInfo = false;
        this.error.additionalInfo =
          "La respuesta debe tener menos de 100 caracteres";
      } else if (this.adoptionRequestSave.additional_information === null) {
        this.validation.additionalInfo = false;
        this.error.additionalInfo = "Este campo es requerido";
      } else {
        this.validation.additionalInfo = true;
        this.error.additionalInfo = null;
      }
    },
    async submitAdoptionForm() {
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
        .then(async (result) => {
          // Marca la función como asíncrona aquí
          this.imagesToHomePet();
          if (result.isConfirmed) {
            try {
              const response = await instance.post("/adoption/", {
                user: localStorage.getItem("userId"),
                pet: localStorage.getItem("petId"),
                reasonsForAdoption: {
                  peopleAgreeToAdopt:
                    this.adoptionRequestSave.reasonsForAdoption
                      .peopleAgreeToAdopt,
                  haveHadPets:
                    this.adoptionRequestSave.reasonsForAdoption.haveHadPets,
                  whereWillThePetBe:
                    this.adoptionRequestSave.reasonsForAdoption
                      .whereWillThePetBe,
                  additionalComments:
                    this.adoptionRequestSave.reasonsForAdoption
                      .additionalComments,
                },
                previousExperiencieDto: {
                  whatDidYouDoWhenThePetGotSick:
                    this.adoptionRequestSave.previousExperiencieDto
                      .whatDidYouDoWhenThePetGotSick,
                  whatKindOfPetsHaveYouHadBefore:
                    this.adoptionRequestSave.previousExperiencieDto
                      .whatKindOfPetsHaveYouHadBefore,
                  whatMemoriesDoYouHaveWithYourPet:
                    this.adoptionRequestSave.previousExperiencieDto
                      .whatMemoriesDoYouHaveWithYourPet,
                  lastPet:
                    this.adoptionRequestSave.previousExperiencieDto.lastPet,
                },
                additional_information:
                  this.adoptionRequestSave.additional_information,
                imageAdoption: this.adoptionRequestSave.imageAdoption,
              });
              swal.fire({
              title: "Espera un momento...",
              text: "Estamos enviando tu solicitud de adopción",
              imageUrl: gatoWalkingGif,
              timer: 5000,
              timerProgressBar: true,
              imageWidth: 160, // Ancho de la imagen
              imageHeight: 160, // Altura de la imagen
              showConfirmButton: false,
            }).finally(() => {
              if (!response.data.error) {
                swal.fire({
                  title: "Solicitud de adopción enviada",
                  text: "Tu solicitud de adopción ha sido enviada con éxito",
                  icon: "success",
                  confirmButtonColor: "#3085d6",
                  confirmButtonText: "Aceptar",
                  timer: 2000,
                }).finally(() => {
                  this.cleanInfo();
                  this.$router.push("/myAplicationAdoption");
                });
              }
            })
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
                  Msjerror =
                    "Ups! la respuesta debe tener entre 10 y 100 caracteres";
                  break;
                case "INVALID_ROLE":
                  Msjerror =
                    "Ups! no tienes permisos para realizar esta acción";
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
                  Msjerror =
                    "Ups! algo salió mal, por favor vuelve a intentarlo";
                  break;
              }
              swal.fire({
                title: "Error al enviar la solicitud de adopción",
                text: Msjerror,
                icon: "error",
                confirmButtonColor: "#3085d6",
                confirmButtonText: "Aceptar",
              });
            }
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
        this.adoptionRequestSave.reasonsForAdoption.haveHadPets === null ||
        this.adoptionRequestSave.reasonsForAdoption.whereWillThePetBe ===
          null ||
        this.adoptionRequestSave.reasonsForAdoption.peopleAgreeToAdopt ===
          null ||
        this.adoptionRequestSave.previousExperiencieDto.lastPet === null ||
        this.adoptionRequestSave.previousExperiencieDto
          .whatDidYouDoWhenThePetGotSick === null ||
        this.adoptionRequestSave.previousExperiencieDto
          .whatKindOfPetsHaveYouHadBefore === null ||
        this.adoptionRequestSave.previousExperiencieDto
          .whatMemoriesDoYouHaveWithYourPet === null
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
      console.log("makeToast", text);
      const Toast = swal.mixin({
        toast: true,
        position: "top-end",
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.onmouseenter = Swal.stopTimer;
          toast.onmouseleave = Swal.resumeTimer;
        },
      });

      Toast.fire({
        icon: "warning",
        title: `ADVERTENCIA: ${text}`,
      });
    },
    imagesToHomePet() {
      this.adoptionRequestSave.imageAdoption.push(
        this.adoptionInfo.placeToSleep
      );
      this.adoptionRequestSave.imageAdoption.push(
        this.adoptionInfo.placeToLive
      );
      this.adoptionRequestSave.imageAdoption.push(
        this.adoptionInfo.placeToPlay
      );
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

.rounded-button {
  border-radius: 20px;
  width: 20%;
}

.tam-img {
  max-width: 200px;
  height: 128px;
}

.card-shadow {
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}
</style>
