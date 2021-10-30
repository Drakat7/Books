package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}),
        @UniqueConstraint(columnNames = {"phone"}), @UniqueConstraint(columnNames = {"email"})})
public class Publishers {

    @Id
    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 80)
    private String phone;

    @Column(nullable = false, length = 24)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publishers", cascade = CascadeType.PERSIST)
    private List<Books> books;

    public Publishers(){}

    public Publishers(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "Publisher Name: " + this.name + ", Phone: " + this.phone + ", Email: " +
                this.email;
    }

    @Override
    public boolean equals(Object o){
        Publishers publisher = (Publishers) o;
        return this.getName() == publisher.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getPhone(), this.getEmail());
    }
}
