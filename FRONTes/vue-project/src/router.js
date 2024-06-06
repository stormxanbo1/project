import { createRouter, createWebHistory } from 'vue-router';
import Registration from './components/registration.vue';
import Login from './components/login.vue';
import Home from './components/home.vue';
import Cart from './components/cart.vue';
import Cards from '@/components/cards.vue';
import Discounts from '@/components/discounts.vue';

export default createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/registration', component: Registration, alias: '/' },
    { path: '/login', component: Login },
    { path: '/home', component: Home },
    { path: '/cart', component: Cart },
    { path: '/discounts', component: Discounts },
    { path: '/cards', component: Cards }
  ],
});
