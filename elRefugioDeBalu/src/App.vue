<script>
import DinamicNavigation from './views/components/DinamicNavigation.vue'
export default {
  components: { DinamicNavigation },
  name: 'App',
  data() {
    return {}
  }
}

</script>

<template>
  <div id="app">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <header>
      <dinamic-navigation />
    </header>
    <main>
      <router-view />
    </main>
  </div>
</template>

<style scoped></style>