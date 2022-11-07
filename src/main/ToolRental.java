package main;

import main.model.RentalAgreement;
import main.model.Tool;
import main.model.ToolCharge;
import main.services.Database;
import main.services.DatabaseImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ToolRental {

    private static final int MINRENTALDAYS = 1;
    private static final int MINDISCOUNT = 0;
    private static final int MAXDISCOUNT = 100;



    public static void main(String []args){
        try {
            checkOut("LADW", -1, 10, LocalDate.now());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static final Database db = new DatabaseImpl();


    public static RentalAgreement checkOut(String toolCode, int rentalDays, int discount, LocalDate checkOutDate) throws Exception {
        if(rentalDays < MINRENTALDAYS){
            throw new Exception("Number of Rental Days must be 1 or greater.");
        }
        if(discount < MINDISCOUNT || discount > MAXDISCOUNT){
            throw new Exception("Discount percentage must be between 0-100");

        }

        Tool tool = db.getTool(toolCode);
        ToolCharge toolCharge = db.getToolCharge(tool.getToolType());
        LocalDate dueDate = checkOutDate.plusDays(rentalDays);
        ArrayList<LocalDate> holidays = db.getHolidays(checkOutDate, dueDate);
        int chargeDays = countChargeDays(checkOutDate.plusDays(1), dueDate,toolCharge, holidays );
        BigDecimal preDiscountCharge = calculatePreDiscountCharge(chargeDays, toolCharge.getDailyCharge());
        BigDecimal discountAmount = calculateDiscountAmount(preDiscountCharge,discount);
        BigDecimal finalCharge = calculateFinalCharge(discountAmount,preDiscountCharge);

        RentalAgreement agreement = new RentalAgreement(tool.getToolCode(),
                tool.getToolType(),
                tool.getBrand(),
                rentalDays,
                checkOutDate,
                dueDate,
                toolCharge.getDailyCharge().setScale(2, RoundingMode.HALF_UP),
                chargeDays,
                preDiscountCharge,
                discount,
                discountAmount,
                finalCharge
                );


        agreement.printAgreement();

        return agreement;
    }
    private static BigDecimal calculatePreDiscountCharge(int chargeDays, BigDecimal dailyCharge){
        return  dailyCharge.multiply(new BigDecimal(chargeDays)).setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal calculateDiscountAmount(BigDecimal amount, int discountPercent){
        BigDecimal percentage = new BigDecimal(discountPercent).divide(new BigDecimal(100));
        percentage = percentage.multiply(amount).setScale(2,RoundingMode.HALF_UP);

        return percentage;
    }

    private static BigDecimal calculateFinalCharge(BigDecimal discountAmt, BigDecimal preDiscountAmt){
        return preDiscountAmt.subtract(discountAmt);
    }

    private static int countChargeDays(LocalDate firstDate, LocalDate dueDate, ToolCharge toolCharge, List<LocalDate> holidays){
        LocalDate tmpDate = firstDate;
        int chargeDayCtr = 0;
        while(!tmpDate.isAfter(dueDate)){
            boolean isHoliday = isHoliday(tmpDate, holidays);
            //Weekday with weekday charge
            if(!isWeekend(tmpDate) && toolCharge.hasWeekdayCharge()){
                //Friday Observing Holiday
                if(tmpDate.getDayOfWeek().equals(DayOfWeek.FRIDAY) && isHoliday(tmpDate.plusDays(1),holidays)) {
                    if(toolCharge.hasHolidayCharge()){
                        chargeDayCtr++;
                    }
                }
                //Monday Observing holiday
                else if(tmpDate.getDayOfWeek().equals(DayOfWeek.MONDAY) && isHoliday(tmpDate.minusDays(1),holidays)) {
                    if (toolCharge.hasHolidayCharge()) {
                        chargeDayCtr++;
                    }
                }
                //Regular day no weekend holiday
                else{
                    //Separated these if statements as to not charge on a holiday without holiday charge on tool
                    if(isHoliday){
                        if(toolCharge.hasHolidayCharge()){
                            chargeDayCtr++;
                        }
                    }
                    else{
                        chargeDayCtr++;
                    }
                }
            }
            //Weekend with weekend charge
            if(isWeekend(tmpDate) && toolCharge.hasWeekendCharge()){
                chargeDayCtr++;
            }
            tmpDate = tmpDate.plusDays(1);
        }
        return chargeDayCtr;
    }

    private static boolean isWeekend(LocalDate date){
        return date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    private static boolean isHoliday(LocalDate currentDate, List <LocalDate> holidays){
        for(LocalDate date: holidays){
            if(currentDate.equals(date)){
                return true;
            }
        }
        return false;
    }
}
