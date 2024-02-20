package hyeonwoo.myapp.dao;

import hyeonwoo.myapp.vo.User;
import java.util.List;

public interface UserDao {

  public void add(User user);

  public int delete(int no);

  public List<User> findAll();

  public User findBy(int no);

  public int update(User user);

  public User findByEmailAndPassword(String email, String password);
}
