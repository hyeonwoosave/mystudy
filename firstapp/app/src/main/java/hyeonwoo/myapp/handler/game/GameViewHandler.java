package hyeonwoo.myapp.handler.game;

import hyeonwoo.menu.AbstractMenuHandler;
import hyeonwoo.myapp.dao.GameDao;
import hyeonwoo.myapp.vo.Game;
import hyeonwoo.util.Prompt;

public class GameViewHandler extends AbstractMenuHandler {

  private GameDao gameDao;

  public GameViewHandler(GameDao gameDao, Prompt prompt) {
    super(prompt);
    this.gameDao = gameDao;
  }

  @Override
  protected void action() {
    try {
      int no = this.prompt.inputInt("번호? ");
      Game game = gameDao.findBy(no);
      if (game == null) {
        System.out.println("게임 번호가 유효하지 않습니다!");
        return;
      }

      System.out.printf("번호: %s\n", game.getNo());
      System.out.printf("게임명: %s\n", game.getTitle());
      System.out.printf("제작사: %s\n", game.getProduce());
      System.out.printf("가격: %s\n", game.getPrice());
      System.out.printf("장르: %s\n", game.getGenre());
      System.out.printf("출시일: %s\n", game.getReleaseDate());

    } catch (Exception e) {
      System.out.println("조회 오류!");
    }
  }

}
