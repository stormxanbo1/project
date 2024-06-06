<template>
    <div>
        <Header />
        <h2>Order Details</h2>
        <div v-if="!order">
            <p>Loading...</p>
        </div>
        <div v-else>
            <div>
                <h3>Order ID: {{ order.id }}</h3>
                <p>Status: {{ order.status ? 'Completed' : 'Pending' }}</p>
                <h4>Items:</h4>
                <ul>
                    <li v-for="(item, index) in order.orderItems" :key="index">
                        <template v-if="item.product">
                            {{ item.product.name }} - {{ item.product.company }} - ${{ item.product.price }}
                        </template>
                        <template v-else>
                            Product information not available
                        </template>
                    </li>
                </ul>
            </div>
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
            order: null
        };
    },
    created() {
        this.loadOrder();
    },
    methods: {
        async loadOrder() {
            const orderId = this.$route.params.orderId;
            if (orderId) {
                try {
                    const response = await Api.get(`secured/get/order/${orderId}`);
                    this.order = response.data;
                } catch (error) {
                    console.error('Failed to load order:', error);
                }
            } else {
                console.error('Order ID is undefined');
            }
        }
    }
};
</script>
<style scoped>
nav {
    display: flex;
    justify-content: center;
    margin-top: 10px;
  }
</style>