package practice;

public class Book {
    private int id;
    private String title;
    private String author;

    // 생성자
    public Book() {}

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // getter
    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}

    // setter
    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}

}