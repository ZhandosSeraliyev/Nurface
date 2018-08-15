package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "docs")
public class Doc extends GenericModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	public Long id;

    @Column(name="name")
    public String name;

    @Column(name = "created_at")
    public Date createdAt;

}
