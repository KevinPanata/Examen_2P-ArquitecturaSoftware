package ec.edu.banquito.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI branchOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("BanQuito Branch & Holidays API")
                        .version("v1")
                        .description("API to manage branches and their holidays using MongoDB."));
    }
}
