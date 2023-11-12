package com.yorku.BidSphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"com.yorku.BidSphere.Payment", "com.yorku.BidSphere.Bid", "com.yorku.BidSphere.DutchBid",
	"com.yorku.BidSphere.Catalog", "com.yorku.BidSphere.User", "com.yorku.BidSphere.DutchCatalog"})
public class BidSphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(BidSphereApplication.class, args);
	}

}
