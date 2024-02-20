package hyeonwoo.myapp.servlet.user;

import hyeonwoo.myapp.dao.UserDao;
import hyeonwoo.myapp.vo.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/add")
public class UserAddServlet extends HttpServlet {

  private UserDao userDao;

  @Override
  public void init() {
    this.userDao = (UserDao) this.getServletContext().getAttribute("userDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html lang='en'>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>현우 개인프로젝트</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원</h1>");

    try {
      User user = new User();
      user.setName(request.getParameter("name"));
      user.setTel(request.getParameter("tel"));
      user.setEmail(request.getParameter("email"));
      user.setPassword(request.getParameter("password"));

      userDao.add(user);
      out.println("<p>회원을 등록했습니다.</p>");

    } catch (Exception e) {
      out.println("<p>회원등록 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
