import javax.swing.JTextField;
import java.awt.*;

public class SudokuJGuiBox extends JTextField{

    public SudokuJGuiBox() {
        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("SansSerif", Font.BOLD, 24));
        setEditable(false);

    }
}
