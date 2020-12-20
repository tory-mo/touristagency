package p.zolotaya.touristagency.data.service.impl;

import org.springframework.web.bind.annotation.RestController;
import p.zolotaya.touristagency.data.dao.DAOException;
import p.zolotaya.touristagency.data.dao.TouristDAO;
import p.zolotaya.touristagency.data.entity.Tourist;
import p.zolotaya.touristagency.data.service.TouristService;
import p.zolotaya.touristagency.data.validator.NameValidator;
import p.zolotaya.touristagency.data.validator.PassportIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/tourists")
public class TouristServiceImpl implements TouristService {

    @Autowired
    private TouristDAO touristDao;
    public TouristServiceImpl(TouristDAO touristDAO){
        this.touristDao = touristDAO;
    }

    @Override
    public void registerTourist(Tourist tourist) {
        checkTouristInfo(tourist);
        touristDao.addTourist(tourist);

    }

    private static void checkTouristInfo(Tourist tourist){
        NameValidator nameValidator = new NameValidator();
        PassportIdValidator passportIdValidator = new PassportIdValidator();
        if (!nameValidator.validate(tourist.getName()) ||
                !nameValidator.validate(tourist.getSurname()) ||
                !passportIdValidator.validate(tourist.getPassportId())) {
            throw new DAOException("incorrect info in registration");
        }
    }

    @Override
    public void deleteTourist(Tourist tourist) throws DAOException {
        touristDao.deleteTourist(tourist);
    }

    @Override
    @GetMapping()
    // public String getAllTourists(Model model) throws DAOException {
    // model.addAttribute("tourists", touristDao.getAllTourists());
    // return "tourists/getAllTourists";}
    public ArrayList<Tourist> getAllTourists() throws DAOException {
        return touristDao.getAllTourists();
    }

    @Override
    @GetMapping("/{surname}")
    //public String getTouristBySurname(@PathVariable("surname") String surname, Model model) throws DAOException {
    // model.addAttribute("tourist", touristDao.getTouristBySurname(surname));
    // return "tourists/getTouristBySurname";}
    public Tourist getTouristBySurname(String surname) throws DAOException {
        return touristDao.getTouristBySurname(surname);
    }
}
