package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import ca.ubc.ece.cpen221.ip.core.Rectangle;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class TestUtils {
    public static Image clip(Image image, Rectangle clippingBox) {

        int clippingBoxWidth = clippingBox.xBottomRight - clippingBox.xTopLeft;
        int clippingBoxHeight = clippingBox.yBottomRight - clippingBox.yTopLeft;
        Image clippedImage = new Image(clippingBoxWidth + 1, clippingBoxHeight + 1);
        for (int x = clippingBox.xTopLeft; x <= clippingBox.xBottomRight; x++) {
            for (int y = clippingBox.yTopLeft; y <= clippingBox.yBottomRight; y++) {
                int originalRGB = image.getRGB(x, y);
                clippedImage
                        .setRGB(x - clippingBox.xTopLeft, y - clippingBox.yTopLeft, originalRGB);
            }
        }

        return clippedImage;
    }

    /*
     * @param array that contains the elements of a vector in order.
     * @return the magnitude of the vector
     */
    private static double magnitude(int[] array) {
        return Math.sqrt(Arrays.stream(array).map(j -> (int) Math.pow(j, 2)).sum());
    }

    /*
     * @param array1 that contains the elements of a vector in order.
     * @param array2 contains the elements of vector and has same length as array 1
     * @return the dot product of the 2 vectors
     */
    private static int dotProduct(int[] array1, int[] array2) {
        return IntStream.range(0, array1.length).map(i -> array1[i] * array2[i]).sum();
    }

    public static double cosineSimilarity(Image img1, Image img2) {
        Image gray1 = img1.grayscale();
        Image gray2 = img2.grayscale();
        int height = gray1.height();
        int width = gray2.width();
        int size = height * width;

        int[] array1 = new int[size];
        int[] array2 = new int[size];
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel1 = gray1.get(j, i);
                Color pixel2 = gray2.get(j, i);
                array1[count] = pixel1.getBlue();
                array2[count] = pixel2.getBlue();
                count++;
            }
        }

        if (Arrays.equals(array1, array2)) return 1.0;

        int product = dotProduct(array1, array2);
        double mag1 = magnitude(array1);
        double mag2 = magnitude(array2);

        return mag1 == 0 || mag2 == 0 ? 0 : product / (mag1 * mag2);

    }

    public static boolean rotationMatch(Image expectedImg, Image outputImage, double thresh) {
        Image expectedCropped = TestUtils.clip(expectedImg,
                new Rectangle(0, 0,
                        Math.min(expectedImg.width(), outputImage.width() - 1),
                        Math.min(expectedImg.height(), outputImage.height()) - 1));

        Image outputCropped = TestUtils.clip(outputImage,
                new Rectangle(0, 0,
                        Math.min(expectedImg.width(), outputImage.width()) - 1,
                        Math.min(expectedImg.height(), outputImage.height()) - 1));
        return cosineSimilarity(outputCropped, expectedCropped) > thresh;
    }

}
