package com.clearfit;

import java.io.*;

import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;

public class StringToJPEG {

  public static byte[] call(String str) throws Exception {
    byte[] retval = null;

    // Create a JPEG transcoder
    JPEGTranscoder t = new JPEGTranscoder();

    // Set the transcoding hints.
    t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY,
        new Float(.8));

    // Create the transcoder input.
    InputStream is = new ByteArrayInputStream(str.getBytes());
    TranscoderInput input = new TranscoderInput(is);

    // Create the transcoder output.
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    TranscoderOutput output = new TranscoderOutput(out);

    // Save the image.
    t.transcode(input, output);

    // Flush and close the stream.
    out.flush();
    retval = out.toByteArray();
    out.close();

    return retval;
  }
}
