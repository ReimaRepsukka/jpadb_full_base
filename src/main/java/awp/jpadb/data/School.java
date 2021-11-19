package awp.jpadb.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//All database columns don't need to be defined (those are just omitted from the json)
//You can omit existing fields from the json by tag @JsonIgnore  

@Entity
@Table(name="school")
public class School {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto increment
    public Long id;

    @Column(name="name")
    public String name;


    @Column(name="city")
    public String city;

    public School(){}

    public School( String name, String city) {
        this.name = name;
        this.city = city;
    }


}
