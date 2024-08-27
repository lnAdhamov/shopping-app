package uz.pdp.shopping_web_app.repo;

import uz.pdp.shopping_web_app.config.ConnectionPoolManager;
import uz.pdp.shopping_web_app.entity.Basket;
import uz.pdp.shopping_web_app.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasketRepo {


    public static List<Basket> findAll() {
        String query = "select * from basket order by id";
        List<Basket> baskets = new ArrayList<>();
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                baskets.add(new Basket(UUID.fromString(resultSet.getString("id")), UUID.fromString(resultSet.getString("user_id") == null ? "d4f2e0a8-9b7d-4c90-ae5a-7b17c2396a45" : resultSet.getString("user_id"))));
            }


            return baskets;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void save() {
        String query = "insert into basket(user_id) values (?)";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setObject(1, UUID.randomUUID());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static Basket findActiveBasket() {
        return findAll().get(0);
    }


}
