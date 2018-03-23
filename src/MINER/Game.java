package MINER;

public class Game {
    private Govn govn;
    private Flag flag;


    private GameState state;
    public GameState getState() {
        return state;
    }

    public Game (int stolbs, int stroks, int govns) {
        Rang.setSize(new Coord(stolbs,stroks));
        govn= new Govn(govns);
        flag= new Flag();
    }
    public void start (){

        govn.start();
        flag.start();
        state=GameState.Plaued;
    }

    public Box getBox (Coord coord){
        if (flag.get(coord)==Box.slt)
            return govn.get(coord);
        else
            return flag.get(coord);
    }

    public void pressLButton(Coord coord) {
        if(gameOver())return;
        openBox(coord);
    }
    private void checkWin (){
        if (state==GameState.Plaued)
            if (flag.getCountCloseBox()==govn.getTotalGovn())
                state=GameState.Winned;

    }
    private void openBox (Coord coord){
        switch (flag.get(coord)){
            case slt:setOpenOnClotheBoxAroundNumber(coord);return;
            case grn:
                switch (govn.get(coord)){
                    case govn:openGovn(coord);return;
                    case slt:openBoxAround(coord); return;
                        default: flag.setOpenBox(coord); return;
                }
            case q:return;
        }
    }

    private void setOpenOnClotheBoxAroundNumber(Coord coord) {
        if (govn.get(coord)!=Box.govn)
            if (flag.getCountOfFlagedBoxAround(coord)==govn.get(coord).getNumber())
                for(Coord around: Rang.getCoordsAround(coord))
                    if (flag.get (around)==Box.grn)
                        openBox(around);
        }



    private void openGovn(Coord govned) {
        state=GameState.Govned;
        flag.setGovnedBox (govned);
        for (Coord coord: Rang.getAllCoord())
            if (govn.get(coord)== Box.govn )
                flag.setOpenOnClozeGovnBox(coord);
            else
                flag.setNoGovnOnFlagedSafeBox(coord);
    }

    private void openBoxAround(Coord coord) {
        flag.setOpenBox(coord);
        for (Coord around: Rang.getCoordsAround(coord))
            openBox(around);
    }

    public void pressRButton(Coord coord) {
        if(gameOver())return;
        flag.swapFlagBox (coord);
    }

    private boolean gameOver() {
        if (state==GameState.Plaued)
            return false;
        start();
        return true;
    }
}
