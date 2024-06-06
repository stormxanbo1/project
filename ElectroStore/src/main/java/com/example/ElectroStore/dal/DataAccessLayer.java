package com.example.ElectroStore.dal;

import com.example.ElectroStore.models.*;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Getter
public class DataAccessLayer {
    private final SessionFactory sessionFactory;

    @Autowired
    public DataAccessLayer(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    Session session = null;
    public void createProduct(Product newProduct) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(newProduct);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
    }
    public void deleteProductById(Long id){
        session = sessionFactory.openSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.remove(product);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
    }
    public void updateProductById(Long id, Product updatedProduct){
        session = sessionFactory.openSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        product.setCompany(updatedProduct.getCompany());
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setDescription(updatedProduct.getDescription());
        session.merge(product);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
    }
    public Product getProductById(Long id){
        session = sessionFactory.openSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
        return product;
    }
    public List<Product> getProducts(){
        session = sessionFactory.openSession();
        session.getTransaction().begin();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root);
        List<Product> resultList = session.createQuery(query).getResultList();
        return resultList;
    }
    public void createUser(User user){
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
    }
    public void deleteUserById(Long id){
        session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.remove(id);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
    }
    public void updateUser(Long id, User updatedUser){
        session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        user.setUserName(updatedUser.getUserName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setRole(updatedUser.getRole());
        session.merge(user);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
    }
    public User getUserById(Long id){
        session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
        return user;
    }
    public List<User> getUsers(){
        session = sessionFactory.openSession();
        session.getTransaction().begin();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        List<User> resultList = session.createQuery(query).getResultList();
        return resultList;
    }
    public String newUserToDatabase(User user) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        String name = user.getUserName();

        Query query = session
                .createQuery("FROM User where userName = :username")
                .setParameter("username", name);
        User userFrom = (User) query.uniqueResult();

        if (userFrom != null) {
            return "Выберите другое имя";
        }
        session.persist(user);
        session.getTransaction().commit();
        session.close();
        return "Pabeda)";
    }
    public User getUserFromDatabaseByUsername(String name) {
        session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session
                .createQuery("FROM User where userName = :username")
                .setParameter("username", name);
        User userFrom = (User) query.uniqueResult();
        if (userFrom == null) {
            return null;
        }
        return userFrom;
    }
    public void createBasket(Basket basket){
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(basket);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
    }
    public void deleteBasketById(Long id){
        session = sessionFactory.openSession();
        session.beginTransaction();
        Basket basket = session.get(Basket.class, id);
        session.remove(id);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
    }
    public void updateBasket(Long id, Basket updatedBasket){
        session = sessionFactory.openSession();
        session.beginTransaction();
        Basket basket = session.get(Basket.class, id);
        basket.setUser(updatedBasket.getUser());
        basket.setProduct(updatedBasket.getProduct());
        session.merge(basket);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
    }
    public Basket getBasketById(Long id){
        session = sessionFactory.openSession();
        session.beginTransaction();
        Basket basket = session.get(Basket.class, id);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
        return basket;
    }
    public List<Basket> getBaskets(){
        session = sessionFactory.openSession();
        session.getTransaction().begin();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Basket> query = builder.createQuery(Basket.class);
        Root<Basket> root = query.from(Basket.class);
        query.select(root);
        List<Basket> resultList = session.createQuery(query).getResultList();
        return resultList;
    }

    public void createOrder(Order order){
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(order);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
    }
    public void deleteOrderById(Long id){
        session = sessionFactory.openSession();
        session.beginTransaction();
        Order order = session.get(Order.class, id);
        session.remove(id);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
    }
    public void updateOrder(Long id, Order updatedorder){
        session = sessionFactory.openSession();
        session.beginTransaction();
        Order order = session.get(Order.class, id);
        order.setBasket(updatedorder.getBasket());
        order.setStatus(updatedorder.isStatus());
        session.merge(order);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
    }
    public Order getOrderById(Long id){
        session = sessionFactory.openSession();
        session.beginTransaction();
        Order order = session.get(Order.class, id);
        session.getTransaction().commit();
        if (session != null) {
            session.close();
        }
        return order;
    }
    public List<Order> getOrders(){
        session = sessionFactory.openSession();
        session.getTransaction().begin();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        query.select(root);
        List<Order> resultList = session.createQuery(query).getResultList();
        return resultList;
    }
        public List<Basket> getBasketsByUserId(Long userId) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        return session.createQuery("SELECT b FROM Basket b WHERE b.user.id = :userId", Basket.class)
                .setParameter("userId", userId)
                .getResultList();
    }

}