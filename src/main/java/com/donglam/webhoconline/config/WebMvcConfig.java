package com.donglam.webhoconline.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Value("${paypal.client.app}")
	private String clientId;
	@Value("${paypal.client.secret}")
	private String clientSecret;
	@Value("${paypal.mode}")
	private String mode;
	/*
	 * @Bean public FilterRegistrationBean processCorsFilter() {
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); CorsConfiguration config = new
	 * CorsConfiguration(); config.setAllowCredentials(true);
	 * config.addAllowedOrigin("*"); config.addAllowedHeader("*");
	 * config.addAllowedMethod("*"); source.registerCorsConfiguration("/**",
	 * config);
	 * 
	 * FilterRegistrationBean bean = new FilterRegistrationBean(new
	 * CorsFilter(source)); bean.setOrder(0); return bean; }
	 */

	@Bean
	public Map<String, String> paypalSdkConfig() {
		Map<String, String> sdkConfig = new HashMap<>();
		sdkConfig.put("mode", mode);
		return sdkConfig;
	}

	@Bean
	public OAuthTokenCredential authTokenCredential() {
		return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
	}

	@Bean
	public APIContext apiContext() throws PayPalRESTException {
		APIContext apiContext = new APIContext(authTokenCredential().getAccessToken());
		apiContext.setConfigurationMap(paypalSdkConfig());
		return apiContext;
	}

	/**/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// file:D:\\data\\file\\image\\
		registry.addResourceHandler("/public/**").addResourceLocations("classpath:/static/uploadmedia/");
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/ckfinder/**").addResourceLocations("classpath:/static/ckfinder/");
		super.addResourceHandlers(registry);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	/*@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};

		tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
		return tomcat;
	}

	private Connector initiateHttpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);

		return connector;
	}*/

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(new MyCounterInterceptor());
	}

}