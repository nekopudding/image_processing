package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.DoubleMatrix;
import ca.ubc.ece.cpen221.ip.core.Image;
import org.junit.Test;

import java.awt.*;

import static ca.ubc.ece.cpen221.ip.mp.ImageProcessing.cosineSimilarity;
import static org.junit.Assert.assertEquals;

public class task34Tests {
    @Test
    public void test_Rotation() {
        Image originalImg = new Image("resources/12003.jpg");
        Image expectedImg = new Image("resources/tests/12003-r30.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        Image outputImage = t.rotate(30);
        System.out.println(cosineSimilarity(expectedImg, outputImage));
    }

    @Test
    public void test_DFT() {
        Image originalImg = new Image("resources/dft2.jpg");
        ImageTransformer t = new ImageTransformer(originalImg);
        DFTOutput out = t.dft();
        DoubleMatrix amp = out.getAmp();
        DoubleMatrix phase = out.getPhase();

        int width = originalImg.width();
        int height = originalImg.height();

        double Amp;
        double Phase;

        //Print amp
        System.out.println("Magnitude:");
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Amp = amp.get(row,col);
                System.out.printf(" %.2f ", Amp);
            }
            System.out.println(" ");
        }
        //print Phase
        System.out.println("Phase:");
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Phase = phase.get(row,col);
                System.out.printf(" %.2f ", Phase);
            }
            System.out.println(" ");
        }
    }
}