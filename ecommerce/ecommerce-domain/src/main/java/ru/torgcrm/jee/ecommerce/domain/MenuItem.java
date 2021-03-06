package ru.torgcrm.jee.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;

/**
 * Menu item
 * See also {@link MenuItem}
 *
 * @author Ilya Durdyev, funbanji@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "menu_items")
public class MenuItem extends AbstractProjectEntity {
    public static final String GEN_NAME = "Gen_Menu_Items";
    public static final String SEQ_NAME = "Seq_Menu_Items";

    @Id
    @SequenceGenerator(sequenceName = SEQ_NAME, name = GEN_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GEN_NAME)
    private Long id;
    @Setter
    private String name;
    private String code;
    @ManyToOne(targetEntity = Menu.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Menu menu;
    private String link;

    @Override
    public Long getId() {
        return this.id;
    }
}
