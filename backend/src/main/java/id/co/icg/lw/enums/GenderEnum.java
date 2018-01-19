package id.co.icg.lw.enums;

public enum GenderEnum {
    MALE(1), FEMALE(2), OTHER(3);

    private int value;

    GenderEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static GenderEnum parse(int id) {
        GenderEnum status = null;
        for (GenderEnum item : GenderEnum.values()) {
            if (item.getValue() == id) {
                status = item;
                break;
            }
        }
        return status;
    }
}