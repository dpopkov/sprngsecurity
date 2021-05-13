package learn.sprsec.ssia0901filterbefore.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class AuthenticationLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) servletRequest;
        String requestId = httpRequest.getHeader("Request-Id");
        log.info("Successfully authenticated request with id {}", requestId);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
