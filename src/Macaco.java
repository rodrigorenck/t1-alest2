import java.util.List;

public class Macaco {

    private int id;
    private Macaco par;
    private Macaco impar;
    private List<Integer> cocos;
    private int parId;
    private int imparId;


    public Macaco(int id, Macaco par, Macaco impar, List<Integer> cocos) {
        this.id = id;
        this.par = par;
        this.impar = impar;
        this.cocos = cocos;
    }

    public Macaco(int id, List<Integer> cocos){
        this.id = id;
        this.cocos = cocos;
    }

    public Macaco(int id, int parId, int imparId, List<Integer> cocos) {
        this.id = id;
        this.parId = parId;
        this.imparId = imparId;
        this.cocos = cocos;
    }

    public void setParImpar(Macaco par, Macaco impar){
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

    public Macaco getPar() {
        return par;
    }

    public void setPar(Macaco par) {
        this.par = par;
    }

    public Macaco getImpar() {
        return impar;
    }

    public void setImpar(Macaco impar) {
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

    public void remove(Integer coco) {
        cocos.remove(coco);
    }

    public void givePar(Integer coco) {
        par.getCocos().add(coco);
    }

    public void giveImpar(Integer coco) {
        impar.getCocos().add(coco);
    }
}
