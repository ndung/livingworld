package id.co.icg.lw.manager.impl;

import id.co.icg.lw.dao.DaoHibernate;
import id.co.icg.lw.dao.model.app.Member;
import id.co.icg.lw.manager.MemberManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memberManager")
public class MemberManagerImpl implements MemberManager {

    @Autowired
    private DaoHibernate daoHibernate;

    @Override
    public List<Member> getMembers() {
        return daoHibernate.getList(Member.class);
    }

}
