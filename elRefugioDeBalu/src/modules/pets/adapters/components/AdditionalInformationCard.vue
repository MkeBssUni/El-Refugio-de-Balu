<template>
    <b-col cols="12" class="px-2 px-sm-4 px-xl-5 my-4">
        <b-row>
            <b-col cols="10" md="8" lg="6">
                <b-card bg-variant="card-header-orange" class="py-2 card-shadow relative-position form-card-title"
                    no-body>
                    <div class="d-flex align-items-center ms-3 ms-md-4">
                        <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
                        <h4 class="mb-0 mt-1">Información adicional</h4>
                    </div>
                </b-card>
            </b-col>
        </b-row>
        <b-row>
            <b-col cols="12">
                <b-card bg-variant="card-content-orange" class="card-shadow form-card-content">
                    <b-card-body>
                        <b-row class="mt-3">
                            <b-col cols="12" lg="6">
                                <b-form-group>
                                    <b-row>
                                        <b-col cols="12">
                                            <label class="mb-2" for="characteristic">
                                                Escribe las características de la mascota:
                                                <span class="required-asterisk">*</span>
                                            </label>
                                        </b-col>
                                        <b-col cols="10" sm="8">
                                            <b-form-input id="characteristic" v-model.trim="characteristic"
                                                placeholder="Característica..." @input="validateField('characteristic')"
                                                @keyup.enter="addCharacteristic()"></b-form-input>
                                        </b-col>
                                        <b-col cols="2" sm="4" class="align-self-start">
                                            <b-button variant="dark-orange" type="button" @click="addCharacteristic()"
                                                class="d-flex align-items-center justify-content-between add-badge-btn">
                                                <span
                                                    class="d-none d-sm-flex align-items-center justify-content-between me-2">Agregar</span>
                                                <b-icon icon="plus-circle"></b-icon>
                                            </b-button>
                                        </b-col>
                                        <b-col cols="10" sm="8">
                                            <div class="px-2 pb-2 mx-1 form-badge-container border-orange"
                                                v-if="form.characteristics.length > 0">
                                                <b-badge v-for="(characteristic, index) in form.characteristics"
                                                    :key="index"
                                                    class="me-2 mt-2 d-inline-flex align-items-center justify-content-between"
                                                    variant="orange-outline">
                                                    <span class="me-1">{{ truncate(characteristic) }}</span>
                                                    <b-icon icon="x" font-scale="1.4"
                                                        @click="removeCharacteristic(index)"></b-icon>
                                                </b-badge>
                                            </div>
                                            <div v-if="showErrors.characteristic"
                                                class="input-badge-invalid-feedback mt-1">
                                                {{ errorMessages.characteristic }}
                                            </div>
                                        </b-col>
                                    </b-row>
                                </b-form-group>
                            </b-col>
                            <b-col cols="12" lg="6" class="mt-3 mt-lg-0">
                                <b-form-group>
                                    <b-row>
                                        <b-col cols="12">
                                            <label class="mb-2" for="care">
                                                Escribe los cuidados especiales que necesita la mascota (si aplica):
                                            </label>
                                        </b-col>
                                        <b-col cols="10" sm="8">
                                            <b-form-textarea id="care" v-model.trim="care"
                                                placeholder="Hábito o cuidado..." @input="validateField('care')"
                                                @keyup.enter="addCare()" rows="1" max-rows="3"></b-form-textarea>
                                        </b-col>
                                        <b-col cols="2" sm="4" class="align-self-start">
                                            <b-button variant="dark-orange" type="button" @click="addCare()"
                                                class="d-flex align-items-center justify-content-between add-badge-btn">
                                                <span
                                                    class="d-none d-sm-flex align-items-center justify-content-between me-2">Agregar</span>
                                                <b-icon icon="plus-circle"></b-icon>
                                            </b-button>
                                        </b-col>
                                        <b-col cols="10" sm="8">
                                            <div class="px-2 pb-2 mx-1 form-badge-container border-orange"
                                                v-if="form.specialCares.length > 0">
                                                <b-badge
                                                    class="me-2 mt-2 d-inline-flex align-items-center justify-content-between"
                                                    variant="orange-outline" v-for="(care, index) in form.specialCares"
                                                    :key="index">
                                                    <span class="me-1">{{ truncate(care) }}</span>
                                                    <b-icon icon="x" font-scale="1.4"></b-icon>
                                                </b-badge>
                                            </div>
                                            <div v-if="showErrors.care" class="input-badge-invalid-feedback mt-1">
                                                {{ errorMessages.care }}
                                            </div>
                                        </b-col>
                                    </b-row>
                                </b-form-group>
                            </b-col>
                            <b-col cols="12" class="mt-3">
                                <b-form-group>
                                    <label for="description" class="mb-2">
                                        Describe a la mascota y cuenta su historia:
                                        <span class="required-asterisk">*</span>
                                    </label>
                                    <b-form-textarea id="description" v-model.trim="form.description" rows="5"
                                        placeholder="Descripción e historia..." @input="validateField('description')">
                                    </b-form-textarea>
                                    <b-form-invalid-feedback v-if="showErrors.description">
                                        {{ errorMessages.description }}
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
            characteristic: "",
            care: "",
            form: {
                characteristics: [],
                specialCares: [],
                description: ""
            },
            showErrors: {
                characteristic: false,
                care: false,
                description: false
            },
            errorMessages: {
                characteristic: "",
                care: "",
                description: ""
            }
        }
    },
    methods: {
        truncate(string) {
            return string.length > 25 ? string.substring(0, 25) + "..." : string;
        },
        addCharacteristic() {
            if (this.characteristic && !this.showErrors.characteristic) {
                this.form.characteristics.push(this.characteristic);
                this.characteristic = "";
            }
        },
        removeCharacteristic(index) {
            this.form.characteristics.splice(index, 1);
        },
        addCare() {
            if (this.care && !this.showErrors.care) {
                this.form.specialCares.push(this.care);
                this.care = "";
            }
        },
        removeCare(index) {
            this.form.specialCares.splice(index, 1);
        },
        validateField(field) {
            const input = document.getElementById(field);
            switch (field) {
                case "characteristic":
                    if (!this.characteristic && this.form.characteristics.length < 3) {
                        this.errorMessages.characteristic = "Agrega mínimo 3 características";
                        this.showErrors.characteristic = true;
                        input.classList.add("is-invalid");
                    } else if (this.characteristic && this.characteristic.length < 3 || this.characteristic.length > 30) {
                        this.errorMessages.characteristic = "La característica debe tener entre 3 y 30 caracteres";
                        this.showErrors.characteristic = true;
                        input.classList.add("is-invalid");
                    } else if (this.characteristic && isInvalidName(this.characteristic)) {
                        this.errorMessages.characteristic = "La característica no puede contener números ni caracteres especiales";
                        this.showErrors.characteristic = true;
                        input.classList.add("is-invalid");
                    } else if (this.form.characteristics.length >= 20) {
                        this.errorMessages.characteristic = "Puedes agregar máximo 20 características";
                        this.showErrors.characteristic = true;
                        input.classList.add("is-invalid");
                    } else if (this.form.characteristics.some(characteristic => characteristic.toLowerCase() === this.characteristic.toLowerCase())) {
                        this.errorMessages.characteristic = "La característica ya fue agregada";
                        this.showErrors.characteristic = true;
                        input.classList.add("is-invalid");
                    } else {
                        this.errorMessages.characteristic = "";
                        this.showErrors.characteristic = false;
                        input.classList.remove("is-invalid");
                    }
                    break;
                case "care":
                    if (this.care) {
                        if (this.care.length < 20 || this.care.length > 200) {
                            this.errorMessages.care = "El hábito o cuidado debe tener entre 20 y 200 caracteres";
                            this.showErrors.care = true;
                            input.classList.add("is-invalid");
                        } else if (this.form.specialCares.some(care => care.toLowerCase() === this.care.toLowerCase())) {
                            this.errorMessages.care = "El hábito o cuidado ya fue agregado";
                            this.showErrors.care = true;
                            input.classList.add("is-invalid");
                        } else {
                            this.errorMessages.care = "";
                            this.showErrors.care = false;
                            input.classList.remove("is-invalid");
                            input.classList.add("is-valid");
                        }
                    } else {
                        this.errorMessages.care = "";
                        this.showErrors.care = false;
                        input.classList.remove("is-invalid");
                    }
                    break;
                case "description":
                    if (!this.form.description) {
                        this.errorMessages.description = "Campo obligatorio";
                        this.showErrors.description = true;
                        input.classList.add("is-invalid");
                    } else if (this.form.description.length < 250 || this.form.description.length > 1500) {
                        this.errorMessages.description = "La descripción debe tener entre 250 y 1500 caracteres";
                        this.showErrors.description = true;
                        input.classList.add("is-invalid");
                    } else {
                        this.errorMessages.description = "";
                        this.showErrors.description = false;
                        input.classList.remove("is-invalid");
                        input.classList.add("is-valid");
                    }
                    break;
                default:
                    break;
            }
        },
        validateForm() {
            this.validateField("characteristic");
            this.validateField("care");
            this.validateField("description");
            if (this.showErrors.characteristic || this.showErrors.care || this.showErrors.description) return false;
            return true;
        },
        resetForm() {
            this.characteristic = "";
            this.care = "";
            this.form.characteristics = [];
            this.form.specialCares = [];
            this.form.description = "";
            this.showErrors.characteristic = false;
            this.showErrors.care = false;
            this.showErrors.description = false;
            this.errorMessages.characteristic = "";
            this.errorMessages.care = "";
            this.errorMessages.description = "";            
        }
    }
}
</script>

<style scoped>
.add-badge-btn {
    border-color: #e79d2e;
}
</style>