package id.co.icg.lw.enums;

public enum MemberEnum {
    GOLD(0), BLUE(1), BLACK(2);

    private int value;

    MemberEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MemberEnum parse(int id) {
        MemberEnum status = null;
        for (MemberEnum item : MemberEnum.values()) {
            if (item.getValue() == id) {
                status = item;
                break;
            }
        }
        return status;
    }
}