// package parkinglot;

import java.util.*;

public class ParkingLot {
    int MAX_SIZE = 0;
    private class Car {
        String regNo;
        String age;
        public Car(String regNo, String age) {
            this.regNo = regNo;
            this.age = age;
        }
    }
    //to track free slots
    ArrayList<Integer> availableSlotList;
    // Slot -> Car
    Map<String, Car> mapSlot2Car;
    // RegNo -> Slot
    Map<String, String> mapRegNo2Slot;
    // Age -> List<RegNo>
    Map<String, ArrayList<String>> mapAge2RegNo;

    //Create Parking lot
    public void createParkingLot(String lotCount) {
        try {
            this.MAX_SIZE = Integer.parseInt(lotCount);
        } catch (Exception e) {
            System.out.println("Invalid lot count");
        }
        this.availableSlotList = new ArrayList<Integer>() {};
        for (int i=1; i<= this.MAX_SIZE; i++) {
            availableSlotList.add(i);
        }
        this.mapSlot2Car = new HashMap<String, Car>();
        this.mapRegNo2Slot = new HashMap<String, String>();
        this.mapAge2RegNo = new HashMap<String, ArrayList<String>>();
        System.out.println("Created parking lot with " + lotCount + " slots");
    }

    //parking car : slot no booked; Car(regNo, age) created
    public void park(String regNo, String age) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Pardon, parking lot is not yet created");
        } else if (this.mapSlot2Car.size() == this.MAX_SIZE) {
            System.out.println("Pardon, parking lot is completely full");
        } else {
            Collections.sort(availableSlotList);
            String slot = availableSlotList.get(0).toString();
            Car car = new Car(regNo, age);
            this.mapSlot2Car.put(slot, car);
            this.mapRegNo2Slot.put(regNo, slot);
            if (this.mapAge2RegNo.containsKey(age)) {
                ArrayList<String> regNoList = this.mapAge2RegNo.get(age);
                this.mapAge2RegNo.remove(age);
                regNoList.add(regNo);
                this.mapAge2RegNo.put(age, regNoList);
            } else {
                ArrayList<String> regNoList = new ArrayList<String>();
                regNoList.add(regNo);
                this.mapAge2RegNo.put(age, regNoList);
            }
            System.out.println("Car with vehicle registration number \""+ regNo + "\" has been parked at slot number " + slot);
            availableSlotList.remove(0);
        }
    }
    public void leave(String slotNo) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking slot is not created");
        } else if (this.mapSlot2Car.size() > 0) {
            Car carToLeave = this.mapSlot2Car.get(slotNo);
            if (carToLeave != null) {
                this.mapSlot2Car.remove(slotNo);
                this.mapRegNo2Slot.remove(carToLeave.regNo);
                ArrayList<String> regNoList = this.mapAge2RegNo.get(carToLeave.age);
                if (regNoList.contains(carToLeave.regNo)) {
                    regNoList.remove(carToLeave.regNo);
                }
                
                this.availableSlotList.add(Integer.parseInt(slotNo));
                System.out.println("Slot number " + slotNo + " vacated, the car with vehicle registration number \"" + carToLeave.regNo + "\" left the space, the driver of the car was of age "+ carToLeave.age);
            } else {
                System.out.println("Slot number " + slotNo + " is already empty");
            }
        } else {
            System.out.println("Parking slot is empty");
        }
    }
    
    public void getRegistrationNumbersFromAge(String age) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.mapAge2RegNo.containsKey(age)) {
            ArrayList<String> regNoList = this.mapAge2RegNo.get(age);
            for (int i=0; i < regNoList.size(); i++) {
                if (!(i==regNoList.size() - 1)){
                    System.out.print(regNoList.get(i) + ",");
                } else {
                    System.out.print(regNoList.get(i));
                }
            }
        } else {
            System.out.println("NO Car with driver of age "+ age +" found");
        }
    }

    public void getSlotNumbersFromAge(String age) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.mapAge2RegNo.containsKey(age)) {
            ArrayList<String> regNoList = this.mapAge2RegNo.get(age);
            ArrayList<Integer> slotList = new ArrayList<Integer>();
            
            for (int i=0; i < regNoList.size(); i++) {
                slotList.add(Integer.valueOf(this.mapRegNo2Slot.get(regNoList.get(i))));
            }
            Collections.sort(slotList);
            for (int j=0; j < slotList.size(); j++) {
                if (!(j == slotList.size() - 1)) {
                    System.out.print(slotList.get(j) + ",");
                } else {
                    System.out.print(slotList.get(j));
                }
            }
            System.out.println();
        } else {
            System.out.println("Not found");
        }
    }
    public void getSlotNumberFromRegNo(String regNo) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.mapRegNo2Slot.containsKey(regNo)) {
            System.out.println(this.mapRegNo2Slot.get(regNo));
        } else {
            System.out.println("Not found");
        }
    }
}