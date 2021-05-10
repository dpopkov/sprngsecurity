package learn.sprsec.ssia0601web.entities;

import learn.sprsec.ssia0601web.entities.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String price;

    @Enumerated(EnumType.STRING)
    private Currency currency;
}
