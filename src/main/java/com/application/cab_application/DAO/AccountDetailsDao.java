package com.application.cab_application.DAO;

import com.application.cab_application.Models.Account;
import com.application.cab_application.Models.AccountDetails;
import com.application.cab_application.Util.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDetailsDao {
    public int createAccountDetails(AccountDetails details) {
        String sql = "Insert into account_details(account_id,name,address) values(?, ?, ?)";
        ResultSet rs;
        try (PreparedStatement preparedStatement = DatabaseConnector.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, details.getAccountId());
            preparedStatement.setString(2, details.getName());
            preparedStatement.setString(3, details.getAddress());
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows > 0){
                rs = preparedStatement.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }else{
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e + " SQL Error raised here");
        } catch (ClassNotFoundException e) {
            System.out.println(e + " Class Error raised here");
        }
        return 0;
    }

    public AccountDetails getAccountDetailsByAccountID(int id){
        String sql = "select * from account_details where account_id ="+id;
        try(PreparedStatement preparedStatement = DatabaseConnector.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                return new AccountDetails(resultSet.getInt("id"),resultSet.getInt("account_id"),
                        resultSet.getString("name"), resultSet.getString("address"),
                        resultSet.getInt("current_ride_id"));
            } else {
                return new AccountDetails();
            }
        }catch (SQLException e){
            System.out.println(e+ " SQL Exception Occurred Here");
        }catch (ClassNotFoundException e){
            System.out.println(e+ " Class not found Exception");
        }
        return new AccountDetails();
    }

    public boolean updateAccount(AccountDetails accountDetails, int id) throws SQLException{
        AccountDetails accountDetails1 = getAccountDetailsByAccountID(id);
        if(accountDetails1.getAccountId() != 0){
            String query = "update accounts set name = ?, address = ? where id ="+ accountDetails.getAccountId() ;
            try(PreparedStatement preparedStatement = DatabaseConnector.getConnection().prepareStatement(query)){
                preparedStatement.setString(1, accountDetails.getName());
                preparedStatement.setString(2,accountDetails.getAddress());
                int rows = preparedStatement.executeUpdate();
                return rows > 0;
            }catch (ClassNotFoundException e){
                System.out.println(e + " Class not found Exception");
            }catch (SQLException e){
                System.out.println(e + " SQL Exception" + e.getMessage());
            }
        }else{
            throw new SQLException("Account Details not Found");
        }

        return false;
    }

    public boolean updateCurrentRideID(int accountDetailsID, int rideID){
        String query = "update accounts set current_ride_id = ? where id ="+ accountDetailsID;
        try(PreparedStatement preparedStatement = DatabaseConnector.getConnection().prepareStatement(query)){
            preparedStatement.setInt(1,rideID);
            int rows = preparedStatement.executeUpdate();
            return rows > 0 ;
        } catch (Exception e){
            System.out.println(e + "Exception");
        }
        return false ;
    }
}