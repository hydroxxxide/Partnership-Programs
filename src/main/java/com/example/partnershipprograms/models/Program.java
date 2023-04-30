package com.example.partnershipprograms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "programs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToMany(mappedBy = "programs")
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "black_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "program_id"})}
    )
    private List<User> blackList;
}
