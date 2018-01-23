package id.co.icg.lw.services;

import id.co.icg.lw.domain.Master;

import java.util.List;

public interface MasterService {
    List<Master> getReligion();
    List<Master> getMartialStatus();
    List<Master> getGender();
    List<Master> getNationality();
}
