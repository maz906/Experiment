using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Diagnostics;

namespace ConsoleApplication2
{
    class Program
    {
        static void data()
        {
            KryptoGame k;

            System.IO.StreamWriter file = new System.IO.StreamWriter("c:\\users\\michael\\desktop\\krypto.txt");

            file.Write("range trials games/trial averageRatio");
            file.WriteLine();

            double solvable = 0;
            double total = 0;
            double ratio = 0;
            double averageRatio = 0;

            
            for (int N = 0; N < 500; N++)
            {
                for (int j = 0; j < 30; j++)
                {
                    for (int i = 0; i < 1000; i++)
                    {
                        k = new KryptoGame(N);
                        if (k.isSolvable())
                        {
                            solvable++;
                        }
                        total++;
                    }

                    ratio = solvable / total;
                    averageRatio += ratio;

                    
                    solvable = 0;
                    total = 0;
                }

                averageRatio = averageRatio / 50;

                int a = N + 1;

                file.Write(a + " " + 30 + " " + 100 + " " + averageRatio);
                file.WriteLine();
            }

            file.Close();
        }

        static void generateGames() 
        {
            System.IO.StreamWriter file = new System.IO.StreamWriter("c:\\users\\michael\\desktop\\kryptoGames.txt");

            
            int count = 0;

            KryptoGame k;
            while (count < 20) {

                k = new KryptoGame(10 + count);

                if (k.isSolvable())
                {
                    count++;
                    file.Write(k.toString());
                    file.WriteLine();
                }

                k.newRandom();
            }


            file.Close();

        }

        static void Main(string[] args)
        {
            List<String> list = generateDyke(1);
            System.IO.StreamWriter file = null;

            try
            {
                file = new System.IO.StreamWriter("c:\\users\\michael\\desktop\\parentheses.txt");
            }
            catch (Exception e)
            {
            }

            try
            {
                foreach (String s in list)
                {
                    file.Write(s);
                    file.WriteLine();
                }
                file.Close();
            }
            catch (Exception e)
            {
            }


        }

        //private static String[] generateDyke(int n)
        //{
        //    //String sum = "";

        //    long N = (long)n;

        //    String[] list = new String[(int) Math.Floor(combination(2 * n, n) / (double) (n + 1))];

        //    for (int i = 0; i < list.Length; i++)
        //        list[i] = "";

        //    int index = 0;
        //    int open = 0;
        //    int close = 0;

        //    int count = 1;

        //    List<String> list2 = new List<String>();

        //    list2.Add("gdsfdsaf");
        //    list2.Insert(0, list2.ElementAt(0) + "(");
        //    list2.Insert(1, list2.ElementAt(0) + ")");
        //    list2.RemoveAt(2);

            
        //    while (open < n || close < n)
        //    {
        //        open = numOfSubstrings("(", list[index]);
        //        close = numOfSubstrings(")", list[index]);

        //        if (open == close)
        //        {
        //            list[index] += "(";
        //            open++;
        //        }
        //        else if (open > close)
        //        {
        //            if (open < n)
        //            {
        //                String temp = list[index];
        //                list[index] += "(";
        //                open++;
        //                list[index + count] = temp + ")";
        //                count++;
        //                // This captures the idea that if  close < open < n, we can add "(" or ")". So we add "(" to the current index and add the current index + "(" to the next index.
        //                // The problem is, running through this loop a second time, if we again reach this step, how do we not erase our previous work?
        //            }
        //            else if (open == n)
        //            {
        //                while (close < n)
        //                {
        //                    list[index] += ")";
        //                    close++;
        //                }
        //            }
        //        }

        //        if (open == n && close == n)
        //        {
        //            for (int i = 0; i < index; i++)
        //                if (list[index].Equals(list[i]))
        //                {
        //                    list[index] = "";
        //                    index--;
        //                }

        //            index++;
        //            //sum = "";
        //            open = 0;
        //            close--;
        //            count = 0;
        //        }

        //        if (index + 1 == combination(2 * n, n) / (double) (n + 1))
        //            break;
                    
        //    }

                
            
        //    return list;
        //}

        private static List<String> generateParentheses(int n)
        {
            Random r = new Random();

            String sum = "";
            int open = 0;
            int close = 0;

            List<String> list = new List<String>();

            while (list.Count < Math.Pow(2, n))
            {
                while (open < n || close < n)
                {
                    if (r.NextDouble() <= 0.5 && close < n)
                    {
                        sum += "(";
                        close++;
                    }
                    else if (r.NextDouble() <= 1 && open < n)
                    {
                        sum += ")";
                        open++;
                    }
                }

                if (!list.Contains(sum))
                {
                    list.Add(sum);
                    open = 0;
                    close = 0;
                    sum = "";
                }
            }

            return list; 
        }

        private static List<String> generateDyke(int n)
        {
            List<String> list = generateParentheses(n);

            foreach (String s in list)
                if (moreCloseThanOpen(s))
                    list.Remove(s);

            return list;
        }

        public static int numOfSubstrings(String s1, String s2)
        {
            int count = 0;
            for (int i = 0; i < s2.Length; i++)
                if (s2.Substring(i, 1).Equals(s1))
                    count++;
            return count;
        }

        public static Boolean moreCloseThanOpen(String s1)
        {
            for (int i = 0; i < s1.Length; i++)
                if (numOfSubstrings(")", s1.Substring(0, i)) > numOfSubstrings("(", s1.Substring(0, i)))
                    return true;
            return false;
        }

        public static int combination(int n, int k)
        {
            double sum = 0;
            for (long i = 0; i < k; i++)
            {
                sum += Math.Log10(n - i);
                sum -= Math.Log10(i + 1);

            }
            return (int) Math.Pow(10, sum);
        }
    
    }
}
