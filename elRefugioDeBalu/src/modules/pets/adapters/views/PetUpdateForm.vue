<template>
    <b-container fluid>
        <b-row>
            <Encabezado color="#118A95" :imagenUrl="require('@/assets/imgs/balueditform.png')"
                titulo="Editar la información de la mascota" />
        </b-row>
        <b-row class="px-2 my-2">
            <GeneralInformationCard ref="generalInformationCard" :pet="pet" />
            <MedicalRecordCard ref="medicalRecordCard" />
            <AdditionalInformationCard ref="additionalInformationCard" />
        </b-row>
        <b-col cols="12" class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
            <b-row class="px-5 px-sm-0 d-flex justify-content-end">
                <b-col cols="12" sm="6" md="5" lg="4" xl="3">
                    <b-button variant="outline-dark-secondary-blue" @click="showConfirmation()"
                        class="d-flex align-items-center justify-content-between w-100">
                        <span class="me-2">Editar</span>
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
import Encabezado from "../../../../views/components/Encabezado.vue";
import Swal from 'sweetalert2';
import instance from '../../../../config/axios';
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import sizes from '../../../../kernel/data/sizes';
import ageUnits from '../../../../kernel/data/ageUnits';
import weightUnits from '../../../../kernel/data/weightUnits';
import lifeStages from '../../../../kernel/data/lifeStages';
import genders from '../../../../kernel/data/genders';

export default {
    data() {
        return {
            pet: {},
            isValidGeneralInformation: false,
            isValidAdditionalInformation: false,
        };
    },
    methods: {
        goBack() {
            this.$router.go(-1);
        },
        mapSize() {
            let size = String(this.pet.size).toLowerCase();
            return sizes.find(s => s.value === size).value;
        },
        mapAgeUnit() {
            let ageUnit = String(this.pet.ageUnit).toLowerCase();
            return ageUnits.find(a => a.value === ageUnit).value;
        },
        mapAgeUnit() {
            let ageUnit = String(this.pet.ageUnit).toLowerCase();
            return ageUnits.find(a => a.value === ageUnit).value;
        },
        mapWeightUnit() {
            let weightUnit = String(this.pet.weightUnit).toLowerCase();
            return weightUnits.find(w => w.value === weightUnit).value;
        },
        mapLifeStage() {
            let lifeStage = String(this.pet.lifeStage).toLowerCase();
            return lifeStages.find(l => l.value === lifeStage).value;
        },
        mapGender() {
            let gender = String(this.pet.gender).toLowerCase();
            return genders.find(g => g.value === gender).value;
        },
        mapCategory() {
            console.log(this.$refs.generalInformationCard.categories)
            for (let i = 0; i < this.$refs.generalInformationCard.categories.length; i++) {
                if (this.$refs.generalInformationCard.categories[i].name === this.pet.category) {
                    return this.$refs.generalInformationCard.categories[i].id;
                }
            }
        },
        async getDetails() {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Estamos cargando los detalles de la mascota, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                const response = await instance.post(`/pet/details`, { id: this.petId });
                this.pet = response.data.data;
                this.$refs.generalInformationCard.form.mainImage = this.pet.mainImage;
                this.$refs.generalInformationCard.form.additionalImages = this.pet.images;
                this.$refs.generalInformationCard.form.name = this.pet.name;
                this.$refs.generalInformationCard.form.breed = this.pet.breed;
                this.$refs.generalInformationCard.form.size = this.mapSize();
                this.$refs.generalInformationCard.form.age = this.pet.age;
                this.$refs.generalInformationCard.form.ageUnit = this.mapAgeUnit();
                this.$refs.generalInformationCard.form.weight = this.pet.weight;
                this.$refs.generalInformationCard.form.weightUnit = this.mapWeightUnit();
                this.$refs.generalInformationCard.form.lifeStage = this.mapLifeStage();
                this.$refs.generalInformationCard.form.gender = this.mapGender();
                this.$refs.generalInformationCard.form.category = this.mapCategory();
                this.$refs.medicalRecordCard.form.vaccinated = this.pet.vaccinated;
                this.$refs.medicalRecordCard.form.dewormed = this.pet.dewormed;
                this.$refs.medicalRecordCard.form.sterilized = this.pet.sterilized;
                this.$refs.medicalRecordCard.form.microchip = this.pet.microchip;
                this.$refs.medicalRecordCard.form.observations = this.pet.observations;
                this.$refs.medicalRecordCard.form.diseases = this.pet.diseases;
                this.$refs.medicalRecordCard.form.allergies = this.pet.allergies;
                this.$refs.additionalInformationCard.form.description = this.pet.description;
                this.$refs.additionalInformationCard.form.characteristics = this.pet.characteristics;
                this.$refs.additionalInformationCard.form.specialCares = this.pet.specialCares;
                Swal.close();
            } catch (error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al cargar los detalles de la mascota',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    this.goBack();
                })
            }
        },
        showConfirmation() {
            this.$refs.generalInformationCard.validateForm() ? this.isValidGeneralInformation = true : this.isValidGeneralInformation = false;
            this.$refs.additionalInformationCard.validateForm() ? this.isValidAdditionalInformation = true : this.isValidAdditionalInformation = false;
            if (this.isValidGeneralInformation && this.isValidAdditionalInformation) {
                Swal.fire({
                    title: '¿Estás seguro?',
                    text: 'Estás a punto de modificar la información de tu mascota, ¿deseas continuar?',
                    icon: 'question',
                    iconColor: '#118A95',
                    showCancelButton: true,
                    confirmButtonColor: '#118A95',
                    cancelButtonColor: '#A93D3D',
                    confirmButtonText: 'Continuar',
                    cancelButtonText: 'Cancelar',
                }).then((result) => {
                    if (result.isConfirmed) {
                        this.updatePet();
                    }
                })
            }
        },
        async updatePet() {
            try {
                Swal.fire({
                    title: 'Modificando mascota...',
                    text: 'Estamos modificando la información de tu mascota, por favor espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                await instance.post('/pet/update', {
                    id: localStorage.getItem('petId'),
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
                    title: 'Mascota modificada',
                    text: 'La información de tu mascota ha sido modificada exitosamente',
                    icon: 'success',
                    iconColor: '#53A93D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false,
                }).then(() => {
                    this.goBack();
                })
            } catch (error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al modificar la información de tu mascota',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false,
                })
            }
        }
    },
    mounted() {
        if (localStorage.getItem("petId")) {
            this.petId = localStorage.getItem("petId");            
            this.$refs.generalInformationCard.getCategories();            
        } else {
            this.goBack();
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