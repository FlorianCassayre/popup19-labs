package se.kth.popup.lab4.geometry.closestpair;

import java.util.List;

public class Pair 
{
    Segment s;
    List<Point> l;
    
    public Pair(Segment s, List<Point> l)
    {
        this.s = s;
        this.l = l;
    }
}
