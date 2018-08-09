package moneyManager.model;

import moneyManager.util.CostsUtil;

import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Vladimir on 08.08.2018.
 */
public class User extends NamedEntity {
    private String email;

    private String password;

    private boolean enabled = true;

    private Date registered = new Date();

    private Set<Role> roles;

    private int sumPerDay = CostsUtil.DEFAULT_SUM_PER_DAY;

    public User() {
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, CostsUtil.DEFAULT_SUM_PER_DAY, true, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, int sumPerDay, boolean enabled, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.sumPerDay = sumPerDay;
        this.enabled = enabled;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getSumPerDay() {
        return sumPerDay;
    }

    public void setSumPerDay(int sumPerDay) {
        this.sumPerDay = sumPerDay;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", sumPerDay=" + sumPerDay +
                ')';
    }
}
