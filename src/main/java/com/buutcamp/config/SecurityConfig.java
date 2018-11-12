package com.buutcamp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource secDataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.jdbcAuthentication().dataSource(secDataSource);

        /*
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("Dan").password("test123").roles("visitor"))
                .withUser(users.username("Miro").password("test123").roles("visitor","manager"));*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/library/").hasRole("visitor")
                .antMatchers("/manager/").hasRole("manager")
                .and()
                .formLogin()
                    .loginPage("/showLoginPage")
                    .loginProcessingUrl("/authenticateUser")
                    .successForwardUrl("/checkuser/")
                .permitAll()
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/logout.done").deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .permitAll();


    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(secDataSource);

        return jdbcUserDetailsManager;
    }
}








