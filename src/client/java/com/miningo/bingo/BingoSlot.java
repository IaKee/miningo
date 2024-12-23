package com.miningo.bingo;

import java.util.ArrayList;
import java.util.List;

public class BingoSlot {
    private final String taskTitle;
    private final String taskDescription;
    private final String taskIconPath;
    private final int taskProgress;
    private final String leadingPlayer;
    private final String leadingTeam;
    private final List<BingoTeam> completedTeams;

    public BingoSlot(
            String taskTitle,
            String taskDescription,
            String taskIconPath,
            int taskProgress,
            String leadingPlayer,
            String leadingTeam,
            List<BingoTeam> completedTeams) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskIconPath = taskIconPath;

        // Ensures progress value is contained between 0 and 100
        this.taskProgress = Math.max(0, Math.min(taskProgress, 100));

        this.leadingPlayer = leadingPlayer;
        this.leadingTeam = leadingTeam;

        this.completedTeams = completedTeams;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getTaskIconPath() {
        return taskIconPath;
    }


    public String getLeadingPlayer() {
        return leadingPlayer;
    }

    public String getLeadingTeam() {
        return leadingTeam;
    }

    public int getTaskProgress() {
        return taskProgress;
    }

    public List<BingoTeam> getAchievers() {
        // returns the list of teams who have completed the task
        List<BingoTeam> achievers = new ArrayList<>();
        for (BingoTeam team : completedTeams) {
            if (team.hasCompleted()) {
                achievers.add(team);
            }
        }
        return achievers;
    }
}
