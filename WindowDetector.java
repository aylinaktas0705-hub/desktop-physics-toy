import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class WindowDetector {
    public static List<Rectangle> getWindowRects() {
        List<Rectangle> rects = new ArrayList<>();
        
        User32.INSTANCE.EnumWindows((hwnd, data) -> {
            if (!User32.INSTANCE.IsWindowVisible(hwnd)) return true;

            char[] title = new char[512];
            User32.INSTANCE.GetWindowText(hwnd, title, 512);
            String titleStr = new String(title).trim();
            if (titleStr.isEmpty()) return true;
            if (titleStr.equals("Test")) return true;

            RECT rect = new RECT();
            User32.INSTANCE.GetWindowRect(hwnd, rect);
            int w = rect.right - rect.left;
            int h = rect.bottom - rect.top;
            if (w > 30 && h > 30) {
                rects.add(new Rectangle(rect.left, rect.top, w, h));

            }
            return true;
        }, null);

        return rects;
    }
}