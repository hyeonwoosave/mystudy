package hyeonwoo.myapp.handler.review;

import hyeonwoo.menu.AbstractMenuHandler;
import hyeonwoo.myapp.dao.ReviewDao;
import hyeonwoo.myapp.vo.Review;
import hyeonwoo.util.Prompt;

public class ReviewModifyHandler extends AbstractMenuHandler {

  private ReviewDao reviewDao;

  public ReviewModifyHandler(ReviewDao reviewDao, Prompt prompt) {
    super(prompt);
    this.reviewDao = reviewDao;
  }

  @Override
  protected void action() {
    int no = this.prompt.inputInt("번호? ");

    Review oldReview = reviewDao.findBy(no);
    if (oldReview == null) {
      System.out.println("리뷰 번호가 유효하지 않습니다.");
      return;
    }

    Review review = new Review();
    review.setNo(oldReview.getNo()); // 기존 게시글의 번호를 그대로 설정한다.
    review.setGame(this.prompt.input("게임(%s)? ", oldReview.getGame()));
    review.setTitle(this.prompt.input("제목(%s)? ", oldReview.getTitle()));
    review.setRating(this.prompt.input("평점(%s)? ", oldReview.getRating()));
    review.setContent(this.prompt.input("내용(%s)? ", oldReview.getContent()));
    review.setWriter(this.prompt.input("작성자(%s)? ", oldReview.getWriter()));
    review.setCreatedDate(oldReview.getCreatedDate());

    reviewDao.update(review);
    System.out.println("리뷰를 변경했습니다.");
  }
}
