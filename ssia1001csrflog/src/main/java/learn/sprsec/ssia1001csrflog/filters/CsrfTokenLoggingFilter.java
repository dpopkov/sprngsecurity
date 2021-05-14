package learn.sprsec.ssia1001csrflog.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class CsrfTokenLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        Object attr = servletRequest.getAttribute("_csrf");
        CsrfToken csrfToken = (CsrfToken) attr;
        log.info("CSRF token {}", csrfToken.getToken());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
