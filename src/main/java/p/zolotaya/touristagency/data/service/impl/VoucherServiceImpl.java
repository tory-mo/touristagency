package p.zolotaya.touristagency.data.service.impl;


import p.zolotaya.touristagency.data.dao.DAOException;
import p.zolotaya.touristagency.data.dao.VoucherDAO;
import p.zolotaya.touristagency.data.entity.Voucher;
import p.zolotaya.touristagency.data.entity.Voyage;
import p.zolotaya.touristagency.data.service.VoucherService;

import java.util.ArrayList;

public class VoucherServiceImpl implements VoucherService {

    private VoucherDAO voucherDAO;
    public VoucherServiceImpl(VoucherDAO voucherDAO) {
        this.voucherDAO = voucherDAO;
    }

    @Override
    public boolean makeVoucher(int voyageID) throws DAOException {
        return voucherDAO.createVoucher(voyageID);
    }
    @Override
    public void payVoucher(int idVoucher) throws DAOException {
        Voucher voucher = voucherDAO.getVoucherById(idVoucher);
        Voyage voyage = voucher.getVoyage();
        int calculatedCost = voyage.getNights() * voyage.getOffer().getDayPrice();
        voucherDAO.updateTotalCost(idVoucher, calculatedCost);
        voucherDAO.changeVoucherStatus(idVoucher, true);
    }

    @Override
    public boolean removeVoucherById(int idVoucher) throws DAOException {
        return voucherDAO.removeVoucherById(idVoucher);
    }

    @Override
    public Voucher getVoucherById(int idVoucher) throws DAOException {
        return voucherDAO.getVoucherById(idVoucher);
    }

    @Override
    public ArrayList<Voucher> getAllVouchers() throws DAOException {
        return voucherDAO.getAllVouchers();
    }

    @Override
    public ArrayList<Voucher> getVouchersByTouristID(int touristID) throws DAOException {
        return voucherDAO.getVouchersByTouristID(touristID);
    }
}
