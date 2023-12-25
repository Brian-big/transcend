package brianbig.transcend.entities.enums;

public enum RoleName {
    ADMIN, TEACHER, STUDENT, DEVELOPER;

    public static RoleName from(String name) {
        return switch (name.toUpperCase()) {
            case "ADMIN" -> ADMIN;
            case "TEACHER" -> TEACHER;
            case "STUDENT" -> STUDENT;
            case "DEVELOPER" -> DEVELOPER;
            default -> throw new IllegalStateException("Unexpected value: " + name + " for ROLE");
        };
    }

}
