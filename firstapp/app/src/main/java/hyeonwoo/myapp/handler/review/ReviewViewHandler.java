package hyeonwoo.myapp.handler.review;

import hyeonwoo.menu.AbstractMenuHandler;
import hyeonwoo.myapp.dao.ReviewDao;
import hyeonwoo.myapp.vo.Review;
import hyeonwoo.util.Prompt;

public class ReviewViewHandler extends AbstractMenuHandler {

  private ReviewDao reviewDao;

  public ReviewViewHandler(ReviewDao reviewDao, Prompt prompt) {
    super(prompt);
    this.reviewDao = reviewDao;
  }

  @Override
  protected void action() {
    int no = this.prompt.inputInt("번호? ");

    Review review = reviewDao.findBy(no);
    if (review == null) {
      System.out.println("리뷰 번호가 유효하지 않습니다.");
      return;
    }

    System.out.printf("번호: %d\n", review.getNo());
    System.out.printf("게임: %s\n", review.getGame());
    System.out.printf("제목: %s\n", review.getTitle());
    System.out.printf("평점: %s\n", review.getRating());
    System.out.printf("내용: %s\n", review.getContent());
    System.out.printf("작성자: %s\n", review.getWriter());
    System.out.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", review.getCreatedDate());
  }
}
