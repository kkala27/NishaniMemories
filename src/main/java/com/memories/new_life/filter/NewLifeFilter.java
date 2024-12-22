package com.memories.new_life.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NewLifeFilter implements Filter {

	@Value("${app.skippeduri.string}")
	String skippedUriString;
	
	@Value("${app.active.profile}")
	String activeProfile;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("########## Initiating NewLifeFilter filter ##########");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		log.info("Active profile detail"+activeProfile);
		if(request.getSession().getAttribute("profile")==null) {
			request.getSession().setAttribute("profile", activeProfile);
		}
		List<String> skippedUris = getSkippedUris();
		if (request.getSession().getAttribute("access-token") != null) {
			String accessToken = request.getSession().getAttribute("access-token").toString();
			log.info("Access token for request => " + accessToken);
			filterChain.doFilter(request, response);
		} else if (skippedUris.contains(request.getRequestURI()) || request.getRequestURI().contains("/getImage")
				|| request.getRequestURI().contains("/css") || request.getRequestURI().contains("/js")||request.getRequestURI().contains("/sendOtp")) {
			filterChain.doFilter(request, response);
		} else {
			response.sendRedirect(String.valueOf(request.getContextPath()) + "/login");
			log.error("Authentication failed for => " + request.getRequestURI());
			return;
		}
	}

	@Override
	public void destroy() {

	}

	private List<String> getSkippedUris() {
		return Arrays.asList(skippedUriString.split(","));
	}

}
