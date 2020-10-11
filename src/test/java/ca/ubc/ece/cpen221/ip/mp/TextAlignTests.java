package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.Test;
import static org.junit.Assert.*;
public class TextAlignTests {

    @Test
    public void test_45Text() {
        Image originalImg = new Image("resources/Text_45.jpg");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImg = t.alignTextImage();
        Image expectedImg1;
        expectedImg1 = t.rotate(42);
        Image expectedImg2;
        expectedImg2 = t.rotate(43);
        Image expectedImg3;
        expectedImg3 = t.rotate(44);
        Image expectedImg4;
        expectedImg4 = t.rotate(45);
        Image expectedImg5;
        expectedImg5 = t.rotate(46);
        Image expectedImg6;
        expectedImg6 = t.rotate(47);
        Image expectedImg7;
        expectedImg7 = t.rotate(48);
        assertTrue(outputImg.equals(expectedImg1)|| outputImg.equals(expectedImg2) ||outputImg.equals(expectedImg3)||
        outputImg.equals(expectedImg4)||outputImg.equals(expectedImg5)||outputImg.equals(expectedImg6)||outputImg.equals(expectedImg7));

    }

    @Test
    public void test_15Text() {
        Image originalImg = new Image("resources/Text_15.jpg");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImg = t.alignTextImage();
        Image expectedImg1;
        expectedImg1 = t.rotate(12);
        Image expectedImg2;
        expectedImg2 = t.rotate(13);
        Image expectedImg3;
        expectedImg3 = t.rotate(14);
        Image expectedImg4;
        expectedImg4 = t.rotate(15);
        Image expectedImg5;
        expectedImg5 = t.rotate(16);
        Image expectedImg6;
        expectedImg6 = t.rotate(17);
        Image expectedImg7;
        expectedImg7 = t.rotate(18);
        assertTrue(outputImg.equals(expectedImg1)|| outputImg.equals(expectedImg2) ||outputImg.equals(expectedImg3)||
                outputImg.equals(expectedImg4)||outputImg.equals(expectedImg5)||outputImg.equals(expectedImg6)||outputImg.equals(expectedImg7));

    }

    @Test
    public void test_123Text() {
        Image originalImg = new Image("resources/Text_123.jpg");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImg = t.alignTextImage();
        Image expectedImg1;
        expectedImg1 = t.rotate(30);
        Image expectedImg2;
        expectedImg2 = t.rotate(31);
        Image expectedImg3;
        expectedImg3 = t.rotate(32);
        Image expectedImg4;
        expectedImg4 = t.rotate(33);
        Image expectedImg5;
        expectedImg5 = t.rotate(34);
        Image expectedImg6;
        expectedImg6 = t.rotate(35);
        Image expectedImg7;
        expectedImg7 = t.rotate(36);

        assertTrue(outputImg.equals(expectedImg1)|| outputImg.equals(expectedImg2) ||outputImg.equals(expectedImg3)||
                outputImg.equals(expectedImg4)||outputImg.equals(expectedImg5)||outputImg.equals(expectedImg6)||outputImg.equals(expectedImg7));

    }

    @Test
    public void test_0Text() {
        Image originalImg = new Image("resources/Original_Text.jpg");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImg = t.alignTextImage();
        Image expectedImg;
        expectedImg = t.rotate(90);
        assertEquals(outputImg, expectedImg);
    }

    @Test
    public void test_90Text() {
        Image originalImg = new Image("resources/Text_L.jpg");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImg = t.alignTextImage();
        Image expectedImg;
        expectedImg = t.rotate(90);
        assertEquals(outputImg, expectedImg);
    }



}
