package com.company;
import java.util.Comparator;
import java.util.GregorianCalendar;

/**
 * Created by Alexandr Shustov on 16.02.2016.
 */
public class TimeCompare implements Comparator<Chat>  {
    @Override
    public int compare(Chat temp1, Chat  temp2) {
        return Long.compare(temp2.getTimestamp() , temp1.getTimestamp());
    }
}
