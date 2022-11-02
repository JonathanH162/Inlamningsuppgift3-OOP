import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FemtonSpel extends JFrame {

    private JPanel tilePanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JButton newGameButton = new JButton("Nytt spel");
    private JButton winButton = new JButton("Klicka för vinst"); // Alla brickor läggs ut sorterade demonstrera vinst.
    private List<JButton> buttonsGame;

    public FemtonSpel(){
        // Lägg till panel,Layout, "Nytt spel" knapp och "Klicka för vinst" knapp
        tilePanel.setLayout(new GridLayout(4,4));
        bottomPanel.add(newGameButton);
        bottomPanel.add(winButton);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(tilePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        newGame(); // Metod finns inte ännu, kommer finnas framöver
        newGameButton.addActionListener(new buttonListener()); // Metod finns inte ännu, kommer finnas framöver
        winButton.addActionListener(new buttonListener());
        add(mainPanel);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }



    public static void main(String[] args){ FemtonSpel fs = new FemtonSpel();}

}
