import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SlidingPuzzle extends JFrame {

    private JPanel tilePanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JButton newGameButton = new JButton("New Game");
    private JButton winButton = new JButton("Click to Win"); 
    private List<JButton> buttonsGame;
    private JLabel victoryMessage = new JLabel("Congratulations, You win!");

    public SlidingPuzzle(){
        
        tilePanel.setLayout(new GridLayout(4,4));
        bottomPanel.add(newGameButton);
        bottomPanel.add(winButton);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(tilePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        newGame(); 
        victoryMessage.setFont(new Font("arial",Font.ITALIC,35));
        newGameButton.addActionListener(new buttonListener());
        newGameButton.setFont(new Font("arial",Font.ITALIC,30));
        winButton.addActionListener(new buttonListener());
        winButton.setFont(new Font("arial",Font.ITALIC,30));
        add(mainPanel);

        setSize(500,500);
        setTitle("Sliding puzzle");
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    //Lägg alla knappar i en lista, shuffla listan
    private void makeButtonList(){
        buttonsGame = new ArrayList<>();
        for(int i = 1; i < 16; i++){
            buttonsGame.add(new JButton(String.valueOf(i)));
        }
        buttonsGame.add(new JButton());
        for (JButton button : buttonsGame) {
            button.setFont(new Font("garamond", Font.ITALIC, 50));
            button.setBorder(BorderFactory.createLineBorder(Color.black));
        }        
        Collections.shuffle(buttonsGame);
    }

    // Alla knappar blandas slumpmässigt
    private void newGame(){
        makeButtonList();
        tilePanel.removeAll(); 
        tilePanel.revalidate(); 
        for(JButton button : buttonsGame){
            tilePanel.add(button);
            button.addActionListener(new buttonListener());
        }
    }

    // Kontrollera om den valda knappen kan byta plats med den tomma platsen
    private void checkIndex(JButton button){

    int index = buttonsGame.indexOf(button);

    if(index % 4 != 0){isBlank(index -1, button);}

    if(index - 4 >= 0){isBlank(index - 4, button);}

    if(index % 4 != 3){isBlank(index + 1, button);}

    if(index + 4 < buttonsGame.size()){isBlank(index + 4, button);}

    }

    //Kontrollera om platsen är tom, om den är det byter den plats med den klickade knappen
    private void isBlank(int empty, JButton button){
        JButton switchPlace = buttonsGame.get(empty);
        if(switchPlace.getText().equals("")){
            switchPlace.setText(button.getText());
            button.setText("");
        }
    }

    //Kontrollera så att det är rätt ordning, 1-15 med sista platsen tom
    public void checkWinningCondition(){
        for(int i = 0; i <buttonsGame.size() -1; i++){
            JButton button = buttonsGame.get(i);
            if(button.getText().equals("")){
                return;
            } else if (Integer.parseInt(button.getText()) != (i +1)) {
                return;
            }
        }
        JOptionPane.showMessageDialog(null, victoryMessage);
    } 
    
     class buttonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() != newGameButton && actionEvent.getSource() != winButton){
                checkIndex((JButton) actionEvent.getSource());
                checkWinningCondition();
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

}
