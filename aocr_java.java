import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;
import org.tensorflow.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.*;
import java.nio.*;
import java.util.*;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.charset.Charset;

public class aocr_java{

    private static String imagepath = "images/test.png";
    private static String modelpath = "aocr_frozen_graph/";
    private static byte[] graphDef;
    private static byte[] imageBytes;

    private static byte[] readAllBytesOrExit(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            System.err.println("Failed to read [" + path + "]: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }


    private static String executeAOCRGraph(byte[] graphDef, Tensor image) {
        try (Graph g = new Graph()) {
            g.importGraphDef(graphDef);
            try (Session s = new Session(g);
                Tensor result = s.runner().feed("input_image_as_bytes", image).fetch("prediction").run().get(0)) {

                String rstring = new String(result.bytesValue(), Charset.forName("UTF-8"));


                return rstring;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("TensorFlow Version" + TensorFlow.version());

        graphDef = readAllBytesOrExit(Paths.get(modelpath, "frozen_model_temp.pb"));
        imageBytes = readAllBytesOrExit(Paths.get(imagepath));


        try (Tensor image = Tensor.create(imageBytes)) {
            String result = executeAOCRGraph(graphDef, image);

            System.out.println(result);

            }
    }
}
