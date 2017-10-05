import java.util.*;

/**
 * Created by sq on 31.08.2017.
 */
public class RectangleArray {

    private ArrayList<Integer> xDots = new ArrayList<>();
    private ArrayList<Integer> yDots = new ArrayList<>();
    private ArrayList<Rectangle> inputRectangles = null;

    public RectangleArray(ArrayList<Rectangle>data) {
        for (Rectangle rectangle:data) {
            xDots.add(rectangle.getLeftBottomDot().getX());
            yDots.add(rectangle.getLeftBottomDot().getY());
            xDots.add(rectangle.getRightTopDot().getX());
            yDots.add(rectangle.getRightTopDot().getY());
        }
        inputRectangles = data;
    }

    public int calculateSquare() {
        int res = 0;

        optimizeData();

        ArrayList<Rectangle> allRectanglesArrayList = new ArrayList<>();

        for (int i = 0; i < yDots.size() - 1; i++) {
            for (int j = 0; j < xDots.size() - 1; j++) {
                Rectangle r = new Rectangle(new Dot(xDots.get(j), yDots.get(i)),
                        new Dot(xDots.get(j + 1), yDots.get(i + 1)));
                allRectanglesArrayList.add(r);
            }
        }

        for (Rectangle r: allRectanglesArrayList) {
            if (checkDotInRects(r.getRightTopDot())) {
                res += r.getSquare();
            }
        }

        return res;
    }

    private void optimizeData() {
        HashSet<Integer> setX = new HashSet<>(xDots);
        xDots = new ArrayList<>(setX);
        Collections.sort(xDots);

        HashSet<Integer> setY = new HashSet<>(yDots);
        yDots = new ArrayList<>(setY);
        Collections.sort(yDots);
    }

    private boolean checkDotInRects(final Dot d) {
        boolean res = false;

        for (Rectangle rect : inputRectangles) {
            if (rect.isDotInside(d)) {
                res = true;
            }
        }
        return res;
    }

}
