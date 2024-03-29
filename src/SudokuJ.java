import javax.swing.*;
import java.awt.*;

public class SudokuJ {

    private final static JTextField[] textBoxes = new JTextField[81];

    private static void createGui() {
        ImageIcon img = new ImageIcon("res\\ico.png");
        JFrame f = new JFrame("SudokuJ");
        f.setIconImage(img.getImage());
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(600, 600);
        f.setResizable(false);
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

            int[][] input = guiToArray();
            SudokuJSolve s = new SudokuJSolve(input);
            arrayToGui(s.solve());

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


        createGui();
        arrayToGui(stringToArray(boardTxt));
    }

    private static void arrayToGui(int[][] input) {

        int pointer = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textBoxes[pointer].setText(String.valueOf(input[i][j]));
                pointer++;
            }
        }
    }


    private static int[][] stringToArray(String txt) {
        int[][] out = new int[9][9];
        String[] rows = txt.split("\n");

        for (int i = 0; i < 9; i++) {
            String[] vals = rows[i].split(",");
            for (int j = 0; j < 9; j++) {
                out[i][j] = Integer.parseInt(vals[j]);
            }
        }
        return out;
    }


    private static int[][] guiToArray() {
        int pointer = 0;
        int[][] out = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                out[i][j] = Integer.parseInt(textBoxes[pointer].getText());
                pointer++;
            }
        }
        return out;
    }

}