package ds.com.phoncnic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private MemberDetailsService memberDetailsService;
  
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
    apiLoginFilter.setAuthenticationFailureHandler(
      new ApiLoginFailHandler());
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

    http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false).expiredUrl("/member/login").sessionRegistry(sessionResistry());

    log.info(">>>"+http.headers().getClass().getName());

    http.authorizeRequests()
    .antMatchers("/manage/gallery/list").hasRole("USER");

    http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

    http.authorizeRequests()
    .antMatchers("/main/mypage").hasRole("USER");

    http.authorizeRequests()
    .antMatchers("/manage/**/**").hasRole("USER");
    http.authorizeRequests()
    .antMatchers("/dyning/**").permitAll();

    http.authorizeRequests()
    .antMatchers("/lookmodal/lookmodify").hasRole("USER");
    
    //3.UserDetailsService 로그인 handler :: security의 '/login'
    http.formLogin().loginPage("/member/login")
    // .loginProcessingUrl("https://accounts.google.com/o/oauth2/v2/auth/identifier?response_type=code&client_id=301468225398-heanhhm34h7tttd2vrd1002iku8jtnr7.apps.googleusercontent.com&scope=email&state=e_nayzmX5_Uu6KiIVYtv6ZlRX7UilF7svDh5OWGqs2c%3D&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fphoncnic%2Flogin%2Foauth2%2Fcode%2Fgoogle&flowName=GeneralOAuthFlow")
    .failureUrl("/member/login")
    .successHandler(loginSuccessHandler());
    
    //4.OAuth2UserDetailsService 로그인 handler :: social의 login
    http.oauth2Login().successHandler(loginSuccessHandler());
    
    http.csrf().disable();

    http.logout().logoutUrl("/member/logout").logoutSuccessHandler(logoutSuccessHandler()).logoutSuccessUrl("/");
    http.rememberMe().tokenValiditySeconds(60*60*24*7).userDetailsService((UserDetailsService) memberDetailsService);

    http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
  }


}
