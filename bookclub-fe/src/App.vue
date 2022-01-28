<template>
  <menu-bar />
  <router-view />
</template>

<script>
import MenuBar from './components/MenuBar.vue'
export default {
  components: {
    MenuBar
  }
}
</script>

<style lang="scss"></style>
