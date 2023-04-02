import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    private final List<Macaco> monkeysList = new ArrayList<>();
    private final String FILE_NAME = "0050macacos.txt";
    private Integer numeroRodadas;

    private void readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        var line = reader.readLine(); //first line
        numeroRodadas = Integer.valueOf(Arrays.stream(line.split(" ")).toList().get(1));
        var monkeyLine = reader.readLine();

        addMonkeysToList(monkeyLine, reader);
    }

    private void addMonkeysToList(String monkeyLine, BufferedReader reader) throws IOException {
        while (monkeyLine != null) {
            var monkeyAttributes = Arrays.stream(monkeyLine.split(" ")).toList();
            var id = Integer.valueOf(monkeyAttributes.get(1));
            var parId = Integer.valueOf(monkeyAttributes.get(4));
            var imparId = Integer.valueOf(monkeyAttributes.get(7));
            var cocos = monkeyAttributes.subList(11, monkeyAttributes.size()).stream().map(Integer::valueOf).collect(Collectors.toList());

            var monkey = new Macaco(id, parId, imparId, cocos);

            monkeysList.add(monkey);
            monkeyLine = reader.readLine();
        }
    }


    public void run() {
        try {
            readFile(FILE_NAME);

            //antes
            System.out.println("Iniciando");
            System.out.println("Antes da primeira rodada: ");
            for (Macaco m :
                    monkeysList) {
                System.out.println(m);
            }
            System.out.println();

            play();

            System.out.println("Fim");
            System.out.println("Depois da ultima rodada: ");
            for (Macaco m :
                    monkeysList) {
                System.out.println(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void play() {
        for (int i = 0; i < numeroRodadas; i++) {
            for (Macaco m :
                    monkeysList) {
                var par = findById(m.getParId()); //cuidar com null
                var impar = findById(m.getImparId());
                m.setParImpar(par, impar);
                List<Integer> newCocos = new ArrayList<>(m.getCocos()); // concurrent modification exception solution - create a copy of the list
                for (Integer coco :
                        newCocos) {
                    //se for par
                    if (coco % 2 == 0) {
                        m.givePar(coco);
                    } else {
                        m.giveImpar(coco);
                    }
                }
            }
        }
    }

    public Macaco findById(int id) {
        for (Macaco macaco :
                monkeysList) {
            if (macaco.getId() == id) {
                return macaco;
            }
        }
        return null;
    }
}

