package hyeonwoo.myapp.dao.mysql;

import hyeonwoo.myapp.dao.DaoException;
import hyeonwoo.util.DBConnectionPool;
import hyeonwoo.myapp.dao.ReviewDao;
import hyeonwoo.myapp.vo.Review;
import hyeonwoo.myapp.vo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {

  DBConnectionPool connectionPool;

  public ReviewDaoImpl(DBConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  @Override
  public void add(Review review) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "insert into reviews(title,rating,content,writer,category) values(?,?,?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)) {

      pstmt.setString(1, review.getTitle());
      pstmt.setInt(2, review.getRating());
      pstmt.setString(3, review.getContent());
      pstmt.setInt(4, review.getWriter().getNo());
      pstmt.setInt(5, review.getCategory());

      pstmt.executeUpdate();

      try (ResultSet keyRs = pstmt.getGeneratedKeys()) {
        keyRs.next();
        review.setNo(keyRs.getInt(1));
      }


    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "delete from reviews where review_no=?")) {
      pstmt.setInt(1, no);
      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<Review> findAll(int category) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select\n"
                + "  r.review_no,\n"
                + "  r.title,\n"
                + "  r.rating,\n"
                + "  r.created_date,\n"
                + "  u.user_no,\n"
                + "  u.name,\n"
                + "  r.content\n"
                + "from\n"
                + "  reviews r inner join users u on r.writer=u.user_no\n"
                + "where\n"
                + "  r.category=?\n"
                + "group by\n"
                + "  review_no\n"
                + "order by\n"
                + "  review_no desc")) {

      pstmt.setInt(1, category);

      try (ResultSet rs = pstmt.executeQuery()) {

        ArrayList<Review> list = new ArrayList<>();

        while (rs.next()) {
          Review review = new Review();
          review.setNo(rs.getInt("review_no"));
          review.setTitle(rs.getString("title"));
          review.setRating(rs.getInt("rating"));
          review.setContent(rs.getString("content"));
          review.setCreatedDate(rs.getDate("created_date"));

          User writer = new User();
          writer.setNo(rs.getInt("user_no"));
          writer.setName(rs.getString("name"));

          review.setWriter(writer);

          list.add(review);
        }
        return list;
      }

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public Review findBy(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select"
                + "  r.review_no,\n"
                + "  r.title,\n"
                + "  r.rating,\n"
                + "  r.content,"
                + "  r.created_date,\n"
                + "  u.user_no,\n"
                + "  u.name\n"
                + " from "
                + "  reviews r inner join users u on r.writer=u.user_no\n"
                + " where review_no=?")) {

      pstmt.setInt(1, no);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          Review review = new Review();
          review.setNo(rs.getInt("review_no"));
          review.setTitle(rs.getString("title"));
          review.setRating(rs.getInt("rating"));
          review.setContent(rs.getString("content"));
          review.setCreatedDate(rs.getDate("created_date"));

          User writer = new User();
          writer.setNo(rs.getInt("user_no"));
          writer.setName(rs.getString("name"));

          review.setWriter(writer);

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
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "update reviews set title=?, rating=?, content=? where review_no=?")) {

      pstmt.setString(1, review.getTitle());
      pstmt.setInt(2, review.getRating());
      pstmt.setString(3, review.getContent());
      pstmt.setInt(4, review.getNo());

      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
