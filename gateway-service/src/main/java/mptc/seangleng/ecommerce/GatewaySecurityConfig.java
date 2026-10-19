package mptc.seangleng.ecommerce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class GatewaySecurityConfig {
    @Bean
    public SecurityWebFilterChain gatewayFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(oauth -> oauth
                .pathMatchers("/auth/**").authenticated()
                .anyExchange().permitAll());

        http.oauth2Login(Customizer.withDefaults());
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        http.cors(ServerHttpSecurity.CorsSpec::disable);
        return http.build();
    }
}
