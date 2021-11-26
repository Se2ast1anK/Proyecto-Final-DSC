package pe.isil.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "name", length = 50)
    private String Name;
    @Column(name = "first_name", length = 50)
    private String FirstName;
    @Column(name = "last_name", length = 50)
    private String LastName;
    @Column(name = "age")
    private Integer Age;
    @Column(name = "address")
    private String Address;
    @Column(name = "sex")
    private String Sex;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Recipe> recipes;

    public User(String FirstName, String LastName, String username, String password) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.username = username;
        this.password = password;
    }
}
