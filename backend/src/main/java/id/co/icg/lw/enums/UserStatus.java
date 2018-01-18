package id.co.icg.lw.enums;

public enum UserStatus {
    PENDING(0), ACTIVE(1), ACTIVE_EMAIL(2), ACTIVE_PHONE(3), DISABLED(4), EXPIRED(5), DELETED(-1);
    private int value;

    UserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserStatus parse(int id) {
        UserStatus status = null;
        for (UserStatus item : UserStatus.values()) {
            if (item.getValue() == id) {
                status = item;
                break;
            }
        }
        return status;
    }
}