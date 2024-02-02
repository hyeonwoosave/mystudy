package hyeonwoo.myapp.handler.game;

import hyeonwoo.menu.AbstractMenuHandler;
import hyeonwoo.myapp.dao.GameDao;
import hyeonwoo.util.Prompt;

public class GameDeleteHandler extends AbstractMenuHandler {

  private GameDao gameDao;

  public GameDeleteHandler(GameDao gameDao, Prompt prompt) {
    super(prompt);
    this.gameDao = gameDao;
  }

  @Override
  protected void action() {
    try {
      int no = this.prompt.inputInt("번호? ");
      if (gameDao.delete(no) == 0) {
        System.out.println("게임 번호가 유효하지 않습니다!");
      } else {
        System.out.println("게임을 삭제했습니다.");
      }

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("삭제 오류!");
    }
  }
}
