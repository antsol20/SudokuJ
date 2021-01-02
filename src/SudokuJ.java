import javax.swing.*;
import java.awt.*;

public class SudokuJ {

    private final static JTextField[] textBoxes = new JTextField[81];

    private static void createGui() {
        String loc = "C:\\Users\\Antony\\Documents\\Source_code\\SudokuJ\\src\\ico.png";
        ImageIcon img = new ImageIcon(loc);
        JFrame f = new JFrame("SudokuJ");
        f.setIconImage(img.getImage());
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(600, 600);
        f.setLocation(300, 200);
        f.setLayout(new GridLayout(9, 9));



        JPanel p = new JPanel();
        p.setLayout(new GridLayout(9, 9));
        int pointer = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textBoxes[pointer] = new SudokuJGuiBox();
                p.add(textBoxes[pointer]);
                pointer++;
            }
        }

        JMenuBar mb = new JMenuBar();
        JMenu x = new JMenu("Solve");
        JMenuItem m1 = new JMenuItem("Solve");
        x.add(m1);
        mb.add(x);
        f.setJMenuBar(mb);
        f.setContentPane(p);
        f.setVisible(true);

        m1.addActionListener(e -> {


        });

    }

    public static void main(String[] args) {
        String boardTxt = """
                0,0,1,8,2,5,0,0,0
                7,9,2,0,0,0,8,6,0
                0,0,5,6,0,7,0,0,2
                5,0,0,0,0,0,0,0,0
                1,0,0,0,3,0,0,2,6
                0,0,0,9,6,0,0,0,4
                0,0,0,0,0,9,6,3,0
                0,1,6,0,8,0,0,5,7
                2,0,3,1,5,6,9,4,8
                """;


        SudokuJSolve s = new SudokuJSolve(boardTxt);
        // s.solve();
        createGui();
        populateGrid(boardTxt);

    }

    private static void populateGrid(String grid) {

        int pointer = 0;
        String[] rows = grid.split("\n");
        for (int i = 0; i < 9; i++) {
            String[] vals = rows[i].split(",");
            for (int j = 0; j < 9; j++) {
                textBoxes[pointer].setText(vals[j]);
                pointer++;
            }
        }
    }

}