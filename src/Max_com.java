import java.util.List;

public class Max_com extends Thread {
    private List<Float> f;
    private Float max_f;

    public Max_com(List<Float> f) {
        this.f = f;
    }

    @Override
    public void run() {
        max_f=f.get(0);
        for (Float f1 : f ) {
            if (max_f<f1){max_f=f1;}
        }
        Main.endMaxVal.add(max_f);

    }

}
