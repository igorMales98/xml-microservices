package com.xml.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthoritiesFilter extends OncePerRequestFilter {

    public AuthoritiesFilter() {

    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("putanja je " + httpServletRequest.getRequestURI());
        if (httpServletRequest.getRequestURI().contains("/ws") || httpServletRequest.getRequestURI().contains(".ico") || httpServletRequest.getRequestURI().contains("schema")) {
            System.out.println("Requested schema.");
        } else {
            String token = httpServletRequest.getHeader("Authorization");
            String authorities = httpServletRequest.getHeader("Authorities");
            String username = httpServletRequest.getHeader("Authorities");
            if (authorities == null) {
                authorities = "[TEST]";
            }
            System.out.println(authorities);
            System.out.println(authorities.substring(1, authorities.length() - 1));
            String[] allPermissions = authorities.substring(1, authorities.length() - 1).split(",");

            final List<GrantedAuthority> AUTHORITIES = new ArrayList<>();

            for (String permission : allPermissions) {
                AUTHORITIES.add(new SimpleGrantedAuthority(permission.replace(" ", "")));
            }

            SecurityContextHolder.getContext().setAuthentication(new AbstractAuthenticationToken(AUTHORITIES) {
                @Override
                public boolean isAuthenticated() {
                    return true;
                }

                @Override
                public Object getCredentials() {
                    return token;
                }

                @Override
                public Object getPrincipal() {
                    return new UserDetails() {
                        @Override
                        public Collection<? extends GrantedAuthority> getAuthorities() {
                            return AUTHORITIES;
                        }

                        @Override
                        public String getPassword() {
                            return "123123";
                        }

                        @Override
                        public String getUsername() {
                            return username;
                        }

                        @Override
                        public boolean isAccountNonExpired() {
                            return true;
                        }

                        @Override
                        public boolean isAccountNonLocked() {
                            return true;
                        }

                        @Override
                        public boolean isCredentialsNonExpired() {
                            return true;
                        }

                        @Override
                        public boolean isEnabled() {
                            return true;
                        }
                    };
                }
            });

            System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        }


        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
