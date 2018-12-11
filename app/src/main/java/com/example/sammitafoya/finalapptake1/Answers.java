package com.example.sammitafoya.finalapptake1;

public class Answers {
    public int yes;
    public int no;
    public int maybe;
    public int askLater;
    public int neverX;

    public Answers() {
        yes = 1;
        no = 2;
        maybe = 3;
        askLater = 4;
        neverX = 5;
    }

    public String getMyAnswer() {
        // GENERATE RANDOM NUMBER BETWEEN 1 AND 5
        int max = 5;
        int min = 1;

        int random = (int )(Math.random() * max + min);

        // DETERMINE WHICH EXERCISE TO ADD
        String answerList = "\n";

        if (random == yes) {
            answerList += "Yes, for sure!";
        } else if (random == no) {
            answerList += "Not in this life time.";
        } else if (random == maybe) {
            answerList += "I am not sure, the response is unclear.";
        } else if (random == askLater) {
            answerList += "My mind is backed up right now, please try again later.";
        } else {
            answerList += "Never ask this again.";
        }

        return answerList;
    }
}
