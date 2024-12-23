package com.miningo.bingo;

import java.util.List;

public class TeamTask {
    private final String name;
    private final int color;
    private final String iconPath;
    private final List<BingoTeamMember> members;

    public TeamTask(
            String name,
            int color,
            String iconPath,
            List<BingoTeamMember> members) {
        this.name = name;
        this.color = color;
        this.iconPath = iconPath;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public String getIconPath() {
        return iconPath;
    }

    public List<BingoTeamMember> getPlayers() {
        return members;
    }


}