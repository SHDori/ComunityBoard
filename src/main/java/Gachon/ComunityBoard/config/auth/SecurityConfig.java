package Gachon.ComunityBoard.config.auth;


import Gachon.ComunityBoard.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/api/board","/","/css/**","/js/**","/images/**","/api/board/search/**","/api/board/all","/api/loginedUser").permitAll()// 모두에게 허용
//                    .antMatchers("/api/**").hasRole(Role.USER.name()) // User권한가진사람한테만 허용
//                    .anyRequest().authenticated() //나머지페이지는 인증된 사람에게만 허용
                    //.antMatchers("/api/board/posts/**","/api/user/userInfo/**").authenticated()
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("http://together2021.dothome.co.kr/")
                .and()
                    .oauth2Login()
                    .defaultSuccessUrl("http://together2021.dothome.co.kr/")
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);

    }

}
