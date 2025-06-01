package br.fiap.ajuda.messaging;

import br.fiap.ajuda.model.TipoAjuda;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static br.fiap.ajuda.config.RabbitMQConfig.QUEUE_NAME;

@Component
public class TipoAjudaConsumer {

    @RabbitListener(queues = QUEUE_NAME)
    public void consumirMensagemTipoAjuda(TipoAjuda tipoAjuda) {
        System.out.println("[RabbitMQ] TipoAjuda criado recebido na fila:");
        System.out.println("   → ID: " + tipoAjuda.getId());
        System.out.println("   → Nome: " + tipoAjuda.getNome());
        System.out.println("   → Descrição: " + tipoAjuda.getDescricao());
    }
}
