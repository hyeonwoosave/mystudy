package hyeonwoo.myapp.servlet.game;

import hyeonwoo.myapp.dao.GameDao;
import hyeonwoo.myapp.vo.Game;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/game/add")
public class GameAddServlet extends HttpServlet {

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

    try {
      Game game = new Game();
      game.setTitle(request.getParameter("title"));
      game.setRating(Integer.parseInt(request.getParameter("rating")));
      game.setPrice(Integer.parseInt(request.getParameter("price")));
      game.setProduce(request.getParameter("produce"));
      game.setGenre(request.getParameter("genre"));
      game.setInfo(request.getParameter("info"));
      game.setRelease_date(Date.valueOf(request.getParameter("release_date")));

      gameDao.add(game);

      out.println("<p>게임을 등록했습니다.</p>");

    } catch (Exception e) {
      out.println("<p>게임 등록 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
