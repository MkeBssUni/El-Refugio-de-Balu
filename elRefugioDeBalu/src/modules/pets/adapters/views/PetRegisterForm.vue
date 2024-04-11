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
                    <b-button variant="outline-dark-secondary-blue" @click="showConfirmation()"
                        class="d-flex align-items-center justify-content-between w-100">
                        <span class="me-2">Publicar</span>
                        <b-icon icon="arrow-up-right-circle" font-scale="1.3"></b-icon>
                    </b-button>
                </b-col>                
                <b-col cols="12" sm="6" md="5" lg="4" xl="3" class="mt-3 mt-sm-0">
                    <b-button variant="outline-danger" @click="goBack()"
                        class="d-flex align-items-center justify-content-between w-100">
                        <span class="me-2">Cancelar</span>
                        <b-icon icon="x-circle" font-scale="1.3"></b-icon>
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
import Encabezado from "../../../../views/components/Encabezado.vue";
import Swal from 'sweetalert2';
import instance from '../../../../config/axios';
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";

export default {
    data() {
        return {
            isValidGeneralInformation: false,
            isValidAdditionalInformation: false,
        };
    },
    methods: {
        showConfirmation() {
            this.$refs.generalInformationCard.validateForm() ? this.isValidGeneralInformation = true : this.isValidGeneralInformation = false;
            this.$refs.additionalInformationCard.validateForm() ? this.isValidAdditionalInformation = true : this.isValidAdditionalInformation = false;
            if (this.isValidGeneralInformation && this.isValidAdditionalInformation) {
                Swal.fire({
                    title: '¿Estás seguro?',
                    text: 'Cuando tu mascota sea publicada, esperará la aprobación o la solicitud de cambios por parte de un moderador para que pueda ser vista por sus futuros posibles adoptantes, ¿deseas continuar?',
                    icon: 'question',
                    iconColor: '#118A95',
                    showCancelButton: true,
                    confirmButtonColor: '#118A95',
                    cancelButtonColor: '#A93D3D',
                    confirmButtonText: 'Continuar',
                    cancelButtonText: 'Cancelar',
                }).then((result) => {
                    if (result.isConfirmed) {
                        this.savePet();
                    }
                })
            }
        },
        convertImageToBase64(image) {
            try {
                return new Promise((resolve, reject) => {
                    const reader = new FileReader();
                    reader.readAsDataURL(image);
                    reader.onload = () => resolve(reader.result);
                    reader.onerror = error => reject(error);
                })
            } catch (error) {
                Swal.fire({
                    icon: 'error',
                    title: '¡Error!',
                    text: 'Ocurrió un error al cargar la imagen, por favor intenta de nuevo',
                    toast: true,
                    position: 'top-end',
                    timer: 3000,
                    showCancelButton: false,
                    showConfirmButton: false,
                    timerProgressBar: true,
                });
            }
        },
        async savePet() {
            try {
                Swal.fire({
                    title: 'Publicando mascota...',
                    text: 'Estamos publicando tu mascota, por favor espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                await instance.post('/pet/save', {
                    name: this.$refs.generalInformationCard.form.name,
                    gender: this.$refs.generalInformationCard.form.gender,
                    breed: this.$refs.generalInformationCard.form.breed,
                    age: this.$refs.generalInformationCard.form.age,
                    ageUnit: this.$refs.generalInformationCard.form.ageUnit,
                    lifeStage: this.$refs.generalInformationCard.form.lifeStage,
                    weight: this.$refs.generalInformationCard.form.weight,
                    weightUnit: this.$refs.generalInformationCard.form.weightUnit,
                    size: this.$refs.generalInformationCard.form.size,
                    description: this.$refs.additionalInformationCard.form.description,
                    characteristics: this.$refs.additionalInformationCard.form.characteristics,
                    specialCares: this.$refs.additionalInformationCard.form.specialCares ? this.$refs.additionalInformationCard.form.specialCares : null,
                    mainImage: this.$refs.generalInformationCard.form.mainImage,
                    images: this.$refs.generalInformationCard.form.additionalImages ? this.$refs.generalInformationCard.form.additionalImages : null,
                    vaccinated: this.$refs.medicalRecordCard.form.vaccinated,
                    dewormed: this.$refs.medicalRecordCard.form.dewormed,
                    sterilized: this.$refs.medicalRecordCard.form.sterilized,
                    microchip: this.$refs.medicalRecordCard.form.microchip,
                    observations: this.$refs.medicalRecordCard.form.observations,
                    diseases: this.$refs.medicalRecordCard.form.diseases ? this.$refs.medicalRecordCard.form.diseases : null,
                    allergies: this.$refs.medicalRecordCard.form.allergies ? this.$refs.medicalRecordCard.form.allergies : null,
                    category: this.$refs.generalInformationCard.form.category,
                    owner: localStorage.getItem('userId')
                })
                Swal.fire({
                    title: 'Mascota publicada',
                    text: 'Tu mascota ha sido publicada con éxito, próximamente recibirás un correo con la respuesta de un moderador',
                    icon: 'success',
                    iconColor: '#53A93D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false,
                }).then(() => {
                    this.$router.push({ name: 'myPets' })
                })
            } catch (error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al publicar la mascota',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false,
                })
            }
        },
        goBack() {
            this.$router.go(-1);
        },
    },
    mounted() {
        this.$refs.generalInformationCard.getCategories();
    },
    components: {
        Encabezado,
        GeneralInformationCard,
        MedicalRecordCard,
        AdditionalInformationCard,
    },
};
</script>