package brianbig.transcend.api.dto;

import brianbig.transcend.entities.User;

import java.util.List;

public record UserDto(String id, String userName, List<String> roles) {

    public static UserDto from(User user) {
        return new UserDto(user.getId(), user.getUsername(),
                user.getRoles().stream().map(role -> role.getRoleName().name()).toList()
        );
    }
}
