package main.model;

public class Tool {

    private final String toolCode;
    private final String toolType;
    private final String brand;



    public Tool(String toolCode, String toolType, String brand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
    }
    public String getBrand() {
        return brand;
    }

    public String getToolCode() {
        return toolCode;
    }
    public String getToolType() {
        return toolType;
    }



    public String toString(){
        return "Tool[toolCode=" + toolCode + ",toolType=" + toolType + ",brand=" + brand;
    }


}
