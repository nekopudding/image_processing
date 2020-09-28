package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.ip.core.Image;
import ca.ubc.ece.cpen221.ip.core.ImageProcessingException;
import ca.ubc.ece.cpen221.ip.core.Rectangle;

import java.awt.Color;

/**
 * This datatype (or class) provides operations for transforming an image.
 *
 * <p>The operations supported are:
 * <ul>
 *     <li>The {@code ImageTransformer} constructor generates an instance of an image that
 *     we would like to transform;</li>
 *     <li></li>
 * </ul>
 * </p>
 */

public class ImageTransformer {

    private Image image;
    private int width;
    private int height;

    /**
     * Creates an ImageTransformer with an image. The provided image is
     * <strong>never</strong> changed by any of the operations.
     *
     * @param img is not null
     */
    public ImageTransformer(Image img) {
        width = img.width();
        height = img.height();
        image = img;
    }

    /**
     * Obtain the grayscale version of the image.
     *
     * @return the grayscale version of the instance.
     */
    public Image grayscale() {
        Image gsImage = new Image(width, height);
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Color color = image.get(col, row);
                Color gray = Image.toGray(color);
                gsImage.set(col, row, gray);
            }
        }
        return gsImage;
    }

    /**
     * Obtain a version of the image with only the red colours.
     *
     * @return a reds-only version of the instance.
     */
    public Image red() {
        Image redImage = new Image(width, height);
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int originalPixel = image.getRGB(col, row);
                int alpha = (originalPixel >> 24) & 0xFF;
                int red = (originalPixel >> 16) & 0xFF;
                int desiredColor = (alpha << 24) | (red << 16) | (0 << 8) | (0);
                redImage.setRGB(col, row, desiredColor);
            }
        }
        return redImage;
    }

    /**
     * Returns the mirror image of an instance.
     *
     * @return the mirror image of the instance.
     */
    public Image mirror() {
        Image mirImage = new Image(width, height);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < Math.ceil(width / 2.0); col++) {
                mirImage.setRGB(col, row, image.getRGB(width - col - 1, row));
                mirImage.setRGB(width - col - 1, row, image.getRGB(col, row));
            }
        }
        return mirImage;
    }

    /**
     * <p>Returns the negative version of an instance.<br />
     * If the colour of a pixel is (r, g, b) then the colours of the same pixel
     * in the negative of the image are (255-r, 255-g, 255-b).</p>
     *
     * @return the negative of the instance.
     */
    public Image negative() {
        Image negImage = new Image(width, height);
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int originalPixel = image.getRGB(col, row);
                int alpha = (originalPixel >> 24) & 0xFF;
                int red = (originalPixel >> 16) & 0xFF;
                int blue = (originalPixel >> 8) & 0xFF;
                int green = originalPixel & 0xFF;
                int desiredColor = (alpha << 24) | ((255 - red) << 16) | ((255 - blue) << 8) | (255 - green);
                negImage.setRGB(col, row, desiredColor);
            }
        }
        return negImage;
    }

    /**
     * <p>Returns the posterized version of an instance.<br />
     * For each pixel, each colour is analyzed independently to produce a new image as follows:
     * <ul>
     * <li>if the value of the colour is between 0 and 64 (limits inclusive), set it to 32;</li>
     * <li>if the value of the colour is between 65 and 128, set it to 96;</li>
     * <li>if the value of the colour is between 129 and 255, set it to 222.</li>
     * </ul>
     * </p>
     *
     * @return the posterized version of the instance.
     */
    public Image posterize() {
        Image posterImage = new Image(width, height);
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int originalPixel = image.getRGB(col, row);
                int alpha = (originalPixel >> 24) & 0xFF;
                int red = (originalPixel >> 16) & 0xFF;
                int blue = (originalPixel >> 8) & 0xFF;
                int green = originalPixel & 0xFF;

                if (red <= 64) {
                    red = 32;
                } else if (red <= 128) {
                    red = 96;
                } else {
                    red = 222;
                }

                if (blue <= 64) {
                    blue = 32;
                } else if (blue <= 128) {
                    blue = 96;
                } else {
                    blue = 222;
                }

                if (green <= 64) {
                    green = 32;
                } else if (green <= 128) {
                    green = 96;
                } else {
                    green = 222;
                }
                int desiredColor = (alpha << 24) | (red << 16) | (blue << 8) | (green);
                posterImage.setRGB(col, row, desiredColor);
            }
        }
        return posterImage;
    }

    /**
     * Clip the image given a rectangle that represents the region to be retained.
     *
     * @param clippingBox is not null.
     * @return a clipped version of the instance.
     * @throws ImageProcessingException if the clippingBox does not fit completely
     *                                  within the image.
     */
    public Image clip(Rectangle clippingBox) throws ImageProcessingException{
        if(clippingBox.xBottomRight > width || clippingBox.xTopLeft > width || clippingBox.yTopLeft > height || clippingBox.yBottomRight > height)
            throw new ImageProcessingException("clippingBox is out of bounds");
        int clipWidth = clippingBox.xBottomRight - clippingBox.xTopLeft + 1;
        int clipHeight = clippingBox.yBottomRight - clippingBox.yTopLeft + 1;
        Image clipImage = new Image(clipWidth, clipHeight);
        for (int row = 0; row < clipHeight; row++) {
            for (int col = 0; col < clipWidth; col++) {
                clipImage.setRGB(col, row, image.getRGB(col + clippingBox.xTopLeft, row + clippingBox.yTopLeft));
            }
        }
        return clipImage;
    }

    /**
     * Denoise an image by replacing each pixel by the median value of that pixel and
     * all its neighbouring pixels. During this process, each colour channel is handled
     * separately.
     *
     * @return a denoised version of the instance.
     */
    public Image denoise() {
        // TODO: Implement this method
        return null;
    }

    /**
     * Returns a weathered version of the image by replacing each pixel by the minimum value
     * of that pixel and all its neighbouring pixels. During this process, each colour channel
     * is handled separately.
     *
     * @return a weathered version of the image.
     */
    public Image weather() {
        // TODO: Implement this method
        return null;
    }

    /**
     * Return a block paint version of the instance by treating the image as a
     * sequence of squares of a given size and replacing all pixels in a square
     * by the average value of all pixels in that square.
     * During this process, each colour channel is handled separately.
     *
     * @param blockSize the dimension of the square block, > 1.
     * @return the block paint version of the instance.
     * When the original image is not a perfect multiple of blockSize * blockSize,
     * the bottom rows and right columns are obtained by averaging the pixels that
     * fit the smaller rectangular regions. For example, if we have a 642 x 642 size
     * original image and the block size is 4 x 4 then the bottom two rows will use
     * 2 x 4 blocks, the rightmost two columns will use 4 x 2 blocks, and the
     * bottom-right corner will use a 2 x 2 block.
     */
    public Image blockPaint(int blockSize) {
        // TODO: Implement this method
        return null;
    }

    /**
     * Rotate an image by the given angle (degrees) about the centre of the image.
     * The centre of an image is the pixel at (width/2, height/2). The new regions
     * that may be created are given the colour white (<code>#ffffff</code>) with
     * maximum transparency (alpha = 255).
     *
     * @param degrees the angle to rotate the image by, 0 <= degrees <= 360.
     * @return a rotate version of the instance.
     */
    public Image rotate(double degrees) {
        // TODO: Implement this method
        return null;
    }

    /**
     * Compute the discrete Fourier transform of the image and return the
     * amplitude and phase matrices as a DFTOutput instance.
     *
     * @return the amplitude and phase of the DFT of the instance.
     */
    public DFTOutput dft() {
        // TODO: Implement this method
        return null;
    }

    /**
     * Replaces a background screen with a provided image.
     * <p>
     * This operation identifies the largest connected region of the image that matches
     * <code>screenColour</code> exactly. This operation determines a rectangle that bounds
     * the "green screen" region and overlays the <code>backgroundImage</code> over that
     * rectangle by aligning the top-left corner of the image with the top-left corner of the
     * rectangle. After determining the screen region, all pixels in that region matching
     * <code>screenColour</code> are replaced with corresponding pixels from
     * <code>backgroundImage</code>.
     * <p>
     * If <code>backgroundImage</code> is smaller
     * than the screen then the image is tiled over the screen.
     *
     * @param screenColour    the colour of the background screen, is not null
     * @param backgroundImage the image to replace the screen with, is not null
     * @return an image with provided image replacing the background screen
     * of the specified colour, tiling the screen with the background image if the
     * background image is smaller than the screen size.
     */
    public Image greenScreen(Color screenColour, Image backgroundImage) {
        // TODO: Implement this method
        return null;
    }

    /**
     * Align (appropriately rotate) an image of text that was improperly aligned.
     * This transformation can work properly only with text images.
     *
     * @return the aligned image.
     */
    public Image alignTextImage() {
        // TODO: Implement this method
        return null;
    }
}
