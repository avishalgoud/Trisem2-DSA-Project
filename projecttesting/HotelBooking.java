import java.util.*;

public class HotelBooking {

    static class Room {

        int number;
        String type;
        int price;
        boolean booked;

        Room(int n, String t, int p) {
            number = n;
            type = t;
            price = p;
            booked = false;
        }
    }

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Room> rooms = new ArrayList<>();
    static Stack<String> history = new Stack<>();
    static Queue<String> waiting = new LinkedList<>();
    static HashMap<String,Integer> bookings = new HashMap<>();


    static void addRooms() {

        rooms.add(new Room(101,"Single",1000));
        rooms.add(new Room(102,"Double",2000));
        rooms.add(new Room(103,"Deluxe",3000));
        rooms.add(new Room(104,"Suite",4000));
    }


    static void displayRooms() {

        for(Room r:rooms){

            System.out.println("Room: "+r.number+
                    " Type: "+r.type+
                    " Price: "+r.price+
                    " Booked: "+r.booked);
        }
    }


    static Room searchRoom(int num){

        for(Room r:rooms){

            if(r.number==num)
                return r;
        }

        return null;
    }


    static void bookRoom(){

        System.out.println("Enter customer name:");
        String name=sc.next();

        System.out.println("Enter room number:");
        int num=sc.nextInt();

        Room r=searchRoom(num);

        if(r==null){

            System.out.println("Room not found");
            return;
        }

        if(!r.booked){

            r.booked=true;

            bookings.put(name,num);

            history.push(name+" booked room "+num);

            System.out.println("Room booked successfully");

        }
        else{

            waiting.add(name);

            System.out.println("Room already booked. Added to waiting list");
        }
    }


    static void cancelBooking(){

        System.out.println("Enter customer name:");
        String name=sc.next();

        if(!bookings.containsKey(name)){

            System.out.println("No booking found");
            return;
        }

        int num=bookings.get(name);

        Room r=searchRoom(num);

        r.booked=false;

        bookings.remove(name);

        history.push(name+" cancelled room "+num);

        System.out.println("Booking cancelled");

        if(!waiting.isEmpty()){

            System.out.println(waiting.poll()+" can book now");
        }
    }


    static void sortRooms(){

        for(int i=0;i<rooms.size()-1;i++){

            for(int j=0;j<rooms.size()-i-1;j++){

                if(rooms.get(j).price > rooms.get(j+1).price){

                    Room temp=rooms.get(j);

                    rooms.set(j,rooms.get(j+1));

                    rooms.set(j+1,temp);
                }
            }
        }

        System.out.println("Rooms sorted by price");
    }


    static void showHistory(){

        for(String s:history){

            System.out.println(s);
        }
    }


    public static void main(String[] args){

        addRooms();

        while(true){

            System.out.println("\n------ HOTEL BOOKING SYSTEM ------");
            System.out.println("1 Display Rooms");
            System.out.println("2 Book Room");
            System.out.println("3 Cancel Booking");
            System.out.println("4 Search Room");
            System.out.println("5 Sort Rooms");
            System.out.println("6 Booking History");
            System.out.println("7 Exit");

            int choice=sc.nextInt();

            switch(choice){

                case 1: displayRooms(); break;

                case 2: bookRoom(); break;

                case 3: cancelBooking(); break;

                case 4:
                    System.out.println("Enter room number:");
                    int n=sc.nextInt();
                    Room r=searchRoom(n);

                    if(r!=null)
                        System.out.println("Room found: "+r.type);
                    else
                        System.out.println("Room not found");
                    break;

                case 5: sortRooms(); break;

                case 6: showHistory(); break;

                case 7: System.exit(0);
            }
        }
    }
}