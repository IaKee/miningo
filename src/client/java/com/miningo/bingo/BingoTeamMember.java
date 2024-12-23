package com.miningo.bingo;

public class BingoTeamMember {
    private String name;
    private int currentProgress;
    private int maximumProgress;
    private int resets;

    public BingoTeamMember(
        String name,
        int currentProgress,
        int maximumProgress,
        int resets) {

        this.name = name;
        this.currentProgress = currentProgress;
        this.maximumProgress = maximumProgress;
        this.resets = resets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    public int getMaximumProgress() {
        return maximumProgress;
    }

    public void setMaximumProgress(int maximumProgress) {
        this.maximumProgress = maximumProgress;
    }

    public int getResets() {
        return resets;
    }

    public void setResets(int resets) {
        this.resets = resets;
    }

    public void addReset() {
        this.resets++;
    }
}
