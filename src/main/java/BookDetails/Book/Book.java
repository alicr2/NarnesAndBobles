package BookDetails.Book;

import jakarta.persistence.*;

@Entity
@Table
public class Book {

@Id
//    @SequenceGenerator(
//            name = "book_sequence",
//            sequenceName = "book_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "book_sequence"
//    )

    private String ISBN;
    private String bookName;
    private String bookDescription;
    private double price;
    private String genre;
    private String publisher;
    private int yearPublished;
    private int copiesSold;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

//    public Book() {
//        ISBN = " ";
//        bookName = " ";
//        bookDescription = " ";
//        price = 0.00;
//        author = ;
//        genre = " ";
//        publisher = " ";
//        yearPublished = 0;
//        copiesSold = 0;
//    }


    public Book() {
    }

    public Book(String ISBN, String bookName, String bookDescription, double price, Author author, String genre, String publisher, int yearPublished, int copiesSold) {
        this.ISBN = ISBN;
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.price = price;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.copiesSold = copiesSold;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", yearPublished=" + yearPublished +
                ", copiesSold=" + copiesSold +
                '}';
    }

    public String getISBN() {
        return ISBN;
    }

    public String getName() {
        return bookName;
    }

    public String getDescription() {
        return bookDescription;
    }

    public double getPrice() {
        return price;
    }

    public Author getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public int getCopiesSold() {
        return copiesSold;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public void setName(String bookName) {
        this.bookName = bookName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public void setCopiesSold(int copiesSold) {
        this.copiesSold = copiesSold;
    }
}