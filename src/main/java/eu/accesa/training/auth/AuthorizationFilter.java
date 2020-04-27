package eu.accesa.training.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authHeader = ((HttpServletRequest)request).getHeader("X-Authentication");

        //if(authHeader != null && !authHeader.isEmpty()) {
            // authHeader resolve JWT claims
            log.info("Got auth header: {}", authHeader);
            chain.doFilter(request, response);
//        } else {
//            log.error("No auth header found!");
//            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN, "Not authorized");
//        }

    }

}
