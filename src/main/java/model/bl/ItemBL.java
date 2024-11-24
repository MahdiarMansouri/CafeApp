package model.bl;

import controller.exceptions.item.DuplicateItemNameException;
import controller.exceptions.item.NoItemFoundException;
import controller.exceptions.item.NotValidEnumException;
import lombok.Getter;
import model.da.ItemDA;
import model.entity.Item;
import model.entity.enums.Category;
import model.tools.CRUD;
import model.tools.Validator;

import java.util.List;

import static model.tools.Validator.isValidEnum;

public class ItemBL implements CRUD<Item> {
    @Getter
    private static final ItemBL itemBl = new ItemBL();

    public ItemBL() {
    }

    @Override
    public Item save(Item item) throws Exception {
        try (ItemDA itemDA = new ItemDA()) {
            if (itemDA.findByItemName(item.getName()) == null) {
                if (Validator.itemNameValidator(item.getName())) {
                    itemDA.save(item);
                    return item;
                }else {
                    throw new Exception("Item name is not valid");
                }
            }else{
                throw new DuplicateItemNameException();
            }
        }
    }

    @Override
    public Item edit(Item item) throws Exception {
        try (ItemDA itemDA = new ItemDA()) {
            if (itemDA.findById(item.getItemId()) != null) {
                if (Validator.itemNameValidator(item.getName())) {
                    itemDA.edit(item);
                    return item;
                }else{
                    throw new Exception("Item name is not valid");
                }
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

    public List<Item> findByCategory(String category) throws Exception {
        try (ItemDA itemDA = new ItemDA()) {
            if (isValidEnum(Category.class, category)) {
                List<Item> itemList = itemDA.findByCategory(category);
                if (!itemList.isEmpty()) {
                    return itemList;
                } else {
                    throw new NoItemFoundException();
                }
            }else {
                throw new NotValidEnumException();
            }
        }
    }

}
