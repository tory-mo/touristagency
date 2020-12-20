package p.zolotaya.touristagency.data.dao;

import p.zolotaya.touristagency.data.entity.Voucher;

import java.util.ArrayList;

public interface VoucherDAO {

    boolean createVoucher(int voyageID) throws DAOException;
    boolean updateTotalCost(int idVoucher, int cost) throws DAOException;
    boolean changeVoucherStatus(int idVoucher,boolean status) throws DAOException;
    boolean removeVoucherById(int idVoucher) throws DAOException;
    Voucher getVoucherById(int idVoucher) throws DAOException;
    ArrayList<Voucher> getAllVouchers() throws DAOException;
    ArrayList<Voucher> getVouchersByTouristID(int touristID) throws DAOException;
}
