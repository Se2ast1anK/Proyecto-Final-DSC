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
@Table(name = "tbl_tip")
public class Tip {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tip_id", length = 30)
    private String Id_Tip;
    @Column(name = "title",length = 250)
    private String Title;
    @Column(name = "document",length = 5000)
    private String Document;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "publication")
    private LocalDate Publication;
    @Column(name = "urlimage",length = 250)
    private String UrlImage;
    //union
    //el admin_id se saca de el id de admin
    @Column(name = "admin_id",length = 30)
    private String AdminId;
    // 1 tip , 1 admin
    //pegar en onetomany el admin pequeño
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //el admin_id sale de la union
    @JoinColumn(name = "admin_id", insertable = false,updatable = false)
    private Admin admin;
}
