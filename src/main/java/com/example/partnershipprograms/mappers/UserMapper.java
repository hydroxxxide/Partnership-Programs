package com.example.partnershipprograms.mappers;

import com.example.partnershipprograms.dto.UserDTO;
import com.example.partnershipprograms.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper mapper;

    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public UserDTO convertToDTO(User user){
        return mapper.map(user, UserDTO.class);
    }

}
