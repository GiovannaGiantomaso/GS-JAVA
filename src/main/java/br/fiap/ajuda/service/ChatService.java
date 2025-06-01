package br.fiap.ajuda.service;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    @Autowired
    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String enviarMensagem(String mensagem) {
        try {
            return chatClient.prompt(mensagem).call().content();
        } catch (Exception e) {
            return "Ocorreu um erro ao consultar a IA. Tente novamente mais tarde.";
        }
    }

}
