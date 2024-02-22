package hyeonwoo.myapp.servlet.review;

import hyeonwoo.myapp.dao.ReviewDao;
import hyeonwoo.myapp.vo.Review;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/review/list")
public class ReviewListServlet extends GenericServlet {

  private ReviewDao reviewDao;

  @Override
  public void init() {
    this.reviewDao = (ReviewDao) this.getServletContext().getAttribute("reviewDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
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

    out.printf("<a href='/review/form?category=%d'>새 리뷰</a>\n", category);

    try {
      out.println("<table border='1'>");
      out.println("    <thead>");
      out.println("    <tr> <th>번호</th> <th>제목</th> <th>평점</th> <th>작성자</th> <th>등록일</th> </tr>");
      out.println("    </thead>");
      out.println("    <tbody>");

      List<Review> list = reviewDao.findAll(category);

      for (Review review : list) {
        out.printf(
            "<tr> <td>%d</td> <td><a href='/review/view?category=%d&no=%1$d'>%s</a></td> <td>%d</td> <td>%s</td> <td>%s</td> </tr>\n",
            review.getNo(),
            category,
            review.getTitle(),
            review.getRating(),
            review.getWriter().getName(),
            review.getCreatedDate());
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
