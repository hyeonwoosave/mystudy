package hyeonwoo.myapp.servlet.freeboard;

import hyeonwoo.myapp.dao.FreeBoardDao;
import hyeonwoo.myapp.vo.FreeBoard;
import hyeonwoo.myapp.vo.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/freeboard/list")
public class FreeBoardListServlet extends GenericServlet {

  private FreeBoardDao freeboardDao;

  @Override
  public void init() {
    this.freeboardDao = (FreeBoardDao) this.getServletContext().getAttribute("freeboardDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
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
    out.println("<h1>자유게시판</h1>");

    out.println("<a href='/freeboard/form.html '>새 리뷰</a>\n");

    try {
      out.println("<table border='1'>");
      out.println("    <thead>");
      out.println("    <tr> <th>번호</th> <th>제목</th> <th>작성자</th> <th>등록일</th> </tr>");
      out.println("    </thead>");
      out.println("    <tbody>");

      List<FreeBoard> list = freeboardDao.findAll();

      for (FreeBoard freeboard : list) {
        out.printf(
            "<tr> <td>%d</td> <td><a href='/freeboard/view?no=%1$d'>%s</a></td> <td>%s</td> <td>%s</td> <td>%d</td> </tr>\n",
            freeboard.getNo(),
            freeboard.getTitle(),
            freeboard.getWriter().getName(),
            freeboard.getCreatedDate());
      }

      out.println("    </tbody>");
      out.println("</table>");

    } catch (Exception e) {
      out.println("<p>목록 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
