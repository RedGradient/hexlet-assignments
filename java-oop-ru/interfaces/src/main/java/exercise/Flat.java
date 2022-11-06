package exercise;

// BEGIN
class Flat implements Home {

    private double area;
    private double balconyArea;
    private int floor;

    Flat(int area, int balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public String toString() {
        return String.format("Квартира площадью %s метров на %s этаже", getArea(), floor);
    }

}
// END
