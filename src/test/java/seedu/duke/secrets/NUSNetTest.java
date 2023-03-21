package seedu.duke.secrets;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 **/
public class NUSNetTest {
    @Test
    public void NUSNetNoFolder() {
        NUSNet nusNet = new NUSNet("NUSNet Name", "e088888@u.nus.edu","aSecurePassword2");
        assertEquals("NUSNet Name", nusNet.getName());
        assertEquals("unnamed", nusNet.getFolderName());
        assertEquals("e088888@u.nus.edu", nusNet.getNUSNet_ID());
        assertEquals("aSecurePassword2", nusNet.getPassword());
    }

    @Test
    public void NUSNetFolder() {
        NUSNet nusNet = new NUSNet("NUSNet Name 2", "FolderName", "e081888@u.nus.edu", "Lorem Ipsum 12");
        assertEquals("NUSNet Name 2", nusNet.getName());
        assertEquals("FolderName", nusNet.getFolderName());
        assertEquals("e081888@u.nus.edu", nusNet.getNUSNet_ID());
        assertEquals("Lorem Ipsum 12", nusNet.getPassword());
    }

}
