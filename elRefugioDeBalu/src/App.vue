<script>
import DinamicNavigation from './views/components/DinamicNavigation.vue'
export default {
  components: { DinamicNavigation },
  name: 'App',
  data() {
    return {
      userId: this.getUserIdFromStorage()
    }
  },
  created() {
    window.addEventListener('storage', this.handleStorageChange);
    this.intervalCheck = setInterval(this.checkLocalStorage, 1000);
  },
  beforeDestroy() {
    window.removeEventListener('storage', this.handleStorageChange);
    clearInterval(this.intervalCheck);
  },
  methods: {
    getUserIdFromStorage() {
      return localStorage.getItem('userId');
    },
    handleStorageChange(event) {
      if (event.key === 'userId') {
        this.userId = this.getUserIdFromStorage();
      }
    },
    checkLocalStorage() {
      const currentUserId = this.getUserIdFromStorage();
      if (this.userId !== currentUserId) {
        this.userId = currentUserId;
      }
    },
    redirectUser() {
      this.$router.push("/")
    }
  },
  watch: {
    userId(newVal) {
      if (!newVal) {
        this.redirectUser();
      }
    }
  }
}
</script>

<template>
  <div id="app">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <header>
      <dinamic-navigation v-if="!$route.meta.hideNavigation" />
    </header>
    <main>
      <router-view />
    </main>
  </div>
</template>