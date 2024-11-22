//package model.bl;
//
//import controller.exceptions.DuplicateMenuNameException;
//import controller.exceptions.NoMenuFoundException;
//import lombok.Getter;
//import model.da.MenuDA;
//import model.entity.Menu;
//import model.tools.CRUD;
//
//import java.util.List;
//
//public class MenuBL implements CRUD<Menu> {
//    @Getter
//    private static final MenuBL menuBl = new MenuBL();
//
//    private MenuBL() {
//    }
//
//    @Override
//    public Menu save(Menu menu) throws Exception {
//        try (MenuDA MenuDA = new MenuDA()) {
//            if (MenuDA.findByMenuName(menu.getName()) == null) {
//                MenuDA.save(menu);
//                return menu;
//            }
//            throw new DuplicateMenuNameException();
//        }
//    }
//
//    @Override
//    public Menu edit(Menu menu) throws Exception {
//        try (MenuDA MenuDA = new MenuDA()) {
//            if (MenuDA.findById(menu.getMenuId()) != null) {
//                MenuDA.edit(menu);
//                return menu;
//            } else {
//                throw new NoMenuFoundException();
//            }
//        }
//    }
//
//    @Override
//    public Menu remove(int id) throws Exception {
//        try (MenuDA MenuDA = new MenuDA()) {
//            Menu menu = MenuDA.findById(id);
//            if (menu != null) {
//                MenuDA.remove(id);
//                return menu;
//            } else {
//                throw new NoMenuFoundException();
//            }
//        }
//    }
//
//    @Override
//    public List<Menu> findAll() throws Exception {
//        try (MenuDA MenuDA = new MenuDA()) {
//            List<Menu> menuList = MenuDA.findAll();
//            if (!menuList.isEmpty()) {
//                return menuList;
//            } else {
//                throw new NoMenuFoundException();
//            }
//        }
//    }
//
//    @Override
//    public Menu findById(int id) throws Exception {
//        try (MenuDA MenuDA = new MenuDA()) {
//            Menu menu = MenuDA.findById(id);
//            if (menu != null) {
//                return menu;
//            } else {
//                throw new NoMenuFoundException();
//            }
//        }
//    }
//
//    public Menu findByMenuName(String menuName) throws Exception {
//        try (MenuDA MenuDA = new MenuDA()) {
//            Menu menu = MenuDA.findByMenuName(menuName);
//            if (menu != null) {
//                return menu;
//            } else {
//                throw new NoMenuFoundException();
//            }
//        }
//    }
//
//}
