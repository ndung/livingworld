package id.co.icg.lw.enums;

public enum ReligionEnum {
    ISLAM(1), CHRISTIAN(2), CATHOLIC(3), HINDU(4), BUDDHA(5), OTHERS(6);

    private int value;

    ReligionEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ReligionEnum parse(int id) {
        ReligionEnum status = null;
        for (ReligionEnum item : ReligionEnum.values()) {
            if (item.getValue() == id) {
                status = item;
                break;
            }
        }
        return status;
    }
}