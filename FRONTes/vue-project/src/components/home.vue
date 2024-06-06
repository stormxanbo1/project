<template>
  <div>
    <Header />
    <h2>Ваша корзина.</h2>
    <div v-if="basketItems.length === 0">
      <p>Ваша корзина пуста.</p>
    </div>
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
import { ref, onMounted } from 'vue';
import Header from '@/components/Header.vue';
import Api from '@/api.js';
import jwt_decode from 'vue-jwt-decode';

const basketItems = ref([]);
const userId = ref(null);

const initializeUserId = () => {
  const token = $cookies.get('jwt');
  if (token) {
    const decodedToken = jwt_decode.decode(token);
    if (decodedToken && decodedToken.id) {
      userId.value = decodedToken.id;
      console.log('User ID:', userId.value);
      loadBasketItems();
    } else {
      console.log('Invalid token');
    }
  } else {
    console.log('Token not found');
  }
};

const loadBasketItems = async () => {
  try {
    const response = await Api.get(`secured/get/baskets/${userId.value}`);
    basketItems.value = response.data;
  } catch (error) {
    console.error('Failed to load basket items:', error);
  }
};

const addToBasket = async (productId) => {
  try {
    // Проверяем наличие существующей корзины
    const basketResponse = await Api.get(`secured/get/basket/${userId.value}`);
    let basketId;

    if (basketResponse.data) {
      // Корзина существует
      basketId = basketResponse.data.id;
    } else {
      // Корзина не существует, создаем новую
      const createBasketResponse = await Api.post('/admin/create/basket', {
        user: {
          id: userId.value
        },
        product: {
          id: productId
        }
      }, {
        headers: {
          'Authorization': 'Bearer ' + $cookies.get('jwt')
        }
      });
      basketId = createBasketResponse.data.id;
    }

    // Добавляем продукт в корзину
    await Api.post('/admin/add-to-basket', {
      basketId: basketId,
      productId: productId,
      quantity: 1
    }, {
      headers: {
        'Authorization': 'Bearer ' + $cookies.get('jwt')
      }
    });

    alert('Товар успешно добавлен в корзину!');
  } catch (error) {
    console.error(error);
    alert('Не удалось добавить товар в корзину.');
  }
};

onMounted(() => {
  initializeUserId();
});
</script>

<style scoped>
.basket-item {
  margin-bottom: 15px;
}
</style>
