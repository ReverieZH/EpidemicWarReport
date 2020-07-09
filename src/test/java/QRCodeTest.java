import com.google.zxing.NotFoundException;
import com.google.zxing.qrcode.encoder.QRCode;
import com.qidian.utils.GetQRCode;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class QRCodeTest {

    @Test
    public void qr(){
    }
    @Test
    public void getContent() throws IOException, NotFoundException {
        GetQRCode.QRReader(new File("src/main/webapp/img/qrform16.png"));
    }
}
