import model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ShowPanel extends CustomPanel implements MyObserver
{
    private Cinema cinema;

    public ShowPanel(Cinema cinema)
    {
        this.cinema = cinema;
        setup();
        build();
    }

    private void setup()
    {      
        setLayout(new GridLayout(0,1));
    }

    private void build()
    {     
        add(new TopShowPanel());
        add(new BottomShowPanel());
    }

    private class TopShowPanel extends CustomPanel implements MyObserver{
        private JTextField t1 = new JTextField(8);
        private JTextField t2 = new JTextField(5);
        private JTextField t3 = new JTextField(5);
        private JTextField t4 = new JTextField(5);

        public TopShowPanel(){
            t1.setText(Double.toString(cinema.balance()));
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
            addPair("Balance",t1);
            addPair("Theatre ID", t2);
            addPair("Time", t3);
            addPair("Amount", t4);
            addButton("Hire", new TopShowListener());
        }

        public void update(){
            cinema.profit();
            t1.setText(Double.toString(cinema.balance()));
        }

        private class TopShowListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                cinema.hireTheatre(cinema.theatres().find(Integer.parseInt(t2.getText())), Integer.parseInt(t3.getText()), Double.parseDouble(t4.getText()));
            }
        }
    }

    private class BottomShowPanel extends CustomPanel implements MyObserver{

        public BottomShowPanel(){
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
            addButton("Cinema Report", new BottomShowListener());
        }

        private class BottomShowListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                cinema.cinemaReport();
            }
        }
    }
}