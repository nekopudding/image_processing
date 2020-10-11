package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;

import java.awt.*;

/**
 * This class provides some simple operations involving
 * more than one image.
 */
public class ImageProcessing {

    /**
     * Compute the cosine similarity between two images.
     *
     * @param img1 : the first image, is not null.
     * @param img2 : the second image, in not null and matches img1 in dimensions.
     * @return the cosine similarity between the Images
     * referenced by img1 and img2.
     */
    /*
     change image to a 1D array of length width * height,
     each grayscaled pixel as the entries in the vector
     */
    public static double cosineSimilarity(Image img1, Image img2) {
        // TODO: Implement this method
        int width = img1.width();
        int height = img1.height();
        int width2 = img2.width();
        int height2 = img2.height();

        int tone1;
        int tone2;

        ImageTransformer t1 = new ImageTransformer(img1);
        ImageTransformer t2 = new ImageTransformer(img2);
        Image gsImg1 = new Image(t1.grayscale());
        Image gsImg2 = new Image(t2.grayscale());

        if (width != width2 || height != height2) {
            throw new IllegalArgumentException(
                    "images should have the same dimensions"
            );
        }
        int[] vec1 = new int[width * height];
        int[] vec2 = new int[width * height];

        //convert images to 1D array
        for (int rowN = 0; rowN < height; rowN++) {
            for (int colN = 0; colN < width; colN++) {
                Color color1 = new Color(gsImg1.getRGB(colN, rowN));
                Color color2 = new Color(gsImg2.getRGB(colN, rowN));
                tone1 = color1.getRed();
                tone2 = color2.getRed();
                vec1[rowN * width + colN] = tone1;
                vec2[rowN * width + colN] = tone2;
            }
        }
        //compare by cosine similarity
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        double mag;

        for (int i = 0; i < vec1.length; i++) {
            dotProduct += vec1[i] * vec2[i];
            normA += Math.pow(vec1[i], 2);
            normB += Math.pow(vec2[i], 2);
        }
        mag = Math.sqrt(normA) * Math.sqrt(normB);
        System.out.printf("Dot Product: %.2f Magnitude: %.2f", dotProduct, mag);
        if(Math.abs(dotProduct) < 1e-7 && Math.abs(mag) < 1e-7) {
            return 1;
        } else {
            return dotProduct / mag;
        }
    }
}
