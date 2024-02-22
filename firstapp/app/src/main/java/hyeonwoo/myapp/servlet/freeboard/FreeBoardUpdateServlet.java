package hyeonwoo.myapp.servlet.freeboard;

import hyeonwoo.myapp.dao.AttachedFileDao;
import hyeonwoo.myapp.dao.FreeBoardDao;
import hyeonwoo.myapp.vo.AttachedFile;
import hyeonwoo.myapp.vo.FreeBoard;
import hyeonwoo.myapp.vo.User;
import hyeonwoo.util.TransactionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/freeboard/update")
public class FreeBoardUpdateServlet extends HttpServlet {

  private TransactionManager txManager;
  private FreeBoardDao freeboardDao;
  private AttachedFileDao attachedFileDao;

  @Override
  public void init() {
    txManager = (TransactionManager) this.getServletContext().getAttribute("txManager");
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
      int no = Integer.parseInt(request.getParameter("no"));

      FreeBoard freeboard = freeboardDao.findBy(no);
      if (freeboard == null) {
        out.println("<p>번호가 유효하지 않습니다.</p>");
        out.println("</body>");
        out.println("</html>");
        return;
      } else if (freeboard.getWriter().getNo() != loginUser.getNo()) {
        out.println("<p>권한이 없습니다.</p>");
        out.println("</body>");
        out.println("</html>");
        return;
      }

      freeboard.setTitle(request.getParameter("title"));
      freeboard.setContent(request.getParameter("content"));

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

      String[] files = request.getParameterValues("files");
      if (files != null) {
        for (String file : files) {
          if (file.length() == 0) {
            continue;
          }
          attachedFiles.add(new AttachedFile().filePath(file));
        }
      }

      txManager.startTransaction();

      freeboardDao.update(freeboard);

      if (attachedFiles.size() > 0) {
        for (AttachedFile attachedFile : attachedFiles) {
          attachedFile.setFreeBoardNo(freeboard.getNo());
        }
        attachedFileDao.addAll(attachedFiles);
      }

      txManager.commit();

      out.println("<p>변경했습니다.</p>");

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
