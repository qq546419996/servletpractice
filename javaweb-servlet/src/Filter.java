import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "Filter")
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String encoding=req.getCharacterEncoding();
        System.out.println("before encoding"+encoding+"filter");
        encoding="utf-8";
        req.setCharacterEncoding(encoding);
        resp.setContentType("text/html;charset="+encoding);
        System.out.println("after encoding"+encoding+"filter");
        System.err.println("----------------------------------");
        //chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
