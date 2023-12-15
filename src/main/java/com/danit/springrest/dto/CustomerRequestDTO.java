package com.danit.springrest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    @Max(value = 2, message = "name cannot be blank > 2 words")
    private String name;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "Phone Numbers cannot be blank")
    private String phoneNumbers;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    private List<AccountRequestDTO> accounts;
    private List<EmployerRequestDTO> employers;


}
