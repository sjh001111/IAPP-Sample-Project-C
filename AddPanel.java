import model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class AddPanel extends CustomPanel implements MyObserver
{
    private Cinema cinema;

    public AddPanel(Cinema cinema)
    {  
        this.cinema = cinema;
        setup();
        build();
    }

    public void setup()
    {  
        setLayout(new GridLayout(0,1));
    }   

    public void build()
    {   
        add(new MovieAddPanel());
        add(new TheatreAddPanel());
        add(new SessionAddPanel());
    }

    private class MovieAddPanel extends CustomPanel implements MyObserver{
        private JTextField t1 = new JTextField(5);
        private JTextField t2 = new JTextField(5);

        public MovieAddPanel(){
            setup();
            build();
        }

        public void setup(){
        }   

        private void build(){
            addTitle ("Movie");
            addPair  ("Name", t1);
            addPair  ("Cost", t2);
            addButton("Add", new MovieAddListener());
            add(confirm);
        }

        private class MovieAddListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                cinema.movies().add(t1.getText(), Double.parseDouble(t2.getText()));
                sayConfirm("New movie has been created .");
            }
        }
    }

    private class TheatreAddPanel extends CustomPanel implements MyObserver{
        private JTextField t1 = new JTextField(5);
        private JTextField t2 = new JTextField(5);
        private JTextField t3 = new JTextField(5);

        public TheatreAddPanel(){
            setup();
            build();
        }

        public void setup(){
        }   

        private void build(){
            addTitle ("Theatre");
            addPair  ("Name", t1);
            addPair  ("Gold", t2);
            addPair  ("Regular", t3);
            addButton("Add", new TheatreAddListener());
            add(confirm);
        }

        private class TheatreAddListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                cinema.theatres().add(t1.getText(), Integer.parseInt(t2.getText()), Integer.parseInt(t3.getText()));
                sayConfirm("New theatre has been created .");
            }
        }
    }

    private class SessionAddPanel extends CustomPanel implements MyObserver{
        private JTextField t1 = new JTextField(5);
        private JTextField t2 = new JTextField(5);
        private JTextField t3 = new JTextField(5);
        private JTextField t4 = new JTextField(5);
        private JTextField t5 = new JTextField(5);
        private JTextField t6 = new JTextField(5);

        public SessionAddPanel(){
            setup();
            build();
        }

        public void setup(){
        }   

        private void build(){
            addTitle ("Session");
            addPair  ("Name", t1);
            addPair  ("Movie ID", t2);
            addPair  ("Theatre ID", t3);
            addPair  ("Time", t4);
            addPair  ("Gold", t5);
            addPair  ("Regular", t6);
            addButton("Add", new SessionAddListener());
            add(confirm);
        }

        private class SessionAddListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                cinema.addSession(t1.getText(), Integer.parseInt(t2.getText()), Integer.parseInt(t3.getText()), Integer.parseInt(t4.getText()), Integer.parseInt(t5.getText()), Integer.parseInt(t6.getText()));
                sayConfirm("New session has been created .");
            }
        }
    }
}