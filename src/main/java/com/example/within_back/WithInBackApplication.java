package com.example.within_back;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WithInBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(WithInBackApplication.class, args);
	}

}
