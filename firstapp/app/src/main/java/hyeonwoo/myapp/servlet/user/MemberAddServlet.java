package bitcamp.myapp.servlet.member;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

  private bitcamp.myapp.dao.UserDao userDao;

  @Override
  public void init() {
    this.userDao = (bitcamp.myapp.dao.UserDao) this.getServletContext().getAttribute("memberDao");
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
    out.println("  <title>비트캠프 데브옵스 5기</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원</h1>");

    try {
      bitcamp.myapp.vo.User user = new bitcamp.myapp.vo.User();
      user.setEmail(request.getParameter("email"));
      user.setName(request.getParameter("name"));
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
