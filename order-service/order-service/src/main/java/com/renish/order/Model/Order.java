package com.renish.order.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "t_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @GeneratedValue(strategy = GenerationType.AUTO)
        @Id
        private  Long id;
        private String orderNumber;
        @OneToMany(cascade = CascadeType.ALL)
        private List<OrderLineItems> orderLineItemsList;
}

