<template>
    <b-container fluid>
        <b-row class="px-3 px-sm-4 px-xl-5">
            <b-form>
                <b-col cols="12" class="px-lg-5 my-5">
                    <b-row>
                        <b-col cols="10" md="8" lg="6">
                            <b-card bg-variant="card-header-orange" class="py-2" no-body>
                                <div class="d-flex align-items-center ms-3 ms-md-4">
                                    <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
                                    <h4 class="mb-0 mt-1">Información general</h4>
                                </div>
                            </b-card>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col cols="12">
                            <b-card bg-variant="card-content-orange" class="card-shadow">
                                <b-card-body>
                                    <b-row>
                                        <b-col cols="12" lg="5" xl="4" class="px-3">
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
                                                        <b-form-input id="name" v-model.trim="form.name"
                                                            placeholder="Nombre..."></b-form-input>
                                                    </b-form-group>
                                                </b-col>
                                                <b-col cols="12" sm="5" xl="4" class="mt-3 mt-xl-0">
                                                    <b-form-group label="Selecciona su especie:" label-for="category">
                                                        <b-form-select id="category" v-model.trim="form.category"
                                                            class="form-select">
                                                            <option value="0" disabled>Especie...</option>
                                                            <option v-for="category in categories" :key="category.id"
                                                                :value="category.id">
                                                                {{ category.name }}
                                                            </option>
                                                        </b-form-select>
                                                    </b-form-group>
                                                </b-col>
                                                <b-col cols="12" sm="7" xl="8" class="mt-3">
                                                    <b-form-group label="Ingresa la raza de la mascota:"
                                                        label-for="breed">
                                                        <b-form-input id="breed" v-model.trim="form.breed"
                                                            placeholder="Raza..."></b-form-input>
                                                    </b-form-group>
                                                </b-col>
                                                <b-col cols="12" sm="5" xl="4" class="mt-3">
                                                    <b-form-group label="Selecciona su tamaño:" label-for="size">
                                                        <b-form-select id="size" v-model.trim="form.size"
                                                            class="form-select">
                                                            <option value="default" disabled>Tamaño...</option>
                                                            <option v-for="size in sizes" :key="size.id"
                                                                :value="size.value">
                                                                {{ size.text }}
                                                            </option>
                                                        </b-form-select>
                                                    </b-form-group>
                                                </b-col>
                                                <b-col cols="12" sm="7" xl="8">
                                                    <b-form-group label="Ingresa la edad de la mascota:" label-for="age"
                                                        class="mt-3">
                                                        <b-row>
                                                            <b-col cols="8" sm="7" xl="8">
                                                                <b-form-input id="age" v-model.trim="form.age"
                                                                    placeholder="Edad..." type="number"></b-form-input>
                                                            </b-col>
                                                            <b-col cols="4" sm="5" xl="4">
                                                                <b-form-select v-model="form.ageType"
                                                                    class="form-select">
                                                                    <option value="default" disabled>Unidad</option>
                                                                    <option v-for="ageType in ageTypes"
                                                                        :key="ageType.value" :value="ageType.value">
                                                                        {{ ageType.text }}
                                                                    </option>
                                                                </b-form-select>
                                                            </b-col>
                                                        </b-row>
                                                    </b-form-group>
                                                </b-col>
                                                <b-col cols="12" sm="5" xl="4">
                                                    <b-form-group label="Selecciona su etapa:" label-for="stage"
                                                        class="mt-3">
                                                        <b-form-select id="stage" v-model.trim="form.stage"
                                                            class="form-select">
                                                            <option value="default" disabled>Etapa...</option>
                                                            <option v-for="stage in stages" :key="stage.id"
                                                                :value="stage.value">
                                                                {{ stage.text }}
                                                            </option>
                                                        </b-form-select>
                                                    </b-form-group>
                                                </b-col>
                                                <b-col cols="12" sm="7" xl="8">
                                                    <b-form-group label="Ingresa el peso de la mascota:"
                                                        label-for="weight" class="mt-3">
                                                        <b-row>
                                                            <b-col cols="8" sm="7" xl="8">
                                                                <b-form-input id="weight" v-model.trim="form.weight"
                                                                    placeholder="Peso..." type="number"></b-form-input>
                                                            </b-col>
                                                            <b-col cols="4" sm="5" xl="4">
                                                                <b-form-select v-model="form.weightType"
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
                                                        <b-form-select id="sex" v-model="form.sex" class="form-select">
                                                            <option value="default" disabled>Sexo...</option>
                                                            <option v-for="sex in sexes" :key="sex.id"
                                                                :value="sex.value">
                                                                {{ sex.text }}
                                                            </option>
                                                        </b-form-select>
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
                <b-col cols="12" class="px-lg-5 my-5">
                    <b-row>
                        <b-col cols="10" md="8" lg="6">
                            <b-card bg-variant="card-header-blue" class="py-2 card-shadow" no-body>
                                <div class="d-flex align-items-center ms-3 ms-md-4">
                                    <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
                                    <h4 class="mb-0 mt-1">Información médica</h4>
                                </div>
                            </b-card>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col cols="12">
                            <b-card bg-variant="card-content-blue" class="card-shadow">
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
                                            <b-form-group
                                                label="Escribe las enfermedades de la mascota (si aplica):"
                                                label-for="diseases">
                                                <b-form-input id="diseases" v-model.trim="tempDisease"
                                                    placeholder="Enfermedad o padecimiento..."
                                                    @keyup.enter="addDisease()"></b-form-input>
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
                                                <b-form-input id="allergies" v-model.trim="tempAllergy"
                                                    placeholder="Alergia..." @keyup.enter="addAllergy()"></b-form-input>
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
                                                <b-form-textarea id="comments" v-model.trim="form.comments"
                                                    placeholder="Comentarios adicionales..."></b-form-textarea>
                                            </b-form-group>
                                        </b-col>
                                    </b-row>
                                </b-card-body>
                            </b-card>
                        </b-col>
                    </b-row>
                </b-col>
                <b-col cols="12" class="px-lg-5 my-5">
                    <b-row>
                        <b-col cols="10" md="8" lg="6">
                            <b-card bg-variant="card-header-secondary-orange" class="py-2 card-shadow" no-body>
                                <div class="d-flex align-items-center ms-3 ms-md-4">
                                    <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
                                    <h4 class="mb-0 mt-1">Información adicional</h4>
                                </div>
                            </b-card>
                        </b-col>
                    </b-row>
                    <b-row>
                        <b-col cols="12">
                            <b-card bg-variant="card-content-orange" class="card-shadow">
                                <b-card-body>
                                    <b-row>
                                        <b-col cols="6">
                                            <b-form-group label="Escribe las características de la mascota:"
                                                label-for="characteristics"
                                                description="Describa de la personalidad y el físico de la mascota">
                                                <b-form-input id="characteristics" v-model.trim="tempCharacteristic"
                                                    placeholder="Características..."
                                                    @keyup.enter="addCharacteristic()"></b-form-input>
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
                                        <b-col cols="6">
                                            <b-form-group
                                                label="Indique cuáles son los hábitos y cuidados de su mascota:"
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
                                                <b-form-textarea id="description" v-model.trim="form.description"
                                                    placeholder="Descripción e historia..."></b-form-textarea>
                                            </b-form-group>
                                        </b-col>
                                    </b-row>
                                </b-card-body>
                            </b-card>
                        </b-col>
                    </b-row>
                </b-col>
            </b-form>
        </b-row>
    </b-container>
</template>

<script>
import Encabezado from "../../../../views/components/Encabezado.vue";
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
                ageType: "default",
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
            categories: [
                { id: 1, name: "Perro" },
                { id: 2, name: "Gato" },
                { id: 3, name: "Conejo" },
                { id: 4, name: "Pájaro" },
            ],
            sizes: [
                { value: "Pequeño", text: "Pequeño" },
                { value: "Mediano", text: "Mediano" },
                { value: "Grande", text: "Grande" },
            ],
            ageTypes: [
                { value: "meses", text: "Meses" },
                { value: "años", text: "Años" },
            ],
            stages: [
                { value: "Cachorro", text: "Cachorro" },
                { value: "Joven", text: "Joven" },
                { value: "Adulto", text: "Adulto" },
                { value: "Senior", text: "Senior" },
            ],
            weightTypes: [
                { value: "kg", text: "Kilogramos" },
                { value: "g", text: "Gramos" },
            ],
            sexes: [
                { value: "Macho", text: "Macho" },
                { value: "Hembra", text: "Hembra" },
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
        },
        removeCare(index) {
            this.form.care.splice(index, 1);
        },
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