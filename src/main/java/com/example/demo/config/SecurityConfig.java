package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class SecurityConfig {

  @Autowired
  private DataSource securityDataSource;


  @Bean
  public UserDetailsService userDetailsService() {
    return new JdbcUserDetailsManager(securityDataSource);
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    CharacterEncodingFilter filter = new CharacterEncodingFilter();
    filter.setEncoding("UTF-8");
    filter.setForceEncoding(true);
    http.addFilterBefore(filter, CsrfFilter.class);

    http.csrf(Customizer.withDefaults());

    http.authorizeHttpRequests(auth -> {
      auth.requestMatchers("/").permitAll();
      auth.requestMatchers("/user/**").hasRole("USER");
      auth.requestMatchers("/nhanvien/**").hasRole("EMPLOYEE");
      auth.requestMatchers("/veXe/**").hasAnyRole("USER", "EMPLOYEE");
      auth.requestMatchers("/quanly/**").hasRole("MANAGER");
      auth.anyRequest().permitAll();
    });

    http.formLogin(form -> {
      form.loginPage("/showLoginPage");
      form.loginProcessingUrl("/authenticateTheUser");
      form.defaultSuccessUrl("/loginSuccessful", true)
          .permitAll();
    });

    http.logout(logout -> {
      logout.logoutUrl("/logout").permitAll();
      logout.logoutSuccessUrl("/");
    });

    return http.build();
  }

  @Bean
  public HttpFirewall allowUrlEncodeSlashHttpFirewall() {
    StrictHttpFirewall firewall = new StrictHttpFirewall();
    firewall.setAllowUrlEncodedDoubleSlash(true);
    return firewall;
  }


//  protected void configure(HttpSecurity http) throws Exception {
//    CharacterEncodingFilter filter = new CharacterEncodingFilter();
//    filter.setEncoding("UTF-8");
//    filter.setForceEncoding(true);
//    http.addFilterBefore(filter, CsrfFilter.class);
//
//    http.authorizeRequests().requestMatchers("/").permitAll().requestMatchers("/user/**").hasRole("USER")
//        .requestMatchers("/nhanvien/**").hasRole("EMPLOYEE").requestMatchers("/veXe/**").hasAnyRole("USER,EMPLOYEE")
//        .requestMatchers("/quanly/**").hasAnyRole("MANAGER").and().formLogin().loginPage("/showLoginPage")
//        .loginProcessingUrl("/authenticateTheUser").defaultSuccessUrl("/loginSuccessful", true).permitAll()
//        .and().logout().permitAll();
//			.and()
//			.exceptionHandling()
  // .accessDeniedPage("/access-denied");
//  }

}
