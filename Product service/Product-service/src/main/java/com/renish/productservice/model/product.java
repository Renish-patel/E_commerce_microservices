package com.renish.productservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "product")
@Entity
public class product {
   @GeneratedValue()
   @Id
   int id;
   String name;
   String description;
   BigDecimal price;
}
