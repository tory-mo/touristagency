package p.zolotaya.touristagency.data.service;


import p.zolotaya.touristagency.data.dao.DAOException;
import p.zolotaya.touristagency.data.entity.Voucher;

import java.util.ArrayList;

public interface VoucherService {
    boolean makeVoucher(int voyageID) throws DAOException;
    void payVoucher(int idVoucher) throws DAOException;
    boolean removeVoucherById(int idVoucher) throws DAOException;
    Voucher getVoucherById(int idVoucher) throws DAOException;
    ArrayList<Voucher> getAllVouchers() throws DAOException;
    ArrayList<Voucher> getVouchersByTouristID(int touristID) throws DAOException;

}
