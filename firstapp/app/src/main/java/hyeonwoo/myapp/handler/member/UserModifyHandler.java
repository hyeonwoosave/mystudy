package hyeonwoo.myapp.handler.member;

import hyeonwoo.menu.AbstractMenuHandler;
import hyeonwoo.myapp.dao.UserDao;
import hyeonwoo.myapp.vo.User;
import hyeonwoo.util.Prompt;

public class UserModifyHandler extends AbstractMenuHandler {

  private UserDao userDao;

  public UserModifyHandler(UserDao userDao, Prompt prompt) {
    super(prompt);
    this.userDao = userDao;
  }

  @Override
  protected void action() {
    int no = this.prompt.inputInt("번호? ");

    User old = userDao.findBy(no);
    if (old == null) {
      System.out.println("유저 번호가 유효하지 않습니다!");
      return;
    }

    User user = new User();
    user.setNo(old.getNo());
    user.setEmail(this.prompt.input("이메일(%s)? ", old.getEmail()));
    user.setName(this.prompt.input("닉네임(%s)? ", old.getName()));
    user.setPassword(this.prompt.input("새 암호? "));
    user.setCreatedDate(old.getCreatedDate());

    userDao.update(user);
    System.out.println("유저를 변경했습니다.");
  }
}
