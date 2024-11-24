package model.da;

import model.entity.Item;
import model.entity.enums.Category;
import model.tools.CRUD;
import model.tools.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ItemDA implements AutoCloseable, CRUD<Item> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public ItemDA() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Item save(Item item) throws Exception {
        item.setItemId(ConnectionProvider.getConnectionProvider().getNextId("ITEM_SEQ"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO ITEM_TBL (ITEM_ID, ITEM_NAME, DESCRIPTION, PRICE, CATEGORY, IS_AVAILABLE) VALUES (?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, item.getItemId());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setString(3, item.getDescription());
        preparedStatement.setFloat(4, item.getPrice());
        preparedStatement.setString(5, String.valueOf(item.getCategory()));
        preparedStatement.setBoolean(6, item.isAvailable());
        preparedStatement.execute();
        return item;
    }

    @Override
    public Item edit(Item item) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE ITEM_TBL SET  ITEM_NAME=?, DESCRIPTION=?, PRICE=?, CATEGORY=?, IS_AVAILABLE=? WHERE ITEM_ID=?"
        );
        preparedStatement.setString(1, item.getName());
        preparedStatement.setString(2, item.getDescription());
        preparedStatement.setFloat(3, item.getPrice());
        preparedStatement.setString(4, String.valueOf(item.getCategory()));
        preparedStatement.setBoolean(5, item.isAvailable());
        preparedStatement.setInt(6, item.getItemId());
        preparedStatement.execute();
        return item;
    }

    @Override
    public Item remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM ITEM_TBL WHERE ITEM_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Item> findAll() throws Exception {
        List<Item> itemList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM_TBL ORDER BY ITEM_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Item item = Item
                    .builder()
                    .itemId(resultSet.getInt("ITEM_ID"))
                    .name(resultSet.getString("ITEM_NAME"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .price(resultSet.getInt("PRICE"))
                    .category(Category.valueOf(resultSet.getString("CATEGORY")))
                    .isAvailable(resultSet.getBoolean("IS_AVAILABLE"))
                    .build();

            itemList.add(item);
        }

        return itemList;
    }

    @Override
    public Item findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM_TBL WHERE ITEM_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Item item = null;
        if (resultSet.next()) {
            item = Item
                    .builder()
                    .itemId(resultSet.getInt("ITEM_ID"))
                    .name(resultSet.getString("ITEM_NAME"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .price(resultSet.getInt("PRICE"))
                    .category(Category.valueOf(resultSet.getString("CATEGORY")))
                    .isAvailable(resultSet.getBoolean("IS_AVAILABLE"))
                    .build();
        }
        return item;
    }

    public Item findByItemName(String itemName) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM_TBL WHERE ITEM_NAME=? ");
        preparedStatement.setString(1, itemName);
        ResultSet resultSet = preparedStatement.executeQuery();

        Item item = null;
        if (resultSet.next()) {
            item = Item
                    .builder()
                    .itemId(resultSet.getInt("ITEM_ID"))
                    .name(resultSet.getString("ITEM_NAME"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .price(resultSet.getInt("PRICE"))
                    .category(Category.valueOf(resultSet.getString("CATEGORY")))
                    .isAvailable(resultSet.getBoolean("IS_AVAILABLE"))
                    .build();
        }
        return item;
    }

    public List<Item> findByCategory(String category) throws Exception {
        List<Item> itemList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM ITEM_TBL WHERE CATEGORY = ? ORDER BY ITEM_ID");
        preparedStatement.setString(1, category);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Item item = Item
                    .builder()
                    .itemId(resultSet.getInt("ITEM_ID"))
                    .name(resultSet.getString("ITEM_NAME"))
                    .description(resultSet.getString("DESCRIPTION"))
                    .price(resultSet.getInt("PRICE"))
                    .category(Category.valueOf(resultSet.getString("CATEGORY")))
                    .isAvailable(resultSet.getBoolean("IS_AVAILABLE"))
                    .build();

            itemList.add(item);
        }

        return itemList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
