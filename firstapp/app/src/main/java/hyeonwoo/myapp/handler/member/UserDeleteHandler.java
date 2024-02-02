package hyeonwoo.myapp.handler.member;

import hyeonwoo.menu.AbstractMenuHandler;
import hyeonwoo.myapp.dao.UserDao;
import hyeonwoo.util.Prompt;

public class UserDeleteHandler extends AbstractMenuHandler {

  private UserDao userDao;

  public UserDeleteHandler(UserDao userDao, Prompt prompt) {
    super(prompt);
    this.userDao = userDao;
  }

  @Override
  protected void action() {
    int no = this.prompt.inputInt("번호? ");
    if (userDao.delete(no) == -1) {
      System.out.println("유저 번호가 유효하지 않습니다!");
    } else {
      System.out.println("유저를 삭제했습니다.");
    }
  }
}
