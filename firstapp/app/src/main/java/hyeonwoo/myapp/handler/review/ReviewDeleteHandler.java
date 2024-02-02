package hyeonwoo.myapp.handler.review;

import hyeonwoo.menu.AbstractMenuHandler;
import hyeonwoo.myapp.dao.ReviewDao;
import hyeonwoo.util.Prompt;

public class ReviewDeleteHandler extends AbstractMenuHandler {

  private ReviewDao reviewDao;

  public ReviewDeleteHandler(ReviewDao reviewDao, Prompt prompt) {
    super(prompt);
    this.reviewDao = reviewDao;
  }

  @Override
  protected void action() {
    int no = this.prompt.inputInt("번호? ");
    if (reviewDao.delete(no) == 0) {
      System.out.println("리뷰 번호가 유효하지 않습니다.");
    } else {
      System.out.println("삭제했습니다!");
    }
  }
}
