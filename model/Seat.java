package model;


import java.util.*;
/**
 * class Seat
 */
public class Seat extends Updater
{   
    protected String name;
    protected int number;
    protected double price;
    protected int sold = 0;

    public Seat(String name, int number)
    {   
        this.name = name;
        this.number = number;   
    }      

    public void setPrice(double price)
    {   
        this.price = price;
        show();
        updateViews();
    }    

    public void addSold(int sell)
    {  
        if (left() >= sell)
        {
            sold += sell;
            show();
        }
        else
            System.out.println("Not enough seats available in " + name + ", sale aborted");
        updateViews();
    }    

    public void reset()
    {  
        sold = 0;
        updateViews();
    }  

    public int left()
    {   
        return number - sold;   
    }

    public double income()
    {  
        return sold * price;    
    } 

    public void show()
    {   
        System.out.println(toString());
        updateViews();
    }                

    public String toString()
    {   String s = name + " seats:";
        s += " n = " + number;
        s += " price = $" + price;
        s += " sold = " + sold;
        return s;   
    }
}
