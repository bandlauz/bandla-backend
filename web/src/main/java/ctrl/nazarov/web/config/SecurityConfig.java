package ctrl.nazarov.web.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final AuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) ->
                        request
                                .requestMatchers("/auth/success").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/sms/**").permitAll()
                                .anyRequest().authenticated());

        http.formLogin(form -> form.loginPage("/auth/login").defaultSuccessUrl("/auth/success", true));
        http.logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll());
        http.exceptionHandling().authenticationEntryPoint(authEntryPoint);
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
