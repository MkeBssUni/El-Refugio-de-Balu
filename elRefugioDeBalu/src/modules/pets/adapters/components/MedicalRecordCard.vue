<template>
    <b-col cols="12" class="px-2 px-sm-4 px-xl-5 mt-4">
        <b-row>
            <b-col cols="10" md="8" lg="6">
                <b-card bg-variant="card-header-blue" class="py-2 card-shadow relative-position form-card-title"
                    no-body>
                    <div class="d-flex align-items-center ms-3 ms-md-4">
                        <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
                        <h4 class="mb-0 mt-1">Información médica</h4>
                    </div>
                </b-card>
            </b-col>
        </b-row>
        <b-row>
            <b-col cols="12">
                <b-card bg-variant="card-content-blue" class="card-shadow form-card-content">
                    <b-card-body>
                        <b-row class="mt-3">
                            <b-col cols="12">
                                <label class="mb-3">
                                    Selecciona las opciones que apliquen a la mascota:
                                </label>
                            </b-col>
                            <b-col cols="12" md="6">
                                <div class="mb-2 form-check">
                                    <input id="vaccinated" type="checkbox" class="form-check-input"
                                        v-model="form.vaccinated" value="true" unchecked-value="false">
                                    <label class="ms-2 form-check-label checkbox-text" for="vaccinated">
                                        Mi mascota cuenta con todas sus vacunas
                                    </label>
                                </div>
                            </b-col>
                            <b-col cols="12" md="6">
                                <div class="mb-2 form-check">
                                    <input id="dewormed" type="checkbox" class="form-check-input"
                                        v-model="form.dewormed" value="true" unchecked-value="false">
                                    <label class="ms-2 form-check-label checkbox-text" for="dewormed">
                                        Mi mascota se encuentra desparasitada
                                    </label>
                                </div>
                            </b-col>
                            <b-col cols="12" md="6">
                                <div class="mb-2 form-check">
                                    <input id="sterilised" type="checkbox" class="form-check-input"
                                        v-model="form.sterilised" value="true" unchecked-value="false">
                                    <label class="ms-2 form-check-label checkbox-text" for="sterilised">
                                        Mi mascota se encuentra esterilizada
                                    </label>
                                </div>
                            </b-col>
                            <b-col cols="12" md="6">
                                <div class="mb-2 form-check">
                                    <input id="microchip" type="checkbox" class="form-check-input"
                                        v-model="form.microchip" value="true" unchecked-value="false">
                                    <label class="ms-2 form-check-label checkbox-text" for="microchip">
                                        Mi mascota cuenta con microchip
                                    </label>
                                </div>
                            </b-col>
                        </b-row>
                        <b-row>
                            <b-col cols="12" lg="6" class="mt-3">
                                <b-form-group>
                                    <b-row>
                                        <b-col cols="12">
                                            <label class="mb-2" for="disease">Escribe las enfermedades de la mascota (si
                                                aplica):</label>
                                        </b-col>
                                        <b-col cols="10" sm="8">                                            
                                            <b-form-input id="disease" v-model.trim="disease"
                                                placeholder="Enfermedad o padecimiento..."
                                                @input="validateField('disease')" @keyup.enter="addDisease()">
                                            </b-form-input>
                                        </b-col>
                                        <b-col cols="2" sm="4" class="align-self-end mb-1 mb-sm-0">
                                            <b-button variant="dark-blue" type="button"
                                                class="d-flex align-items-center justify-content-between add-badge-btn"
                                                @click="addDisease()">
                                                <span
                                                    class="d-none d-sm-flex align-items-center justify-content-between me-2">Agregar</span>
                                                <b-icon icon="plus-circle"></b-icon>
                                            </b-button>
                                        </b-col>
                                        <b-col cols="10" sm="8">
                                            <div class="px-2 pb-2 mx-1 form-badge-container border-blue"
                                                v-if="form.diseases.length > 0">
                                                <b-badge v-for="(disease, index) in form.diseases" :key="index"
                                                    class="me-2 mt-2 d-inline-flex align-items-center justify-content-between"
                                                    variant="info-outline">
                                                    <span class="me-1">{{ truncate(disease) }}</span>
                                                    <b-icon icon="x" @click="removeDisease(index)"
                                                        font-scale="1.4"></b-icon>
                                                </b-badge>
                                            </div>
                                            <div v-if="showErrors.disease" class="input-badge-invalid-feedback mt-1">
                                                {{ errorMessages.disease }}
                                            </div>
                                        </b-col>
                                    </b-row>
                                </b-form-group>
                            </b-col>
                            <b-col cols="12" lg="6" class="mt-3">
                                <b-form-group>
                                    <b-row>
                                        <b-col cols="12">
                                            <label class="mb-2" for="allergie">Escribe las alergias de la mascota (si
                                                aplica):</label>
                                        </b-col>
                                        <b-col cols="10" sm="8">                                            
                                            <b-form-input id="allergie" v-model.trim="allergie"
                                                placeholder="Alergia o intolerancia..."
                                                @input="validateField('allergie')" @keyup.enter="addAllergie()">
                                            </b-form-input>
                                        </b-col>
                                        <b-col cols="2" sm="4" class="align-self-end mb-1 mb-sm-0">
                                            <b-button variant="dark-blue" type="button"
                                                class="d-flex align-items-center justify-content-between add-badge-btn"
                                                @click="addAllergie()">
                                                <span
                                                    class="d-none d-sm-flex align-items-center justify-content-between me-2">Agregar</span>
                                                <b-icon icon="plus-circle"></b-icon>
                                            </b-button>
                                        </b-col>
                                        <b-col cols="10" sm="8">
                                            <div class="px-2 pb-2 mx-1 form-badge-container border-blue"
                                                v-if="form.allergies.length > 0">
                                                <b-badge v-for="(allergie, index) in form.allergies" :key="index"
                                                    class="me-2 mt-2 d-inline-flex align-items-center justify-content-between"
                                                    variant="info-outline">
                                                    <span class="me-1">{{ truncate(allergie) }}</span>
                                                    <b-icon icon="x" @click="removeAllergie(index)"
                                                        font-scale="1.4"></b-icon>
                                                </b-badge>
                                            </div>
                                            <div v-if="showErrors.allergie" class="input-badge-invalid-feedback mt-1">
                                                {{ errorMessages.allergie }}
                                            </div>
                                        </b-col>
                                    </b-row>
                                </b-form-group>
                            </b-col>
                        </b-row>
                        <b-row>
                            <b-col cols="12" class="mt-4">
                                <b-form-group>
                                    <label for="observations" class="mb-2">
                                        Agrega algún comentario adicional respecto a la salud de tu mascota:
                                    </label>
                                    <b-form-textarea id="observations" v-model.trim="form.observations" rows="2" max-rows="4"
                                        placeholder="Comentario adicional..." @input="validateField('observations')">
                                    </b-form-textarea>
                                    <b-form-invalid-feedback v-if="showErrors.observations">
                                        {{ errorMessages.observations }}
                                    </b-form-invalid-feedback>
                                </b-form-group>
                            </b-col>
                        </b-row>
                    </b-card-body>
                </b-card>
            </b-col>
        </b-row>
    </b-col>
