package model.tools;


import lombok.Getter;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class ConnectionProvider {
    @Getter
    private static ConnectionProvider connectionProvider = new ConnectionProvider();
    private static BasicDataSource basicDataSource = new BasicDataSource();

    private ConnectionProvider() {}

    public Connection getConnection() throws SQLException {
        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        basicDataSource.setUsername("java");
        basicDataSource.setPassword("java123");
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(20);
        return basicDataSource.getConnection();
    }

    public int getNextId(String sequenceName) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select " + sequenceName + ".nextval as NEXT_ID from dual"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("NEXT_ID");

    }

}
