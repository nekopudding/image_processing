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
        Image originalImg = new Image("resources/dft1.png");
        ImageTransformer t = new ImageTransformer(originalImg);
        DFTOutput out = t.dft();
        DoubleMatrix amp = out.getAmp();
        DoubleMatrix phase = out.getPhase();

        int width = 5;
        int height = 5;

        double[][] Amp = new double[height][width];
        double[][] Phase = new double[height][width];
        double OutImage;

        //turn matrix back into double array, print the real part of dft
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Amp[row][col] = amp.get(row,col);
                Phase[row][col] = phase.get(row,col);
                OutImage = Amp[row][col]*Math.cos(Phase[row][col]);
                System.out.printf(" %.2f ", OutImage);
            }
            System.out.println(" ");
        }
    }
}
