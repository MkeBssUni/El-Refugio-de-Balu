<template>
    <b-container fluid>
        <b-row>
            <Encabezado color="#118A95" :imagenUrl="require('@/assets/imgs/mascotas-formulario.png')"
                titulo="Dar mascota en adopción" />
        </b-row>
        <b-row class="px-2 my-2">
            <GeneralInformationCard ref="generalInformationCard" />
            <MedicalRecordCard ref="medicalRecordCard" />
            <AdditionalInformationCard ref="additionalInformationCard" />
        </b-row>
        <b-col cols="12" class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
            <b-row class="px-5 px-sm-0 d-flex justify-content-end">
                <b-col cols="12" sm="6" md="5" lg="4" xl="3">
                    <b-button variant="outline-dark-secondary-blue" @click="savePet()"
                        class="d-flex align-items-center justify-content-between w-100">
                        <span class="me-2">Publicar</span>
                        <b-icon icon="arrow-up-right-circle" font-scale="1.3"></b-icon>
                    </b-button>
                </b-col>
            </b-row>
        </b-col>
    </b-container>
</template>

<script>
import GeneralInformationCard from '../components/GeneralInformationCard.vue';
import MedicalRecordCard from '../components/MedicalRecordCard.vue';
import AdditionalInformationCard from '../components/AdditionalInformationCard.vue';
import { isInvalidName } from '../../../../kernel/validations';
import Encabezado from "../../../../views/components/Encabezado.vue";
import Swal from 'sweetalert2';
import instance from '../../../../config/axios';
import { encrypt } from '../../../../kernel/hashFunctions';
export default {
    data() {
        return {
            isValidGeneralInformation: false,
            isValidAdditionalInformation: false,
        };
    },
    methods: {
        savePet() {
            this.$refs.generalInformationCard.validateForm() ? this.isValidGeneralInformation = true : this.isValidGeneralInformation = false;
            this.$refs.additionalInformationCard.validateForm() ? this.isValidAdditionalInformation = true : this.isValidAdditionalInformation = false;
            if (this.isValidGeneralInformation && this.isValidAdditionalInformation) {  
                console.log("todo bien")
            }
        }
    },
    components: {
        Encabezado,
        GeneralInformationCard,
        MedicalRecordCard,
        AdditionalInformationCard,
    },
};
</script>

<style scoped></style>