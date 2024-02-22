package hyeonwoo.myapp.dao.mysql;

import hyeonwoo.myapp.dao.DaoException;
import hyeonwoo.util.DBConnectionPool;
import hyeonwoo.myapp.dao.UserDao;
import hyeonwoo.myapp.vo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

  DBConnectionPool connectionPool;

  public UserDaoImpl(DBConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  @Override
  public void add(User user) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "insert into users(name,tel,email,password) values(?,?,?,sha2(?,256))")) {
      pstmt.setString(1, user.getName());
      pstmt.setString(2, user.getTel());
      pstmt.setString(3, user.getEmail());
      pstmt.setString(4, user.getPassword());
      pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 입력 오류", e);
    }
  }

  @Override
  public int delete(int no) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "delete from users where user_no=?")) {
      pstmt.setInt(1, no);
      return pstmt.executeUpdate();

    } catch (Exception e) {
      throw new DaoException("데이터 삭제 오류", e);
    }
  }

  @Override
  public List<User> findAll() {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select user_no, name, tel, email, created_date from users");
        ResultSet rs = pstmt.executeQuery();) {

      ArrayList<User> list = new ArrayList<>();

      while (rs.next()) {
        User user = new User();
        user.setNo(rs.getInt("user_no"));
        user.setName(rs.getString("name"));
        user.setTel(rs.getString("tel"));
        user.setEmail(rs.getString("email"));
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
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select user_no, name, tel, email, created_date from users where user_no=?")) {
      pstmt.setInt(1, no);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          User user = new User();
          user.setNo(rs.getInt("user_no"));
          user.setName(rs.getString("name"));
          user.setTel(rs.getString("tel"));
          user.setEmail(rs.getString("email"));
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
    String sql = null;
    if (user.getPassword().length() == 0) {
      sql = "update users set name=?, tel=?, email=? where user_no=?";

      try (Connection con = connectionPool.getConnection();
          PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getTel());
        pstmt.setString(3, user.getEmail());
        pstmt.setInt(4, user.getNo());

        return pstmt.executeUpdate();

      } catch (Exception e) {
        throw new DaoException("데이터 변경 오류", e);
      }

    } else {
      sql = "update users set name=?, tel=?, email=?, password=sha2(?,256) where user_no=?";

      try (Connection con = connectionPool.getConnection();
          PreparedStatement pstmt = con.prepareStatement(sql)) {
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getTel());
        pstmt.setString(3, user.getEmail());
        pstmt.setString(4, user.getPassword());
        pstmt.setInt(5, user.getNo());

        return pstmt.executeUpdate();

      } catch (Exception e) {
        throw new DaoException("데이터 변경 오류", e);
      }
    }
  }

  @Override
  public User findByEmailAndPassword(String email, String password) {
    try (Connection con = connectionPool.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
            "select user_no, name, tel, email, created_date from users where email=? and password=sha2(?,256)")) {
      pstmt.setString(1, email);
      pstmt.setString(2, password);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          User user = new User();
          user.setNo(rs.getInt("user_no"));
          user.setName(rs.getString("name"));
          user.setTel(rs.getString("tel"));
          user.setEmail(rs.getString("email"));
          user.setCreatedDate(rs.getDate("created_date"));
          return user;
        }
        return null;
      }

    } catch (Exception e) {
      throw new DaoException("데이터 가져오기 오류", e);
    }
  }
}
