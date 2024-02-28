package hyeonwoo.myapp.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Review implements Serializable {

  private static final long serialVersionUID = 100L;

  private int category;
  private int no;
  private int rating;
  private String content;
  private User writer;
  private Date createdDate;


  @Override
  public String toString() {
    return "Review{" +
        "category=" + category +
        ", no=" + no +
        ", rating=" + rating +
        ", content='" + content + '\'' +
        ", writer=" + writer +
        ", createdDate=" + createdDate +
        '}';
  }

  public int getCategory() {
    return category;
  }

  public void setCategory(int category) {
    this.category = category;
  }

  public User getWriter() {
    return writer;
  }

  public void setWriter(User writer) {
    this.writer = writer;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
}
