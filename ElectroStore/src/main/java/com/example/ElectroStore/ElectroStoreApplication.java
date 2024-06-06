package com.example.ElectroStore;

import com.example.ElectroStore.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ElectroStoreApplication {
	public static ApplicationContext context;
	public static User currentUser = null;
	public static void main(String[] args) {
		SpringApplication.run(ElectroStoreApplication.class, args);
	}
}