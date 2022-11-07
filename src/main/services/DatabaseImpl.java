package main.services;

import main.model.Tool;
import main.model.ToolCharge;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseImpl implements Database {

    private static final HashMap<String, Tool> tools = new HashMap<>();

    private static final HashMap<String, ToolCharge> toolCharges = new HashMap<>();


    private static final ArrayList<LocalDate> holidays  = new ArrayList<>();

    public DatabaseImpl(){
        tools.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl"));
        tools.put("LADW", new Tool("LADW", "Ladder", "Werner"));
        tools.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt"));
        tools.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid"));


        toolCharges.put("Ladder",new ToolCharge(new BigDecimal("1.99"), true, true, false));
        toolCharges.put("Chainsaw",new ToolCharge(new BigDecimal("1.49"), true, false, true));
        toolCharges.put("Jackhammer",new ToolCharge(new BigDecimal("2.99"), true, false, false));
    }


    @Override
    public Tool getTool(String toolCode) {
        return tools.get(toolCode);
    }

    @Override
    public ToolCharge getToolCharge(String toolType) {
        return toolCharges.get(toolType);
    }

    //Ideally this would be a database call that would return holidays based on a query
    //so that holidays would not be hardcoded
    @Override
    public ArrayList<LocalDate> getHolidays(LocalDate beginDate, LocalDate endDate) {
        //For this implementation to insure dates are not being added to a long list
        holidays.clear();
        LocalDate tmp1, tmp2;
        tmp1 = beginDate;
        tmp2 = endDate;

        while(!tmp1.equals(tmp2)){
            //4th of July
            if(tmp1.getMonth() == Month.JULY && tmp1.getDayOfMonth() == 4){
                holidays.add(tmp1);
            }
            //Labor Day
            if(tmp1.getMonth() == Month.SEPTEMBER
                    && tmp1.getDayOfWeek() == DayOfWeek.MONDAY
                    && tmp1.getDayOfMonth() <= 7){
                holidays.add(tmp1);

            }

            tmp1 = tmp1.plusDays(1);
        }

        return holidays;
    }
}

