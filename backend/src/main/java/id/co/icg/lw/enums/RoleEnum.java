package id.co.icg.lw.enums;

public enum RoleEnum {
    ADMIN(0), USER(1);

    private int value;

    RoleEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RoleEnum parse(int id) {
        RoleEnum status = null;
        for (RoleEnum item : RoleEnum.values()) {
            if (item.getValue() == id) {
                status = item;
                break;
            }
        }
        return status;
    }
}