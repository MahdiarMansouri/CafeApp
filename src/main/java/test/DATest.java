package test;

import model.bl.*;
import model.entity.*;
import model.entity.enums.*;
import model.tools.ConnectionProvider;

import java.util.ArrayList;
import java.util.List;

public class DATest {
    public static void main(String[] args) throws Exception {
        User user = User.builder()
                .username("mahd")
                .password("mym123")
                .role(Role.Admin)
                .build();


        Person person = Person.builder()
                .name("mahdiar")
                .family("mansouri")
                .nationalId("0022667441")
                .phoneNumber("09126997377")
                .gender(Gender.MALE)
                .user(user)
                .build();

        System.out.println(user);
        System.out.println(person);

        Item item1 = Item.builder()
                .name("Late")
                .description("Coffee with milk by 70%")
                .price(70000)
                .category(Category.Coffee)
                .isAvailable(true)
                .build();

        Item item2 = Item.builder()
                .name("Espresso")
                .description("Coffee ")
                .price(90000)
                .category(Category.Coffee)
                .isAvailable(true)
                .build();

        Item item3 = Item.builder()
                .name("Cheese Cake")
                .description("Cheese Cake with Cream")
                .price(170000)
                .category(Category.Cake)
                .isAvailable(true)
                .build();
        System.out.println(item1);
        System.out.println(item2);
        System.out.println(item3);

        Customer customer = Customer.builder()
                .firstName("ali")
                .lastName("alipour")
                .phoneNumber("09213167115")
                .build();

        System.out.println(customer);

        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        Order order = Order.builder()
                .customer(customer)
                .status(OrderStatus.Closed)
                .products(itemList)
                .build();

        order.calculateTotalPrice();
        System.out.println(order);

        Payment payment = Payment.builder()
                .paymentMethod(PaymentMethod.CreditCard)
                .amount(300000)
                .paymentStatus(PaymentStatus.Completed)
                .order(order)
                .build();

        System.out.println(payment);
        System.out.println("------------------------------------------");

//        System.out.println("Fetching next sequence value...");
//        int paymentId = ConnectionProvider.getConnectionProvider().getNextId("PAYMENT_SEQ");
//        System.out.println("Fetched Payment ID: " + paymentId);
//        PaymentBL paymentBL = new PaymentBL();
//        paymentBL.save(payment);
//        System.out.println("Payments Saved");


        UserBL userBL = new UserBL();
        userBL.save(user);
        System.out.println("User Saved");

        PersonBL personBL = new PersonBL();
        personBL.save(person);
        System.out.println("Person Saved");

        ItemBL itemBL = new ItemBL();
        itemBL.save(item1);
        itemBL.save(item2);
        itemBL.save(item3);
        System.out.println("Items Saved");

        CustomerBL customerBL = new CustomerBL();
        customerBL.save(customer);
        System.out.println("Customer Saved");

        OrderBL orderBL = new OrderBL();
        orderBL.save(order);
        System.out.println("Orders Saved");

        PaymentBL paymentBL = new PaymentBL();
        paymentBL.save(payment);
        System.out.println("Payments Saved");

    }

}
