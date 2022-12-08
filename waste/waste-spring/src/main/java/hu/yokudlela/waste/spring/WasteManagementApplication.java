/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.yokudlela.waste.spring;

import hu.yokudlela.waste.Waste;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author jakab
 */
@SecurityScheme(
type = SecuritySchemeType.OAUTH2,name="oauth2",description = "Keycloak Yokudlela",flows = 
        @OAuthFlows(implicit = @OAuthFlow(authorizationUrl = "http://yokudlela:6080/auth/realms/yokudlela/protocl/openid-connect/auth?client_id=account&redirect_uri=http://localhost:8080/storage/swagger-ui/oauth2-redirect.html"
                + "&response_type=code&scope=openid")))
@SecurityScheme(
        type = SecuritySchemeType.APIKEY,name="apikey",description = "Keycloak Yokudlela",
        paramName = "Authorization",
        in = SecuritySchemeIn.HEADER
)
@SecurityScheme(
type = SecuritySchemeType.OPENIDCONNECT,name = "openid",description = "Keycloak yokudlela",openIdConnectUrl = "http://yokudlela:6080/auth/realms/yokudlela/.well-known/openid-configuration")
@OpenAPIDefinition(
        servers = {
            @Server(url = "http://yokudlela:8081/waste", description = "local dev")},
        info = @Info(
                title = "Yokudlela Storage API",
                version = "v1",
                description = "description =Yokudlela Waste Management API for Graphical User Interface ."))
@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@ComponentScan(basePackages = {"hu.yokudlela.waste.rest", "hu.yokudlela.waste"})
@EnableJpaRepositories(basePackages = {"hu.yokudlela.waste.store"})
@EntityScan(basePackageClasses = {Waste.class})
@EnableTransactionManagement
//@SpringBootApplication
public class WasteManagementApplication {
  public static void main(String[] args)
  {
      SpringApplication.run(WasteManagementApplication.class, args);
  }
  
  @Bean
    	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}
          
}
