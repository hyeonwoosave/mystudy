package hyeonwoo.myapp.dao;

import hyeonwoo.myapp.vo.Game;
import java.util.List;

public interface GameDao {

  void add(Game game);

  int delete(int no);

  List<Game> findAll();

  Game findBy(int no);

  int update(Game game);

}
