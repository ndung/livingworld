package id.co.icg.lw.web.member;

import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/member/editmessage.html")
public class EditMessageActionBean extends AddMessageActionBean {

    public Resolution update() {
        boolean edit = messageManager.editMessage(getMessage());
        System.out.println("edit:"+edit);
        return back();
    }

    @DontValidate
    public Resolution delete() {
        messageManager.deleteMessage(getMessage());
        return back();
    }
}