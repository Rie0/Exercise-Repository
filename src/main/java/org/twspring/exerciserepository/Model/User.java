package org.twspring.exerciserepository.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity@Data@AllArgsConstructor@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(25) not null")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min=5, max=25, message = "Name must have between 5 and 25 characters")
    private String name;

    @Column(columnDefinition = "varchar(25) not null unique")
    @NotEmpty(message = "Username cannot be empty")
    @Size(min=5, max=25, message = "Username must have between 5 and 25 characters")
    private String username;

    @Column(columnDefinition = "varchar(25) not null unique")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Size(min=7, max=25, message = "Email must have between 7 and 25 characters")
    private String email;

    @Column(columnDefinition = "varchar(25) not null")
    @NotEmpty(message = "Password cannot be empty")
    @Size(min=8, max=25, message = "Password must have between 8 and 25 characters")
    private String password;

    @Column(columnDefinition = "varchar(25) not null")
    @NotEmpty(message = "Role cannot be empty")
    @Pattern(regexp = "^(User|Admin)$")
    @Size(min=4, max=25, message = "Role must have between 4 and 25 characters")
    private String role;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "Age cannot be empty")
    @Positive(message = "Age cannot be a negative number or a zero")
    private Integer age;

}
