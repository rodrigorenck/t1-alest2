import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Authors: Rodrigo Rosa Renck, Isadora Ferreira Guerra
 */
public class App {

    private final List<Monkey> monkeysList = new ArrayList<>();
    private final String FILE_SUFIX = "macacos.txt";
    private final String FILE_NAME = "0200".concat(FILE_SUFIX);
    private Integer roundsNumber; //numero de rodadas que vamos jogar

    private void readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        var line = reader.readLine(); //first line
        roundsNumber = Integer.valueOf(Arrays.stream(line.split(" ")).toList().get(1));
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

            var monkey = new Monkey(id, parId, imparId, cocos);

            monkeysList.add(monkey);
            monkeyLine = reader.readLine();
        }
    }


    public void run() {
        try {
            var begin = System.currentTimeMillis();
            System.out.println("Reading file and setting the game...");
            readFile(FILE_NAME);

            System.out.println("Playing...");
            play();

            var winner = monkeysList.stream().max(Comparator.comparing(Monkey::numberOfCoconuts)).get();
            System.out.println("WINNER: " + winner);

            var end = System.currentTimeMillis();
            var timeToRun = (end - begin) / 1000.0;
            System.out.println("Time to run the application in seconds: " + timeToRun);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void play() {
        for (int i = 0; i < roundsNumber; i++) {
            for (Monkey m :
                    monkeysList) {
                var par = findById(m.getParId()); //cuidar com null
                var impar = findById(m.getImparId());
                m.setParImpar(par, impar);
                List<Integer> newCocos = new ArrayList<>(m.getCocos()); // concurrent modification exception solution - create a copy of the list
                for (Integer coco :
                        newCocos) {
                    if (coco % 2 == 0) {
                        m.givePar(coco);
                    } else {
                        m.giveImpar(coco);
                    }
                }
            }
        }
    }

    private void before() {
        System.out.println("Iniciando");
        System.out.println("Antes da primeira rodada: ");
        monkeysList.forEach(System.out::println);
        System.out.println();
    }

    private void after() {
        System.out.println("Fim");
        System.out.println("Depois da ultima rodada: ");
        monkeysList.forEach(System.out::println);
        System.out.println();
    }

    private Monkey findById(int id) {
        return monkeysList.stream().filter(m -> id == m.getId()).findAny().get();
    }
}

