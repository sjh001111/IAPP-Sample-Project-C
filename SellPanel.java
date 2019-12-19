
import model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SellPanel extends CustomPanel implements MyObserver
{      
    private Cinema cinema;
    private int sessionID;
    private BottomSellPanel p3;

    public SellPanel(Cinema cinema)
    {   
        this.cinema = cinema;
        sessionID = 0;
        p3 = new BottomSellPanel();
        setup();
        build();
    }

    private void setup()
    {      
        setLayout(new GridLayout(0,1));
    }

    private void build()
    {     
        add(new TopSellPanel());
        add(new MiddleSellPanel());
        add(p3);
    }

    private class TopSellPanel extends CustomPanel implements MyObserver{
        private JTextField t1 = new JTextField(5);
        private JTextField t2 = new JTextField(5);

        public TopSellPanel(){
            setup();
            build();
        }

        public void setup(){
        }   

        private void build(){
            addPair  ("Session ID", t1);
            addButton("Search", new TopSellListener());
            addPair  ("Movie", t2);
        }

        private class TopSellListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                sessionID = Integer.parseInt(t1.getText());
                t2.setText(cinema.sessions().find(sessionID).movie().getName());
                p3.update();
            }
        }
    }

    private class MiddleSellPanel extends CustomPanel implements MyObserver{
        private JTextField t1 = new JTextField(5);
        private JTextField t2 = new JTextField(5);

        public MiddleSellPanel(){
            setup();
            build();
        }

        public void setup(){
        }   

        private void build(){
            addPair  ("Gold", t1);
            addPair  ("Regular", t2);
            addButton("Sell", new MiddleSellListener());
        }

        private class MiddleSellListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                cinema.sessions().sellTickets(sessionID, Integer.parseInt(t1.getText()), Integer.parseInt(t2.getText()));
            }
        }
    }

    private class BottomSellPanel extends CustomPanel implements MyObserver{
        private JTextField t1 = new JTextField(25);
        private JTextField t2 = new JTextField(25);

        public BottomSellPanel(){
            setup();
            build();
        }

        public void setup(){
            cinema.attach(this);
            cinema.movies().attach(this);
            cinema.theatres().attach(this);
            cinema.sessions().attach(this);
            Iterator<Record> it1;
            it1 = cinema.movies().records().iterator();
            while(it1.hasNext())
                it1.next().attach(this);
            it1 = cinema.theatres().records().iterator();
            while(it1.hasNext())
                it1.next().attach(this);
            it1 = cinema.sessions().records().iterator();
            while(it1.hasNext()){
                Session it = (Session) it1.next();
                it.attach(this);
                Iterator<Seat> it2 = it.seats().iterator();
                while(it2.hasNext())
                    it2.next().attach(this);
            }
        }   

        private void build(){
            addPair("Gold", t1);
            addPair("Regular", t2);
        }

        public void update(){
            if(sessionID != 0){
                t1.setText(cinema.sessions().find(sessionID).seats().get(0).toString());
                t2.setText(cinema.sessions().find(sessionID).seats().get(1).toString());
            }
        }
    }
}