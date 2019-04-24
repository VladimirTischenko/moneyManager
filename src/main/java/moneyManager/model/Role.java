package moneyManager.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Vladimir on 08.08.2018.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
