package com.drfirst.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api(){
		 return new Docket(DocumentationType.SWAGGER_2)  
		          .select()                                  
		          .apis(RequestHandlerSelectors.any())              
		          .paths(PathSelectors.ant("/log/*") )                         
		          .build(); 
		
	}
}
//@Configuration
//@EnableSwagger2
//@EnableAutoConfiguration
//public class SwaggerConfig {
//	@Bean
//	public Docket newsApi() {
//		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().build();
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().version("2.0").build();
//	}
//}
