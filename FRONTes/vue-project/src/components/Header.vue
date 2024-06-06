<template>
    <header class="d-flex justify-content-between align-items-center p-3 mb-4 bg-primary text-white">
      <div class="d-flex align-items-center">
        <h1 class="m-0">ElectroStore</h1>
      </div>
      <nav class="d-flex">
        <router-link to="/home" class="btn btn-outline-light mx-2">Главная</router-link>
        <router-link to="/cart" class="btn btn-outline-light mx-2">Корзина</router-link>
        <router-link to="/discounts" class="btn btn-outline-light mx-2">Скидки</router-link>
        <button @click="logOut()" class="btn btn-outline-light mx-2">Выход</button>
      </nav>
    </header>
</template>
<script setup></script>
<script>


export default {
  data() {
    return {
      
    };
  },
  methods: {
    logOut() {
      this.$cookies.remove('jwt');
      window.location.href = "/login";
    },
    
  }
  
}
</script>

<style scoped>
html,
body,
#app {
  height: 100%;
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

.container {
  padding: 20px;
}

.card {
  border: 1px solid #d6a100;
  border-radius: 4px;
  box-shadow: 0 0 10px rgba(255, 193, 7, 0.1);
}

.card-title, .card-text {
  color: #333;
}
</style>
