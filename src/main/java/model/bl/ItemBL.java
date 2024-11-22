package model.bl;

import controller.exceptions.DuplicateItemNameException;
import controller.exceptions.NoItemFoundException;
import lombok.Getter;
import model.da.ItemDA;
import model.entity.Item;
import model.tools.CRUD;

import java.util.List;

public class ItemBL implements CRUD<Item> {
    @Getter
    private static final ItemBL itemBl = new ItemBL();

    private ItemBL() {
    }

    @Override
    public Item save(Item item) throws Exception {
        try (ItemDA ItemDA = new ItemDA()) {
            if (ItemDA.findByItemName(item.getName()) == null) {
                ItemDA.save(item);
                return item;
            }
            throw new DuplicateItemNameException();
        }
    }

    @Override
    public Item edit(Item item) throws Exception {
        try (ItemDA ItemDA = new ItemDA()) {
            if (ItemDA.findById(item.getItemId()) != null) {
                ItemDA.edit(item);
                return item;
            } else {
                throw new NoItemFoundException();
            }
        }
    }

    @Override
    public Item remove(int id) throws Exception {
        try (ItemDA ItemDA = new ItemDA()) {
            Item item = ItemDA.findById(id);
            if (item != null) {
                ItemDA.remove(id);
                return item;
            } else {
                throw new NoItemFoundException();
            }
        }
    }

    @Override
    public List<Item> findAll() throws Exception {
        try (ItemDA ItemDA = new ItemDA()) {
            List<Item> itemList = ItemDA.findAll();
            if (!itemList.isEmpty()) {
                return itemList;
            } else {
                throw new NoItemFoundException();
            }
        }
    }

    @Override
    public Item findById(int id) throws Exception {
        try (ItemDA ItemDA = new ItemDA()) {
            Item item = ItemDA.findById(id);
            if (item != null) {
                return item;
            } else {
                throw new NoItemFoundException();
            }
        }
    }

    public Item findByItemName(String itemName) throws Exception {
        try (ItemDA ItemDA = new ItemDA()) {
            Item item = ItemDA.findByItemName(itemName);
            if (item != null) {
                return item;
            } else {
                throw new NoItemFoundException();
            }
        }
    }

}
