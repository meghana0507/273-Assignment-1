package A1;

/**
 * Created by Meghana on 3/3/2015.
 */

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.context.annotation.ComponentScan;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
    import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
    import org.springframework.web.servlet.config.annotation.EnableWebMvc;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
    import javax.management.relation.Role;
	
@Configuration
@ComponentScan
    @EnableWebMvcSecurity
    public class BasicAuth extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/api/v1/moderators/*","/api/v1/moderators/{moderator_id}/*", "/api/v1/moderators/{moderator_id}/polls/*").hasRole("USER").and()
                    .httpBasic();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .inMemoryAuthentication()
                    .withUser("foo").password("bar").roles("USER");
        }
    }
