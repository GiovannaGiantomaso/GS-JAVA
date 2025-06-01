package br.fiap.ajuda.messaging;

import br.fiap.ajuda.model.TipoAjuda;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static br.fiap.ajuda.config.RabbitMQConfig.*;

@Component
@RequiredArgsConstructor
public class TipoAjudaProducer {

    private final RabbitTemplate rabbitTemplate;

    public void enviarTipoAjudaCriado(TipoAjuda tipoAjuda) {
        System.out.println("Enviando TipoAjuda criado para a fila: " + tipoAjuda);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, tipoAjuda);
    }
}
