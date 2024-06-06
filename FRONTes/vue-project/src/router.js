import { createRouter, createWebHistory } from 'vue-router';
import Registration from './components/registration.vue';
import Login from './components/login.vue';
import Cart from'./components/cart.vue';
import Product from './components/product.vue';
import Home from './components/home.vue';
import Profile from './components/profile.vue';
import Order from './components/order.vue';


export default createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/registration', component: Registration, alias: '/' },
    { path: '/login', component: Login },
    { path: '/home', component: Home },
    { path: '/cart', component: Cart },
    { path: '/product/:id', component: Product },
    { path: '/profile', component: Profile },
    { path: '/order/:orderId', component: Order }
  ],
});
