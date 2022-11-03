import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
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
        newGame(); 
        newGameButton.addActionListener(new buttonListener());
        winButton.addActionListener(new buttonListener());
        add(mainPanel);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    // Lägg alla knappar i en lista, shuffla listan med Collections.shuflle();
    private void makeButtonList(){
        buttonsGame = new ArrayList<>();
        for(int i = 1; i < 16; i++){
            buttonsGame.add(new JButton(String.valueOf(i)));
        }
        buttonsGame.add(new JButton());
        Collections.shuffle(buttonsGame);
    }

    //Alla brickor blandas slumpmässigt
    private void newGame(){
        makeButtonList();
        tilePanel.removeAll(); // Tar bort alla komponenter
        tilePanel.revalidate(); // placerar om
        for(JButton button : buttonsGame){
            tilePanel.add(button);
            button.addActionListener(new buttonListener());
        }
    }
    // Kontrollerar om den valda knappen kan byta plats med den tomma platsen
    private void checkIndex(JButton button){
    int index = buttonsGame.indexOf(button);
    if(index % 4 != 0){
        isEmpty(index -1, button);
    }
    if(index - 4 >= 0){
        isEmpty(index - 4, button);
    }
    if(index % 4 != 3){
        isEmpty(index + 1, button);
    }
    if(index + 4 < buttonsGame.size()){
        isEmpty(index + 4, button);
        }
    }
    
    //Kontrollera om platsen är tom, om den är det byt plats med den klickade knappen
    private void isEmpty(int empty, JButton button){
        JButton switchPlace = buttonsGame.get(empty);
        if(switchPlace.getText().equals("")){
            switchPlace.setText(button.getText());
            button.setText("");
        }
    }
    // kontrollera så att det är rätt ordning 1-15 med den sista platsen tom, isf kör metoden youWonOutPrint();
    public void checkOrder(){
        for(int i = 0; i <buttonsGame.size() -1; i++){
            JButton button = buttonsGame.get(i);
            if(button.getText().equals("")){
                return;
            } else if (Integer.parseInt(button.getText()) != (i +1)) {
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Congratulations you won!");
    } 
    
     class buttonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() != newGameButton && actionEvent.getSource() != winButton){
                checkIndex((JButton) actionEvent.getSource());
                checkOrder();
            }
            else if (actionEvent.getSource() == newGameButton) {
                newGame();
            } else if (actionEvent.getSource() == winButton) {
                for(int i = 0; i < buttonsGame.size() -1; i++){
                    buttonsGame.get(i).setText(String.valueOf(i +1));
                }
                buttonsGame.get(15).setText("");
            }
        }
    }

    public static void main(String[] args){ FemtonSpel fs = new FemtonSpel();}
}
