package moneyManager.to;

import moneyManager.util.HasId;
import moneyManager.util.UserUtil;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserTo implements HasId, Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Size(min = 5, max = 64, message = " must between 5 and 64 characters")
    private String password;

    @Range(min = 10, max = 10000)
    @NotNull
    private Integer sumPerDay = UserUtil.DEFAULT_SUM_PER_DAY;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password, int sumPerDay) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.sumPerDay = sumPerDay;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    public Integer getSumPerDay() {
        return sumPerDay;
    }

    public void setSumPerDay(Integer sumPerDay) {
        this.sumPerDay = sumPerDay;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sumPerDay='" + sumPerDay + '\'' +
                '}';
    }
}
