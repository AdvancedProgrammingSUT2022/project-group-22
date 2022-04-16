package Model;

public class MilitaryUnit extends  Unit{

    private boolean isAlert;

    public void ChangeState(){
        if(isAlert) isAlert = false;
        else isAlert = true;
    }

    public void fortify(){

    }



}
