package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.*;

public class task2Tests {
    @Test
    public void test_BlockPaint_4x4() {
        Image originalImg = new Image("resources/95006.jpg");
        Image expectedImg = new Image("resources/tests/95006-seurat-4x4.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.blockPaint(4);
        assertEquals(expectedImg, outputImage);
    }

    @Test
    public void test_BlockPaint_3x3() {
        Image originalImg = new Image("resources/216053.jpg");
        Image expectedImg = new Image("resources/tests/216053-seurat-3x3.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.blockPaint(3);
        assertEquals(expectedImg, outputImage);
    }

    @Test
    public void test_Denoise() {
        Image originalImg = new Image(3, 3);

        Color currColor = new Color(255, 100, 100);
        originalImg.set(0, 0, currColor);

        currColor = new Color(30, 15, 100);
        originalImg.set(1, 0, currColor);

        currColor = new Color(200, 200, 200);
        originalImg.set(2, 0, currColor);

        currColor = new Color(0, 1, 0);
        originalImg.set(0, 1, currColor);

        currColor = new Color(0, 0, 100);
        originalImg.set(1, 1, currColor);

        currColor = new Color(150, 0, 0);
        originalImg.set(2, 1, currColor);

        currColor = new Color(200, 200, 100);
        originalImg.set(0, 2, currColor);

        currColor = new Color(10, 150, 200);
        originalImg.set(1, 2, currColor);

        currColor = new Color(100, 100, 255);
        originalImg.set(2, 2, currColor);

        Image expectedImage = new Image(3, 3);

        currColor = new Color(15, 8, 100);
        expectedImage.set(0, 0, currColor);

        currColor = new Color(90, 8, 100);
        expectedImage.set(1, 0, currColor);

        currColor = new Color((float) 90.0 / 255, (float) 7.5 / 255, (float) 100.0 / 255);
        expectedImage.set(2, 0, currColor);

        currColor = new Color((float) 20.0 / 255, (float) 57.5 / 255, (float) 100.0 / 255);
        expectedImage.set(0, 1, currColor);

        currColor = new Color(100, 100, 100);
        expectedImage.set(1, 1, currColor);

        currColor = new Color((float) 65.0 / 255, (float) 57.5 / 255, (float) 150.0 / 255);
        expectedImage.set(2, 1, currColor);

        currColor = new Color((float) 5.0 / 255, (float) 75.5 / 255, (float) 100.0 / 255);
        expectedImage.set(0, 2, currColor);

        currColor = new Color((float) 55.0 / 255, (float) 50.5 / 255, (float) 100.0 / 255);
        expectedImage.set(1, 2, currColor);

        currColor = new Color(55, 50, 150);
        expectedImage.set(2, 2, currColor);

        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.denoise();
        assertEquals(expectedImage, outputImage);

    }
}
