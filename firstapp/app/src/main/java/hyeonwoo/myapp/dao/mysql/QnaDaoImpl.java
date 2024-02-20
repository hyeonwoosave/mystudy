package hyeonwoo.myapp.dao.mysql;

import hyeonwoo.myapp.dao.DaoException;
import hyeonwoo.util.DBConnectionPool;
import hyeonwoo.myapp.dao.QnaDao;
import hyeonwoo.myapp.vo.Qna;
import hyeonwoo.myapp.vo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QnaDaoImpl implements QnaDao {

  DBConnectionPool connectionPool;

  public QnaDaoImpl(DBConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  @Override
  public void add(Qna qna) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "insert into qnas(title,content,writer) values(?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)) {

      pstmt.setString(1, qna.getTitle());
      pstmt.setString(2, qna.getContent());
      pstmt.setInt(3, qna.getWriter().getNo());

      pstmt.executeUpdate();
      
      try (ResultSet keyRs = pstmt.getGeneratedKeys()) {
        keyRs.next();
        qna.setNo(keyRs.getInt(1));
      }


    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "delete from qnas where qna_no=?")) {
      pstmt.setInt(1, no);
      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<Qna> findAll() {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select\n"
                + "  q.qna_no,\n"
                + "  q.title,\n"
                + "  q.created_date,\n"
                + "  u.user_no,\n"
                + "  u.name\n"
                + "from\n"
                + "  inner join users u on q.writer=u.user_no\n"
                + "group by\n"
                + "  qna_no\n"
                + "order by\n"
                + "  qna_no desc")) {

      try (ResultSet rs = pstmt.executeQuery()) {

        ArrayList<Qna> list = new ArrayList<>();

        while (rs.next()) {
          Qna qna = new Qna();
          qna.setNo(rs.getInt("qna_no"));
          qna.setTitle(rs.getString("title"));
          qna.setCreatedDate(rs.getDate("created_date"));

          User writer = new User();
          writer.setNo(rs.getInt("user_no"));
          writer.setName(rs.getString("name"));

          qna.setWriter(writer);

          list.add(qna);
        }
        return list;
      }

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public Qna findBy(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select"
                + "  q.qna_no,\n"
                + "  q.title,\n"
                + "  q.content,"
                + "  q.created_date,\n"
                + "  u.user_no,\n"
                + "  u.name\n"
                + " from "
                + "  qnas q inner join users u on q.writer=u.user_no\n"
                + " where qna_no=?")) {

      pstmt.setInt(1, no);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          Qna qna = new Qna();
          qna.setNo(rs.getInt("qna_no"));
          qna.setTitle(rs.getString("title"));
          qna.setContent(rs.getString("content"));
          qna.setCreatedDate(rs.getDate("created_date"));

          User writer = new User();
          writer.setNo(rs.getInt("user_no"));
          writer.setName(rs.getString("name"));

          qna.setWriter(writer);

          return qna;
        }
        return null;
      }
    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(Qna qna) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "update qnas set title=?, content=? where qna_no=?")) {

      pstmt.setString(1, qna.getTitle());
      pstmt.setString(2, qna.getContent());
      pstmt.setInt(3, qna.getNo());

      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
