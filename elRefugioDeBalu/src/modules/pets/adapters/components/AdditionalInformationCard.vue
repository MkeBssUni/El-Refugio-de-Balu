<template>
    <b-col cols="12" class="px-2 px-sm-4 px-xl-5 my-4 mb-sm-5">
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
                        <b-row>
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
                                                placeholder="Característica..."></b-form-input>
                                        </b-col>
                                        <b-col cols="2" sm="4" class="align-self-end mb-1 mb-sm-0">
                                            <b-button variant="dark-gray" type="button"
                                                class="d-flex align-items-center justify-content-between add-badge-btn">
                                                <span
                                                    class="d-none d-sm-flex align-items-center justify-content-between me-2">Agregar</span>
                                                <b-icon icon="plus-circle"></b-icon>
                                            </b-button>
                                        </b-col>
                                        <b-col cols="10" sm="8">
                                            <div class="px-2 pb-2 mx-1 form-badge-container border-orange">
                                                <b-badge
                                                    class="me-2 mt-2 d-inline-flex align-items-center justify-content-between"
                                                    variant="orange-outline">
                                                    <span class="me-1">Característica</span>
                                                    <b-icon icon="x" font-scale="1.4"></b-icon>
                                                </b-badge>
                                            </div>
                                        </b-col>
                                    </b-row>
                                </b-form-group>
                            </b-col>
                            <b-col cols="12" md="6" class="mt-3 mt-md-0">
                                <b-form-group>
                                    <b-row>
                                        <b-col cols="12">
                                            <label class="mb-2" for="care">
                                                Escribe los hábitos y cuidados especiales que necesita la mascota:
                                                <span class="required-asterisk">*</span>
                                            </label>
                                        </b-col>
                                        <b-col cols="10" sm="8">
                                            <b-form-input id="care" v-model.trim="care"
                                                placeholder="Hábito o cuidado..."></b-form-input>
                                        </b-col>
                                        <b-col cols="2" sm="4" class="align-self-end mb-1 mb-sm-0">
                                            <b-button variant="dark-gray" type="button"
                                                class="d-flex align-items-center justify-content-between add-badge-btn">
                                                <span
                                                    class="d-none d-sm-flex align-items-center justify-content-between me-2">Agregar</span>
                                                <b-icon icon="plus-circle"></b-icon>
                                            </b-button>
                                        </b-col>
                                        <b-col cols="10" sm="8">
                                            <div class="px-2 pb-2 mx-1 form-badge-container border-orange">
                                                <b-badge
                                                    class="me-2 mt-2 d-inline-flex align-items-center justify-content-between"
                                                    variant="orange-outline">
                                                    <span class="me-1">Hábito o cuidado</span>
                                                    <b-icon icon="x" font-scale="1.4"></b-icon>
                                                </b-badge>
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
                                        placeholder="Descripción e historia..."></b-form-textarea>
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
export default {
    data() {
        return {
            characteristic: '',
            care: '',
            form: {
                description: ''
            }
        }
    }
}
</script>

<style></style>