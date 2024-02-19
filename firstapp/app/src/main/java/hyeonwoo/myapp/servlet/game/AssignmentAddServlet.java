package bitcamp.myapp.servlet.assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/assignment/add")
public class AssignmentAddServlet extends HttpServlet {

  private bitcamp.myapp.dao.GameDao gameDao;

  @Override
  public void init() {
    gameDao = (bitcamp.myapp.dao.GameDao) this.getServletContext().getAttribute("assignmentDao");
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
    out.println("<h1>과제</h1>");

    try {
      bitcamp.myapp.vo.Game game = new bitcamp.myapp.vo.Game();
      game.setTitle(request.getParameter("title"));
      game.setContent(request.getParameter("content"));
      game.setDeadline(Date.valueOf(request.getParameter("deadline")));

      gameDao.add(game);

      out.println("<p>과제를 등록했습니다.</p>");

    } catch (Exception e) {
      out.println("<p>과제 등록 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
