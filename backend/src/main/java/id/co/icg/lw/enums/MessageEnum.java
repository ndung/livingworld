package id.co.icg.lw.enums;

public enum MessageEnum {
    SENT(1), DELIVERED(2), PENDING(3), FAILED(5);

    private int value;

    MessageEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MessageEnum parse(int id) {
        MessageEnum status = null;
        for (MessageEnum item : MessageEnum.values()) {
            if (item.getValue() == id) {
                status = item;
                break;
            }
        }
        return status;
    }
}