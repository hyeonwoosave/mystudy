package hyeonwoo.myapp.handler.game;

import hyeonwoo.menu.AbstractMenuHandler;
import hyeonwoo.myapp.dao.GameDao;
import hyeonwoo.myapp.vo.Game;
import hyeonwoo.util.Prompt;

public class GameModifyHandler extends AbstractMenuHandler {

  private GameDao gameDao;

  public GameModifyHandler(GameDao gameDao, Prompt prompt) {
    super(prompt);
    this.gameDao = gameDao;
  }

  @Override
  protected void action() {
    try {
      int no = this.prompt.inputInt("번호? ");

      Game old = gameDao.findBy(no);
      if (old == null) {
        System.out.println("게임 번호가 유효하지 않습니다!");
        return;
      }

      Game game = new Game();
      game.setNo(old.getNo());
      game.setTitle(this.prompt.input("게임명(%s)? ", old.getTitle()));
      game.setProduce(this.prompt.input("제작사(%s)? ", old.getProduce()));
      game.setPrice(this.prompt.input("가격(%s)? ", old.getPrice()));
      game.setGenre(this.prompt.input("장르(%s)? ", old.getGenre()));
      game.setReleaseDate(this.prompt.inputDate("출시일(%s)? ", old.getReleaseDate()));

      gameDao.update(game);
      System.out.println("게임을 변경했습니다.");

    } catch (NumberFormatException e) {
      System.out.println("숫자를 입력하세요!");

    } catch (IllegalArgumentException e) {
      System.out.println("게임 변경 오류!");
      System.out.println("다시 시도 하세요.");

    } catch (Exception e) {
      System.out.println("실행 오류!");
      e.printStackTrace();
    }

  }
}
