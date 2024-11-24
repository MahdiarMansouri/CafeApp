package model.bl;

import controller.exceptions.person.DuplicatePersonException;
import lombok.Getter;

import java.util.List;

import controller.exceptions.person.NoPersonFoundException;
import model.da.PersonDA;
import model.entity.Person;
import model.entity.User;
import model.tools.CRUD;
import model.tools.Validator;

public class PersonBL implements CRUD<Person> {
    @Getter
    private static final PersonBL personBl = new PersonBL();

    public PersonBL() {
    }

    @Override
    public Person save(Person person) throws Exception {
        try (PersonDA personDA = new PersonDA()) {
            if (personDA.findByNationalID(person.getNationalId()) == null) {
                if (Validator.nameValidator(person.getName()) && Validator.nameValidator(person.getFamily()) && Validator.nationalIDValidator(person.getNationalId()) && Validator.phoneNumberValidator(person.getPhoneNumber())) {
                    personDA.save(person);
                    return person;
                } else {
                    throw new Exception("Invalid inputs for person properties..!");
                }
            } else {
                throw new DuplicatePersonException();
            }
        }
    }

    @Override
    public Person edit(Person person) throws Exception {
        try (PersonDA personDA = new PersonDA()) {
            if (personDA.findById(person.getId()) != null) {
                if (Validator.nameValidator(person.getName()) && Validator.nameValidator(person.getFamily()) && Validator.nationalIDValidator(person.getNationalId()) && Validator.phoneNumberValidator(person.getPhoneNumber())) {
                    personDA.edit(person);
                    return person;
                } else {
                    throw new NoPersonFoundException();
                }
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    @Override
    public Person remove(int id) throws Exception {
        try (PersonDA personDA = new PersonDA()) {
            Person person = personDA.findById(id);
            if (person != null) {
                personDA.remove(id);
                return person;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    @Override
    public List<Person> findAll() throws Exception {
        try (PersonDA personDA = new PersonDA()) {
            List<Person> personList = personDA.findAll();
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

    @Override
    public Person findById(int id) throws Exception {
        try (PersonDA personDA = new PersonDA()) {
            Person person = personDA.findById(id);
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
        try (PersonDA personDA = new PersonDA()) {
            List<Person> personList = personDA.findByFamily(family);
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

    public Person findByUsername(String username) throws Exception {
        try (PersonDA personDA = new PersonDA()) {
            User user = UserBL.getUserBl().findByUsername(username);
            Person person = personDA.findByUserId(user.getUser_id());
            person.setUser(UserBL.getUserBl().findById(person.getUser().getUser_id()));
            return person;
        }
    }
}
