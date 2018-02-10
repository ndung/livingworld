package id.co.icg.lw.services.currentOffer;

import id.co.icg.lw.domain.CurrentOffer;

import java.util.List;

public interface CurrentOfferService {
    List<CurrentOfferRequest> findAllCurrentOffer(int page);

    CurrentOfferDetailRequest findCurrentOffer(long currentOfferId);

    boolean createCurrentOffer(CreateCurrentOfferRequest request);

    List<CurrentOffer> findAll();

    CurrentOffer findOne(long id);
}
