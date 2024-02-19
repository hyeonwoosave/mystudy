package hyeonwoo.myapp.dao.mysql;

import bitcamp.myapp.dao.DaoException;
import bitcamp.util.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QnaDaoImpl implements bitcamp.myapp.dao.ReviewDao {

  DBConnectionPool connectionPool;

  public QnaDaoImpl(DBConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  @Override
  public void add(bitcamp.myapp.vo.FreeBoard freeBoard) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "insert into boards(title,content,writer,category) values(?,?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS)) {

      pstmt.setString(1, freeBoard.getTitle());
      pstmt.setString(2, freeBoard.getContent());
      pstmt.setInt(3, freeBoard.getWriter().getNo());
      pstmt.setInt(4, freeBoard.getCategory());

      pstmt.executeUpdate();

      // 자동 생성된 PK 값을 가져와서 Board 객체에 저장한다.
      try (ResultSet keyRs = pstmt.getGeneratedKeys()) {
        keyRs.next();
        freeBoard.setNo(keyRs.getInt(1));
      }


    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "delete from boards where board_no=?")) {
      pstmt.setInt(1, no);
      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<bitcamp.myapp.vo.FreeBoard> findAll(int category) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select\n"
                + "  b.board_no,\n"
                + "  b.title,\n"
                + "  b.created_date,\n"
                + "  count(file_no) file_count,\n"
                + "  m.member_no,\n"
                + "  m.name\n"
                + "from\n"
                + "  boards b left outer join board_files bf on b.board_no=bf.board_no\n"
                + "  inner join members m on b.writer=m.member_no\n"
                + "where\n"
                + "  b.category=?\n"
                + "group by\n"
                + "  board_no\n"
                + "order by\n"
                + "  board_no desc")) {

      pstmt.setInt(1, category);

      try (ResultSet rs = pstmt.executeQuery()) {

        ArrayList<bitcamp.myapp.vo.FreeBoard> list = new ArrayList<>();

        while (rs.next()) {
          bitcamp.myapp.vo.FreeBoard freeBoard = new bitcamp.myapp.vo.FreeBoard();
          freeBoard.setNo(rs.getInt("board_no"));
          freeBoard.setTitle(rs.getString("title"));
          freeBoard.setCreatedDate(rs.getDate("created_date"));
          freeBoard.setFileCount(rs.getInt("file_count"));

          bitcamp.myapp.vo.User writer = new bitcamp.myapp.vo.User();
          writer.setNo(rs.getInt("member_no"));
          writer.setName(rs.getString("name"));

          freeBoard.setWriter(writer);

          list.add(freeBoard);
        }
        return list;
      }

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public bitcamp.myapp.vo.FreeBoard findBy(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select"
                + "  b.board_no,\n"
                + "  b.title,\n"
                + "  b.content,"
                + "  b.created_date,\n"
                + "  m.member_no,\n"
                + "  m.name\n"
                + " from "
                + "  boards b inner join members m on b.writer=m.member_no\n"
                + " where board_no=?")) {

      pstmt.setInt(1, no);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          bitcamp.myapp.vo.FreeBoard freeBoard = new bitcamp.myapp.vo.FreeBoard();
          freeBoard.setNo(rs.getInt("board_no"));
          freeBoard.setTitle(rs.getString("title"));
          freeBoard.setContent(rs.getString("content"));
          freeBoard.setCreatedDate(rs.getDate("created_date"));

          bitcamp.myapp.vo.User writer = new bitcamp.myapp.vo.User();
          writer.setNo(rs.getInt("member_no"));
          writer.setName(rs.getString("name"));

          freeBoard.setWriter(writer);

          return freeBoard;
        }
        return null;
      }
    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(bitcamp.myapp.vo.FreeBoard freeBoard) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "update boards set title=?, content=? where board_no=?")) {

      pstmt.setString(1, freeBoard.getTitle());
      pstmt.setString(2, freeBoard.getContent());
      pstmt.setInt(3, freeBoard.getNo());

      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
