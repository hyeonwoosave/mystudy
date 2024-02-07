package hyeonwoo.myapp.dao.mysql;

import hyeonwoo.myapp.dao.DaoException;
import hyeonwoo.myapp.dao.UserDao;
import hyeonwoo.myapp.vo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

  Connection con;

  public UserDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public void add(User user) {
    try(PreparedStatement pstmt = con.prepareStatement(
        "insert into users(email,name,password) values(?,?,sha2(?,256))")) {

      pstmt.setString(1, user.getEmail());
      pstmt.setString(2, user.getName());
      pstmt.setString(3, user.getPassword());

      pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "delete from users where user_no=?")){
      pstmt.setInt(1, no);

      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<User> findAll() {
    try (PreparedStatement pstmt = con.prepareStatement(
        "select user_no, email, name, created_date from users order by user_no desc");
        ResultSet rs = pstmt.executeQuery()) {

      ArrayList<User> list = new ArrayList<>();

      while (rs.next()) {
        User user = new User();
        user.setNo(rs.getInt("user_no"));
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("name"));
        user.setCreatedDate(rs.getDate("created_date"));

        list.add(user);
      }
      return list;

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public User findBy(int no) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "select * from users where user_no=?")) {

      pstmt.setInt(1, no);

      try (ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {
          User user = new User();
          user.setNo(rs.getInt("user_no"));
          user.setEmail(rs.getString("email"));
          user.setName(rs.getString("name"));
          user.setCreatedDate(rs.getDate("created_date"));

          return user;
        }
        return null;
      }
    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }

  @Override
  public int update(User user) {
    try(PreparedStatement pstmt = con.prepareStatement(
        "update users set email=?, name=?, password=sha2(?,256) where user_no=?")) {

      pstmt.setString(1, user.getEmail());
      pstmt.setString(2, user.getName());
      pstmt.setString(3, user.getPassword());
      pstmt.setInt(4, user.getNo());

      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 변경 오류", e);
    }
  }
}
