package com.thousandeyes.rest.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.thousandeyes.rest.service.IKeyService;

public class AuthenticationFilter implements Filter {

	
	private IKeyService keyService;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		
		
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			String authCredentials = request.getParameter("api_key");

			/*boolean authenticationStatus = authenticationService
					.authenticate(authCredentials);
*/
			
			keyService.checkAPIKeys(authCredentials);
			boolean authenticationStatus = false;
			if (authenticationStatus) {
				filter.doFilter(request, response);
			} else {
				if (response instanceof HttpServletResponse) {
					HttpServletResponse httpServletResponse = (HttpServletResponse) response;
					httpServletResponse
							.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
			}
		}
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		WebApplicationContext springContext = 
		        WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		keyService = (IKeyService) springContext.getBean("keyService");
		System.out.println("Hii");
	}

}
