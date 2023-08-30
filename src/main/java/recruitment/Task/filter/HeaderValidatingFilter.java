package recruitment.Task.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import recruitment.Task.DTO.ErrorDTO;
import recruitment.Task.exception.BadHeaderException;
import recruitment.Task.exception.ExceptionsAdviser;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@Order(1)
public class HeaderValidatingFilter implements Filter {

    private ExceptionsAdviser exceptionAdviser;

    @Override
    public void init(FilterConfig arg0) {
        //Manually get an instance of MyExceptionController
        ApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(arg0.getServletContext());

        //MyExceptionHandler is now accessible because I loaded it manually
        this.exceptionAdviser = ctx.getBean(ExceptionsAdviser.class);
    }

    @Override
    public void doFilter(ServletRequest aServletRequest, ServletResponse aServletResponse, FilterChain aFilterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) aServletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) aServletResponse;

        String acceptHeader = httpRequest.getHeader("Accept");
        try {
            if(acceptHeader == null || !acceptHeader.equals("application/json")){
                throw new BadHeaderException();
            }
        } catch(BadHeaderException ex) {
            ErrorDTO errorDTO = exceptionAdviser.handleBadHeaderException();

            //set the response object
            httpResponse.setStatus(errorDTO .getStatus());
            httpResponse.setContentType("application/json");

            //pass down the actual obj that exception handler normally send
            ObjectMapper mapper = new ObjectMapper();
            PrintWriter out = httpResponse.getWriter();
            out.print(mapper.writeValueAsString(errorDTO ));
            out.flush();

            return;
        }

        aFilterChain.doFilter(httpRequest, aServletResponse);
    }
}
