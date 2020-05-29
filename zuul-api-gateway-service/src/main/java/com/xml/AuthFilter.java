package com.xml;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;

@Component
@CrossOrigin(value = "https://localhost:4200")
public class AuthFilter extends ZuulFilter {

    @Autowired
    private AuthClient authClient;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    private void setFailedRequest(String body, int code) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("Usao je u zuul");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        if (request.getHeader("Authorization") == null) {
            return null;
        }

        String fullToken = request.getHeader("Authorization");
        String token = request.getHeader("Authorization").substring(7);
        System.out.println(token);
        try {
            System.out.println("da li je dobar ");
            Collection<Permission> permissions = authClient.verify(token);
            System.out.println(permissions.toString());

            Collection<String> permissionString = new HashSet<>();
            for (Permission p : permissions) {
                permissionString.add(p.getName());
            }

            Long userId = authClient.getLoggedInUser(token);
            System.out.println("logovan korisnike je " + userId);

            ctx.addZuulRequestHeader("Authorization", fullToken);
            ctx.addZuulRequestHeader("Authorities", permissionString.toString());
            ctx.addZuulRequestHeader("UserId", Long.toString(userId));

        } catch (FeignException e) {
            e.printStackTrace();
            setFailedRequest("Consumer does not exist!", 403);
        }

        return null;
    }


}
