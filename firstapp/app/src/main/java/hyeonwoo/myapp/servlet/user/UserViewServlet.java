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

@WebServlet("/user/view")
public class UserViewServlet extends HttpServlet {

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
      int no = Integer.parseInt(request.getParameter("no"));

      User user = userDao.findBy(no);
      if (user == null) {
        out.println("<p>회원 번호가 유효하지 않습니다.</p>");
        out.println("</body>");
        out.println("</html>");
        return;
      }

      out.println("<form action='/user/update'>");
      out.println("<div>");
      out.printf("  번호: <input readonly name='no' type='text' value='%d'>\n", user.getNo());
      out.println("</div>");
      out.println("<div>");
      out.printf("  이름: <input name='name' type='text' value='%s'>\n", user.getName());
      out.println("</div>");
      out.println("<div>");
      out.printf("  휴대폰번호: <input name='tel' type='text' value='%s'>\n", user.getTel());
      out.println("</div>");
      out.println("<div>");
      out.printf("  이메일: <input name='email' type='text' value='%s'>\n", user.getEmail());
      out.println("</div>");
      out.println("<div>");
      out.println("  암호: <input name='password' type='password'>");
      out.println("</div>");
      out.println("<div>");
      out.printf("  가입일: <input readonly type='text' value='%s'>\n", user.getCreatedDate());
      out.println("</div>");
      out.println("<div>");
      out.println("  <button>변경</button>");
      out.printf("  <a href='/user/delete?no=%d'>[삭제]</a>\n", no);
      out.println("</div>");
      out.println("</form>");

    } catch (Exception e) {
      out.println("<p>조회 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
