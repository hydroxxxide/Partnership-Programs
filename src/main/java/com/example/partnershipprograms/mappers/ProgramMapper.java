package com.example.partnershipprograms.mappers;

import com.example.partnershipprograms.dto.ProgramDTO;
import com.example.partnershipprograms.models.Program;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProgramMapper {
    private final ModelMapper mapper;

    public ProgramMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ProgramDTO convertToDTO(Program program){
        return mapper.map(program, ProgramDTO.class);
    }

    public List<ProgramDTO> convertToDTOList(List<Program> programs){
        List<ProgramDTO> programDTOList = new ArrayList<>();
        for (Program p:programs) {
            programDTOList.add(convertToDTO(p));
        }
        return programDTOList;
    }

    public Program convertToEntity(ProgramDTO dto){
        return mapper.map(dto, Program.class);
    }
}
