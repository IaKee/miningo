package com.miningo.gui;

import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;

public class ProgressBar {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private float progress;
    private int borderWidth;
    private int borderColor;
    private int fillColor;
    private int backgroundColor;

    public ProgressBar(
            int x1,
            int y1,
            int x2,
            int y2,
            float progress,
            int borderWidth,
            int borderColor,
            int fillColor,
            int backgroundColor) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        setProgress(progress);
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
    }

    public ProgressBar(
            int x,
            int y,
            int width,
            int height,
            float progress,
            int borderWidth,
            int borderColor,
            int fillColor,
            int backgroundColor,
            boolean useDimensions) {
        this(x, y, x + width, y + height, progress, borderWidth, borderColor, fillColor, backgroundColor);
    }

    public float get() {
        return progress;
    }

    public void setProgress(float progress) {
        if(progress < 0.0f || progress > 1.0f) {
            throw new IllegalArgumentException("Progress must be a value between 0.0f and 1.0f (inclusive).");
        }
        this.progress = progress;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
    }

    public void pack(@NotNull DrawContext context) {
        // border
        context.fill(
                x1 - borderWidth,
                y1 - borderWidth,
                x2 + borderWidth,
                y2 + borderWidth,
                borderColor);

        // bar background (first bar)
        context.fill(
                x1,
                y1,
                x2,
                y2,
                backgroundColor);

        int progressWidth = (int) ((x2 - x1) * progress);

        // progress indicator (second bar)
        context.fill(
                x1,
                y1,
                x1 + progressWidth,
                y2,
                fillColor);
    }

    public void resize(int width, int height) {
        this.x2 = this.x1 + width;
        this.y2 = this.y1 + height;
    }

    public void resize(int x1,  int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void move(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}

// eof