package moneyManager.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by Vladimir on 08.08.2018.
 */
@MappedSuperclass
public class NamedEntity extends BaseEntity {
    @NotBlank
    @Column(name = "name", nullable = false)
    protected String name;

    NamedEntity() {
    }

    NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
