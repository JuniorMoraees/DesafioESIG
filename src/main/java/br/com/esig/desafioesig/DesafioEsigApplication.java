package br.com.esig.desafioesig;

import br.com.esig.desafioesig.repository.CargoRepository;
import br.com.esig.desafioesig.repository.PessoaRepository;
import com.sun.faces.config.ConfigureListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import javax.faces.webapp.FacesServlet;

@SpringBootApplication
@EntityScan(basePackages = "br.com.esig.desafioesig.domain")
@EnableJpaRepositories(basePackages = "br.com.esig.desafioesig.repository")
@ComponentScan(basePackages = "br.com.esig.desafioesig.controller")
public class DesafioEsigApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DesafioEsigApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DesafioEsigApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean facesServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
        registration.setLoadOnStartup(1);
        registration.addUrlMappings("*.com");
        return registration;
    }

    @Bean
    public ServletContextInitializer servletContextInitializer(){
        return servletContext -> {
            servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
        };
    }

    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<>(new ConfigureListener());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
