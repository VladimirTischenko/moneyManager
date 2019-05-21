package moneyManager;

/**
 * Created by Vladimir on 29.04.2019.
 */
public interface HasId {
    Integer getId();

    void setId(Integer id);

    default boolean isNew() {
        return (getId() == null);
    }
}
