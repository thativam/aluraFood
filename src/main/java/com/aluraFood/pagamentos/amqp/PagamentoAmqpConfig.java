package com.aluraFood.pagamentos.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoAmqpConfig {
    @Bean
    public Queue pagamentoQueue() {
        return new Queue("pagamento.concluido", false);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializa(RabbitAdmin rabbitAdmin) {
        return event -> {
            rabbitAdmin.initialize();
        };
        
    }
}
