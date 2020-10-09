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
        double cosSim = cosineSimilarity(expectedImg, outputImage);
        if (Math.abs(cosSim) > (1 - 1e-7)) {
            System.out.println("Passed");
        } else
            System.out.println("Failed");
    }

    @Test
    public void test_DFT() {
        Image originalImg = new Image("resources/dft3.jpg");
        ImageTransformer t = new ImageTransformer(originalImg);
        DFTOutput out = t.dft();
        DoubleMatrix amp = out.getAmp();
        DoubleMatrix phase = out.getPhase();

        int width = originalImg.width();
        int height = originalImg.height();

        double actualAmp;
        double actualPhase;
        boolean failed = false;

        double[][] expectedAmp = {{510, 10}, {10, 478}};
        double[][] expectedPhase = {{0, 0}, {0, 0}};

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                actualAmp = amp.get(row, col);
                actualPhase = phase.get(row, col);

                if (Math.abs(actualAmp - expectedAmp[row][col]) > 1e-7) {
                    failed = true;
                    System.out.printf("%n Test failed with magnitude at row:%i col: %i", row, col);
                }
                if (Math.abs(actualPhase - expectedPhase[row][col]) > 1e-7) {
                    failed = true;
                    System.out.printf("%n Test failed with phase at row:%i col: %i", row, col);
                }
            }
        }
        if (failed == false) {
            System.out.printf("Passed");
        } else {
            System.out.printf("Failed");
        }

        /*
        //Debugging: Print amp
        double Amp;
        double Phase;
        System.out.printf("%n Magnitude: %n");
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Amp = amp.get(row,col);
                System.out.printf(" %.2f ", Amp);
            }
            System.out.println(" ");
        }
        //print Phase
        System.out.printf("%n Phase: %n");
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Phase = phase.get(row,col);
                System.out.printf(" %.2f ", Phase);
            }
            System.out.println(" ");
        }
         */
    }
}