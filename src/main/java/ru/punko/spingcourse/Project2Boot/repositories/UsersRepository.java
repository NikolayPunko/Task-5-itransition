package ru.punko.spingcourse.Project2Boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.punko.spingcourse.Project2Boot.models.User;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

    Optional<User> findByName(String name);
}
