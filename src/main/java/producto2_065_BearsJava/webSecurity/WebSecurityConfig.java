package producto2_065_BearsJava.webSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //Necesario para evitar que la seguridad se aplique a los resources
    //Como los css, imagenes y javascripts
    String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // http configuracion requiest (quien esta autorizado y de que manera)
        http
            .authorizeRequests()
            .antMatchers(resources).permitAll()
            .antMatchers("/","/index").permitAll()
            .antMatchers("/marca/**").hasRole("ADMIN")
//            .antMatchers("/admin*").access("hasRole('ADMIN')")
//            .antMatchers("/user*").access("hasRole('USER')")
            .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/userForm")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .csrf().disable()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout"); // con spring security no necesitamos controlador de logout
    }
    // Creamos el encriptador de contraseñas
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // Utilizamos la configuración predeterminada de BCrypt
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserDetailsService userDetailsService;

    // Registra el service para usuarios y el encriptador de contrasena
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Setting Service to find User in the database.
        // And Setting PasswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
