package hyeonwoo.myapp.handler.review;

import hyeonwoo.menu.AbstractMenuHandler;
import hyeonwoo.myapp.dao.ReviewDao;
import hyeonwoo.myapp.vo.Review;
import hyeonwoo.util.Prompt;
import java.util.List;

public class ReviewListHandler extends AbstractMenuHandler {

  private ReviewDao reviewDao;

  public ReviewListHandler(ReviewDao reviewDao, Prompt prompt) {
    super(prompt);
    this.reviewDao = reviewDao;
  }

  @Override
  protected void action() {
    System.out.printf("%-4s\t%-10s\t%-20s\t%-20s\t%-1s\t%-1s\n", "번호", "게임", "제목",  "평점", "작성자", "작성일");

    List<Review> list = reviewDao.findAll();

    for (Review review : list) {
      System.out.printf("%-4d\t%-10s\t%-20s\t%-20s\t%s\t%6$tY-%6$tm-%6$td\n",
          review.getNo(),
          review.getGame(),
          review.getTitle(),
          review.getRating(),
          review.getWriter(),
          review.getCreatedDate());
    }
  }
}
