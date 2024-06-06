<template>
  <div>
    <Header />
    <main class="cards-container">
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
<style>
/* Контейнер для карточек */
.cards-container {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
  justify-content: center; /* Центровка карточек по горизонтали */
  padding: 16px; /* Отступы внутри контейнера */
}

/* Основные стили для карточки товара */
.product-card {
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 16px;
  width: 250px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s, box-shadow 0.2s;
  display: flex;
  flex-direction: column;
  align-items: center; /* Центровка контента по горизонтали */
  text-align: center;
}

.product-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 12px rgba(0, 0, 0, 0.2);
}

/* Стили для названия товара */
.product-card h3 {
  font-size: 18px;
  margin: 0 0 8px 0;
  color: #333;
}

/* Стили для цены товара */
.product-card p {
  font-size: 16px;
  color: #777;
  margin: 0 0 16px 0;
}

/* Стили для кнопки */
.product-card button {
  background-color: #b39800;
  color: #fff;
  border: none;
  border-radius: 4px;
  padding: 10px 16px;
  cursor: pointer;
  font-size: 14px;
  transition: background-color 0.2s;
}

.product-card button:hover {
  background-color: #b39800;
}

</style>