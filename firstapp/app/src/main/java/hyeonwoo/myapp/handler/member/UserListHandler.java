package hyeonwoo.myapp.handler.member;

import hyeonwoo.menu.AbstractMenuHandler;
import hyeonwoo.myapp.dao.UserDao;
import hyeonwoo.myapp.vo.User;
import hyeonwoo.util.Prompt;
import java.util.List;

public class UserListHandler extends AbstractMenuHandler {

  private UserDao userDao;

  public UserListHandler(UserDao userDao, Prompt prompt) {
    super(prompt);
    this.userDao = userDao;
  }

  @Override
  protected void action() {
    System.out.printf("%-4s\t%-20s\t%-20s\t%s\n", "번호", "닉네임", "이메일", "가입일");

    List<User> list = userDao.findAll();

    for (User user : list) {
      System.out.printf("%-4d\t%-20s\t%-20s\t%4$tY-%4$tm-%4$td\n",
          user.getNo(),
          user.getName(),
          user.getEmail(),
          user.getCreatedDate());
    }
  }
}
