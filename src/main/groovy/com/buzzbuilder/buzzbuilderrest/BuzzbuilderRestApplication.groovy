package com.buzzbuilder.buzzbuilderrest

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@MapperScan("com.buzzbuilder.buzzbuilderrest.mapper")

class BuzzbuilderRestApplication {

	static void main(String[] args) {
		SpringApplication.run(BuzzbuilderRestApplication, args)
	}

}

