import factories.StreamsFactory;
import models.OrderItemModel;
import models.OrderModel;
import models.ProductModel;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        StreamsFactory.init();
        List<ProductModel> productModels = new ArrayList<>(StreamsFactory.PRODUCT_LIST);
        List<OrderModel> orderModels = new ArrayList<>(StreamsFactory.ORDER_LIST);
        testFilterMethod(productModels);
        testMapMethod(productModels);
        testFlatMapMethod(orderModels);
        testReduceMethod(orderModels);
        testGroupingByMethod(orderModels);
        testSortedMethod(orderModels);
        testSkipAndLimitMethods(orderModels);
        testMaxAndMinMethods(orderModels);
    }


    private static void testFilterMethod(List<ProductModel> productModels) {
        System.out.println("Filtering odd products");
        List<ProductModel> oddProducts = productModels.stream().filter(productModel -> productModel.getName().equals("Odd product")).collect(Collectors.toList());
        System.out.println(oddProducts);
    }

    private static void testMapMethod(List<ProductModel> productModels) {
        System.out.println("Mapping products to their names");
        List<String> productNames = productModels.stream().map(ProductModel::getName).collect(Collectors.toList());
        System.out.println(productNames);
    }

    private static void testFlatMapMethod(List<OrderModel> orderModels) {
        System.out.println("Flatmapping orders to their order items");
        List<Stream> ordersMapped = orderModels.stream().map(orderModel -> orderModel.getOrderItemModels().stream()).collect(Collectors.toList());
        List<OrderItemModel> ordersFlatMapped = orderModels.stream().flatMap(orderModel -> orderModel.getOrderItemModels().stream()).collect(Collectors.toList());
        System.out.println(ordersMapped);
        System.out.println(ordersFlatMapped);
    }

    private static void testReduceMethod(List<OrderModel> orderModels) {
        System.out.println("Reducing orders by their total");
        double total = orderModels.stream().map(OrderModel::getTotal).reduce(0.0, Double::sum);
        System.out.printf("Orders total: %.2f\n", total);
    }

    private static void testGroupingByMethod(List<OrderModel> orderModels) {
        System.out.println("Grouping orders by their total, counting how many orders have the same total");
        Map<Double, Long> ordersTotalsMap = orderModels.stream().collect(Collectors.groupingBy(OrderModel::getTotal, Collectors.counting()));
        System.out.println(ordersTotalsMap);
    }

    private static void testSortedMethod(List<OrderModel> orderModels) {
        System.out.println("Sorting orders by their total");
        List<OrderModel> sortedOrders = orderModels.stream().sorted(Comparator.comparing(OrderModel::getTotal)).collect(Collectors.toList());
        System.out.println(sortedOrders.stream().map(OrderModel::getTotal).collect(Collectors.toList()));
    }

    private static void testSkipAndLimitMethods(List<OrderModel> orderModels) {
        System.out.println("Skipping 6 orders and limiting them to 20");
        System.out.println(orderModels.stream().skip(6).limit(20).map(OrderModel::getId).collect(Collectors.toList()));
    }

    private static void testMaxAndMinMethods(List<OrderModel> orderModels) {
        System.out.println("Getting the orders with max and min total amount");
        Optional<OrderModel> optionalMaxOrderModel = orderModels.stream().max(Comparator.comparing(OrderModel::getTotal));
        Optional<OrderModel> optionalMinOrderModel = orderModels.stream().min(Comparator.comparing(OrderModel::getTotal));
        System.out.println(optionalMaxOrderModel.isPresent() ? optionalMaxOrderModel.get() : "Not found");
        System.out.println(optionalMinOrderModel.isPresent() ? optionalMinOrderModel.get() : "Not found");
    }

}
