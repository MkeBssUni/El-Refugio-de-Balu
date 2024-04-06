<template>
    <b-col cols="12" class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
        <b-row>
            <b-col cols="10" md="8" lg="6">
                <b-card bg-variant="card-header-orange" class="py-2 form-card-shadow relative-position form-card-title"
                    no-body>
                    <div class="d-flex align-items-center ms-3 ms-md-4">
                        <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
                        <h4 class="mb-0 mt-1">Información general</h4>
                    </div>
                </b-card>
            </b-col>
        </b-row>
        <b-row>
            <b-col cols="12">
                <b-card bg-variant="card-content-orange" class="form-card-shadow form-card-content">
                    <b-card-body>
                        <b-row>
                            <b-col cols="12" lg="5" xl="4" class="px-3 pt-4">
                                <b-row class="d-flex justify-content-center">
                                    <b-col cols="12" class="position-relative">
                                        <b-img :src="showImg()" class="main-img" alt="Imagen principal seleccionada"
                                            fluid rounded center></b-img>
                                        <input type="file" ref="mainImageSelector" style="display: none"
                                            accept="image/jpeg, image/png" @change="selectImg()">
                                        <b-button v-if="!form.mainImage" @click="triggerMainImgSelector()"
                                            class="btn-add center-position d-flex align-items-center justify-content-center p-2">
                                            <b-icon icon="plus" font-scale="5"></b-icon>
                                        </b-button>
                                        <b-button v-else @click="unselectImg()"
                                            class="btn-remove center-position d-flex align-items-center justify-content-center p-2">
                                            <b-icon icon="x" font-scale="5"></b-icon>
                                        </b-button>
                                    </b-col>
                                    <b-col cols="12" sm="9" md="8" lg="12" class="mt-4">
                                        <b-row class="d-flex justify-content-center">
                                            <b-col cols="3" v-for="(image, index) in form.additionalImages" :key="index"
                                                class="position-relative d-flex justify-content-center">
                                                <div class="additional-img-container">
                                                    <b-img :src="getFile(image)" class="additional-img"
                                                        alt="Imagen adicional" center></b-img>
                                                </div>
                                                <b-button @click="removeAdditionalImg(index)"
                                                    class="btn-remove center-position d-flex align-items-center justify-content-center p-1">
                                                    <b-icon icon="x" font-scale="2"></b-icon>
                                                </b-button>
                                            </b-col>
                                            <b-col cols="3" v-if="form.additionalImages.length < 4"
                                                class="d-flex align-items-center justify-content-center">
                                                <input type="file" ref="additionalImageSelector" style="display: none"
                                                    accept="image/jpeg, image/png" @change="selectAdditionalImg()">
                                                <b-button @click="triggerAdditionalImgSelector()"
                                                    class="btn-add d-flex align-items-center justify-content-center p-1">
                                                    <b-icon icon="plus" font-scale="2.5"></b-icon>
                                                </b-button>
                                            </b-col>
                                            <b-col cols="9" md="7" lg="9" v-if="form.additionalImages.length == 0"
                                                class="d-flex align-items-center">
                                                <label>Agrega imágenes adicionales</label>
                                            </b-col>
                                        </b-row>
                                    </b-col>
                                </b-row>
                            </b-col>
                            <b-col class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
                                <b-row>
                                    <b-col cols="12" sm="7" xl="8" class="mt-3 mt-xl-0">
                                        <b-form-group label-for="name">
                                            <label slot="label">
                                                Ingresa el nombre de la mascota:
                                                <span class="required-asterisk">*</span>
                                            </label>
                                            <b-form-input id="name" v-model.trim="form.name"
                                                @input="validateInput('name')" placeholder="Nombre..."></b-form-input>
                                            <b-form-invalid-feedback v-if="showErrors.name">{{ errorMessages.name
                                                }}</b-form-invalid-feedback>
                                        </b-form-group>
                                    </b-col>
                                    <b-col cols="12" sm="5" xl="4" class="mt-3 mt-xl-0">
                                        <b-form-group label-for="category">
                                            <label slot="label">
                                                Selecciona su especie:
                                                <span class="required-asterisk">*</span>
                                            </label>
                                            <b-form-select id="category" v-model="form.category" class="form-select"
                                                @input="validateInput('category')">
                                                <option value="0" disabled>Especie...</option>
                                                <option v-for="category in categories" :key="category.id"
                                                    :value="category.id">
                                                    {{ category.name }}
                                                </option>
                                            </b-form-select>
                                            <b-form-invalid-feedback v-if="showErrors.category">{{
                                                errorMessages.category
                                                }}</b-form-invalid-feedback>
                                        </b-form-group>
                                    </b-col>
                                    <b-col cols="12" sm="7" xl="8" class="mt-3">
                                        <b-form-group label-for="breed">
                                            <label slot="label">
                                                Ingresa la raza de la mascota: <span class="required-asterisk">*</span>
                                            </label>
                                            <b-form-input id="breed" v-model.trim="form.breed"
                                                @input="validateInput('breed')" placeholder="Raza..."></b-form-input>
                                            <b-form-invalid-feedback v-if="showErrors.breed">{{ errorMessages.breed
                                                }}</b-form-invalid-feedback>
                                        </b-form-group>
                                    </b-col>
                                    <b-col cols="12" sm="5" xl="4" class="mt-3">
                                        <b-form-group label-for="size">
                                            <label slot="label">
                                                Selecciona su tamaño: <span class="required-asterisk">*</span>
                                            </label>
                                            <b-form-select id="size" v-model="form.size" @input="validateInput('size')"
                                                class="form-select">
                                                <option value="">Tamaño...</option>
                                                <option v-for="size in sizes" :key="size.key" :value="size.value">
                                                    {{ size.text }}
                                                </option>
                                            </b-form-select>
                                            <b-form-invalid-feedback v-if="showErrors.size">{{ errorMessages.size
                                                }}</b-form-invalid-feedback>
                                        </b-form-group>
                                    </b-col>
                                    <b-col cols="12" sm="7" xl="8">
                                        <b-form-group label-for="age" class="mt-3">
                                            <label slot="label">
                                                Ingresa la edad de la mascota: <span class="required-asterisk">*</span>
                                            </label>
                                            <b-row>
                                                <b-col cols="8" sm="7" xl="8">
                                                    <b-form-input id="age" v-model="form.age" placeholder="Edad..."
                                                        @input="validateInput('age')" type="number"></b-form-input>
                                                    <b-form-invalid-feedback v-if="showErrors.age">{{ errorMessages.age
                                                        }}</b-form-invalid-feedback>
                                                </b-col>
                                                <b-col cols="4" sm="5" xl="4">
                                                    <b-form-select id="ageUnit" v-model="form.ageUnit"
                                                        class="form-select" @input="validateInput('ageUnit')">
                                                        <option value="" disabled>Unidad</option>
                                                        <option v-for="ageUnit in ageUnits" :key="ageUnit.value"
                                                            :value="ageUnit.value">
                                                            {{ ageUnit.text }}
                                                        </option>
                                                    </b-form-select>
                                                    <b-form-invalid-feedback v-if="showErrors.ageUnit">{{
                                                        errorMessages.ageUnit }}</b-form-invalid-feedback>
                                                </b-col>
                                            </b-row>
                                        </b-form-group>
                                    </b-col>
                                    <b-col cols="12" sm="5" xl="4">
                                        <b-form-group label-for="lifeStage" class="mt-3">
                                            <label slot="label">
                                                Selecciona su etapa: <span class="required-asterisk">*</span>
                                            </label>
                                            <b-form-select id="lifeStage" v-model="form.lifeStage"
                                                @input="validateInput('lifeStage')" class="form-select">
                                                <option value="" disabled>Etapa...</option>
                                                <option v-for="lifeStage in lifeStages" :key="lifeStage.id"
                                                    :value="lifeStage.value">
                                                    {{ lifeStage.text }}
                                                </option>
                                            </b-form-select>
                                            <b-form-invalid-feedback v-if="showErrors.lifeStage">{{
                                                errorMessages.lifeStage
                                            }}</b-form-invalid-feedback>
                                        </b-form-group>
                                    </b-col>
                                    <b-col cols="12" sm="7" xl="8">
                                        <b-form-group label-for="weight" class="mt-3">
                                            <label slot="label">
                                                Ingresa el peso de la mascota: <span class="required-asterisk">*</span>
                                            </label>
                                            <b-row>
                                                <b-col cols="8" sm="7" xl="8">
                                                    <b-form-input id="weight" v-model="form.weight"
                                                        @input="validateInput('weight')" placeholder="Peso..."
                                                        type="number"></b-form-input>
                                                    <b-form-invalid-feedback v-if="showErrors.weight">{{
                                                        errorMessages.weight }}</b-form-invalid-feedback>
                                                </b-col>
                                                <b-col cols="4" sm="5" xl="4">
                                                    <b-form-select v-model="form.weightUnit" id="weightUnit"
                                                        @input="validateInput('weightUnit')" class="form-select">
                                                        <option value="" disabled>Unidad</option>
                                                        <option v-for="weightUnit in weightUnits"
                                                            :key="weightUnit.value" :value="weightUnit.value">
                                                            {{ weightUnit.text }}
                                                        </option>
                                                    </b-form-select>
                                                    <b-form-invalid-feedback v-if="showErrors.weight">{{
                                                        errorMessages.weight }}</b-form-invalid-feedback>
                                                </b-col>
                                            </b-row>
                                        </b-form-group>
                                    </b-col>
                                    <b-col cols="12" sm="5" xl="4">
                                        <b-form-group label-for="gender" class="mt-3">
                                            <label slot="label">
                                                Selecciona su sexo: <span class="required-asterisk">*</span>
                                            </label>
                                            <b-form-select id="gender" v-model="form.gender" class="form-select"
                                                @input="validateInput('gender')">
                                                <option value="" disabled>Sexo...</option>
                                                <option v-for="gender in genders" :key="gender.id"
                                                    :value="gender.value">
                                                    {{ gender.text }}
                                                </option>
                                            </b-form-select>
                                            <b-form-invalid-feedback v-if="showErrors.gender">{{ errorMessages.gender
                                                }}</b-form-invalid-feedback>
                                        </b-form-group>
                                    </b-col>
                                </b-row>
                            </b-col>
                        </b-row>
                    </b-card-body>
                </b-card>
            </b-col>
        </b-row>
    </b-col>
