public class Monkey {

    private final int id;
    private Monkey par;
    private Monkey impar;
    private final int parId;
    private final int imparId;
    private int quantCocoPar;
    private int quantCocoImpar;
    private int quantCoco;

    public Monkey(int id, int parId, int imparId, int quantCocosPar, int quantCocosImpar) {
        this.id = id;
        this.parId = parId;
        this.imparId = imparId;
        this.quantCocoPar = quantCocosPar;
        this.quantCocoImpar = quantCocosImpar;
        this.quantCoco = this.quantCocoImpar + this.quantCocoPar;
    }

    public void setParImpar(Monkey par, Monkey impar){
        this.par = par;
        this.impar = impar;
    }


    @Override
    public String toString() {
        return "Macaco{" +
                "id=" + id +
                ", parId=" + parId +
                ", imparId=" + imparId +
                ", quantCocos=" + quantCoco +
                '}';
    }

    public int getId() {
        return id;
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

    public int getParId() {
        return parId;
    }

    public int getImparId() {
        return imparId;
    }

    /**
     * Remove o numero de cocos par da sua quantidade de cocos total e transfere esse valor para o seu macaco par
     */
    public void givePar() {
        par.quantCocoPar+= this.quantCocoPar;
        par.quantCoco += this.quantCocoPar;
        this.quantCoco = this.quantCoco - quantCocoPar;
        this.quantCocoPar = 0;
    }

    /**
     * Remove o numero de cocos impar da sua quantidade de cocos total e transfere esse valor para o seu macaco impar
     */
    public void giveImpar() {
        impar.quantCocoImpar+= this.quantCocoImpar;
        impar.quantCoco += this.quantCocoImpar;
        this.quantCoco = this.quantCoco - quantCocoImpar;
        this.quantCocoImpar = 0;
    }

    public int numberOfCocos(){
        return quantCoco;
    }
}
