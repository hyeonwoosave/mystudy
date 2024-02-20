package hyeonwoo.myapp.dao.mysql;

import hyeonwoo.myapp.dao.DaoException;
import hyeonwoo.util.DBConnectionPool;
import hyeonwoo.myapp.dao.GameDao;
import hyeonwoo.myapp.vo.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GameDaoImpl implements GameDao {

  DBConnectionPool connectionPool;

  public GameDaoImpl(DBConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  @Override
  public void add(Game game) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "insert into games(title,rating,price,produce,genre,info,release_date) "
                + "values(?,?,?,?,?,?,?)")) {

      pstmt.setString(1, game.getTitle());
      pstmt.setInt(2, game.getRating());
      pstmt.setInt(3, game.getPrice());
      pstmt.setString(4, game.getProduce());
      pstmt.setString(5, game.getGenre());
      pstmt.setString(6, game.getInfo());
      pstmt.setDate(7, game.getRelease_date());

      pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "delete from games where game_no=?")) {
      pstmt.setInt(1, no);

      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<Game> findAll() {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select game_no, title, rating, price, produce"
                + " from games order by game_no desc");
        ResultSet rs = pstmt.executeQuery()) {

      ArrayList<Game> list = new ArrayList<>();

      while (rs.next()) {
        Game game = new Game();
        game.setNo(rs.getInt("game_no"));
        game.setTitle(rs.getString("title"));
        game.setRating(rs.getInt("rating"));
        game.setPrice(rs.getInt("price"));
        game.setProduce(rs.getString("produce"));

        list.add(game);
      }
      return list;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public Game findBy(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select * from games where game_no=?")) {

      pstmt.setInt(1, no);

      try (ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {
          Game game = new Game();
          game.setNo(rs.getInt("game_no"));
          game.setTitle(rs.getString("title"));
          game.setRating(rs.getInt("rating"));
          game.setPrice(rs.getInt("price"));
          game.setProduce(rs.getString("produce"));
          game.setGenre(rs.getString("genre"));
          game.setInfo(rs.getString("info"));
          game.setRelease_date(rs.getDate("release_date"));
          return game;
        }
        return null;
      }

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(Game game) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "update games set title=?, rating=?, price=?, produce=?, genre=?, info=?, release_date=? "
                + "where game_no=?")) {

      pstmt.setString(1, game.getTitle());
      pstmt.setInt(2, game.getRating());
      pstmt.setInt(3, game.getPrice());
      pstmt.setString(4, game.getProduce());
      pstmt.setString(5, game.getGenre());
      pstmt.setString(6, game.getInfo());
      pstmt.setDate(7, game.getRelease_date());
      pstmt.setInt(8, game.getNo());

      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
