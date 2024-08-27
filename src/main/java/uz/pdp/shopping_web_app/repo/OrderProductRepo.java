package uz.pdp.shopping_web_app.repo;

import uz.pdp.shopping_web_app.config.ConnectionPoolManager;
import uz.pdp.shopping_web_app.entity.BasketProduct;
import uz.pdp.shopping_web_app.entity.OrderProduct;
import uz.pdp.shopping_web_app.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderProductRepo {
    public static List<OrderProduct> findAll() {
        String query = "select * from order_product";
        List<OrderProduct> orderProducts = new ArrayList<>();
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderProducts.add(new OrderProduct(resultSet.getInt("amount"), UUID.fromString(resultSet.getString("product_id")), resultSet.getInt("orderId")));
            }
            return orderProducts;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(OrderProduct orderProduct) {
        String query = "insert into order_product() values (?,?,?)";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, orderProduct.getAmount());
            preparedStatement.setObject(2, orderProduct.getProductId());
            preparedStatement.setInt(3, orderProduct.getOrderId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<BasketProduct> readProductsFromBasketProducts(UUID id) {
        return BasketProductRepo.findByBasketId(id);
    }

    public static void putProductsFromBasketProduct(List<BasketProduct> basketProducts, Integer id) {

        String query = "INSERT INTO order_product() values (?,?,?)";
        try (
                Connection connection = ConnectionPoolManager.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            List<Product> products = ProductRepo.findAll();
            for (BasketProduct basketProduct : basketProducts) {
                for (Product product : products) {
                    if (basketProduct.getProductId().equals(product.getId())) {
                        preparedStatement.setInt(1, basketProduct.getAmount());
                        preparedStatement.setObject(2, product.getId());
                        preparedStatement.setInt(3, id);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearDeliveredOrderProductsByOrderId(Integer id) {
        String query = "delete from order_product where order_id=?";
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

}
