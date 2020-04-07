package andersen.lab.orderservice.messaging;

import andersen.lab.orderservice.domain.OrderItem;
import andersen.lab.orderservice.messaging.dto.OrderItemDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockMessageSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void reduceProductStock(OrderItem orderItem) {
        amqpTemplate.convertAndSend("${jsa.rabbitmq.queue}",
                new OrderItemDTO(orderItem.getProductId(), orderItem.getAmount()));
    }

}
