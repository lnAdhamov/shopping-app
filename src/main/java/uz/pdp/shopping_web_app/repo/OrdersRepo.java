package uz.pdp.shopping_web_app.repo;

import uz.pdp.shopping_web_app.config.ConnectionPoolManager;
import uz.pdp.shopping_web_app.entity.Orders;
import uz.pdp.shopping_web_app.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrdersRepo {
    public static List<Orders> findAll() {
        String query = "select * from orders order by status,deliver";
        List<Orders> orders = new ArrayList<>();
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(new Orders(resultSet.getInt("id"), resultSet.getTimestamp("date_time").toLocalDateTime(), UUID.fromString(resultSet.getString("user_id") == null ? "d4f2e0a8-9b7d-4c90-ae5a-7b17c2396a45" : resultSet.getString("user_id")), resultSet.getBoolean("status"), resultSet.getBoolean("deliver")));
            }
            return orders;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer newOrder(User currentUser1) {
        Orders orders = new Orders();
        orders.setStatus(false);
        orders.setUserId((currentUser1.getId()));
        String query = "insert into orders  (status,user_id) values(?,?) returning id";
        Integer id = 0;
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            preparedStatement.setBoolean(1, orders.isStatus());
            preparedStatement.setObject(2, orders.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            return id;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deliverById(Integer id) {
        String query = "update orders set deliver=? where id=?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteById(int id) {
        String query = "delete from orders where id=?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void setStatusById(int id) {
        String query = "update orders set status=? where id=?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
