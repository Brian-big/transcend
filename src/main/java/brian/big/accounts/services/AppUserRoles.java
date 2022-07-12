package brian.big.accounts.services;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

import static brian.big.accounts.services.AppUserPermissions.*;

public enum AppUserRoles {
    ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE)),
    STUDENT(Sets.newHashSet(STUDENT_READ)),
    DEVELOPER(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE));

    private final Set<AppUserPermissions> permissions;

    AppUserRoles(Set<AppUserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermissions> getPermissions() {
        return permissions;
    }
}
