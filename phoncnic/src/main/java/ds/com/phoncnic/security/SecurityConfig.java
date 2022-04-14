package ds.com.phoncnic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ds.com.phoncnic.security.filter.ApiLoginFilter;
import ds.com.phoncnic.security.handler.ApiLoginFailHandler;
import ds.com.phoncnic.security.handler.CustomAccessDeniedHandler;
import ds.com.phoncnic.security.handler.CustomLoginSuccessHandler;
import ds.com.phoncnic.security.handler.CustomLogoutSuccessHandler;
import ds.com.phoncnic.security.util.JWTUtil;
import lombok.extern.log4j.Log4j2;


@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Bean
  CustomAccessDeniedHandler accessDeniedHandler(){
    return new CustomAccessDeniedHandler();
  }

  @Bean
  CustomLoginSuccessHandler loginSuccessHandler(){
    return new CustomLoginSuccessHandler();
  }

  @Bean
  CustomLogoutSuccessHandler logoutSuccessHandler(){
    return new CustomLogoutSuccessHandler();
  }

  @Bean
  public ApiLoginFilter apiLoginFilter() throws Exception {
    ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/api/login", jwtUtil());
    apiLoginFilter.setAuthenticationManager(authenticationManager());
    apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());
    return apiLoginFilter;
  }

  @Bean
  public JWTUtil jwtUtil() {
    return new JWTUtil();
  }

  @Bean
  public SessionRegistry sessionResistry() {
    return new SessionRegistryImpl();
  }
  
  //시큐리티를 적용하기 위한 url에 대한 설정과 로그인과 access 거부일때
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    log.info(">>>"+http.headers().getClass().getName());
   
    // session 당 로그인 인원 제한
    http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false)
    .sessionRegistry(sessionResistry())
    .expiredUrl("/member/login");;
   
    // access 거부 handler
    http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    
    http.authorizeRequests()
    .antMatchers("/admin/**/**").hasRole("ADMIN")
    .antMatchers("/manage/rolechoice", "/main/mypage", "/lookmodal/lookmodify").authenticated()
    .antMatchers("/manage/dyning/**/**").hasAnyRole("CEO", "ADMIN")
    .antMatchers("/manage/gallery/**").hasAnyRole("ARTIST", "ADMIN")
    .antMatchers("/dyning/**", "/gallery/**").permitAll();

    // OAuth2UserDetailsService 로그인 handler :: social의 login
    http.oauth2Login().loginPage("/member/login").successHandler(loginSuccessHandler());
    // logout
    http.logout().logoutUrl("/member/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID")
    // .logoutSuccessHandler(logoutSuccessHandler())
    .logoutSuccessUrl("/");
    
    // remember 
    // http.rememberMe().tokenValiditySeconds(60*60*24*7).userDetailsService((UserDetailsService) memberDetailsService);
   
    // csrf 
    http.csrf().disable();
   
    // login filter
    http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
  }


}
