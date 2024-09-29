package org.example.forum.service;

import org.example.forum.repository.MessageRepository;
import org.example.forum.entity.Message;
import org.springframework.data.domain.Page; // Pagination
import org.springframework.data.domain.PageRequest; // Pagination
import org.springframework.data.domain.Pageable; // Pagination
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    // ========== Propriétés ==========

    private final MessageRepository messageRepository;


    // ========== Constructeur ==========

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    // ========== Méthodes ==========

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

//    public List<Message> getAllMessages() {
//        return messageRepository.findAll();
//    }

    public List<Message> getMessagesBySujetId(int sujetId) {
        return messageRepository.findBySujetId(sujetId);
    }

    /**
     * Obtenir le dernier message d'un sujet (pour afficher ses infos)
     * @return Message
     */
    public Message getLastMessageBySujetId(int sujetId) {
        return messageRepository.findTopBySujetIdOrderByDatetimeDesc(sujetId);
    }

    /**
     * Obtenir le dernier message du forum
     * @return Message
     */
    public Message getLatestMessage() {
        return messageRepository.findLatestMessage();
    }

    // ----- Pagination -----

    public Page<Message> getMessagesBySujetId(int sujetId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return messageRepository.findBySujetId(sujetId, pageable);
    }

    // ----- Compter nombre de messages -----

    public long countAllMessages() {
        return messageRepository.count();
    }


}
