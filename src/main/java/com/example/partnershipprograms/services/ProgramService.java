package com.example.partnershipprograms.services;

import com.example.partnershipprograms.models.Program;
import com.example.partnershipprograms.repositories.ProgramRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramService {
    private final ProgramRepository repository;

    public ProgramService(ProgramRepository repository) {
        this.repository = repository;
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

    public List<Program> getAllProgramsByUserId(Long id){
       List<Long> programsId = repository.findProgramsIdByUserId(id);
       List<Program> programs = new ArrayList<>();
        for (Long p:programsId) {
            programs.add(getById(p));
        }
        return programs;
    }

    public List<Program> sortByPopularity() {
        List<Program> programs = getAllPrograms();



        return programs.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getUsers().size(), p1.getUsers().size()))
                .collect(Collectors.toList());
    }
}
