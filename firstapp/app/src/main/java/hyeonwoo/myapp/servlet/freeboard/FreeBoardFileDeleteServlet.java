package hyeonwoo.myapp.servlet.freeboard;

import hyeonwoo.myapp.dao.AttachedFileDao;
import hyeonwoo.myapp.dao.FreeBoardDao;
import hyeonwoo.myapp.vo.AttachedFile;
import hyeonwoo.myapp.vo.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/freeboard/file/delete")
public class FreeBoardFileDeleteServlet extends HttpServlet {

  private FreeBoardDao freeboardDao;
  private AttachedFileDao attachedFileDao;

  @Override
  public void init() {
    this.freeboardDao = (FreeBoardDao) this.getServletContext().getAttribute("freeboardDao");
    this.attachedFileDao = (AttachedFileDao) this.getServletContext()
        .getAttribute("attachedFileDao");
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

    User loginUser = (User) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      out.println("<p>로그인하시기 바랍니다!</p>");
      out.println("</body>");
      out.println("</html>");
      return;
    }

    try {
      int fileNo = Integer.parseInt(request.getParameter("no"));

      AttachedFile file = attachedFileDao.findByNo(fileNo);
      if (file == null) {
        out.println("<p>첨부파일 번호가 유효하지 않습니다.</p>");
        out.println("</body>");
        out.println("</html>");
        return;
      }

      User writer = freeboardDao.findBy(file.getFreeBoardNo()).getWriter();
      if (writer.getNo() != loginUser.getNo()) {
        out.println("<p>권한이 없습니다.</p>");
        out.println("</body>");
        out.println("</html>");
        return;
      }

      attachedFileDao.delete(fileNo);
      out.println("<script>");
      out.println("  location.href = document.referrer;");
      out.println("</script>");
//      out.println("<p>첨부파일을 삭제했습니다!</p>");

    } catch (Exception e) {
      out.println("<p>삭제 오류!</p>");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}