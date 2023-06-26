package com.projeto.msa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.projeto.config.DatabaseConfig;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Connection;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.projeto.config")
public class DemoApplication {

    @Autowired
    private DatabaseConfig databaseConfig;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    public void performDatabaseOperations() {
        String url = databaseConfig.getUrl();
        String username = databaseConfig.getUsername();
        String password = databaseConfig.getPassword();

        try {
            // Estabelecer conexão com o banco de dados
            Connection connection = DriverManager.getConnection(url, username, password);

            // Preparar a consulta SQL para inserção do cliente
            String sql = "INSERT INTO cliente (email, nome, senha, cpf, dataNascimento) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Definir os valores dos parâmetros da consulta
            statement.setString(1, "cliente@example.com");
            statement.setString(2, "Nome do Cliente");
            statement.setString(3, "senha123");
            statement.setString(4, "12345678901");
            statement.setDate(5, java.sql.Date.valueOf("2000-01-01"));

            // Executar a consulta
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente inserido com sucesso!");
            } else {
                System.out.println("Falha ao inserir o cliente.");
            }

            // Fechar a conexão e o statement
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


/*@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}*/
