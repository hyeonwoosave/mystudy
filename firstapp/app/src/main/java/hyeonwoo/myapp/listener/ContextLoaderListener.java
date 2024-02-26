package hyeonwoo.myapp.listener;

import hyeonwoo.myapp.dao.AttachedFileDao;
import hyeonwoo.myapp.dao.mysql.AttachedFileDaoImpl;
import hyeonwoo.util.DBConnectionPool;
import hyeonwoo.util.TransactionManager;
import hyeonwoo.myapp.dao.FreeBoardDao;
import hyeonwoo.myapp.dao.GameDao;
import hyeonwoo.myapp.dao.QnaDao;
import hyeonwoo.myapp.dao.ReviewDao;
import hyeonwoo.myapp.dao.UserDao;
import hyeonwoo.myapp.dao.mysql.FreeBoardDaoImpl;
import hyeonwoo.myapp.dao.mysql.GameDaoImpl;
import hyeonwoo.myapp.dao.mysql.QnaDaoImpl;
import hyeonwoo.myapp.dao.mysql.ReviewDaoImpl;
import hyeonwoo.myapp.dao.mysql.UserDaoImpl;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
  // 웹애플리케이션이 사용할 자원을 준비시키고 해제시키는 역할


  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("웹애플리케이션 자원 준비!");

    // DB 커넥션, DAO, 트랜잭션 관리자 생성
    DBConnectionPool connectionPool = new DBConnectionPool(
        "jdbc:mysql://localhost/studydb", "study", "Bitcamp!@#123");

    GameDao gameDao = new GameDaoImpl(connectionPool);
    UserDao userDao = new UserDaoImpl(connectionPool);
    FreeBoardDao freeBoardDao = new FreeBoardDaoImpl(connectionPool);
    ReviewDao reviewDao = new ReviewDaoImpl(connectionPool);
    QnaDao qnaDao = new QnaDaoImpl(connectionPool);
    AttachedFileDao attachedFileDao = new AttachedFileDaoImpl(connectionPool);

    TransactionManager txManager = new TransactionManager(connectionPool);

    // 서블릿에서 사용할 수 있도록 웹애플리케이션 저장소에 보관한다.
    ServletContext 웹애플리케이션저장소 = sce.getServletContext();
    웹애플리케이션저장소.setAttribute("gameDao", gameDao);
    웹애플리케이션저장소.setAttribute("userDao", userDao);
    웹애플리케이션저장소.setAttribute("freeboardDao", freeBoardDao);
    웹애플리케이션저장소.setAttribute("reviewDao", reviewDao);
    웹애플리케이션저장소.setAttribute("qnaDao", qnaDao);
    웹애플리케이션저장소.setAttribute("txManager", txManager);
    웹애플리케이션저장소.setAttribute("attachedFileDao", attachedFileDao);


  }
}
