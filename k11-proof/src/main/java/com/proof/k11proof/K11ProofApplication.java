package com.proof.k11proof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class K11ProofApplication {

	public static void main(String[] args) {
		SpringApplication.run(K11ProofApplication.class, args);
	}

}
