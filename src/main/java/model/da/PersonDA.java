package model.da;

import lombok.extern.log4j.Log4j;
import model.entity.Person;
import model.entity.User;
import model.entity.enums.Gender;
import model.tools.CRUD;
import model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class PersonDA implements AutoCloseable, CRUD<Person> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public PersonDA() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Person save(Person person) throws Exception {
        person.setId(ConnectionProvider.getConnectionProvider().getNextId("PERSON_SEQ"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO PERSON_TBL (ID, NAME, FAMILY, GENDER, NATIONAL_ID, PHONE_NUMBER, USER_ID) VALUES (?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setString(4, person.getGender().name());
        preparedStatement.setString(5, person.getNationalId());
        preparedStatement.setString(6, person.getPhoneNumber());
        preparedStatement.setInt(7, person.getUser().getUser_id());
        preparedStatement.execute();
        return person;
    }

    @Override
    public Person edit(Person person) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE PERSON_TBL SET NAME=?, FAMILY=?, GENDER=?, BIRTH_DATE=?, CITY=?, ALGO=?, SE=?, EE=?, USER_ID=? WHERE ID=?"
        );
        preparedStatement.setInt(1, person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setString(4, person.getGender().name());
        preparedStatement.setString(5, person.getNationalId());
        preparedStatement.setString(6, person.getPhoneNumber());
        preparedStatement.setInt(7, person.getUser().getUser_id());
        preparedStatement.execute();
        return person;
    }

    @Override
    public Person remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PERSON_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Person> findAll() throws Exception {
        List<Person> personList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON_TBL ORDER BY ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Person person = Person
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .nationalId(resultSet.getString("NATIONAL_ID"))
                    .phoneNumber(resultSet.getString("PHONE_NUMBER"))
                    .user(User.builder().user_id(resultSet.getInt("USER_ID")).build())
                    .build();

            personList.add(person);
        }

        return personList;
    }

    @Override
    public Person findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON_TBL WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;
        if (resultSet.next()) {
            person = Person
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .nationalId(resultSet.getString("NATIONAL_ID"))
                    .phoneNumber(resultSet.getString("PHONE_NUMBER"))
                    .user(User.builder().user_id(resultSet.getInt("USER_ID")).build())
                    .build();
        }
        return person;
    }

    public Person findByUserId(int userId) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON_TBL WHERE USER_ID=?");
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;
        if (resultSet.next()) {
            person = Person
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .nationalId(resultSet.getString("NATIONAL_ID"))
                    .phoneNumber(resultSet.getString("PHONE_NUMBER"))
                    .user(User.builder().user_id(resultSet.getInt("USER_ID")).build())
                    .build();
        }
        return person;
    }

    public List<Person> findByFamily(String family) throws Exception {
        List<Person> personList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM PERSON_TBL WHERE FAMILY LIKE? ORDER BY ID");
        preparedStatement.setString(1, family + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Person person = Person
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .gender(Gender.valueOf(resultSet.getString("GENDER")))
                    .nationalId(resultSet.getString("NATIONAL_ID"))
                    .phoneNumber(resultSet.getString("PHONE_NUMBER"))
                    .user(User.builder().user_id(resultSet.getInt("USER_ID")).build())
                    .build();

            personList.add(person);
        }

        return personList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
