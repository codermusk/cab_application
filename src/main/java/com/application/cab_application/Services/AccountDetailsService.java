package com.application.cab_application.Services;

import com.application.cab_application.DAO.AccountDao;
import com.application.cab_application.DAO.AccountDetailsDao;
import com.application.cab_application.DAO.DriverDetailsDao;
import com.application.cab_application.Models.Account;
import com.application.cab_application.Models.AccountDetails;

import com.application.cab_application.Models.DriverDetails;
import com.application.cab_application.Util.PrettyPrintHelper;
import com.application.cab_application.enums.AccountType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class AccountDetailsService {

    public static String getAccountDetailsResponse(int id){
        Account account = AccountDao.getByID(id);
        AccountDetails accountDetails = AccountDetailsDao.getAccountDetailsByAccountID(id);
        JsonObject accountDetailsObject = PrettyPrintHelper.prettyPrintHelper(accountDetails).getAsJsonObject();
        if(account.getAccountType() == AccountType.DRIVER){
            DriverDetails driverDetails = DriverDetailsDao.getDriverDetailsByAccountID(id);
            accountDetailsObject.addProperty("detailsUpdated", driverDetails.getId() != 0);
        }
        Gson gson = new Gson();
        return gson.toJson(accountDetailsObject);
    }
}