</template>

<script>
import Swal from 'sweetalert2';
import { isInvalidName } from '../../../../kernel/validations';
import instance from "../../../../config/axios";
import gatoWalkingGif from "@/assets/imgs/gatoWalking.gif";
import sizes from "../../../../kernel/data/sizes";
import ageUnits from "../../../../kernel/data/ageUnits";
import lifeStages from "../../../../kernel/data/lifeStages";
import weightUnits from "../../../../kernel/data/weightUnits";
import genders from "../../../../kernel/data/genders";

export default {
    data() {
        return {
            form: {
                mainImage: null,
                additionalImages: [],
                name: "",
                category: "0",
                breed: "",
                size: "",
                age: null,
                ageUnit: "",
                lifeStage: "",
                weight: null,
                weightUnit: "",
                gender: ""
            },
            isValidForm: false,
            errorMessages: {
                name: "",
                category: "",
                breed: "",
                size: "",
                age: "",
                ageUnit: "",
                lifeStage: "",
                weight: "",
                weightUnit: "",
                gender: ""
            },
            showErrors: {
                mainImage: false,
                name: false,
                category: false,
                breed: false,
                size: false,
                age: false,
                ageUnit: false,
                lifeStage: false,
                weight: false,
                weightUnit: false,
                gender: false
            },
            categories: [],
            sizes: sizes,
            ageUnits: ageUnits,
            lifeStages: lifeStages,
            weightUnits: weightUnits,
            genders: genders
        }
    },
    methods: {
        async getCategories() {
            try {
                Swal.fire({
                    title: 'Cargando...',
                    text: 'Estamos cargando las categorías, espera un momento',
                    imageUrl: gatoWalkingGif,
                    imageWidth: 160,
                    imageHeight: 160,
                    showConfirmButton: false
                })
                const response = await instance.get(`/category/list`)
                this.categories = response.data.data
                Swal.close()
            } catch (error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al cargar las categorías',
                    icon: 'error',
                    iconColor: '#A93D3D',
                    timer: 3000,
                    timerProgressBar: true,
                    showConfirmButton: false
                }).then(() => {
                    this.$router.push('/myPets')
                })
            }
        },
        duplicateImg() {
            Swal.fire({
                icon: 'error',
                title: '¡Error!',
                text: 'No puede haber imágenes duplicadas',
                toast: true,
                position: 'top-end',
                timer: 3000,
                showCancelButton: false,
                showConfirmButton: false,
                timerProgressBar: true,
            });
        },
        validateMainImage() {
            if (!this.form.mainImage) {
                Swal.fire({
                    icon: 'error',
                    title: '¡Error!',
                    text: 'Debes seleccionar una imagen principal',
                    toast: true,
                    position: 'top-end',
                    timer: 3000,
                    showCancelButton: false,
                    showConfirmButton: false,
                    timerProgressBar: true,
                });
                this.showErrors.mainImage = true;
                return;
            }
            this.showErrors.mainImage = false;
        },
        validateMainImageSize(file) {
            if (file.size > 4000000) {
                Swal.fire({
                    icon: 'error',
                    title: '¡Error!',
                    text: 'La imagen no puede pesar más de 4MB',
                    toast: true,
                    position: 'top-end',
                    timer: 3000,
                    showCancelButton: false,
                    showConfirmButton: false,
                    timerProgressBar: true,
                });
                return false;
            }
            return true;
        },
        validateInput(field) {
            const input = document.getElementById(field);
            switch (field) {
                case "name":
                    if (!this.form.name) {
                        this.errorMessages.name = "Campo obligatorio";
                        this.showErrors.name = true;
                        input.classList.add('is-invalid');
                    } else if (this.form.name.length < 3 || this.form.name.length > 30) {
                        this.errorMessages.name = "El nombre de la mascota debe tener entre 3 y 30 caracteres";
                        this.showErrors.name = true;
                        input.classList.add('is-invalid');
                    } else if (isInvalidName(this.form.name)) {
                        this.errorMessages.name = "El nombre de la mascota no puede contener números ni caracteres especiales";
                        this.showErrors.name = true;
                        input.classList.add('is-invalid');
                    } else {
                        this.errorMessages.name = "";
                        this.showErrors.name = false;
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;
                case "category":
                    if (this.form.category == "0") {
                        this.errorMessages.category = "Campo obligatorio";
                        this.showErrors.category = true;
                        input.classList.add('is-invalid');
                    } else {
                        this.errorMessages.category = "";
                        this.showErrors.category = false;
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;
                case "breed":
                    if (!this.form.breed) {
                        this.errorMessages.breed = "Campo obligatorio";
                        this.showErrors.breed = true;
                        input.classList.add('is-invalid');
                    } else if (this.form.breed.length < 3 || this.form.breed.length > 50) {
                        this.errorMessages.breed = "La raza de la mascota debe tener entre 3 y 50 caracteres";
                        this.showErrors.breed = true;
                        input.classList.add('is-invalid');
                    } else if (isInvalidName(this.form.breed)) {
                        this.errorMessages.breed = "La raza de la mascota no puede contener números ni caracteres especiales";
                        this.showErrors.breed = true;
                        input.classList.add('is-invalid');
                    } else {
                        this.errorMessages.breed = "";
                        this.showErrors.breed = false;
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;
                case "size":
                    if (!this.form.size) {
                        this.errorMessages.size = "Campo obligatorio";
                        this.showErrors.size = true;
                        input.classList.add('is-invalid');
                    } else {
                        this.errorMessages.size = "";
                        this.showErrors.size = false;
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;
                case "age":
                    if (!this.form.age) {
                        this.errorMessages.age = "Campo obligatorio";
                        this.showErrors.age = true;
                        input.classList.add('is-invalid');
                    } else if (this.form.age <= 0) {
                        this.errorMessages.age = "La edad de la mascota debe ser mayor a 0";
                        this.showErrors.age = true;
                        input.classList.add('is-invalid');
                    } else if (this.form.age % 1 != 0) {
                        this.errorMessages.age = "La edad de la mascota debe ser un número entero";
                        this.showErrors.age = true;
                        input.classList.add('is-invalid');
                    } else {
                        this.errorMessages.age = "";
                        this.showErrors.age = false;
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;
                case "ageUnit":
                    if (!this.form.ageUnit) {
                        this.errorMessages.ageUnit = "Campo obligatorio";
                        this.showErrors.ageUnit = true;
                        input.classList.add('is-invalid');
                    } else {
                        this.errorMessages.ageUnit = "";
                        this.showErrors.ageUnit = false;
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;
                case "lifeStage":
                    if (!this.form.lifeStage) {
                        this.errorMessages.lifeStage = "Campo obligatorio";
                        this.showErrors.lifeStage = true;
                        input.classList.add('is-invalid');
                    } else {
                        this.errorMessages.lifeStage = "";
                        this.showErrors.lifeStage = false;
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;
                case "weight":
                    if (!this.form.weight) {
                        this.errorMessages.weight = "Campo obligatorio";
                        this.showErrors.weight = true;
                        input.classList.add('is-invalid');
                    } else if (this.form.weight <= 0) {
                        this.errorMessages.weight = "El peso de la mascota debe ser mayor a 0";
                        this.showErrors.weight = true;
                        input.classList.add('is-invalid');
                    } else {
                        this.errorMessages.weight = "";
                        this.showErrors.weight = false;
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;
                case "weightUnit":
                    if (!this.form.weightUnit) {
                        this.errorMessages.weightUnit = "Campo obligatorio";
                        this.showErrors.weightUnit = true;
                        input.classList.add('is-invalid');
                    } else {
                        this.errorMessages.weightUnit = "";
                        this.showErrors.weightUnit = false;
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;
                case "gender":
                    if (!this.form.gender) {
                        this.errorMessages.gender = "Campo obligatorio";
                        this.showErrors.gender = true;
                        input.classList.add('is-invalid');
                    } else {
                        this.errorMessages.gender = "";
                        this.showErrors.gender = false;
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
            }
        },
        showImg() {
            if (this.form.mainImage) return URL.createObjectURL(this.form.mainImage);
            return "https://t3.ftcdn.net/jpg/07/01/59/38/240_F_701593826_ojYyX0cKXG3OzhoYkbeesqsQtaA6zBbj.jpg";
        },
        triggerMainImgSelector() {
            this.$refs.mainImageSelector.click();
        },
        selectImg() {
            const file = this.$refs.mainImageSelector.files[0];
            if (file && this.validateMainImageSize(file)) {
                if (this.form.additionalImages.length > 0) {
                    const repeated = this.form.additionalImages.some(image => image.name == file.name);
                    if (repeated) {
                        this.duplicateImg();
                        return;
                    }
                }
                this.form.mainImage = file;
            }
        },
        unselectImg() {
            this.form.mainImage = null;
        },
        getFile(file) {
            return URL.createObjectURL(file);
        },
        triggerAdditionalImgSelector() {
            this.$refs.additionalImageSelector.click();
        },
        selectAdditionalImg() {
            const file = this.$refs.additionalImageSelector.files[0];
            if (file && this.validateMainImageSize(file)) {
                if (this.form.mainImage && this.form.mainImage.name == file.name) {
                    this.duplicateImg();
                    return;
                }
                const repeated = this.form.additionalImages.some(image => image.name == file.name);
                if (repeated) {
                    this.duplicateImg();
                    return;
                }
                this.form.additionalImages.push(file);
            }
        },
        removeAdditionalImg(index) {
            this.form.additionalImages.splice(index, 1);
        },
        validateForm() {
            this.validateMainImage();
            this.validateInput('name');
            this.validateInput('category');
            this.validateInput('breed');
            this.validateInput('size');
            this.validateInput('age');
            this.validateInput('ageUnit');
            this.validateInput('lifeStage');
            this.validateInput('weight');
            this.validateInput('weightUnit');
            this.validateInput('gender');
            if (this.showErrors.mainImage || this.showErrors.name || this.showErrors.category || this.showErrors.breed ||
                this.showErrors.size || this.showErrors.age || this.showErrors.ageUnit || this.showErrors.lifeStage ||
                this.showErrors.weight || this.showErrors.weightUnit || this.showErrors.gender) return false;
            return true;
        }
    },
    mounted() {
        this.getCategories();
    }
}
</script>

<style scoped>
.main-img {
    width: 280px;
    height: 240px;
    object-fit: cover;
    border: 4px solid black;
}

.additional-img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.additional-img-container {
    min-width: 90px;
    max-width: 90px;
    height: 80px;
    border: 2px solid black;
    border-radius: 5px;
}

.btn-add {
    border: none;
    cursor: pointer;
    border-radius: 50%;
    background-color: rgba(83, 169, 61, 0.6);
    color: #316E21;
}

.btn-add:hover {
    background-color: rgba(83, 169, 61, 1);
}

.btn-add:active {
    background-color: #347424;
    color: #1f4915;
}

.btn-remove {
    border: none;
    cursor: pointer;
    border-radius: 50%;
    background-color: rgba(169, 61, 61, 0.61);
    color: #571724;
}

.btn-remove:hover {
    background-color: rgba(169, 61, 61, 1);
}

.btn-remove:active {
    background-color: #742434;
    color: #49151f;
}
</style>