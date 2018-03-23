import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import MINER.Box;
import MINER.Coord;
import MINER.Game;
import MINER.Rang;

public class JavaMiner extends JFrame {
    private Game game;
    private JPanel panel;
    private JLabel label;

    private final int stolbs=20;
    private final int stroks=15;
    private final int govns=40;
    private final int imgSize=30;


    public static void main(String[] args) {
        new JavaMiner();

    }
    private JavaMiner(){
        game=new Game(stolbs,stroks,govns);
        game.start();
        Rang.setSize (new Coord(stolbs,stroks));
        setImages();
        initLabel();
        initPanel();
        initFrame ();
    }
    private void initLabel(){
        label=new JLabel("Привет!!! Нажми на любую клетку, только не вляпайся сразу)");
        add (label, BorderLayout.NORTH);
    }
    private void initPanel(){
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (Coord coord: Rang.getAllCoord()) {

                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x*imgSize, coord.y*imgSize, this);
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x=e.getX()/imgSize;
                int y=e.getY()/imgSize;
                Coord coord=new Coord(x,y);
                if (e.getButton()==MouseEvent.BUTTON1)
                    game.pressLButton (coord);
                if (e.getButton()==MouseEvent.BUTTON3)
                    game.pressRButton (coord);
                if (e.getButton()==MouseEvent.BUTTON2)
                    game.start ();
                label.setText(getMessage());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(Rang.getSize().x*imgSize, Rang.getSize().y*imgSize));
        add (panel);
    }

    private String getMessage() {
        switch (game.getState()){
            case Plaued: return "Не наступи в какашку! Правила как в обычном сапере (надеюсь ты их знаешь).";
            case Govned: return "Фу, ну и вонища!!!";
            case Winned: return "Молодец, вышел сухим из воды...";
            default: return "Привет!";
        }
    }

    private void initFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Джава Говнёр");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setIconImage(getImage("icon"));
    }
    private void setImages (){
        for (Box box : Box.values ())
            box.image = getImage(box.name().toLowerCase());
    }
    private Image getImage (String name){
        String filename ="/"+name.toLowerCase()+".jpg";
        ImageIcon icon= new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}
