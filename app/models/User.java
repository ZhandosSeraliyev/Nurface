package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends GenericModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Integer id;

    @Column(name="first")
    public String first;

    @Column(name="password")
    public String password;

    @Column(name="second")
    public String second;


}
