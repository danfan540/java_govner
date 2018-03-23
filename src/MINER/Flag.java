package MINER;

class Flag {
    private Matrix flagMap;
    private int countCloseBox;
    void start(){
        flagMap =new Matrix(Box.grn);
        countCloseBox=Rang.getSize().x*Rang.getSize().y;
    }
    Box get (Coord coord){
        return flagMap.get(coord);
    }

    void setOpenBox(Coord coord) {
        flagMap.set(coord, Box.slt);
        countCloseBox--;
    }
    void swapFlagBox (Coord coord){
        switch (flagMap.get(coord)){
            case q: setCloseBox (coord);break;
            case grn: setFlagBox (coord);break;
        }
    }

    private void setCloseBox(Coord coord) {
        flagMap.set(coord, Box.grn);
    }

    void setFlagBox(Coord coord) {
        flagMap.set(coord, Box.q);
    }

    int getCountCloseBox() {
        return countCloseBox;
    }

    void setGovnedBox(Coord coord) {
        flagMap.set (coord, Box.rad);
    }

    void setOpenOnClozeGovnBox(Coord coord) {
        if (flagMap.get(coord)==Box.grn)
            flagMap.set(coord, Box.slt);
    }

    void setNoGovnOnFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord)==Box.q)
            flagMap.set(coord, Box.crst);
    }

    int getCountOfFlagedBoxAround(Coord coord) {
        int count = 0;
        for (Coord around: Rang.getCoordsAround(coord))
            if (flagMap.get(around)== Box.q)
                count++;
        return count;
    }
}
