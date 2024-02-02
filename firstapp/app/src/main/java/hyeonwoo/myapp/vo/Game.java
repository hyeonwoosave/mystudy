package hyeonwoo.myapp.vo;

import java.io.Serializable;
import java.sql.Date;

public class Game implements Serializable {

  private static final long serialVersionUID = 100L;

  private int no;
  private String title;
  private String produce;
  private String price;
  private String genre;
  private Date releaseDate;

  @Override
  public String toString() {
    return "Game{" +
        "no=" + no +
        ", title='" + title + '\'' +
        ", produce='" + produce + '\'' +
        ", price='" + price + '\'' +
        ", genre='" + genre + '\'' +
        ", releaseDate=" + releaseDate +
        '}';
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getProduce() {
    return produce;
  }

  public void setProduce(String produce) {
    this.produce = produce;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }
}
