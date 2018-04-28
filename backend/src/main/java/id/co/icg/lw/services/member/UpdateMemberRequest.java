package id.co.icg.lw.services.member;

import java.util.Date;

public class UpdateMemberRequest extends CreateMemberRequest {
    private String tid;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
