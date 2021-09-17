public class item extends thing {
    public double weight;
    public int count;
    public int durability;

    public item(String n, boolean ren, double w, int c) {
        super(n, ren);
        weight = w;
        count = c;
        durability = 100;
    }

    public item(String n, boolean ren, double w, int c, int d) {
        this(n, ren, w, c);
        durability = d;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double w) {
        weight = w;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int c) {
        count = c;
    }

}
