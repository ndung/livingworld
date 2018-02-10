package id.co.icg.lw.services.currentOffer;

import id.co.icg.lw.domain.CurrentOffer;
import id.co.icg.lw.domain.CurrentOfferImage;
import id.co.icg.lw.repositories.CurrentOfferImageRepository;
import id.co.icg.lw.repositories.CurrentOfferRepository;
import id.co.icg.lw.services.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrentOfferBean implements CurrentOfferService {

    @Autowired
    private CurrentOfferRepository currentOfferRepository;

    @Autowired
    private CurrentOfferImageRepository currentOfferImageRepository;

    @Autowired
    private FileService fileService;

    @Value("${pageSize}")
    private int pageSize;

    @Override
    public List<CurrentOfferRequest> findAllCurrentOffer(int page) {
        return currentOfferRepository.findAllCurrentOffer(new PageRequest(page - 1, pageSize));
    }

    @Override
    public CurrentOfferDetailRequest findCurrentOffer(long currentOfferId) {
        return currentOfferRepository.findById(currentOfferId);
    }

    @Override
    public boolean createCurrentOffer(CreateCurrentOfferRequest request) {
        CurrentOffer currentOffer = new CurrentOffer();
        currentOffer.setTitle(request.getTitle());
        currentOffer.setDescription(request.getDescription());
        currentOffer.setShortDescription(request.getShortDescription());
        currentOffer.setEndDate(request.getEndDate());
        currentOffer.setStartDate(request.getStartDate());

        currentOfferRepository.saveAndFlush(currentOffer);

        List<CurrentOfferImage> images = new ArrayList<>();
        for (MultipartFile file : request.getImages()) {
            String path = fileService.upload(file);
            CurrentOfferImage image = new CurrentOfferImage();
            image.setCurrentOffer(currentOffer);
            image.setCurrentOfferImageId(path);
            images.add(image);
        }

        currentOfferImageRepository.save(images);

        return true;
    }

    @Override
    public List<CurrentOffer> findAll() {
        return currentOfferRepository.findAll();
    }

    @Override
    public CurrentOffer findOne(long id) {
        return currentOfferRepository.findOne(id);
    }
}
