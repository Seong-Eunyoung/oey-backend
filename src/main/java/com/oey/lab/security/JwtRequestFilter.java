package com.oey.lab.security;

import com.oey.lab.common.JwtUtil;
import com.oey.lab.dto.CustomUserDetail;
import com.oey.lab.entity.User;
import com.oey.lab.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.equals("/") || uri.equals("/api/login") || uri.equals("/api/loginProc") || uri.equals("/api/join")) {
			filterChain.doFilter(request, response);
			return;
		}
		if (uri.startsWith("/ws")) {
			filterChain.doFilter(request, response);
			return;
		}

		String jwtToken = null;
		String subject = null;

		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwtToken = authorizationHeader.substring(7);
			try {
				subject = jwtUtil.extractUsername(jwtToken);
			} catch (Exception e) {
				log.warn("JWT 토큰 처리 중 예외 발생: {}", e.getMessage());  // 로그 레벨을 warn으로 변경
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("Invalid credentials");  // 일반적인 오류 메시지
				response.getWriter().flush();
				return;
			}
		} else {
			log.warn("Authorization 헤더 누락 또는 토큰 형식 오류");  // 로그 레벨을 warn으로 변경
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Invalid credentials");  // 일반적인 오류 메시지
			response.getWriter().flush();
			return;
		}

		if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			User user = repository.findByUserName(subject);

			if (user != null && jwtUtil.validateToken(jwtToken, subject)) {
				CustomUserDetail userDetail = new CustomUserDetail(user);
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetail, null, userDetail.getAuthorities()); // 여기서 userDetail 객체를 사용

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				log.warn("JWT 토큰 검증 실패 또는 사용자 정보가 유효하지 않음");  // 로그 레벨을 warn으로 변경
				SecurityContextHolder.getContext().setAuthentication(null);
			}
		}

		filterChain.doFilter(request, response);
	}
}