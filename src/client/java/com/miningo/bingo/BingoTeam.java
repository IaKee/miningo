package com.miningo.bingo;

import java.util.List;

public class BingoTeam {
    private final String name;
    private final String themeColor;
    private final String iconPath;
    private final List<BingoTeamMember> members;

    public BingoTeam(String name, String themeColor, String iconPath, List<BingoTeamMember> members) {
        this.name = name;
        this.themeColor = themeColor;
        this.iconPath = iconPath;
        this.members = members;
    }
    public String getName() {
        return name;
    }
    public String getThemeColor() {
        return themeColor;
    }
    public String getIconPath() {
        return iconPath;
    }

    public List<BingoTeamMember> getMembers() {
        return members;
    }

    public boolean isEmpty() {
        return members.isEmpty();
    }

    public boolean hasPlayer(String playerName) {
        // iterates through every member in the team looking for a match
        for (BingoTeamMember member : members) {
            if (member.getName().equalsIgnoreCase(playerName)) {
                return true;
            }
        }
        return false;
    }

    public int getResets() {
        int resets = 0;
        for (BingoTeamMember member : members) {
            resets += member.getResets();
        }
        return resets;
    }

    public BingoTeamMember getClosest() {
        // returns the player who is closest to completing the task
        BingoTeamMember closest = null;
        for (BingoTeamMember member : members) {
            if (closest == null) {
                closest = member;
                continue;
            }

            if (member.getCurrentProgress() > closest.getCurrentProgress()) {
                closest = member;
            }
        }
        return closest;
    }

    public boolean hasCompleted() {
        // returns true if the task has been completed
        for (BingoTeamMember player : members) {
            if (player.getMaximumProgress() >= 100) {
                return true;
            }
        }
        return false;
    }

    public void addMember(BingoTeamMember member) {
        members.add(member);
    }

    public void removeMember(BingoTeamMember member) {
        members.remove(member);
    }
}
