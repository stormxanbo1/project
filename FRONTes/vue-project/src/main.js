import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import VueCookies from 'vue-cookies';
import 'bootstrap/dist/css/bootstrap.min.css';

const app = createApp(App);
app.use(VueCookies);
app.use(router);
app.mount('#app');
