/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.yokudlela.storage.spring;

import hu.yokudlela.storage.Product;
import hu.yokudlela.storage.ProductCategory;
import hu.yokudlela.storage.utils.RequestBean;
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
import org.jboss.logging.MDC;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;
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
            @Server(url = "http://yokudlela:8080/storage", description = "local dev")},
        info = @Info(
                title = "Yokudlela Storage API",
                version = "v1",
                description = "description = \"Yokudlela Storage API for Graphical User Interface ."))
@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@ComponentScan(basePackages = {"hu.yokudlela.storage.rest", "hu.yokudlela.storage"})
@EnableJpaRepositories(basePackages = {"hu.yokudlela.storage.store"})
@EntityScan(basePackageClasses = {Product.class,ProductCategory.class})
@EnableTransactionManagement
//@SpringBootApplication
public class StorageApplication {
  public static void main(String[] args)
  {
      SpringApplication.run(StorageApplication.class, args);
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
    @Bean("requestScopedBean")
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RequestBean requestBean() {
        MDC.put("application", "2");
        MDC.put("host", "3");
        return new RequestBean();
    }   
}


