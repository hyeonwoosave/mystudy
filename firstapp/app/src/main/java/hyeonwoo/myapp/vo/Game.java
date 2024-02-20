package hyeonwoo.myapp.vo;

import java.io.Serializable;
import java.sql.Date;

public class Game implements Serializable {

  private static final long serialVersionUID = 100L;

  private int no;
  private String title;
  private int rating;
  private int price;
  private String produce;
  private String genre;
  private String info;
  private Date release_date;

  @Override
  public String toString() {
    return "Game{" +
        "no=" + no +
        ", title='" + title + '\'' +
        ", rating=" + rating +
        ", price=" + price +
        ", produce='" + produce + '\'' +
        ", genre='" + genre + '\'' +
        ", info='" + info + '\'' +
        ", release_date=" + release_date +
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

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getProduce() {
    return produce;
  }

  public void setProduce(String produce) {
    this.produce = produce;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public Date getRelease_date() {
    return release_date;
  }

  public void setRelease_date(Date release_date) {
    this.release_date = release_date;
  }

}
