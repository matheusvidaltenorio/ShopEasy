package util.domain;

//Depois de configurar para integrar com o banco e instalar pacotes necessário
//criamos essa classe para fazer a conversação através dela.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://localhost:5432/shopeasy";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}
