package bitcamp.myapp.listener;

import bitcamp.util.DBConnectionPool;
import bitcamp.util.TransactionManager;
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

    bitcamp.myapp.dao.GameDao gameDao = new bitcamp.myapp.dao.mysql.GameDaoImpl(connectionPool);
    bitcamp.myapp.dao.UserDao userDao = new bitcamp.myapp.dao.mysql.UserDaoImpl(connectionPool);
    bitcamp.myapp.dao.ReviewDao reviewDao = new bitcamp.myapp.dao.mysql.ReviewDaoImpl(connectionPool);
    AttachedFileDao attachedFileDao = new AttachedFileDaoImpl(connectionPool);
    TransactionManager txManager = new TransactionManager(connectionPool);

    // 서블릿에서 사용할 수 있도록 웹애플리케이션 저장소에 보관한다.
    ServletContext 웹애플리케이션저장소 = sce.getServletContext();
    웹애플리케이션저장소.setAttribute("assignmentDao", gameDao);
    웹애플리케이션저장소.setAttribute("memberDao", userDao);
    웹애플리케이션저장소.setAttribute("boardDao", reviewDao);
    웹애플리케이션저장소.setAttribute("attachedFileDao", attachedFileDao);
    웹애플리케이션저장소.setAttribute("txManager", txManager);
  }
}
