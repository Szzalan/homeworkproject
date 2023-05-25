package tablegame.model;
/**The class contains a record of positions on the table
 *
 * @param row respresents a row coordinate in the grid
 * @param col represents a column coordinate in the grid
 */

public record Position(int row, int col) {

    public String toString() {
        return String.format("(%d,%d)", row, col);
    }

}