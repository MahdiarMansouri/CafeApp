package model.da;

import model.tools.ConnectionProvider;
import java.sql.Connection;
import java.sql.*;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;


public class DAInitializer implements AutoCloseable {
    private final Connection connection;
    private static final String SQL_FILE_PATH = "dbInit.sql";
    private static PreparedStatement preparedStatement;

    public DAInitializer() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    public static void main(String[] args) {
        executeSqlScript(SQL_FILE_PATH);
    }

    private static void executeSqlScript(String sqlFilePath) {
        StringBuilder sql = new StringBuilder();
        // Read the SQL file
        try (BufferedReader reader = new BufferedReader(new FileReader(sqlFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }
        } catch (Exception e) {
            e.getMessage();
            return;
        }

        try {
            String[] statements = sql.toString().split(";");
            for (String statement : statements) {
                if (!statement.trim().isEmpty()) {
                    preparedStatement.execute(statement.trim());
                    System.out.println("Executed: " + statement);
                }
            }

            System.out.println("SQL script executed successfully.");
        } catch (Exception e) {
            e.getMessage();
        }
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

}
