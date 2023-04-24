
public class Main{

    public static void main(String[] args) throws Exception {
        //Player player = new Player();
        model m = new model();
        view v = new view();
        controller c = new controller(v,m);
    }
}

