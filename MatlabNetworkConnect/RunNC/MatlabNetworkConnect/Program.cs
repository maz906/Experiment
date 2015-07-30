using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using TestStack.White;
using TestStack.White.UIItems;
using TestStack.White.UIItems.Finders;
using TestStack.White.UIItems.MenuItems;
using TestStack.White.UIItems.TreeItems;
using TestStack.White.UIItems.WindowItems;
using TestStack.White.UIItems.WindowStripControls;

namespace MatlabNetworkConnect
{
    class LogIn
    {
        private const String vpn = "https://vpn.eng.utah.edu/";
        private const String ncPath = "C:\\Program Files (x86)\\Juniper Networks\\Network Connect 8.0\\dsNetworkConnect.exe"; //path to network connect
        private const String loginWindow = "Network Connect - Sign In";
        private const String name = "michaelz";
        private const String pass = "Enderwiggin1";
        private const String goButton = "Go";
        private const String signInButton = "Sign In";

        static void Main(string[] args)
        {
            Application nc = Application.Launch(ncPath);
            Window ncWindow = nc.GetWindow(loginWindow);
            //Enter the VPN address.
            TextBox server = ncWindow.Get<TextBox>(SearchCriteria.All);
            server.Enter(vpn);
            //Hit the button labeled "Go" to open the login page of the server.
            Button go = ncWindow.Get<Button>(goButton);
            go.Click();
            //Enter login credentials.
            ncWindow.RightClickAt(new System.Windows.Point(744, 422));
            ncWindow.Enter("test");
            Panel login = ncWindow.Get<Panel>(SearchCriteria.ByAutomationId("202"));
            TextBox temp = (TextBox) usr;
            //Hit the button labeled "Sign in".
            Button sign = ncWindow.Get<Button>(signInButton);
            sign.Click();
            nc.Close(); //TODO: remove this later.
        }
    }

}
