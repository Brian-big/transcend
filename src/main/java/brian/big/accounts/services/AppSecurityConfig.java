package brian.big.accounts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    private final PasswordEncoder encoder;

    @Autowired
    public AppSecurityConfig(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        (auth) -> auth
                                .anyRequest().authenticated()
                )
                .build();
    }


    //
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable() //TODO: fix this
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/api/**").hasAuthority(STUDENT_WRITE.name())
//                .antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(STUDENT_WRITE.name())
//                .antMatchers(HttpMethod.PUT, "/api/**").hasAuthority(STUDENT_WRITE.name())
//                .antMatchers("/api/**").hasAnyRole(ADMIN.name(), DEVELOPER.name())
//                .and()
//                .httpBasic();
//    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails developerDetails = User.builder()
//                .username("developer")
//                .password(encoder.encode("developer"))
//                .roles(DEVELOPER.name())
//                .build();
//
//        UserDetails userBrian = User.builder()
//                .username("brian")
//                .password(encoder.encode("passwordAdmin"))
//                .roles(ADMIN.name())
//                .build();
//
//        UserDetails userMerlin = User.builder()
//                .username("merlin")
//                .password(encoder.encode("merlin"))
//                .roles(STUDENT.name())
//                .build();
//
//
//        return new InMemoryUserDetailsManager(
//                developerDetails,
//                userBrian,
//                userMerlin
//        );
//    }
}
