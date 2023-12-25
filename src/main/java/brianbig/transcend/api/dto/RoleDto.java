package brianbig.transcend.api.dto;

import brianbig.transcend.entities.Role;

public record RoleDto(String id, String name) {

    public static RoleDto from(Role role) {
        return new RoleDto(role.getId(), role.getRoleName().name());
    }
}
