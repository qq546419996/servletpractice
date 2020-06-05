import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    public static int count=0;

    //session创建时计数+1
    @Override
    public void sessionCreated(HttpSessionEvent event){
        HttpSession httpSession=event.getSession();
        ServletContext servletContext=httpSession.getServletContext();
        count++;
        servletContext.setAttribute("count",count);
    }

    //session销毁时计数-1
    @Override
    public void sessionDestroyed(HttpSessionEvent event){
        HttpSession httpSession=event.getSession();
        ServletContext servletContext=httpSession.getServletContext();
        count--;
        servletContext.setAttribute("count",count);
    }

}
