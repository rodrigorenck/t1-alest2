import java.io.*;
import java.util.*;

/**
 * Authors: Rodrigo Rosa Renck, Isadora Ferreira Guerra
 */
public class App {

    private final List<Monkey> monkeysList = new ArrayList<>();
    private final String FILE_SUFIX = "macacos.txt";
    private final String FILE_NAME = "1000".concat(FILE_SUFIX);
    private int roundsNumber; //numero de rodadas que vamos jogar

    /**
     * Le o arquivo e para cada linha adiciona o macaco na lista
     */
    private void readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        var line = reader.readLine(); //first line
        roundsNumber = Integer.valueOf(Arrays.stream(line.split(" ")).toList().get(1));
        var monkeyLine = reader.readLine();
        addMonkeysToList(monkeyLine, reader);
    }

    /**
     * Para cada linha do arquivo cria um macaco com a quantidade de cocos
     * par e cocos impar, para facilitar na hora de jogar a rodada e distribuir os cocos
     */
    private void addMonkeysToList(String monkeyLine, BufferedReader reader) throws IOException {
        while (monkeyLine != null) {
            var monkeyAttributes = Arrays.stream(monkeyLine.split(" ")).toList();
            var id = Integer.valueOf(monkeyAttributes.get(1));
            var parId = Integer.valueOf(monkeyAttributes.get(4));
            var imparId = Integer.valueOf(monkeyAttributes.get(7));
            var cocos = monkeyAttributes.subList(11, monkeyAttributes.size()).stream().map(Integer::valueOf).toList();

            int quantPar = 0;
            int quantImpar = 0;
            for (int coco: cocos){
                if(coco%2==0) quantPar++;
                else quantImpar++;
            }

            var monkey = new Monkey(id, parId, imparId, quantPar, quantImpar);

            monkeysList.add(monkey);
            monkeyLine = reader.readLine();
        }
    }


    /**
     * Executa a aplicacao
     */
    public void run() {
        try {
            var begin = System.currentTimeMillis();
            System.out.println("Reading file [" +  FILE_NAME + "] and setting the game...");
            readFile(FILE_NAME);

            System.out.println("Playing...");
            play();

            var winner = getWinner();
            System.out.println("WINNER: " + winner);

            var end = System.currentTimeMillis();
            var timeToRun = (end - begin) / 1000.0;
            System.out.println("Time to run the application in seconds: " + timeToRun);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Percorre a lista de macacos e busca o macaco que satisfaz o criterio de comparacao informado,
     * que no caso é o macaco que tem o maior numero de cocos
     */
    private Monkey getWinner() {
        return monkeysList.stream().max(Comparator.comparing(Monkey::numberOfCocos)).get();
    }

    /**
     * Para cada rodada nos vamos percorrer a lista de macacos e para cada macaco vamos
     * distribuir os cocos para seu macaco par e para seu macaco impar
     */
    private void play() {
        preparingTheMonkeys();
        for (int i = 0; i < roundsNumber; i++) {
            for (Monkey m :
                    monkeysList) {
                    m.givePar();
                    m.giveImpar();
                }
            }
        }

    /**
     * Metodo que percorre a lista de macacos e para cada macaco informa para ele
     * qual macaco ele deve dar seus cocos par e para qual macaco deve dar os seus cocos impar
     */
    private void preparingTheMonkeys() {
        for (Monkey m :
                monkeysList) {
            var par = findById(m.getParId());
            var impar = findById(m.getImparId());
            m.setParImpar(par, impar);
        }
    }

    /**
     * Percorre a lista de macacos e devolve o macaco com o id solicitado
     */
    private Monkey findById(int id) {
        return monkeysList.stream().filter(m -> id == m.getId()).findAny().get();
    }
}

