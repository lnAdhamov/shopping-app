package uz.pdp.shopping_web_app.repo;

import uz.pdp.shopping_web_app.config.ConnectionPoolManager;
import uz.pdp.shopping_web_app.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasketProductRepo {
    public static void save(BasketProduct basketProduct) {
        String query = "insert into basket_product(product_id,basket_id,amount) values (?,?,?)";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setObject(1, basketProduct.getProductId());
            preparedStatement.setObject(2, basketProduct.getBasketId());
            preparedStatement.setInt(3, basketProduct.getAmount());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<BasketProduct> findAll() {
        String query = "select * from basket_product";
        List<BasketProduct> basketProducts = new ArrayList<>();
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                basketProducts.add(new BasketProduct(UUID.fromString(resultSet.getString("product_id")), UUID.fromString(resultSet.getString("basket_id")), resultSet.getInt("amount")));
            }
            return basketProducts;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static List<BasketProduct> findByBasketId(UUID id) {
        List<BasketProduct> basketProducts = new ArrayList<>();
        String query = "select * from basket_product where basket_id=?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                basketProducts.add(new BasketProduct(UUID.fromString(resultSet.getString("product_id")), UUID.fromString(resultSet.getString("basket_id")), resultSet.getInt("amount")));
            }
            return basketProducts;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteProductById(UUID id) {

        String query = "delete from basket_product where product_id=?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            UUID deletingId = null;
            List<BasketProduct> bps = findAll();
            List<Product> products = ProductRepo.findAll();
            for (BasketProduct bp : bps) {
                for (Product product : products) {
                    if (bp.getProductId().equals(product.getId())) {
                        deletingId = product.getId();
                    }
                }
            }
            preparedStatement.setObject(1, deletingId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void clearOldBasketProducts(UUID id) {
        List<BasketProduct> basketProducts = findAll();

        String query = "delete from basket_product where basket_id=?";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            for (BasketProduct basketProduct : basketProducts) {
                if (basketProduct.getBasketId().equals(id)) {
                    preparedStatement.setObject(1, basketProduct.getBasketId());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    ;
}
