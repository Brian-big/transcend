package brianbig.transcend.entities;

import brianbig.transcend.entities.enums.AppUserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity

public class Role extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private AppUserRole roleName;
    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "roles", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<User> usersWithRole;

    public Role() {
    }


    public Role(String id, LocalDateTime createdAt, LocalDateTime updatedAt, AppUserRole roleName, Set<User> usersWithRole) {
        super(id, createdAt, updatedAt);
        this.roleName = roleName;
        this.usersWithRole = usersWithRole;
    }

    public Role(AppUserRole roleName, Set<User> usersWithRole) {
        this(null, null, null, roleName, usersWithRole);
    }

    public Role(AppUserRole roleName) {
        this(roleName, null);
    }


    public AppUserRole getRoleName() {
        return roleName;
    }

    public void setRoleName(AppUserRole roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsersWithRole() {
        return usersWithRole;
    }

    public void setUsersWithRole(Set<User> usersWithRole) {
        this.usersWithRole = usersWithRole;
    }

    @Override
    public String toString() {
        return "Role{" +
                "Id='" + getId() + '\'' +
                ", roleName=" + roleName +
                '}';
    }
}
