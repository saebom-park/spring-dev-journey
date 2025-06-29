package practice;

public class Order {
    private int id;
    private int memberId;
    private int bookId;
    private OrderStatus status;

    // constructor
    public Order() {}
    public Order(int id) {this.id = id;}
    public Order(int id, int memberId, int bookId, OrderStatus status) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.status = status;
    }

    // getter
    public int getId() {return id;}
    public int getMemberId() {return memberId;}
    public int getBookId() {return bookId;}
    public OrderStatus getStatus() {return status;}

    // setter
    public void setId(int id) {this.id = id;}
    public void setMemberId(int memberId) {this.memberId = memberId;}
    public void setBookId(int bookId) {this.bookId = bookId;}
    public void setStatus(OrderStatus status) {this.status = status;}
}