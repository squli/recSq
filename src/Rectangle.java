import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by sq on 28.08.2017.
 */
public class Rectangle {

    private Dot leftBot = new Dot();
    private Dot rightTop  = new Dot();

    public static int DOT_COUNT_IN_RECT = 2;
    public static int COORD_COUNT_OF_DOT = 2;
    public static int COORD_FIELD_DIMENSION = 10000;

    public static boolean checkStringOfDots(String[] inputString) {
        if (inputString.length == Rectangle.DOT_COUNT_IN_RECT * Rectangle.COORD_COUNT_OF_DOT) {
            try {
                int values[] = {0, 0, 0, 0};
                for (int i = 0; i < 4; i++) {
                    if (!(Math.abs(Integer.parseInt(inputString[i])) < COORD_FIELD_DIMENSION)) {
                        return false;
                    }
                    values[i] = Integer.parseInt(inputString[i]);
                }

                if ((values[0] > values[2]) || (values[1] > values[3]))
                    return false;

                return true;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Rectangle(int x1, int y1, int x2, int y2) {
        leftBot.setX(x1);
        leftBot.setY(y1);
        rightTop.setX(x2);
        rightTop.setY(y2);
    }

    public Rectangle(Dot leftBot, Dot rightTop) {
        this.leftBot = leftBot;
        this.rightTop = rightTop;
    }

    public int getWidth() {
        return rightTop.getX() - leftBot.getX();
    }

    public int getHeight() {
        return rightTop.getY() - leftBot.getY();
    }

    public int getSquare() {
        return (getWidth() * getHeight());
    }

    public boolean isDotInside(final Dot d) {
        boolean res = false;

        if ( (d.getX() <= rightTop.getX()) && (d.getX() > leftBot.getX()) ) {
            if (d.getY() <= rightTop.getY() && d.getY() > leftBot.getY()) {
                res = true;
            }
        }
        return res;
    }

    public Dot getLeftBottomDot() {
        return leftBot;
    }

    public Dot getRightTopDot() {
        return rightTop;
    }

    public boolean isIn(final Rectangle r) {
        if (this.getLeftBottomDot().getX() >= r.getLeftBottomDot().getX() &&
                this.getLeftBottomDot().getY() >= r.getLeftBottomDot().getY())
            if (this.getRightTopDot().getX() <= r.getRightTopDot().getX() &&
                    this.getRightTopDot().getY() <= r.getRightTopDot().getY()) {
                return true;
            }
        return false;
    }

    public Dot getRectangleCenter() {
        return new Dot((rightTop.getX() - leftBot.getX()) / 2,
                (rightTop.getY() - leftBot.getY()) / 2);
    }
}
