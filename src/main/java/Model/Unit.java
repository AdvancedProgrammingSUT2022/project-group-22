package Model;

public class Unit {
    private int speed;
    private int power;
    private int upgrade;
    private Tiles positon;
    private boolean isSleeping;

    public void MoveTo(Tiles tile){
        this.positon = tile;
    }

    public void ChangeState(){
        if(isSleeping) isSleeping = false;
        else isSleeping = true;
    }

    public void Fortify(){
        isSleeping = true;
    }


}
