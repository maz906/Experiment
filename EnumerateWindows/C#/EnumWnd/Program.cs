using System;
using System.Text;
using System.Runtime.InteropServices;

namespace EnumWnd
{
    class Program
    {
        protected delegate bool EnumWindowsProc(IntPtr hWnd, IntPtr lParam);
        [DllImport("user32.dll", CharSet = CharSet.Unicode)]
        protected static extern int GetWindowText(IntPtr hWnd, StringBuilder strText, int maxCount);
        [DllImport("user32.dll", CharSet = CharSet.Unicode)]
        protected static extern int GetWindowTextLength(IntPtr hWnd);
        [DllImport("user32.dll")]
        protected static extern bool EnumWindows(EnumWindowsProc enumProc, IntPtr lParam);
        [DllImport("user32.dll")]
        protected static extern bool IsWindowVisible(IntPtr hWnd);
        [DllImport("user32.dll")]
        protected static extern int EnumChildWindows(IntPtr hWnd, EnumWindowsProc enumProc, IntPtr lParam);

        protected static bool EnumTheWindows(IntPtr hWnd, IntPtr lParam)
        {
            int size = GetWindowTextLength(hWnd);
            if (size++ > 0 && IsWindowVisible(hWnd))
            {
                StringBuilder sb = new StringBuilder(size);
                GetWindowText(hWnd, sb, size);
                Console.WriteLine("Window: " + sb.ToString());
                EnumChildWindows(IntPtr.Zero, new EnumWindowsProc(other), IntPtr.Zero);
            }
            return true;
        }

        protected static bool other(IntPtr hWnd, IntPtr lParam)
        {
            int size = GetWindowTextLength(hWnd);
            if (size++ > 0 && IsWindowVisible(hWnd))
            {
                StringBuilder sb = new StringBuilder(size);
                GetWindowText(hWnd, sb, size);
                Console.WriteLine("Subwindow(?): " + sb.ToString());
            }
            return true;
        }

        static void Main(string[] args)
        {
            EnumWindows(new EnumWindowsProc(EnumTheWindows), IntPtr.Zero);
#if DEBUG
            Console.ReadKey();
#endif
        }
    }
}
