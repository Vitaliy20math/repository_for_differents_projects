package org.example;

import org.example.util.Util;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = Util.getConnection();
        System.out.println(connection);
    }
}