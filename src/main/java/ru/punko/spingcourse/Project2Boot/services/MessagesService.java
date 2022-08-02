package ru.punko.spingcourse.Project2Boot.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.punko.spingcourse.Project2Boot.models.Message;
import ru.punko.spingcourse.Project2Boot.models.User;
import ru.punko.spingcourse.Project2Boot.repositories.MessagesRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MessagesService {

    private final MessagesRepository messagesRepository;
    private final UsersService usersService;

    public MessagesService(MessagesRepository messagesRepository, UsersService usersService) {
        this.messagesRepository = messagesRepository;
        this.usersService = usersService;
    }

    public List<Message> findByRecipient(String name) {
        return messagesRepository.findByRecipientOrderByDateDesc(name);
    }

    @Transactional
    public void save(Message message) {
        message.setDate(new Date());
        messagesRepository.save(message);
    }

    public List<Message> findByRecipientNewMessage(String name) {
        List<Message> newMessage = new ArrayList<>();
        for (Message m : findByRecipient(name)) {
            if (new Date().getTime() - m.getDate().getTime() < 5000)
                newMessage.add(m);
        }
        return newMessage;
    }
}
