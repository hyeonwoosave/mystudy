package hyeonwoo.myapp.handler.member;

import hyeonwoo.menu.AbstractMenuHandler;
import hyeonwoo.myapp.dao.UserDao;
import hyeonwoo.myapp.vo.User;
import hyeonwoo.util.Prompt;

public class UserViewHandler extends AbstractMenuHandler {

  private UserDao userDao;

  public UserViewHandler(UserDao userDao, Prompt prompt) {
    super(prompt);
    this.userDao = userDao;
  }

  @Override
  protected void action() {
    int no = this.prompt.inputInt("번호? ");

    User user = userDao.findBy(no);
    if (user == null) {
      System.out.println("유저 번호가 유효하지 않습니다!");
      return;
    }

    System.out.printf("번호: %d\n", user.getNo());
    System.out.printf("이메일: %s\n", user.getEmail());
    System.out.printf("닉네임: %s\n", user.getName());
    System.out.printf("가입일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", user.getCreatedDate());
  }
}
