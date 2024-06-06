<template>
  <div class="profile-container">
    <header class="d-flex justify-content-between align-items-center p-3 mb-4 bg-primary text-white">
      <div class="d-flex align-items-center">
        <h1 class="m-0">ElectroStore</h1>
      </div>
      <nav class="d-flex">
        <router-link to="/home" class="btn btn-outline-light mx-2">Главная</router-link>
        <router-link to="/cart" class="btn btn-outline-light mx-2">Корзина</router-link>
        <router-link to="/profile" class="btn btn-outline-light mx-2">Профиль</router-link>
        <button class="btn btn-outline-light mx-2" @click="logOut">Выйти из аккаунта</button>
      </nav>
    </header>
    <main class="content">
    </main>
    <h2>Профиль</h2>
    <div v-if="user" class="profile-details">
      <p><strong>Имя пользователя:</strong> {{ user }}</p>
      <p><strong>E-mail:</strong> {{ email }}</p>
    </div>
    <div v-else class="not-authorized">
      <p>Вы не авторизованы</p>
      <h5>Войдите в аккаунт, чтобы увидеть подробности</h5>
    </div>
  </div>
</template>

<script>
import Api from '@/api.js';
import jwt_decode from 'vue-jwt-decode';
import Header from './Header.vue';

export default {
  components: {
    Header
  },
  data() {
    return {
      user: null,
      email: null
    };
  },
  created() {
    const token = this.$cookies.get('jwt');
    if (token) {
      try {
        const decodedToken = jwt_decode.decode(token);
        if (decodedToken) {
          console.log('Decoded token:', decodedToken);
          const userId = decodedToken.id;
          Api.get('/auth/get/user/' + userId, {
            headers: {
              'Authorization': 'Bearer ' + token
            }
          })
          .then(response => {
            this.user = response.data.userName;
            this.email = response.data.email;
          })
          .catch(error => {
            console.error('Failed to fetch user profile:', error.response ? error.response.data : error.message);
          });
        } else {
          console.log('Invalid token');
        }
      } catch (error) {
        console.error('Failed to decode token:', error.message);
      }
    } else {
      console.log('Token not found');
    }
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


h2 {
  text-align: center;
  color: #333;
  margin-bottom: 20px;
}

.profile-details {
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.profile-details p {
  margin: 10px 0;
}

.not-authorized {
  text-align: center;
  color: #ff6b6b;
}

.not-authorized p {
  font-size: 18px;
  margin-bottom: 10px;
}

.not-authorized h5 {
  font-size: 16px;
  color: #999;
}
html, body, #app {
  height: 100%;
  margin: 0;
  padding: 0;
}

.page-container {
  padding: 0;
  display: flex;
  flex-direction: column;
  height: 100%;
}

header {
  width: 100%;
  background: linear-gradient(135deg, #ff8c42, #ff3d00);
  color: #fff;
  padding: 10px 20px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  margin: 0;
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
