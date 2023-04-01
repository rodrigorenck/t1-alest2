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
            var reader = new BufferedReader(new FileReader(new File("0050macacos.txt")));
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

            for (int i = 0; i<numeroRodadas; i++){
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
//                            m.remove(coco);
                            m.givePar(coco);
                        } else {
//                            m.remove(coco);
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
}
