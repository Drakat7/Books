package csulb.cecs323.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Books {
    @Id
    @Column(nullable = false, length = 17)
    private String ISBN;

    @Column(nullable = false, length = 80)
    private String title;

    @Column(nullable = false)
    private int year_published;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authoring_entity_name", referencedColumnName = "email", nullable = false)
    private Authoring_entities authoring_entities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_name", referencedColumnName = "name", nullable = false)
    private Publishers publishers;

    public Books() {}

    public Books(String ISBN, String title, int year_published, Authoring_entities authoring_entities, Publishers publishers){
        this.ISBN = ISBN;
        this.title = title;
        this.year_published = year_published;
        this.authoring_entities = authoring_entities;
        this.publishers = publishers;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear_published() {
        return year_published;
    }

    public void setYear_published(int year_published) {
        this.year_published = year_published;
    }

    public Authoring_entities getAuthoring_entities() {
        return authoring_entities;
    }

    public void setAuthoring_entities(Authoring_entities authoring_entities) {
        this.authoring_entities = authoring_entities;
    }

    public Publishers getPublishers() {
        return publishers;
    }

    public void setPublishers(Publishers publishers) {
        this.publishers = publishers;
    }

    @Override
    public String toString(){
        return "ISBN: " + this.ISBN + ", Title: " + this.title + ", Year Published: " +
                this.year_published;
    }

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
