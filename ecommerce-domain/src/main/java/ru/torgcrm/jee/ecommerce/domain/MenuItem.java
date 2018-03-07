package ru.torgcrm.jee.ecommerce.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Ilya Durdyev, funbanji@gmail.com
 */
@Entity
@Table(name = "menu_item")
public class MenuItem extends GenericEntity {
    public static final String GEN_NAME = "Gen_Menu_Item";
    public static final String SEQ_NAME = "Seq_Menu_Item";

    @Id
    @SequenceGenerator(sequenceName = SEQ_NAME, name = GEN_NAME)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GEN_NAME)
    private Long id;

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    @ManyToOne(targetEntity = Menu.class)
    private Menu menu;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", menu=" + menu +
                '}';
    }
}