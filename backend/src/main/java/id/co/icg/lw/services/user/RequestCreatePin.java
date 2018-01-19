package id.co.icg.lw.services.user;

public class RequestCreatePin {
    private String pin;
    private String pinConfirmation;
    private int securityQuestion;
    private String securityAnswer;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPinConfirmation() {
        return pinConfirmation;
    }

    public void setPinConfirmation(String pinConfirmation) {
        this.pinConfirmation = pinConfirmation;
    }

    public int getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(int securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
}
