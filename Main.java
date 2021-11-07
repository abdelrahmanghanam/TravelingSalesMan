import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] Args){
        City a= new City("a");
        City b= new City("b");
        City c= new City("c");
        City d= new City("d");
        a.addNeighbour(new Route(b,2));
        a.addNeighbour(new Route(c,5));
        a.addNeighbour(new Route(d,7));
        b.addNeighbour(new Route(a,2));
        b.addNeighbour(new Route(c,8));
        b.addNeighbour(new Route(d,3));
        c.addNeighbour(new Route(a,5));
        c.addNeighbour(new Route(b,8));
        c.addNeighbour(new Route(d,1));
        d.addNeighbour(new Route(a,7));
        d.addNeighbour(new Route(b,3));
        d.addNeighbour(new Route(c,1));

        travellingSalesMan(a);
    }
    public static void travellingSalesMan(City a){

        ArrayList<ArrayList<City>> mystack=new ArrayList<>();
        ArrayList<City> route1= new ArrayList<>();
        route1.add(a);
        mystack.add(route1);

        for (int i=0;i<mystack.size();i++){
            ArrayList<City> current=mystack.get(i);
            City lastElement=mystack.get(i).get(mystack.get(i).size()-1);
            int m=0;
            for (int j=0;j<lastElement.getNeighbours().size();j++){
                if(!mystack.get(i).contains(lastElement.getNeighbours().get(j).city)){
                    m=1;
                    ArrayList<City> subArray=new ArrayList<>();
                    subArray.addAll(current);
                    subArray.add(lastElement.getNeighbours().get(j).city);
//                    System.out.println(subArray);
                    mystack.add(subArray);
                }


            }
            if (m==0){
                break;
            }
        }
        ArrayList<ArrayList<City>> mycity= new ArrayList<>();
        for (ArrayList<City> k:mystack){
            if (k.size()>=4){
                mycity.add(k);
            }
        }
        for (ArrayList<City> arr:mycity){
            arr.add(a);
            int cost=0;
//            for (City cit:arr){
//                System.out.print(cit.name);
//            }
            for (int i=0;i<arr.size();i++){
                System.out.print(arr.get(i).name);
                if (i+1<arr.size()){
                    City current= arr.get(i);
                    City next = arr.get(i+1);
                    for (Route r:current.getNeighbours()){
                        if (r.city.name==next.name){
                            cost=cost+r.cost;
                        }
                    }
                }
            }
            System.out.print(" = "+cost);
            System.out.println("");
        }

    }


}
class Route{
    public City city;
    public int cost;
    public Route(City c,int co){
        this.city=c;
        this.cost =co;
    }
}
class City{
    String name;
    ArrayList<Route> neighbours;
    public City(String n){
        name= n;
        neighbours=new ArrayList<Route>();
    }
    public void addNeighbour(Route r){
        neighbours.add(r);
    }
    public ArrayList<Route> getNeighbours(){
        return neighbours;
    }

}