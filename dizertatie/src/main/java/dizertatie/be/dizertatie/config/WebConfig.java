package dizertatie.be.dizertatie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    //https://stackoverflow.com/a/32239263
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") //"http://localhost:4208", "http://35.157.203.74:7698")
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "PATCH", "DELETE")
                .allowedHeaders("Content-Type", "X-Email", "Accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers", "Authorization", "auth-type") //, "Access-Control-Allow-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true)
                .maxAge(3600);
    }
}