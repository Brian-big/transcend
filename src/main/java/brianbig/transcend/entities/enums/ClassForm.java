package brianbig.transcend.entities.enums;

public enum ClassForm {
    ONE, TWO, THREE, FOUR;


    public static int form(ClassForm classForm) {
        return switch (classForm.name().toUpperCase()) {
            case "ONE" -> 1;
            case "TWO" -> 2;
            case "THREE" -> 3;
            default -> 4;
        };
    }

    public static ClassForm from(int form) {
        return switch (form) {
            case 1 -> ClassForm.ONE;
            case 2 -> ClassForm.TWO;
            case 3 -> ClassForm.THREE;
            default -> ClassForm.FOUR;
        };
    }

    public static ClassForm from(String form) {
        return switch (form.toUpperCase()) {
            case "ONE" -> ClassForm.ONE;
            case "TWO" -> ClassForm.TWO;
            case "THREE" -> ClassForm.THREE;
            default -> ClassForm.FOUR;
        };
    }


}
