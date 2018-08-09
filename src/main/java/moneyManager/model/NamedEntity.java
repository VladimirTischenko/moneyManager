package moneyManager.model;

/**
 * Created by Vladimir on 08.08.2018.
 */
public class NamedEntity extends BaseEntity {
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
