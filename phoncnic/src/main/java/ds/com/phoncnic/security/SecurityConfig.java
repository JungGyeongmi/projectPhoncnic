package ds.com.phoncnic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ds.com.phoncnic.security.filter.ApiCheckFilter;
import ds.com.phoncnic.security.filter.ApiLoginFilter;
import ds.com.phoncnic.security.handler.ApiLoginFailHandler;
import ds.com.phoncnic.security.handler.CustomAccessDeniedHandler;
import ds.com.phoncnic.security.handler.CustomLoginSuccessHandler;
import ds.com.phoncnic.security.handler.CustomLogoutSuccessHandler;
import ds.com.phoncnic.security.service.MemberDetailsService;
import ds.com.phoncnic.security.util.JWTUtil;
import lombok.extern.log4j.Log4j2;


@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
  
  @Autowired
  private MemberDetailsService memberDetailsService;
  
  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  CustomAccessDeniedHandler accessDeniedHandler(){
    return new CustomAccessDeniedHandler();
  }

  @Bean
  CustomLoginSuccessHandler loginSuccessHandler(){
    return new CustomLoginSuccessHandler(passwordEncoder());
  }

  @Bean
  CustomLogoutSuccessHandler logoutSuccessHandler(){
    return new CustomLogoutSuccessHandler();
  }

  @Bean
  public ApiCheckFilter apiCheckFilter() {
    return new ApiCheckFilter("/phoncnic/manage/**/*", jwtUtil());
  }

  @Bean
  public ApiLoginFilter apiLoginFilter() throws Exception {
    ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/api/login", jwtUtil());
    apiLoginFilter.setAuthenticationManager(authenticationManager());
    apiLoginFilter.setAuthenticationFailureHandler(
      new ApiLoginFailHandler());
    return apiLoginFilter;
  }

  @Bean
  public JWTUtil jwtUtil() {
    return new JWTUtil();
  }
  
  //시큐리티를 적용하기 위한 url에 대한 설정과 로그인과 access 거부일때
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    log.info(">>>"+http.headers().getClass().getName());
    // http.authorizeRequests()
    // .antMatchers("/notes").permitAll()
    // .antMatchers("/sample/all").permitAll()
    // .antMatchers("/sample/member").hasRole("MEMBER")
    // .antMatchers("/sample/admin").hasRole("ADMIN");
    http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    
    //1. Security login form 사용
    // http.formLogin();
    
    //2. 사용자 정의에 의한 loginProcessingUrl 사용
    // http.formLogin().loginPage("/member/login")
    // .loginProcessingUrl("/member/login");
    
    //3.UserDetailsService 로그인 handler :: security의 '/login'
    http.formLogin().loginPage("/member/login")
    .loginProcessingUrl("/login")
    .failureUrl("/member/login?error")
    .successHandler(loginSuccessHandler());
    
    //4.OAuth2UserDetailsService 로그인 handler :: social의 login
    http.oauth2Login().successHandler(loginSuccessHandler());
    
    http.csrf().disable();
    http.logout().logoutSuccessHandler(logoutSuccessHandler());
    //.logoutUrl("/member/logout").logoutSuccessUrl("/member/login")
    http.rememberMe().tokenValiditySeconds(60*60*24*7).userDetailsService((UserDetailsService) memberDetailsService);
    http.addFilterBefore(apiCheckFilter(), 
              UsernamePasswordAuthenticationFilter.class);
    http.addFilterBefore(apiLoginFilter(), 
              UsernamePasswordAuthenticationFilter.class);

  }

}
