package com.application.cab_application.DAO.V1;

import com.application.cab_application.Exception.DbNotReachableException;
import com.application.cab_application.Models.Bill;
import java.sql.ResultSet;
import java.sql.Statement;

public class BillsDao {
    public static int createBill(Bill bill) throws DbNotReachableException {
        bill.setPaymentId(null);
        return BaseDao.create(bill.billTableObject(), "bills");
    }

    public static Boolean updatePaymentInBill(int billID, int paymentID) throws DbNotReachableException {
        return BaseDao.updateColumn("payment_id", paymentID, "bills", billID);
    }

    public static Bill getBillRideID(int id) throws DbNotReachableException {
        ResultSet resultSet = BaseDao.find_by("bills", "ride_id", id);
        return billMapper(resultSet);
    }

    public static Bill getBill(int id) throws DbNotReachableException {
        ResultSet resultSet = BaseDao.find(id,"bills");
        return billMapper(resultSet);
    }

    public static Bill billMapper(ResultSet resultSet){
        try {
            Bill bill ;
            if(resultSet.next()){
                bill = new Bill(resultSet.getInt("id"), resultSet.getInt("ride_id"), resultSet.getDouble("bill_amount"), resultSet.getInt("payment_id"));
            }else{
                bill = new Bill();
            }
            return bill;
        }catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            try {
                Statement statement = resultSet.getStatement();
                resultSet.close();
                statement.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return new Bill();
    }
}
