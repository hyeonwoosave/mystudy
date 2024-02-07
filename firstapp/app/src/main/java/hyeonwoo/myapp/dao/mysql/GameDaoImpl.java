package hyeonwoo.myapp.dao.mysql;

import hyeonwoo.myapp.dao.GameDao;
import hyeonwoo.myapp.dao.DaoException;
import hyeonwoo.myapp.vo.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GameDaoImpl implements GameDao {

  Connection con;

  public GameDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public void add(Game game) {
    try(PreparedStatement pstmt = con.prepareStatement(
        "insert into games(title,produce,price,genre,release_date) values(?,?,?,?,?)")) {

      pstmt.setString(1, game.getTitle());
      pstmt.setString(2, game.getProduce());
      pstmt.setString(3, game.getPrice());
      pstmt.setString(4, game.getGenre());
      pstmt.setDate(5, game.getReleaseDate());

      pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "delete from games where game_no=?")) {
      pstmt.setInt(1, no);

      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<Game> findAll() {
    try (PreparedStatement pstmt = con.prepareStatement(
        "select game_no, title, produce, price, genre, release_date from games order by game_no desc");
        ResultSet rs = pstmt.executeQuery()) {

      ArrayList<Game> list = new ArrayList<>();

      while (rs.next()) {
        Game game = new Game();
        game.setNo(rs.getInt("game_no"));
        game.setTitle(rs.getString("title"));
        game.setProduce(rs.getString("produce"));
        game.setPrice(rs.getString("price"));
        game.setGenre(rs.getString("genre"));
        game.setReleaseDate(rs.getDate("release_date"));

        list.add(game);
      }
      return list;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public Game findBy(int no) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "select * from games where game_no=?")) {

      pstmt.setInt(1, no);

      try (ResultSet rs = pstmt.executeQuery()) {

      if (rs.next()) {
        Game game = new Game();
        game.setNo(rs.getInt("game_no"));
        game.setTitle(rs.getString("title"));
        game.setProduce(rs.getString("produce"));
        game.setPrice(rs.getString("price"));
        game.setGenre(rs.getString("genre"));
        game.setReleaseDate(rs.getDate("release_date"));

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
    try(PreparedStatement pstmt = con.prepareStatement(
        "update games set title=?, produce=?, price=?, genre=?, release_date=? where game_no=?")) {

      pstmt.setString(1, game.getTitle());
      pstmt.setString(2, game.getProduce());
      pstmt.setString(3, game.getPrice());
      pstmt.setString(4, game.getGenre());
      pstmt.setDate(5, game.getReleaseDate());
      pstmt.setInt(6, game.getNo());

      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
