package hyeonwoo.myapp.handler.review;

import hyeonwoo.menu.AbstractMenuHandler;
import hyeonwoo.myapp.dao.ReviewDao;
import hyeonwoo.myapp.vo.Review;
import hyeonwoo.util.Prompt;
import java.util.Date;

public class ReviewAddHandler extends AbstractMenuHandler {

  private ReviewDao reviewDao;

  public ReviewAddHandler(ReviewDao reviewDao, Prompt prompt) {
    super(prompt);
    this.reviewDao = reviewDao;
  }

  @Override
  protected void action() {
    Review review = new Review();
    review.setGame(this.prompt.input("게임? "));
    review.setTitle(this.prompt.input("제목? "));
    review.setRating(this.prompt.input("평점?(1 ~ 10) "));
    review.setContent(this.prompt.input("내용? "));
    review.setWriter(this.prompt.input("작성자? "));
    review.setCreatedDate(new Date());

    reviewDao.add(review);
  }
}
