package Components.Middle;
import java.io.IOException;
import Framework.CommonFilterImpl;

public class AddCourseMiddleFilter  extends CommonFilterImpl{

    private String wantToAdd = "" ;

    public AddCourseMiddleFilter(String wantToAdd) {
        this.wantToAdd = wantToAdd;
    }

    @Override
    public boolean specificComputationForFilter() throws IOException { // 12345 &23456 &17651 filter
        int numOfBlank = 0;
        int checkBlank = 3;
        int idx = 0;
        byte[] buffer = new byte[64];
        int byte_read = 0;


        boolean upperChecker = false;
        boolean hasWTF = false;
        int cNumPoint = 0;


        while (true) {
            while(byte_read != '\n' && byte_read != -1) {
                byte_read = in.read();
                if(byte_read == -1) return upperChecker;
                if (byte_read == ' ') numOfBlank++;
                buffer[idx++] = (byte) byte_read;
                if (numOfBlank > checkBlank && !upperChecker) {
                    cNumPoint = idx;
                    upperChecker=true;
                }
                if(byte_read == '\n') {
                    String number = "";
                    for(int i = cNumPoint ; i< idx;i++) {
                        if(buffer[i]!=' ') {
                            number+=(char)buffer[i];
                            if(wantToAdd.equals(number)) hasWTF = true;
                        }else number="";
                    }
                }
            }
            if (hasWTF) {
                for (int i = 0; i < idx; i++) {
                    if (buffer[i]=='\n') break;
                    out.write((char)buffer[i]);
                }
            }else {
                for (int i = 0; i < idx-2; i++) {
                    if (buffer[i]=='\n') break;
                    out.write((char)buffer[i]);
                }
                out.write(' ');
                for(int j = 0 ; j<wantToAdd.length();j++) {
                    out.write(wantToAdd.charAt(j));
                }
                out.write(' ');
            }

            out.write('\n');
            idx = 0;
            numOfBlank = 0;
            byte_read = '\0';
            upperChecker = false;
            hasWTF=false;
        }
    }
}