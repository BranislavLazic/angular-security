package org.blogapp.filter;


import io.jsonwebtoken.*;
import org.blogapp.authentication.TokenHandler;
import org.blogapp.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtFilter implements Filter {

    private UserService userService;

    public JwtFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");

        HttpServletRequest request = (HttpServletRequest) req;

        if(!request.getMethod().equals("OPTIONS")) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("Missing or invalid Authorization header.");
            }

            String token = authHeader.substring(7);

            Claims claims = Jwts.parser().setSigningKey(TokenHandler.SECRET)
                    .parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
            chain.doFilter(req, res);
        }

    }

    @Override
    public void destroy() {

    }
}
