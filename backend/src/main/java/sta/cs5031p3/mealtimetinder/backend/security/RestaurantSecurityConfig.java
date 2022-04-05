package sta.cs5031p3.mealtimetinder.backend.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sta.cs5031p3.mealtimetinder.backend.filter.CustomAuthenticationFilter;
import sta.cs5031p3.mealtimetinder.backend.filter.CustomAuthorisationFilter;
import sta.cs5031p3.mealtimetinder.backend.service.impl.RestaurantDetailServiceImpl;

/**
 * Config Spring security for authentication and authorisation of restaurant user.
 * @author 200011181
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Order(2)
@Slf4j
public class RestaurantSecurityConfig extends WebSecurityConfigurerAdapter {
    private final RestaurantDetailServiceImpl userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/restaurant/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //http.authorizeRequests().antMatchers("/restaurant/login/**").permitAll();
        http.requestMatchers().antMatchers("/restaurant/**")
                .and().authorizeRequests().anyRequest().hasAuthority("RESTAURANT");
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorisationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
