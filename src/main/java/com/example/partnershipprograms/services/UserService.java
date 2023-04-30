package com.example.partnershipprograms.services;

import com.example.partnershipprograms.models.Program;
import com.example.partnershipprograms.models.User;
import com.example.partnershipprograms.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepository repository;
    private final ProgramService service;

    public UserService(UserRepository repository, ProgramService service) {
        this.repository = repository;
        this.service = service;
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User getById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User update(User u1) {
        User u2 = getById(u1.getId());
        u2.setName(u1.getName());
        u2.setEmail(u1.getEmail());
        return repository.save(u2);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void subscribeUserToProgram(Long userId, Long programId) throws Exception {
        try {
            User user = getById(userId);
            Program program = service.getById(programId);
            user.getPrograms().add(program);
            program.getUsers().add(user);
            update(user);
            service.update(program);
        } catch (Exception e) {
            System.out.println("Пользователь с именем " +
                    getById(userId).getName() +
                    " уже подписан на программу " + service.getById(programId).getTitle());
        }

    }

    public void unsubscribeUserFromProgram(Long userId, Long programId) {
        User user = getById(userId);
        Program program = service.getById(programId);
        if (user.getPrograms().size() == 0){
            System.out.println("Пользователь с именем - " + user.getName() +
                    " не подписан на программу " + program.getTitle());
        }else {
            user.getPrograms().remove(program);
            update(user);
            service.update(program);
        }
    }

    public List<Program> getProgramsByUserId(Long id) {
        return service.getAllProgramsByUserId(id);
    }
}
