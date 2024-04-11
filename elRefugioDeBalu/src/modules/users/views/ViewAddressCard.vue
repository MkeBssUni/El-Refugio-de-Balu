<template>
  <b-col cols="12" class="px-2 px-sm-4 px-xl-5 mt-4">
    <b-row>
      <b-col cols="10" md="8" lg="6">
        <b-card
          bg-variant="card-header-blue"
          class="py-2 form-card-shadow relative-position form-card-title"
          no-body
        >
          <div class="d-flex align-items-center ms-3 ms-md-4">
            <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
            <h4 class="mb-0 mt-1">Domicilio Actual</h4>
          </div>
        </b-card>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="12">
        <b-card
          bg-variant="card-content-blue"
          class="form-card-shadow form-card-content"
        >
          <b-card-body>
            <b-row>
              <b-col cols="12" class="px-2 px-sm-4 px-xl-5 mb-sm-5">
                <b-row>
                  <b-col cols="12" md="4">
                    <b-form-group label="País" label-align="left">
                      <b-input readonly v-model="address.country"></b-input>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" md="4">
                    <b-form-group label="Calle" label-align="left">
                      <b-input readonly v-model="address.street"></b-input>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" md="4">
                    <b-form-group label="Colonia" label-align="left">
                      <b-input readonly v-model="address.colony"></b-input>
                    </b-form-group>
                  </b-col>
                </b-row>
                <b-row>
                  <b-col cols="12" md="4">
                    <b-form-group label="Ciudad" label-align="left">
                      <b-input readonly v-model="address.city"></b-input>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" md="4">
                    <b-form-group label="Estado" label-align="left">
                      <b-input readonly v-model="address.state"></b-input>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" md="4">
                    <b-form-group
                      label="Código Postal"
                      label-align="left"
                      :state="postalCodeValidation"
                    >
                      <b-input readonly v-model="address.postalCode"></b-input>
                      <b-form-invalid-feedback :state="postalCodeValidation">
                        Codigo postal invalido
                      </b-form-invalid-feedback>
                    </b-form-group>
                  </b-col>
                </b-row>
                <b-row>
                  <b-col cols="12">
                    <b-form-group
                      label="Referencia de dirección"
                      label-align="left"
                    >
                      <b-form-textarea
                        id="textarea"
                         v-model="address.addressReference"
                        readonly
                        rows="3"
                        max-rows="6"
                      ></b-form-textarea>
                    </b-form-group>
                  </b-col>
                </b-row>
                <b-row>
                  <b-col cols="12" md="4">
                    <b-form-group label="Número exterior" label-align="left">
                      <b-input
                        readonly
                        v-model="address.exteriorNumber"
                      ></b-input>
                      <b-form-invalid-feedback>
                        Ingresa solo números
                      </b-form-invalid-feedback>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" md="4">
                    <b-form-group label="Número interior" label-align="left">
                      <b-input
                        readonly
                        v-model="address.interiorNumber"
                      ></b-input>
                    </b-form-group>
                  </b-col>
                </b-row>
                <div class="d-flex justify-content-end mt-3">
                  <b-button
                    variant="outline-light"
                    class="d-flex align-items-center"
                    @click="ModifyAddress"
                  >
                    Modificar
                      <b-icon icon="pencil" class="ms-2" font-scale="1.3"></b-icon>
                  </b-button>
                </div>
              </b-col>
            </b-row>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-col>
</template>

<script>
import swal from "sweetalert2";
import instance from "../../../config/axios";
export default {
  name: "ViewAddressCard",
  data() {
    return {
      address: {
        country: "",
        street: "",
        colony: "",
        city: "",
        state: "",
        postalCode: "",
        addressReference: "",
        exteriorNumber: "",
        interiorNumber: "",
        id: "",
      },
      postalCodeValidation: null,
      postalCodeValidationMessage: "",
      geetAddresDto: {
        userId: localStorage.getItem("userId"),
      },
    };
  },
  mounted() {
    this.getAddressDetails();
  },
  computed: {
    domicilioCompleto() {
      return (
        this.country &&
        this.street &&
        this.colony &&
        this.city &&
        this.state &&
        this.postalCode &&
        this.addressReference &&
        this.exteriorNumber
      );
    },
  },

  methods: {
    async getAddressDetails() {
      this.loading = true;
      try {
        const response = await instance.post(
          "/address/details",
          this.geetAddresDto
        );
        if (!response.data.error) {
          this.address = {
            country: response.data.data.country,
            street: response.data.data.street,
            colony: response.data.data.colony,
            city: response.data.data.city,
            state: response.data.data.state,
            postalCode: response.data.data.postalCode,
            addressReference: response.data.data.addressReference,
            exteriorNumber: response.data.data.exteriorNumber,
            interiorNumber: response.data.data.interiorNumber,
            id:response.data.data.id
          };
        } else {
          swal.fire({
            icon: "error",
            title: "Error",
            text: "No se pudo obtener la dirección del usuario",
          });
        }
      } catch (error) {
        console.error(error);
        swal.fire({
          icon: "error",
          title: "Error",
          text: "No se pudo obtener la dirección del usuario",
        });
      } finally {
        this.loading = false;
      }
    },
    ModifyAddress(){
      const addressModify=this.address
      this.$emit("ModifyAddress",addressModify);
    }
  },
};
</script>

<style>
.encabezadoColorform {
  width: 50%;
  background-color: #4db8c0;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(255, 255, 255, 0.05) 0px 0.25em 1em;
  position: relative;
  z-index: 2;
  color: #ffffff;
}

.contentform {
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
  margin-top: -30px;
  position: relative;
  z-index: 1;
  border-radius: 20px;
  background-color: #ffffff;
}
.card.encabezadoColorform {
  margin: 0 auto;
  width: 95%;
  height: 60px;
  padding: 20px;
  border-radius: 60px;
  text-align: center;
}
</style>

