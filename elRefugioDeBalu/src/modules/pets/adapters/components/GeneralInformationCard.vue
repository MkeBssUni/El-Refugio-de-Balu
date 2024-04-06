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
                                            accept="image/jpeg" @change="selectImg()">
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
                                                    accept="image/jpeg" @change="selectAdditionalImg()">
                                                <b-button @click="triggerAdditionalImgSelector()"
                                                    class="btn-add d-flex align-items-center justify-content-center p-1">
                                                    <b-icon icon="plus" font-scale="2.5"></b-icon>
                                                </b-button>
                                            </b-col>
                                            <b-col cols="9" md="7" lg="9"
                                                v-if="form.additionalImages.length == 0"
                                                class="d-flex align-items-center">
                                                <label>Agrega imágenes adicionales</label>
                                            </b-col>
                                        </b-row>
                                    </b-col>
                                </b-row>
                            </b-col>
                            <b-col class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
                                
                            </b-col>
                        </b-row>
                    </b-card-body>
                </b-card>
            </b-col>
        </b-row>
    </b-col>
</template>

<script>
export default {
    data() {
        return {
            form: {
                mainImage: null,
                additionalImages: [],
                /* name: "",
                category: "0",
                breed: "",
                size: "default",
                age: "",
                ageUnit: "default",
                stage: "default",
                weight: "",
                weightType: "default" */
            },
            categories: [
                { id: 1, name: "Perro" },
                { id: 2, name: "Gato" },
                { id: 3, name: "Otro" }
            ],
            sizes: [
                { id: 1, value: "small", text: "Pequeño" },
                { id: 2, value: "medium", text: "Mediano" },
                { id: 3, value: "large", text: "Grande" }
            ],
            ageUnits: [
                { value: "months", text: "Meses" },
                { value: "years", text: "Años" }
            ],
            stages: [
                { id: 1, value: "puppy", text: "Cachorro" },
                { id: 2, value: "adult", text: "Adulto" },
                { id: 3, value: "senior", text: "Adulto mayor" }
            ],
            weightTypes: [
                { value: "kg", text: "Kg" },
                { value: "lb", text: "Lb" }
            ]
        }
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