package hyeonwoo.myapp.dao.mysql;

import hyeonwoo.myapp.dao.ReviewDao;
import hyeonwoo.myapp.dao.DaoException;
import hyeonwoo.myapp.vo.Review;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {

  Connection con;

  public ReviewDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public void add(Review review) {
    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate(String.format(
          "insert into reviews(game,title,rating,content,writer) values('%s','%s','%s','%s','%s')",
          review.getGame(), review.getTitle(), review.getRating(), review.getContent(), review.getWriter()));

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try {
      Statement stmt = con.createStatement();
      return stmt.executeUpdate(String.format("delete from reviews where review_no=%d", no));

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<Review> findAll() {
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from reviews");

      ArrayList<Review> list = new ArrayList<>();

      while (rs.next()) {
        Review review = new Review();
        review.setNo(rs.getInt("review_no"));
        review.setGame(rs.getString("game"));
        review.setTitle(rs.getString("title"));
        review.setRating(rs.getString("rating"));
        review.setContent(rs.getString("content"));
        review.setWriter(rs.getString("writer"));
        review.setCreatedDate(rs.getDate("created_date"));

        list.add(review);
      }
      return list;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public Review findBy(int no) {
    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select * from reviews where review_no = " + no);

      if (rs.next()) {
        Review review = new Review();
        review.setNo(rs.getInt("review_no"));
        review.setGame(rs.getString("game"));
        review.setTitle(rs.getString("title"));
        review.setRating(rs.getString("rating"));
        review.setContent(rs.getString("content"));
        review.setWriter(rs.getString("writer"));
        review.setCreatedDate(rs.getDate("created_date"));

        return review;
      }
      return null;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(Review review) {
    try {
      Statement stmt = con.createStatement();
      return stmt.executeUpdate(String.format(
          "update reviews set game='%s', title='%s', rating='%s', content='%s', writer='%s' where review_no=%d",
          review.getGame(), review.getTitle(), review.getRating(), review.getContent(), review.getWriter(), review.getNo()));

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
