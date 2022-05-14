package files.service;

import files.object.Techs;
import files.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TechsSevice implements ITechsSevice {
    public static String path = "data/techs.scv";

    @Override
    public List<Techs> getTechs() {
        List<Techs> newTechs = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newTechs.add(Techs.parseTechs(record));
        }
        return newTechs;
    }

    @Override
    public void add(Techs newTechs) {
        newTechs.setCreatDate(Instant.now());
        List<Techs> techs = getTechs();
        techs.add(newTechs);
        CSVUtils.write(path, techs);
    }

    @Override
    public void update(Techs newTechs) {
        List<Techs> techs = getTechs();
        for (int i = 0; i < techs.size(); i++) {
            if (techs.get(i).getId() == newTechs.getId()) {
                techs.set(i, newTechs);
                break;
            }
        }
    }


    @Override
    public void remove(Techs newTechs) {
        List<Techs> techs = getTechs();
        techs.removeIf(techs1 -> techs1.getId() == newTechs.getId());
        CSVUtils.write(path, techs);
    }


    @Override
    public boolean existById(int id) {
        return getTechsById(id) != null;
    }

    @Override
    public Techs getTechsById(int id) {
        List<Techs> techs = getTechs();
        for (Techs tech : techs) {
            if (tech.getId() == id)
                return tech;
        }
        return null;
    }
}
