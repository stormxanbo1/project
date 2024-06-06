<template>
  <div>
    <Header />

    <div class="main c1">
      <div class="container">
        <h1>Товары</h1>

        <div class="workout" v-for="product in products" :key="product.id">
          <h2>{{ product.name }}</h2>
          <p>{{ product.description }}</p>
          <button type="button" class="btn" @click="addToBasket(product.id)">В корзину</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import Header from '@/components/Header.vue';
import api from '@/api.js';
</script>

<script>
export default {
  data() {
    return {
      products: []
    };
  },
  methods: {
    async fetchProductData() {
      try {
        const response = await api.get('/auth/get/products', {
          headers: {
            'Authorization': 'Bearer ' + this.$cookies.get('jwt')
          }
        });
        this.products = response.data;
      } catch (error) {
        console.error(error);
      }
    },
    async addToBasket(productId) {
      try {
        // Create a new basket
        const basketResponse = await api.post('/admin/create/basket', {}, {
          headers: {
            'Authorization': 'Bearer ' + this.$cookies.get('jwt')
          }
        });
        const basketId = basketResponse.data.id;

        // Add product to the created basket
        await api.post('/admin/add-to-basket', {
          basketId: basketId,
          productId: productId,
          quantity: 1
        }, {
          headers: {
            'Authorization': 'Bearer ' + this.$cookies.get('jwt')
          }
        });

        alert('Product added to basket successfully!');
      } catch (error) {
        console.error(error);
        alert('Failed to add product to basket.');
      }
    }
  },
  mounted() {
    this.fetchProductData();
  }
}
</script>

<style scoped>
.First {
  margin-top: 7%;
}
.c1 {
  animation: gradient 5s infinite linear;
  background-size: 400%;
  min-height: 100vh; /* Make sure it covers the full viewport height */
}
.btn {
  color: aliceblue;
  background-color: rgb(35, 25, 66);
}
@media (max-width: 768px) {
  .search-bar input {
    width: 150px;
  }
}
@media (max-width: 480px) {
  .First {
    flex-direction: column;
    padding: 10px;
  }
  .c1 {
    width: 100%;
    margin-bottom: 7%;
  }
  .buttons {
    margin-top: 10px;
  }
  .buttons button {
    margin-left: 0;
    display: block;
    width: 100%;
    margin-top: 7px;
  }
}
</style>
