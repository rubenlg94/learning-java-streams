package factories;

import models.OrderItemModel;
import models.OrderModel;
import models.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class StreamsFactory {

    public static List<ProductModel> PRODUCT_LIST;
    public static List<OrderModel> ORDER_LIST;

    public static void init() {
        PRODUCT_LIST = generateProducts();
        ORDER_LIST = generateOrders(PRODUCT_LIST);
        relateOrdersAndProducts(ORDER_LIST, PRODUCT_LIST);
    }

    private static List<ProductModel> generateProducts() {
        List<ProductModel> productModels = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ProductModel productModel = new ProductModel(i);
            if (i % 2 == 0) {
                productModel.setName(String.format("Pair product: %d", i));
            } else {
                productModel.setName("Odd product");
            }
            productModel.setPrice((Math.random() + 1) * 100);
            productModels.add(productModel);
        }
        return productModels;
    }

    private static List<OrderModel> generateOrders(List<ProductModel> productModels) {
        List<OrderModel> orderModels = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            OrderModel orderModel = new OrderModel(i);
            if (i % 2 == 0) {
                orderModel.setClientName(String.format("Pair client: %d", i));
            } else {
                orderModel.setClientName("Odd client");
            }
            orderModels.add(orderModel);
        }
        return orderModels;
    }

    private static void relateOrdersAndProducts(List<OrderModel> orderModels, List<ProductModel> productModels) {
        for (int i = 0; i < orderModels.size(); i++) {
            OrderModel orderModel = orderModels.get(i);
            List<OrderItemModel> orderItemModels = new ArrayList<>();
            double total = 0;
            int distinctProducts = (int) (Math.random() * 5) + 1;
            for (int j = 0; j < distinctProducts; j++) {
                int productId = (int) (Math.random() * productModels.size());
                int quantity = (int) (Math.random() * 3) + 1;
                OrderItemModel orderItemModel = new OrderItemModel(orderModel.getId(), productId, quantity);
                orderItemModels.add(orderItemModel);
                total += (productModels.get(productId).getPrice() * quantity);
            }
            orderModel.setOrderItemModels(orderItemModels);
            orderModel.setTotal(i % 2 == 0 ? 100 : total);
        }
    }

}
