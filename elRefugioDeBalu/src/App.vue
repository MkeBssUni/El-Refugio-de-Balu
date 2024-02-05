<script>
import DinamicNavbar from './views/DinamicNavbar.vue';

components: {
  DinamicNavbar
}

export default {
  components: { DinamicNavbar },
  name: 'App',
  data() {
    return {
      role: ''
    }
  }
}

</script>

<template>
  <div id="app">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

    <header>
      <dinamic-navbar :role="role"/>
    </header>
    <router-view/>
  </div>
</template>

<style scoped></style>