</template>

<script>
import { isInvalidName } from '../../../../kernel/validations';
export default {
    data() {
        return {
            disease: "",
            allergie: "",
            form: {
                vaccinated: false,
                dewormed: false,
                sterilised: false,
                microchip: false,
                diseases: [],
                allergies: [],
                observations: "",
            },
            showErrors: {
                disease: false,
                allergie: false,
                observations: false,
            },
            errorMessages: {
                disease: "",
                allergie: "",
                observations: "",
            }
        }
    },
    methods: {
        truncate(string) {
            return string.length > 25 ? string.substring(0, 25) + '...' : string;
        },
        addDisease() {
            if (this.disease && !this.showErrors.disease) {
                if (!this.form.diseases.some(disease => disease.toLowerCase() === this.disease.toLowerCase())) {
                    this.form.diseases.push(this.disease);
                    this.disease = "";
                }
            }
        },
        removeDisease(index) {
            this.form.diseases.splice(index, 1);
        },
        addAllergie() {
            if (this.allergie && !this.showErrors.allergie) {
                if (!this.form.allergies.some(allergie => allergie.toLowerCase() === this.allergie.toLowerCase())) {
                    this.form.allergies.push(this.allergie);
                    this.allergie = "";
                }
            }
        },
        removeAllergie(index) {
            this.form.allergies.splice(index, 1);
        },
        validateField(field) {
            const input = document.getElementById(field);
            switch (field) {
                case 'disease':
                    if (this.disease) {
                        if (this.disease.length < 3 || this.disease.length > 50) {
                            this.errorMessages.disease = 'La enfermedad debe tener entre 3 y 50 caracteres';
                            this.showErrors.disease = true;
                            input.classList.add('is-invalid');
                        } else if (isInvalidName(this.disease)) {
                            this.errorMessages.disease = 'La enfermedad no debe contener números ni caracteres especiales';
                            this.showErrors.disease = true;
                            input.classList.add('is-invalid');
                        } else if (this.form.diseases.some(disease => disease.toLowerCase() === this.disease.toLowerCase())) {
                            this.errorMessages.disease = 'La enfermedad ya ha sido agregada';
                            this.showErrors.disease = true;
                            input.classList.add('is-invalid');
                        } else {
                            this.errorMessages.disease = "";
                            this.showErrors.disease = false;
                            input.classList.remove('is-invalid');
                        }
                    } else {
                        this.errorMessages.disease = "";
                        this.showErrors.disease = false;
                        input.classList.remove('is-invalid');
                    }
                    break;
                case 'allergie':
                    if (this.allergie) {
                        if (this.allergie.length < 3 || this.allergie.length > 50) {
                            this.errorMessages.allergie = 'La alergia debe tener entre 3 y 50 caracteres';
                            this.showErrors.allergie = true;
                            input.classList.add('is-invalid');
                        } else if (isInvalidName(this.allergie)) {
                            this.errorMessages.allergie = 'La alergia no debe contener números ni caracteres especiales';
                            this.showErrors.allergie = true;
                            input.classList.add('is-invalid');
                        } else if (this.form.allergies.some(allergie => allergie.toLowerCase() === this.allergie.toLowerCase())) {
                            this.errorMessages.allergie = 'La alergia ya ha sido agregada';
                            this.showErrors.allergie = true;
                            input.classList.add('is-invalid');
                        } else {
                            this.errorMessages.allergie = "";
                            this.showErrors.allergie = false;
                            input.classList.remove('is-invalid');
                        }
                    } else {
                        this.errorMessages.allergie = "";
                        this.showErrors.allergie = false;
                        input.classList.remove('is-invalid');
                    }
                    break;
                case 'observations':
                    if (this.form.observations) {
                        if (this.form.observations.length < 30 || this.form.observations.length > 500) {
                            this.errorMessages.observations = 'El comentario debe tener entre 30 y 500 caracteres';
                            this.showErrors.observations = true;
                            input.classList.add('is-invalid');
                        } else {
                            this.errorMessages.observations = "";
                            this.showErrors.observations = false;
                            input.classList.remove('is-invalid');
                        }
                    } else {
                        this.errorMessages.observations = "";
                        this.showErrors.observations = false;
                        input.classList.remove('is-invalid');
                    }
                    break;
                default:
                    break;
            }
        },
    }
}
</script>

<style scoped>
.add-badge-btn {
    border-color: #0c717a;
}
</style>