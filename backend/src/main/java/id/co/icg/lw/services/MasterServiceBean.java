package id.co.icg.lw.services;

import id.co.icg.lw.domain.Master;
import id.co.icg.lw.enums.ReligionEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MasterServiceBean implements MasterService {
    @Override
    public List<Master> getReligion() {
        List<Master> religions = new ArrayList<>();
        List<ReligionEnum> enumList = Arrays.asList(ReligionEnum.values());
        for (ReligionEnum e : enumList ) {
            religions.add(new Master(e.getValue(), e.name()));
        }

        return religions;
    }

    @Override
    public List<Master> getMartialStatus() {
        return null;
    }

    @Override
    public List<Master> getGender() {
        return null;
    }

}
