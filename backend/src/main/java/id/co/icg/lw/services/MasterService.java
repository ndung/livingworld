package id.co.icg.lw.services;

import id.co.icg.lw.domain.master.Master;
import id.co.icg.lw.domain.master.MemberType;

import java.util.List;

public interface MasterService {
    List<MemberType> getMemberTypes();

    List<Master> getReligions();

    List<Master> getMartialStatus();

    List<Master> getGenders();

    List<Master> getNationalities();

    List<Master> getCities();

}
