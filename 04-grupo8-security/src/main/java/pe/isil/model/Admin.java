package pe.isil.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_admin")
public class Admin {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "admin_id", length = 30)
    private String Id_Admin;
    @Column(name = "name", length = 50)
    private String Name;
    @Column(name = "first_name", length = 50)
    private String FirstName;
    @Column(name = "last_name", length = 50)
    private String LastName;
    @Column(name = "age")
    private Integer Age;
    @Column(name = "address", length = 50)
    private String Address;
    @Column(name = "sex")
    private String Sex;
    //lista de tips
    //pegar el admin que esta en el modelo tip
    @OneToMany(mappedBy = "admin",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Tip>tips;
    //Lista de recipes
    //pegar el admin que esta en el modelo recipe
    @OneToMany(mappedBy = "admin",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Recipe>recipes;


}
