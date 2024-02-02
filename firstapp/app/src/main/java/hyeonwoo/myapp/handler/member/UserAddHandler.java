package hyeonwoo.myapp.handler.member;

import hyeonwoo.menu.AbstractMenuHandler;
import hyeonwoo.myapp.dao.UserDao;
import hyeonwoo.myapp.vo.User;
import hyeonwoo.util.Prompt;
import java.util.Date;

public class UserAddHandler extends AbstractMenuHandler {

  private UserDao userDao;

  public UserAddHandler(UserDao userDao, Prompt prompt) {
    super(prompt);
    this.userDao = userDao;
  }

  @Override
  protected void action() {
    User user = new User();
    user.setEmail(this.prompt.input("이메일? "));
    user.setName(this.prompt.input("닉네임? "));
    user.setPassword(this.prompt.input("암호? "));
    user.setCreatedDate(new Date());

    userDao.add(user);
  }
}
