package hyeonwoo.myapp.servlet.review;

import hyeonwoo.myapp.dao.ReviewDao;
import hyeonwoo.myapp.vo.Review;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/review/view")
public class ReviewViewServlet extends HttpServlet {

  private ReviewDao reviewDao;

  @Override
  public void init() {
    this.reviewDao = (ReviewDao) this.getServletContext().getAttribute("reviewDao");
  }

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

    try {
      int no = Integer.parseInt(request.getParameter("no"));

        Review review = reviewDao.findBy(no);
        if (review == null) {
          out.println("<p>번호가 유효하지 않습니다.</p>");
          out.println("</body>");
          out.println("</html>");
          return;

      }

      out.println("<form action='/review/update'>");
      out.printf("<input name='category' type='hidden' value='%d'>\n", category);
      out.println("<div>");
      out.printf("  번호: <input readonly name='no' type='text' value='%d'>\n", review.getNo());
      out.println("</div>");
      out.println("<div>");
      out.printf("  평점: <input name='rating' type='text' value='%d'>\n", review.getRating());
      out.println("</div>");
      out.println("<div>");
      out.printf("  내용: <textarea name='content'>%s</textarea>\n", review.getContent());
      out.println("</div>");
      out.println("<div>");
      out.printf("  작성자: <input readonly type='text' value='%s'>\n", review.getWriter().getName());
      out.println("</div>");

      out.println("<div>");
      out.println("  <button>변경</button>");
      out.printf("  <a href='/review/delete?category=%d&no=%d'>[삭제]</a>\n", category, no);
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
