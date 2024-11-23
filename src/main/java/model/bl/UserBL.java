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

    public UserBL() {
    }

    @Override
    public User save(User user) throws Exception {
        try (UserDA userDA = new UserDA()) {
            if (userDA.findByUsername(user.getUsername()) == null) {
                userDA.save(user);
                return user;
            } else {
                throw new DuplicateUsernameException();
            }
        }
    }

    @Override
    public User edit(User user) throws Exception {
        try (UserDA userDA = new UserDA()) {
            if (userDA.findById(user.getUser_id()) != null) {
                userDA.edit(user);
                return user;
            } else {
                throw new NoUserFoundException();
            }
        }
    }

    @Override
    public User remove(int id) throws Exception {
        try (UserDA userDA = new UserDA()) {
            User user = userDA.findById(id);
            if (user != null) {
                userDA.remove(id);
                return user;
            } else {
                throw new NoUserFoundException();
            }
        }
    }

    @Override
    public List<User> findAll() throws Exception {
        try (UserDA userDA = new UserDA()) {
            List<User> perosnList = userDA.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoUserFoundException();
            }
        }
    }

    @Override
    public User findById(int id) throws Exception {
        try (UserDA userDA = new UserDA()) {
            User user = userDA.findById(id);
            if (user != null) {
                return user;
            } else {
                throw new NoUserFoundException();
            }
        }
    }

    public User findByUsername(String username) throws Exception {
        try (UserDA userDA = new UserDA()) {
            User user = userDA.findByUsername(username);
            if (user != null) {
                return user;
            } else {
                throw new NoUserFoundException();
            }
        }
    }

    public User findByUsernameAndPassword(String username, String password) throws Exception {
        try (UserDA userDA = new UserDA()) {
            User user = userDA.findByUsernameAndPassword(username, password);
            if (user != null) {
                return user;
            } else {
                throw new NoUserFoundException();
            }
        }
    }
}
