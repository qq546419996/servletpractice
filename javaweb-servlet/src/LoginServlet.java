import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                response.setContentType("text/html; charset=UTF-8");
                request.setCharacterEncoding("UTF-8");

        PrintWriter output=response.getWriter();
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        //output.println("账号:  "+username+"  密码:  "+pwd+"<br>");
        ResultSet rs ;
        Statement statement = null;
        Connection conn =null;
        String url="jdbc:mysql://localhost:3306/servlet_test";
        String user="root";
        String password="root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
            String sql="select * from servlet_test.tb_login where username='"+username+"' and password='"+pwd+"'";
            statement =conn.createStatement();
            rs = statement.executeQuery(sql);

            if(rs.next()){
                output.println("登录成功！<br>");
                output.println("当前用户：  "+username+" <br>");

                Cookie cookieUser = new Cookie("usname",username);
                cookieUser.setMaxAge(60*60*24*30);
                response.addCookie(cookieUser);

                Cookie cookiePwd= new Cookie("password",pwd);
                cookiePwd.setMaxAge(60*60*24*30);
                response.addCookie(cookiePwd);
                //创建session
                HttpSession session=request.getSession();
                //获取session内的计数
                Object count;
                ServletContext servletContext=getServletContext();
                count=servletContext.getAttribute("count");
                //显示当前在线人数
                output.println("当前在线人数："+count);
            }else {
                output.println("账号或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
