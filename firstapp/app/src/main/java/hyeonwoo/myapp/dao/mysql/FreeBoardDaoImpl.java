package hyeonwoo.myapp.dao.mysql;

import hyeonwoo.myapp.dao.DaoException;
import hyeonwoo.util.DBConnectionPool;
import hyeonwoo.myapp.dao.FreeBoardDao;
import hyeonwoo.myapp.vo.FreeBoard;
import hyeonwoo.myapp.vo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FreeBoardDaoImpl implements FreeBoardDao {

  DBConnectionPool connectionPool;

  public FreeBoardDaoImpl(DBConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  @Override
  public void add(FreeBoard freeboard) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "insert into freeboards(title,content,writer) values(?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)) {

      pstmt.setString(1, freeboard.getTitle());
      pstmt.setString(2, freeboard.getContent());
      pstmt.setInt(3, freeboard.getWriter().getNo());

      pstmt.executeUpdate();

      try (ResultSet keyRs = pstmt.getGeneratedKeys()) {
        keyRs.next();
        freeboard.setNo(keyRs.getInt(1));
      }


    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "delete from freeboards where freeboard_no=?")) {
      pstmt.setInt(1, no);
      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<FreeBoard> findAll() {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select\n"
                + "  f.freeboard_no,\n"
                + "  f.title,\n"
                + "  f.created_date,\n"
                + "  u.user_no,\n"
                + "  u.name\n"
                + "from\n"
                + "  freeboards f left outer join freeboard_files ff on f.freeboard_no=ff.freeboard_no\n"
                + "  inner join users u on f.writer=u.user_no\n"
                + "group by\n"
                + "  freeboard_no\n"
                + "order by\n"
                + "  freeboard_no desc")) {

      try (ResultSet rs = pstmt.executeQuery()) {

        ArrayList<FreeBoard> list = new ArrayList<>();

        while (rs.next()) {
          FreeBoard freeboard = new FreeBoard();
          freeboard.setNo(rs.getInt("freeboard_no"));
          freeboard.setTitle(rs.getString("title"));
          freeboard.setCreatedDate(rs.getDate("created_date"));

          User writer = new User();
          writer.setNo(rs.getInt("user_no"));
          writer.setName(rs.getString("name"));

          freeboard.setWriter(writer);

          list.add(freeboard);
        }
        return list;
      }

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public FreeBoard findBy(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select"
                + "  f.freeboard_no,\n"
                + "  f.title,\n"
                + "  f.content,\n"
                + "  f.created_date,\n"
                + "  u.user_no,\n"
                + "  u.name\n"
                + " from "
                + "  freeboards f inner join users u on f.writer=u.user_no\n"
                + " where freeboard_no=?")) {

      pstmt.setInt(1, no);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          FreeBoard freeboard = new FreeBoard();
          freeboard.setNo(rs.getInt("freeboard_no"));
          freeboard.setTitle(rs.getString("title"));
          freeboard.setContent(rs.getString("content"));
          freeboard.setCreatedDate(rs.getDate("created_date"));

          User writer = new User();
          writer.setNo(rs.getInt("user_no"));
          writer.setName(rs.getString("name"));

          freeboard.setWriter(writer);

          return freeboard;
        }
        return null;
      }
    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(FreeBoard freeboard) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "update freeboards set title=?, content=? where freeboard_no=?")) {

      pstmt.setString(1, freeboard.getTitle());
      pstmt.setString(2, freeboard.getContent());
      pstmt.setInt(3, freeboard.getNo());

      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
