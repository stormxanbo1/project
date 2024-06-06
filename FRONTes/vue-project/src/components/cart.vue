<template>
  <div>
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
    <div class="container">
      <h2>Ваша корзина</h2>
      <div v-if="cartItems.length">
        <div v-for="(item, index) in cartItems" :key="item.id" class="card mb-3">
          <div class="card-body d-flex justify-content-between align-items-center">
            <div>
              <h5 class="card-title">{{ item.name }}</h5>
              <p class="card-text">{{ item.price }} ₽</p>
              <div class="d-flex align-items-center">
                <label for="quantity" class="mr-2">Количество:</label>
                <input
                  type="number"
                  v-model="item.quantity"
                  min="1"
                  class="form-control"
                  style="width: 60px;"
                  @change="updateQuantity(index, item.quantity)"
                />
              </div>
            </div>
            <div>
              <p class="card-text">Итого: {{ item.price * item.quantity }} ₽</p>
              <button class="btn btn-danger" @click="removeItem(index)">Удалить</button>
            </div>
          </div>
        </div>
        <div class="total mt-4">
          <h4>Общая сумма: {{ totalPrice }} ₽</h4>
          <button class="btn btn-success" @click="checkout">Оформить заказ</button>
        </div>
      </div>
      <div v-else>
        <p>Ваша корзина пуста.</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      cartItems: [] // Массив для хранения товаров в корзине
    };
  },
  computed: {
    totalPrice() {
      return this.cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
    }
  },
  methods: {
    async addToBasket(userId, productId) {
      try {
        // Отправка запроса на сервер для добавления товара в корзину
        const response = await axios.post('http://localhost:8080/admin/create/basket', {
          user: { id: userId },
          product: { id: productId }
        }, {
          headers: {
            'Authorization': 'Bearer ' + this.$cookies.get('jwt')
          }
        });
        
        alert('Product added to basket successfully!');
      } catch (error) {
        console.error('Failed to add product to basket:', error);
        alert('Failed to add product to basket.');
      }
    },
    updateQuantity(index, quantity) {
      this.cartItems[index].quantity = quantity;
      this.saveCart();
    },
    removeItem(index) {
      this.cartItems.splice(index, 1);
      this.saveCart();
    },
    checkout() {
      // Логика для оформления заказа
      alert('Оформление заказа!');
    },
    saveCart() {
      localStorage.setItem('cart', JSON.stringify(this.cartItems));
    },
    loadCart() {
      const cart = localStorage.getItem('cart');
      if (cart) {
        this.cartItems = JSON.parse(cart);
      }
    },
    logOut() {
      this.$cookies.remove('jwt');
      window.location.href = "/login";
    }
  },
  mounted() {
    this.loadCart();
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
  margin-bottom: 10px;
}

.total {
  margin-top: 20px;
  text-align: right;
}
</style>
