package app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("app")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().antMatchers("/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js", "/**/*.map", "/**/*.ttf",
            "/**/*.woff", "/**/*.woff2", "/images/**", "/fonts/**", "/swagger-ui.html/**", "/swagger**", "/produtos/**").permitAll()
            .and()
            .csrf().disable()
            .authorizeRequests()
            .and()
            .httpBasic()
            .and();
    }

}
