package hyeonwoo.myapp.dao;

import hyeonwoo.myapp.vo.FreeBoard;
import java.util.List;

public interface FreeBoardDao {

  void add(FreeBoard freeboard);

  int delete(int no);

  List<FreeBoard> findAll();

  FreeBoard findBy(int no);

  int update(FreeBoard freeboard);

}
