package tableordering.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import tableordering.OrderApplication;
import tableordering.domain.OrderCancelledEvent;
import tableordering.domain.OrderConfirmedEvent;
import tableordering.domain.OrderPlacedEvent;

@Entity
@Table(name = "Order_table")
@Data
//<<< DDD / Aggregate Root
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    @ElementCollection
    private List<Long> menuId;

    private Integer qty;

    private Date createdAt;

    private Date updatedAt;

    private String orderStatus;

    @PostPersist
    public void onPostPersist() {
        OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(this);
        orderPlacedEvent.publishAfterCommit();

        OrderCancelledEvent orderCancelledEvent = new OrderCancelledEvent(this);
        orderCancelledEvent.publishAfterCommit();

        OrderConfirmedEvent orderConfirmedEvent = new OrderConfirmedEvent(this);
        orderConfirmedEvent.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    public void menuList(MenuListCommand menuListCommand) {
        tableordering.external.MenuListQuery menuListQuery = new tableordering.external.MenuListQuery();
        OrderApplication.applicationContext
            .getBean(tableordering.external.MenuService.class)
            .menuList(menuListQuery);
    }

    //<<< Clean Arch / Port Method
    public static void updateStatusPolicy(OutOfStockEvent outOfStockEvent) {
        //implement business logic here:

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(outOfStockEvent.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updateStatusPolicy(
        PaymentCompleteEvent paymentCompleteEvent
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCompleteEvent.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
