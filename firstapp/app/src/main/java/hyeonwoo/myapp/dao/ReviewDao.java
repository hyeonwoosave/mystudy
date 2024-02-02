package hyeonwoo.myapp.dao;

import hyeonwoo.myapp.vo.Review;
import java.util.List;

public interface ReviewDao {

  void add(Review review);

  int delete(int no);

  List<Review> findAll();

  Review findBy(int no);

  int update(Review review);

}
