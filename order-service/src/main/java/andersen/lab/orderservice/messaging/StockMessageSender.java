package andersen.lab.orderservice.messaging;

import andersen.lab.orderservice.config.RabbitMQConfig;
import andersen.lab.orderservice.domain.OrderItem;
import andersen.lab.orderservice.messaging.dto.OrderItemDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void reduceProductStock(OrderItem orderItem) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY,
                new OrderItemDTO(orderItem.getProductId(), orderItem.getAmount()));
    }

}
