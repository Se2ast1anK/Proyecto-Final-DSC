package pe.isil.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pe.isil.service.RetiUserDetailsService;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final RetiUserDetailsService retiUserDetailsService;

    @Bean
      public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(retiUserDetailsService).passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/img/*","/styles/*","/js/*","/recipes","/tips", "/home", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home",true)
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/home")
                .and()
                .headers()
                .frameOptions().sameOrigin();
    }

    //  @Bean
    //  @Override
    //  public UserDetailsService userDetailsServiceBean() throws Exception {

    //  UserDetails jorgeUser = User.builder()
    //          .username("jorge")
    //          .password("{noop}j123456")
    //          .roles("Admin")
    //          .build();


    //  UserDetails fabianUser = User.builder()
    //          .username("fabian")
    //          .password("{noop}f123456")
    //          .roles("Admin")
    //          .build();

    //
    //  UserDetails sebastianUser = User.builder()
    //          .username("sebastian")
    //          .password("{noop}s123456")
    //          .roles("Admin")
    //          .build();


    //  return new InMemoryUserDetailsManager(jorgeUser,fabianUser,sebastianUser);
    //  }
}
