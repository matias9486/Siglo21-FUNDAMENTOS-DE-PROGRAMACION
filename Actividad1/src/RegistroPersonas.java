import java.util.ArrayList;
import java.util.List;

public class RegistroPersonas implements IRegistroPersonas {
    private List<Persona> personas;

    public RegistroPersonas() {
        this.personas = new ArrayList<>();
    }

    @Override
    public void agregarPersona(Persona p){
        personas.add(p);
    }
    public List<Persona> getPersonas(){
        return personas;
    }
}
