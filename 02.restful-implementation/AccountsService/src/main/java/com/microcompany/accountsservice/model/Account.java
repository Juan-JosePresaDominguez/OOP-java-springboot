package com.microcompany.accountsservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microcompany.accountsservice.constraints.ValidDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long id;

    //@Column(name = "tipo") // Ahorro o corriente
    @NotBlank
    @Size(min = 3, max = 20)
    //@Pattern(regexp = "")
    private String type;

    @DateTimeFormat
    @NotNull
    //@ValidDate
    Date openingDate;

    @Min(0)
    private int balance;

    @Min(1)
    private Long ownerId;

    @Transient
    @JsonIgnore // Hace que no aparezca el campo anotado con @Transient en las peticiones RESTer
    Customer owner;

}
