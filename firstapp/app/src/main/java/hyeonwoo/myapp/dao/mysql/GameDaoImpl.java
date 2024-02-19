package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.DaoException;
import bitcamp.util.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GameDaoImpl implements bitcamp.myapp.dao.GameDao {

  DBConnectionPool connectionPool;

  public GameDaoImpl(DBConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  @Override
  public void add(bitcamp.myapp.vo.Game game) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "insert into assignments(title,content,deadline) values(?,?,?)")) {

      pstmt.setString(1, game.getTitle());
      pstmt.setString(2, game.getContent());
      pstmt.setDate(3, game.getDeadline());

      pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "delete from assignments where assignment_no=?")) {
      pstmt.setInt(1, no);

      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<bitcamp.myapp.vo.Game> findAll() {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select assignment_no, title, deadline from assignments order by assignment_no desc");
        ResultSet rs = pstmt.executeQuery()) {

      ArrayList<bitcamp.myapp.vo.Game> list = new ArrayList<>();

      while (rs.next()) {
        bitcamp.myapp.vo.Game game = new bitcamp.myapp.vo.Game();
        game.setNo(rs.getInt("assignment_no"));
        game.setTitle(rs.getString("title"));
        game.setDeadline(rs.getDate("deadline"));

        list.add(game);
      }
      return list;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public bitcamp.myapp.vo.Game findBy(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select * from assignments where assignment_no=?")) {

      pstmt.setInt(1, no);

      try (ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {
          bitcamp.myapp.vo.Game game = new bitcamp.myapp.vo.Game();
          game.setNo(rs.getInt("assignment_no"));
          game.setTitle(rs.getString("title"));
          game.setContent(rs.getString("content"));
          game.setDeadline(rs.getDate("deadline"));
          return game;
        }
        return null;
      }

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(bitcamp.myapp.vo.Game game) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "update assignments set title=?, content=?, deadline=? where assignment_no=?")) {

      pstmt.setString(1, game.getTitle());
      pstmt.setString(2, game.getContent());
      pstmt.setDate(3, game.getDeadline());
      pstmt.setInt(4, game.getNo());

      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
