package com.xml;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

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
            // authClient.verify(token);
            System.out.println("da li je dobar " + authClient.verify(token));
            ctx.addZuulRequestHeader("Authorization", fullToken);
            //ctx.addZuulRequestHeader("role", "SIMPLE_USER");

        } catch (FeignException e) {
            e.printStackTrace();
            setFailedRequest("Consumer does not exist!", 403);
        }

        return null;
    }


}
