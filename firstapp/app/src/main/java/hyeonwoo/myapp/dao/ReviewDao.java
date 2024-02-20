package hyeonwoo.myapp.dao;

import hyeonwoo.myapp.vo.Review;
import java.util.List;

public interface ReviewDao {

  void add(Review freeBoard);

  int delete(int no);

  List<Review> findAll(int category);

  Review findBy(int no);

  int update(Review freeBoard);

}
