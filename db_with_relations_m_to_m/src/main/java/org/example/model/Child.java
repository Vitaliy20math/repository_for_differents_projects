package org.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Одна из сущностей - ребенок с полями:
 * id - уникальный id ребенка
 * name - имя ребенка
 * surname - фамилия ребенка
 * Set<Toys> toys - сет игрушек ребенка
 *
 * не используем orphanRemove в связях типа ManyTo.. В данном случае лучше применить cascadeType
 */

@Entity
@Table(name = "child")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String name;
    @Column(name = "surname")
    private String surname;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(
            name = "children_toys",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "toy_id"))
    private Set<Toys> toys = new HashSet<>();

    public Child(String name, String surname, Set<Toys> toys) {
        this.name = name;
        this.surname = surname;
        this.toys = toys;
    }

    public Child(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Child() {

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Toys> getToys() {
        return toys;
    }

    public void setToys(Set<Toys> toys) {
        this.toys = toys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return id == child.id && Objects.equals(name, child.name) && Objects.equals(surname, child.surname) && Objects.equals(toys, child.toys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, toys);
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", toys=" + toys +
                '}';
    }
}
