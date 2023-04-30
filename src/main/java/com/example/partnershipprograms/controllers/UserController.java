package com.example.partnershipprograms.controllers;

import com.example.partnershipprograms.dto.ProgramDTO;
import com.example.partnershipprograms.dto.UserDTO;
import com.example.partnershipprograms.mappers.ProgramMapper;
import com.example.partnershipprograms.mappers.UserMapper;
import com.example.partnershipprograms.models.User;
import com.example.partnershipprograms.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;
    private final ProgramMapper programMapper;
    public UserController(UserService service, UserMapper mapper, ProgramMapper programMapper) {
        this.service = service;
        this.mapper = mapper;
        this.programMapper = programMapper;
    }

    @PostMapping("/create")
    public UserDTO create(@RequestBody User user){
        return mapper.convertToDTO(service.create(user));
    }

    @GetMapping("/get/{id}")
    public UserDTO getById(@PathVariable Long id){
        return mapper.convertToDTO(service.getById(id));
    }

    @PutMapping("/update")
    public UserDTO update(@RequestBody User u1){
        return mapper.convertToDTO(service.update(u1));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/sub/{userId}/{programId}")
    public void subscribe(@PathVariable Long userId, @PathVariable Long programId) throws Exception {
        service.subscribeUserToProgram(userId, programId);
    }

    @PutMapping("/unsub/{userId}/{programId}")
    public void unsubscribe(@PathVariable Long userId, @PathVariable Long programId){
        service.unsubscribeUserFromProgram(userId, programId);
    }

    @GetMapping("/all/{id}")
    public List<ProgramDTO> getAllProgramsByUserId(@PathVariable Long id){
        return programMapper.convertToDTOList(service.getProgramsByUserId(id));
    }
}
