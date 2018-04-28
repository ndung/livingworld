package id.co.icg.lw.domain.master;

public class MemberType extends Master {
    private String minimumTransaction;

    public MemberType(int id, String name) {
        super(id, name);
    }

    public String getMinimumTransaction() {
        return minimumTransaction;
    }

    public void setMinimumTransaction(String minimumTransaction) {
        this.minimumTransaction = minimumTransaction;
    }
}
