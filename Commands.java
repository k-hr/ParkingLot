// package parkinglot;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.HashMap;

public class Commands {
    public Map<String, Method> mapCmd2Method;

    public Commands() {
        mapCmd2Method = new HashMap<String, Method>();
        try {
            populateCmd2Method();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    private void populateCmd2Method() throws NoSuchMethodException {
        mapCmd2Method.put("Create_parking_lot", ParkingLot.class.getMethod("createParkingLot", String.class));
        mapCmd2Method.put("Park", ParkingLot.class.getMethod("park", String.class, String.class));
        mapCmd2Method.put("Leave", ParkingLot.class.getMethod("leave", String.class));
        mapCmd2Method.put("Vehicle_registration_number_for_driver_of_age", ParkingLot.class.getMethod("getRegistrationNumbersFromAge", String.class));
        mapCmd2Method.put("Slot_numbers_for_driver_of_age", ParkingLot.class.getMethod("getSlotNumbersFromAge", String.class));
        mapCmd2Method.put("Slot_number_for_car_with_number", ParkingLot.class.getMethod("getSlotNumberFromRegNo", String.class));
    }
}