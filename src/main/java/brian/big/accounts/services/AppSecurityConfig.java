package brian.big.accounts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static brian.big.accounts.services.AppUserRoles.*;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder encoder;

    @Autowired
    public AppSecurityConfig(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //TODO: fix this
                .authorizeRequests()
                .antMatchers("/api/**").hasRole(DEVELOPER.name())
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails developerDetails = User.builder()
                .username("developer")
                .password(encoder.encode("developer"))
                .roles(DEVELOPER.name())
                .build();

        UserDetails userBrian = User.builder()
                .username("brian")
                .password(encoder.encode("passwordAdmin"))
                .roles(ADMIN.name())
                .build();

        UserDetails userMerlin = User.builder()
                .username("merlin")
                .password(encoder.encode("merlin"))
                .roles(STUDENT.name())
                .build();


        return new InMemoryUserDetailsManager(
                developerDetails,
                userBrian,
                userMerlin
        );
    }
}
