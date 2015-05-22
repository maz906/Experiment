using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication2
{
    public class KryptoGame
    {
        private double A1;
        private double A2;
        private double A3;
        private double A4;
        private double A5;
        private double A6;

        private Random r;

        private int N;

        public KryptoGame(int _N)
        {
            N = _N;

            r = new Random();
            A1 = r.Next(1, N + 1);
            A2 = r.Next(1, N + 1);
            A3 = r.Next(1, N + 1);
            A4 = r.Next(1, N + 1);
            A5 = r.Next(1, N + 1);
            A6 = r.Next(1, N + 1);

        }

        public double getA1() { return this.A1; }

        public double getA2() { return this.A2; }

        public double getA3() { return this.A3; }

        public double getA4() { return this.A4; }

        public double getA5() { return this.A5; }

        public double getA6() { return this.A6; }

        public void newRandom()
        {
            r = new Random();
        }
        public Boolean isSolvable()
        {
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                    for (int k = 0; k < 5; k++)
                        if (op(op(op(A1, A2, k), A3, j), A4, i) == A5 || op(op(A1, op(A2, A3, k), j), A4, i) == A5
                             || op(op(A1, A2, k), op(A3, A4, j), i) == A5 || op(A1, op(op(A2, A3, k), A4, j), i) == A5
                                || op(A1, op(A2, op(A3, A4, k), j), i) == A5)
                            return true;

            return false;
        }

        private double op(double a, double b, int x)
        {
            switch (x)
            {
                case 0:
                    return a + b;
                case 1:
                    return a - b;
                case 2:
                    return a / b;
                case 3:
                    return a * b;
                case 4:
                    return Math.Pow(a, b);
                default:
                    return 0;
            }
        }

        public String toString()
        {
            return A1 + " " + A2 + " " + A3 + " " + A4 + " " + A5;
        }


    }
}
