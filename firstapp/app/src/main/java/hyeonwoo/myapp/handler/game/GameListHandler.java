package hyeonwoo.myapp.handler.game;

import hyeonwoo.menu.AbstractMenuHandler;
import hyeonwoo.myapp.dao.GameDao;
import hyeonwoo.myapp.vo.Game;
import hyeonwoo.util.Prompt;
import java.util.List;

public class GameListHandler extends AbstractMenuHandler {

  private GameDao gameDao;

  public GameListHandler(GameDao gameDao, Prompt prompt) {
    super(prompt);
    this.gameDao = gameDao;
  }

  @Override
  protected void action() {
    System.out.printf("%-4s\t%-10s\t%-10s\t%-10s\t%-10s\t%s\n", "번호", "게임명", "제작사", "가격", "장르", "출시일");

    List<Game> list = gameDao.findAll();

    for (Game game : list) {
      System.out.printf("%-4s\t%-10s\t%-10s\t%-10s\t%-10s\t%s\n",
          game.getNo(),
          game.getTitle(),
          game.getProduce(),
          game.getPrice(),
          game.getGenre(),
          game.getReleaseDate());
    }
  }
}
