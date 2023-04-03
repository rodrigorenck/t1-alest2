import java.util.List;

public class Monkey {

    private int id;
    private Monkey par;
    private Monkey impar;
    private List<Integer> cocos;
    private int parId;
    private int imparId;


    public Monkey(int id, Monkey par, Monkey impar, List<Integer> cocos) {
        this.id = id;
        this.par = par;
        this.impar = impar;
        this.cocos = cocos;
    }

    public Monkey(int id, List<Integer> cocos){
        this.id = id;
        this.cocos = cocos;
    }

    public Monkey(int id, int parId, int imparId, List<Integer> cocos) {
        this.id = id;
        this.parId = parId;
        this.imparId = imparId;
        this.cocos = cocos;
    }

    public void setParImpar(Monkey par, Monkey impar){
        this.par = par;
        this.impar = impar;
    }


    @Override
    public String toString() {
        return "Macaco{" +
                "id=" + id +
                ", par=" + parId +
                ", impar=" + imparId +
                ", cocos=" + cocos +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Monkey getPar() {
        return par;
    }

    public void setPar(Monkey par) {
        this.par = par;
    }

    public Monkey getImpar() {
        return impar;
    }

    public void setImpar(Monkey impar) {
        this.impar = impar;
    }

    public List<Integer> getCocos() {
        return cocos;
    }

    public void setCocos(List<Integer> cocos) {
        this.cocos = cocos;
    }

    public int getParId() {
        return parId;
    }

    public void setParId(int parId) {
        this.parId = parId;
    }

    public int getImparId() {
        return imparId;
    }

    public void setImparId(int imparId) {
        this.imparId = imparId;
    }

    public void givePar(Integer coco) {
        cocos.remove(coco);
        par.getCocos().add(coco);
    }

    public void giveImpar(Integer coco) {
        cocos.remove(coco);
        impar.getCocos().add(coco);
    }

    public int numberOfCoconuts(){
        return cocos.size();
    }
}
