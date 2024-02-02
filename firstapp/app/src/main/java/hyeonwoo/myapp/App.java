package hyeonwoo.myapp;

import hyeonwoo.menu.MenuGroup;
import hyeonwoo.myapp.dao.GameDao;
import hyeonwoo.myapp.dao.ReviewDao;
import hyeonwoo.myapp.dao.UserDao;
import hyeonwoo.myapp.dao.mysql.GameDaoImpl;
import hyeonwoo.myapp.dao.mysql.ReviewDaoImpl;
import hyeonwoo.myapp.dao.mysql.UserDaoImpl;
import hyeonwoo.myapp.handler.game.GameAddHandler;
import hyeonwoo.myapp.handler.game.GameDeleteHandler;
import hyeonwoo.myapp.handler.game.GameListHandler;
import hyeonwoo.myapp.handler.game.GameModifyHandler;
import hyeonwoo.myapp.handler.game.GameViewHandler;
import hyeonwoo.myapp.handler.member.UserAddHandler;
import hyeonwoo.myapp.handler.member.UserDeleteHandler;
import hyeonwoo.myapp.handler.member.UserListHandler;
import hyeonwoo.myapp.handler.member.UserModifyHandler;
import hyeonwoo.myapp.handler.member.UserViewHandler;
import hyeonwoo.myapp.handler.review.ReviewAddHandler;
import hyeonwoo.myapp.handler.review.ReviewDeleteHandler;
import hyeonwoo.myapp.handler.review.ReviewListHandler;
import hyeonwoo.myapp.handler.review.ReviewModifyHandler;
import hyeonwoo.myapp.handler.review.ReviewViewHandler;
import hyeonwoo.util.Prompt;
import java.sql.Connection;
import java.sql.DriverManager;

public class App {

  Prompt prompt = new Prompt(System.in);

  GameDao gameDao;
  ReviewDao reviewDao;
  UserDao userDao;

  MenuGroup mainMenu;

  App() {
    prepareDatabase();
    prepareMenu();
  }

  public static void main(String[] args) {
    System.out.println("[게임 관리]");
    new App().run();
  }

  void prepareDatabase() {
    try {
      // JVM이 JDBC 드라이버 파일(.jar)에 설정된대로 자동으로 처리한다.
//      Driver driver = new com.mysql.cj.jdbc.Driver();
//      DriverManager.registerDriver(driver);

      Connection con = DriverManager.getConnection(
          // "jdbc:mysql://localhost/studydb", "study", "Bitcamp!@#123");
          "jdbc:mysql://db-ld2bd-kr.vpc-pub-cdb.ntruss.com/studydb", "study", "Bitcamp!@#123");

      gameDao = new GameDaoImpl(con);
      reviewDao = new ReviewDaoImpl(con);
      userDao = new UserDaoImpl(con);

    } catch (Exception e) {
      System.out.println("통신 오류!");
      e.printStackTrace();
    }
  }

  void prepareMenu() {
    mainMenu = MenuGroup.getInstance("메인");

    MenuGroup assignmentMenu = mainMenu.addGroup("게임");
    assignmentMenu.addItem("등록", new GameAddHandler(gameDao, prompt));
    assignmentMenu.addItem("조회", new GameViewHandler(gameDao, prompt));
    assignmentMenu.addItem("변경", new GameModifyHandler(gameDao, prompt));
    assignmentMenu.addItem("삭제", new GameDeleteHandler(gameDao, prompt));
    assignmentMenu.addItem("목록", new GameListHandler(gameDao, prompt));

    MenuGroup boardMenu = mainMenu.addGroup("리뷰");
    boardMenu.addItem("등록", new ReviewAddHandler(reviewDao, prompt));
    boardMenu.addItem("조회", new ReviewViewHandler(reviewDao, prompt));
    boardMenu.addItem("변경", new ReviewModifyHandler(reviewDao, prompt));
    boardMenu.addItem("삭제", new ReviewDeleteHandler(reviewDao, prompt));
    boardMenu.addItem("목록", new ReviewListHandler(reviewDao, prompt));

    MenuGroup userMenu = mainMenu.addGroup("유저");
    userMenu.addItem("등록", new UserAddHandler(userDao, prompt));
    userMenu.addItem("조회", new UserViewHandler(userDao, prompt));
    userMenu.addItem("변경", new UserModifyHandler(userDao, prompt));
    userMenu.addItem("삭제", new UserDeleteHandler(userDao, prompt));
    userMenu.addItem("목록", new UserListHandler(userDao, prompt));

  }

  void run() {
    while (true) {
      try {
        mainMenu.execute(prompt);
        prompt.close();
        break;
      } catch (Exception e) {
        System.out.println("예외 발생!");
      }
    }
  }

}
