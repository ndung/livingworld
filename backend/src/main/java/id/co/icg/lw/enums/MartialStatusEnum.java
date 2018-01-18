package id.co.icg.lw.enums;

public enum MartialStatusEnum {
    SINGLE(1), MARRIED(2), OTHER(3);

    private int value;

    MartialStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MartialStatusEnum parse(int id) {
        MartialStatusEnum status = null;
        for (MartialStatusEnum item : MartialStatusEnum.values()) {
            if (item.getValue() == id) {
                status = item;
                break;
            }
        }
        return status;
    }
}