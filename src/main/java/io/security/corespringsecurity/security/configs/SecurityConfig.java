package io.security.corespringsecurity.security.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final UserDetailsService userDetailsService;
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  // 메모리 방식
/*  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    String password = passwordEncoder().encode("1111");

    auth.inMemoryAuthentication().withUser("user").password(password).roles("USER");
    auth.inMemoryAuthentication().withUser("manager").password(password).roles("MANAGER");
    auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN","USER", "MANAGER");
  }*/

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/", "/users").permitAll()
        .antMatchers("/mypage").hasRole("USER")
        .antMatchers("/messages").hasRole("MANAGER")
        .antMatchers("/config").hasRole("ADMIN")
        .anyRequest()
        .authenticated()

        .and()
        .formLogin();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());

  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
