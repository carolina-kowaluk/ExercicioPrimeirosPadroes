import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Consultas {
    private Predicate<RegistroDoTempo> consultaPadrao;
    private RegistroDoTempoRepository _repository;

    public Consultas(RegistroDoTempoRepository repository){
        _repository = repository;
        consultaPadrao = r -> r.getHorasInsolacao() > 10;
    }

    public List<String> datasEmQueChouveuMaisDe(double milimetros){
        return _repository.getAll()
            .stream()
            .filter(r->r.getPrecipitacao() > milimetros)
            .map(r->r.getDia()+"/"+r.getMes()+"/"+r.getAno())
            .toList();
    }

    public String diaQueMaisChoveuNoAno(int ano){
        RegistroDoTempo registro = _repository.getAll()
        .stream()
        .filter(reg->reg.getAno() == ano)
        .max(Comparator.comparing(RegistroDoTempo::getPrecipitacao))
        .orElseThrow(IllegalArgumentException::new);
        String resp = registro.getDia()+"/"+registro.getMes()+"/"+registro.getAno()+", "+registro.getPrecipitacao();
        return resp;
    }

    public List<RegistroDoTempo> diasEmQue(){
        return _repository.getAll()
            .stream()
            .filter(consultaPadrao)
            .toList();
    }
}
