import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static List<Macaco> monkeysList;

    public static void main(String[] args) {

        try {
            var reader = new BufferedReader(new FileReader(new File("teste2.txt")));
            var line = reader.readLine(); //first line
            var numeroRodadas = Integer.valueOf(Arrays.stream(line.split(" ")).toList().get(1));

            monkeysList = new ArrayList<Macaco>();

            var monkeyLine = reader.readLine(); //second line

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


            //antes
            System.out.println("Antes da primeira rodada: ");
            for (Macaco m :
                    monkeysList) {
                System.out.println(m);
            }
            System.out.println("");

            for (int i = 0; i<4; i++){
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
                            m.remove(coco);
                            m.givePar(coco);
                        } else {
                            m.remove(coco);
                            m.giveImpar(coco);
                        }
                    }
                }
            }

            System.out.println("Depois da ultima rodada: ");
            for (Macaco m :
                    monkeysList) {
                System.out.println(m);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Macaco findById(int id){
        for (Macaco macaco:
             monkeysList) {
            if(macaco.getId() == id){
                return macaco;
            }
        }
        return null;
    }




    public static Macaco createMonkey(int id, int parId, int imparId, List<Integer> cocos){
        return new Macaco(id, parId, imparId, cocos);
    }




    public void criaJogo(){
        criaMacacos();
    }


    public void criaMacacos(){
        var macaco0 = new Macaco(0, new ArrayList<>(Arrays.asList(178, 84, 1, 111, 159, 22, 54, 132, 201, 51, 44)));
        var macaco1 = new Macaco(1, new ArrayList<>(Arrays.asList(80, 82, 10, 83, 98, 31, 56, 84, 53)));
        var macaco2 = new Macaco(2, new ArrayList<>(Arrays.asList(65, 194, 35, 132, 191, 202, 62)));
        var macaco3 = new Macaco(3, new ArrayList<>(Arrays.asList(121, 10, 162)));
        var macaco4 = new Macaco(4, new ArrayList<>(Arrays.asList(16, 110, 125, 113, 35)));
        var macaco5 = new Macaco(5, new ArrayList<>(Arrays.asList(120, 25, 20, 134, 166, 100, 157, 159)));

//        macaco0.setParImpar();

    }
}
