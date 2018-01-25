package id.co.icg.lw.enums;

public enum NationalityEnum {
    INDONESIAN(1), OTHER(2);

    private int value;

    NationalityEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static NationalityEnum parse(int id) {
        NationalityEnum status = null;
        for (NationalityEnum item : NationalityEnum.values()) {
            if (item.getValue() == id) {
                status = item;
                break;
            }
        }
        return status;
    }
}