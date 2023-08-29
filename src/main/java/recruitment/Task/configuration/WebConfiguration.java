package recruitment.Task.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfiguration {

    @Bean
    public WebClient localApiClient() {
        return WebClient.create("https://api.github.com/");
    }
}
