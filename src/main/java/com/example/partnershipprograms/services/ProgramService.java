package com.example.partnershipprograms.services;

import com.example.partnershipprograms.models.Program;
import com.example.partnershipprograms.models.User;
import com.example.partnershipprograms.repositories.ProgramRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProgramService {
    private final ProgramRepository repository;
    private final UserService service;

    public ProgramService(ProgramRepository repository, UserService service) {
        this.repository = repository;
        this.service = service;
    }

    public Program create(Program program){
        return repository.save(program);
    }

    public Program getById(Long id){
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Program update(Program p1){
        Program p2 = getById(p1.getId());
        p2.setTitle(p1.getTitle());
        p2.setDescription(p1.getDescription());
        return repository.save(p2);
    }

    public List<Program> getAllPrograms(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Program> sortByPopularity() {
        List<Program> programs = getAllPrograms();
        return programs.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getUsers().size(), p1.getUsers().size()))
                .collect(Collectors.toList());
    }

    public void banUser(Long userId, Long programId){
        try {
            Program p = getById(programId);
            User u = service.getById(userId);
            assert p != null;
            p.getBlackList().add(u);
            p.getUsers().remove(u);
            u.getPrograms().remove(p);
            update(p);
            service.update(u);
        }catch (Exception e){
            System.out.println("---------------------------ОШИБКА---------------------------" +
                    "\nПользователь с именем " +
                    service.getById(userId).getName() +
                    " уже заблокирован программой " + getById(programId).getTitle());
        }
    }
}
