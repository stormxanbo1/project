<template>
    <div>
      <Header />
      <div>
        <p><strong>Название:</strong> {{ product.name }}</p>
        <p><strong>Производитель:</strong> {{ product.company }}</p>
        <p><strong>Артикул:</strong> {{ product.articule }}</p>
        <p><strong>Стоимость:</strong> {{ product.price }}</p>
        <p><strong>Описание:</strong> {{ product.description }}</p>
      </div>
    </div>
  </template>
  
  <script>
  import Api from '@/api.js';
  import Header from './Header.vue';
  
  export default {
    components: {
      Header
    },
    data() {
      return {
        product: {
          name: "",
          company: "",
          articule: "",
          price: "",
          description: ""
        }
      };
    },
    created() {
      const productId = this.$route.params.id;
      this.getProductById(productId);
    },
    methods: {
      async getProductById(id) {
        try {
          const response = await Api.get('unauthorized/get/product/' + id, {
            headers: {
              'Authorization': 'Bearer ' + this.$cookies.get('jwt')
            }
          });
          this.product = response.data; // Предполагается, что API возвращает объект с полями name, company, articule, price и description
        } catch (error) {
          console.error("There was an error fetching the product:", error);
        }
      }
    }
  };
  </script>
  
  <style scoped>
  /* Стили для страницы товара */
  </style>
  