package landry.paysted.configuration;

import java.net.http.HttpClient;
import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class HttpConfiguration {

    @Bean
    public HttpClient httpClient(){
        return HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(20, 0))
            .build();
    }
}
