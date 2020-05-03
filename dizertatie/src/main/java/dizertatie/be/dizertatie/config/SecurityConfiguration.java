package dizertatie.be.dizertatie.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
        .antMatchers(HttpMethod.OPTIONS, "/**")
        .antMatchers("/h2console/**")
        .antMatchers("/v2/api-docs")
        .antMatchers("/configuration/ui")
        .antMatchers("/swagger-resources/**")
        .antMatchers("/configuration/security")
        .antMatchers("/swagger-ui.html")
        .antMatchers("/webjars/**")
        //.antMatchers("/app/**")
            ;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .exceptionHandling()
            .authenticationEntryPoint(null)
            .accessDeniedHandler(null)
            .authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT"))
        .and()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        	.authorizeRequests()
        	//web login
        	.antMatchers("/**/rest-api/v1/web/user/login",
			        	 "/**/rest-api/v1/web/user/logoff",
			        	 "/**/rest-api/v1/web/user/resetPasswordRequest",
			        	 "/**/rest-api/v1/web/user/checkResetToken/**",
			        	 "/**/rest-api/v1/web/user/resetPassword",
                    //cu sau fara v1?
                    "/**/rest-api/account/login",
                    "/**/rest-api/account/logoff",
                    "/**/rest-api/account/resetPasswordRequest",
                    "/**/rest-api/account/checkResetToken/**",
                    "/**/rest-api/account/resetPassword",
                    "/**/integration/anaf/mock/**"
        				).permitAll()

	        //actuator
	        .antMatchers("/**/health", "/**/info", "/**/prometheus").permitAll()
                //camunda
	        .antMatchers("/**/app/**").permitAll()

	        //all others
	        //.anyRequest().authenticated();
	        //TODO: fix this when endpoints are defined
	        .anyRequest().permitAll();

    }
}
