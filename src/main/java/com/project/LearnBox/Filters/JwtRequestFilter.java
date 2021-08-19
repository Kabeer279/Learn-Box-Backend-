package com.project.LearnBox.Filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.LearnBox.Config.JwtUtil;
import com.project.LearnBox.Security.MyUserDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		
		final String header = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		if(header !=null && header.startsWith("Bearer ")) {
			jwt = header.substring(7);
			
			try {
			username = jwtUtil.extractUsername(jwt);
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		
			
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(token);	
			//System.out.print(jwt);
		}
		else {
			System.out.print("Token not validated");
		}
		}
		filterChain.doFilter(request,response);
		
	}

}
