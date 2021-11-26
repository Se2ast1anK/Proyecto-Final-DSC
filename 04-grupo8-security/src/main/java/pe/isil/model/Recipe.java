package pe.isil.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_recipe")
public class Recipe {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column (name = "recipe_id", length = 30)
    private String Id_Recipe;
    @Column (name = "title", length = 250)
    private String Title;
    @Column(name = "description",length = 5000)
    private String Description;
    @Column(name = "ingredients",length = 250)
    private String Ingredients;
    @Column(name = "ingredients2",length = 250)
    private String Ingredients2;
    @Column(name = "ingredients3",length = 250)
    private String Ingredients3;
    @Column(name = "ingredients4",length = 250)
    private String Ingredients4;
    @Column(name = "ingredients5",length = 250)
    private String Ingredients5;
    @Column(name = "preparation",length = 5000)
    private String Preparation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "publication")
    private LocalDate Publication;
    @Column(name = "urlimage",length = 250)
    private String UrlImage;
    @Column(name = "urlimagephoto",length = 250)
    private String UrlImagephoto;

    //union
    //el admin_id se saca de el id de admin
    @Column(name = "admin_id",length = 30)
    private String AdminId;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id", insertable = false,updatable = false)
    private Admin admin;

    //union
    //el user_id se saca de el id de user
    @Column(name = "user_id", length = 30)
    private String UserId;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false,updatable = false)
    private User user;
}
