package hyeonwoo.myapp.dao;

import java.util.List;

public interface GameDao {

  void add(bitcamp.myapp.vo.Game game);

  int delete(int no);

  List<bitcamp.myapp.vo.Game> findAll();

  bitcamp.myapp.vo.Game findBy(int no);

  int update(bitcamp.myapp.vo.Game game);

}
