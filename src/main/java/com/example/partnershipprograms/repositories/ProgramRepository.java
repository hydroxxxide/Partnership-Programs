package com.example.partnershipprograms.repositories;

import com.example.partnershipprograms.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    @Query(value = "SELECT program_id FROM subscribes WHERE user_id = ?", nativeQuery = true)
    List<Long> findProgramsIdByUserId(@Param("userId") Long userId);

}
