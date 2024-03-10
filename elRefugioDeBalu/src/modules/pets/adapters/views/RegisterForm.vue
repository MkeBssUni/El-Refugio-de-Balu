<template>
    <b-container fluid>
        <b-row class="px-5">
            <b-form>
                <b-col cols="12" class="px-5 my-5">
                    <b-card bg-variant="card-header-orange" class="w-50 py-2 card-shadow" no-body>
                        <div class="d-flex align-items-center ms-4">
                            <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
                            <h4 class="mb-0 mt-1">Información general de la mascota</h4>
                        </div>
                    </b-card>
                    <b-card bg-variant="card-content-orange" class="card-shadow">
                        <b-card-body>
                            <b-row>
                                <b-col cols="4">
                                    <b-row>
                                        <b-col cols="12">
                                            <b-img :src="showImg()" class="main-img" alt="Imagen principal seleccionada"
                                                fluid rounded center></b-img>
                                        </b-col>
                                    </b-row>
                                    <b-row class="px-4 my-3">
                                        <b-col cols="3">
                                            <b-img src="https://cdn-icons-png.flaticon.com/512/1160/1160358.png"
                                                alt="Imagen principal" fluid rounded center
                                                style="width: 280px; height: auto;">
                                            </b-img>
                                        </b-col>
                                        <b-col cols="3">
                                            <b-img src="https://cdn-icons-png.flaticon.com/512/1160/1160358.png"
                                                alt="Imagen principal" fluid rounded center
                                                style="width: 280px; height: auto;">
                                            </b-img>
                                        </b-col>
                                        <b-col cols="3">
                                            <b-img src="https://cdn-icons-png.flaticon.com/512/1160/1160358.png"
                                                alt="Imagen principal" fluid rounded center
                                                style="width: 280px; height: auto;">
                                            </b-img>
                                        </b-col>
                                        <b-col cols="3">
                                            <b-img src="https://cdn-icons-png.flaticon.com/512/1160/1160358.png"
                                                alt="Imagen principal" fluid rounded center
                                                style="width: 280px; height: auto;">
                                            </b-img>
                                        </b-col>
                                    </b-row>
                                </b-col>
                                <b-col cols="8" class="d-flex align-items-center">
                                    <b-row>
                                        <b-col cols="8">
                                            <b-form-group label="Ingresa el nombre de la mascota:" label-for="name">
                                                <b-form-input id="name" v-model.trim="form.name"
                                                    placeholder="Nombre..."></b-form-input>
                                            </b-form-group>
                                        </b-col>
                                        <b-col cols="4">
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
                                        <b-col cols="8">
                                            <b-form-group label="Ingresa la raza de la mascota:" label-for="breed"
                                                class="mt-3">
                                                <b-form-input id="breed" v-model.trim="form.breed"
                                                    placeholder="Raza..."></b-form-input>
                                            </b-form-group>
                                        </b-col>
                                        <b-col cols="4">
                                            <b-form-group label="Selecciona su tamaño:" label-for="size" class="mt-3">
                                                <b-form-select id="size" v-model.trim="form.size" class="form-select">
                                                    <option value="default" disabled>Tamaño...</option>
                                                    <option v-for="size in sizes" :key="size.id" :value="size.value">
                                                        {{ size.text }}
                                                    </option>
                                                </b-form-select>
                                            </b-form-group>
                                        </b-col>
                                        <b-col cols="8">
                                            <b-row>
                                                <b-col cols="8">
                                                    <b-form-group label="Ingresa la edad de la mascota:" label-for="age"
                                                        class="mt-3">
                                                        <b-form-input id="age" v-model.trim="form.age"
                                                            placeholder="Edad..." type="number"></b-form-input>
                                                    </b-form-group>
                                                </b-col>
                                                <b-col cols="4">
                                                    <b-form-group class="mt-3">
                                                        <b-form-select v-model="form.ageType" class="form-select mt-4">
                                                            <option value="default" disabled>Unidad...</option>
                                                            <option v-for="ageType in ageTypes" :key="ageType.value"
                                                                :value="ageType.value">
                                                                {{ ageType.text }}
                                                            </option>
                                                        </b-form-select>
                                                    </b-form-group>
                                                </b-col>
                                            </b-row>
                                        </b-col>
                                        <b-col cols="4">
                                            <b-form-group label="Selecciona su etapa de vida:" label-for="stage"
                                                class="mt-3">
                                                <b-form-select id="stage" v-model.trim="form.stage" class="form-select">
                                                    <option value="default" disabled>Etapa...</option>
                                                    <option v-for="stage in stages" :key="stage.id"
                                                        :value="stage.value">
                                                        {{ stage.text }}
                                                    </option>
                                                </b-form-select>
                                            </b-form-group>
                                        </b-col>
                                        <b-col cols="8">
                                            <b-row>
                                                <b-col cols="8">
                                                    <b-form-group label="Ingresa el peso de la mascota:"
                                                        label-for="weight" class="mt-3">
                                                        <b-form-input id="weight" v-model.trim="form.weight"
                                                            placeholder="Peso..." type="number"></b-form-input>
                                                    </b-form-group>
                                                </b-col>
                                                <b-col cols="4">
                                                    <b-form-group class="mt-3">
                                                        <b-form-select v-model="form.weightType"
                                                            class="form-select mt-4">
                                                            <option value="default" disabled>Unidad...</option>
                                                            <option v-for="weightType in weightTypes"
                                                                :key="weightType.value" :value="weightType.value">
                                                                {{ weightType.text }}
                                                            </option>
                                                        </b-form-select>
                                                    </b-form-group>
                                                </b-col>
                                            </b-row>
                                        </b-col>
                                        <b-col cols="4">
                                            <b-form-group label="Selecciona su sexo:" label-for="sex" class="mt-3">
                                                <b-form-select id="sex" v-model="form.sex" class="form-select">
                                                    <option value="default" disabled>Sexo...</option>
                                                    <option v-for="sex in sexes" :key="sex.id" :value="sex.value">
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
                <b-col cols="12" class="px-5 my-5">
                    <b-card bg-variant="card-header-blue" class="w-50 py-2 card-shadow" no-body>
                        <div class="d-flex align-items-center ms-4">
                            <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
                            <h4 class="mb-0 mt-1">Información médica de la mascota</h4>
                        </div>
                    </b-card>
                    <b-card bg-variant="card-content-blue" class="card-shadow">
                        <b-card-body>
                            <b-row>
                                <b-col cols="12">
                                    <b-form-group>
                                        <b-row>
                                            <b-col cols="12">
                                                <label class="mb-3">
                                                    Selecciona las opciones que apliquen a la mascota:
                                                </label>
                                            </b-col>
                                            <b-col cols="6">
                                                <div class="mb-2 form-check">
                                                    <input id="isVaccinated" type="checkbox" class="form-check-input"
                                                        v-model="form.isVaccinated" value="true"
                                                        unchecked-value="false">
                                                    <label class="ms-2 form-check-label checkbox-text"
                                                        for="isVaccinated">
                                                        Mi mascota cuenta con todas sus vacunas
                                                    </label>
                                                </div>
                                            </b-col>
                                            <b-col cols="6">
                                                <div class="mb-2 form-check">
                                                    <input id="isDewormed" type="checkbox" class="form-check-input"
                                                        v-model="form.isDewormed" value="true" unchecked-value="false">
                                                    <label class="ms-2 form-check-label checkbox-text" for="isDewormed">
                                                        Mi mascota se encuentra desparasitada
                                                    </label>
                                                </div>
                                            </b-col>
                                            <b-col cols="6">
                                                <div class="mb-2 form-check">
                                                    <input id="isSterilised" type="checkbox" class="form-check-input"
                                                        v-model="form.isSterilised" value="true"
                                                        unchecked-value="false">
                                                    <label class="ms-2 form-check-label checkbox-text"
                                                        for="isSterilised">
                                                        Mi mascota se encuentra esterilizada
                                                    </label>
                                                </div>
                                            </b-col>
                                            <b-col cols="6">
                                                <div class="mb-2 form-check">
                                                    <input id="microchip" type="checkbox" class="form-check-input"
                                                        v-model="form.microchip" value="true" unchecked-value="false">
                                                    <label class="ms-2 form-check-label checkbox-text" for="microchip">
                                                        Mi mascota cuenta con microchip
                                                    </label>
                                                </div>
                                            </b-col>
                                        </b-row>
                                        <b-row class="mt-3">
                                            <b-col cols="6">
                                                <b-form-group
                                                    label="Escribe las enfermedades o padecimientos de la mascota (si aplica):"
                                                    label-for="diseases">
                                                    <b-form-input id="diseases" v-model.trim="form.diseases"
                                                        placeholder="Enfermedad o padecimiento..."></b-form-input>
                                                </b-form-group>
                                            </b-col>
                                            <b-col cols="6">
                                                <b-form-group label="Escribe las alergias de la mascota (si aplica):"
                                                    label-for="allergies">
                                                    <b-form-input id="allergies" v-model.trim="form.allergies"
                                                        placeholder="Alergia..."></b-form-input>
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
                                    </b-form-group>
                                </b-col>
                            </b-row>
                        </b-card-body>
                    </b-card>
                </b-col>
                <b-col cols="12" class="px-5 my-5">
                    <b-card bg-variant="card-header-secondary-orange" class="w-50 py-2 card-shadow" no-body>
                        <div class="d-flex align-items-center ms-4">
                            <i class="material-icons me-2" style="font-size: 1.5rem">pets</i>
                            <h4 class="mb-0 mt-1">Información adicional de la mascota</h4>
                        </div>
                    </b-card>
                    <b-card bg-variant="card-content-orange" class="card-shadow">
                        <b-card-body>
                            <b-row>
                                <b-col cols="6">
                                    <b-form-group label="Escribe las características de la mascota:"
                                        label-for="characteristics" description="Describa de la personalidad y el físico de la mascota">
                                        <b-form-input id="characteristics" v-model.trim="form.characteristics"
                                            placeholder="Características..."></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="6">
                                    <b-form-group label="Indique cuáles son los hábitos y cuidados de su mascota:"
                                        label-for="care">
                                        <b-form-input id="care" v-model.trim="form.care"
                                            placeholder="Hábitos y cuidados especiales..."></b-form-input>
                                    </b-form-group>
                                </b-col>
                                <b-col cols="12" class="mt-3">
                                    <b-form-group
                                        label="Describe a la mascota y cuenta su historia:"
                                        label-for="description">
                                        <b-form-textarea id="description" v-model.trim="form.description"
                                            placeholder="Descripción e historia..."></b-form-textarea>
                                    </b-form-group>
                                </b-col>
                            </b-row>
                        </b-card-body>
                    </b-card>
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
            form: {
                mainImage: null,
                images: [],
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
                diseases: "",
                allergies: "",
                comments: "",
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
        triggerFileInput() {
            this.$refs.fileInput.click();
        },
        showImg() {
            if (this.form.mainImage) return URL.createObjectURL(this.form.mainImage);
            return "https://cdn-icons-png.flaticon.com/512/1160/1160358.png"
        },
        addImageField() {
            if (this.form.images.length < 4) {
                this.form.images.push(null);
            } else {
                alert('You can only upload a maximum of 4 additional images.');
            }
        },
        removeImage(index) {
            this.form.images.splice(index, 1);
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
}
</style>