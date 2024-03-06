<template>
  <div>
    <div class="card encabezadoColorform">
      <h4 style="margin-left: 2rem">
        <i
          class="material-icons ms-2"
          style="font-size: larger; vertical-align: middle"
          >pets</i
        >
        Registro de categoria
      </h4>
    </div>
    <b-card-group deck>
      <b-card class="contentform">
        <b-row>
          <b-col>
            <b-row>
              <b-col>
                <b-form-group
                  label="Categoria"
                  :invalid-feedback="invalidFeedback"
                  :state="state"
                >
                  <b-form-input
                    id="input-1"
                    v-model="this.SaveCategoryDto.name"
                    :state="state"
                    trim
                  ></b-form-input>
                </b-form-group>
              </b-col>
            </b-row>
            <b-row>
              <b-col>
                <b-form-group
                  label="Descripción"
                  :invalid-feedback="invalidFeedback"
                  :state="state"
                >
                  <b-form-textarea
                    id="input-1"
                    v-model="this.SaveCategoryDto.name"
                    :state="state"
                    trim
                    rows="3"
                  ></b-form-textarea>
                </b-form-group>
              </b-col>
            </b-row>
          </b-col>
          <b-col>
            <b-form-file class="form-file-control"
              multiple
              :file-name-formatter="formatNames"
            ></b-form-file>
          </b-col>
        </b-row>
      </b-card>
    </b-card-group>
  </div>
</template>

<script>
export default {
  name: "SaveCategory",
  data() {
    return {
      SaveCategoryDto: {
        name: "",
        description: "",
        image: "",
      },
    };
  },
  methods: {},
};
</script>

<style>
</style>