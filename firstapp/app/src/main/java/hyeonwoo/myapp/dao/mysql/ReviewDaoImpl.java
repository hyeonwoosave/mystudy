package hyeonwoo.myapp.dao.mysql;

import hyeonwoo.myapp.dao.ReviewDao;
import hyeonwoo.myapp.dao.DaoException;
import hyeonwoo.myapp.vo.Review;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    try(PreparedStatement pstmt = con.prepareStatement(
        "insert into reviews(game,title,rating,content,writer) values(?,?,?,?,?)")) {

      pstmt.setString(1, review.getGame());
      pstmt.setString(2, review.getTitle());
      pstmt.setString(3, review.getRating());
      pstmt.setString(4, review.getContent());
      pstmt.setString(5, review.getWriter());

      pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "delete from reviews where review_no=?")) {
      pstmt.setInt(1, no);

      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<Review> findAll() {
    try (PreparedStatement pstmt = con.prepareStatement(
        "select review_no, game, title, rating, content, writer, created_date from reviews order by review_no desc");
        ResultSet rs = pstmt.executeQuery()) {

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
    try (PreparedStatement pstmt = con.prepareStatement(
        "select * from reviews where review_no=?")) {

      pstmt.setInt(1, no);

      try (ResultSet rs = pstmt.executeQuery()) {

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
      }
    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(Review review) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "update reviews set game=?, title=?, rating=?, content=?, writer=? where review_no=?")) {

      pstmt.setString(1, review.getGame());
      pstmt.setString(2, review.getTitle());
      pstmt.setString(3, review.getRating());
      pstmt.setString(4, review.getContent());
      pstmt.setString(5, review.getWriter());
      pstmt.setInt(6, review.getNo());

      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
