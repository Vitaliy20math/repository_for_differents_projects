package org.example.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Одна из сущностей - ребенок с полями:
 * id - уникальный id ребенка
 * name - имя ребенка
 * surname - фамилия ребенка
 * Set<Toys> toys - сет игрушек ребенка
 */
public class Child {
    int id;
    String name;
    String surname;
    Set<Toys> toys = new HashSet<Toys>();

}
