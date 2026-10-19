package mptc.seangleng.ecommerce.order;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        /*
         * 1. Define endpoints. (Secure / Permit)
         * 2.
         */
        http.authorizeHttpRequests(endpoints -> endpoints
                .requestMatchers("/nacos-config").permitAll()
                .anyRequest().authenticated());

        // 2. Convert to stateless.
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Disable CSRF (Cross-site Request Forgery)
        http.csrf(AbstractHttpConfigurer::disable);

        // Disable form Login
        http.formLogin(AbstractHttpConfigurer::disable);

        // Security mechanism
        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(Customizer.withDefaults()));

        return http.build();
    }
}
