package eu.accesa.training.config;

import eu.accesa.training.model.TestUser;
import eu.accesa.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests()
                .antMatchers("/price/all").access("hasRole('ADMIN')")
                .antMatchers("/**").permitAll()
//                .antMatchers("/public/*").permitAll()
//                .antMatchers("/actuator/*").permitAll()
//                .anyRequest().authenticated()

                .and()

                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true")

                .and()

                .logout()
                .permitAll();
    }

    @Override
    protected void configure(
            AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(new UserDetailsService() {
                    @Override
                    public UserDetails loadUserByUsername(String username)
                            throws UsernameNotFoundException {
                        PasswordEncoder encoder =
                                PasswordEncoderFactories.createDelegatingPasswordEncoder();
                        TestUser user  = userService.findByUsername(username);
                        if (user != null) {
                            return User.withUsername(user.getUsername())
                                    .password(encoder.encode(user.getPassword()))
                                    .roles("ADMIN").build();
                        }
                        throw new UsernameNotFoundException("User '" + username + "' not found.");
                    }
                });
    }

}
