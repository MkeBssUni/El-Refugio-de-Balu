<template>
    <b-container fluid>
        <b-row>
            <Encabezado color="#118A95" :imagenUrl="require('@/assets/imgs/mascotas-formulario.png')"
                titulo="Dar mascota en adopción" />
        </b-row>
        <b-row class="px-2 px-sm-4 my-2 my-sm-4">
            <b-form>
                <b-col cols="12" class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
                    <b-row>
                        <b-col cols="10" md="8" lg="6">
                            <b-card bg-variant="card-header-orange"
                                class="py-2 card-shadow relative-position form-card-title" no-body>
                                <div class="d-flex align-items-center ms-3 ms-md-4">
                                    <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
                                    <h4 class="mb-0 mt-1">Información general</h4>
                                </div>
                            </b-card>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col cols="12">
                            <b-card bg-variant="card-content-orange" class="card-shadow form-card-content">
                                <b-card-body>
                                    <b-row>
                                        <b-col cols="12" lg="5" xl="4" class="px-3 pt-4">
                                            <b-row>
                                                <b-col cols="12" class="position-relative">
                                                    <b-img :src="showImg()" class="main-img"
                                                        alt="Imagen principal seleccionada" fluid rounded
                                                        center></b-img>
                                                    <input type="file" ref="mainImageSelector" style="display: none"
                                                        accept="image/jpeg" @change="selectImg()">
                                                    <b-button v-if="!form.mainImage" @click="triggerMainImgSelector()"
                                                        class="btn-add position-center d-flex align-items-center justify-content-center p-2">
                                                        <b-icon icon="plus" font-scale="5"></b-icon>
                                                    </b-button>
                                                    <b-button v-else @click="unselectImg()"
                                                        class="btn-remove position-center d-flex align-items-center justify-content-center p-2">
                                                        <b-icon icon="x" font-scale="5"></b-icon>
                                                    </b-button>
                                                </b-col>
                                            </b-row>
                                            <b-row class="my-3 custom-row-width">
                                                <b-col cols="3" v-for="(image, index) in form.additionalImages"
                                                    :key="index" class="position-relative">
                                                    <div class="additional-img-container">
                                                        <b-img :src="getFile(image)" class="additional-img"
                                                            alt="Imagen adicional" fluid rounded center></b-img>
                                                    </div>
                                                    <b-button @click="removeAdditionalImg(index)"
                                                        class="btn-remove position-center d-flex align-items-center justify-content-center p-1">
                                                        <b-icon icon="x" font-scale="2"></b-icon>
                                                    </b-button>
                                                </b-col>
                                                <b-col cols="3" v-if="form.additionalImages.length < 4"
                                                    class="d-flex align-items-center justify-content-center">
                                                    <input type="file" ref="additionalImageSelector"
                                                        style="display: none" accept="image/jpeg"
                                                        @change="selectAdditionalImg()">
                                                    <b-button @click="triggerAdditionalImgSelector()"
                                                        class="btn-add d-flex align-items-center justify-content-center p-1">
                                                        <b-icon icon="plus" font-scale="2.5"></b-icon>
                                                    </b-button>
                                                </b-col>
                                                <b-col cols="9" v-if="form.additionalImages.length == 0"
                                                    class="d-flex align-items-center">
                                                    <label>Agrega imágenes adicionales</label>
                                                </b-col>
                                            </b-row>
                                        </b-col>
                                        <b-col cols="12" lg="7" xl="8" class="d-flex align-items-center">
                                            <b-row>
                                                <b-col cols="12" sm="7" xl="8" class="mt-3 mt-xl-0">
                                                    <b-form-group label="Ingresa el nombre de la mascota:" 
                                                        label-for="name">
                                                        <b-form-input id="name" v-model.trim="form.name" @input="validateInput('name')" @focus="validateInput('name')"
                                                            placeholder="Nombre..."></b-form-input>
                                                            <b-form-invalid-feedback v-if="showErrors.name"
                                                            >{{ errorsMessages.name }}</b-form-invalid-feedback>
                                                    </b-form-group>
                                                </b-col>
                                                <b-col cols="12" sm="5" xl="4" class="mt-3 mt-xl-0">
                                                    <b-form-group label="Selecciona su especie:" label-for="category">
                                                        <b-form-select id="category" v-model.trim="form.category" @input="validateInput('species')" @click="validateInput('species')" 
                                                            class="form-select">
                                                            <option value="0" disabled>Especie...</option>
                                                            <option v-for="category in categories" :key="category.id"
                                                                :value="category.id">
                                                                {{ category.name }}
                                                            </option>
                                                        </b-form-select>
                                                        <b-form-invalid-feedback v-if="showErrors.species"
                                                            >{{ errorsMessages.species }}</b-form-invalid-feedback>
                                                    </b-form-group>
                                                </b-col>
                                                <b-col cols="12" sm="7" xl="8" class="mt-3">
                                                    <b-form-group label="Ingresa la raza de la mascota:"
                                                        label-for="breed">
                                                        <b-form-input id="breed" v-model.trim="form.breed" @input="validateInput('breed')" @focus="validateInput('breed')"
                                                            placeholder="Raza..."></b-form-input>
                                                            <b-form-invalid-feedback v-if="showErrors.breed"
                                                            >{{ errorsMessages.breed }}</b-form-invalid-feedback>
                                                    </b-form-group>
                                                </b-col>
                                                <b-col cols="12" sm="5" xl="4" class="mt-3">
                                                    <b-form-group label="Selecciona su tamaño:" label-for="size">
                                                        <b-form-select id="size" v-model.trim="form.size" @input="validateInput('size')" @click="validateInput('size')"
                                                            class="form-select">
                                                            <option value="default" disabled>Tamaño...</option>
                                                            <option v-for="size in sizes" :key="size.id"
                                                                :value="size.value">
                                                                {{ size.text }}
                                                            </option>
                                                        </b-form-select>
                                                        <b-form-invalid-feedback v-if="showErrors.size"
                                                            >{{ errorsMessages.size }}</b-form-invalid-feedback>
                                                    </b-form-group>
                                                </b-col>
                                                <b-col cols="12" sm="7" xl="8">
                                                    <b-form-group label="Ingresa la edad de la mascota:" label-for="age"
                                                        class="mt-3">
                                                        <b-row>
                                                            <b-col cols="8" sm="7" xl="8">
                                                                <b-form-input id="age" v-model.trim="form.age" @input="validateInput('age')" @focus="validateInput('age')"
                                                                    placeholder="Edad..." type="number"></b-form-input>
                                                                    <b-form-invalid-feedback v-if="showErrors.age"
                                                                    >{{ errorsMessages.age }}</b-form-invalid-feedback>
                                                            </b-col>
                                                            <b-col cols="4" sm="5" xl="4">
                                                                <b-form-select id="ageUnit" v-model="form.ageUnit" @input="validateInput('ageUnit')" @click="validateInput('ageUnit')"
                                                                    class="form-select">
                                                                    <option value="default" disabled>Unidad</option>
                                                                    <option v-for="ageUnit in ageUnits"
                                                                        :key="ageUnit.value" :value="ageUnit.value">
                                                                        {{ ageUnit.text }}
                                                                    </option>
                                                                </b-form-select>
                                                                <b-form-invalid-feedback v-if="showErrors.ageUnit"
                                                                >{{ errorsMessages.ageUnit }}</b-form-invalid-feedback>
                                                            </b-col>
                                                        </b-row>
                                                    </b-form-group>
                                                </b-col>
                                                <b-col cols="12" sm="5" xl="4">
                                                    <b-form-group label="Selecciona su etapa:" label-for="stage"
                                                        class="mt-3">
                                                        <b-form-select id="stage" v-model.trim="form.stage" @input="validateInput('stage')" @click="validateInput('stage')"
                                                            class="form-select">
                                                            <option value="default" disabled>Etapa...</option>
                                                            <option v-for="stage in stages" :key="stage.id"
                                                                :value="stage.value">
                                                                {{ stage.text }}
                                                            </option>
                                                        </b-form-select>
                                                        <b-form-invalid-feedback v-if="showErrors.stage"
                                                        >{{ errorsMessages.stage }}</b-form-invalid-feedback>
                                                    </b-form-group>
                                                </b-col>
                                                <b-col cols="12" sm="7" xl="8">
                                                    <b-form-group label="Ingresa el peso de la mascota:"
                                                        label-for="weight" class="mt-3">
                                                        <b-row>
                                                            <b-col cols="8" sm="7" xl="8">
                                                                <b-form-input id="weight" v-model.trim="form.weight" @input="validateInput('weight')" @focus="validateInput('weight')"
                                                                    placeholder="Peso..." type="number"></b-form-input>
                                                                    <b-form-invalid-feedback v-if="showErrors.weight"
                                                                    >{{ errorsMessages.weight }}</b-form-invalid-feedback>
                                                            </b-col>
                                                            <b-col cols="4" sm="5" xl="4">
                                                                <b-form-select v-model="form.weightType" id="weightType" @input="validateInput('weightType')" @click="validateInput('weightType')"
                                                                    class="form-select">
                                                                    <option value="default" disabled>Unidad</option>
                                                                    <option v-for="weightType in weightTypes"
                                                                        :key="weightType.value"
                                                                        :value="weightType.value">
                                                                        {{ weightType.text }}
                                                                    </option>
                                                                </b-form-select>
                                                            </b-col>
                                                        </b-row>
                                                    </b-form-group>
                                                </b-col>
                                                <b-col cols="12" sm="5" xl="4">
                                                    <b-form-group label="Selecciona su sexo:" label-for="sex"
                                                        class="mt-3">
                                                        <b-form-select id="sex" v-model="form.sex" class="form-select" @input="validateInput('gender')" @click="validateInput('gender')">
                                                            <option value="default" disabled>Sexo...</option>
                                                            <option v-for="sex in sexes" :key="sex.id"
                                                                :value="sex.value">
                                                                {{ sex.text }}
                                                            </option>
                                                        </b-form-select>
                                                        <b-form-invalid-feedback v-if="showErrors.gender"
                                                        >{{errorsMessages.gender}}</b-form-invalid-feedback>
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
                <b-col cols="12" class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
                    <b-row>
                        <b-col cols="10" md="8" lg="6">
                            <b-card bg-variant="card-header-blue"
                                class="py-2 card-shadow relative-position form-card-title" no-body>
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
                                    <b-row>
                                        <b-col cols="12">
                                            <label class="mb-3">
                                                Selecciona las opciones que apliquen a la mascota:
                                            </label>
                                        </b-col>
                                        <b-col cols="12" md="6">
                                            <div class="mb-2 form-check">
                                                <input id="isVaccinated" type="checkbox" class="form-check-input"
                                                    v-model="form.isVaccinated" value="true" unchecked-value="false">
                                                <label class="ms-2 form-check-label checkbox-text" for="isVaccinated">
                                                    Mi mascota cuenta con todas sus vacunas
                                                </label>
                                            </div>
                                        </b-col>
                                        <b-col cols="12" md="6">
                                            <div class="mb-2 form-check">
                                                <input id="isDewormed" type="checkbox" class="form-check-input"
                                                    v-model="form.isDewormed" value="true" unchecked-value="false">
                                                <label class="ms-2 form-check-label checkbox-text" for="isDewormed">
                                                    Mi mascota se encuentra desparasitada
                                                </label>
                                            </div>
                                        </b-col>
                                        <b-col cols="12" md="6">
                                            <div class="mb-2 form-check">
                                                <input id="isSterilised" type="checkbox" class="form-check-input"
                                                    v-model="form.isSterilised" value="true" unchecked-value="false">
                                                <label class="ms-2 form-check-label checkbox-text" for="isSterilised">
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
                                        <b-col cols="12" md="6" class="mt-3">
                                            <b-form-group label="Escribe las enfermedades de la mascota (si aplica):"
                                                label-for="diseases">
                                                <b-form-input id="diseases" v-model.trim="tempDisease"
                                                    placeholder="Enfermedad o padecimiento..." @input="validateInput('tempDisease')"
                                                    @keyup.enter="addDisease()"></b-form-input>
                                                    <b-form-invalid-feedback v-if="showErrors.tempDisease"
                                                    >{{ errorsMessages.tempDisease }}</b-form-invalid-feedback>
                                                <div class="px-3 pb-2 mx-2 badge-container">
                                                    <b-badge v-for="(disease, index) in form.diseases" :key="index"
                                                        class="me-2 mt-2 d-inline-flex align-items-center justify-content-between"
                                                        variant="info-outline">
                                                        <span class="me-1">{{ disease }}</span>
                                                        <b-icon icon="x" @click="removeDisease(index)"
                                                            font-scale="1.4"></b-icon>
                                                    </b-badge>
                                                </div>
                                            </b-form-group>
                                        </b-col>
                                        <b-col cols="12" md="6" class="mt-3">
                                            <b-form-group label="Escribe las alergias de la mascota (si aplica):"
                                                label-for="allergies">
                                                <b-form-input id="allergies" v-model.trim="tempAllergy" @input="validateInput('tempAllergy')"
                                                    placeholder="Alergia..." @keyup.enter="addAllergy()"></b-form-input>
                                                    <b-form-invalid-feedback v-if="showErrors.tempAllergy"
                                                    >{{ errorsMessages.tempAllergy }}</b-form-invalid-feedback>
                                                <div class="px-3 pb-2 mx-2 badge-container">
                                                    <b-badge v-for="(allergy, index) in form.allergies" :key="index"
                                                        class="me-2 mt-2 d-inline-flex align-items-center justify-content-between"
                                                        variant="info-outline">
                                                        <span class="me-1">{{ allergy }}</span>
                                                        <b-icon icon="x" @click="removeAllergy(index)"
                                                            font-scale="1.4"></b-icon>
                                                    </b-badge>
                                                </div>
                                            </b-form-group>
                                        </b-col>
                                        <b-col cols="12" class="mt-3">
                                            <b-form-group
                                                label="Escribe comentarios adicionales sobre la salud de la mascota (si aplica):"
                                                label-for="comments">
                                                <b-form-textarea id="comments" v-model.trim="form.comments" @input="validateInput('comments')"
                                                    placeholder="Comentarios adicionales..."></b-form-textarea>
                                                <b-form-invalid-feedback v-if="showErrors.comments"
                                                >{{ errorsMessages.comments }}</b-form-invalid-feedback>
                                            </b-form-group>
                                        </b-col>
                                    </b-row>
                                </b-card-body>
                            </b-card>
                        </b-col>
                    </b-row>
                </b-col>
                <b-col cols="12" class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
                    <b-row>
                        <b-col cols="10" md="8" lg="6">
                            <b-card bg-variant="card-header-secondary-orange"
                                class="py-2 card-shadow relative-position form-card-title" no-body>
                                <div class="d-flex align-items-center ms-3 ms-md-4">
                                    <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
                                    <h4 class="mb-0 mt-1">Información adicional</h4>
                                </div>
                            </b-card>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col cols="12">
                            <b-card bg-variant="card-content-secondary-orange" class="card-shadow form-card-content">
                                <b-card-body>
                                    <b-row>
                                        <b-col cols="12" md="6">
                                            <b-form-group label="Escribe las características de la mascota:"
                                                label-for="characteristics"
                                                description="Describe la personalidad y el físico de la mascota con adjetivos calificativos">
                                                <b-form-input id="characteristics" v-model.trim="tempCharacteristic" @input="validateInput('characteristics')" @focus="validateInput('characteristics')"
                                                    placeholder="Características..."
                                                    @keyup.enter="addCharacteristic()"></b-form-input>
                                                    <b-form-invalid-feedback v-if="showErrors.characteristics"
                                                    >{{ errorsMessages.characteristics }}</b-form-invalid-feedback>
                                                <div class="px-3 pb-2 mx-2 badge-container">
                                                    <b-badge v-for="(characteristic, index) in form.characteristics"
                                                        :key="index"
                                                        class="me-2 mt-2 d-inline-flex align-items-center justify-content-between"
                                                        variant="orange-outline">
                                                        <span class="me-1">{{ characteristic }}</span>
                                                        <b-icon icon="x" @click="removeCharacteristic(index)"
                                                            font-scale="1.4"></b-icon>
                                                    </b-badge>
                                                </div>
                                            </b-form-group>
                                        </b-col>
                                        <b-col cols="12" md="6" class="mt-3 mt-md-0">
                                            <b-form-group label="Escriba los hábitos y cuidados de su mascota:"
                                                label-for="care">
                                                <b-form-input id="care" v-model.trim="tempCare"
                                                    placeholder="Hábitos y cuidados especiales..."
                                                    @keyup.enter="addCare()"></b-form-input>
                                                <div class="px-3 pb-2 mx-2 badge-container">
                                                    <b-badge v-for="(care, index) in form.care" :key="index"
                                                        class="me-2 mt-2 d-inline-flex align-items-center justify-content-between"
                                                        variant="orange-outline">
                                                        <span class="me-1">{{ care }}</span>
                                                        <b-icon icon="x" @click="removeCare(index)"
                                                            font-scale="1.4"></b-icon>
                                                    </b-badge>
                                                </div>
                                            </b-form-group>
                                        </b-col>
                                        <b-col cols="12" class="mt-3">
                                            <b-form-group label="Describe a la mascota y cuenta su historia:"
                                                label-for="description">
                                                <b-form-textarea id="description" v-model.trim="form.description" @input="validateInput('description')" @focus="validateInput('description')"
                                                    placeholder="Descripción e historia..."></b-form-textarea>
                                                    <b-form-invalid-feedback v-if="showErrors.description"
                                                    >{{ errorsMessages.description }}</b-form-invalid-feedback>
                                            </b-form-group>
                                        </b-col>
                                    </b-row>
                                </b-card-body>
                            </b-card>
                        </b-col>
                    </b-row>
                </b-col>
                <b-col cols="12" class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
                    <b-row class="px-5 px-sm-0 d-flex justify-content-end">
                        <b-col cols="12" sm="6" md="5" lg="4" xl="3">
                            <b-button variant="outline-dark-secondary-blue" @click="prepareSave"
                                class="d-flex align-items-center justify-content-between w-100">
                                <span class="me-2">Publicar</span>
                                <b-icon icon="arrow-up-right-circle" font-scale="1.3"></b-icon>
                            </b-button>
                        </b-col>
                        <b-col cols="12" sm="6" md="5" lg="4" xl="3" class="mt-3 mt-sm-0">
                            <b-button variant="outline-danger"
                                class="d-flex align-items-center justify-content-between w-100" @click="goBack">
                                <span class="me-2">Cancelar</span>
                                <b-icon icon="trash" font-scale="1.3"></b-icon>
                            </b-button>
                        </b-col>
                    </b-row>
                </b-col>
            </b-form>
        </b-row>
    </b-container>
