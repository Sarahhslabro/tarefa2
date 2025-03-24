

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;
public class labelframe extends JFrame {
        private JLabel label3;
    public labelframe() {
        super("Logo do IFMT");
        setLayout (new FlowLayout());


        Icon bug= new ImageIcon (getClass().getResource("if.png"));

        label3 = new JLabel();
        label3.setText(" INSTITUTO FEDERAL DE EDUCAÇÃO, CIÊNCIA E TECNOLOGIA DE MATO GROSSO");
        label3.setIcon(bug);
        label3.setHorizontalTextPosition(SwingConstants.CENTER);
        label3.setVerticalTextPosition(SwingConstants.BOTTOM);
        label3.setToolTipText("label3");
        add (label3);
    }
}
