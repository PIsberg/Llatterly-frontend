package com.example.latterly.llatterly;

/**
 * Created by gotrex on 2015-06-07.
 */
public class MockData {

    private static MockData instance;

    static {
        instance = new MockData();
    }

    private MockData() {
    }

    public static MockData getInstance() {
        return MockData.instance;
    }
    /*
    // used for mocking //TODO: persistence
    private int counter = 0;

    private String[] videoIds = new String[] {"_oEA18Y8gM0", "CPGFfVLdQ9w"};
    private String[] question = new String[] {"asdf", "asdf"};
    private String[] answers = new String[] {"asdf", "asdf"};
    private int[] rightAnswer = new int[] {1, 2};

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    public boolean isRightAnswer(int answer) {
        boolean result = false;
        if(answer == rightAnswer[this.counter]) {
            result = true;
        }
        return result;
    }

    public String getNextVideoId() {
        return videoIds[counter];
    }
    */

}
