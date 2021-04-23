package app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
@ComponentScan("app")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().antMatchers("/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js", "/**/*.map", "/**/*.ttf", "/**/*.woff", "/**/*.woff2", 
            "/images/**", "/fonts/**", "/swagger-ui.html/**", "/swagger**", "/webjars/**", "/api/**").permitAll() 
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/**").permitAll()
            .antMatchers(HttpMethod.PUT, "/api/**").permitAll()
            .antMatchers(HttpMethod.DELETE, "/api/**").permitAll()
            .and()
            .httpBasic()
            .and();
    }

}
