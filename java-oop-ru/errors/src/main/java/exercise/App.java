package exercise;

// BEGIN
class App {
    public static void printSquare(Circle circle) {
        try {
            int square = (int) Math.round(circle.getSquare());
            System.out.println(square + "\nВычисление окончено");
        } catch (NegativeRadiusException e) {
            System.out.println("Не удалось посчитать площадь\nВычисление окончено");
        }
    }
}
// END
