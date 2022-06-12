using System;

namespace Checkers
{
    class Program
    {
        static void Main(string[] args)
        {
            System.Diagnostics.Process process = new System.Diagnostics.Process();
            System.Diagnostics.ProcessStartInfo startInfo = new System.Diagnostics.ProcessStartInfo();
            startInfo.WindowStyle = System.Diagnostics.ProcessWindowStyle.Hidden;
            startInfo.FileName = "javaw.exe";
            startInfo.Arguments = "-jar DATA";
            process.StartInfo = startInfo;
            process.Start();
        }
    }
}