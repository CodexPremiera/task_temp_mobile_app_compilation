package task.mobile_app_portfolio.app05_connect_three.logic;

import java.util.ArrayList;

public class Grid {
    /* FIELDS */
    private final ArrayList<Column> columns;
    private int numberOfColumns;
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    /* CONSTRUCTOR */
    public Grid() {
        this.columns = new ArrayList<>();
        this.numberOfColumns = 0;
    }

    /* METHODS */
    public Boolean checkConnect3(Column column, Player player) {
        // color the top of column and get origin
        int originY = column.addColoredCell(player);
        int originX = columns.indexOf(column);

        // throw error if column is full
        if (originY >= column.getSize())
            throw new RuntimeException("Column is full.");

        // initialize range
        minX = maxX = originX;
        minY = maxY = originY;
        setRange(originX, originY);

        // check horizontal, vertical, and diagonal
        if (checkHorizontal(originX, originY))
            return true;
        if (checkVertical(originX, originY))
            return true;
        if (checkDiagonalUp(originX, originY))
            return true;
        //return false;
        return checkDiagonalDown(originX, originY);
    }

    private void setRange(int originX, int originY) {
        while (minX > 0 && minX > originX - 2) minX--;
        while (minY > 0 && minY > originY - 2) minY--;
        while (maxX < this.numberOfColumns && maxX < originX + 2) maxX++;
        while (maxY < this.numberOfColumns && maxY < originY + 2) maxY++;
    }

    private boolean checkHorizontal(int originX, int originY) {
        Cell origin = columns.get(originX).get(originY);
        int connectCount = 0;

        for (int col = 0; col < 5; col++) {
            Cell currentCell = columns.get(col).get(originY);
            connectCount = (currentCell.getColorist() == origin.getColorist()) ? connectCount + 1 : 0;

            if (connectCount == 3) return true;
        }

        return false;
    }

    private boolean checkVertical(int originX, int originY) {
        Cell origin = columns.get(originX).get(originY);
        int connectCount = 0;

        for (int row = minY; row < maxY; row++) {
            Cell currentCell = columns.get(originX).get(row);
            connectCount = (currentCell.getColorist() == origin.getColorist()) ? connectCount + 1 : 0;

            if (connectCount == 3) return true;
        }

        return false;
    }

    private boolean checkDiagonalUp(int originX, int originY) {
        Cell origin = columns.get(originX).get(originY);
        int connectCount = 0;

        for (int col = minX, row = minY; col < maxX && row < maxY; col++, row++) {
            Cell currentCell = columns.get(col).get(row);
            connectCount = (currentCell.getColorist() == origin.getColorist()) ? connectCount + 1 : 0;

            if (connectCount == 3) return true;
        }

        return false;
    }

    private boolean checkDiagonalDown(int originX, int originY) {
        Cell origin = columns.get(originX).get(originY);

        try {
            return (columns.get(originX).get(originY).getColorist()
                            == columns.get(originX + 1).get(originY - 1).getColorist() ) &&
                    (columns.get(originX).get(originY).getColorist()
                            == columns.get(originX + 2).get(originY - 2).getColorist());
        } catch (Exception ignored) {}

        return false;
    }

    public void addColumn(Column column) {
        columns.add(column);
        numberOfColumns++;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }
}