package hyeonwoo.myapp.servlet.review;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/review/form")
public class ReviewFormServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int category = Integer.valueOf(request.getParameter("category"));
    String title = category == 1 ? "리뷰(1)" : "리뷰(2)";

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html lang='en'>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>현우 개인프로젝트</title>");
    out.println("</head>");
    out.println("<body>");
    out.printf("<h1>%s</h1>\n", title);

    out.printf("<form action='/review/add?category=%d'>\n", category);
    out.printf("<input name='category' type='hidden' value='%d'>\n", category);
    out.println("<div>");
    out.println("      제목: <input name='title' type='text'>");
    out.println("</div>");
    out.println("<div>");
    out.println("      평점: <input name='rating' type='text'>");
    out.println("</div>");
    out.println("<div>");
    out.println("      내용: <textarea name='content'></textarea>");
    out.println("</div>");

    out.println("<div>");
    out.println("  <button>등록</button>");
    out.println("</div>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }
}
