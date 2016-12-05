package ua.nure.easygo.util;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;

/**
 * Created by Oleg on 27.11.2016.
 */
public class ImageHelper {

    private static String formPath(String image) {
        return "images/" + image + ".png";
    }

    public static void save(String image, byte[] bytes) throws IOException {
        BufferedImage im = ImageIO.read(new ByteArrayInputStream(bytes));
        int w=80, h=80;

        BufferedImage thumb = new BufferedImage( w, h, BufferedImage.TYPE_4BYTE_ABGR );
        Graphics2D tGraphics2D = thumb.createGraphics(); //create a graphics object to paint to
        //tGraphics2D.setBackground(  );
        //tGraphics2D.setPaint( Color.WHITE );
        //tGraphics2D.fillRect( 0, 0, tThumbWidth, tThumbHeight );
        tGraphics2D.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
        tGraphics2D.drawImage( im, 0, 0, w, h, null ); //draw the image scaled

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write( thumb, "PNG",  bos);

        File file = new File(formPath(image));
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bos.toByteArray());
        }
    }

    public static byte[] load(String image) throws IOException {
        File file = new File(formPath(image));

        byte[] bytes = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytes);
        }

        return bytes;
    }
}
