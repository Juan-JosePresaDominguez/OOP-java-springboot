package com.microcompany.productsservice.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.microcompany.productsservice.constraints.SerialNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(name = "product", description = "Modelo producto")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    @Schema(name = "Product ID", example = "1", required = false)
    private Long id;

    @Column
    @NotBlank
    @Size(min = 3, max = 20)
    @Schema(name = "Product name", example = "Escoba 345", required = true)
    private String name;

    @Column
    @NotBlank
//    @Pattern(regexp = "[0-9]{3}-[0-9]{3}-[0-9]{4}")   // 123-123-1234
    @SerialNumber
    @Schema(name = "Product serial number", example = "234-567-8912", required = true)
    private String serial;
}
