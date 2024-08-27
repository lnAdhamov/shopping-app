package uz.pdp.shopping_web_app.repo;

import uz.pdp.shopping_web_app.config.ConnectionPoolManager;
import uz.pdp.shopping_web_app.entity.Category;
import uz.pdp.shopping_web_app.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductRepo {
    public static List<Product> findAll() {
        String query = "select * from product order by id ";
        List<Product> products = new ArrayList<>();
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(UUID.fromString(resultSet.getString("id")), resultSet.getString("name"), resultSet.getInt("price"), UUID.fromString(resultSet.getString("category_id")), resultSet.getBytes("photo")));
            }
            return products;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(Product product) {
        String query = "insert into product(name,price,category_id,photo) values (?,?,?,?)";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setObject(3, product.getCategoryId());
            preparedStatement.setBytes(4, product.getPhoto());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Product findById(UUID id) {
        String query = "select * from product where id=?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Product(UUID.fromString(resultSet.getString("id")), resultSet.getString("name"), resultSet.getInt("price"), UUID.fromString(resultSet.getString("category_id")), resultSet.getBytes("photo"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void edit(Product product) {
        String query = "update product set name=?,price=?,category_id=?,photo=? where id=?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setObject(3, product.getCategoryId());
            preparedStatement.setBytes(4, product.getPhoto());
            preparedStatement.setObject(5, product.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteById(UUID id) {
        String query = "delete from product where id=?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
