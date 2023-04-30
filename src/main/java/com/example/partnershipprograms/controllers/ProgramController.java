package com.example.partnershipprograms.controllers;

import com.example.partnershipprograms.dto.ProgramDTO;
import com.example.partnershipprograms.mappers.ProgramMapper;
import com.example.partnershipprograms.models.Program;
import com.example.partnershipprograms.services.ProgramService;
import com.example.partnershipprograms.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/program")
public class ProgramController {
    private final ProgramService service;
    private final ProgramMapper mapper;

    public ProgramController(ProgramService service, ProgramMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public ProgramDTO create(@RequestBody Program program){
        return mapper.convertToDTO(service.create(program));
    }

    @GetMapping("/get/{id}")
    public ProgramDTO getById(@PathVariable Long id){
        return mapper.convertToDTO(service.getById(id));
    }

    @PutMapping("/update")
    public ProgramDTO update(@RequestBody Program p1){
        return mapper.convertToDTO(service.update(p1));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/all")
    public List<ProgramDTO> getAll(){
        return mapper.convertToDTOList(service.getAllPrograms());
    }

    @GetMapping("/popular")
    public List<ProgramDTO> getPopularPrograms(){
        return mapper.convertToDTOList(service.sortByPopularity());
    }

    @PutMapping("/ban/{userId}/{programId}")
    public void banUser(@PathVariable Long userId, @PathVariable Long programId){
        service.banUser(userId, programId);
    }
}
