package files.service;

import files.object.Techs;

import java.util.List;

public interface ITechsSevice {
    List<Techs> getTechs();

    void add(Techs newTechs);

    void update(Techs newTechs);

    void remove(Techs newTechs);

    boolean existById(int id);

    Techs getTechsById(int id);

}
