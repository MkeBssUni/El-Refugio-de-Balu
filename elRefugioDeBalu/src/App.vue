<script>
import DinamicNavigation from './views/components/DinamicNavigation.vue'
export default {
  components: { DinamicNavigation },
  name: 'App',
  data() {
    return {

      /* role: ''
      role: 'ADOPTANTE'
      role: 'MODERADOR' */
      role: 'ADMINISTRADOR'
    }
  }
}

</script>

<template>
  <div id="app">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

    <header>
      <dinamic-navigation :role="role"/>
    </header>
    <router-view/>
  </div>
</template>

<style scoped></style>