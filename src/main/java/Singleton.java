import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by dutzul on 26.05.2017.
 */

public class Singleton {
    private static Singleton instance = null;
    public static ArrayList<HashSet<Integer>> moves=null;

    protected Singleton() {
        System.out.println(10);
        moves=new ArrayList<HashSet<Integer>>();
        for(int i=0;i<2;++i) moves.add(new HashSet<Integer>());
    }

    public static ArrayList<HashSet<Integer>> getMoves(){
        return moves;
    }

    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
