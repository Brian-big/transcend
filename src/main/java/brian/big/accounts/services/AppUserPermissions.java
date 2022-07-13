package brian.big.accounts.services;

public enum AppUserPermissions {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write");

    private final String permission;

    AppUserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
