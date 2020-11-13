package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.Test;

import static org.junit.Assert.*;

public class Level2Tests {

    @Test
    public void test_Weathering() {
        Image originalImg = new Image("resources/tests/0001.png");
        Image expectedImg = new Image("resources/tests/0001-weather.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.weather();
        assertEquals(expectedImg, outputImage);
    }

    // @Test
    // public void test_Denoise() {
    //     Image originalImg = new Image("resources/tests/0001.png");
    //     Image expectedImg = new Image("resources/tests/0001-denoise.png");
    //     ImageTransformer t = new ImageTransformer(originalImg);
    //     Image outputImage = t.denoise();
    //     assertEquals(expectedImg, outputImage);
    // }

    @Test
    public void test_Block() {
        Image originalImg = new Image("resources/tests/0001.png");
        Image expectedImg = new Image("resources/tests/0001-block-10.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.blockPaint(10);
        assertEquals(expectedImg, outputImage);
    }

    @Test
    public void test_LargeBlock() {
        Image originalImg = new Image("resources/tests/0001.png");
        Image expectedImg = new Image("resources/tests/0001-block-100.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.blockPaint(100);
        assertEquals(expectedImg, outputImage);
    }

}
