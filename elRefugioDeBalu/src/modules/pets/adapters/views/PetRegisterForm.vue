<template>
    <b-container fluid>
        <b-row>
            <Encabezado color="#118A95" :imagenUrl="require('@/assets/imgs/mascotas-formulario.png')"
                titulo="Dar mascota en adopción" />
        </b-row>
        <b-row class="px-2 my-2">
            <GeneralInformationCard ref="generalInformationCard" />
            <MedicalRecordCard />
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
import { isInvalidName } from '../../../../kernel/validations';
import Encabezado from "../../../../views/components/Encabezado.vue";
import Swal from 'sweetalert2';
import instance from '../../../../config/axios';
import { encrypt } from '../../../../kernel/hashFunctions';
export default {
    data() {
        return {
            isValidGeneralInformationForm: false,
            tempDisease: "",
            tempAllergy: "",
            tempCharacteristic: "",
            tempCare: "",
            form: {
                
            },
            formPet: {},
            isValidForm: false,
            showErrors: {
                name: false,
                species: false,
                breed: false,
                size: false,
                age: false,
                ageUnit: false,
                weight: false,
                weightType: false,
                gender: false,
                tempDisease: false,
                tempAllergy: false,
                comments: false,
                characteristics: false,
                stage: false,
                description: false,
            },
            errorsMessages: {
                name: '',
                species: '',
                breed: '',
                size: '',
                age: '',
                ageUnit: '',
                weight: '',
                weightType: '',
                gender: '',
                tempDisease: '',
                tempAllergy: '',
                comments: '',
                characteristics: '',
                stage: '',
                description: '',
            },
            categories: [
                { id: 1, name: "Perro" },
                { id: 2, name: "Gato" },
                { id: 3, name: "Conejo" },
                { id: 4, name: "Pájaro" },
            ],
            sizes: [
                { value: "SMALL", text: "Pequeño" },
                { value: "MEDIUM", text: "Mediano" },
                { value: "BIG", text: "Grande" },
            ],
            ageUnits: [
                { value: "MONTHS", text: "Meses" },
                { value: "YEARS", text: "Años" },
            ],
            stages: [
                { value: "BABY", text: "Cachorro" },
                { value: "YOUNG", text: "Joven" },
                { value: "ADULT", text: "Adulto" },
                { value: "SENIO", text: "Senior" },
            ],
            weightTypes: [
                { value: "KILOGRAMS", text: "Kilogramos" },
                { value: "GRAMS", text: "Gramos" },
            ],
            sexes: [
                { value: "MALE", text: "Macho" },
                { value: "FEMALE", text: "Hembra" },
            ],
        };
    },
    methods: {
        addDisease() {
            if (this.tempDisease) {
                this.form.diseases.push(this.tempDisease);
                this.tempDisease = '';
            }
        },
        addAllergy() {
            if (this.tempAllergy) {
                this.form.allergies.push(this.tempAllergy);
                this.tempAllergy = '';
            }
        },
        removeDisease(index) {
            this.form.diseases.splice(index, 1);
        },
        removeAllergy(index) {
            this.form.allergies.splice(index, 1);
        },
        addCharacteristic() {
            if (this.tempCharacteristic) {
                this.form.characteristics.push(this.tempCharacteristic);
                this.tempCharacteristic = '';
                this.validateInput('characteristics');
            }
        },
        addCare() {
            if (this.tempCare) {
                this.form.care.push(this.tempCare);
                this.tempCare = '';
            }
        },
        removeCharacteristic(index) {
            this.form.characteristics.splice(index, 1);
            this.validateInput('characteristics');
        },
        removeCare(index) {
            this.form.care.splice(index, 1);
        },
        goBack() {
            this.$router.go(-1);
        },
        validateInput(validateInput) {
            console.log(this.form)
            let input;
            switch (validateInput) {
                case 'name':
                    input = document.getElementById('name');
                    if (this.form.name == '') {
                        this.showErrors.name = true;
                        this.errorsMessages.name = 'El nombre de la mascota es requerido';
                        input.classList.add('is-invalid');
                    } else if (isInvalidName(this.form.name)) {
                        this.showErrors.name = true;
                        this.errorsMessages.name = 'El nombre de la mascota no es válido, ingresa solo letras';
                        input.classList.add('is-invalid');
                    } else {
                        this.showErrors.name = false;
                        this.errorsMessages.name = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;

                case 'species':
                    input = document.getElementById('category');
                    if (this.form.category == 0) {
                        this.showErrors.species = true;
                        this.errorsMessages.species = 'La especie de la mascota es requerida';
                        input.classList.add('is-invalid');
                    } else {
                        this.showErrors.species = false;
                        this.errorsMessages.species = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;

                case 'breed':
                    input = document.getElementById('breed');
                    if (this.form.breed == '') {
                        this.showErrors.breed = true;
                        this.errorsMessages.breed = 'La raza de la mascota es requerida';
                        input.classList.add('is-invalid');
                    } else if (isInvalidName(this.form.breed)) {
                        this.showErrors.breed = true;
                        this.errorsMessages.breed = 'La raza de la mascota no es válida, ingresa solo letras';
                        input.classList.add('is-invalid');
                    } else {
                        this.showErrors.breed = false;
                        this.errorsMessages.breed = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;
                case 'size':
                    input = document.getElementById('size');
                    if (this.form.size == 'default') {
                        this.showErrors.size = true;
                        this.errorsMessages.size = 'El tamaño de la mascota es requerido';
                        input.classList.add('is-invalid');
                    } else {
                        this.showErrors.size = false;
                        this.errorsMessages.size = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;
                case 'age':
                    input = document.getElementById('age');
                    if (this.form.age == null) {
                        this.showErrors.age = true;
                        this.errorsMessages.age = 'La edad de la mascota es requerida';
                        input.classList.add('is-invalid');
                    } else if (this.form.age < 0 || this.form.age == 0) {
                        this.showErrors.age = true;
                        this.errorsMessages.age = 'La edad de la mascota no es válida, ingresa un número mayor a 0';
                        input.classList.add('is-invalid');
                    } else {
                        this.showErrors.age = false;
                        this.errorsMessages.age = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;
                case 'ageUnit':
                    input = document.getElementById('ageUnit');
                    if (this.form.ageUnit == 'default') {
                        this.showErrors.ageUnit = true;
                        this.errorsMessages.ageUnit = 'La unidad de la edad de la mascota es requerida';
                        input.classList.add('is-invalid');
                    } else {
                        this.showErrors.ageUnit = false;
                        this.errorsMessages.ageUnit = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;
                case 'weight':
                    input = document.getElementById('weight');
                    if (this.form.weight == null) {
                        this.showErrors.weight = true;
                        this.errorsMessages.weight = 'El peso de la mascota es requerido';
                        input.classList.add('is-invalid');
                    } else if (this.form.weight < 0 || this.form.weight == 0) {
                        this.showErrors.weight = true;
                        this.errorsMessages.weight = 'El peso de la mascota no es válido, ingresa un número mayor a 0';
                        input.classList.add('is-invalid');
                    } else {
                        this.showErrors.weight = false;
                        this.errorsMessages.weight = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;

                case 'weightType':
                    input = document.getElementById('weightType');
                    if (this.form.weightType == 'default') {
                        this.showErrors.weightType = true;
                        this.errorsMessages.weightType = 'La unidad de peso de la mascota es requerida';
                        input.classList.add('is-invalid');
                    } else {
                        this.showErrors.weightType = false;
                        this.errorsMessages.weightType = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;

                case 'gender':
                    input = document.getElementById('sex')
                    if (this.form.sex == 'default') {
                        input.classList.add('is-invalid');
                        this.showErrors.gender = true;
                        this.errorsMessages.gender = 'El sexo de la mascota es requerido';
                    } else {
                        this.showErrors.gender = false;
                        this.errorsMessages.gender = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;

                case 'tempDisease':
                    input = document.getElementById('diseases');
                    if (this.tempDisease.length < 3) {
                        this.showErrors.tempDisease = true;
                        this.errorsMessages.tempDisease = 'La enfermedad de la mascota no es válida, ingresa al menos 3 caracteres';
                        input.classList.add('is-invalid');
                    } else {
                        this.showErrors.tempDisease = false;
                        this.errorsMessages.tempDisease = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;

                case 'tempAllergy':
                    input = document.getElementById('allergies');
                    if (this.tempAllergy.length < 3) {
                        this.showErrors.tempAllergy = true;
                        this.errorsMessages.tempAllergy = 'La alergia de la mascota no es válida, ingresa al menos 3 caracteres';
                        input.classList.add('is-invalid');
                    } else {
                        this.showErrors.tempAllergy = false;
                        this.errorsMessages.tempAllergy = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;

                case 'comments':
                    input = document.getElementById('comments');
                    if (this.form.comments.length < 50 || this.form.comments.length > 500) {
                        this.showErrors.comments = true;
                        this.errorsMessages.comments = 'Los comentarios adicionales de la mascota no son válidos, ingresa al menos 50 caracteres y máximo 500';
                        input.classList.add('is-invalid');
                    } else {
                        this.showErrors.comments = false;
                        this.errorsMessages.comments = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;

                case 'characteristics':
                    input = document.getElementById('characteristics');
                    if (this.form.characteristics.length < 2) {
                        this.showErrors.characteristics = true;
                        this.errorsMessages.characteristics = 'Las características de la mascota no son válidas, ingresa al menos 3 características';
                        input.classList.add('is-invalid');
                    } else if (this.tempCharacteristic.length < 3) {
                        this.showErrors.characteristics = true;
                        this.errorsMessages.characteristics = 'Las características de la mascota no son válidas, ingresa al menos 3 caracteres';
                        input.classList.add('is-invalid');
                    } else {
                        this.showErrors.characteristics = false;
                        this.errorsMessages.characteristics = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;

                case 'stage':
                    input = document.getElementById('stage');
                    if (this.form.stage == 'default') {
                        this.showErrors.stage = true;
                        this.errorsMessages.stage = 'La etapa de la mascota es requerida';
                        input.classList.add('is-invalid');
                    } else {
                        this.showErrors.stage = false;
                        this.errorsMessages.stage = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;

                case 'description':
                    input = document.getElementById('description');
                    if (this.form.description.length < 100 || this.form.description.length > 1500) {
                        this.showErrors.description = true;
                        this.errorsMessages.description = 'La descripción de la mascota no es válida, ingresa al menos 100 caracteres y máximo 1500';
                        input.classList.add('is-invalid');
                    } else {
                        this.showErrors.description = false;
                        this.errorsMessages.description = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;
            }
        },
        async prepareSave() {
            this.validateInput('name');
            this.validateInput('sex');
            this.validateInput('breed');
            this.validateInput('age');
            this.validateInput('ageUnit');
            this.validateInput('weight');
            this.validateInput('weightType');
            this.validateInput('stage');
            this.validateInput('size');
            this.validateInput('species');
            this.validateInput('description');
            console.log("image", this.form.mainImage)
            if (this.form.mainImage == null) {
                Swal.fire({
                    icon: 'error',
                    title: '¡Error!',
                    text: 'La imagen principal de la mascota es requerida',
                    toast: true,
                    position: 'bottom-end',
                    timer: 2000,
                    showCancelButton: false,
                    showConfirmButton: false,
                    timerProgressBar: true,
                });
                return;
            }
            if (this.showErrors.name || this.showErrors.species || this.showErrors.breed || this.showErrors.size || this.showErrors.age || this.showErrors.ageUnit || this.showErrors.weight || this.showErrors.weightType
                || this.showErrors.gender || this.showErrors.tempDisease || this.showErrors.tempAllergy || this.showErrors.comments || this.showErrors.characteristics || this.showErrors.stage || this.showErrors.description) {
                Swal.fire({
                    icon: 'error',
                    title: '¡Error!',
                    text: 'Hay errores en el formulario, por favor revisa los campos',
                    toast: true,
                    position: 'bottom-end',
                    timer: 2000,
                    showCancelButton: false,
                    showConfirmButton: false,
                    timerProgressBar: true,
                }).then(() => {
                    return;
                });
            }

            Swal.fire({
                title: '¿Estás seguro de publicar la mascota?',
                text: "La solicitud deberá ser aprobada por un moderador antes de ser publicada",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí, publicar',
                cancelButtonText: 'Cancelar',
            }).then(async (result) => {
                if (result.isConfirmed) {
                    Swal.showLoading();
                    const reader = new FileReader();
                    reader.readAsDataURL(this.form.mainImage);
                    reader.onloadend = () => {
                        this.formPet = {
                            name: this.form.name,
                            gender: this.form.sex,
                            breed: this.form.breed,
                            age: this.form.age,
                            ageUnit: this.form.ageUnit,
                            lifeStage: this.form.stage,
                            weight: this.form.weight,
                            weightUnit: this.form.weightType,
                            size: this.form.size,
                            description: this.form.description,
                            characteristics: this.form.characteristics,
                            specialCares: this.form.care,
                            mainImage: reader.result,
                            images: this.form.additionalImages,
                            vaccinated: this.form.isVaccinated,
                            sterilized: this.form.isSterilised,
                            dewormed: this.form.isDewormed,
                            microchip: this.form.microchip,
                            observations: this.form.comments,
                            diseases: this.form.diseases,
                            allergies: this.form.allergies,
                            category: this.form.category,
                            owner: localStorage.getItem('userId'),
                        }
                        this.addPet(this.formPet);
                    }
                }
            });
        },
        async addPet() {
            this.formPet.category = await encrypt(this.formPet.category);

            let response = await instance.post('/pet/save', this.formPet);
            console.log(response);
        },
        validateForm() {
            this.$refs.generalInformationCard.validateForm()
            console.log(this.$refs.generalInformationCard.validateForm())

        },
        savePet() {
            if (this.$refs.generalInformationCard.validateForm()) this.isValidGeneralInformationForm = true;
            else this.isValidGeneralInformationForm = false;
        }
    },
    components: {
        Encabezado,
        GeneralInformationCard,
        MedicalRecordCard,
    },
};
</script>

<style scoped></style>