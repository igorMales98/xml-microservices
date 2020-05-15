package com.xml;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
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

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        if (request.getHeader("token") == null) {
            return null;
        };

        String token = request.getHeader("Authorization");
        try {
            authClient.verify(token);

            //ctx.addZuulRequestHeader("username", email);
            //ctx.addZuulRequestHeader("role", "SIMPLE_USER");

        } catch (FeignException.NotFound e) {
            setFailedRequest("Consumer does not exist!", 403);
        }

        return null;
    }


}
