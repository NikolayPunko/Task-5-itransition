package ru.punko.spingcourse.Project2Boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.punko.spingcourse.Project2Boot.models.Message;

import java.util.List;
import java.util.Optional;


@Repository
public interface MessagesRepository extends JpaRepository<Message, Integer> {

    List<Message> findByRecipientOrderByDateDesc (String recipient);


}
