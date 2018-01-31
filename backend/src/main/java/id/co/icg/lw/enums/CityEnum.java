package id.co.icg.lw.enums;

public enum CityEnum {
    JAKARTA(1), OTHER(2);

    private int value;

    CityEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CityEnum parse(int id) {
        CityEnum status = null;
        for (CityEnum item : CityEnum.values()) {
            if (item.getValue() == id) {
                status = item;
                break;
            }
        }
        return status;
    }
}