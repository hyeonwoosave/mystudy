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

@WebServlet("/game/view")
public class GameViewServlet extends HttpServlet {

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
      int no = Integer.parseInt(request.getParameter("no"));

      Game game = gameDao.findBy(no);
      if (game == null) {
        out.println("<p>게임 번호가 유효하지 않습니다.</p>");
        out.println("</body>");
        out.println("</html>");
        return;
      }

      out.println("<form action='/game/update'>");
      out.println("<div>");
      out.printf("  번호: <input readonly name='no' type='text' value='%d'>\n", game.getNo());
      out.println("</div>");
      out.println("<div>");
      out.printf("  게임명: <input name='title' type='text' value='%s'>\n", game.getTitle());
      out.println("</div>");
      out.println("<div>");
      out.printf("  평점: <input name='rating' type='text' value='%d'>\n", game.getRating());
      out.println("</div>");
      out.println("<div>");
      out.printf("  가격: <input name='price' type='text' value='%d'>\n", game.getPrice());
      out.println("</div>");
      out.println("<div>");
      out.printf("  제작사: <input name='produce' type='text' value='%s'>\n", game.getProduce());
      out.println("</div>");
      out.println("<div>");
      out.printf("  장르: <input name='genre' type='text' value='%s'>\n", game.getGenre());
      out.println("</div>");
      out.println("<div>");
      out.printf("  소개: <textarea name='info'>%s</textarea>\n", game.getInfo());
      out.println("</div>");
      out.println("<div>");
      out.printf("  출시일: <input name='release_date' type='date' value='%s'>\n",
          game.getRelease_date());
      out.println("</div>");
      out.println("<div>");
      out.println("  <button>변경</button>");
      out.printf("  <a href='/game/delete?no=%d'>[삭제]</a>\n", no);
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
