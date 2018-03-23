package MINER;

public enum Box {
    slt,
    num1,
    num2,
    num3,
    num4,
    num5,
    num6,
    num7,
    num8,
    rad,
    q,
    grn,
    govn,
    crst,
    icon;
    public Object image;
    Box getNextNumBox(){
        return Box.values()[this.ordinal()+1];
    }
    int getNumber(){
        return this.ordinal();
    }
}
