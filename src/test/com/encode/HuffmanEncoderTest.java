package test.com.encode;

import com.encode.HuffmanEncoder;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HuffmanEncoderTest {

    @Test
    public void testHuffmanEncodingAndDecoding() {
        final String originalData = "abcdeffg";
        final HuffmanEncoder encoder = new HuffmanEncoder();
        final HuffmanEncoder.HuffmanEncodedResult result = encoder.compress(originalData);
        final String decodedData = encoder.decompress(result);

        assertEquals(originalData, decodedData);
    }
}
