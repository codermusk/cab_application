package com.application.cab_application.DAO.V1;

import com.application.cab_application.Models.RideDetails;
import com.application.cab_application.Util.DatabaseConnector;
import com.application.cab_application.enums.RequestStatus;
import com.application.cab_application.enums.VehicleType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RideDetailsDao {
    public static int createRideDetail(RideDetails rideDetails) {
        return BaseDao.create(rideDetails.rideDetailsMapper(),"ride_details");
    }


    public static RideDetails getRideDetails(int id) {
        ResultSet resultSet = BaseDao.find_by("ride_details", "ride_id", id);
        return RideDetailsMapper(resultSet);
    }

    public static boolean updateRideDetails(RideDetails rideDetails) {
        return BaseDao.update(rideDetails.rideDetailsMapper(),"ride_details",rideDetails.getId());
    }

    public static void updateRideStatus(int id, RequestStatus requestStatus) {
        BaseDao.updateColumn("ride_details", requestStatus.getCode(), "ride_status", id);
    }

    public static RideDetails RideDetailsMapper(ResultSet resultSet){
        RideDetails rideDetails ;
        try {
            if(resultSet.next()){
                rideDetails = new RideDetails(resultSet.getInt("id"), resultSet.getInt("from_location_id"),
                        resultSet.getInt("to_location_id"), RequestStatus.fromCode(resultSet.getInt("ride_status")),
                        resultSet.getTimestamp("start_time"), resultSet.getTimestamp("end_time"), resultSet.getInt("ride_id"),
                        VehicleType.fromCode(resultSet.getInt("vehicle_type")));
            }else{
                rideDetails = new RideDetails();
            }
            resultSet.close();
            return rideDetails;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new RideDetails();
    }
}