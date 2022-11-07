package main.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class RentalAgreement {
    private String toolCode;

    private String toolType;
    private String toolBrand;

    private int rentalDays;
    private LocalDate checkOutDate;

    private LocalDate dueDate;
    private BigDecimal dailyRentalCharge;
    private int chargeDays;
    private BigDecimal preDiscountCharge;
    private int discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public RentalAgreement(String toolCode, String toolType, String toolBrand, int rentalDays, LocalDate checkOutDate, LocalDate dueDate, BigDecimal dailyRentalCharge, int chargeDays, BigDecimal preDiscountCharge, int discountPercent, BigDecimal discountAmount, BigDecimal finalCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkOutDate = checkOutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }


    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public void setDailyRentalCharge(BigDecimal dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }

    public void printAgreement(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        String builder = "Tool code:" + this.toolCode + "\n" +
                "Tool type:" + this.toolType + "\n" +
                "Tool brand:" + this.toolBrand + "\n" +
                "Rental days:" + this.rentalDays + "\n" +
                "Check out date:" + formatter.format(this.checkOutDate) + "\n" +
                "Due date:" + formatter.format(this.dueDate) + "\n" +
                "Daily rental charge:" + format.format(this.dailyRentalCharge.doubleValue()) + "\n" +
                "Charge days:" + this.chargeDays + "\n" +
                "Pre-discount charge:" + format.format(this.preDiscountCharge.doubleValue()) + "\n" +
                "Discount percent:" + String.format("%d%%", this.discountPercent) + "\n" +
                "Discount amount:" + format.format(this.discountAmount.doubleValue()) + "\n" +
                "Final Charge:" + format.format(this.finalCharge.doubleValue()) + "\n";

        System.out.print(builder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toolCode, toolType, toolBrand, rentalDays, checkOutDate, dueDate, dailyRentalCharge, chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalAgreement that = (RentalAgreement) o;
        return rentalDays == that.rentalDays && chargeDays == that.chargeDays && discountPercent == that.discountPercent && toolCode.equals(that.toolCode) && toolType.equals(that.toolType) && toolBrand.equals(that.toolBrand) && checkOutDate.equals(that.checkOutDate) && dueDate.equals(that.dueDate) && dailyRentalCharge.equals(that.dailyRentalCharge) && preDiscountCharge.equals(that.preDiscountCharge) && discountAmount.equals(that.discountAmount) && finalCharge.equals(that.finalCharge);
    }

}
