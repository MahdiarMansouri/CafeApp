package model.bl;

import lombok.Getter;

import java.util.List;

import controller.exceptions.person.NoPersonFoundException;
import model.da.PersonDA;
import model.entity.Person;
import model.entity.User;
import model.tools.CRUD;

public class PersonBL implements CRUD<Person> {
    @Getter
    private static final PersonBL personBl = new PersonBL();

    private PersonBL() {
    }

    @Override
    public Person save(Person person) throws Exception {
        try (PersonDA PersonDA = new PersonDA()) {
            PersonDA.save(person);
            return person;
        }
    }

    @Override
    public Person edit(Person person) throws Exception {
        try (PersonDA PersonDA = new PersonDA()) {
            if (PersonDA.findById(person.getId()) != null) {
                PersonDA.edit(person);
                return person;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    @Override
    public Person remove(int id) throws Exception {
        try (PersonDA PersonDA = new PersonDA()) {
            Person person = PersonDA.findById(id);
            if (person != null) {
                PersonDA.remove(id);
                return person;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    @Override
    public List<Person> findAll() throws Exception {
        try (PersonDA PersonDA = new PersonDA()) {
            List<Person> personList = PersonDA.findAll();
            if (!personList.isEmpty()) {
//                personList
//                        .stream()
//                        .map(person -> person.setUser(UserBL.getUserBl().findById(person.getUser().getUser_id())))
//                        .collect(Collectors.toList());

                for (Person person : personList) {
                    person.setUser(UserBL.getUserBl().findById(person.getUser().getUser_id()));
                }
                return personList;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    @Override
    public Person findById(int id) throws Exception {
        try (PersonDA PersonDA = new PersonDA()) {
            Person person = PersonDA.findById(id);
            if (person != null) {
                int userId = person.getUser().getUser_id();
                User user = UserBL.getUserBl().findById(userId);
                person.setUser(user);
                return person;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    public List<Person> findByFamily(String family) throws Exception {
        try (PersonDA PersonDA = new PersonDA()) {
            List<Person> personList = PersonDA.findByFamily(family);
            if (!personList.isEmpty()) {
                for (Person person : personList) {
                    person.setUser(UserBL.getUserBl().findById(person.getUser().getUser_id()));
                }
                return personList;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    public Person findByUsername(String username)throws Exception {
        try (PersonDA PersonDA = new PersonDA()) {
            User user = UserBL.getUserBl().findByUsername(username);
            Person person = PersonDA.findByUserId(user.getUser_id());
            person.setUser(UserBL.getUserBl().findById(person.getUser().getUser_id()));
            return person;
        }
    }
}
