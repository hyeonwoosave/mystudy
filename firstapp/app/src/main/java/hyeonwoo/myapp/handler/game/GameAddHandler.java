package hyeonwoo.myapp.handler.game;

import hyeonwoo.menu.AbstractMenuHandler;
import hyeonwoo.myapp.dao.GameDao;
import hyeonwoo.myapp.vo.Game;
import hyeonwoo.util.Prompt;

public class GameAddHandler extends AbstractMenuHandler {

  private GameDao gameDao;


  public GameAddHandler(GameDao gameDao, Prompt prompt) {
    super(prompt);
    this.gameDao = gameDao;
  }

  @Override
  protected void action() {
    try {
      Game game = new Game();
      game.setTitle(this.prompt.input("게임명? "));
      game.setProduce(this.prompt.input("제작사? "));
      game.setPrice(this.prompt.input("가격? "));
      game.setGenre(this.prompt.input("장르? "));
      game.setReleaseDate(this.prompt.inputDate("출시일?(예: 2023-12-25) "));

      gameDao.add(game);

    } catch (Exception e) {
      System.out.println("게임 등록 중 오류 발생!");
      System.out.println("다시 시도하시기 바랍니다.");
    }
  }
}
