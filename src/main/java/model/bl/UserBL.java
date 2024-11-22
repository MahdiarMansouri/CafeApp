package model.bl;

import controller.exceptions.user.DuplicateUsernameException;
import controller.exceptions.user.NoUserFoundException;
import lombok.Getter;
import model.da.UserDA;
import model.entity.User;
import model.tools.CRUD;

import java.util.List;

public class UserBL implements CRUD<User> {
    @Getter
    private static final UserBL userBl = new UserBL();

    private UserBL() {
    }

    @Override
    public User save(User user) throws Exception {
        try (UserDA UserDA = new UserDA()) {
            if(UserDA.findByUsername(user.getUser_name()) == null) {
                UserDA.save(user);
                return user;
            }
            throw new DuplicateUsernameException();
        }
    }

    @Override
    public User edit(User user) throws Exception {
        try (UserDA UserDA = new UserDA()) {
            if (UserDA.findById(user.getUser_id()) != null) {
                UserDA.edit(user);
                return user;
            } else {
                throw new NoUserFoundException();
            }
        }
    }

    @Override
    public User remove(int id) throws Exception {
        try (UserDA UserDA = new UserDA()) {
            User user = UserDA.findById(id);
            if (user != null) {
                UserDA.remove(id);
                return user;
            } else {
                throw new NoUserFoundException();
            }
        }
    }

    @Override
    public List<User> findAll() throws Exception {
        try (UserDA UserDA = new UserDA()) {
            List<User> perosnList = UserDA.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoUserFoundException();
            }
        }
    }

    @Override
    public User findById(int id) throws Exception {
        try (UserDA UserDA = new UserDA()) {
            User user = UserDA.findById(id);
            if (user != null) {
                return user;
            } else {
                throw new NoUserFoundException();
            }
        }
    }

    public User findByUsername(String username) throws Exception {
        try (UserDA UserDA = new UserDA()) {
            User user = UserDA.findByUsername(username);
            if (user != null) {
                return user;
            } else {
                throw new NoUserFoundException();
            }
        }
    }

    public User findByUsernameAndPassword(String username, String password) throws Exception {
        try (UserDA UserDA = new UserDA()) {
            User user = UserDA.findByUsernameAndPassword(username,password);
            if (user != null) {
                return user;
            } else {
                throw new NoUserFoundException();
            }
        }
    }
}
