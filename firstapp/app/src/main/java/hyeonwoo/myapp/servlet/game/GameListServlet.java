package hyeonwoo.myapp.servlet.game;

import hyeonwoo.myapp.dao.GameDao;
import hyeonwoo.myapp.vo.Game;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/game/list")
public class GameListServlet extends HttpServlet {

  private GameDao gameDao;

  @Override
  public void init() {
    gameDao = (GameDao) this.getServletContext().getAttribute("gameDao");
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
    out.println("<h1>게임</h1>");

    out.println("<a href='/game/form.html'>새 게임</a>");

    try {
      out.println("<table border='1'>");
      out.println("    <thead>");
      out.println("    <tr> <th>번호</th> <th>타이틀</th> <th>평점</th> <th>가격</th>"
          + " <th>제작사</th> </tr>");
      out.println("    </thead>");
      out.println("    <tbody>");

      List<Game> list = gameDao.findAll();

      for (Game game : list) {
        out.printf(
            "<tr> <td>%d</td> <td><a href='/game/view?no=%1$d'>%s</a></td> <td>%d</td> <td>%d</td>"
                + " <td>%s</td> </tr>\n",
            game.getNo(),
            game.getTitle(),
            game.getRating(),
            game.getPrice(),
            game.getProduce());
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
