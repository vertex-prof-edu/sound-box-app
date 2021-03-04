package vertex.pro.edu.soung_box_app.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import vertex.pro.edu.soung_box_app.entity.user.User;
import vertex.pro.edu.soung_box_app.service.user.crud.UserCrudService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserCrudService userCrudService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Override
//    public void configure(final WebSecurity web) {
//        web.ignoring().requestMatchers(PUBLIC_URLS);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user*").hasRole("USER")
                .antMatchers("/artist*").hasRole("ARTIST")
                .antMatchers(String.valueOf(PUBLIC_URLS))
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth){
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(bCryptPasswordEncoder);
//        provider.setUserDetailsService(userCrudService);
//        return provider;
//    }

    private static final RequestMatcher PUBLIC_URLS =
            new OrRequestMatcher(new AntPathRequestMatcher("/public/**"));
}
