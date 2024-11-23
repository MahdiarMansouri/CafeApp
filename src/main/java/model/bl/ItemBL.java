package model.bl;

import controller.exceptions.item.DuplicateItemNameException;
import controller.exceptions.item.NoItemFoundException;
import lombok.Getter;
import model.da.ItemDA;
import model.entity.Item;
import model.tools.CRUD;

import java.util.List;

public class ItemBL implements CRUD<Item> {
    @Getter
    private static final ItemBL itemBl = new ItemBL();

    public ItemBL() {
    }

    @Override
    public Item save(Item item) throws Exception {
        try (ItemDA itemDA = new ItemDA()) {
            if (itemDA.findByItemName(item.getName()) == null) {
                itemDA.save(item);
                return item;
            }
            throw new DuplicateItemNameException();
        }
    }

    @Override
    public Item edit(Item item) throws Exception {
        try (ItemDA itemDA = new ItemDA()) {
            if (itemDA.findById(item.getItemId()) != null) {
                itemDA.edit(item);
                return item;
            } else {
                throw new NoItemFoundException();
            }
        }
    }

    @Override
    public Item remove(int id) throws Exception {
        try (ItemDA itemDA = new ItemDA()) {
            Item item = itemDA.findById(id);
            if (item != null) {
                itemDA.remove(id);
                return item;
            } else {
                throw new NoItemFoundException();
            }
        }
    }

    @Override
    public List<Item> findAll() throws Exception {
        try (ItemDA itemDA = new ItemDA()) {
            List<Item> itemList = itemDA.findAll();
            if (!itemList.isEmpty()) {
                return itemList;
            } else {
                throw new NoItemFoundException();
            }
        }
    }

    @Override
    public Item findById(int id) throws Exception {
        try (ItemDA itemDA = new ItemDA()) {
            Item item = itemDA.findById(id);
            if (item != null) {
                return item;
            } else {
                throw new NoItemFoundException();
            }
        }
    }

    public Item findByItemName(String itemName) throws Exception {
        try (ItemDA itemDA = new ItemDA()) {
            Item item = itemDA.findByItemName(itemName);
            if (item != null) {
                return item;
            } else {
                throw new NoItemFoundException();
            }
        }
    }

}
