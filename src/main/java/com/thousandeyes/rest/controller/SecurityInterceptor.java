package com.thousandeyes.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.thousandeyes.dao.TweetJDBCTemplate;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private TweetJDBCTemplate jdbcTemplate;

	public TweetJDBCTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(TweetJDBCTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String apiKey = request.getParameter("api_key");
		
		if(request.getRequestURL().toString().contains("/restKey/generateApiKey")){
			return true;
		}
		if(apiKey==null){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		String sql = "SELECT * FROM USERDETAILS WHERE APIKEY=?";
		List key = jdbcTemplate.getJdbcTemplateObject().queryForList(sql, apiKey);
		if (key == null || key.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}

		return true;
	}
}