</template>

<script>
import { isInvalidName } from '../../../../kernel/validations';
import Encabezado from "../../../../views/components/Encabezado.vue";
import Swal from 'sweetalert2';
import instance from '../../../../config/axios';
import { encrypt } from '../../../../kernel/hashFunctions';
export default {
    data() {
        return {
            tempDisease: "",
            tempAllergy: "",
            tempCharacteristic: "",
            tempCare: "",
            form: {
                mainImage: null,
                additionalImages: [],
                name: "",
                category: 0,
                breed: "",
                size: "default",
                age: null,
                ageUnit: "default",
                stage: "default",
                weight: null,
                weightType: "default",
                sex: "default",
                isVaccinated: false,
                isDewormed: false,
                isSterilised: false,
                microchip: false,
                diseases: [],
                allergies: [],
                comments: "",
                characteristics: [],
                care: [],
                description: "",
            },
            formPet:{},
            isValidForm: false,
            showErrors:{
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
            errorsMessages:{
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
        showImg() {
            if (this.form.mainImage) return URL.createObjectURL(this.form.mainImage);
            return "https://t3.ftcdn.net/jpg/07/01/59/38/240_F_701593826_ojYyX0cKXG3OzhoYkbeesqsQtaA6zBbj.jpg";
        },
        triggerMainImgSelector() {
            this.$refs.mainImageSelector.click();
        },
        selectImg() {
            this.form.mainImage = this.$refs.mainImageSelector.files[0];
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
            const files = this.$refs.additionalImageSelector.files;
            if (files.length > 0) {
                this.form.additionalImages.push(files[0]);
            }
        },
        removeAdditionalImg(index) {
            this.form.additionalImages.splice(index, 1);
        },
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
        validateInput(validateInput){
            console.log(this.form)
            let input;
            switch(validateInput){
                case 'name':
                    input = document.getElementById('name');
                    if(this.form.name == ''){
                        this.showErrors.name = true;
                        this.errorsMessages.name = 'El nombre de la mascota es requerido';
                        input.classList.add('is-invalid');
                    } else if(isInvalidName(this.form.name)){
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
                    if(this.form.category == 0){
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
                    if(this.form.breed == ''){
                        this.showErrors.breed = true;
                        this.errorsMessages.breed = 'La raza de la mascota es requerida';
                        input.classList.add('is-invalid');
                    } else if(isInvalidName(this.form.breed)){
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
                    if(this.form.size == 'default'){
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
                    if(this.form.age == null){
                        this.showErrors.age = true;
                        this.errorsMessages.age = 'La edad de la mascota es requerida';
                        input.classList.add('is-invalid');
                    }else if(this.form.age < 0 || this.form.age == 0){
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
                    if(this.form.ageUnit == 'default'){
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
                    if(this.form.weight == null){
                        this.showErrors.weight = true;
                        this.errorsMessages.weight = 'El peso de la mascota es requerido';
                        input.classList.add('is-invalid');
                    }else if(this.form.weight < 0 || this.form.weight == 0){
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
                    if(this.form.weightType == 'default'){
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
                    if(this.form.sex == 'default'){
                        input.classList.add('is-invalid');
                        this.showErrors.gender = true;
                        this.errorsMessages.gender = 'El sexo de la mascota es requerido';
                    }else{
                        this.showErrors.gender = false;
                        this.errorsMessages.gender = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;

                case 'tempDisease':
                    input = document.getElementById('diseases');
                    if(this.tempDisease.length < 3){
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
                    if(this.tempAllergy.length < 3){
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
                    if(this.form.comments.length < 50 || this.form.comments.length > 500){
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
                    if(this.form.characteristics.length < 2){
                        this.showErrors.characteristics = true;
                        this.errorsMessages.characteristics = 'Las características de la mascota no son válidas, ingresa al menos 3 características';
                        input.classList.add('is-invalid');
                    }else if(this.tempCharacteristic.length < 3){
                        this.showErrors.characteristics = true;
                        this.errorsMessages.characteristics = 'Las características de la mascota no son válidas, ingresa al menos 3 caracteres';
                        input.classList.add('is-invalid');
                    } else{
                        this.showErrors.characteristics = false;
                        this.errorsMessages.characteristics = '';
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                    break;

                case 'stage':
                    input = document.getElementById('stage');
                    if(this.form.stage == 'default'){
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
                    if(this.form.description.length < 100 || this.form.description.length > 1500){
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
        async prepareSave(){
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

            if(this.form.mainImage == null){
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
            }).then(async (result)=>{
                if(result.isConfirmed){
                    Swal.showLoading();
                    const reader = new FileReader();
                    reader.readAsDataURL(this.form.mainImage);
                    reader.onloadend = () => {
                        this.formPet ={
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
                        console.log(this.formPet)
                        this.addPet(this.formPet);
                    }
                }
            })
        },
        async addPet(){
            this.formPet.category = await encrypt(this.formPet.category);

            let response = await instance.post('/pet/save', this.formPet);
            console.log(response);
        }
    },
    components: {
        Encabezado,
    },
};
</script>

<style scoped>
.card-shadow {
    box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
        rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
}

.main-img {
    width: 280px;
    height: 240px;
    object-fit: cover;
    border: 4px solid black;
}

.additional-img-container {
    width: 100%;
    padding-top: 100%;
    position: relative;
    overflow: hidden;
    border: 2px solid black;
    border-radius: 5px;
}

.additional-img {
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;
    object-fit: cover;
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
    background-color: rgba(169, 61, 61, 0.6);
    color: #6E2131;
}

.btn-remove:hover {
    background-color: rgba(169, 61, 61, 1);
}

.btn-remove:active {
    background-color: #742434;
    color: #49151f;
}

.position-center {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}

.badge-container {
    background-color: #F2F2F2;
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 10px;
}

@media (min-width: 576px) {
    .custom-row-width {
        width: 80%;
        margin-right: auto;
        margin-left: auto;
    }
}

@media (min-width: 768px) {
    .custom-row-width {
        width: 60%;
        margin-right: auto;
        margin-left: auto;
    }
}

@media (min-width: 992px) {
    .custom-row-width {
        width: 100%;
        margin-right: auto;
        margin-left: auto;
    }
}
</style>