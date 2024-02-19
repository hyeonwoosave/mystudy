package hyeonwoo.myapp.dao;

import java.util.List;

public interface QnaDao {

  void add(bitcamp.myapp.vo.FreeBoard freeBoard);

  int delete(int no);

  List<bitcamp.myapp.vo.FreeBoard> findAll(int category);

  bitcamp.myapp.vo.FreeBoard findBy(int no);

  int update(bitcamp.myapp.vo.FreeBoard freeBoard);

}
