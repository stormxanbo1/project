<template>
  <div>
    <Header />
    <main>
      <div class="product-card" v-for="product in products" :key="product.id" @click="goToProduct(product.id)">
        <h3>{{ product.name }}</h3>
        <p>{{ product.price }}</p>
        <button @click.stop="addToBasket(product)">Добавить в корзину</button>
      </div>
    </main>
  </div>
</template>

<script>
import Header from './Header.vue';
import Api from '@/api.js';
import jwt_decode from 'vue-jwt-decode';

export default {
  components: {
    Header
  },
  data() {
    return {
      products: [],
    };
  },
  mounted() {
    this.fetchProducts();
  },
  methods: {
    goToProduct(productId) {
      this.$router.push(`/product/${productId}`);
    },
    addToBasket(product) {
      const token = this.$cookies.get('jwt'); 
      const decodedToken = jwt_decode.decode(token);

      if (decodedToken) {
        this.userId = decodedToken.id;
        Api.post('auth/create/basket', {
          user: {id: this.userId},
          product:{id: product.id}
        })
        .then(response => {
          console.log('Product added to basket:', product.id, this.userId);
        })
        .catch(error => {
          console.error('Error adding product to basket:', error);
        });
      } else {
        console.log('Invalid token');
      }
    },
    fetchProducts() {
      Api.get('unauthorized/get/products', {
        headers: {
          'Authorization': 'Bearer ' + this.$cookies.get('jwt')
        }
      })
      .then(response => {
        this.products = response.data;
      })
      .catch(error => {
        console.error("There was an error fetching the products:", error);
      });
    }
  }
};
</script>
