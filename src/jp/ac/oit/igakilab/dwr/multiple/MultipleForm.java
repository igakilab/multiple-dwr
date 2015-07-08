package jp.ac.oit.igakilab.dwr.multiple;


public class MultipleForm{
    private int max = 20;
    private int multiple = 3;

    public MultipleForm(){
    }

    public void setMax(int max){
        this.max = max;
    }

    public void setMultiple(int multiple){
        this.multiple = multiple;
    }

    public int getMax(){
        return max;
    }

    public int getMultiple(){
        return multiple;
    }
}
