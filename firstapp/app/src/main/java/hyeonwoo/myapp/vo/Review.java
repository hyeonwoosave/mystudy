package hyeonwoo.myapp.vo;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {

  private static final long serialVersionUID = 100L;

  private int no;
  private String game;
  private String title;
  private String rating;
  private String content;
  private String writer;
  private Date createdDate;
  
  @Override
  public String toString() {
    return "Review{" +
        "no=" + no +
        ", game='" + game + '\'' +
        ", title='" + title + '\'' +
        ", rating='" + rating + '\'' +
        ", content='" + content + '\'' +
        ", writer='" + writer + '\'' +
        ", createdDate=" + createdDate +
        '}';
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getGame() {
    return game;
  }

  public void setGame(String game) {
    this.game = game;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getWriter() {
    return writer;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
}
