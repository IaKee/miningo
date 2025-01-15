package com.miningo.gui;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class SlotContainer {
    // TODO: this should be the ui of a bingo slot container on the bingo board
    // this should be different than the other implementation of a bingo slot
    // as it should only be a visual representation instead of being the main
    // one in charge of the task data and progress
    // TODO: tooltip with rewards, etc
    // allows up to 3 items as its icon

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    private String backgroundSpritePath;

    private String taskTitle;

    private Item taskItem1;
    private Item taskItem2;
    private Item taskItem3;

    private ProgressBar pb;

    public SlotContainer(
            int x1,
            int y1,
            int x2,
            int y2,
            String backgroundSpritePath,
            String taskTitle,
            Item taskItem1,
            Item taskItem2,
            Item taskItem3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.backgroundSpritePath = backgroundSpritePath;
        this.taskTitle = taskTitle;
        this.taskItem1 = taskItem1;
        this.taskItem2 = taskItem2;
        this.taskItem3 = taskItem3;

        // TODO: init progressbar
        // this.pb = new ProgressBar(x1, y1, x2, y2, 100, 100);
    }
    public void render() {

    }

        /*
        for(int i = 0; i < visibleSlots.size(); i++) {
        int col = i % columns;
        int row = i / columns;

        int slotX = xStart + col * (SLOT_SIZE + PADDING);
        int slotY = yStart + row * (SLOT_SIZE + PADDING);
        com.miningo.bingo.BingoSlot slot = visibleSlots.get(i);

        // Draw slot background
        // Default gray or team's color
        //int backgroundColor = slot.getAchievers().isEmpty() ? 0xFF505050 : slot.getAchievers().get(0).getThemeColor();
        //int backgroundColor = slot.getAchievers().isEmpty() ? 0xFF505050 : 0xFFFFFF00;
        //context.fill(slotX, slotY, slotX + slotSize, slotY + slotSize, backgroundColor);

        drawScaledCenteredTextWithShadow(
                context,
                client.textRenderer,
                slot.getTaskTitle(),
                slotX + slotSize / 2,
                slotY + 5,
                0.6f,
                0xFFFFFF);

        Item appleItem = Items.WAXED_WEATHERED_CUT_COPPER_STAIRS;
        ItemStack appleStack = new ItemStack(appleItem);
        context.drawItem(
                appleStack,
                slotX + (int) (slotSize/2) - 8,
                slotY + 12);

        // Green progress bar
        int progressBarWidth = (int) (slotSize * (slot.getTaskProgress() / 100.0));
        int progressBarHeight = 6;
        int progressBarY1 = slotY + slotSize - 15;
        int progressBarY2 = progressBarY1 + progressBarHeight;

        drawProgressBar(
                context,
                slotX + 5,
                progressBarY1,
                slotX + slotSize - 5 - 12,
                progressBarY2,
                (float) slot.getTaskProgress() / 100,
                1,
                0xFFFFFFFF,
                0xFF00FF00,
                0xFF404040);


        // Draw progress percentage
        String progressText = slot.getTaskProgress() + "%";
        drawScaledTextWithShadow(
                context,
                client.textRenderer,
                progressText,
                slotX + slotSize - 12,
                progressBarY1 + 1,
                0.5f,
                0xFFFFFF);

        // Draw leading player's name
        String closestPlayerText = "Top 1: " + slot.getLeadingPlayer();
        drawScaledTextWithShadow(
                context,
                client.textRenderer,
                closestPlayerText,
                slotX + 5,
                slotY + slotSize - 6,
                0.5f,
                0xFFFFAA00);
    }
    */
}
