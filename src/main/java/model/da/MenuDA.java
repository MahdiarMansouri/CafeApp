package model.da;

import model.entity.Item;
import model.entity.Menu;
import model.entity.enums.Role;
import model.tools.CRUD;
import model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDA implements AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public MenuDA() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

//    public Menu addItem(Item item) throws Exception {
//        preparedStatement = connection.prepareStatement(
//                "INSERT INTO MENU_TBL (ITEM_ID, ITEM_NAME, DESCRIPTION, PRICE, CATEGORY, IS_AVAILABLE) VALUES (?,?,?,?,?,?)"
//        );
//        preparedStatement.setInt(1, item.getItemId());
//        preparedStatement.setString(2, item.getName());
//        preparedStatement.setString(3, item.getDescription());
//        preparedStatement.setString(4, item.getPrice().toString());
//        preparedStatement.setString(5, item.getCategory().toString());
//        preparedStatement.setBoolean(6, item.isAvailable());
//        preparedStatement.execute();
//        return menu;
//    }
//
//
//    public Menu edit(Menu menu) throws Exception {
//        preparedStatement = connection.prepareStatement(
//                "UPDATE USER_TBL SET USERNAME=?, PASSWORD=?, ENABLED=? WHERE ID=?"
//        );
//        preparedStatement.setString(1, menu.getMenu_name());
//        preparedStatement.setString(2, menu.getPassword());
//        preparedStatement.setString(3, String.valueOf(menu.getRole()));
//        preparedStatement.setInt(4, menu.getMenu_id());
//        preparedStatement.execute();
//        return menu;
//    }
//
//
//    public Menu remove(int id) throws Exception {
//        preparedStatement = connection.prepareStatement(
//                "DELETE FROM USER_TBL WHERE ID=?"
//        );
//        preparedStatement.setInt(1, id);
//        preparedStatement.execute();
//        return null;
//    }
//
//
//    public List<Menu> findAll() throws Exception {
//        List<Menu> menuList = new ArrayList<>();
//
//        preparedStatement = connection.prepareStatement("SELECT * FROM USER_TBL ORDER BY ID");
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        while (resultSet.next()) {
//            Menu menu = Menu
//                    .builder()
//                    .menu_id(resultSet.getInt("ID"))
//                    .menu_name(resultSet.getString("USERNAME"))
//                    .password(resultSet.getString("PASSWORD"))
//                    .role(Role.valueOf(resultSet.getString("ROLE")))
//                    .build();
//
//            menuList.add(menu);
//        }
//
//        return menuList;
//    }
//
//
//    public Menu findById(int id) throws Exception {
//        preparedStatement = connection.prepareStatement("SELECT * FROM USER_TBL WHERE ID=?");
//        preparedStatement.setInt(1, id);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        Menu menu = null;
//        if (resultSet.next()) {
//            menu = Menu
//                    .builder()
//                    .menu_id(resultSet.getInt("ID"))
//                    .menu_name(resultSet.getString("USERNAME"))
//                    .password(resultSet.getString("PASSWORD"))
//                    .role(Role.valueOf(resultSet.getString("ROLE")))
//                    .build();
//        }
//        return menu;
//    }
//
//    public Menu findByMenuname(String menuname) throws Exception {
//        preparedStatement = connection.prepareStatement("SELECT * FROM USER_TBL WHERE USERNAME=? ");
//        preparedStatement.setString(1, menuname);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        Menu menu = null;
//        if (resultSet.next()) {
//            menu = Menu
//                    .builder()
//                    .menu_id(resultSet.getInt("ID"))
//                    .menu_name(resultSet.getString("USERNAME"))
//                    .password(resultSet.getString("PASSWORD"))
//                    .role(Role.valueOf(resultSet.getString("ROLE")))
//                    .build();
//        }
//        return menu;
//    }
//
//    public Menu findByMenunameAndPassword(String menuname, String password) throws Exception {
//        preparedStatement = connection.prepareStatement("SELECT * FROM USER_TBL WHERE USERNAME=? AND PASSWORD=?");
//        preparedStatement.setString(1, menuname);
//        preparedStatement.setString(2, password);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        Menu menu = null;
//        if (resultSet.next()) {
//            menu = Menu
//                    .builder()
//                    .menu_id(resultSet.getInt("ID"))
//                    .menu_name(resultSet.getString("USERNAME"))
//                    .password(resultSet.getString("PASSWORD"))
//                    .role(Role.valueOf(resultSet.getString("ROLE")))
//                    .build();
//        }
//        return menu;
//    }


    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}