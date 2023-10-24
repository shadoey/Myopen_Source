import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.*;

public class Mainframe extends JFrame {

    public void initialise(User user) {

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 2, 5, 5));
        infoPanel.add(new JLabel("name"));
        infoPanel.add(new JLabel(user.Username));
        infoPanel.add(new JLabel("USer ID"));
        infoPanel.add(new JLabel(user.UserID));
        infoPanel.add(new JLabel("email"));
        infoPanel.add(new JLabel(user.Email));
        infoPanel.add(new JLabel("Number"));
        infoPanel.add(new JLabel(user.Number));

        add(infoPanel, BorderLayout.NORTH);

        setTitle("Dashboard");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
