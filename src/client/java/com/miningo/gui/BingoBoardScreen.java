package com.miningo.gui;

import com.miningo.bingo.BingoSlot;
import com.miningo.bingo.BingoTeam;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.resource.ResourceFinder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BingoBoardScreen extends Screen {
    private ResourceFinder GUI_TEXTURE_FINDER = new ResourceFinder(
        "textures/gui/bingo/",
        ".png");

    private static final int GUI_WIDTH = 256;
    private static final int GUI_HEIGHT = 256;

    // Example data for the bingo card
    private final List<BingoSlot> bingoSlots = new ArrayList<>();
    private static final int BOARD_SIZE = 5;
    private static final int SLOT_SIZE = 40;
    private static final int PADDING = 10;

    // TODO: update these
    private final int COLOR_DARK_TEXT = 0x404040;
    private final int COLOR_SLOT_BACKGROUND = 0x8B8B8B;
    private final int COLOR_SLOT_HOVERED = 0xFF00FF00;
    private final int COLOR_SLOT_SELECTED = 0xFF0000FF;

    private int currentPage = 0;

    public BingoBoardScreen(List<BingoSlot> bingoSlots) {
        super(Text.translatable("gui.bingo.board.title"));
    }

    @Override
    protected void init() {
        super.init();

        int centerX = width / 2;
        int centerY = height / 2;

        Text leftButtonText = Text.of("<");
        ButtonWidget leftButton = ButtonWidget.builder(leftButtonText, button -> {
            // TODO: Implement left button functionality
            if(currentPage > 0) {
                currentPage --;
            }
        })
            .dimensions(centerX - 50 - 40, centerY + 100, 40, 20)
            .tooltip(Tooltip.of(Text.literal("Previous page")))
            .build();
        addDrawableChild(leftButton);

        Text rightButtonText = Text.of(">");
        ButtonWidget rightButton = ButtonWidget.builder(rightButtonText, button -> {
            // TODO: Implement right button functionality
            int maxPage = (bingoSlots.size() - 1) / calculateVisibleSlots();
            if(currentPage < maxPage - 1) {
                currentPage++;
            }
        })
            .dimensions(centerX + 10, centerY + 100, 40, 20)
            .tooltip(Tooltip.of(Text.literal("Next page")))
            .build();
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

        Identifier TEXTURE = Identifier.of("miningo", "textures/gui/base.png");
        context.drawTexture(
            RenderLayer::getGuiTextured,
            TEXTURE,
            0,
            0,
            0,
            0,
            100,
            100,
            256,
            256);


        // Iterate through bingo slots and draw them
        // Initialize example bingo card data
        int columns = width / (SLOT_SIZE + PADDING);
        int xStart = (width - (columns * (SLOT_SIZE + PADDING))) / 2;
        int yStart = PADDING;
        List<BingoSlot> visibleSlots = getVisibleSlots();

        for(int i = 0; i < visibleSlots.size(); i++) {
            int col = i % columns;
            int row = i / columns;

            int slotX = xStart + col * (SLOT_SIZE + PADDING);
            int slotY = yStart + row * (SLOT_SIZE + PADDING);
            BingoSlot slot = visibleSlots.get(i);

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

            ProgressBar pb = new ProgressBar(
                slotX + 5,
                progressBarY1,
                slotX + slotSize - 5 - 12,
                progressBarY2,
                (float) slot.getTaskProgress() / 100,
                1,
                0xFFFFFFFF,
                0xFF00FF00,
                0xFF404040);
            pb.pack(context);

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

    @Override
    public boolean shouldPause() {
        return false;
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

    private int calculateVisibleSlots() {
        int availableWidth = width - (2 * PADDING);
        int availableHeight = height - (2 * PADDING);

        int columns = availableWidth / (SLOT_SIZE + PADDING);
        int rows = availableHeight / (SLOT_SIZE + PADDING);

        return rows * columns;
    }

    private List<BingoSlot> getVisibleSlots() {
        int visibleSlots = calculateVisibleSlots();
        int start = currentPage * visibleSlots;
        int end = Math.min(start + visibleSlots, bingoSlots.size());

        return bingoSlots.subList(start, end);
    }
}

// eof