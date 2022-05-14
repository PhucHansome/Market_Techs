package files.object;

import java.time.Instant;

public class Techs {

    private int id;
    private String nameTechs;
    private double priceTechs;
    private int numberTechs;
    private String description;
    private Instant creatDate;

    public Techs() {
        this.id = id;
        this.nameTechs = nameTechs;
        this.priceTechs = priceTechs;
        this.numberTechs = numberTechs;
        this.description = description;
    }

    public static Techs parseTechs(String rar){
        Techs techs = new Techs();
        String[] strings = rar.split(",");
        techs.id = Integer.parseInt(strings[0]);
        techs.nameTechs = strings[1];
        techs.priceTechs = Double.parseDouble(strings[2]);
        techs.numberTechs = Integer.parseInt(strings[3]);
        techs.description = strings[4];
        String temp = strings[5];
        techs.creatDate = Instant.parse(temp);
        return techs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTechs() {
        return nameTechs;
    }

    public void setNameTechs(String nameTechs) {
        this.nameTechs = nameTechs;
    }

    public double getPriceTechs() {
        return priceTechs;
    }

    public void setPriceTechs(double priceTechs) {
        this.priceTechs = priceTechs;
    }

    public int getNumberTechs() {
        return numberTechs;
    }

    public void setNumberTechs(int numberTechs) {
        this.numberTechs = numberTechs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Instant creatDate) {
        this.creatDate = creatDate;
    }

    @Override
    public String toString() {
        return  id + "," +
                nameTechs  + "," +
                priceTechs + "," +
                numberTechs + "," +
                description + ","+
                creatDate;
    }
}
