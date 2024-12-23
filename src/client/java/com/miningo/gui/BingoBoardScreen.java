package com.miningo.gui;

import com.miningo.bingo.BingoSlot;
import com.miningo.bingo.BingoTeam;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BingoBoardScreen extends Screen {

    // Example data for the bingo card
    private final List<BingoSlot> bingoSlots = new ArrayList<>();
    private static final int BOARD_SIZE = 5;
    private static final int SLOT_SIZE = 40;
    private static final int PADDING = 10;

    public BingoBoardScreen() {
        super(Text.of("Bingo Card"));
    }

    @Override
    protected void init() {
        super.init();

        int centerX = width / 2;
        int centerY = height / 2;

        // Handle left button click
        // TODO: Implement left button functionality
        ButtonWidget leftButton = ButtonWidget.builder(Text.of("<"), button -> {
            // Handle left button click
            // TODO: Implement left button functionality

        }).dimensions(centerX - 50 - 40, centerY + 100, 40, 20).build();
        addDrawableChild(leftButton);

        // Handle right button click
        // TODO: Implement right button functionality
        ButtonWidget rightButton = ButtonWidget.builder(Text.of(">"), button -> {
            // Handle right button click
            // TODO: Implement right button functionality
        }).dimensions(centerX + 10, centerY + 100, 40, 20).build();
        addDrawableChild(rightButton);


        // Initialize example bingo card data
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {

                String taskName = "Task " + (row + 1) + ":" + (col + 1);
                int progress = (row * 10) % 100;
                String leadingPlayer = "Player " + ((row % 2) + 1) + ":" + ((col % 2) + 1);
                String closestTeam = row % 2 == 0 ? "Red Team" : "Blue Team";
                List<BingoTeam> achievers = new ArrayList<>();

                BingoSlot slot = new BingoSlot(
                    taskName,
                    "this is a dummy task",
                    "",
                    progress,
                    leadingPlayer,
                    closestTeam,
                    achievers);

                bingoSlots.add(slot);
            }
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        if(client == null) return;

        // Constants for card layout
        int cardWidth = 300; // Width of the bingo card
        int cardHeight = 300; // Height of the bingo card
        int slotPadding = 5; // Space between slots
        int slotSize = (cardWidth - (6 * slotPadding)) / 5; // 5 slots per row/column
        int cardX = (this.width - cardWidth) / 2; // Center the card horizontally
        int cardY = (this.height - cardHeight) / 2; // Center the card vertically

        // Draw background for the bingo card
        context.fill(
                cardX - 5, cardY - 5,
                cardX + cardWidth + 5,
                cardY + cardHeight + 5,
                0xFF2B2B2B); // Dark gray border
        context.fill(
                cardX,
                cardY,
                cardX + cardWidth,
                cardY + cardHeight,
                0xFF404040); // Gray background

        // Iterate through bingo slots and draw them
        // TODO: fix out of bounds
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                int slotX = cardX + slotPadding + col * (slotSize + slotPadding);
                int slotY = cardY + slotPadding + row * (slotSize + slotPadding);

                BingoSlot slot = bingoSlots.get(row * 5 + col); // Assuming `bingoSlots` is a list of 25 slots

                // Draw slot background
                // Default gray or team's color
                //int backgroundColor = slot.getAchievers().isEmpty() ? 0xFF505050 : slot.getAchievers().get(0).getThemeColor();
                int backgroundColor = slot.getAchievers().isEmpty() ? 0xFF505050 : 0xFFFFFF00;
                context.fill(slotX, slotY, slotX + slotSize, slotY + slotSize, backgroundColor);

                // Draw task title
                context.drawCenteredTextWithShadow(
                        client.textRenderer,
                        slot.getTaskTitle(),
                        slotX + slotSize / 2,
                        slotY + 5,
                        0xFFFFFF
                );

                // Green progress bar
                int progressBarWidth = (int) (slotSize * (slot.getTaskProgress() / 100.0));
                context.fill(
                        slotX + 5,
                        slotY + slotSize - 15,
                        slotX + 5 + progressBarWidth,
                        slotY + slotSize - 5,
                        0xFF00FF00);

                // Remaining gray
                context.fill(
                        slotX + 5 + progressBarWidth,
                        slotY + slotSize - 15,
                        slotX + slotSize - 5,
                        slotY + slotSize - 5,
                        0xFF404040);

                // Draw progress percentage
                String progressText = slot.getTaskProgress() + "%";
                context.drawCenteredTextWithShadow(
                        client.textRenderer,
                        progressText,
                        slotX + slotSize / 2,
                        slotY + slotSize - 25,
                        0xFFFFFF
                );

                // Draw leading player's name
                String closestPlayerText = "Closest: " + slot.getLeadingPlayer();
                context.drawTextWithShadow(
                    client.textRenderer,
                    closestPlayerText,
                    slotX + 5,
                    slotY + slotSize - 35,
                    0xFFFFAA00
                );
            }
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
