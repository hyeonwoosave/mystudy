package hyeonwoo.myapp.servlet.review;

import hyeonwoo.myapp.dao.ReviewDao;
import hyeonwoo.myapp.vo.Review;
import hyeonwoo.myapp.vo.User;
import hyeonwoo.util.TransactionManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/review/add")
public class ReviewAddServlet extends HttpServlet {

  private TransactionManager txManager;
  private ReviewDao reviewDao;
  
  @Override
  public void init() {
    txManager = (TransactionManager) this.getServletContext().getAttribute("txManager");
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

    User loginUser = (User) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      out.println("<p>로그인하시기 바랍니다!</p>");
      out.println("</body>");
      out.println("</html>");
      return;
    }

    Review review = new Review();
    review.setCategory(category);
    review.setTitle(request.getParameter("title"));
    review.setRating(Integer.parseInt(request.getParameter("rating")));
    review.setContent(request.getParameter("content"));
    review.setWriter(loginUser);

    try {
      txManager.startTransaction();
      reviewDao.add(review);
      txManager.commit();

      out.println("<p>등록했습니다.</p>");

    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e2) {
      }
      out.println("<p>등록 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
