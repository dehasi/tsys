package model;


import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @Column(name = "id_city")
    private long id;
    @Column(name = "name")
    private String name;

    @Column(name = "comment")
    private String comment;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
