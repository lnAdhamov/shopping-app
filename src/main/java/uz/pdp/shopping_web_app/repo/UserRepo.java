package uz.pdp.shopping_web_app.repo;

import jakarta.servlet.http.HttpSession;
import uz.pdp.shopping_web_app.config.ConnectionPoolManager;
import uz.pdp.shopping_web_app.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepo {
    public static List<User> findAll() {
        String query = "select * from users";
        List<User> users = new ArrayList<>();
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getString("name"), UUID.fromString(resultSet.getString("id")), resultSet.getString("phone"), resultSet.getString("password"), resultSet.getString("role")));
            }
            return users;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static Optional<User> findByName(String name) {
        String query = "select * from users where name = ?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(resultSet.getString("name"), UUID.fromString(resultSet.getString("id")), resultSet.getString("phone"), resultSet.getString("password"), resultSet.getString("role")));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Optional<User> findById(UUID userId) {
        String query = "select * from users where id = ?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setObject(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(resultSet.getString("name"), UUID.fromString(resultSet.getString("id")), resultSet.getString("phone"), resultSet.getString("password"), resultSet.getString("role")));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUser(HttpSession session) {

        Object currentUser = session.getAttribute("currentUser");
        if (currentUser != null) {
            return (User) currentUser;
        } else {
            return null;
        }

    }
}
