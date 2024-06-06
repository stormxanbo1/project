<template>
  <div class="page-container">
    <header class="d-flex justify-content-between align-items-center p-3 mb-4 bg-primary text-white">
      <div class="d-flex align-items-center">
        <h1 class="m-0">ElectroStore</h1>
      </div>
      <nav class="d-flex">
        <router-link to="/home" class="btn btn-outline-light mx-2">Главная</router-link>
        <router-link to="/cart" class="btn btn-outline-light mx-2">Корзина</router-link>
        <router-link to="/profile" class="btn btn-outline-light mx-2">Профиль</router-link>
      </nav>
    </header>
    <main class="content">
      <slot></slot>
    </main>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {
    logOut() {
      this.$cookies.remove('jwt');
      window.location.href = "/login";
    }
  }
};
</script>

<style scoped>
html,
body,
#app {
  height: 100%;
  margin: 0;
  padding: 0;
}

.page-container {
  padding: 0 0 0 ;
  display: flex;
  flex-direction: column;
}

header {
  width: 100%;
  background: linear-gradient(135deg, #ff8c42, #ff3d00);
  color: #fff;
  padding: 10px 20px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

h1 {
  margin: 0;
}

nav .btn {
  margin-right: 10px;
}

.content {
  flex: 1;
  padding: 20px;
}

</style>
