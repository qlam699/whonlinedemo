package com.donglam.webhoconline.config;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyCounterInterceptor extends HandlerInterceptorAdapter {
	private AtomicInteger counter = new AtomicInteger(0);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		request.setAttribute("visitorCounter", counter.incrementAndGet());
		return true;
	}
}