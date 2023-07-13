package org.example.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Одна из сущностей - игрушка, с полями:
 *
 */
@Entity
@Table(name = "toys")
public class Toys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;


    public Toys(String name) {
        this.name = name;
    }

    public Toys() {

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toys toys = (Toys) o;
        return id == toys.id && Objects.equals(name, toys.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Toys{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
