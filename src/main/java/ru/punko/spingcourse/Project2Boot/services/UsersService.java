package ru.punko.spingcourse.Project2Boot.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.punko.spingcourse.Project2Boot.models.User;
import ru.punko.spingcourse.Project2Boot.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll(){
        return usersRepository.findAll();
    }

    @Transactional
    public void save(User user){
        usersRepository.save(user);
    }

    public Optional<User> getUserByName(String name) {
        return usersRepository.findByName(name).stream().findAny();
    }
}
