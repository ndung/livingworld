package com.livingworld.clients.master.model;

/**
 * Created by ndoenks on 03/06/18.
 */

public class MemberType extends Master {
    private String minimumTransaction;

    public MemberType(String id, String name) {
        super(id, name);
    }

    public String getMinimumTransaction() {
        return minimumTransaction;
    }

    public void setMinimumTransaction(String minimumTransaction) {
        this.minimumTransaction = minimumTransaction;
    }

    @Override
    public String toString() {
        return "MemberType{" +
                "id='" + getId() + '\'' +
                "name='" + getName() + '\'' +
                "minimumTransaction='" + minimumTransaction + '\'' +
                '}';
    }
}
