package p.zolotaya.touristagency.data.service.impl;

import org.springframework.web.bind.annotation.*;
import p.zolotaya.touristagency.data.dao.DAOException;
import p.zolotaya.touristagency.data.dao.OfferDAO;
import p.zolotaya.touristagency.data.entity.Offer;
import p.zolotaya.touristagency.data.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offers")
public class OffersServiceImpl implements OfferService {

    @Autowired
    private OfferDAO offerDao;
    public OffersServiceImpl(OfferDAO offerDao){
        this.offerDao = offerDao;
    }

    @Override
    @GetMapping("/{hot}")
    //public String getHotOffers(Model model) throws DAOException {
    // ArrayList<Offer> hotOffers = offerDao.getOfferList().stream().filter(offer -> offer.getIsHot() == true)
    //                .collect(Collectors.toCollection(ArrayList::new));
    // model.addAttribute("offers", hotOffers);
    // return "offers/getHotOffers";}
    public ArrayList<Offer> getHotOffers() throws DAOException {
        return offerDao.getOfferList().stream().filter(offer -> offer.getIsHot() == true)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    @GetMapping("/{city}")
    //public String getOffersByCity(@PathVariable("city") String city, Model model) throws DAOException {
    // model.addAttribute("offer", offerDao.getOffersByCity(city));
    // return "offers/getOffersByCity";}
    public ArrayList<Offer> getOffersByCity(String city) throws DAOException {
        return offerDao.getOffersByCity(city);
    }

    @Override
    @GetMapping()
   // public String getOfferList(Model model) throws DAOException {
    // model.addAttribute("offers", offerDao.getOfferList());
    // return "offers/getOfferList";}
    public ArrayList<Offer> getOfferList() throws DAOException {
        return offerDao.getOfferList();
    }

    @Override
    @PostMapping("/offer")
    public boolean addOffer(@RequestBody Offer offer) throws DAOException {
        return offerDao.createOffer(offer);
    }

    @Override
    public boolean changeHotStatus(int idOffer, boolean isHot) throws DAOException {
        return offerDao.changeHotStatus(idOffer, isHot);
    }

    @Override
    public boolean updatePrice(int idOffer, int newPrice) throws DAOException {
        return offerDao.updatePrice(idOffer, newPrice);
    }

    @Override
    public boolean removeOffer(Offer offer) throws DAOException {
        return offerDao.removeOffer(offer);
    }
}
