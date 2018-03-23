package MINER;

class Govn {
    private Matrix govnMap;
    private int totalGovn;
    Govn (int totalGovn){
        this.totalGovn=totalGovn;
        fixGovnCount();
    }
    void start (){
        govnMap= new Matrix(Box.slt);
        for (int j=0 ; j<totalGovn; j++)
            plaseGovn ();
    }
    Box get (Coord coord){
        return govnMap.get(coord);
    }
    private void fixGovnCount (){
        int maxGovn =Rang.getSize().x*Rang.getSize().y/2;
        if(totalGovn>maxGovn)
            totalGovn=maxGovn;
    }
    private void  plaseGovn (){
        while ( true ) {
            Coord coord = Rang.getRandomCoord();
            if (Box.govn==govnMap.get(coord))
                continue;
            govnMap.set(coord, Box.govn);
            incNumAroundGovn(coord);
            break;
        }

    }
    private void incNumAroundGovn (Coord coord){
        for (Coord around: Rang.getCoordsAround(coord))
            if (Box.govn !=govnMap.get(around))
                govnMap.set(around,govnMap.get(around).getNextNumBox());

    }

    int getTotalGovn() {
        return totalGovn;
    }
}
