package hyeonwoo.myapp.dao;

import hyeonwoo.myapp.vo.Qna;
import java.util.List;

public interface QnaDao {

  void add(Qna freeBoard);

  int delete(int no);

  List<Qna> findAll();

  Qna findBy(int no);

  int update(Qna freeBoard);

}
