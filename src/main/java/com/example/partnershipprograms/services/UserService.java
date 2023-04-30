package com.example.partnershipprograms.services;

import com.example.partnershipprograms.models.Program;
import com.example.partnershipprograms.models.User;
import com.example.partnershipprograms.repositories.ProgramRepository;
import com.example.partnershipprograms.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class UserService {
    private final UserRepository repository;
    private final ProgramRepository programRepository;

    public UserService(UserRepository repository, ProgramRepository programRepository) {
        this.repository = repository;
        this.programRepository = programRepository;
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

    public void subscribeUserToProgram(Long userId, Long programId) {
        try {
            User user = getById(userId);
            Program program = programRepository.findById(programId).orElse(null);
            assert program != null;
            List<User> blackList = program.getBlackList();
            for (User u : blackList){
                if (u.equals(user)){
                    System.out.println("---------------------------ОШИБКА---------------------------" +
                            "\nПользователь с именем " + user.getName()
                            + " находится в чёрном списке программы " + program.getTitle() + "\nПодписка невозможна");
                    return;
                }
            }
            user.getPrograms().add(program);
            program.getUsers().add(user);
            update(user);
            programRepository.save(program);
        } catch (Exception e) {
            System.out.println("---------------------------ОШИБКА---------------------------" +
                    "\nПользователь с именем " +
                    getById(userId).getName() +
                    " уже подписан на программу "
                    + Objects.requireNonNull(programRepository.findById(programId).orElse(null)).getTitle());
        }

    }

    public void unsubscribeUserFromProgram(Long userId, Long programId) {
        User user = getById(userId);
        Program program = programRepository.findById(programId).orElse(null);
        if (user.getPrograms().size() == 0){
            assert program != null;
            System.out.println("Пользователь с именем - " + user.getName() +
                    " не подписан на программу " + program.getTitle());
        }else {
            user.getPrograms().remove(program);
            update(user);
            assert program != null;
            programRepository.save(program);
        }
    }

    public List<Program> getProgramsByUserId(Long id) {
        List<Long> programsId = programRepository.findProgramsIdByUserId(id);
        List<Program> programs = new ArrayList<>();
        for (Long p:programsId) {
            programs.add(programRepository.findById(p).orElse(null));
        }
        return programs;
    }

}
