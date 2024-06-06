<template>
  <div class="auth-container">
    <h2>Вход в аккаунт</h2>
    <form @submit.prevent="loginUser">
      <input type="text" v-model="userName" placeholder="Имя пользователя" required>
      <input type="password" v-model="password" placeholder="Пароль" required>
      <button type="submit" class="btn btn-primary">Войти</button>
      <router-link to="/registration" class="btn btn-link mt-3">Создать аккаунт</router-link>
    </form>
    <div v-if="error" class="alert alert-danger mt-3" role="alert">
      Ошибка: Неправильные данные.
    </div>
  </div>
</template>

<script>
import Api from '@/api.js';
export default {
  data() {
    return {
      userName: '',
      password: '',
      jwt: null,
      error: false
    };
  },
  methods: {
    loginUser() {
      const loginData = {
        userName: this.userName,
        password: this.password
      }
      Api.post('/auth/signin', loginData)
        .then(response => { 
          this.jwt = response.data; 
          this.$cookies.remove('jwt'); 
          this.$cookies.set('jwt', this.jwt, '1h');
          console.log(document.cookie);
          this.$router.push('/home');
        })
        .catch(error => {
          console.error('Login failed:', error.response.data);
          this.error = true;
        });
    }
  }
}
</script>

<style scoped>
html,
body,
.auth-container {
  height: 100%;
}

.auth-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #d6a100;
  border-radius: 4px;
  box-shadow: 0 0 10px rgba(255, 193, 7, 0.1);
  background: linear-gradient(135deg, #ff8c42, #ff3d00);
  color: #fff;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

h2 {
  margin-bottom: 20px;
  color: #fff;
}

input {
  display: block;
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #d6a100;
  border-radius: 4px;
  background-color: rgba(255, 255, 255, 0.1);
  color: #fff;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #d6a100;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #b47200;
}

a {
  display: block;
  margin-top: 10px;
  text-align: center;
  color: #fff;
}

.router-link-exact-active {
  text-decoration: none;
}
</style>
