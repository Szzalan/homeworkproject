package tablegame.json;

public class JsonResult {
    private String winner;
    private int stepCounter;
    public JsonResult(){}
    public JsonResult(String winner, int stepCounter) {
        this.winner = winner;
        this.stepCounter = stepCounter;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getStepCounter() {
        return stepCounter;
    }

    public void setStepCounter(int stepCounter) {
        this.stepCounter = stepCounter;
    }
}

