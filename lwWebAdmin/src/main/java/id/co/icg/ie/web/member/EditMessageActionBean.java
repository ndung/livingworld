package id.co.icg.ie.web.member;

import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/member/editmessage.html")
public class EditMessageActionBean extends AddMessageActionBean {

    public Resolution update() {
        messageManager.editMessage(getMessage());
        return back();
    }

    @DontValidate
    public Resolution delete() {
        messageManager.deleteMessage(getMessage());
        return back();
    }
}