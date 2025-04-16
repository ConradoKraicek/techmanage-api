package com.techmanage.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome completo é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String fullName;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "^\\+[0-9]{1,3} [0-9]{1,3} [0-9]{4,5}-[0-9]{4}$",
            message = "Telefone deve ser no formato international (e.g., +55 11 99999-9999)")
    private String phone;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data deve ser no passado")
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @NotNull(message = "Tipo de usuário é obrigatório")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}


