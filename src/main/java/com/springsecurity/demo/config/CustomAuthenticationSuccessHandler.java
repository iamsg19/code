package com.springsecurity.demo.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		authorities.forEach(authority -> {
			if (authority.getAuthority().equals("Role1")) {
				try {
					redirectStrategy.sendRedirect(request, response, "/api/register");
				} catch (Exception e) {

					e.printStackTrace();
				}
			} else if (authority.getAuthority().equals("Role2")) {
				try {
					redirectStrategy.sendRedirect(request, response, "/api/viewData");
				} catch (Exception e) {

					e.printStackTrace();
				}
			} else if (authority.getAuthority().equals("Role3")) {
				try {
					redirectStrategy.sendRedirect(request, response, "/api/recomended/data");
				} catch (Exception e) {

					e.printStackTrace();
				}
			} else if (authority.getAuthority().equals("Role4")) {
				try {
					redirectStrategy.sendRedirect(request, response, "/api/final/data");
				} catch (Exception e) {

					e.printStackTrace();
				}
			} else {
				throw new IllegalStateException();
			}
		});

	}

}
