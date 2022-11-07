package test;
import main.model.RentalAgreement;
import main.ToolRental;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class ToolRentalTest {

    @Test
    public void test1(){
        try {
            RentalAgreement result = ToolRental.checkOut("JAKR", 5, 101, LocalDate.of(2015, 9, 3));
        }
        catch(Exception e){

            assertEquals("Discount percentage must be between 0-100", e.getMessage());
        }

    }
    @Test
    public void test2(){
        RentalAgreement expected = new RentalAgreement("LADW",
                "Ladder",
                "Werner",
                3,
                LocalDate.of(2020,7,2),
                LocalDate.of(2020,7,5),
                new BigDecimal("1.99"),
                2,
                new BigDecimal("3.98"),
                10,
                new BigDecimal("0.40"),
                new BigDecimal("3.58"));
        RentalAgreement result;
        try {
            result = ToolRental.checkOut("LADW", 3, 10, LocalDate.of(2020, 7, 2));
            assertEquals(expected,result);
        }
        catch(Exception e){
            fail("No exception should have been Thrown." + "Given Exception:" + e.getMessage());
        }

    }


    @Test
    public void test3(){
        RentalAgreement expected = new RentalAgreement("CHNS",
                "Chainsaw",
                "Stihl",
                5,
                LocalDate.of(2015,7,2),
                LocalDate.of(2015,7,7),
                new BigDecimal("1.49"),
                3,
                new BigDecimal("4.47"),
                25,
                new BigDecimal("1.12"),
                new BigDecimal("3.35"));
        RentalAgreement result;
        try {
            result = ToolRental.checkOut("CHNS", 5, 25, LocalDate.of(2015, 7, 2));
            assertEquals(expected,result);
        }
        catch(Exception e){
            fail("No exception should have been Thrown." + "Given Exception:" + e.getMessage());
        }
    }

    @Test
    public void test4(){
        RentalAgreement expected = new RentalAgreement("JAKD",
                "Jackhammer",
                "DeWalt",
                6,
                LocalDate.of(2015, 9, 3),
                LocalDate.of(2015,9,9),
                new BigDecimal("2.99"),
                3,
                new BigDecimal("8.97"),
                0,
                new BigDecimal("0.00"),
                new BigDecimal("8.97"));
        RentalAgreement result;
        try {
            result = ToolRental.checkOut("JAKD", 6, 0, LocalDate.of(2015, 9, 3));
            assertEquals(expected,result);
        }
        catch(Exception e){
            fail("No exception should have been Thrown." + "Given Exception:" + e.getMessage());
        }
    }

    @Test
    public void test5(){
        RentalAgreement expected = new RentalAgreement("JAKR",
                "Jackhammer",
                "Ridgid",
                9,
                LocalDate.of(2015, 7, 2),
                LocalDate.of(2015,7,11),
                new BigDecimal("2.99"),
                5,
                new BigDecimal("14.95"),
                0,
                new BigDecimal("0.00"),
                new BigDecimal("14.95"));
        RentalAgreement result;
        try {
            result = ToolRental.checkOut("JAKR", 9, 0, LocalDate.of(2015, 7, 2));
            assertEquals(expected,result);
        }
        catch(Exception e){
            fail("No exception should have been Thrown." + "Given Exception:" + e.getMessage());
        }
    }

    @Test
    public void test6(){
        RentalAgreement expected = new RentalAgreement("JAKR",
                "Jackhammer",
                "Ridgid",
                4,
                LocalDate.of(2020, 7, 2),
                LocalDate.of(2020,7,6),
                new BigDecimal("2.99"),
                1,
                new BigDecimal("2.99"),
                50,
                new BigDecimal("1.50"),
                new BigDecimal("1.49"));
        RentalAgreement result;
        try {
            result = ToolRental.checkOut("JAKR", 4, 50, LocalDate.of(2020, 7, 2));
            assertEquals(expected,result);
        }
        catch(Exception e){
            fail("No exception should have been Thrown." + "Given Exception:" + e.getMessage());
        }
    }
}
