package main.services;

import main.model.Tool;
import main.model.ToolCharge;

import java.time.LocalDate;
import java.util.ArrayList;

public interface Database {

    Tool getTool(String toolCode);

    ToolCharge getToolCharge(String toolType);


    ArrayList<LocalDate> getHolidays(LocalDate beginDate, LocalDate endDate);


}
