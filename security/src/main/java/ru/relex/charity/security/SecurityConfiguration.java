package ru.relex.charity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.relex.charity.security.handler.CharityLogoutSuccessHandler;
import ru.relex.charity.security.handler.CharityLoginSuccessHandler;

@Configuration
@Import(CurrentUserConfiguration.class)
@ComponentScan({
        "ru.relex.charity.security.handler",
        "ru.relex.charity.security.service"
})
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final CharityLoginSuccessHandler loginSuccessHandler;

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService, CharityLoginSuccessHandler loginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) {
        final var daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        builder.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/ads").permitAll()
                .antMatchers("/api/categories").permitAll()
                .antMatchers("/api/users/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/auth/login")
                .successHandler(loginSuccessHandler)
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessHandler(new CharityLogoutSuccessHandler())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CharityEntryPoint())
                .and()
                .sessionManagement()
                .sessionFixation()
                .changeSessionId()
        ;

    }
}
