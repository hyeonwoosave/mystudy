package hyeonwoo.myapp.servlet.freeboard;

import hyeonwoo.myapp.dao.FreeBoardDao;
import hyeonwoo.myapp.vo.FreeBoard;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/freeboard/view")
public class FreeBoardViewServlet extends HttpServlet {

  private FreeBoardDao freeboardDao;

  @Override
  public void init() {
    this.freeboardDao = (FreeBoardDao) this.getServletContext().getAttribute("freeboardDao");
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
    out.println("<h1>자유게시판</h1>");

    try {
      int no = Integer.parseInt(request.getParameter("no"));

        FreeBoard freeboard = freeboardDao.findBy(no);
        if (freeboard == null) {
          out.println("<p>번호가 유효하지 않습니다.</p>");
          out.println("</body>");
          out.println("</html>");
          return;

      }

      out.println("<form action='/freeboard/update'>");
      out.println("<div>");
      out.printf("  번호: <input readonly name='no' type='text' value='%d'>\n", freeboard.getNo());
      out.println("</div>");
      out.println("<div>");
      out.printf("  제목: <input name='title' type='text' value='%s'>\n", freeboard.getTitle());
      out.println("</div>");
      out.println("<div>");
      out.printf("  내용: <textarea name='content'>%s</textarea>\n", freeboard.getContent());
      out.println("</div>");
      out.println("<div>");
      out.printf("  작성자: <input readonly type='text' value='%s'>\n", freeboard.getWriter().getName());
      out.println("</div>");

      out.println("<div>");
      out.println("  <button>변경</button>");
      out.printf("  <a href='/freeboard/delete?no=%d'>[삭제]</a>\n", no);
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
