package MINER;

class Matrix {
    private Box [] [] matrix;
    Matrix (Box defBox){
        matrix=new Box[Rang.getSize().x][Rang.getSize().y];
        for (Coord coord : Rang.getAllCoord())
            matrix [coord.x] [coord.y] = defBox;
    }
    Box get (Coord coord) {
        if (Rang.inRang(coord))
        return matrix [coord.x] [coord.y];
        return null;
    }
    void set (Coord coord, Box box){
        if (Rang.inRang(coord))
        matrix [coord.x] [coord.y]=box;
    }
}
