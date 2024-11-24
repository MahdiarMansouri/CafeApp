//package test;
//
//import model.entity.*;
//import model.entity.enums.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class entityTest {
//    public static void main(String[] args) {
//
//        User user = User.builder()
//                .user_id(1)
//                .username("mym")
//                .password("mym123")
//                .role(Role.Admin)
//                .build();
//
//
//        Person person = Person.builder()
//                .id(1)
//                .name("mahdiar")
//                .family("mansouri")
//                .nationalId("0022667441")
//                .phoneNumber("09126997377")
//                .gender(Gender.MALE)
//                .user(user)
//                .build();
//
//        System.out.println(user);
//        System.out.println(person);
//
//        Item item1 = Item.builder()
//                .itemId(1)
//                .name("Late")
//                .description("Coffee with milk by 70%")
//                .price(70000)
//                .category(Category.Coffee)
//                .isAvailable(true)
//                .build();
//
//        Item item2 = Item.builder()
//                .itemId(2)
//                .name("Espresso")
//                .description("Coffee ")
//                .price(90000)
//                .category(Category.Coffee)
//                .isAvailable(true)
//                .build();
//
//        Item item3 = Item.builder()
//                .itemId(3)
//                .name("Cheese Cake")
//                .description("Cheese Cake with Cream")
//                .price(170000)
//                .category(Category.Cake)
//                .isAvailable(true)
//                .build();
//        System.out.println(item1);
//        System.out.println(item2);
//        System.out.println(item3);
//
//        Customer customer = Customer.builder()
//                .customerId(1)
//                .firstName("ali")
//                .lastName("alipour")
//                .phoneNumber("09213167115")
//                .build();
//
//        System.out.println(customer);
//
//        List<Item> itemList = new ArrayList<>();
//        itemList.add(item1);
//        itemList.add(item2);
//        itemList.add(item3);
//
//        Order order = Order.builder()
//                .orderId(1)
//                .customer(customer)
//                .status(OrderStatus.Closed)
//                .products(itemList)
//                .build();
//
//        order.calculateTotalPrice();
//        System.out.println(order);
//
//        Payment payment = Payment.builder()
//                .paymentId(1)
//                .paymentMethod(PaymentMethod.CreditCard)
//                .amount(300000)
//                .paymentStatus(PaymentStatus.Completed)
//                .order(order)
//                .build();
//
//        System.out.println(payment);
//
//
//
//    }
//
//}
