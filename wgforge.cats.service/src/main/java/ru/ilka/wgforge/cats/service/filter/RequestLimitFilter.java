package ru.ilka.wgforge.cats.service.filter;

import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.ilka.wgforge.cats.service.service.RequestCountService;

import javax.servlet.*;
import java.io.IOException;

public class RequestLimitFilter implements Filter {
    private RequestCountService requestCountService;

    @Override
    public void init(FilterConfig filterConfig) {
        injectRequestCountService(filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (requestCountService == null) {
            injectRequestCountService(servletRequest.getServletContext());
        }
        requestCountService.handleNewRequest();
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void injectRequestCountService(ServletContext servletContext) {
        requestCountService = (RequestCountService) WebApplicationContextUtils
                .getRequiredWebApplicationContext(servletContext)
                .getBean("requestCountService");
    }

}
