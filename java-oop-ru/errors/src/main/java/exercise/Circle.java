package exercise;

// BEGIN
class Circle {

    private Point center;
    private int radius;

    Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("");
        }
        return Math.PI * Math.pow(radius, 2);
    }
}
// END
