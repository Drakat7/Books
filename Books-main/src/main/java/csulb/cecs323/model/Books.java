package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedNativeQuery(
        name = "ReturnBooks",
        query = "SELECT * " +
                "FROM BOOKS ",
        resultClass = Books.class
)

@NamedNativeQuery(
        name = "CheckISBN",
        query = "SELECT * " +
                "FROM BOOKS " +
                "WHERE ISBN = ?",
        resultClass = Books.class
)
@NamedNativeQuery(
        name = "CheckTitleAndPublisher",
        query = "SELECT * " +
                "FROM BOOKS " +
                "WHERE TITLE = ? AND " +
                "PUBLISHER_NAME = ? ",
        resultClass = Books.class
)
@NamedNativeQuery(
        name = "CheckTitleAndAuthor",
        query = "SELECT * " +
                "FROM BOOKS " +
                "WHERE TITLE = ? AND " +
                "AUTHORING_ENTITY_NAME = ? ",
        resultClass = Books.class
)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames =
        {"title", "publisher_name"}), @UniqueConstraint(columnNames = {"title", "authoring_entity_name"})})
/**
 * A book entity with a publisher and an authoring entity
 */
public class Books {
    @Id
    @Column(nullable = false, length = 17)
    /**
     * An external attribute every books has
     */
    private String ISBN;

    @Column(nullable = false, length = 80)
    /**
     * title of the book
     */
    private String title;

    @Column(nullable = false)
    /**
     * year of the books publishing
     */
    private int year_published;

    /**
     * books realtionship to authoring_entities
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authoring_entity_name", referencedColumnName = "email", nullable = false)
    /**
     * the authoring entity who made the book
     */
    private Authoring_entities authoring_entities;

    /**
     * books realtionship to Publishers
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_name", referencedColumnName = "name", nullable = false)
    /**
     * publisher of the book
     */
    private Publishers publishers;

    /**
     * default constructor
     */
    public Books() {}

    /**
     * Parameterized  constructor for the Books entity
     * @param ISBN                  An external attribute every books has
     * @param title                 title of the book
     * @param year_published        year of the books publishing
     * @param authoring_entities    the authoring entity who made the book
     * @param publishers            publisher of the book
     */
    public Books(String ISBN, String title, int year_published, Authoring_entities authoring_entities, Publishers publishers){
        this.ISBN = ISBN;
        this.title = title;
        this.year_published = year_published;
        this.authoring_entities = authoring_entities;
        this.publishers = publishers;
    }

    /**
     * getter for the books ISBN
     * @return String   ISBN
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * setter fot the books ISBN
     * @param ISBN
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * getter for the books title
     * @return String   title
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter for the books title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * getter for the books publishing year
     * @return int  year_published
     */
    public int getYear_published() {
        return year_published;
    }

    /**
     * setter for the books publishing year
     * @param year_published
     */
    public void setYear_published(int year_published) {
        this.year_published = year_published;
    }

    /**
     * getter for the books authoring entities
     * @return Authoring_entities  authoring_entities
     */
    public Authoring_entities getAuthoring_entities() {
        return authoring_entities;
    }

    /**
     * setter for the books authoring entities
     * @param authoring_entities
     */
    public void setAuthoring_entities(Authoring_entities authoring_entities) {
        this.authoring_entities = authoring_entities;
    }

    /**
     * getter for the books publishers
     * @return Publishers   publishers
     */
    public Publishers getPublishers() {
        return publishers;
    }

    /**
     * setter for the books publishers
     * @param publishers
     */
    public void setPublishers(Publishers publishers) {
        this.publishers = publishers;
    }

    /**
     * toString method for the books entity
     * @return String   books information
     */
    @Override
    public String toString(){
        return "ISBN: " + this.ISBN + ", Title: " + this.title + ", Year Published: " +
                this.year_published + ", Authors Email: " + this.authoring_entities.getEmail() + ", Publisher: " + this.publishers.getName();
    }

    /**
     * equals method which compares two book's ISBN
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o){
        Books books = (Books) o;
        return this.getISBN() == books.getISBN();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getISBN(), this.getTitle(), this.getYear_published());
    }
}
