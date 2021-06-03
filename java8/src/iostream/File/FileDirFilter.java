package iostream.File;

import java.io.File;
import java.io.FilenameFilter;

public class FileDirFilter implements FilenameFilter {
    //文件名过滤器，需要实现接口FilenameFilter
    @Override
    public boolean accept(File dir, String name) {
        return false;
    }

}
