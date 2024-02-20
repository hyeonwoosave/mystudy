package hyeonwoo.myapp.servlet.auth;

import hyeonwoo.myapp.dao.UserDao;
import hyeonwoo.myapp.vo.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

  UserDao userDao;

  @Override
  public void init() {
    this.userDao = (UserDao) this.getServletContext().getAttribute("userDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html lang='en'>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>현우 개인프로젝트</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게임 관리 시스템</h1>");
    out.println("<h2>로그인</h2>");

    try {
      User user = userDao.findByEmailAndPassword(email, password);
      if (user != null) {
        request.getSession().setAttribute("loginUser", user);
        out.printf("<p>%s 님 환영합니다.</p>\n", user.getName());
      } else {
        out.println("<p>이메일 또는 암호가 맞지 않습니다.</p>");
      }
    } catch (Exception e) {
      out.println("<p>로그인 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
