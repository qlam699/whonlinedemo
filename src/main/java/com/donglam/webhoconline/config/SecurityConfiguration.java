package com.donglam.webhoconline.config;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CompositeFilter;

import com.donglam.webhoconline.model.NguoiDung;
import com.donglam.webhoconline.service.NguoiDungService;
import com.donglam.webhoconline.service.QuyenService;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
@EnableAuthorizationServer
@RestController
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	NguoiDungService nds;
	@Autowired
	QuyenService qs;

	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	// allow ckfinder
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/ckfinder/**").antMatchers(HttpMethod.OPTIONS, "/**");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/user")
	public Principal user(Principal principal, HttpServletRequest request) { // only -> sso
		System.out.println("Chay vao Principal");
		if (principal != null && (principal instanceof OAuth2Authentication)) {
			OAuth2Authentication oAuth2Authentication = null;
			try {
				oAuth2Authentication = (OAuth2Authentication) principal;
			} catch (ClassCastException ee) {
				System.out.println("Dang dang nhap voi user local cast " + principal.getName());
				return null;
			}
			//
			Authentication authentication = oAuth2Authentication.getUserAuthentication();
			Map<String, String> details = new LinkedHashMap<>();
			details = (Map<String, String>) authentication.getDetails();
			System.out.println("Login with social!");

			String id = details.get("id"); // id fb
			String sub = details.get("sub"); // id gg
			boolean isFB = details.get("id") != null ? true : false;
			String ids = details.get("id") == null ? details.get("sub") : details.get("id");
			// đã login
			if (details != null) {
				NguoiDung nd = new NguoiDung();
				if (nds.findByConfirmationToken(ids) == null) { // neu chua dang ki thi
					nd.setKichhoat(true);
					nd.setEmail(details.get("email"));
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					nd.setQuyen(qs.get(4));
					nd.setNgaycap(df.format(new Date()));
					if (isFB) { // la facebook va chua ton tai
						nd.setHovaten(details.get("name"));
						nd.setLoaitaikhoan("facebook");
						nd.setKhoabimat(id);
						String h = "https://graph.facebook.com/" + id + "/picture?type=normal";
						nd.setHinh(h);
					} else { // la google va chua ton tai
						nd.setHovaten(details.get("family_name") + " " + details.get("given_name"));
						nd.setKhoabimat(sub);
						nd.setLoaitaikhoan("google");
						nd.setHinh(details.get("picture"));
					}
					nds.saveOrUpdate(nd);
				} else {
					nd = nds.findByConfirmationToken(ids);
				}
				Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
				grantedAuthorities.add(new SimpleGrantedAuthority(nd.getQuyen().getTenquyen()));

				HttpSession session = request.getSession(true);
				session.setAttribute("CKFinder_UserRole", nd.getQuyen().getTenquyen());

				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(oAuth2Authentication,
						oAuth2Authentication.getCredentials(), grantedAuthorities);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}

		}
		return principal;
	}

	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/", "/login**", "logout", "/webjars/**", "/resources/**", "/detailcourse/**").permitAll()
				.antMatchers("/account").hasAnyRole("ADMIN")
				/* .antMatchers("/courses/**").hasAnyRole("TEACHER","STAFF","STUDENT") */
				.antMatchers("/courses/page/*").permitAll()
				/* .antMatchers("/teachers/**").hasAnyRole("TEACHER","STAFF","STUDENT") */
				.antMatchers("/myaccount").hasAnyRole("ADMIN", "TEACHER", "STAFF", "STUDENT")
				/* .antMatchers("/contact").hasAnyRole("ADMIN","TEACHER","STAFF","STUDENT") */
				.antMatchers("/forum/**").hasAnyRole("TEACHER", "STUDENT")
				/*
				 * .antMatchers("/detailteacher/**").hasAnyRole("ADMIN","TEACHER","STAFF",
				 * "STUDENT")
				 */
				.antMatchers("/adteachers").hasAnyRole("STAFF").antMatchers("/adstudent").hasAnyRole("STUDENT")
				.antMatchers("/discussion").hasAnyRole("TEACHER", "STAFF").antMatchers("/adstudents")
				.hasAnyRole("TEACHER", "STAFF").antMatchers("/adcourse").hasAnyRole("TEACHER", "STAFF")
				.antMatchers("/adlesson").hasAnyRole("TEACHER").antMatchers("/message").hasAnyRole("TEACHER", "STAFF")
				.antMatchers("/stream/**").hasAnyRole("TEACHER", "STUDENT").antMatchers("/index/**").hasAnyRole("ADMIN")
				.and().formLogin().loginPage("/login").usernameParameter("email").passwordParameter("password")
				.defaultSuccessUrl("/login?st=success").failureUrl("/login?st=error")
				// .and().requiresChannel().anyRequest().requiresSecure()
				/*
				 * .antMatchers("/account").requiresSecure()
				 * .antMatchers("/courses/**").requiresSecure()
				 * .antMatchers("/teachers/**").requiresSecure()
				 * .antMatchers("/myaccount").requiresSecure()
				 * .antMatchers("/contact").requiresSecure()
				 * .antMatchers("/forum/**").requiresSecure()
				 * .antMatchers("/detailteacher/**").requiresSecure()
				 * .antMatchers("/adteachers").requiresSecure()
				 * .antMatchers("/adstudent").requiresSecure()
				 * .antMatchers("/discussion").requiresSecure()
				 * .antMatchers("/discussion").requiresSecure()
				 * .antMatchers("/adstudents").requiresSecure()
				 * .antMatchers("/adcourse").requiresSecure()
				 * .antMatchers("/adlesson").requiresSecure()
				 * .antMatchers("/message").requiresSecure()
				 * .antMatchers("/stream/**").requiresSecure()
				 */
				.and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class).exceptionHandling()
				.accessDeniedPage("/error")

				// .anyRequest().authenticated()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/")).and().logout()
				.logoutSuccessUrl("/").permitAll().and().csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

		/*
		 * http.authorizeRequests() .antMatchers("/").permitAll()
		 * .antMatchers("/register").permitAll()
		 * .antMatchers("/confirm").permitAll().anyRequest()
		 * .authenticated().and().logout().logoutSuccessUrl("/").permitAll().and().csrf(
		 * ) .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		 */

	}

	// @formatter:on
	private Filter ssoFilter(ClientResources client, String path) {
		OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
		OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		filter.setRestTemplate(template);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(),
				client.getClient().getClientId());
		tokenServices.setRestTemplate(template);
		filter.setTokenServices(tokenServices);

		return filter;
	}

	private Filter ssoFilter() {

		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		filters.add(ssoFilter(facebook(), "/login/facebook"));
		filters.add(ssoFilter(google(), "/login/google"));
		filter.setFilters(filters);
		return filter;
	}

	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);

		return registration;
	}

	@Bean
	@ConfigurationProperties("google")
	public ClientResources google() {
		return new ClientResources();
	}

	@Bean
	@Primary
	@ConfigurationProperties("facebook")
	public ClientResources facebook() {
		return new ClientResources();
	}

}

class ClientResources {

	@NestedConfigurationProperty
	private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

	@NestedConfigurationProperty
	private ResourceServerProperties resource = new ResourceServerProperties();

	public AuthorizationCodeResourceDetails getClient() {
		return client;
	}

	public ResourceServerProperties getResource() {
		return resource;
	}
}