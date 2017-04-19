package com.GestionBanque.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
  private DataSource dataSource  ;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		/*auth.inMemoryAuthentication().withUser("medhedhili6@gmail.com").password("789456").roles("ADMIN","USER");
		auth.inMemoryAuthentication().withUser("mohamed").password("45678").roles("USER"); */
		// utiliser jdbcAuthentification 
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username as principal , password as credentials  , active  from  users where  username =? ")
		.authoritiesByUsernameQuery("select  username as  principal  ,  roles  as  role from  users_role where username = ?")
		.rolePrefix("ROLE_")  //  indique au  spring  security  un prefix  q il  faut  ajouté  au  role  
		.passwordEncoder(new Md5PasswordEncoder() ) ; //  indique  au  spring  security  qu il faut  utilisé  l algorithme  MD5  pour encoder le password 
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
// http.formLogin(); // page par defaut d authentification  oferte  par  Spring
		http.formLogin().loginPage("/login"); // personnaliser  la page d'authentification
http.authorizeRequests().antMatchers("/operations","/consulterCompte").hasRole("USER");
http.authorizeRequests().antMatchers("/saveOperation").hasRole("ADMIN");
http.exceptionHandling().accessDeniedPage("/accessDeniedPage"); // personnaliser  la page Access denied page  

	}
	

}
