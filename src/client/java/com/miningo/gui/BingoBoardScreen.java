package com.miningo.gui;

import com.miningo.bingo.BingoSlot;
import com.miningo.bingo.BingoTeam;
import net.minecraft.client.font.TextHandler;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

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

                // Assuming `bingoSlots` is a list of 25 slots
                BingoSlot slot = bingoSlots.get(row * 5 + col);

                // Draw slot background
                // Default gray or team's color
                //int backgroundColor = slot.getAchievers().isEmpty() ? 0xFF505050 : slot.getAchievers().get(0).getThemeColor();
                int backgroundColor = slot.getAchievers().isEmpty() ? 0xFF505050 : 0xFFFFFF00;
                context.fill(slotX, slotY, slotX + slotSize, slotY + slotSize, backgroundColor);

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
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    private void drawProgressBar(
            @NotNull DrawContext context,
            int x1,
            int y1,
            int x2,
            int y2,
            float progress,
            int borderWidth,
            int borderColor,
            int fillColor,
            int backgroundColor) {

        // border
        context.fill(
            x1 - borderWidth,
            y1 - borderWidth,
            x2 + borderWidth,
            y2 + borderWidth,
            borderColor);

        // background
        context.fill(
            x1,
            y1,
            x2,
            y2,
            backgroundColor);

        int progressWidth = (int) ((x2 - x1) * progress);

        // main colored progress indicator
        context.fill(
            x1,
            y1,
            x1 + progressWidth,
            y2,
            fillColor);
    }

    private void drawScaledCenteredTextWithShadow(
            @NotNull DrawContext context,
            TextRenderer renderer,
            String contents,
            int x,
            int y,
            float scaleFactor,
            int color) {
        context.getMatrices().push();

        context.getMatrices().scale(scaleFactor, scaleFactor, scaleFactor);
        int scaledX = (int) (x / scaleFactor);
        int scaledY = (int) (y / scaleFactor);
        context.drawCenteredTextWithShadow(
            renderer,
            contents,
            scaledX,
            scaledY,
            color);
        context.getMatrices().pop();
    }

    private void drawScaledTextWithShadow(
            @NotNull DrawContext context,
            TextRenderer renderer,
            String contents,
            int x,
            int y,
            float scaleFactor,
            int color) {
        context.getMatrices().push();

        context.getMatrices().scale(scaleFactor, scaleFactor, scaleFactor);
        int scaledX = (int) (x / scaleFactor);
        int scaledY = (int) (y / scaleFactor);
        context.drawTextWithShadow(
                renderer,
                contents,
                scaledX,
                scaledY,
                color);
        context.getMatrices().pop();
    }
}
