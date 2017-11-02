import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Sławek on 10.07.2017.
 */
public class TicTacToeFrame extends JFrame implements ActionListener {

    private ArrayList<JButton> buttons = new ArrayList<>(); // dla zwycięzcy

    public TicTacToeFrame(){ // konstruktor
        setTitle("Kółko i Krzyżyk");
        setLayout(new GridLayout(3,3));

        //JButton jButton = new JButton("");
        for (int i=1 ; i<=9 ; i++){
            JButton jButton = new JButton(""); // tworzy na nowo - nowego buttona - gdyby był za pętlą twotzyłby
            // tylko jednego buttona tak jak wyżej w komentarzu
            jButton.addActionListener(this); // implementuje action listener (this) - this bo oczekuje interfejsu
            // jakby był string w metodzie to oczekiwały zamiast this stringa
            // addActionListener dodaje nowego "słuchacza" gracza i jako argument przyjmuje ActionListenera
            add(jButton); // konstruktor, który przyjmuje pustego stringa
            buttons.add(jButton); // dla zwycięzcy

        }
        setSize(600,600);
        setVisible(true);

    }

    public boolean isWinner (){
        if (isWinner(0,1,2))
            return  true;
        if (isWinner(3,4,5))
            return  true;
        if (isWinner(6,7,8))
            return  true;
        if (isWinner(0,3,6))
            return  true;
        if (isWinner(1,4,7))
            return  true;
        if (isWinner(2,5,8))
            return  true;
        if (isWinner(0,4,8))
            return  true;
        if (isWinner(2,4,6))
            return  true;

        return  false;
    }


    // poniżej metoda która sprawdza zwycięzkie pola

    public boolean isWinner(int i, int j, int k) { // rozbita metoda isWinner (to co jest wyżej i tu) - tzw. przeciążanie metody
        if (buttons.get(i).getText().equals("")){
            return  false;
        }

        if (buttons.get(i).getText().equals(buttons.get(j).getText()) &&
                buttons.get(j).getText().equals(buttons.get(k).getText())) {
            return true;
        } else {
            return false;
        }


    }

    public void setAllDisable(){ // metoda ustawiająca wszystkie przyciski na nieaktywne po zakończeniu gry
        for ( JButton button: buttons ){
            button.setEnabled(false);
        }

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToeFrame();
            }
        });
        // polimorfizm
        // ActionListener hello = new TicTacToeFrame();

    }

    private int counter; // jeżeli nie napiszemy = 0 to przyjmie domyślnie 0

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        button.setText("X");
        if (counter % 2 == 0){
            button.setText("X"); // wpisujemy tą operację żeby było wiadomo co było kliknięte
            //System.out.println("X");
        }
        else{
            button.setText("O"); // analogicznie jak z Xem
            //System.out.println("O");
        }
        button.setEnabled(false);// wpisujemy to żeby nie było możliwości kliknięcie drugi raz tego samego przycisku
        counter++;
        if (isWinner()){
            JOptionPane.showMessageDialog(null,"Koniec gry!");
            setAllDisable(); // polecenie zablokowania przycisków po zakończeniu gry
        }
        else{
            if (counter == 9)
            JOptionPane.showMessageDialog(null,"Koniec gry, nikt nie wygrał!");
        }

        System.out.println("Kliknięto");

    }
}
